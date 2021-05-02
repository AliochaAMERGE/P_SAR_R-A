package test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import core.Node;
import core.graph.BipartiGraph;
import core.graph.CycleGraph;
import core.graph.Graph;
import core.graph.RandomGraphDense;
import core.graph.SputnikGraph;
import core.graph.TreeGraph;
import utils.Combination_generator;

public class mainTest {

	private static ArrayList<ArrayList<Node>> MIS;
	private static Graph g;

	public static void main(String[] args) throws FileNotFoundException {

		int type = 5;
		// 0 -> test all, fichier test.csv
		//

		// g = new RandomGraphDense(25, 0.3);

		// System.out.println("isBiparti "+g.isBiparti()+";");
		/*
		 * test sur chacun des graphes en fonctions de leur taille et potentiellement de
		 * leur parametre (arbre taille) test : temps, fiabilité etudes des resultats en
		 * fonction du type de graph combien de MIS/ combien sont robustes qu'est ce qui
		 * permettrait a l'algo d'etre plus efficace dans en fonction du type de graph
		 * étude de la complexité
		 */
		// g.load("src/testgraph2.txt");
		// System.out.println("Graph Loaded");
		// System.out.println(g);
		// System.out.println("----------------------");
		// g.optimize();
		// System.out.println(g);
		// System.out.println("----------------------");
		// int[] tab_test = {0,1,2,3,4,5,6};
		// int [][] res = Independant_set_detector.combinaison(tab_test);
		//////////////// ArrayList<int[]> res =
		// Combination_generator.combinaison(g.getList(), g);

		//////////////// TEST DIRCET///////////////////
		// System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g,
		//////////////// "fin"));

		// System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g,
		// "debut"));

		// System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g,
		// "milieu"));

		int cpt;
		FileWriter myWriter;

		switch (type) {
		case 0:
			cpt = 5;
			try {
				myWriter = new FileWriter("src/test_files/test.csv");

				myWriter.write("taille; dureeAnneauDebut; dureeAnneauMilieu; dureeAnneauFin; "
						+ "dureeArbreDebut; dureeArbreMilieu; dureeArbreFin; "
						+ "dureeRandomLightDebut; dureeRandomLightMilieu; dureeRandomLightFin; "
						+ "dureeRandomDenseDebut; dureeRandomDenseMilieu; dureeRandomDenseFin; "
						+ "dureebipartiDebut; dureebipartiMilieu; dureebipartiFin; "
						+ "dureesputnikDebut; dureesputnikMilieu; dureesputnikFin\n");
				while (cpt < 18) {
					long cmoyennemilieu = 0;
					long tmoyennemilieu = 0;
					long rmoyennemilieu = 0;
					long rdmoyennemilieu = 0;
					long bmoyennemilieu = 0;
					long smoyennemilieu = 0;
					long cmoyennedebut = 0;
					long tmoyennedebut = 0;
					long rmoyennedebut = 0;
					long rdmoyennedebut = 0;
					long bmoyennedebut = 0;
					long smoyennedebut = 0;
					long cmoyennefin = 0;
					long tmoyennefin = 0;
					long rmoyennefin = 0;
					long rdmoyennefin = 0;
					long bmoyennefin = 0;
					long smoyennefin = 0;
					for (int j = 0; j < 10; j++) {
						SputnikGraph sg = new SputnikGraph(cpt);
						CycleGraph cg = new CycleGraph(cpt);
						TreeGraph tg = new TreeGraph(cpt);
						RandomGraphDense rg = new RandomGraphDense(cpt, 2);
						RandomGraphDense rdg = new RandomGraphDense(cpt, cpt / 2);
						BipartiGraph bg = new BipartiGraph(cpt, cpt / 2);
						// System.out.println("isBiparti "+sg.isBiparti()+"; "+cg.isBiparti()+";
						// "+tg.isBiparti()+"; "+rg.isBiparti()+"; "+bg.isBiparti()+"; ");
						smoyennemilieu += Combination_generator.combinaison(sg.getList(), sg, "milieu", 0);
						cmoyennemilieu += Combination_generator.combinaison(cg.getList(), cg, "milieu", 0);
						tmoyennemilieu += Combination_generator.combinaison(tg.getList(), tg, "milieu", 0);
						rmoyennemilieu += Combination_generator.combinaison(rg.getList(), rg, "milieu", 0);
						rdmoyennemilieu += Combination_generator.combinaison(rdg.getList(), rdg, "milieu", 0);
						bmoyennemilieu += Combination_generator.combinaison(bg.getList(), bg, "milieu", 0);
						smoyennefin += Combination_generator.combinaison(sg.getList(), sg, "fin", 0);
						cmoyennefin += Combination_generator.combinaison(cg.getList(), cg, "fin", 0);
						tmoyennefin += Combination_generator.combinaison(tg.getList(), tg, "fin", 0);
						rmoyennefin += Combination_generator.combinaison(rg.getList(), rg, "fin", 0);
						rdmoyennefin += Combination_generator.combinaison(rdg.getList(), rdg, "fin", 0);
						bmoyennefin += Combination_generator.combinaison(bg.getList(), bg, "fin", 0);
						smoyennedebut += Combination_generator.combinaison(sg.getList(), sg, "debut", 0);
						cmoyennedebut += Combination_generator.combinaison(cg.getList(), cg, "debut", 0);
						tmoyennedebut += Combination_generator.combinaison(tg.getList(), tg, "debut", 0);
						rmoyennedebut += Combination_generator.combinaison(rg.getList(), rg, "debut", 0);
						rdmoyennedebut += Combination_generator.combinaison(rdg.getList(), rdg, "debut", 0);
						bmoyennedebut += Combination_generator.combinaison(bg.getList(), bg, "debut", 0);
					}
					/*
					 * System.out.println("la moyenne pour des graph de taille : "+cpt+" est de "+
					 * "cycle moyenne debut: "+cmoyennedebut/100
					 * +"tree moyenne debut: "+tmoyennedebut/100
					 * +"random moyenne debut "+rmoyennedebut/100
					 * +"biparti moyenne debut "+bmoyennedebut/100
					 * +"sputnik moyenne debut: "+smoyennedebut/100
					 * 
					 * +"cycle moyenne fin: "+cmoyennefin/100 +"tree moyenne fin: "+tmoyennefin/100
					 * +"random moyenne fin "+rmoyennefin/100
					 * +"biparti moyenne fin "+bmoyennefin/100
					 * +"sputnik moyenne fin: "+smoyennefin/100
					 * 
					 * +"cycle moyenne milieu: "+cmoyennemilieu/100
					 * +"tree moyenne milieu: "+tmoyennemilieu/100
					 * +"random moyenne milieu "+rmoyennemilieu/100
					 * +"biparti moyenne milieu "+bmoyennemilieu/100
					 * +"sputnik moyenne milieu: "+smoyennemilieu/100 );
					 */
					myWriter.write(cpt + "; " + cmoyennedebut / 100 + "; " + cmoyennemilieu / 100 + "; "
							+ cmoyennefin / 100 + "; " + tmoyennedebut / 100 + "; " + tmoyennemilieu / 100 + "; "
							+ tmoyennefin / 100 + "; " + rmoyennedebut / 100 + "; " + rmoyennemilieu / 100 + "; "
							+ rmoyennefin / 100 + "; " + rdmoyennedebut / 100 + "; " + rdmoyennemilieu / 100 + "; "
							+ rdmoyennefin / 100 + "; " + bmoyennedebut / 100 + "; " + bmoyennemilieu / 100 + "; "
							+ bmoyennefin / 100 + "; " + smoyennedebut / 100 + "; " + smoyennemilieu / 100 + "; "
							+ smoyennefin / 100 + "\n");
					cpt++;
				}
				myWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 1:
			cpt = 5;
			try {
				myWriter = new FileWriter("src/test_files/ecart_type.csv");
				int max1 = 19;
				int max2 = 20;

				myWriter.write("ecart type light debut; ecart type medium milieu; ecart type dense fin; "
						+ "ecart type light debut; ecart type medium milieu; ecart type dense fin; "
						+ "ecart type light debut; ecart type medium milieu; ecart type dense fin\n");

				long one_debut[] = new long[max2];
				long quart_debut[] = new long[max2];
				long moitie_debut[] = new long[max2];
				long one_milieu[] = new long[max2];
				long quart_milieu[] = new long[max2];
				long moitie_milieu[] = new long[max2];
				long one_fin[] = new long[max2];
				long quart_fin[] = new long[max2];
				long moitie_fin[] = new long[max2];

				long one_debut_m[] = new long[max1];
				long quart_debut_m[] = new long[max1];
				long moitie_debut_m[] = new long[max1];
				long one_milieu_m[] = new long[max1];
				long quart_milieu_m[] = new long[max1];
				long moitie_milieu_m[] = new long[max1];
				long one_fin_m[] = new long[max1];
				long quart_fin_m[] = new long[max1];
				long moitie_fin_m[] = new long[max1];

				long et_o_d;
				long et_q_d;
				long et_m_d;
				long et_o_m;
				long et_q_m;
				long et_m_m;
				long et_o_f;
				long et_q_f;
				long et_m_f;

				while (cpt < max1) {
					one_debut_m[cpt] = 0;
					quart_debut_m[cpt] = 0;
					moitie_debut_m[cpt] = 0;
					one_milieu_m[cpt] = 0;
					quart_milieu_m[cpt] = 0;
					moitie_milieu_m[cpt] = 0;
					one_fin_m[cpt] = 0;
					quart_fin_m[cpt] = 0;
					moitie_fin_m[cpt] = 0;

					et_o_d = 0;
					et_q_d = 0;
					et_m_d = 0;
					et_o_m = 0;
					et_q_m = 0;
					et_m_m = 0;
					et_o_f = 0;
					et_q_f = 0;
					et_m_f = 0;

					for (int i = 0; i < max2; i++) {
						RandomGraphDense gOne = new RandomGraphDense(cpt, 1);
						RandomGraphDense gQuart = new RandomGraphDense(cpt, cpt / 4);
						RandomGraphDense gMoitie = new RandomGraphDense(cpt, cpt * 3 / 4);
						one_debut[i] = Combination_generator.combinaison(gOne.getList(), gOne, "debut", 0);
						quart_debut[i] = Combination_generator.combinaison(gQuart.getList(), gQuart, "debut",0);
						moitie_debut[i] = Combination_generator.combinaison(gMoitie.getList(), gMoitie, "debut",0);
						one_milieu[i] = Combination_generator.combinaison(gOne.getList(), gOne, "milieu", 0);
						quart_milieu[i] = Combination_generator.combinaison(gQuart.getList(), gQuart, "milieu",0);
						moitie_milieu[i] = Combination_generator.combinaison(gMoitie.getList(), gMoitie, "milieu",0);
						one_fin[i] = Combination_generator.combinaison(gOne.getList(), gOne, "fin", 0);
						quart_fin[i] = Combination_generator.combinaison(gQuart.getList(), gQuart, "fin", 0);
						moitie_fin[i] = Combination_generator.combinaison(gMoitie.getList(), gMoitie, "fin",0);

						one_debut_m[cpt] += one_debut[i];
						quart_debut_m[cpt] += quart_debut[i];
						moitie_debut_m[cpt] += moitie_debut[i];
						one_milieu_m[cpt] += one_milieu[i];
						quart_milieu_m[cpt] += quart_milieu[i];
						moitie_milieu_m[cpt] += moitie_milieu[i];
						one_fin_m[cpt] += one_fin[i];
						quart_fin_m[cpt] += quart_fin[i];
						moitie_fin_m[cpt] += moitie_fin[i];

						/*
						 * myWriter.write(one_debut+"; "+quart_debut+"; "+moitie_debut+";"+
						 * one_milieu+"; "+quart_milieu+"; "+moitie_milieu+";"+
						 * one_fin+"; "+quart_fin+"; "+moitie_fin+";\n");
						 */
					}
					// myWriter.write("\n");

					for (int i = 0; i < max2; i++) {
						et_o_d += (long) Math.pow(one_debut_m[cpt] / max2 - one_debut[i], 2);
						et_q_d += (long) Math.pow(quart_debut_m[cpt] / max2 - quart_debut[i], 2);
						et_m_d += (long) Math.pow(moitie_debut_m[cpt] / max2 - moitie_debut[i], 2);
						et_o_m += (long) Math.pow(one_milieu[cpt] / max2 - one_milieu[i], 2);
						et_q_m += (long) Math.pow(quart_milieu_m[cpt] / max2 - quart_milieu[i], 2);
						et_m_m += (long) Math.pow(moitie_milieu_m[cpt] / max2 - moitie_milieu[i], 2);
						et_o_f += (long) Math.pow(one_fin_m[cpt] / max2 - one_fin[i], 2);
						et_q_f += (long) Math.pow(quart_fin_m[cpt] / max2 - quart_fin[i], 2);
						et_m_f += (long) Math.pow(moitie_fin_m[cpt] / max2 - moitie_fin[i], 2);
					}
					myWriter.write((long) Math.sqrt(et_o_d / max2) + "; " + (long) Math.sqrt(et_q_d / max2) + "; "
							+ (long) Math.sqrt(et_m_d / max2) + ";" + (long) Math.sqrt(et_o_m / max2) + "; "
							+ (long) Math.sqrt(et_q_m / max2) + "; " + (long) Math.sqrt(et_m_m / max2) + ";"
							+ (long) Math.sqrt(et_o_f / max2) + "; " + (long) Math.sqrt(et_q_f / max2) + "; "
							+ (long) Math.sqrt(et_m_f / max2) + ";\n");

					// myWriter.write("\n");

					cpt++;
					System.out.println(cpt);
				}
				myWriter.write("\n");
				myWriter.write("moyenne light debut; moyenne medium debut; moyenne dense debut; "
						+ "moyenne light milieu; moyenne medium milieu; moyenne dense milieu; "
						+ "moyenne light fin; moyenne medium fin; moyenne dense fin\n");
				for (int i = 5; i < max1; i++) {
					myWriter.write(one_debut_m[i] / max2 + "; " + quart_debut_m[i] / max2 + "; "
							+ moitie_debut_m[i] / max2 + ";" + one_milieu_m[i] / max2 + "; " + quart_milieu_m[i] / max2
							+ "; " + moitie_milieu_m[i] / max2 + ";" + one_fin_m[i] / max2 + "; "
							+ quart_fin_m[i] / max2 + "; " + moitie_fin_m[i] / max2 + ";\n");
				}
				myWriter.close();
			} catch (IOException e) {

			}
			break;
		case 2:
			// System.out.println(Math.pow(2, 5));
			float testast = 0;
			int i;
			while(testast<1) {
				int testo = 0;
				int testa = 0;
				int testi = 0;
				int testu = 0;
				cpt = 0;
				while (cpt < 10000) {
					i = (int) (Math.random() * (16));
					i += 5;
					// System.out.println(i);
					g = new RandomGraphDense(i, testast);
					if (g.isSpoutnik()) {
						testo++;
					}
					if (g.isBipartiComplet()) {
						testa++;
					}
					if (g.isCycle()) {
						testi++;
					}
					if(g.isCycle() || g.isBipartiComplet() || g.isSpoutnik()) {
						testu++;
					}
					cpt++;
				}
	
				System.out.println(testo + " et " + testa + " et " + testi+ " et "+testu +" et "+testast);
				testast+=0.1;
			}
			// g = new SputnikGraph(20);
			// System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g,
			// "debut"));

			break;
		case 3:
			cpt = 5;
			while (cpt < 18) {
				/*
				 * long cmoyennemilieu = 0; long tmoyennemilieu = 0; long rmoyennemilieu = 0;
				 * long rdmoyennemilieu = 0; long bmoyennemilieu = 0; long smoyennemilieu = 0;
				 */
				long cmoyennedebut = 0;
				long tmoyennedebut = 0;
				long rmoyennedebut = 0;
				long rdmoyennedebut = 0;
				long bmoyennedebut = 0;
				long smoyennedebut = 0;
				for (int j = 0; j < 20; j++) {
					SputnikGraph sg = new SputnikGraph(cpt);
					CycleGraph cg = new CycleGraph(cpt);
					TreeGraph tg = new TreeGraph(cpt);
					RandomGraphDense rg = new RandomGraphDense(cpt, 0.3);
					RandomGraphDense rdg = new RandomGraphDense(cpt, 0.6);
					BipartiGraph bg = new BipartiGraph(cpt, cpt / 2);
					// System.out.println("isBiparti "+sg.isBiparti()+"; "+cg.isBiparti()+";
					// "+tg.isBiparti()+"; "+rg.isBiparti()+"; "+bg.isBiparti()+"; ");
					/*
					 * smoyennemilieu += Combination_generator.combinaisonOneRes(sg.getList(), sg,
					 * "milieu"); cmoyennemilieu +=
					 * Combination_generator.combinaisonOneRes(cg.getList(), cg, "milieu");
					 * tmoyennemilieu += Combination_generator.combinaisonOneRes(tg.getList(), tg,
					 * "milieu"); rmoyennemilieu +=
					 * Combination_generator.combinaisonOneRes(rg.getList(), rg, "milieu");
					 * rdmoyennemilieu += Combination_generator.combinaisonOneRes(rdg.getList(),
					 * rdg, "milieu"); bmoyennemilieu +=
					 * Combination_generator.combinaisonOneRes(bg.getList(), bg, "milieu");
					 */
					smoyennedebut += Combination_generator.combinaison(sg.getList(), sg, "debut", 0);
					cmoyennedebut += Combination_generator.combinaison(cg.getList(), cg, "debut", 0);
					tmoyennedebut += Combination_generator.combinaison(tg.getList(), tg, "debut", 0);
					rmoyennedebut += Combination_generator.combinaison(rg.getList(), rg, "debut", 0);
					rdmoyennedebut += Combination_generator.combinaison(rdg.getList(), rdg, "debut", 0);
					bmoyennedebut += Combination_generator.combinaison(bg.getList(), bg, "debut", 0);
				}
				System.out
						.println("la moyenne pour des graph de taille : " + cpt + " est de " + " cycle moyenne debut: "
								+ cmoyennedebut / 100 + " ,tree moyenne debut: " + tmoyennedebut / 100
								+ " ,random moyenne debut " + rmoyennedebut / 100 + " ,biparti moyenne debut "
								+ bmoyennedebut / 100 + " ,sputnik moyenne debut: " + smoyennedebut / 100

						/*
						 * +"cycle moyenne milieu: "+cmoyennemilieu/100
						 * +"tree moyenne milieu: "+tmoyennemilieu/100
						 * +"random moyenne milieu "+rmoyennemilieu/100
						 * +"biparti moyenne milieu "+bmoyennemilieu/100
						 * +"sputnik moyenne milieu: "+smoyennemilieu/100
						 */
						);
				cpt++;
			}
			break;
		case 4:

			g = new SputnikGraph(7);
			System.out.println(g);
			break;

		case 5:

			System.out.println("Debut du test");

			// test sur des graphs random trié par le nombre de voisin (Compar)
			// test avec des graphs de taille 5 -> 50
			// 20 tests a chaque fois
			double floata = 0.2;
			cpt = 5;
			while (floata < 0.8) {
				long time_no_t = 0; // aucune opti
				long time_t_nb = 0; // opti nb voisin
				long time_t_mis = 0; // opti nb_voisin mis
				long time_t_nb_REVERSE = 0; // opti nb voisin_REVERSE
				//long time_t_mis_REVERSE = 0; // opti nb_voisin mis_REVERSE
				
				//long time_no_t_inde = 0; // aucune opti
				//long time_t_nb_inde = 0; // opti nb voisin
				//long time_t_mis_inde = 0; // opti nb_voisin mis
				//long time_t_nb_inde_REVERSE = 0; // opti nb voisin_REVERSE
				//long time_t_mis_inde_REVERSE = 0; // opti nb_voisin mis_REVERSE
				
				//long time_no_t_mis = 0; // aucune opti
				//long time_t_nb_mis = 0; // opti nb voisin
				//long time_t_mis_mis = 0; // opti nb_voisin mis
				//long time_t_nb_mis_REVERSE = 0; // opti nb voisin_REVERSE
				//long time_t_mis_mis_REVERSE = 0; // opti nb_voisin mis_REVERSE
	
				//long time_no_t_cut = 0; // aucune opti
				//long time_t_nb_cut = 0; // opti nb voisin
				//long time_t_mis_cut = 0; // opti nb_voisin mis
				//long time_t_nb_cut_REVERSE = 0; // opti nb voisin_REVERSE
				//long time_t_mis_cut_REVERSE = 0; // opti nb_voisin mis_REVERSE
	
				long time_no_t_tot = 0; // aucune opti
				long time_t_nb_tot = 0; // opti nb voisin
				long time_t_mis_tot = 0; // opti nb_voisin mis
				long time_t_nb_tot_REVERSE = 0; // opti nb voisin_REVERSE
				//long time_t_mis_tot_REVERSE = 0; // opti nb_voisin mis_REVERSE
	
				int size = 18;
	
				for (i = 0; i < 10; i++) {
					System.out.println(g);
					Graph g = new RandomGraphDense(size, floata);
					// sans opti
					time_no_t += Combination_generator.combinaison(g.getList(), g, "debut", 0);//LE 0 C'EST POUR LANCER LE TEST SANS TEST AVANT LE ISCUT
	
					//time_no_t_mis += Combination_generator.tempsmistot;
					//time_no_t_cut += Combination_generator.tempscuttot;
					//time_no_t_inde += Combination_generator.tempsindetot;
					time_no_t_tot += Combination_generator.tempstot;

					System.out.println(g);
					// opti voisin
					g.optimize();

					System.out.println(g);
					time_t_mis += Combination_generator.combinaison(g.getList(), g, "debut", 0);
	
					//time_t_mis_mis += Combination_generator.tempsmistot;
					//time_t_mis_cut += Combination_generator.tempscuttot;
					//time_t_mis_inde += Combination_generator.tempsindetot;
					time_t_mis_tot += Combination_generator.tempstot;

					System.out.println(g);
					// opti mis
					time_t_nb += Combination_generator.combinaison(g.getList(), g, "debut", 1);//LE 1 C'EST POUR LANCER LE TEST EN TRIANT AVANT CHAQUE ISCUT
	
					//time_t_nb_mis += Combination_generator.tempsmistot;
					//time_t_nb_cut += Combination_generator.tempscuttot;
					//time_t_nb_inde += Combination_generator.tempsindetot;
					time_t_nb_tot += Combination_generator.tempstot;
	
					
					
					// opti mis REVERSE
					//time_t_mis_REVERSE += Combination_generator.combinaison(g.getList(), g, "debut", 2);//LE 2 C'EST POUR LANCER LE TEST EN TRIANT AVANT CHAQUE ISCUT EN REVERSE
	
					//time_t_nb_mis_REVERSE += Combination_generator.tempsmistot;
					//time_t_nb_cut_REVERSE += Combination_generator.tempscuttot;
					//time_t_nb_inde_REVERSE += Combination_generator.tempsindetot;
					//time_t_nb_tot_REVERSE += Combination_generator.tempstot;

					System.out.println(g);
					// opti voisin REVERSE
					g.optimizeREVERSE();
					time_t_nb_REVERSE += Combination_generator.combinaison(g.getList(), g, "debut", 0);
	
					//time_t_mis_mis_REVERSE += Combination_generator.tempsmistot;
					//time_t_mis_cut_REVERSE += Combination_generator.tempscuttot;
					//time_t_mis_inde_REVERSE += Combination_generator.tempsindetot;
					time_t_nb_tot_REVERSE += Combination_generator.tempstot;

					System.out.println(g);
				}
				System.out.println("\n\ntime for NOT sorted size (" + floata + ") : " + (time_no_t / 1000) / 20);
				System.out.println("temps : " + (time_no_t_tot / 1000) / 20);
				//System.out.println("temps inde : " + (time_no_t_inde / 1000) / 20);
				//System.out.println("temps mis : " + (time_no_t_mis / 1000) / 20);
				//System.out.println("temps cut : " + (time_no_t_cut / 1000) / 20);
	
				System.out.println("\ntime for compa1 sorted size (" + floata + ") : " + (time_t_nb / 1000) / 20);
				System.out.println("temps : " + (time_t_nb_tot / 1000) / 20);
				//System.out.println("temps inde : " + (time_t_nb_inde / 1000) / 20);
				//System.out.println("temps mis : " + (time_t_nb_mis / 1000) / 20);
				//System.out.println("temps cut : " + (time_t_nb_cut / 1000) / 20);
	
				System.out.println("\ntime for compa2 sorted size (" + floata + ") : " + (time_t_mis / 1000) / 20);
				System.out.println("temps : " + (time_t_mis_tot / 1000) / 20);
				//System.out.println("temps inde : " + (time_t_mis_inde / 1000) / 20);
				//System.out.println("temps mis : " + (time_t_mis_mis / 1000) / 20);
				//System.out.println("temps cut : " + (time_t_mis_cut / 1000) / 20);
				
				System.out.println("\ntime for compa1 REVERSE sorted size (" + floata + ") : " + (time_t_nb_REVERSE / 1000) / 20);
				System.out.println("temps : " + (time_t_nb_tot_REVERSE / 1000) / 20);
				//System.out.println("temps inde : " + (time_t_nb_inde_REVERSE / 1000) / 20);
				//System.out.println("temps mis : " + (time_t_nb_mis_REVERSE / 1000) / 20);
				//System.out.println("temps cut : " + (time_t_nb_cut_REVERSE / 1000) / 20);
	
				//System.out.println("\ntime for compa2 REVERSE sorted size (" + cpt + ") : " + (time_t_mis_REVERSE / 1000) / 20);
				//System.out.println("temps : " + (time_t_mis_tot_REVERSE / 1000) / 20);
				//System.out.println("temps inde : " + (time_t_mis_inde_REVERSE / 1000) / 20);
				//System.out.println("temps mis : " + (time_t_mis_mis_REVERSE / 1000) / 20);
				//System.out.println("temps cut : " + (time_t_mis_cut_REVERSE / 1000) / 20);
				cpt++;
				floata+=0.1;
			}
			break; 
			
		case 6:
			g = new RandomGraphDense(10,0.3);
			System.out.println(g);
			g.optimize();
			System.out.println(g);
			g.optimizeREVERSE();
			System.out.println(g);
			break;
			// }
		case 7:
			int yolo = 0;
			int res = 0;
			while(yolo < 20000) {
				g = new BipartiGraph(30);
				try {
					
				if(g.isBipartiComplet()) {
					res++;
				}
				}catch(IndexOutOfBoundsException e) {
					System.out.println("pb viens de isBipartiComplet avec le graph : " 
							+ g);
				}
				yolo++;
			}
			System.out.println(res);
			break;
		}
	}
}


/*
 * 
 * 
	public static boolean isStable(Graph g, ArrayList<Node> valid_list) {
		for (Node node : g.getList())
		{
			if (!Common_methods.isIn(node, valid_list)) {
				for (Node node_neighbor : node.getNeighbors()) 
				{
					if (!Common_methods.isIn(node_neighbor, valid_list)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	public static ArrayList<ArrayList<Node>> getMIS(ArrayList<int[]> tab,Graph g) {
		ArrayList<ArrayList<Node>> res = new ArrayList<ArrayList<Node>>();

		for (int[] t : tab) {
			ArrayList<Node> listInde = Common_methods.transform(t, g);
			ArrayList<Node> alist = Common_methods.reverseMIS(listInde, g);
			boolean isMaxInde;
			boolean isBad = false;
			for (Node n : alist) {
				isMaxInde = true;
				for (Node neigh : n.getNeighbors()) {
					if (Common_methods.isIn(neigh, listInde)) {
						isMaxInde = false; // false car n pourrait etre dans l'ensemble de point independant
					}
				}
				if (isMaxInde == true) {
					isBad = true;
					break;
				}
			}
			if (isBad == false) {
				res.add(listInde);
			}
		}

		return res;
	}
	
	
	
	
	
	public static void testCut(ArrayList<ArrayList<Node>> aalist, Graph g) {
		int mistot = 0;
		int misrob = 0;
		// prend en parametre la liste de mis. puis cré un graph specifique de
		// test pour
		// chacun des nodes faibles. ces grapes sont alors testé

		for (ArrayList<Node> list : aalist) {
			ArrayList<Node> reverselist = Common_methods.reverseMIS(list, g);

			boolean isMISrobust = true;
			for (Node n : reverselist) {
				ArrayList<Node> listgraph = new ArrayList<Node>();
				for (Node n_neigh : n.getNeighbors()) {
					// creation de l'arraylist constitué des points fort voisin
					// de n utilisé dans le
					// constructeur pour pouvoir cree un graph test
					if (Common_methods.isIn(n_neigh, list)) {
						listgraph.add(n_neigh);
					}
				}
				Graph graph = new Graph(g, n, listgraph);// on genere un nouveau graph sans les connections pour le test
				//System.out.println(graph);//"\ng : "+g+"\nn : "+n+"\nlistgraph : "+listgraph+"\n"+
				//System.out.println("" + isLinked(n, graph.getList())); // if isLinked true -> le MIS n'est pas robuste
				if(isLinked(n,graph.getList())) {
					isMISrobust = false;
					break;
				}
				
			}
			if(isMISrobust) {
				System.out.println("on a un MIS robust, avec comme point : "+list+" dans le graph : "+g);
				misrob++;
			}else{
				System.out.println("le MIS n'est pas robuste, avec comme point : "+list+" dans le graph : "+g);
			}
			mistot++;
		}
		System.out.println("le ratio MIS possible MIS robuste :"+misrob+"/"+mistot);
	}
 */

















