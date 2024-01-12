# Dessin des graphes 3D
# Auteur : Ayoub Bouchama

from functions import draw_graph_3D

######################################################################
######################## Dessiner les graphes ########################
######################################################################

# ---------- Graphes low ---------- #
# Unweighted
draw_graph_3D('topology/topology_low.csv', 20)
draw_graph_3D('topology/topology_low.csv', 40)
draw_graph_3D('topology/topology_low.csv', 60)
# Weighted
draw_graph_3D('topology/topology_low.csv', 20, weighted=True)
draw_graph_3D('topology/topology_low.csv', 40, weighted=True)
draw_graph_3D('topology/topology_low.csv', 60, weighted=True)

# ---------- Graphes average ---------- #
# Unweighted
draw_graph_3D('topology/topology_avg.csv', 20)
draw_graph_3D('topology/topology_avg.csv', 40)
draw_graph_3D('topology/topology_avg.csv', 60)
# Weighted
draw_graph_3D('topology/topology_avg.csv', 20, weighted=True)
draw_graph_3D('topology/topology_avg.csv', 40, weighted=True)
draw_graph_3D('topology/topology_avg.csv', 60, weighted=True)

# ---------- Graphes high ---------- #
# Unweighted
draw_graph_3D('topology/topology_high.csv', 20)
draw_graph_3D('topology/topology_high.csv', 40)
draw_graph_3D('topology/topology_high.csv', 60)
# Weighted
draw_graph_3D('topology/topology_high.csv', 20, weighted=True)
draw_graph_3D('topology/topology_high.csv', 40, weighted=True)
draw_graph_3D('topology/topology_high.csv', 60, weighted=True)

##################################################################

