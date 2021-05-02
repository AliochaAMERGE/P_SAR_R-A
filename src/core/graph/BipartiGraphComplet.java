package core.graph;

import java.util.ArrayList;

import core.Node;

public class BipartiGraphComplet extends Graph {

    public BipartiGraphComplet(int nb_nodes) {
        node_list = BipartiGraphCompletGenerator(nb_nodes);
    }

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
