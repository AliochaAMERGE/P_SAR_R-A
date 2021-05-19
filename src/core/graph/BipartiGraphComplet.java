package core.graph;

import java.util.ArrayList;

import core.Node;

/**
 * Implémentation d'un graphe Biparti Complet C'est un graphe tel que tous les
 * noeuds peuvent être séparer en deux ensembles A et B distincts tel que tous
 * les noeuds de l'ensemble A (resp B) sont liés à l'ensemble des neoeuds de B
 * (resp A)
 */
public class BipartiGraphComplet extends Graph {

    /**
	 * Constructeur d'un graphe biparti Complet
	 * 
	 * @param nb_nodes le nombre de noeuds du graphe
	 */
    public BipartiGraphComplet(int nb_nodes) {
        node_list = BipartiGraphCompletGenerator(nb_nodes);
    }

    /**
     * utilitaire du constructor
     * @param nb_nodes nombre de noeuds du graphe
     * @return le graphe biparti complet
     */
    private ArrayList<Node> BipartiGraphCompletGenerator(int nb_nodes) {
        ArrayList<Node> res = new ArrayList<Node>();
        ArrayList<Node> rightside = new ArrayList<Node>();
        ArrayList<Node> leftside = new ArrayList<Node>();
        if (nb_nodes < 1) {
            return null;
        }
        while (leftside.size() == 0 || rightside.size() == 0) {
            rightside = new ArrayList<Node>();
            leftside = new ArrayList<Node>();
            for (int i = 0; i < nb_nodes; i++) { // complexité : n
                Node n = new Node(i);

                if ((int) (Math.random() * 2) == 1) {
                    rightside.add(n);
                } else {
                    leftside.add(n);
                }
            }
        }
        for (Node nl : leftside) {
            for (Node nr : rightside) {
                nl.addNeighbor(nr);
            }
        }
        res.addAll(rightside);
        res.addAll(leftside);
        return res;
    }

}
