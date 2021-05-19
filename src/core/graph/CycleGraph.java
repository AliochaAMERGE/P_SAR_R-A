package core.graph;

import java.util.ArrayList;

import core.Node;

/**
 * a chained graph
 *
 */
public class CycleGraph extends Graph {

	/**
	 * Constructeur d'un graphe
	 * 
	 * @param nb_nodes le nombre de noeud du graphe
	 */
	public CycleGraph(int nb_nodes) {
		node_list = CycleGraphGenerator(nb_nodes);
	}

	/**
	 * utilitaire du constructeur
	 * 
	 * @param length le nombre de noeud du graphe
	 * @return le Cycle
	 */
	public static ArrayList<Node> CycleGraphGenerator(int length) {
		ArrayList<Node> res = new ArrayList<Node>();
		for (int i = 0; i < length; i++) {
			res.add(new Node(i));
		}
		for (int i = 0; i < length; i++) {
			ArrayList<Node> neigh = new ArrayList<Node>();
			neigh.add(res.get((i - 1 + length) % length));
			neigh.add(res.get((i + 1) % length));
			res.get(i).setNeighbors(neigh);
		}
		return res;
	}
}
