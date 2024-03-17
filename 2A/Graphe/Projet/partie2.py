from functions import *
import networkx as nx

##################################################################
######################## Lire les graphes ########################
##################################################################

# ---------- Graphes low ---------- #
Gl20 = return_graph_2D('topology/topology_low.csv', 20)
Gl40 = return_graph_2D('topology/topology_low.csv', 40)
Gl60 = return_graph_2D('topology/topology_low.csv', 60)


# ---------- Graphes average ---------- #
Ga20 = return_graph_2D('topology/topology_avg.csv', 20)
Ga40 = return_graph_2D('topology/topology_avg.csv', 40)
Ga60 = return_graph_2D('topology/topology_avg.csv', 60)


# ---------- Graphes high ---------- #
Gh20 = return_graph_2D('topology/topology_high.csv', 20)
Gh40 = return_graph_2D('topology/topology_high.csv', 40)
Gh60 = return_graph_2D('topology/topology_high.csv', 60)


###################################################################
########################## Calcul du degré moyen ##################
###################################################################
# ---------- Graphes low ---------- #
degGl20 = degreeMoyen(Gl20)
print("degGl20 = ", degGl20)
degGl40 = degreeMoyen(Gl40)
print("degGl40 = ", degGl40)
degGl60 = degreeMoyen(Gl60)
print("degGl60 = ", degGl60)

# ---------- Graphes average ---------- #
degGa20 = degreeMoyen(Ga20)
print("degGa20 = ", degGa20)
degGa40 = degreeMoyen(Ga40)
print("degGa40 = ", degGa40)
degGa60 = degreeMoyen(Ga60)
print("degGa60 = ", degGa60)

# ---------- Graphes high ---------- #
degGh20 = degreeMoyen(Gh20)
print("degGh20 = ", degGh20)
degGh40 = degreeMoyen(Gh40)
print("degGh40 = ", degGh40)
degGh60 = degreeMoyen(Gh60)
print("degGh60 = ", degGh60)

###################################################################
########################## Distribution du degré ##################
###################################################################
# ---------- Graphes low ---------- #
# affiche_degreeDistribution(Gl20, "Graph low 20km")
# affiche_degreeDistribution(Gl40, "Graph low 40km")
# affiche_degreeDistribution(Gl60, "Graph low 60km")

# ---------- Graphes average ---------- #
# affiche_degreeDistribution(Ga20, "Graph average 20km")
# affiche_degreeDistribution(Ga40, "Graph average 40km")
# affiche_degreeDistribution(Ga60, "Graph average 60km")

# ---------- Graphes high ---------- #
# affiche_degreeDistribution(Gh20, "Graph high 20km")
# affiche_degreeDistribution(Gh40, "Graph high 40km")
# affiche_degreeDistribution(Gh60, "Graph high 60km")

###################################################################
############Calcul du degré moyen de clustering ##################
###################################################################
# ---------- Graphes low ---------- #
affiche_moyenClustering_global(Gl20, "Graph low 20km")
affiche_moyenClustering_global(Gl40, "Graph low 40km")
affiche_moyenClustering_global(Gl60, "Graph low 60km")

# ---------- Graphes average ---------- #
affiche_moyenClustering_global(Ga20, "Graph average 20km")
affiche_moyenClustering_global(Ga40, "Graph average 40km")
affiche_moyenClustering_global(Ga60, "Graph average 60km")

# ---------- Graphes high ---------- #
affiche_moyenClustering_global(Gh20, "Graph high 20km")
affiche_moyenClustering_global(Gh40, "Graph high 40km")
affiche_moyenClustering_global(Gh60, "Graph high 60km")

###################################################################
################# Distribution du degré de clustering #############
###################################################################
# ---------- Graphes low ---------- #
# affiche_distributionClustering(Gl20, "Graph low 20km")
# affiche_distributionClustering(Gl40, "Graph low 40km")
# affiche_distributionClustering(Gl60, "Graph low 60km")

# ---------- Graphes average ---------- #
# affiche_distributionClustering(Ga20, "Graph average 20km")
# affiche_distributionClustering(Ga40, "Graph average 40km")
# affiche_distributionClustering(Ga60, "Graph average 60km")

# ---------- Graphes high ---------- #
# affiche_distributionClustering(Gh20, "Graph high 20km")
# affiche_distributionClustering(Gh40, "Graph high 40km")
# affiche_distributionClustering(Gh60, "Graph high 60km")

###################################################################
################# Nombre de cliques ###############################
###################################################################
# ---------- Graphes low ---------- #
# affiche_cliques(Gl20, "Graph low 20km")
# affiche_cliques(Gl40, "Graph low 40km")
# affiche_cliques(Gl60, "Graph low 60km")

# ---------- Graphes average ---------- #
# affiche_cliques(Ga20, "Graph average 20km")
# affiche_cliques(Ga40, "Graph average 40km")
# affiche_cliques(Ga60, "Graph average 60km")

# ---------- Graphes high ---------- #
# affiche_cliques(Gh20, "Graph high 20km")
# affiche_cliques(Gh40, "Graph high 40km")
# affiche_cliques(Gh60, "Graph high 60km")

###################################################################
################# Composantes connexes ############################
###################################################################
# ---------- Graphes low ---------- #
# affiche_composantes_connexes1(Gl20, "Graph low 20km")
# affiche_composantes_connexes1(Gl40, "Graph low 40km")
# affiche_composantes_connexes1(Gl60, "Graph low 60km")

# ---------- Graphes average ---------- #
# affiche_composantes_connexes1(Ga20, "Graph average 20km")
# affiche_composantes_connexes1(Ga40, "Graph average 40km")
# affiche_composantes_connexes1(Ga60, "Graph average 60km")

# ---------- Graphes high ---------- #
# affiche_composantes_connexes1(Gh20, "Graph high 20km")
# affiche_composantes_connexes1(Gh40, "Graph high 40km")
# affiche_composantes_connexes1(Gh60, "Graph high 60km")

###################################################################
################# Chemin le plus court ############################
###################################################################
# ---------- Graphes low ---------- #
affiche_tableChemin(Gl20, "Graph low 20km")
affiche_tableChemin(Gl40, "Graph low 40km")
affiche_tableChemin(Gl60, "Graph low 60km")

# ---------- Graphes average ---------- #
affiche_tableChemin(Ga20, "Graph average 20km")
affiche_tableChemin(Ga40, "Graph average 40km")
affiche_tableChemin(Ga60, "Graph average 60km")

# ---------- Graphes high ---------- #
affiche_tableChemin(Gh20, "Graph high 20km")
affiche_tableChemin(Gh40, "Graph high 40km")
affiche_tableChemin(Gh60, "Graph high 60km")




