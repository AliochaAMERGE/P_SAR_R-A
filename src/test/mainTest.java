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
		
		
		//System.out.println("isBiparti "+g.isBiparti()+";");
		/* 
		 * test sur chacun des graphes en fonctions de leur taille et potentiellement de leur parametre (arbre taille)
		 * test : temps, fiabilité 
		 * etudes des resultats
		 * en fonction du type de graph combien de MIS/ combien sont robustes
		 * qu'est ce qui permettrait a l'algo d'etre plus efficace dans en fonction du type de graph
		 * étude de la complexité
		 */
//		g.load("src/testgraph2.txt");
		// System.out.println("Graph Loaded");
		// System.out.println(g);
		// System.out.println("----------------------");
		// g.optimize();
		// System.out.println(g);
		// System.out.println("----------------------");
		// int[] tab_test = {0,1,2,3,4,5,6};
		// int [][] res = Independant_set_detector.combinaison(tab_test);
		////////////////ArrayList<int[]> res = Combination_generator.combinaison(g.getList(), g);
		
		
		////////////////TEST DIRCET///////////////////
		//System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g, "fin"));
		
		//System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g, "debut"));

		//System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g, "milieu"));

		
		int cpt;
		FileWriter myWriter;
		
		switch (type) {
			case 0:
				cpt = 5;
				try {
					myWriter = new FileWriter("src/test_files/test.csv");
				
					myWriter.write("taille; dureeAnneauDebut; dureeAnneauMilieu; dureeAnneauFin; "+
							"dureeArbreDebut; dureeArbreMilieu; dureeArbreFin; "+
							"dureeRandomLightDebut; dureeRandomLightMilieu; dureeRandomLightFin; "+
							"dureeRandomDenseDebut; dureeRandomDenseMilieu; dureeRandomDenseFin; "+
							"dureebipartiDebut; dureebipartiMilieu; dureebipartiFin; "+
							"dureesputnikDebut; dureesputnikMilieu; dureesputnikFin\n");
					while(cpt < 18) {
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
						for(int j = 0; j < 10; j++) {
							SputnikGraph sg = new SputnikGraph(cpt);
							CycleGraph cg = new CycleGraph(cpt);
							TreeGraph tg = new TreeGraph(cpt);
							RandomGraphDense rg = new RandomGraphDense(cpt,2);
							RandomGraphDense rdg = new RandomGraphDense(cpt,cpt/2);
							BipartiGraph bg = new BipartiGraph(cpt, cpt/2);
							//System.out.println("isBiparti "+sg.isBiparti()+"; "+cg.isBiparti()+"; "+tg.isBiparti()+"; "+rg.isBiparti()+"; "+bg.isBiparti()+"; ");
							smoyennemilieu += Combination_generator.combinaisonOneRes(sg.getList(), sg, "milieu", false);
							cmoyennemilieu += Combination_generator.combinaisonOneRes(cg.getList(), cg, "milieu", false);
							tmoyennemilieu += Combination_generator.combinaisonOneRes(tg.getList(), tg, "milieu", false);
							rmoyennemilieu += Combination_generator.combinaisonOneRes(rg.getList(), rg, "milieu", false);
							rdmoyennemilieu += Combination_generator.combinaisonOneRes(rdg.getList(), rdg, "milieu", false);
							bmoyennemilieu += Combination_generator.combinaisonOneRes(bg.getList(), bg, "milieu", false);
							smoyennefin += Combination_generator.combinaisonOneRes(sg.getList(), sg, "fin", false);
							cmoyennefin += Combination_generator.combinaisonOneRes(cg.getList(), cg, "fin", false);
							tmoyennefin += Combination_generator.combinaisonOneRes(tg.getList(), tg, "fin", false);
							rmoyennefin += Combination_generator.combinaisonOneRes(rg.getList(), rg, "fin", false);
							rdmoyennefin += Combination_generator.combinaisonOneRes(rdg.getList(), rdg, "fin", false);
							bmoyennefin += Combination_generator.combinaisonOneRes(bg.getList(), bg, "fin", false);
							smoyennedebut += Combination_generator.combinaisonOneRes(sg.getList(), sg, "debut", false);
							cmoyennedebut += Combination_generator.combinaisonOneRes(cg.getList(), cg, "debut", false);
							tmoyennedebut += Combination_generator.combinaisonOneRes(tg.getList(), tg, "debut", false);
							rmoyennedebut += Combination_generator.combinaisonOneRes(rg.getList(), rg, "debut", false);
							rdmoyennedebut += Combination_generator.combinaisonOneRes(rdg.getList(), rdg, "debut", false);
							bmoyennedebut += Combination_generator.combinaisonOneRes(bg.getList(), bg, "debut", false);
						}
						/*System.out.println("la moyenne pour des graph de taille : "+cpt+" est de "+
								"cycle moyenne debut: "+cmoyennedebut/100
								+"tree moyenne debut: "+tmoyennedebut/100
								+"random moyenne debut "+rmoyennedebut/100
								+"biparti moyenne debut "+bmoyennedebut/100
								+"sputnik moyenne debut: "+smoyennedebut/100
								
								+"cycle moyenne fin: "+cmoyennefin/100
								+"tree moyenne fin: "+tmoyennefin/100
								+"random moyenne fin "+rmoyennefin/100
								+"biparti moyenne fin "+bmoyennefin/100
								+"sputnik moyenne fin: "+smoyennefin/100
								
								+"cycle moyenne milieu: "+cmoyennemilieu/100
								+"tree moyenne milieu: "+tmoyennemilieu/100
								+"random moyenne milieu "+rmoyennemilieu/100
								+"biparti moyenne milieu "+bmoyennemilieu/100
								+"sputnik moyenne milieu: "+smoyennemilieu/100
								);*/
						myWriter.write(cpt+"; "+
								cmoyennedebut/100+"; "+
								cmoyennemilieu/100+"; "+
								cmoyennefin/100+"; "+
								tmoyennedebut/100+"; "+
								tmoyennemilieu/100+"; "+
								tmoyennefin/100+"; "+
								rmoyennedebut/100+"; "+
								rmoyennemilieu/100+"; "+
								rmoyennefin/100+"; "+
								rdmoyennedebut/100+"; "+
								rdmoyennemilieu/100+"; "+
								rdmoyennefin/100+"; "+
								bmoyennedebut/100+"; "+
								bmoyennemilieu/100+"; "+
								bmoyennefin/100+"; "+
								smoyennedebut/100+"; "+
								smoyennemilieu/100+"; "+
								smoyennefin/100+"\n");
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
				
					myWriter.write("ecart type light debut; ecart type medium milieu; ecart type dense fin; "+
							"ecart type light debut; ecart type medium milieu; ecart type dense fin; "+
							"ecart type light debut; ecart type medium milieu; ecart type dense fin\n");
					
					
					long one_debut[] = new long[max2];
					long quart_debut[] = new long[max2];
					long moitie_debut[] = new long[max2];
					long one_milieu[] = new long[max2];
					long quart_milieu[] = new long[max2];
					long moitie_milieu[] = new long[max2];
					long one_fin[] = new long[max2];
					long quart_fin[] = new long[max2];
					long moitie_fin[] = new long[max2];
					
					long one_debut_m [] = new long[max1];
					long quart_debut_m [] = new long[max1];
					long moitie_debut_m [] = new long[max1];
					long one_milieu_m [] = new long[max1];
					long quart_milieu_m [] = new long[max1];
					long moitie_milieu_m [] = new long[max1];
					long one_fin_m [] = new long[max1];
					long quart_fin_m [] = new long[max1];
					long moitie_fin_m [] = new long[max1];
					
					long et_o_d;
					long et_q_d;
					long et_m_d;
					long et_o_m;
					long et_q_m;
					long et_m_m;
					long et_o_f;
					long et_q_f;
					long et_m_f;
					
					while(cpt < max1) {
						one_debut_m[cpt]=0;
						quart_debut_m[cpt]=0;
						moitie_debut_m[cpt]=0;
						one_milieu_m[cpt]=0;
						quart_milieu_m[cpt]=0;
						moitie_milieu_m[cpt]=0;
						one_fin_m[cpt]=0;
						quart_fin_m[cpt]=0;
						moitie_fin_m[cpt]=0;
						
						et_o_d=0;
						et_q_d=0;
						et_m_d=0;
						et_o_m=0;
						et_q_m=0;
						et_m_m=0;
						et_o_f=0;
						et_q_f=0;
						et_m_f=0;
						
						for(int i =0; i<max2; i++) {
							RandomGraphDense gOne = new RandomGraphDense(cpt, 1);
							RandomGraphDense gQuart = new RandomGraphDense(cpt, cpt/4);
							RandomGraphDense gMoitie = new RandomGraphDense(cpt, cpt*3/4);
							one_debut[i] = Combination_generator.combinaisonOneRes(gOne.getList(), gOne, "debut", false);
							quart_debut[i] = Combination_generator.combinaisonOneRes(gQuart.getList(), gQuart, "debut", false);
							moitie_debut[i] = Combination_generator.combinaisonOneRes(gMoitie.getList(), gMoitie, "debut", false);
							one_milieu[i] = Combination_generator.combinaisonOneRes(gOne.getList(), gOne, "milieu", false);
							quart_milieu[i] = Combination_generator.combinaisonOneRes(gQuart.getList(), gQuart, "milieu", false);
							moitie_milieu[i] = Combination_generator.combinaisonOneRes(gMoitie.getList(), gMoitie, "milieu", false);
							one_fin[i] = Combination_generator.combinaisonOneRes(gOne.getList(), gOne, "fin", false);
							quart_fin[i] = Combination_generator.combinaisonOneRes(gQuart.getList(), gQuart, "fin", false);
							moitie_fin[i] = Combination_generator.combinaisonOneRes(gMoitie.getList(), gMoitie, "fin", false);
							
							one_debut_m[cpt]+=one_debut[i];
							quart_debut_m[cpt]+=quart_debut[i];
							moitie_debut_m[cpt]+=moitie_debut[i];
							one_milieu_m[cpt]+=one_milieu[i];
							quart_milieu_m[cpt]+=quart_milieu[i];
							moitie_milieu_m[cpt]+=moitie_milieu[i];
							one_fin_m[cpt]+=one_fin[i];
							quart_fin_m[cpt]+=quart_fin[i];
							moitie_fin_m[cpt]+=moitie_fin[i];
							
							/*myWriter.write(one_debut+"; "+quart_debut+"; "+moitie_debut+";"+
									one_milieu+"; "+quart_milieu+"; "+moitie_milieu+";"+
									one_fin+"; "+quart_fin+"; "+moitie_fin+";\n");*/
						}
						//myWriter.write("\n");
						
						for(int i =0; i<max2; i++) {
							et_o_d+=(long)Math.pow(one_debut_m[cpt]/max2-one_debut[i],2);
							et_q_d+=(long)Math.pow(quart_debut_m[cpt]/max2-quart_debut[i], 2);
							et_m_d+=(long)Math.pow(moitie_debut_m[cpt]/max2-moitie_debut[i], 2);
							et_o_m+=(long)Math.pow(one_milieu[cpt]/max2-one_milieu[i],2);
							et_q_m+=(long)Math.pow(quart_milieu_m[cpt]/max2-quart_milieu[i],2);
							et_m_m+=(long)Math.pow(moitie_milieu_m[cpt]/max2-moitie_milieu[i],2);
							et_o_f+=(long)Math.pow(one_fin_m[cpt]/max2-one_fin[i],2);
							et_q_f+=(long)Math.pow(quart_fin_m[cpt]/max2-quart_fin[i],2);
							et_m_f+=(long)Math.pow(moitie_fin_m[cpt]/max2-moitie_fin[i],2);
						}
						myWriter.write((long)Math.sqrt(et_o_d/max2)+"; "+(long)Math.sqrt(et_q_d/max2)+"; "+(long)Math.sqrt(et_m_d/max2)+";"+
								(long)Math.sqrt(et_o_m/max2)+"; "+(long)Math.sqrt(et_q_m/max2)+"; "+(long)Math.sqrt(et_m_m/max2)+";"+
								(long)Math.sqrt(et_o_f/max2)+"; "+(long)Math.sqrt(et_q_f/max2)+"; "+(long)Math.sqrt(et_m_f/max2)+";\n");
						
						//myWriter.write("\n");
						
						cpt++;
						System.out.println(cpt);
					}
					myWriter.write("\n");
					myWriter.write("moyenne light debut; moyenne medium debut; moyenne dense debut; "+
							"moyenne light milieu; moyenne medium milieu; moyenne dense milieu; "+
							"moyenne light fin; moyenne medium fin; moyenne dense fin\n");
					for(int i = 5; i<max1; i++ ) {
						myWriter.write(one_debut_m[i]/max2+"; "+quart_debut_m[i]/max2+"; "+moitie_debut_m[i]/max2+";"+
								one_milieu_m[i]/max2+"; "+quart_milieu_m[i]/max2+"; "+moitie_milieu_m[i]/max2+";"+
								one_fin_m[i]/max2+"; "+quart_fin_m[i]/max2+"; "+moitie_fin_m[i]/max2+";\n");
					}
				myWriter.close();
				}catch(IOException e){
					
				}
				break;
			case 2: 
				//System.out.println(Math.pow(2, 5));
				cpt = 0;
				int i;
				int testo = 0;
				int testa = 0;
				int testi = 0;
				while(cpt<100000) {
					i = (int) (Math.random() * (16));
					i+=5;
					//System.out.println(i);
					g = new RandomGraphDense(i,0.3);
					if(g.isSpoutnik()) {
						testo++;	
					}
					if(g.isBiparti()) {
						testa++;	
					}
					if(g.isBiparti() && g.isSpoutnik()) {
						testi++;
					}
					cpt++;
				}
				//g = new SputnikGraph(20);
				//System.out.println(Combination_generator.combinaisonOneRes(g.getList(), g, "debut"));
				System.out.println(testo + " et "+testa+ " et "+testi);
				
				break;
			case 3:
				cpt = 5;
				while(cpt < 18) {
					/*long cmoyennemilieu = 0;
					long tmoyennemilieu = 0;
					long rmoyennemilieu = 0;
					long rdmoyennemilieu = 0;
					long bmoyennemilieu = 0;
					long smoyennemilieu = 0;*/
					long cmoyennedebut = 0;
					long tmoyennedebut = 0;
					long rmoyennedebut = 0;
					long rdmoyennedebut = 0;
					long bmoyennedebut = 0;
					long smoyennedebut = 0;
					for(int j = 0; j < 20; j++) {
						SputnikGraph sg = new SputnikGraph(cpt);
						CycleGraph cg = new CycleGraph(cpt);
						TreeGraph tg = new TreeGraph(cpt);
						RandomGraphDense rg = new RandomGraphDense(cpt,0.3);
						RandomGraphDense rdg = new RandomGraphDense(cpt,0.6);
						BipartiGraph bg = new BipartiGraph(cpt, cpt/2);
						//System.out.println("isBiparti "+sg.isBiparti()+"; "+cg.isBiparti()+"; "+tg.isBiparti()+"; "+rg.isBiparti()+"; "+bg.isBiparti()+"; ");
						/*smoyennemilieu += Combination_generator.combinaisonOneRes(sg.getList(), sg, "milieu");
						cmoyennemilieu += Combination_generator.combinaisonOneRes(cg.getList(), cg, "milieu");
						tmoyennemilieu += Combination_generator.combinaisonOneRes(tg.getList(), tg, "milieu");
						rmoyennemilieu += Combination_generator.combinaisonOneRes(rg.getList(), rg, "milieu");
						rdmoyennemilieu += Combination_generator.combinaisonOneRes(rdg.getList(), rdg, "milieu");
						bmoyennemilieu += Combination_generator.combinaisonOneRes(bg.getList(), bg, "milieu");*/
						smoyennedebut += Combination_generator.combinaisonOneRes(sg.getList(), sg, "debut", false);
						cmoyennedebut += Combination_generator.combinaisonOneRes(cg.getList(), cg, "debut", false);
						tmoyennedebut += Combination_generator.combinaisonOneRes(tg.getList(), tg, "debut", false);
						rmoyennedebut += Combination_generator.combinaisonOneRes(rg.getList(), rg, "debut", false);
						rdmoyennedebut += Combination_generator.combinaisonOneRes(rdg.getList(), rdg, "debut", false);
						bmoyennedebut += Combination_generator.combinaisonOneRes(bg.getList(), bg, "debut", false);
					}
					System.out.println("la moyenne pour des graph de taille : "+cpt+" est de "+
							" cycle moyenne debut: "+cmoyennedebut/100
							+" ,tree moyenne debut: "+tmoyennedebut/100
							+" ,random moyenne debut "+rmoyennedebut/100
							+" ,biparti moyenne debut "+bmoyennedebut/100
							+" ,sputnik moyenne debut: "+smoyennedebut/100
							
							/*+"cycle moyenne milieu: "+cmoyennemilieu/100
							+"tree moyenne milieu: "+tmoyennemilieu/100
							+"random moyenne milieu "+rmoyennemilieu/100
							+"biparti moyenne milieu "+bmoyennemilieu/100
							+"sputnik moyenne milieu: "+smoyennemilieu/100*/
							);
					cpt++;
				}
				break;
			case 4: 

				g = new SputnikGraph(7);
				System.out.println(g);
				break;

			case 5:
				
				// test sur des graphs random trié par le nombre de voisin (Compar)
				// test avec des graphs de taille 5 -> 50
				// 20 tests a chaque fois
				
				cpt = 5;
				while (cpt < 30) {
					long time_no_t = 0; // aucune opti
					long time_t_nb = 0;  // opti nb voisin
					long time_t_mis = 0; // opti nb_voisin mis
					long time_t_both = 0; // both opti
					for (i = 0; i < 20; i++) {
						Graph g = new RandomGraphDense(cpt, 0.3);
						// sans opti
						time_no_t += Combination_generator.combinaisonOneRes(g.getList(), g, "debut", false);
						// opti mis
						time_t_mis += Combination_generator.combinaisonOneRes(g.getList(), g, "debut", true);
						// opti voisin
						g.optimize();
						time_t_nb += Combination_generator.combinaisonOneRes(g.getList(), g, "debut", false);
						// les deux
						time_t_both += Combination_generator.combinaisonOneRes(g.getList(), g, "debut", true);
					}
					System.out.println("time for NOT sorted size ("+cpt+") : "+ (time_no_t/100)/20 + "ms");
					System.out.println("time for compa1 sorted size ("+cpt+") : "+ (time_t_nb/100)/20 + "ms");
					System.out.println("time for compa2 sorted size ("+cpt+") : "+ (time_t_mis/100)/20 + "ms");
					System.out.println("time for compa1&2 sorted size ("+cpt+") : "+ (time_t_both/100)/20 + "ms");
					cpt++;
				}
				
		}
	}
}
