package core.graph;

import java.util.ArrayList;

import core.Node;
import utils.Common_methods;
import utils.Robustess_detector;

public class SputnikGraph extends Graph {
	public SputnikGraph(int nb_nodes) {
		node_list = SputnikGraphGenerator(nb_nodes);
	}

	private ArrayList<Node> SputnikGraphGenerator(int nb_nodes) {

		// code de random graph
		ArrayList<Node> res = HalfRandomSputnikGenerator(nb_nodes / 2);
		for (int i = nb_nodes / 2; i < nb_nodes; i++) {
			Node n = new Node(i);
			res.add(n);
			res.get((i - nb_nodes / 2) % (nb_nodes / 2)).addNeighbor(res.get(i));
			System.out.println((i - nb_nodes / 2) % (nb_nodes / 2) + "   " + i);
		}
		return res;
	}

	public ArrayList<Node> HalfRandomSputnikGenerator(int nb_nodes) {// code de random graph
		ArrayList<Node> res = new ArrayList<Node>();
		for (int i = 0; i < nb_nodes; i++) { // complexité : n
			res.add(new Node(i));
		}
		int n1, n2;
		while (!Robustess_detector.isLinked(res.get(0), res)) { // complexité : O((n-2)+(n-3)+...+1)
			n1 = (int) (Math.random() * (nb_nodes));
			do {
				n2 = (int) (Math.random() * (nb_nodes));
			} while (n1 == n2);
			if (!Common_methods.isIn(res.get(n1), res.get(n2).getNeighbors())) {
				// System.out.println(n1 +" "+n2);
				res.get(n1).addNeighbor(res.get(n2));
			}

		}
		return res;
	}
}
