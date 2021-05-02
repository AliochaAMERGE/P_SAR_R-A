package core;

import java.util.ArrayList;

/**
 * A node object for a graph
 * defined my its ID
 * and a list of its neighbors
 */
public class Node {
	/* Every neigbors of the current node */
	private ArrayList<Node> neighbors;
	/* Node's id */
	private int id;
	private int value;
	
	
	
	/**
	 * @param id : int -> Node's id
	 * neighbors'list empty at first
	 */
	public Node(int id) {
		this.id = id;
		neighbors = new ArrayList<Node>();
	}
	
	/**
	 * @param id : int -> Node's id
	 * @param neighbors : List<Node> -> Node's neighbors
	 */
	public Node(int id, ArrayList<Node> neighbors) {
		this.id = id;
		this.neighbors = neighbors;
	}

	/**
	 * set node's neighbors
	 * @param neighbors : List<Node> -> Node's neighbors
	 */
	public void setNeighbors(ArrayList<Node> neighbors) {
		this.neighbors = neighbors;
	}

	/**
	 * Node to String :
	 * id : neighbor's id
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append(": ");
		for (Node n : neighbors) {
			sb.append(" " + n.getId());
		}
		return sb.toString();
	}
	
	
	/**
	 * add a neighbor to the list
	 * and add current node to neighbor's list
	 * for non oriented graph
	 * @param neighbor : Node -> a new neighbor
	 */
	public void addNeighbor(Node n) {
		neighbors.add(n);
		n.neighbors.add(this);
	}
	
	public void addNeighborUnidirectionnel(Node n) {
		neighbors.add(n);
	}

	/**
	 * @return node's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return node's neighbors
	 */
	public ArrayList<Node> getNeighbors() {
		return neighbors;
	}

	/**
	 * return if a Node is a neighbor of current
	 * research by node'id
	 * @param id : the node id we try to find
	 * @return boolean : if the node is a neighbor
	 */
	public boolean isNeighborbyId(int id) {
		for (Node neig : this.getNeighbors()) {
			if (neig.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public void setValue(int cpt) {
		value = cpt;;
		
	}

	public int getValue() {
		return value;
	}
}
