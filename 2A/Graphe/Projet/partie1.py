# Dessine les graphes 2D non-pondérés
# Auteur : Ayoub Bouchama

import networkx as nx
import matplotlib.pyplot as plt
import mpl_toolkits.mplot3d as plt3d
from functions import return_graph_2D

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

######################################################################
######################## Afficher les graphes ########################
######################################################################
# ---------- Graphes low ---------- #
# Graphes low 20km
plt.figure(figsize=(10, 10))
plt.title('Graphe low 20km')
color_mapping = nx.coloring.greedy_color(Gl20, strategy="largest_first")
nx.draw(Gl20, node_color=[color_mapping[node] for node in Gl20.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)
# Graphes low 40km
plt.figure(figsize=(10, 10))
plt.title('Graphe low 40km')
color_mapping = nx.coloring.greedy_color(Gl40, strategy="largest_first")
nx.draw(Gl40, node_color=[color_mapping[node] for node in Gl40.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)
# Graphes low 60km
plt.figure(figsize=(10, 10))
plt.title('Graphe low 60km')
color_mapping = nx.coloring.greedy_color(Gl60, strategy="largest_first")
nx.draw(Gl60, node_color=[color_mapping[node] for node in Gl60.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)

# ---------- Graphes average ---------- #
# Graphes average 20km
plt.figure(figsize=(10, 10))
plt.title('Graphe average 20km')
color_mapping = nx.coloring.greedy_color(Ga20, strategy="largest_first")
nx.draw(Ga20, node_color=[color_mapping[node] for node in Ga20.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)
# Graphes average 40km
plt.figure(figsize=(10, 10))
plt.title('Graphe average 40km')
color_mapping = nx.coloring.greedy_color(Ga40, strategy="largest_first")
nx.draw(Ga40, node_color=[color_mapping[node] for node in Ga40.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)
# Graphes average 60km
plt.figure(figsize=(10, 10))
plt.title('Graphe average 60km')
color_mapping = nx.coloring.greedy_color(Ga60, strategy="largest_first")
nx.draw(Ga60, node_color=[color_mapping[node] for node in Ga60.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)

# ---------- Graphes high ---------- #
# Graphes high 20km
plt.figure(figsize=(10, 10))
plt.title('Graphe high 20km')
color_mapping = nx.coloring.greedy_color(Gh20, strategy="largest_first")
nx.draw(Gh20, node_color=[color_mapping[node] for node in Gh20.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)
# Graphes high 40km
plt.figure(figsize=(10, 10))
plt.title('Graphe high 40km')
color_mapping = nx.coloring.greedy_color(Gh40, strategy="largest_first")
nx.draw(Gh40, node_color=[color_mapping[node] for node in Gh40.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)
# Graphes high 60km
plt.figure(figsize=(10, 10))
plt.title('Graphe high 60km')
color_mapping = nx.coloring.greedy_color(Gh60, strategy="largest_first")
nx.draw(Gh60, node_color=[color_mapping[node] for node in Gh60.nodes()], cmap=plt.cm.rainbow, node_size=50, width=0.1)

######################################################################
######################## Afficher les graphes ########################
######################################################################
plt.show()