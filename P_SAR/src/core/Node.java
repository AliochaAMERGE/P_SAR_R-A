package core;

import java.util.ArrayList;

public class Node {
	private ArrayList<Node> neighbors;
	private int id;

	public Node(int id) {
		this.id = id;
	}

	public void setNeighbors(ArrayList<Node> neighbors)
	{
		this.neighbors = neighbors;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(": ");
		for (Node n : neighbors)
		{
			sb.append(" " + n.getId());
		}
		return sb.toString();
	}

	public ArrayList<Node> getNeighbors()
	{
		return neighbors;
	}

	public void addNeighbor(Node n)
	{
		neighbors.add(n);
		n.neighbors.add(this);
	}

	public void addneighbor(Node neighbor)
	{
		neighbors.add(neighbor);
	}

	public int getId()
	{
		return id;
	}

}
