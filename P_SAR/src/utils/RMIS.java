package utils;

import java.util.ArrayList;

import core.Graph;
import core.Node;

public class RMIS {

	// our Graph
	private Graph g;
	// our set of independent set // todo change this
	private ArrayList<ArrayList<Node>> ind_sets = new ArrayList<ArrayList<Node>>();

	public RMIS(Graph g) {
		this.g = g;
	}

	public static boolean isIndependant(ArrayList<Node> list)
	{
		// Check every node, if one of their neighbor is in the set, return false, else
		// return true
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

	public static boolean isInArray(ArrayList<Node> array, int id)
	{
		// check if the node id is in the array
		for (Node n : array)
		{
			if (id == n.getId())
			{
				return true;
			}
		}
		return false;
	}

	public static ArrayList<ArrayList<Node>> getMIS(int[][] tab, Graph g)
	{
		ArrayList<ArrayList<Node>> res = new ArrayList<ArrayList<Node>>();
		int length = 0;
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
				res.add(Transform(a, g));
			}
		}
		return res;
	}

	public static ArrayList<Node> Transform(int[] id_tab, Graph g)
	{ // transform an array of id in an ArrayList of node considering a graph g
		ArrayList<Node> res = new ArrayList<>();
		if (id_tab == null)
		{
			return null;
		}
		for (int i = 0; i < id_tab.length; i++)
		{
			for (Node n : g.getList())
			{
				if (n.getId() == id_tab[i])
				{
					res.add(n);
				}
			}
		}
		return res;
	}

	public static int[][] detect_stable(int[][] tab, Graph g)
	{ // considering an array of
		int[][] res = new int[tab.length][];
		for (int i = 0; i < tab.length; i++)
		{
			if (isIndependant(Transform(tab[i], g)))
			{
				res[i] = tab[i];
			} else
			{
				res[i] = null;
			}
		}
		return res;
	}

	private static int cpt;

	public static int[][] combinaison(ArrayList<Node> nodes)
	{
		cpt = 0;
		int[] a = new int[nodes.size()];
		int cpt = 0;
		for (Node n : nodes)
		{
			a[cpt++] = n.getId();
		}
		// System.out.println("oui");
		int[][] tout = new int[(int) Math.pow(2, a.length)][];
		for (var i = 0; i < a.length; i++)
		{
			// System.out.println(i);
			int[] b = {};
			inter(i, a, b, tout);
		}
		System.out.println("fin");
		int[][] res = push(tout, a);
		// tout.push(a);
		return tout;
	}

	public static void testCut(ArrayList<ArrayList<Node>> aalist, Graph g)
	{
		for (ArrayList<Node> list : aalist)
		{
			ArrayList<Node> reverselist = reverseMIS(list, g);
			for (Node n : reverselist)
			{
				ArrayList<Node> listgraph = new ArrayList<Node>();
				for (Node n_neigh : n.getNeighbors())
				{// creation de l'arraylist constitué des points fort voisin de n
					// utilisé dans le
					// constructeur pour pouvoir cree un graph test
					if (isIn(n_neigh, list))
					{
						listgraph.add(n_neigh);
					}
				}
				Graph graph = new Graph(g, n, listgraph);// on genere un nouveau graph sans les connections pour le test
				System.out.println(isLinked(n, graph));
			}
			// Graph g = new Graph();
		}
	}

	public static ArrayList<Node> reverseMIS(ArrayList<Node> list, Graph g)
	{
		ArrayList<Node> res = new ArrayList<Node>();
		for (Node n : g.getList())
		{
			if (!isIn(n, list))
			{
				res.add(n);
			}
		}
		return res;
	}

	public static boolean isLinked(Node n, Graph g)
	{
		ArrayList<Node> voisin_a_voir = new ArrayList<Node>();
		ArrayList<Node> voisin_vu = new ArrayList<Node>();
		voisin_vu.add(n);
		for (Node neigh : n.getNeighbors())
		{
			voisin_a_voir.add(neigh);
		}
		if (voisin_a_voir.isEmpty())
		{
			return false;
		}
		while (!voisin_a_voir.isEmpty())
		{
			for (Node neigh : voisin_a_voir.get(0).getNeighbors())
			{
				if (!isIn(neigh, voisin_a_voir) && !isIn(neigh, voisin_vu))
				{
					voisin_a_voir.add(neigh);
				}
				voisin_vu.add(voisin_a_voir.remove(0));
			}

		} // a ce stade voisin_vu represente l'arraylist des nodes connecté au node n. on
			// a juste a la comparer avec la liste des nodes du graph.
		boolean ispresent;
		for (Node node_vu : voisin_vu)
		{
			ispresent = false;
			for (Node node_graph : g.getList())
			{
				if (node_vu.getId() == node_graph.getId())
				{
					ispresent = true;
				}
			}
			if (!ispresent)
			{
				System.out.println("there is a cut");
				return false;
			}
		}
		return true;
	}

	public static boolean isIn(Node n, ArrayList<Node> ar)
	{// verifie si chacun des nodes de ar a le meme id que n.
		// return true si c'est le
		// cas
		for (Node neigh : ar)
		{
			if (neigh.getId() == n.getId())
			{
				return true;
			}
		}
		return false;
	}

	// combination stuff to cleanup

	public static int[][] combinaison(int[] a)
	{
		cpt = 0;
		int[][] tout = new int[(int) Math.pow(2, a.length)][];
		for (var i = 0; i < a.length; i++)
		{
			// System.out.println(i);
			int[] b = {};
			inter(i, a, b, tout);
		}
		System.out.println("fin");
		int[][] res = push(tout, a);
		// tout.push(a);
		return tout;
	}

	public static void inter(int n, int[] source, int[] en_cours, int[][] tout)
	{
		if (n == 0)
		{
			if (en_cours.length > 0)
			{

				tout[cpt++] = en_cours;
			}
			return;
		}
		for (var j = 0; j < source.length; j++)
		{
			int[] tab_source = slice(source, j + 1);
			int[] new_source = { source[j] };
			int[] tab_en_cours = concat(en_cours, new_source);
			inter(n - 1, tab_source, tab_en_cours, tout);

		}
		return;
	}

	public static int[] concat(int[] source, int[] source_bis)
	{
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
	{
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

	public static int[][] push(int[][] tab, int[] ajout)
	{
		int[][] res = new int[tab.length + 1][];
		int cpt = 0;
		while (cpt < res.length)
		{
			if (cpt == res.length - 1)
			{
				res[cpt] = ajout;
			} else
			{
				res[cpt] = tab[cpt];
			}
			cpt++;
		}
		return tab;
	}

}
