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

	/**
	 * Constructeur utilitaire pour les tests
	 * 
	 * @param g     un graphe
	 * @param n     un noeud de départ
	 * @param alist une liste de noeuds
	 */
	public Graph(Graph g, Node n, ArrayList<Node> alist) {
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

	/**
	 * Detecteur de graphe Biparti
	 * 
	 * @return true si le graphe est un Biparti false sinon
	 */
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

	/**
	 * Detecteur de graphe Biparti Complet
	 * 
	 * @return true si le graphe est un Biparti Complet false sinon
	 */
	public boolean isBipartiComplet() {

		if (node_list.size() == 1 || node_list.size() == 2) {
			return true;
		}
		// noeud init
		Node start = node_list.get(0);

		List<Node> right = new ArrayList<Node>();
		List<Node> left = new ArrayList<Node>();

		right.add(start);
		for (Node n_n : start.getNeighbors()) {
			// Supposons aucun doublons chez les voisins
			left.add(n_n);
		}
		start = left.get(0);
		for (Node n_n : start.getNeighbors()) {
			if (right.contains(n_n) && n_n != right.get(0)) {
				return false;
			} else if (!left.contains(n_n) && !right.contains(n_n)) {
				right.add(n_n);
			}
		}
		// maintenant nous pourrions verifié chaque voisins de chaque noeuds de droite
		// ou gauche, et verifié qu'il est present dans gauche resp. droite
		// Ou nous pourrions verifié avec le nombres de voisins, ce dernier doit etre
		// egal au nombre d'element de l'ensemble opposé
		for (Node n : right) {
			if (n.getNeighbors().size() != left.size()) {
				return false;
			}
		}
		for (Node n : left) {
			if (n.getNeighbors().size() != right.size()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retourne un noeud en fonction de son id
	 * @param id
	 * @return Node 
	 */
	public Node getNode(int id) {
		return node_list.get(id);
	}

	/**
	 * Detecteur de graphe Sputnik
	 * 
	 * @return true si le graphe est un graphe Sputnik false sinon
	 */
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

	/**
	 * Utilitaire pour le detecteur de Sputnik
	 * detecte une antenne (un voisin isolé)
	 * @param n un noeud
	 * @return si le noeud dispose d'un voisin isolé
	 */
	private boolean hasALoneNeighbor(Node n) {

		for (Node neigh : n.getNeighbors()) {
			if (neigh.getNeighbors().size() == 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Utilitaire pour le detecteur de Sputnik
	 * vérifie si un noeud est dans une boucle
	 * @param n un noeud
	 * @return si le noeud est dans une boucle
	 */
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

	/**
	 * Comparateur pour les Heuristiques de tri 
	 * Tri en fonction du nombre de voisin
	 */

	public class compa implements Comparator<Node> {

		/**
		 * Comparateur du nombre de voisin entre deux noeuds
		 */
		@Override
		public int compare(Node n1, Node n2) {

			// a negative integer, zero, or a positive integer as the first argument is less
			// than, equal to, or greater than the second.
			return n1.getNeighbors().size() - n2.getNeighbors().size();
		}

	}

	/**
	 * Heuristique de tri de la liste des noeuds en fonctions du nombre de voisins 
	 */
	public void optimize() {
		Collections.sort(node_list, new compa());
	}

	/**
	 *  Comparateur pour les Heuristiques de tri
	 *  tri en fonction du nombre de voisins dans le MIS
	 */
	public class compa2 implements Comparator<Node> {

		/**
		 * comparateur du nombre de voisins dans le MIS
		 */
		@Override
		public int compare(Node n1, Node n2) {

			// a negative integer, zero, or a positive integer as the first argument is less
			// than, equal to, or greater than the second.
			return (n1.getValue() / n1.getNeighbors().size()) - (n2.getValue() / n2.getNeighbors().size());
		}

	}

	/**
	 * Heuristique de tri de la liste de noeud en fonction du nombre de voisins du MIS
	 * @param list la liste de noeuds
	 */
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

	public class compaREVERSE implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {

			// a negative integer, zero, or a positive integer as the first argument is less
			// than, equal to, or greater than the second.
			return n2.getNeighbors().size() - n1.getNeighbors().size();
		}

	}

	public void optimizeREVERSE() {
		Collections.sort(node_list, new compaREVERSE());
	}

	public class compa2REVERSE implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {

			// a negative integer, zero, or a positive integer as the first argument is less
			// than, equal to, or greater than the second.
			return (n2.getValue() / n2.getNeighbors().size()) - (n1.getValue() / n1.getNeighbors().size());
		}

	}

	public void optimizeMISREVERSE(ArrayList<Node> list) {
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
		Collections.sort(node_list, new compa2REVERSE());
	}

	/**
	 * Detecteur de Cycle
	 * @return so le graphe est un cycle
	 */
	public boolean isCycle() {
		for (Node n : node_list) {
			if (n.getNeighbors().size() != 2) {
				return false;
			}
		}
		return true;
	}
}
