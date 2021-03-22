package utils;

import java.util.ArrayList;

import core.Node;
import core.graph.Graph;

public class Combination_generator {

	private static Graph g;

	public static ArrayList<int[]> combinaison(ArrayList<Node> nodes, Graph graph)
	{ // cette fonction renvoie une arraylist contenant des tableaux d'int
		// correspondant, chacune a un ensemble stable du graphe

		// for each combination of nodes in our graph, check if the combination is an
		// independent set
		g = graph;
		long startTime = System.nanoTime();
		int[] a = new int[nodes.size()];
		int cpt = 0;
		for (Node n : nodes)
		{
			a[cpt++] = n.getId();
		}

		// int[][] tout = new int [(int)Math.pow(2, a.length)][];
		ArrayList<int[]> tout = new ArrayList<int[]>();

		for (var i = 0; i < a.length; i++)
		{
			int[] b = {};
			inter(i, a, b, tout);
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("le calcul de combinaison a pris : " + duration / 1000000000 + " sec(s)");
		return tout;
	}

	public static void inter(int n, int[] source, int[] en_cours, ArrayList<int[]> tout)
	{// fonction recurcive appeleé par la fonction combinaison
		if (n == 0)
		{
			if (en_cours.length > 0)
			{
				if (Independant_set_detector.detectStable(en_cours, g))
				{
					tout.add(en_cours);
				}
			}
			return;
		}
		for (var j = 0; j < source.length; j++)
		{
			int[] tab_source = slice(source, j + 1);

			int[] new_source = { source[j] };
			int[] tab_en_cours = concat(en_cours, new_source);

			if (n - 1 >= 0)
			{
				inter(n - 1, tab_source, tab_en_cours, tout); // fn(n - 1, source.slice(j + 1),
																// en_cours.concat([source[j]]), tout);
			}
		}
		return;
	}

	public static int[] concat(int[] source, int[] source_bis)
	{ // concatenation de deux tableau -> utilisé par inter
		int[] res = new int[source.length + source_bis.length];
		int cpt = 0;
		while (cpt < source.length + source_bis.length)
		{
			if (cpt < source.length)
			{
				res[cpt] = source[cpt];
			} else
			{
				res[cpt] = source_bis[cpt - source.length];
			}
			cpt++;
		}
		return res;
	}

	public static int[] slice(int[] source, int a)
	{ // retourne un tableau identique a la difference que les elements d'index
		// inferieur a (int a) ont été enlevé (decalage vers la gauche de (int a))
		int[] tab = new int[source.length - a];
		int cpt = 0;
		while (cpt < source.length)
		{
			if (cpt >= a)
			{
				tab[cpt - a] = source[cpt];
			}
			cpt++;
		}
		return tab;
	}

}
