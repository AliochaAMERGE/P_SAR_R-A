package utils;

import java.util.ArrayList;
import java.util.Arrays;

import core.Node;
import core.graph.Graph;

public class Independant_set_detector {

	// method appelé depuis d'autre classe utils :
	// Robustess_detector.reverseMIS, isIn, isInArray, transform

	public static boolean isIndependant(int[] tab, Graph g) {
		// Check every node, if one of their neighbor is in the set, return
		// false, else return true

		for (int n : tab) {
			for (Node n_n : g.getNode(n).getNeighbors()) {
				if (Arrays.binarySearch(tab, n_n.getId()) >= 0)
					return false;

			}
		}
		return true;
	}

	public static boolean isMIS(int[] tab, Graph g) {

		ArrayList<Node> listInde = Common_methods.transform(tab, g);
		ArrayList<Node> alist = Common_methods.reverseMIS(listInde, g);
		boolean isMaxInde;
		boolean isBad = false;
		for (Node n : alist) {
			isMaxInde = true;
			for (Node neigh : n.getNeighbors()) {
				if (Common_methods.isIn(neigh, listInde)) {
					// false car n pourrait etre dans l'ensemble de point independant
					isMaxInde = false;
				}
			}
			if (isMaxInde == true) {
				isBad = true;
				break;
			}
		}
		if (isBad == false) {
			return true;
		}

		return false;
	}
}
