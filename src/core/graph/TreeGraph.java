package core.graph;

import java.util.ArrayList;

import core.Node;

public class TreeGraph extends Graph{
	public TreeGraph(int nb_nodes) { //peut prendre en param le nombre de branche max : int nb_branches
		ArrayList<Node> res = new ArrayList<Node>();
		ArrayList<Node> canope_actuelle = new ArrayList<Node>();
		ArrayList<Node> futur_canope = new ArrayList<Node>();
		int cpt = 0;
		int progression_canope = 0;
		if(nb_nodes != 0) {
			Node racine = new Node(cpt);
			canope_actuelle.add(racine);
			res.add(racine);
			cpt++;
		}
		while(cpt < nb_nodes) {
			if(canope_actuelle.size()*2>futur_canope.size()) {// 2 -> int nb_branches
				Node n = new Node(cpt);
				canope_actuelle.get(progression_canope/2).addNeighbor(n);// 2 -> int nb_branches
				futur_canope.add(n);
				res.add(n);
				cpt++;
				progression_canope++;
			}else {
				canope_actuelle = futur_canope;
				futur_canope = new ArrayList<Node>();
				progression_canope = 0;
			}
		}
		node_list = res;
	}

}
