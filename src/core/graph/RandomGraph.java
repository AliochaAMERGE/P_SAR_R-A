package core.graph;

import java.util.ArrayList;

import core.Node;
import utils.Common_methods;
import utils.Robustess_detector;

public class RandomGraph extends Graph {

	public RandomGraph(int nb_nodes) {
		node_list = RandomGraphGenerator(nb_nodes);
	}

	public ArrayList<Node> RandomGraphGenerator(int length)
	{	
		ArrayList<Node> res = new ArrayList<Node>();
		if(length < 1) {
			return null;
		}
		for(int i =0;i<length;i++) {  // complexité : n
			res.add(new Node(i));
		}
		int n1, n2;
		while(!Robustess_detector.isLinked(res.get(0), res)) { // complexité : O((n-2)+(n-3)+...+1) 
			n1 = (int) (Math.random() * (length));
			n2 = (int) (Math.random() * (length));
			System.out.println("preventif"+n1 +" "+n2);
			if(n1 != n2 && !Common_methods.isIn(res.get(n1),res.get(n2).getNeighbors())) { // && n1.getList().size() < 4 && n2.getList().size()
				System.out.println(n1 +" "+n2);
				res.get(n1).addNeighbor(res.get(n2));
			}
			if(res.get(n1).getNeighbors().size() > 3 || res.get(n2).getNeighbors().size() > 3) {
				return RandomGraphGenerator(length);
			}
		}
		return res;
	}
}
