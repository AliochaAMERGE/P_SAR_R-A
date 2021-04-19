package utils;

import java.util.ArrayList;

import core.Node;
import core.graph.Graph;

public class Common_methods {

	public static boolean isIn(Node n, ArrayList<Node> ar) {
		// verifie si chacun des nodes de ar a le meme id que n. return true si
		// c'est le
		// cas
		for (Node neigh : ar) {
			if (neigh.getId() == n.getId()) {
				return true;
			}
		}
		return false;
	}

	public static ArrayList<Node> transform(int[] tab, Graph g) {
		ArrayList<Node> res = new ArrayList<>();
		if (tab == null) {
			return null;
		}
		for (int i = 0; i < tab.length; i++) {
			for (Node n : g.getList()) {
				if (n.getId() == tab[i]) {
					res.add(n);
				}
			}
		}
		return res;
	}

	public static ArrayList<Node> reverseMIS(ArrayList<Node> list, Graph g) {
		// renvoie une arraylist composée de tout les nodes n'appartenant pas a
		// l'ensemble de point stable
		ArrayList<Node> res = new ArrayList<Node>();
		for (Node n : g.getList()) {
			if (!isIn(n, list)) {
				res.add(n);
			}
		}
		return res;
	}

	public static int[] reverseArray(int[] list, Graph g) {
		// renvoie une arraylist composée de tout les nodes n'appartenant pas a
		// l'ensemble de point stable
		int taille = (g.getList().size() - list.length);
		int res[] = new int[taille];
		boolean bool;
		int cpt = 0;
		for (Node n : g.getList()) {
			bool = true;
			for (int i : list) {
				if (i == n.getId()) {
					bool = false;
					break;
				}
			}
			if (bool) {
				res[cpt++] = n.getId();
			}

		}
		return res;
	}
}
