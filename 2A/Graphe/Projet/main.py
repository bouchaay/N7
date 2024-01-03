# main.py

import networkx as nx
import matplotlib.pyplot as plt
import mpl_toolkits.mplot3d as plt3d
from create_graph import create_graph

###################################################################
######################## Créer les graphes ########################
###################################################################

# ---------- Graphes low ---------- #
# Unweighted
create_graph('topology/topology_low.csv', 20, 'graphs/graph_low_20km.graphml')
create_graph('topology/topology_low.csv', 40, 'graphs/graph_low_40km.graphml')
create_graph('topology/topology_low.csv', 60, 'graphs/graph_low_60km.graphml')
# Weighted
create_graph('topology/topology_low.csv', 20, 'graphs/graph_low_20km_weighted.graphml', weighted=True)
create_graph('topology/topology_low.csv', 40, 'graphs/graph_low_40km_weighted.graphml', weighted=True)
create_graph('topology/topology_low.csv', 60, 'graphs/graph_low_60km_weighted.graphml', weighted=True)


# ---------- Graphes average ---------- #
# Unweighted
create_graph('topology/topology_avg.csv', 20, 'graphs/graph_avg_20km.graphml')
create_graph('topology/topology_avg.csv', 40, 'graphs/graph_avg_40km.graphml')
create_graph('topology/topology_avg.csv', 60, 'graphs/graph_avg_60km.graphml')
# Weighted
create_graph('topology/topology_avg.csv', 20, 'graphs/graph_avg_20km_weighted.graphml', weighted=True)
create_graph('topology/topology_avg.csv', 40, 'graphs/graph_avg_40km_weighted.graphml', weighted=True)
create_graph('topology/topology_avg.csv', 60, 'graphs/graph_avg_60km_weighted.graphml', weighted=True)

# ---------- Graphes high ---------- #
# Unweighted
create_graph('topology/topology_high.csv', 20, 'graphs/graph_high_20km.graphml')
create_graph('topology/topology_high.csv', 40, 'graphs/graph_high_40km.graphml')
create_graph('topology/topology_high.csv', 60, 'graphs/graph_high_60km.graphml')
# Weighted
create_graph('topology/topology_high.csv', 20, 'graphs/graph_high_20km_weighted.graphml', weighted=True)
create_graph('topology/topology_high.csv', 40, 'graphs/graph_high_40km_weighted.graphml', weighted=True)
create_graph('topology/topology_high.csv', 60, 'graphs/graph_high_60km_weighted.graphml', weighted=True)

##################################################################
######################## Lire les graphes ########################
##################################################################

# ---------- Graphes low ---------- #
# Unweighted
Gl20 = nx.read_graphml('graphs/graph_low_20km.graphml')
Gl40 = nx.read_graphml('graphs/graph_low_40km.graphml')
Gl60 = nx.read_graphml('graphs/graph_low_60km.graphml')
# Weighted
Gl20w = nx.read_graphml('graphs/graph_low_20km_weighted.graphml')
Gl40w = nx.read_graphml('graphs/graph_low_40km_weighted.graphml')
Gl60w = nx.read_graphml('graphs/graph_low_60km_weighted.graphml')

# ---------- Graphes average ---------- #
# Unweighted
Ga20 = nx.read_graphml('graphs/graph_avg_20km.graphml')
Ga40 = nx.read_graphml('graphs/graph_avg_40km.graphml')
Ga60 = nx.read_graphml('graphs/graph_avg_60km.graphml')
# Weighted
Ga20w = nx.read_graphml('graphs/graph_avg_20km_weighted.graphml')
Ga40w = nx.read_graphml('graphs/graph_avg_40km_weighted.graphml')
Ga60w = nx.read_graphml('graphs/graph_avg_60km_weighted.graphml')

# ---------- Graphes high ---------- #
# Unweighted
Gh20 = nx.read_graphml('graphs/graph_high_20km.graphml')
Gh40 = nx.read_graphml('graphs/graph_high_40km.graphml')
Gh60 = nx.read_graphml('graphs/graph_high_60km.graphml')
# Weighted
Gh20w = nx.read_graphml('graphs/graph_high_20km_weighted.graphml')
Gh40w = nx.read_graphml('graphs/graph_high_40km_weighted.graphml')
Gh60w = nx.read_graphml('graphs/graph_high_60km_weighted.graphml')

######################################################################
######################## Afficher les graphes ########################
######################################################################

# ---------- Graphes low ---------- #
# Graphes low 20km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe low 20km (unweighted)')
nx.draw(Gl20, with_labels=True, arrows=False)
# Graphes low 40km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe low 40km (unweighted)')
nx.draw(Gl40, with_labels=True, arrows=False)
# Graphes low 60km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe low 60km (unweighted)')
nx.draw(Gl60, with_labels=True, arrows=False)
# Graphes low 20km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe low 20km (weighted)')
nx.draw(Gl20w, with_labels=True, arrows=False)
# Graphes low 40km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe low 40km (weighted)')
nx.draw(Gl40w, with_labels=True, arrows=False)
# Graphes low 60km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe low 60km (weighted)')
nx.draw(Gl60w, with_labels=True, arrows=False)

# ---------- Graphes average ---------- #
# Graphes average 20km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe average 20km (unweighted)')
nx.draw(Ga20, with_labels=True, arrows=False)
# Graphes average 40km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe average 40km (unweighted)')
nx.draw(Ga40, with_labels=True, arrows=False)
# Graphes average 60km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe average 60km (unweighted)')
nx.draw(Ga60, with_labels=True, arrows=False)
# Graphes average 20km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe average 20km (weighted)')
nx.draw(Ga20w, with_labels=True, arrows=False)
# Graphes average 40km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe average 40km (weighted)')
nx.draw(Ga40w, with_labels=True, arrows=False)
# Graphes average 60km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe average 60km (weighted)')
nx.draw(Ga60w, with_labels=True, arrows=False)

# ---------- Graphes high ---------- #
# Graphes high 20km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe high 20km (unweighted)')
nx.draw(Gh20, with_labels=True, arrows=False)
# Graphes high 40km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe high 40km (unweighted)')
nx.draw(Gh40, with_labels=True, arrows=False)
# Graphes high 60km (unweighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe high 60km (unweighted)')
nx.draw(Gh60, with_labels=True, arrows=False)
# Graphes high 20km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe high 20km (weighted)')
nx.draw(Gh20w, with_labels=True, arrows=False)
# Graphes high 40km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe high 40km (weighted)')
nx.draw(Gh40w, with_labels=True, arrows=False)
# Graphes high 60km (weighted)
plt.figure(figsize=(10, 10))
plt.title('Graphe high 60km (weighted)')
nx.draw(Gh60w, with_labels=True, arrows=False)

######################################################################
######################## Afficher les graphes ########################
######################################################################
plt.show()





