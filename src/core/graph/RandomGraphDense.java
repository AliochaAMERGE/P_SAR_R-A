package core.graph;

import java.util.ArrayList;

import core.Node;
import utils.Common_methods;
import utils.Robustess_detector;

/**
 * a random graph based on the density of a graph
 * density = (2 * |E|) / (|V| * (|V| - 1))
 * E the Edges, V the vertices
 * we add a certain number of vertices until the density desired is reached,
 * if the graph is bit connected at the end, we manually add the desired vertices
 */

public class RandomGraphDense extends Graph {
	public RandomGraphDense(int nb_nodes, double min) {
		node_list = RandomGraphGenerator(nb_nodes, min);
	}

	public RandomGraphDense(int nb_nodes) {
		node_list = RandomGraphGenerator(nb_nodes, 0.5);
	}

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
		while (!Robustess_detector.isLinked(res.get(0), res) || nb_liaison / Math.pow(length, 2) <= min / 2.106) {
			n1 = (int) (Math.random() * (length));
			do {
				n2 = (int) (Math.random() * (length));
			} while (n1 == n2);
			if (!Common_methods.isIn(res.get(n1), res.get(n2).getNeighbors())) {
				res.get(n1).addNeighbor(res.get(n2));
				nb_liaison++;
			}
		}
		return res;
	}

	public boolean isEnoughLiaison(int min, ArrayList<Node> list) {
		for (Node n : list) {
			if (n.getNeighbors().size() < min) {
				return false;
			}
		}
		return true;
	}
}
