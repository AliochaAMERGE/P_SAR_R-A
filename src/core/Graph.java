package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import utils.RMIS;

public class Graph {

	ArrayList<Node> node_list;

	public Graph() {
		// Empty graph
		node_list = new ArrayList<Node>();
	}

	public Graph(String path) throws FileNotFoundException {
		// graph creation from File (Adjacency matrix)
		node_list = new ArrayList<Node>();
		this.load(path);
	}

	public Graph(Graph g, Node n, ArrayList<Node> alist) {
		// graph creation from another graph, without the arc between n and elements of
		// alist
		ArrayList<Node> res = new ArrayList<Node>();
		for (Node node : g.getList())
		{
			Node resnode;
			if (node.getId() == n.getId())
			{
				resnode = new Node(n.getId());
				for (Node n_neigh : n.getNeighbors())
				{
					if (!RMIS.isIn(n_neigh, alist))
					{
						resnode.addneighbor(n_neigh);
					}
				}
			} else
			{
				if (RMIS.isIn(node, alist))
				{
					resnode = new Node(node.getId());
					for (Node n_neigh : node.getNeighbors())
					{
						if (n_neigh.getId() != n.getId())
						{
							resnode.addneighbor(n_neigh);
						}
					}
				} else
				{
					resnode = node;
				}
			}
			res.add(resnode);
		}
		node_list = res;
	}

	public Graph(ArrayList<Node> node_list) {
		// create a graph from another graph
		this.node_list = node_list;
	}

	public static ArrayList<Node> RandomGraphGenerator(int length)
	{
		// work in progress, doesn't work
		ArrayList<Node> res = new ArrayList<Node>();
		for (int i = 0; i < length; i++)
		{
			res.add(new Node(i));
		}
		for (int i = 0; i < length; i++)
		{

		}
		return res;
	}

	public static ArrayList<Node> ChainGraphGenerator(int length)
	{
		ArrayList<Node> res = new ArrayList<Node>();
		for (int i = 0; i < length; i++)
		{
			res.add(new Node(i));
		}
		for (int i = 0; i < length; i++)
		{
			ArrayList<Node> neigh = new ArrayList<Node>();
			neigh.add(res.get((i - 1 + length) % length));
			neigh.add(res.get((i + 1) % length));
			res.get(i).setNeighbors(neigh);
		}
		return res;
	}

	public void load(String path) throws FileNotFoundException
	{// load a graph from a file

		File file = new File(path);
		Scanner myReader = new Scanner(file);
		String data;

		// read nodes list (first line)
		data = myReader.nextLine();

		String[] nodes = data.split(" ");

		for (String str : nodes)
		{
			System.out.print(" " + str);
			node_list.add(new Node(Integer.parseInt(str)));
		}

		System.out.print("\n");

		// read node's neighbor
		int cptligne = 0, cptcol;
		int N = node_list.size();
		int[][] ids = new int[N][N];
		while (myReader.hasNextLine())
		{
			data = myReader.nextLine();
			String[] tabdata = data.split(",");
			cptcol = 0;
			for (String s : tabdata)
			{
				ids[cptligne][cptcol] = Integer.parseInt(s);
				cptcol++;
			}

			cptligne++;
		}
		for (int i = 0; i < N; i++)
		{
			ArrayList<Node> neighbors = new ArrayList<Node>();
			for (int j = 0; j < N; j++)
			{
				if (ids[i][j] != 0)
				{
					neighbors.add(node_list.get(j));
				}
			}
			node_list.get(i).setNeighbors(neighbors);
		}
		myReader.close();
	}

	public void save(String fichier)
	{ // TODO : save a graph in a file

	}

	public String toString()
	{

		StringBuilder sb = new StringBuilder();
		for (Node n : node_list)
		{
			sb.append(n.toString() + "\n");
		}
		return sb.toString();
	}

	public int getSize()
	{
		return this.node_list.size();
	}

	public ArrayList<Node> getList()
	{
		return this.node_list;
	}

	public boolean contains(int id)
	{
		for (Node n : node_list)
		{
			if (id == n.getId())
			{
				return true;
			}
		}
		return false;
	}
}
