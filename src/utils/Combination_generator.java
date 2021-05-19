package utils;

import java.util.ArrayList;

import core.Node;
import core.graph.Graph;

public class Combination_generator {

	private static Graph g;
	private static int complex;
	private static int[] OneRes;

	public static long tempstot;
	public static long tempsindetot;
	public static long tempsmistot;
	public static long tempscuttot;

	public static long combinaison(ArrayList<Node> nodes, Graph graph, String start, int optiM) {
		// cette fonction renvoie une arraylist contenant des tableaux d'int
		// correspondant, chacune a un ensemble stable du graphe

		// for each combination of nodes in our graph, check if the combination is an
		// independent set

		tempstot = 0;
		tempsindetot = 0;
		tempsmistot = 0;
		tempscuttot = 0;

		complex = 0;
		g = graph;


		long startTime = System.nanoTime();
		int[] a;
		if (nodes != null) {
			a = new int[nodes.size()];
		} else {

			return System.nanoTime() - startTime;
		}

		if (g.isBiparti() || g.isSpoutnik()) {
			return System.nanoTime() - startTime;
		}

		int cpt = 0;
		for (Node n : nodes) {
			a[cpt++] = n.getId();
		}
		ArrayList<int[]> tout = new ArrayList<int[]>();
		OneRes = null;

		switch (start) {
			case "milieu":
				int cpt2 = 1;
				int i = a.length / 2;
				boolean swaper = true;
				while (i <= a.length && i >= 0) {
					int[] b = {};
					inter(i, a, b, tout, optiM);
					if (swaper) {
						i += cpt2;
					} else {
						i -= cpt2;
					}
					swaper = !swaper;
					cpt2++;
				}
				break;
			case "fin":
				for (i = 0; i <= a.length; i++) {
					int[] b = {};
					interFin(i, a, b, tout, optiM);
				}
				break;
			case "debut":
				for (i = 0; i <= a.length; i++) {
					int[] b = {};
					inter(i, a, b, tout, optiM);
				}
				break;
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);

		return duration;// OneRes
	}

	public static void inter(int n, int[] source, int[] en_cours, ArrayList<int[]> tout, int optiM) {
		// fonction recurcive appeleé par la fonction combinaison
		complex++;
		long tempsinde = 0;
		long tempsmis = 0;
		long tempscut = 0;

		long tempsmisfin = 0;
		long tempscutfin = 0;
		long tempstest = 0;

		if (OneRes == null && n <= source.length) {
			if (n == 0) {
				long temps = System.nanoTime();
				if (Independant_set_detector.isIndependant(en_cours, g)) {
					tempsmisfin = System.nanoTime();
					if (Independant_set_detector.isMIS(en_cours, g)) {
						tempstest = System.nanoTime();
						if (optiM != 0) {
							if (optiM == 3) {
								g.optimizeMIS(Common_methods.transform(en_cours, g));
							} else if (optiM == 4) {
								g.optimizeMISREVERSE(Common_methods.transform(en_cours, g));
							}
						}
						tempscutfin = System.nanoTime();
						if (Robustess_detector.isRobust(en_cours, g)) {
							OneRes = en_cours;

						}
						tempscut = System.nanoTime() - tempscutfin;
					}
					tempsmis = System.nanoTime() - tempsmisfin - (tempscutfin - tempstest) - tempscut;
				}
				tempsinde = System.nanoTime() - temps - tempsmis - (tempscutfin - tempstest) - tempscut;

				tempstot += System.nanoTime() - temps;

				tempsindetot += tempsinde;
				tempsmistot += tempsmis;
				tempscuttot += tempscut;

				return;
			}
			for (var j = 0; j < source.length; j++) {

				int[] tab_source = slice(source, j + 1);

				int[] new_source = { source[j] };
				int[] tab_en_cours = concat(en_cours, new_source);

				if (n - 1 >= 0) {
					inter(n - 1, tab_source, tab_en_cours, tout, optiM);
				}
			}
			return;
		} else {
			return;
		}
	}

	public static void interFin(int n, int[] source, int[] en_cours, ArrayList<int[]> tout, int optiM) { // FIN
		complex++;
		long tempsinde = 0;
		long tempsmis = 0;
		long tempscut = 0;

		long tempsmisfin = 0;
		long tempscutfin = 0;
		long tempstest = 0;
		if (OneRes == null && n <= source.length) {
			if (n == 0) {
				long temps = System.nanoTime();

				int reverseEnd[] = Common_methods.reverseArray(en_cours, g);
				if (Independant_set_detector.isIndependant(reverseEnd, g)) {
					tempsmisfin = System.nanoTime();
					if (Independant_set_detector.isMIS(reverseEnd, g)) {
						tempstest = System.nanoTime();
						if (optiM != 0) {
							if (optiM == 3) {
								g.optimizeMIS(Common_methods.transform(en_cours, g));
							} else if (optiM == 4) {
								g.optimizeMISREVERSE(Common_methods.transform(en_cours, g));
							}
						}
						tempscutfin = System.nanoTime();
						if (Robustess_detector.isRobust(reverseEnd, g)) {
							OneRes = en_cours;
						}
						tempscut = System.nanoTime() - tempscutfin;
					}
					tempsmis = System.nanoTime() - tempsmisfin - (tempscutfin - tempstest) - tempscut;
				}
				tempsinde = System.nanoTime() - temps - tempsmis - (tempscutfin - tempstest) - tempscut;

				tempstot += System.nanoTime() - temps;

				tempsindetot += tempsinde;
				tempsmistot += tempsmis;
				tempscuttot += tempscut;

				return;
			}
			for (var j = 0; j < source.length; j++) {

				int[] tab_source = slice(source, j + 1);

				int[] new_source = { source[j] };
				int[] tab_en_cours = concat(en_cours, new_source);
				if (n - 1 == 0) {
					// System.out.println("tab_en_cours : "+Arrays.toString(tab_en_cours));
				}

				if (n - 1 >= 0) {
					interFin(n - 1, tab_source, tab_en_cours, tout, optiM);
				}
			}
			return;
		} else {
			return;
		}
	}

	public static int[] concat(int[] source, int[] source_bis) {
		// concatenation de deux tableau -> utilisé par inter
		int[] res = new int[source.length + source_bis.length];
		int cpt = 0;
		while (cpt < source.length + source_bis.length) {
			if (cpt < source.length) {
				res[cpt] = source[cpt];
			} else {
				res[cpt] = source_bis[cpt - source.length];
			}
			cpt++;
		}
		return res;
	}

	public static int[] slice(int[] source, int a) {
		// retourne un tableau identique a la difference que les elements
		// d'index
		// inferieur a (int a) ont été enlevé (decalage vers la gauche de (int
		// a))
		int[] tab = new int[source.length - a];
		int cpt = 0;
		while (cpt < source.length) {
			if (cpt >= a) {
				tab[cpt - a] = source[cpt];
			}
			cpt++;
		}
		return tab;
	}

	/****************************************************************
	 *********************** No specific time saved *****************
	 ****************************************************************/

	public static long combinaisonnoT(ArrayList<Node> nodes, Graph graph, String start, int optiM) {
		// cette fonction renvoie une arraylist contenant des tableaux d'int
		// correspondant, chacune a un ensemble stable du graphe

		// for each combination of nodes in our graph, check if the combination is an
		// independent set

		tempstot = 0;
		tempsindetot = 0;
		tempsmistot = 0;
		tempscuttot = 0;

		complex = 0;
		g = graph;

		long startTime = System.nanoTime();
		int[] a;
		if (nodes != null) {
			a = new int[nodes.size()];
		} else {

			return System.nanoTime() - startTime;
		}

		if (g.isBiparti() || g.isSpoutnik()) {
			return System.nanoTime() - startTime;
		}

		int cpt = 0;
		for (Node n : nodes) {
			a[cpt++] = n.getId();
		}
		ArrayList<int[]> tout = new ArrayList<int[]>();
		OneRes = null;

		switch (start) {
			case "milieu":
				int cpt2 = 1;
				int i = a.length / 2;
				boolean swaper = true;
				while (i <= a.length && i >= 0) {
					int[] b = {};
					internoT(i, a, b, tout, optiM);
					if (swaper) {
						i += cpt2;
					} else {
						i -= cpt2;
					}
					swaper = !swaper;
					cpt2++;
				}
				break;
			case "fin":
				for (i = 0; i <= a.length; i++) {
					int[] b = {};
					interFinnoT(i, a, b, tout, optiM);
				}
				break;
			case "debut":
				for (i = 0; i <= a.length; i++) {
					int[] b = {};
					internoT(i, a, b, tout, optiM);
				}
				break;
		}

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);

		return duration;// OneRes
	}

	public static void internoT(int n, int[] source, int[] en_cours, ArrayList<int[]> tout, int optiM) {
		// fonction recurcive appeleé par la fonction combinaison
		complex++;

		if (OneRes == null && n <= source.length) {
			if (n == 0) {
				if (Independant_set_detector.isIndependant(en_cours, g)) {
					if (Independant_set_detector.isMIS(en_cours, g)) {
						if (optiM != 0) {
							if (optiM == 3) {
								g.optimizeMIS(Common_methods.transform(en_cours, g));
							} else if (optiM == 4) {
								g.optimizeMISREVERSE(Common_methods.transform(en_cours, g));
							}
						}
						if (Robustess_detector.isRobust(en_cours, g)) {
							OneRes = en_cours;
						}
					}
				}
				return;
			}
			for (var j = 0; j < source.length; j++) {

				int[] tab_source = slice(source, j + 1);

				int[] new_source = { source[j] };
				int[] tab_en_cours = concat(en_cours, new_source);

				if (n - 1 >= 0) {
					internoT(n - 1, tab_source, tab_en_cours, tout, optiM);
				}
			}
			return;
		} else {
			return;
		}
	}

	public static void interFinnoT(int n, int[] source, int[] en_cours, ArrayList<int[]> tout, int optiM) { // FIN
		complex++;
		if (OneRes == null && n <= source.length) {
			if (n == 0) {
				int reverseEnd[] = Common_methods.reverseArray(en_cours, g);
				if (Independant_set_detector.isIndependant(reverseEnd, g)) {
					if (Independant_set_detector.isMIS(reverseEnd, g)) {
						if (Robustess_detector.isRobust(reverseEnd, g)) {
							OneRes = en_cours;
						}
					}
				}
				return;
			}
			for (var j = 0; j < source.length; j++) {

				int[] tab_source = slice(source, j + 1);

				int[] new_source = { source[j] };
				int[] tab_en_cours = concat(en_cours, new_source);
				if (n - 1 == 0) {
					// System.out.println("tab_en_cours : "+Arrays.toString(tab_en_cours));
				}

				if (n - 1 >= 0) {
					interFinnoT(n - 1, tab_source, tab_en_cours, tout, optiM);
				}
			}
			return;
		} else {
			return;
		}
	}
}