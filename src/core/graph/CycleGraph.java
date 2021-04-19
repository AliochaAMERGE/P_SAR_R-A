package core.graph;

import java.util.ArrayList;

import core.Node;

/**
 * a chained graph
 * built simply by adding vertices 
 * between every points two by two until a cycle is made
 */
public class CycleGraph extends Graph {

	public CycleGraph(int nb_nodes) {
		node_list = CycleGraphGenerator(nb_nodes);
	}

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
