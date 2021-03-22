package test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import core.Node;
import core.graph.Graph;
import core.graph.RandomGraph;


import utils.Combination_generator;
import utils.Independant_set_detector;
import utils.Robustess_detector;

public class mainTest {

	private static ArrayList<ArrayList<Node>> MIS;
	private static Graph g;

	public static void main(String[] args) throws FileNotFoundException
	{
		// g = new Graph();
		g = new RandomGraph(10);
//		g.load("src/testgraph2.txt");
		System.out.println("Graph Loaded");
		System.out.println(g);
		System.out.println("----------------------");
		// int[] tab_test = {0,1,2,3,4,5,6};
		// int [][] res = Independant_set_detector.combinaison(tab_test);
		ArrayList<int[]> res = Combination_generator.combinaison(g.getList(), g);
		/*
		 * for(int i = 0; i < res.length; i++) {
		 * System.out.println(Arrays.toString(res[i])); }
		 */

		// int [][] tab_stable = Independant_set_detector.detectStable(res, g);
		/*
		 * for(int i = 0; i < tab_stable.length; i++) {
		 * System.out.println(Arrays.toString(tab_stable[i])); }
		 */

		MIS = Independant_set_detector.getMIS(res, g);
		/*
		 * for(ArrayList<Node> list : MIS) { System.out.println(list.size()+" "+ MIS); }
		 */
		// System.out.println(Independant_set_detector.getMSI(tab_stable,g));
		for (ArrayList<Node> list : MIS)
		{
			for (Node node : list)
			{
				//System.out.print(node.getId() + " ");
			}
			//System.out.println();
		}
		Robustess_detector.testCut(MIS, g);

		// PROTOTYPE D'AFFICHAGE GRAPHIQUE

		/*
		 * JFrame cadre = new javax.swing.JFrame("Premiere fenetre");
		 * 
		 * JButton button = new JButton("next poss"); JPanel panneau = new MyPanel();
		 * panneau.add(button);
		 * 
		 * 
		 * panneau.setPreferredSize(new Dimension(800, 800));
		 * 
		 * panneau.setBackground(Color.WHITE); cadre.setContentPane(panneau);
		 * cadre.setLocation(400, 300); cadre.pack(); cadre.setVisible(true);
		 * cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 */
	}

//	static class MyPanel extends JPanel {
//		@Override
//		public void paintComponent(Graphics graphics)
//		{
//			super.paintComponent(graphics);
//			Graphics2D g2 = (Graphics2D) graphics;
//			/*
//			 * int y2 = (int) (40 * Math.random()); Line2D line = new Line2D.Double(10, 10,
//			 * 60, y2); Rectangle2D rectangle = new Rectangle2D.Double(200, 120, 70, 30);
//			 * Ellipse2D oval = new Ellipse2D.Double(400, 200, 40, 60); g2.draw(line);
//			 * g2.setPaint(Color.RED); g2.fill(rectangle); g2.setPaint(Color.ORANGE);
//			 * g2.fill(oval);
//			 */
//			int cpt = 0, cpt2 = 0, x = 0, y = 100;
//			for (Node node : g.getList())
//			{
//				if (cpt > Math.sqrt(g.getList().size()))
//				{
//					cpt = 0;
//					y = y + 100;
//					x = 100;
//				} else
//				{
//					x = x + 100;
//				}
//				Rectangle2D rectangle = new Rectangle2D.Double(x, y, 15, 15);
//				g2.draw(rectangle);
//				g2.drawString(cpt2 + "", x + 2, y + 12);
//				for (Node neighbor : node.getNeighbors())
//				{
//				}
//				cpt++;
//				cpt2++;
//			}
//		}
//	}
	
}
