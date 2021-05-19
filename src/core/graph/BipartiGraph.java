package core.graph;

import java.util.ArrayList;

import core.Node;
import utils.Common_methods;
import utils.Robustess_detector;

/**
 * Implémentation d'un graphe Biparti un graphe biparti est défini tel que nous
 * pouvons séparer l'ensemble des noeuds en deux ensembles A et B distincts et
 * tel que tous les noeuds de A (resp B) ne sont liés qu'à des noeuds de B (resp
 * A)
 */
public class BipartiGraph extends Graph {
	/**
	 * Constructeur d'un graphe biparti
	 * 
	 * @param nb_nodes le nombre de noeuds du graphe
	 */
	public BipartiGraph(int nb_nodes) {
		node_list = BipartiGraphGenerator(nb_nodes, 5);
	}

	/**
	 * Constructeur complémentaire majorant le nombre de voisin d'un noeud
	 * 
	 * @param nb_nodes     le nombre de noeuds
	 * @param nbvoisinsmax le nombre maximum de voisin d'un noeud
	 */
	public BipartiGraph(int nb_nodes, int nbvoisinsmax) {
		node_list = BipartiGraphGenerator(nb_nodes, nbvoisinsmax);
	}

	/**
	 * utilitaire du constructeur
	 * 
	 * @param length nombre de noeud du graphe
	 * @param nbmax  nombre de voisin max d'un noeud
	 * @return le graphe biparti
	 */
	public ArrayList<Node> BipartiGraphGenerator(int length, int nbmax) {
		ArrayList<Node> res = new ArrayList<Node>();
		ArrayList<Node> rightside = new ArrayList<Node>();
		ArrayList<Node> leftside = new ArrayList<Node>();
		if (length < 1) {
			return null;
		}
		while (leftside.size() == 0 || rightside.size() == 0) {
			rightside = new ArrayList<Node>();
			leftside = new ArrayList<Node>();
			for (int i = 0; i < length; i++) { // complexité : n
				Node n = new Node(i);

				if ((int) (Math.random() * 2) == 1) {
					rightside.add(n);
				} else {
					leftside.add(n);
				}
			}
		}
		res.addAll(leftside);
		res.addAll(rightside);
		int right, left;
		while (!Robustess_detector.isLinked(res.get(0), res)) {
			right = (int) (Math.random() * (rightside.size()));
			left = (int) (Math.random() * (leftside.size()));
			if (!Common_methods.isIn(rightside.get(right), leftside.get(left).getNeighbors())) {
				rightside.get(right).addNeighbor(leftside.get(left));
			}
			if (rightside.get(right).getNeighbors().size() > nbmax
					|| leftside.get(left).getNeighbors().size() > nbmax) {
				return BipartiGraphGenerator(length, nbmax);
			}
		}
		return res;
	}
}
