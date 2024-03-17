from functions import *
import networkx as nx

##################################################################
######################## Lire les graphes ########################
##################################################################

# ---------- Graphes low ---------- #
Gl60 = return_graph_2D('topology/topology_low.csv', 60, weighted=True)

# ---------- Graphes average ---------- #
Ga60 = return_graph_2D('topology/topology_avg.csv', 60, weighted=True)

# ---------- Graphes high ---------- #
Gh60 = return_graph_2D('topology/topology_high.csv', 60, weighted=True)

###################################################################
################# Chemin le plus court ############################
###################################################################
# ---------- Graphes low ---------- #
affiche_tableChemin(Gl60, "Graph low 60km (weighted)")

# ---------- Graphes average ---------- #
affiche_tableChemin(Ga60, "Graph average 60km (weighted)")

# ---------- Graphes high ---------- #
affiche_tableChemin(Gh60, "Graph high 60km (weighted)")