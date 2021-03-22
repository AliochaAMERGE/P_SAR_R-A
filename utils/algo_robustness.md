# Pseudo code de l'algorithme de detection de la robustesse d'un MIS

    Lemma 3.
    For  any  (static)  graph g and  any  minimal  dominating  set M of g, M is  a  strong  minimal  dominating  set  of g if  and  only  if  the  set  of  edges { { p,q } | q ∈ M ∩ N p } is a cut-set in g for every process p ∈ V \ M.
        _source_ : https://pages.lip6.fr/Swan.Dubois/pdf/CR14.pdf



```py
def RMIS_detector : 
    #Input : a graph G
    #Output : boolean (is our graph robust)

    #independant set of points of our graph
    stable_set = G.getStable()

    #remaining points (not in stable_set)
    remaining_points = g.getRemainingPoints(stable_set)



    for each point in remaining_points:
        for each neighboor in point.getNeighboor():
            if stable_set.contains(neighboor):
                strong_neighboors.add(neighboor)
        # create a new graph without the arc between the point and every independant neighboors
        # pas clair ici si on enleve 1 arc ou tous
        graph_temp = new Graph_without{arc(point, strong_neighboors)}

        # Si le graph n'est plus connexe ( coupure )
        if not graph_temp.isConnected():
            return false
        # 
        # else if not graph_temp.isStable():
        #     return false
        else :
            continue
    return true

def isConnected():

    unvisited_neighbor = empty_list

    visited_neighbor = empty_list

    visited_neighbor.add(random_neighbor)

    for each nei in random_neighbor.getNeighboor:
        unvisited_neighbor.add(nei)

    while(not unvisited_neighbor.isEmpty()):
        for each nei in unvisited_neighbor.getNeighboor():
            if not visited_neighbor.contains(nei):
                unvisited_neighbor.add(nei)
                visited_neighbor.add(nei)


```
