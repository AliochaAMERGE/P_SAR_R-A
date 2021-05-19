package core.graph;

import java.util.ArrayList;

import core.Node;
import utils.Common_methods;
import utils.Robustess_detector;

/**
 * Implémentation d'un graphe aléatoire défini par son nombre de voisin et sa
 * densité le graphe résultant est connexe, et la densité est une densité
 * minimum atteinte
 */
public class RandomGraphDense extends Graph {

	/**
	 * Constructeur d'un graphe aléatoire en fonction de la densité
	 * 
	 * @param nb_nodes le nombre de noeud
	 * @param density
	 */
	public RandomGraphDense(int nb_nodes, double density) {
		node_list = RandomGraphGenerator(nb_nodes, density);
	}

	/**
	 * Constructeur d'un graphe aléatoire avec une densité fixée à l'avance
	 * 
	 * @param nb_nodes le nombre de Node
	 */
	public RandomGraphDense(int nb_nodes) {
		node_list = RandomGraphGenerator(nb_nodes, 0.3);
	}

	/**
	 * Utilitaire pour la construction d'un graphe aléatoire
	 * 
	 * @param length le nombre de neouds
	 * @param min la densité (minimum)
	 * @return le graphe aléatoire
	 */
	public ArrayList<Node> RandomGraphGenerator(int length, double min) {
		int nb_liaison = 0;
		ArrayList<Node> res = new ArrayList<Node>();
		if (length < 1) {
			return null;
		}
		for (int i = 0; i < length; i++) {
			res.add(new Node(i));
		}
		int n1, n2;
		while (!Robustess_detector.isLinked(res.get(0), res)
				|| nb_liaison / Math.pow(length, 2) <= min / 2.106 /* !isEnoughLiaison(min, res) */) {
			n1 = (int) (Math.random() * (length));
			do {
				n2 = (int) (Math.random() * (length));
			} while (n1 == n2);
			// System.out.println(nb_liaison/Math.pow(length, 2)+" et "+min);
			if (!Common_methods.isIn(res.get(n1), res.get(n2).getNeighbors())) {
				res.get(n1).addNeighbor(res.get(n2));
				nb_liaison++;
			}
		}
		return res;
	}

	/**
	 * utilitaire pour la création d'un graphe aléatoire
	 * @param min la densité à atteindre
	 * @param list la liste de noeuds du graphe
	 * @return si la densité est atteinte
	 */
	public boolean isEnoughLiaison(int min, ArrayList<Node> list) {
		for (Node n : list) {
			if (n.getNeighbors().size() < min) {
				return false;
			}
		}
		return true;
	}
}
