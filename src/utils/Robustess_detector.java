package utils;

import java.util.ArrayList;

import core.Node;
import core.graph.Graph;

public class Robustess_detector {

	/**
	 * Vérifie la robustesse du MIS testé
	 * @param aalist 
	 * @param g
	 * @return si robuste
	 */
	public static boolean isRobust(int[] aalist, Graph g) {
		// prend en parametre la liste de mis. puis cré un graph specifique de
		// test pour
		// chacun des nodes faibles. ces grapes sont alors testé
		ArrayList<Node> list = Common_methods.transform(aalist, g);
		ArrayList<Node> reverselist = Common_methods.reverseMIS(list, g);

		boolean isMISrobust = true;
		for (Node n : reverselist) {
			ArrayList<Node> listgraph = new ArrayList<Node>();
			for (Node n_neigh : n.getNeighbors()) {
				// creation de l'arraylist constitué des points fort voisin de n utilisé dans le
				// constructeur pour pouvoir cree un graph test

				if (Common_methods.isIn(n_neigh, list)) {
					listgraph.add(n_neigh);
				}
			}
			Graph graph = new Graph(g, n, listgraph);
			// on genere un nouveau graph sans les connections pour le test
			if (isLinked(n, graph.getList())) {
				isMISrobust = false;
				break;
			}
		}
		if (isMISrobust) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Vérifie la connexité du graphe
	 * @param node
	 * @param list
	 * @return
	 */
	public static boolean isLinked(Node node, ArrayList<Node> list) {
		// cette methode permet de voir si il existe un cheminement d'arrete le
		// menant jusqu'a tout les autres nodes
		ArrayList<Node> voisin_a_voir = new ArrayList<Node>();
		ArrayList<Node> voisin_vu = new ArrayList<Node>();
		voisin_vu.add(node);
		Node n = null;
		for (Node fut_n : list) {
			if (fut_n.getId() == node.getId()) {
				n = fut_n;
			}
		}
		if (n == null) {
			// node n'appatient pas a list
			return false;
		}
		for (Node neigh : n.getNeighbors()) {
			voisin_a_voir.add(neigh);
		}
		if (voisin_a_voir.isEmpty()) {
			return false;
		}
		while (!voisin_a_voir.isEmpty()) {
			for (Node neigh : voisin_a_voir.get(0).getNeighbors()) {
				if (!Common_methods.isIn(neigh, voisin_a_voir) && !Common_methods.isIn(neigh, voisin_vu)) {
					voisin_a_voir.add(neigh);
				}
			}
			voisin_vu.add(voisin_a_voir.remove(0));
		}
		// A ce stade voisin_vu represente l'arraylist des nodes connecté au node n.
		// On a juste à la comparer avec la liste des nodes du graph.
		boolean ispresent;
		for (Node node_graph : list) {

			ispresent = false;
			for (Node node_vu : voisin_vu) {
				if (node_vu.getId() == node_graph.getId()) {
					ispresent = true;
				}
			}
			if (ispresent == false) {
				return false;
			}
		}
		return true;
	}

}
