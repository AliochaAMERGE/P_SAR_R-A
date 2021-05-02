package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.graph.BipartiGraphComplet;
import core.graph.CycleGraph;
import core.graph.Graph;
import core.graph.RandomGraphDense;
import core.graph.SputnikGraph;
import core.graph.TreeGraph;
import utils.Combination_generator;

public class TestParametre {

  public static void main(String[] args) throws IOException {

    /**
     * Classe de test principale
     * 
     * Les différents tests possibles sont les suivants ;
     * 
     * Choix de graphes : boolean (1 pour chaque instance de graphe) { - Random
     * (dense) - Sputnik - Bipartite (complet) - arbre - cycle - lecture d'un graphe
     * deja preconstruit }
     * 
     * Taille des graphes : intervalle : int *2 Nombre de tests pour instance de
     * meme taille : int
     * 
     * Sauvegade dans un .csv (en indiant son nom) : String sauvegardé dans le
     * dosser test-files
     * 
     * Début de la recherche : unique int { 0, 1, 2 } { - début - milieu - fin }
     * 
     * Temps : juste le temps d'execution total, ou le temps de toutes les méthodes
     * séparément
     * 
     * Tri des listes : { 0 - aucun tri - 1 - tri par nb voisin - 2 - tri par nb
     * voisins dans le MIS - 3 -tri par nb voisin reverse - 4 - tri par nb voisins
     * dans le MIS reverse }
     */

    // todo :
    // - paramétré l'ecriture dans le fichier (en fonction des graphes choisis)

    /* Parametres en dur pour le moment */

    // contient les graphes que nous testerons
    /*
     * 0 - Random (dense) 1 - Sputnik 2 - Bipartite (complet) 3 - arbre 4 - cycle
     */

    List<Boolean> graphs_test_param = new ArrayList<Boolean>() {
      {
        add(true); // random dense (density not defined yet)
        add(true); // Sputnik
        add(true); // Bipartite
        add(false); // arbre
        add(false); // cycle
      }
    };
    // densité pour le graph random
    double random_density = 0.3;

    // interval de taille des graphes, si nous voulons une unique taille : start =
    // end
    int graph_size_start_param = 5;
    int graph_size_end_param = 15;

    // nombre de tests pour chaque graphes (pour une taille donnée)
    int nb_tests_param = 20;

    // Debut de la recherche :
    /*
     * debut - milieu - fin
     */
    String recherche_param = "debut";

    // sauvegarde dans un fichier : String

    String file_param = "src/test_files/test" + System.currentTimeMillis() + ".csv";

    // temps des méthodes {true = toutes les méthodes, false = temps total
    // uniquement}
    boolean test_temps_param = true;

    // tri
    int tri_param = 0;

    /***************************************************************
     ******************* utilisation d'un preset *******************
     ***************************************************************/

    String preset_path = "src/test/test1.txt";

    if (preset_path != "") {
      BufferedReader myReader = new BufferedReader(new FileReader(preset_path));
      String line = myReader.readLine();
      
      // comment
      line = myReader.readLine();
      
      // title
      line = myReader.readLine();
      
      // choix des graphes à tester
      String[] param = line.split(" ");
      for (int i = 0; i < 5; i++) {
        graphs_test_param.set(i, (param[i].equals("1")));
      }

      // comment
      line = myReader.readLine();

      // random_density
      line = myReader.readLine();
      random_density = Double.parseDouble(line);

      // comment
      line = myReader.readLine();

      // interval de taille des graphes, si nous voulons une unique taille
      line = myReader.readLine();
      param = line.split(" ");
      graph_size_start_param = Integer.parseInt(param[0]);
      graph_size_end_param = Integer.parseInt(param[1]);

      // comment
      line = myReader.readLine();

      // nombre de tests pour une taille donnée
      line = myReader.readLine();
      nb_tests_param = Integer.parseInt(line);

      // comment
      line = myReader.readLine();

      // emplacement du début de la recherche
      recherche_param = myReader.readLine();
      ;

      // comment
      line = myReader.readLine();

      // emplacement de sauvegarde
      file_param = myReader.readLine();

      // comment
      line = myReader.readLine();

      // sauvegarde des temps des méthodes
      test_temps_param = (myReader.readLine().equals("1"));

      // comment
      line = myReader.readLine();

      // choix du tri
      tri_param = Integer.parseInt(myReader.readLine());

      myReader.close();
    }

    /****************************************************************
     ********************** FIN DES PARAMETREs **********************
     ***************************************************************/

    FileWriter myWriter;

    Graph randomDense;
    Graph sputnik;
    Graph bipartiCompleted;
    Graph tree;
    Graph cycle;
    // temps totaux

    /* Sauvegarde dans un fichier */
    myWriter = new FileWriter(file_param);
    // todo ecrire les entetes
    /* changement de taille des graphes */
    for (int i = graph_size_start_param; i <= graph_size_end_param; i++) {

      long durationrandom = 0;
      long durationsputnik = 0;
      long duration_biparti = 0;
      long durationstree = 0;
      long durationcycle = 0;

      long durationrandominde = 0;
      long durationrandomcut = 0;
      long durationrandommis = 0;

      long durationsputnikinde = 0;
      long durationsputnikcut = 0;
      long durationsputnikmis = 0;

      long duration_bipartiinde = 0;
      long duration_biparticut = 0;
      long duration_bipartimis = 0;

      long durationstreeinde = 0;
      long durationstreecut = 0;
      long durationstreemis = 0;

      long durationcycleinde = 0;
      long durationcyclecut = 0;
      long durationcyclemis = 0;

      /* nombre de répétitions pour une taille données */
      for (int j = 0; j < nb_tests_param; j++) {

        /* Graphe Random Dense */
        if (graphs_test_param.get(0)) {
          // System.out.println("Création graph random dense taille " + i);
          randomDense = new RandomGraphDense(i, random_density);

          if (tri_param == 1) {
            randomDense.optimize();
          } else if (tri_param == 2) {
            randomDense.optimizeREVERSE();
          }

          if (!test_temps_param) {
            /* uniquement le temps totale d'execution */
            durationrandom = Combination_generator.combinaisonnoT(randomDense.getList(), randomDense,
                recherche_param, tri_param);
          } else {
            /* temps des fonctions séparément */
            durationrandom = Combination_generator.combinaison(randomDense.getList(), randomDense,
                recherche_param, tri_param);
            durationrandominde += Combination_generator.tempsindetot;
            durationrandomcut += Combination_generator.tempscuttot;
            durationrandommis += Combination_generator.tempsmistot;
          }
        }
        /* Graphe Sputnik */
        if (graphs_test_param.get(1)) {
          // System.out.println("Création graph Sputnik taille " + i);
          sputnik = new SputnikGraph(i);

          if (tri_param == 1) {
            sputnik.optimize();
          } else if (tri_param == 2) {
            sputnik.optimizeREVERSE();
          }

          if (!test_temps_param) {
            /* uniquement le temps totale d'execution */
            durationsputnik = Combination_generator.combinaisonnoT(sputnik.getList(), sputnik, recherche_param,
                tri_param);
          } else {
            /* temps des fonctions séparément */
            durationsputnik = Combination_generator.combinaison(sputnik.getList(), sputnik, recherche_param,
                tri_param);
            durationsputnikinde += Combination_generator.tempsindetot;
            durationsputnikcut += Combination_generator.tempscuttot;
            durationsputnikmis += Combination_generator.tempsmistot;
          }
        }
        /* Graphe Biparti Complet */
        if (graphs_test_param.get(2)) {
          // System.out.println("Création graph biparti complet taille " + i);
          bipartiCompleted = new BipartiGraphComplet(i);

          if (tri_param == 1) {
            bipartiCompleted.optimize();
          } else if (tri_param == 2) {
            bipartiCompleted.optimizeREVERSE();
          }

          if (!test_temps_param) {
            /* uniquement le temps totale d'execution */
            duration_biparti = Combination_generator.combinaisonnoT(bipartiCompleted.getList(), bipartiCompleted,
                recherche_param, tri_param);
          } else {
            /* temps des fonctions séparément */
            duration_biparti = Combination_generator.combinaison(bipartiCompleted.getList(), bipartiCompleted,
                recherche_param, tri_param);
            duration_bipartiinde += Combination_generator.tempsindetot;
            duration_biparticut += Combination_generator.tempscuttot;
            duration_bipartimis += Combination_generator.tempsmistot;
          }
        }
        /* Arbre */
        if (graphs_test_param.get(3)) {
          // System.out.println("Création arbre taille " + i);
          tree = new TreeGraph(i);

          if (tri_param == 1) {
            tree.optimize();
          } else if (tri_param == 2) {
            tree.optimizeREVERSE();
          }

          if (!test_temps_param) {
            /* uniquement le temps totale d'execution */
            durationstree = Combination_generator.combinaisonnoT(tree.getList(), tree, recherche_param,
                tri_param);
          } else {
            /* temps des fonctions séparément */
            durationstree = Combination_generator.combinaison(tree.getList(), tree, recherche_param, tri_param);
            durationstreeinde += Combination_generator.tempsindetot;
            durationstreecut += Combination_generator.tempscuttot;
            durationstreemis += Combination_generator.tempsmistot;
          }
        }
        /* Cycle */
        if (graphs_test_param.get(4)) {
          // System.out.println("Création cycle taille " + i);
          cycle = new CycleGraph(i);

          if (tri_param == 1) {
            cycle.optimize();
          } else if (tri_param == 2) {
            cycle.optimizeREVERSE();
          }

          if (!test_temps_param) {
            /* uniquement le temps totale d'execution */
            durationcycle = Combination_generator.combinaisonnoT(cycle.getList(), cycle, recherche_param,
                tri_param);
          } else {
            /* temps des fonctions séparément */
            durationcycle = Combination_generator.combinaison(cycle.getList(), cycle, recherche_param, tri_param);
            durationcycleinde += Combination_generator.tempsindetot;
            durationcyclecut += Combination_generator.tempscuttot;
            durationcyclemis += Combination_generator.tempsmistot;
          }
        }
      }
      // écriture des temps dans le fichier : les graphs non testé seront noté avec
      // une valeurs = 0
      if (file_param != "") {
        myWriter.write("\n");
        if (!test_temps_param) {
          // on écrit uniquement les temps totaux
          myWriter.write(i + ";" + durationrandom / nb_tests_param * 1000 + ";"
              + durationsputnik / nb_tests_param * 1000 + ";" + duration_biparti / nb_tests_param * 1000 + ";"
              + durationstree / nb_tests_param * 1000 + ";" + durationcycle / nb_tests_param * 1000);
        } else {
          // on écrit tout les temps de chaque méthodes
          myWriter.write(
              i + ";" + durationrandom / nb_tests_param * 1000 + ";" + durationrandominde / nb_tests_param * 1000 + ";"
                  + durationrandomcut / nb_tests_param * 1000 + ";" + durationrandommis / nb_tests_param * 1000 + ";"
                  + durationsputnik / nb_tests_param * 1000 + ";" + durationsputnikinde / nb_tests_param * 1000 + ";"
                  + durationsputnikcut / nb_tests_param * 1000 + ";" + durationsputnikmis / nb_tests_param * 1000 + ";"
                  + duration_biparti / nb_tests_param * 1000 + ";" + duration_bipartiinde / nb_tests_param * 1000 + ";"
                  + duration_biparticut / nb_tests_param * 1000 + ";" + duration_bipartimis / nb_tests_param * 1000
                  + ";" + durationstree / nb_tests_param * 1000 + ";" + durationstreeinde / nb_tests_param * 1000 + ";"
                  + durationstreecut / nb_tests_param * 1000 + ";" + durationstreemis / nb_tests_param * 1000 + ";"
                  + durationcycle / nb_tests_param * 1000 + ";" + durationcycleinde / nb_tests_param * 1000 + ";"
                  + durationcyclecut / nb_tests_param * 1000 + ";" + durationcyclemis / nb_tests_param * 1000 + ";");
        }

      }
    }
    myWriter.close();
  }
}
