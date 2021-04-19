package core.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import core.Node;
import utils.Common_methods;

/**
 * a graph defined by a list of Nodes objects
 *
 */
public class Graph {

	ArrayList<Node> node_list;

	/**
	 * an empty graph constructor
	 */
	public Graph() {
		node_list = new ArrayList<Node>();
	}

	/**
	 * a graph constructor from a list of Nodes
	 * 
	 * @param list : a list of Node
	 */
	public Graph(ArrayList<Node> n_list) {
		this.node_list = n_list;
	}

	/**
	 * build a graph from a text File
	 * 
	 * @param path : text file path
	 * @throws FileNotFoundException
	 */
	public Graph(String path) throws FileNotFoundException {
		node_list = new ArrayList<Node>();
		this.load(path);
	}

	public Graph(Graph g, Node n, ArrayList<Node> alist) {
		// ce constructeur sert a la creation des graphs de test
		ArrayList<Node> res = new ArrayList<Node>();
		ArrayList<Node> intermediaire = new ArrayList<Node>();
		for (Node node : g.getList()) {
			intermediaire.add(new Node(node.getId()));
		}
		Node resnode;
		for (Node node : g.getList()) {
			if (node.getId() == n.getId()) {
				resnode = intermediaire.get(node.getId());
				for (Node n_neigh : node.getNeighbors()) {
					if (!Common_methods.isIn(n_neigh, alist)) {
						resnode.addNeighborUnidirectionnel(intermediaire.get(n_neigh.getId()));
					}
				}
			} else {
				resnode = intermediaire.get(node.getId());
				for (Node n_neigh : node.getNeighbors()) {
					if (!Common_methods.isIn(node, alist)) {
						resnode.addNeighborUnidirectionnel(intermediaire.get(n_neigh.getId()));//
					}
				}
			}
			res.add(resnode);
		}
		node_list = res;
	}

	/**
	 * load a graph from a text File
	 * 
	 * @param path : text file path
	 * @throws FileNotFoundException
	 */
	public void load(String path) throws FileNotFoundException {
		Scanner myReader = new Scanner(new File(path));
		String data;

		// read nodes list (first line)
		data = myReader.nextLine();

		String[] nodes = data.split(" ");

		for (String str : nodes) {
			System.out.print(" " + str);
			node_list.add(new Node(Integer.parseInt(str)));
		}
		System.out.print("\n");

		// read node's neighbor
		int cptligne = 0, cptcol;
		int l = node_list.size();
		int[][] ids = new int[l][l];
		while (myReader.hasNextLine()) {
			data = myReader.nextLine();
			String[] tabdata = data.split(" ");
			cptcol = 0;
			for (String s : tabdata) {
				ids[cptligne][cptcol] = Integer.parseInt(s);
				cptcol++;
			}
			cptligne++;
		}
		for (int i = 0; i < l; i++) {
			ArrayList<Node> neighbors = new ArrayList<Node>();
			for (int j = 0; j < l; j++) {
				if (ids[i][j] != 0) {
					neighbors.add(node_list.get(j));
				}
			}
			node_list.get(i).setNeighbors(neighbors);
		}
		myReader.close();
	}

	/**
	 * save a graph to a text file
	 * 
	 * @param path : text file path
	 * @throws IOException
	 */
	public void save(String path) throws IOException {
		FileWriter myWriter = new FileWriter(path);

		for (Node node : this.getList()) {
			myWriter.write(node.getId() + " ");
		}
		myWriter.write("\n");

		for (Node node : this.getList()) {
			for (int i = 0; i < this.getSize(); i++) {
				if (node.isNeighborbyId(i)) {
					myWriter.write("1");
				} else {
					myWriter.write("0");
				}
				if (i != this.getSize()) {
					myWriter.write(" ");
				}
			}
			myWriter.write("\n");
		}
		myWriter.close();
	}

	/**
	 * graph to string : Node.toString()
	 */
	public String toString() {

		StringBuilder sb = new StringBuilder();
		for (Node n : node_list) {
			sb.append(n.toString() + "\n");
		}
		return sb.toString();
	}

	/**
	 * @return int : number of Node of the graph
	 */
	public int getSize() {
		return this.node_list.size();
	}

	/**
	 * @return list of Node of the graph
	 */
	public ArrayList<Node> getList() {
		return this.node_list;
	}

	public boolean isBiparti() {// interet : tester la propriété enoncé et ca s'avère juste : is bipartite ->
		// robust
		if (node_list.size() == 1 || node_list.size() == 2) {
			return true;
		}
		// noeud init
		Node start = node_list.get(0);

		// liste contenant les couleurs des differents noeuds, ranger par ID
		int color[] = new int[node_list.size()];
		// les couleurs possibles sont 1 et 2

		// on assigne la non_couleur a 0

		color[start.getId()] = 1;

		// queue des voisins du noeud init
		LinkedList<Node> r = new LinkedList<Node>();
		r.add(start);

		while (!r.isEmpty()) {
			Node n = r.poll();
			for (Node child : n.getNeighbors()) {
				// si pas de couleur
				if (color[child.getId()] == 0) {
					// assigne la couleur opposee a son pere
					color[child.getId()] = (color[n.getId()] == 1 ? 2 : 1);
					r.add(child);
				} else if (color[child.getId()] == color[n.getId()]) {
					// meme couleur
					return false;
				}
			}
		}
		// tout a ete teste et pas de probleme, notre graphe est biparti
		return true;
	}

	public Node getNode(int num) {
		return node_list.get(num);
	}

	public boolean isSpoutnik() {
		for (Node n : node_list) {
			if (isInABoucle(n)) {
				if (!hasALoneNeighbor(n)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean hasALoneNeighbor(Node n) {

		for (Node neigh : n.getNeighbors()) {
			if (neigh.getNeighbors().size() == 1) {
				return true;
			}
		}
		return false;
	}

	public boolean isInABoucle(Node n) {
		for (Node pdd : n.getNeighbors()) {
			List<Node> vu = new ArrayList<Node>();
			vu.add(pdd);
			List<Node> avoir = new ArrayList<Node>();
			for (Node init : pdd.getNeighbors()) {
				if (init.getId() != n.getId()) {
					avoir.add(init);
				}
			}
			// avoir.addAll(pdd.getNeighbors());
			while (!avoir.isEmpty()) {
				for (Node neigh : avoir.get(0).getNeighbors()) {
					if (!vu.contains(neigh) && !avoir.contains(neigh)) {
						if (neigh.getId() == n.getId()) {
							return true;
						}
						avoir.add(neigh);
					}
				}
				vu.add(avoir.remove(0));
			}
		}

		return false;
	}

	public class compa implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {

			// a negative integer, zero, or a positive integer as the first argument is less
			// than, equal to, or greater than the second.
			return n1.getNeighbors().size() - n2.getNeighbors().size();
		}

	}

	public void optimize() {
		Collections.sort(node_list, new compa());
	}

	public class compa2 implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {

			// a negative integer, zero, or a positive integer as the first argument is less
			// than, equal to, or greater than the second.
			return (n1.getValue() / n1.getNeighbors().size()) - (n2.getValue() / n2.getNeighbors().size());
		}

	}

	public void optimizeMIS(ArrayList<Node> list) {
		int cpt;
		for (Node n : node_list) {
			cpt = 0;
			for (Node neigh : n.getNeighbors()) {
				if (list.contains(neigh)) {
					cpt++;
				}
			}
			n.setValue(cpt);

		}
		Collections.sort(node_list, new compa2());
	}

}
