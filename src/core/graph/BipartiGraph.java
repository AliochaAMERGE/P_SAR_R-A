package core.graph;

import java.util.ArrayList;

import core.Node;
import utils.Common_methods;
import utils.Robustess_detector;

/**
 * A complete bipartite graph is a graph G = (V1 U V2, E) such that V1 inter V2 = null
 * and E = {{v1, v2}, v1 € V1 and v2 € V2}
 */

/**
 * The graph is built the following way :
 * we divide the graph's node into two non-empty lists
 * we add vertices between these nodes (one from each sets)
 * until the graph is connected.
 */
public class BipartiGraph extends Graph{
	public BipartiGraph(int nb_nodes) {
		node_list = BipartiGraphGenerator(nb_nodes, 5);
	}
	
	public BipartiGraph(int nb_nodes, int nbvoisinsmax) {
		node_list = BipartiGraphGenerator(nb_nodes,nbvoisinsmax);
	}

	public ArrayList<Node> BipartiGraphGenerator(int length, int nbmax)
	{	
		ArrayList<Node> res = new ArrayList<Node>();
		ArrayList<Node> rightside = new ArrayList<Node>();
		ArrayList<Node> leftside = new ArrayList<Node>();
		if(length < 1) {
			return null;
		}
		while(leftside.size() == 0 || rightside.size() == 0) {
			rightside = new ArrayList<Node>();
			leftside = new ArrayList<Node>();
			for(int i =0;i<length;i++) {  // complexité : n
				Node n = new Node(i);
				res.add(n);
				if((int)(Math.random()*2) == 1) {
					rightside.add(n);
				}else{
					leftside.add(n);
				}
			}
		}
		int right, left;
		while(!Robustess_detector.isLinked(res.get(0), res)) {
			right = (int) (Math.random() * (rightside.size()));
			left = (int) (Math.random() * (leftside.size()));
			if(!Common_methods.isIn(rightside.get(right),leftside.get(left).getNeighbors())) { // && n1.getList().size() < 4 && n2.getList().size()
				
				rightside.get(right).addNeighbor(leftside.get(left));
			}
			if(rightside.get(right).getNeighbors().size() > nbmax || leftside.get(left).getNeighbors().size() > nbmax) {
				
				return BipartiGraphGenerator(length, nbmax);
			}
		}
		return res;
	}
}
