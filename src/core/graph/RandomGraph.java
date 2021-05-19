package core.graph;

import java.util.ArrayList;

import core.Node;
import utils.Common_methods;
import utils.Robustess_detector;

/**
 * Implémentation d'un graphe défini aléatoirement en fonction de son nombre de
 * voisins Seule contrainte : le graphe résultant est connexe
 */
public class RandomGraph extends Graph {

	/**
	 * Constructeur d'un graphe aléatoire
	 * 
	 * @param nb_nodes le nombre de noeuds
	 */
	public RandomGraph(int nb_nodes) {
		node_list = RandomGraphGenerator(nb_nodes);
	}

	/**
	 * utilitaire du constructeur d'un graphe aléatoire ajoute des liaisons entre
	 * les noeuds tant que le graphe n'est pas connexe
	 * 
	 * @param length le nombre de noeuds
	 * @return le graphe aléatoire
	 */
	public ArrayList<Node> RandomGraphGenerator(int length) {
		ArrayList<Node> res = new ArrayList<Node>();
		if (length < 1) {
			return null;
		}
		for (int i = 0; i < length; i++) {
			res.add(new Node(i));
		}
		int n1, n2;
		while (!Robustess_detector.isLinked(res.get(0), res)) {
			n1 = (int) (Math.random() * (length));
			n2 = (int) (Math.random() * (length));
			if (n1 != n2 && !Common_methods.isIn(res.get(n1), res.get(n2).getNeighbors())) {
				res.get(n1).addNeighbor(res.get(n2));
			}
			if (res.get(n1).getNeighbors().size() > 4 || res.get(n2).getNeighbors().size() > 4) {
				return RandomGraphGenerator(length);
			}
		}
		return res;
	}
}
