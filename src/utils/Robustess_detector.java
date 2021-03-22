package utils;

import java.util.ArrayList;

import core.Node;
import core.graph.Graph;

public class Robustess_detector {

	public static void testCut(ArrayList<ArrayList<Node>> aalist, Graph g) {
		// prend en parametre la liste de mis. puis cré un graph specifique de
		// test pour
		// chacun des nodes faibles. ces grapes sont alors testé

		for (ArrayList<Node> list : aalist) {
			ArrayList<Node> reverselist = Common_methods.reverseMIS(list, g);
			for (Node n : reverselist) {
				ArrayList<Node> listgraph = new ArrayList<Node>();
				for (Node n_neigh : n.getNeighbors()) {
					// creation de l'arraylist constitué des points fort voisin
					// de n utilisé dans le
					// constructeur pour pouvoir cree un graph test
					if (Common_methods.isIn(n_neigh, list)) {
						listgraph.add(n_neigh);
					}
				}
				Graph graph = new Graph(g, n, listgraph);// on genere un nouveau
															// graph sans les
															// connections pour
															// le test
				System.out.println(graph);
				System.out.println("" + isLinked(n, graph.getList()) + "\n\n");
			}
		}
	}

	public static boolean isLinked(Node node, ArrayList<Node> list) {
		// cette methode permet de voir si il existe un cheminement d'arrete le
		// menant
		// jusqu'a tout les autres nodes
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
			System.out.println(
					"dans Robustess_detector.isLinked, node n'appatient pas a list");
		}
		for (Node neigh : n.getNeighbors()) {
			voisin_a_voir.add(neigh);
		}
		if (voisin_a_voir.isEmpty()) {
			System.out.println("ke");
			return false;
		}
		while (!voisin_a_voir.isEmpty()) {
			for (Node neigh : voisin_a_voir.get(0).getNeighbors()) {
				if (!Common_methods.isIn(neigh, voisin_a_voir)
						&& !Common_methods.isIn(neigh, voisin_vu)) {
					voisin_a_voir.add(neigh);
				}
			}
			voisin_vu.add(voisin_a_voir.remove(0));
		}
		// a ce stade voisin_vu represente l'arraylist des nodes connecté au
		// node n. on
		// a juste a la comparer avec la liste des nodes du graph.
		boolean ispresent;
		for (Node node_graph : list) {

			ispresent = false;
			for (Node node_vu : voisin_vu) {
				// if(isIn(node_vu, g.getList()){ispresent = true;}
				if (node_vu.getId() == node_graph.getId()) {
					ispresent = true;
				}
			}
			if (ispresent == false) {
				System.out.println("there is a cut");
				return false;
			}
		}
		return true;
	}

}
