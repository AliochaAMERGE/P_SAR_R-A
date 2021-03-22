package core.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import core.Node;
import utils.Common_methods;

/**
 * a graph
 * defined by a list of Nodes objects
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
	 * @param list : a list of Node
	 */
	public Graph(ArrayList<Node> n_list) {
		this.node_list = n_list;
	}

	/**
	 * build a graph from a text File
	 * @param path text file path
	 * @throws FileNotFoundException
	 */
	public Graph(String path) throws FileNotFoundException {
		node_list = new ArrayList<Node>();
		this.load(path);
	}

	public Graph(Graph g, Node n, ArrayList<Node> alist) {
		// ce constructeur sert a la creation des graphs de test
		// TODO trouver une meilleure implementation pour les tests
		ArrayList<Node> res = new ArrayList<Node>();
		for (Node node : g.getList()) {
			Node resnode;
			if (node.getId() == n.getId()) {
				resnode = new Node(n.getId());
				for (Node n_neigh : n.getNeighbors()) {
					if (!Common_methods.isIn(n_neigh, alist)) {
						resnode.addNeighbor(n_neigh);
					}
				}
			} else {
				if (Common_methods.isIn(node, alist)) {
					resnode = new Node(node.getId());
					for (Node n_neigh : node.getNeighbors()) {
						if (n_neigh.getId() != n.getId()) {
							resnode.addNeighbor(n_neigh);
						}
					}
				} else {
					resnode = node;
				}
			}
			res.add(resnode);
		}
		node_list = res;
	}

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
		// list=temp;

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

	public String toString() {

		StringBuilder sb = new StringBuilder();
		for (Node n : node_list) {
			sb.append(n.toString() + "\n");
		}
		return sb.toString();
	}

	public int getSize() {
		return this.node_list.size();
	}

	public ArrayList<Node> getList() {
		return this.node_list;
	}
}
