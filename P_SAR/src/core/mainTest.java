package core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import utils.RMIS;

public class mainTest {

	// list of MIS
	private static ArrayList<ArrayList<Node>> MIS_list;
	// our graph
	private static Graph g;

	public static void main(String[] args) throws FileNotFoundException
	{

		g = new Graph();

		g.load("src/test_files/testGraphComplexe");

		System.out.println(g);

		int[][] res = RMIS.combinaison(g.getList());

		for (int i = 0; i < res.length; i++)
		{
			System.out.println(Arrays.toString(res[i]));
		}

		int[][] tab_stable = RMIS.detect_stable(res, g);
		for (int i = 0; i < tab_stable.length; i++)
		{
			System.out.println(Arrays.toString(tab_stable[i]));
		}

		MIS_list = RMIS.getMIS(tab_stable, g);

		for (ArrayList<Node> list : MIS_list)
		{
			System.out.println(list.size() + " " + MIS_list);
		}

		System.out.println(RMIS.getMIS(tab_stable, g));

		/*
		 * GRAPHICAL DISPLAY INCOMPLET JFrame cadre = new
		 * javax.swing.JFrame("Premiere fenetre");
		 * 
		 * JButton button = new JButton("next poss"); JPanel panneau = new MyPanel();
		 * panneau.add(button);
		 * 
		 * panneau.setPreferredSize(new Dimension(800, 800));
		 * 
		 * panneau.setBackground(Color.WHITE); cadre.setContentPane(panneau);
		 * cadre.setLocation(400, 300); cadre.pack(); cadre.setVisible(true);
		 * cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 */
	}

	static class MyPanel extends JPanel {
		@Override
		public void paintComponent(Graphics graphics)
		{
			super.paintComponent(graphics);
			Graphics2D g2 = (Graphics2D) graphics;
			/*
			 * int y2 = (int) (40 * Math.random()); Line2D line = new Line2D.Double(10, 10,
			 * 60, y2); Rectangle2D rectangle = new Rectangle2D.Double(200, 120, 70, 30);
			 * Ellipse2D oval = new Ellipse2D.Double(400, 200, 40, 60); g2.draw(line);
			 * g2.setPaint(Color.RED); g2.fill(rectangle); g2.setPaint(Color.ORANGE);
			 * g2.fill(oval);
			 */
			int cpt = 0, cpt2 = 0, x = 0, y = 100;
			for (Node node : g.getList())
			{
				if (cpt > Math.sqrt(g.getList().size()))
				{
					cpt = 0;
					y = y + 100;
					x = 100;
				} else
				{
					x = x + 100;
				}
				Rectangle2D rectangle = new Rectangle2D.Double(x, y, 15, 15);
				g2.draw(rectangle);
				g2.drawString(cpt2 + "", x + 2, y + 12);
				for (Node neighbor : node.getNeighbors())
				{

				}
				cpt++;
				cpt2++;
			}
		}
	}
}
