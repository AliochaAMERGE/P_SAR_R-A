package utils;

import java.util.ArrayList;

import core.Node;
import core.graph.Graph;

public class Independant_set_detector {
	
	//method appelé depuis d'autre classe utils : Robustess_detector.reverseMIS, isIn, isInArray, transform

	public static boolean isStable(Graph g, ArrayList<Node> valid_list) // complexité O(n^2)
	{
		for (Node node : g.getList())  // complexite : n
		{
			if (!Common_methods.isIn(node, valid_list))
			{
				for (Node node_neighbor : node.getNeighbors()) //complexite : O(n)
				{
					if (!Common_methods.isIn(node_neighbor, valid_list))
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	

	public static ArrayList<ArrayList<Node>> getMIS(ArrayList<int[]> tab, Graph g)
	{// TODO modif pas maximum mais maximal
		ArrayList<ArrayList<Node>> res = new ArrayList<ArrayList<Node>>();
		/*int length = 0;
		for (int[] t : tab)
		{
			if (t != null && t.length > length)
			{
				length = t.length;
			}
		}
		for (int[] a : tab)
		{
			if (a != null && a.length == length)
			{
				res.add(transform(a, g));
			}
		}*/
		
		for(int [] t : tab) {
			ArrayList<Node> listInde = Common_methods.transform(t,g);
			ArrayList<Node> alist = Common_methods.reverseMIS(listInde,g);
			boolean isMaxInde = true;
			boolean isBad = false;
			for (Node n : alist) {
				for (Node neigh : n.getNeighbors()) {
					if(!Common_methods.isIn(neigh, listInde)) {
						isMaxInde = false; // false car n pourrait etre dans l'ensemble de point independant
					}
				}
				if(isMaxInde == true) {
					isBad = true;
					break;
				}
			}
			if(isBad == false) {
				res.add(listInde);
			}
		}
		
		return res;
	}

	public static boolean isIndependant(ArrayList<Node> list)
	{// Check every node, if one of their neighbor is in the set, return false, else return true
		if (list == null)
		{
			return false;
		}
		for (Node n : list)
		{
			for (Node n_n : n.getNeighbors())
			{

				if (list.contains(n_n))
					return false;
			}
		}
		return true;
	}

	

	/*
	 * public static int [][] detectStable(int[][] tab, Graph g){ int [][] res = new
	 * int[tab.length][]; for(int i = 0; i < tab.length; i++) {
	 * if(isIndependant(transform(tab[i], g))){ res[i]=tab[i]; }else { res[i]= null;
	 * } } return res; // TEST 1 }
	 */

	public static boolean detectStable(int[] tab, Graph g)
	{
		if (isIndependant(Common_methods.transform(tab, g)))
		{
			return true;
		}
		return false;
	}
}
