<img align="right" height="120" width="280" src="/img/image23.png">

## [M1 Informatique - UE Projet PSAR : Heuristiques pour la construction de MIS robustes](https://github.com/AliochaAMERGE/P_SAR_R-A.git "lien du projet")

Réalisé par :

- **Barthelemy ROBIN M1 SAR**
- **Aliocha AMERGÉ M1 SAR**

Lien du projet : https://github.com/AliochaAMERGE/P_SAR_R-A.git

Ce projet a été réalisé dans le cadre de l'UE PROJET SAR du master 1 Informatique mention SAR de *Sorbonne Université*.

## Rapport incomplet à ce jour

<div style="page-break-after: always;"></div>

## Table des matières 
TODO

# Introduction

&emsp;Les structures issues de la théorie des graphes (comme le coloriage, les arbres couvrants, les ensembles dominants, les couplages, etc.) sont d’un grand intérêt pour les systèmes répartis. Par exemple, un arbre couvrant permet de faire du routage tandis qu’un ensemble indépendant permet de choisir des têtes de cluster. Il n’est donc pas étonnant que la construction de ces structures dans des environnements répartis ait reçu une grande attention.
&emsp;Un des défis actuels de l’algorithmique répartie est de prendre en compte le dynamisme du système (c’est-à-dire la possibilité pour le graphe de communication d’évoluer dans le temps). Dans ce contexte, la définition même des structures n’est plus valable étant donné qu’elle considère un graphe statique et non dynamique. De plus, celle-ci peut dépendre du “niveau” de dynamisme du graphe.
&emsp;Parmi les différentes adaptations possibles de ces structures au monde dynamique, nous nous intéressons à la robustesse qui permet intuitivement d’obtenir une structure satisfaisante infiniment souvent la propriété de son analogue statique même dans un graphe très dynamique.
Ce projet est destiné à une étude expérimentale des performances d’un algorithme par force brute déterminant si un graphe donné en paramètre possède au moins un ensemble indépendant maximal (MIS) robuste.

# Présentation du projet

&emsp;Nous définirons tout d’abord les termes clés de ce projet :

### Maximal independant set ou MIS (Stable et Maximal)

&emsp;Un independant set (ou ensemble stable) est un ensemble de sommets d’un graphe dont chacun de ces sommets n’est pas adjacent au reste des sommets de cet ensemble. Ce dernier est maximal si, il n’est pas possible de rajouter un sommet du graphe à cet ensemble sans que ce dernier ne soit plus stable.
&emsp;Attention, il ne faut pas confondre avec un ensemble **maximum**, en effet, un ensemble stable maximum désigne l’ensemble stable contenant le plus de sommet possible. Un ensemble de sommets **maximum** est **maximal** mais pas inversement.
Nous présentons ci-dessous la différence entre ces deux termes, sur deux ensembles stables d’un même graphe (représenté par des points rouges). 
<center>
<img height="330" width="500" src="/img/image21.png">

Figure  1  : différence entre le terme maximal (à gauche) et maximum (à droite)
NB: le graphe de droite est également maximal 
</center>


### Robuste

&emsp;La propriété (ou structure) d’un graphe G est dite robustes si et seulement si, pour tout sous-graphes couvrant et connexe de G, ce dernier garde la propriété (ou structure) concernée.
Plus simplement, supposons qu’un graphe perde une arête, le graphe résultant (à condition qu’il soit connexe) devra également présenter la propriété étudiée. Dans notre cas, la maximalité et la stabilité de l’ensemble de sommet (MIS) étudié.

&emsp;Dans ce projet, nous nous intéressons à la robustesse d’un MIS sur un graphe, ainsi l’ensemble des sommets devra garder ses propriétés de MIS, à savoir, sa stabilité ainsi que sa maximalité.

### couvrant

&emsp;Un sous-graphe couvrant de G est un sous-graphe G’ tel que tous les sommets de G soient dans G’ .
 
### Connexe
 
 &emsp;Un graphe est dit connexe s’il existe un chemin entre tous les sommets de ce graphe.
 
&emsp;Ci-dessous un exemple de MIS robuste, et non robuste. (En rouge les sommets formant un MIS, nous retirons une arête arbitrairement de ce graphe, et observons si le MIS concerné est toujours maximal et stable )

<center>
<img height="330" width="400" src="/img/image14.png">

Figure 2 : exemple de la robustesse d’un MIS
</center>

### Pourquoi s'intéresser au graphe et pourquoi la robustesse ?
 
&emsp;En informatique, les structures issues de la théorie des graphes sont omniprésentes. Elles sont utilisées dans les réseaux de communication, les bases de données, les objets communicants, les jeux vidéo, et d'innombrables autres domaines. 
&emsp;Posons un exemple simple, un réseau entre différents systèmes, tout connecté entre eux. Il est possible qu’un système tombe en panne, et donc qu’une liaison disparaisse, comment pouvons-nous gérer cet incident ? Le réseau est-il toujours fonctionnel ? Quelle propriété subsistent ? C’est en répondant à ces questions que nous nous intéressons à la robustesse d’une propriété ou d’une structure. 
&emsp;De plus en plus, la concurrence est présente dans les systèmes, dans des systèmes de plus en plus critiques, et de plus en plus présent dans la vie de tous les jours, il est donc nécessaire de se pencher sur la dynamicité de ces réseaux, et de leur évolution dans le temps, c’est ce que nous faisons en étudiant la robustesse. 

&emsp;Ce projet porte donc sur l’étude de la ***robustesse*** d’un ***MIS*** d’un graphe. Les objectifs de ce dernier consistent tout d’abord à pouvoir détecter / générer un MIS concernant un graphe donné. Nous devons ensuite mettre en place un algorithme par force brute permettant de détecter la robustesse d’un MIS. Après la mise en place de cet algorithme, nous devons mettre en place un jeu de test pour vérifier la véracité de notre implémentation, ce jeu de test passe d’abord par la génération de graphe automatique suivant certaines propriétés. Notre objectif serait de repérer ces propriétés afin de pouvoir détecter en amont les graphes pouvant contenir un MIS robuste, afin de limiter au plus l’utilisation de l’algorithme par force brute, ayant une complexité très élevée, nous cherchons à trouver d’autre propriété caractéristique afin de réduire le temps d’exécution de notre algorithme et / ou de détecter la présence d’une RMIS plus tôt.
 
&emsp;La première partie de ce projet est donc plus technique (mise en place du projet et des différents algorithmes, génération de graphe etc…), alors que la deuxième partie sera plus de la recherche, et de l’optimisation des algorithmes précédemment implémenter.

# Algorithmes

### Objectifs

&emsp;L’objectif du projet est dans un premier temps de mettre en place un algorithme permettant, à partir d’un graphe donné de déduire si ce graphe possède un RMIS. Ensuite nous essaierons d’augmenter les performances de cet algorithme afin de trouver plus rapidement l'existence de ce RMIS. Nous ferons cela à l’aide de différentes heuristiques que nous expliquerons plus tard.

*L’ordre d'exécution “classique” des différents algorithmes :*
- Nous chargeons / générons un graphe.
- Si ce graphe est un Sputnik, un graphe Biparti Complet ou un Cycle pair, nous savons qu’il contient au moins un RMIS, nous sortons de l’algorithme.
- Si aucune détection de graphe n’est fructueuse, nous continuons avec l’exécution classique.

    Nous générons tous les MIS associés à ce graphe avec un appel à combinaison(). Pour chaque combinaison, nous faisons un appel à is_MIS() afin de savoir si ce dernier est stable. S’il l’est, nous vérifions sa robustesse avec un appel à isRobuste(), qui renvoie un booléen sur l’ensemble concerné. 

```
def main :

# input a graph G(V;E) or a path to a file
# output list of all robust MIS

begin
# we generate all MIS of G, and we put them in a list
mis_list = combination(G)
for each potentialMIS  in mis_list do
if potentialMIS  isIndependant then
        if potentialMIS  isMaximal then
            # our set is a MIS
              if potentialMIS  is Robust then
                # our set is a robust MIS
                    output RMIS found
output no RMIS found
end
```

*NB : le main sera représenté par la méthode combinaison() dans notre implémentation*


### Différents générateurs de graphes :

&emsp;Au cours du projet il s’est montré crucial de pouvoir tester les différents algorithmes sur plusieurs types de graphes afin de voir / prouver lesquels possèdent quelles propriétés. Ainsi que savoir quels graphes est le plus efficace pour une propriété donnée. Tous les graphes que nous utiliserons tout au long de ce projet seront connexes.
Un graphe est constitué d’une liste de nœuds, qui pour chaque nœud, détient sa liste de nœuds voisins.

- Graphe Sputnik

&emsp;Un graphe sputnik est un graphe très spécifique, où pour chaque nœud appartenant à un cycle au sein du graphe, possède un voisin (antenne) qui lui-même ne possède qu’un unique voisin.
&emsp;Dans un graph Sputnik, nous savons que tous ses MIS seront robustes, sachant que tout graphe non vide possède au moins un MIS, tout graphe Sputnik possède un MIS robuste.
&emsp;Le fait de savoir que ce genre de graphe possède forcément un RMIS nous donne un avantage que nous explorerons à l’aide des détecteurs de graphe, ce que nous aborderons dans la suite de ce rapport.

<center>
<img height="300" width="500" src="/img/image25.png">

Figure 3 a : Exemple de Sputnik de taille 20 généré par notre algorithme.
En rouge les nœuds appartenant à un cycle, en bleu leurs antennes.

<br>

<img height="300" width="500" src="/img/image12.png">

Figure 3 b : Exemple de RMIS sur le graphe Sputnik présenté ci-dessus
</center>

- Arbre

&emsp;Un arbre est un graphe construit tel que pour chaque couple de nœuds, il existe un unique chemin entre ces deux nœuds. Il ne possède donc aucun cycle, ce qui le fait entrer dans la catégorie des graphes Sputniks.

<center>
<img height="320" width="350" src="/img/image26.png">

Figure 4 : Exemple d’Arbre de taille 15 généré par notre algorithme
Les points colorés forment un exemple de RMIS
</center>

- Cycle

&emsp;Le cycle fut le premier graphe que nous avons implémenté étant le plus simple. Mais il s’est très vite révélé utile par d’autres aspects. En effet un graphe cyclique de taille pair possède à coup sur deux RMIS. A contrario, un graphe de taille impair n’en possède aucun. 
&emsp;Lors des différents tests que nous avons réalisés, nous remarquons qu’un cycle impair exécutera l’algorithme jusqu’au bout sans trouver de RMIS.

<center>
<img height="200" width="200" src="/img/image13.png">

Figure 5 : Exemple de Cycle de taille 6 généré par notre algorithme
NB : le cycle étant pair, il présente 2 RMIS indiqué en rouge et en bleu
</center>

- Graphe Biparti

&emsp;Un graphe biparti est un graphe dont l’ensemble des points peuvent être séparés en deux listes distinctes, une partie A et une partie B. Les nœuds de la partie A ne peuvent avoir comme voisins que les nœuds de la partie B et vice versa.
&emsp;Un graphes biparti complet reprend le même concept que le graphe biparti classique mais ici chacun des nœuds de la partie A possède la totalité des nœuds de la partie B comme voisins et chacun des nœuds de la partie B possède la totalité des nœuds de la partie A comme voisins.

