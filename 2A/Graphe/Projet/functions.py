# Description: This file contains all the functions used.
import pandas as pd
import networkx as nx
import matplotlib.pyplot as plt
import mpl_toolkits.mplot3d as plt3d

# La distance entre deux points dans l'espace
def distance(x1, y1, z1, x2, y2, z2):
    return (((x1 - x2)**2 + (y1 - y2)**2 + (z1 - z2)**2)**0.5)


# Vérifier si deux noeuds sont à portée
def a_la_portee(x1, y1, z1, x2, y2, z2, portee_km):
    return distance(x1, y1, z1, x2, y2, z2) <= portee_km


# Créer un graphe à partir d'un fichier CSV
def create_graph_2D(density_file, portee_km, output_graphml, weighted=False):

    # Lire le fichier CSV
    data = pd.read_csv(density_file)
    
    # Normaliser les coordonnées
    data.x = data.x / 1000
    data.y = data.y / 1000
    data.z = data.z / 1000

    # Créer un graphe dirigé
    G = nx.DiGraph()

    # Ajouter les nœuds avec les coordonnées comme attributs de position
    for row in data.itertuples():
        id = int(row.sat_id)
        position = [row.x, row.y, row.z]
        G.add_node(str(id), pos=str(position))

    # Ajouter les arêtes en fonction de la portée
    for i in range(len(data)):
        
        # Les coordonnées du noeud i
        xi, yi, zi = data.iloc[i].x, data.iloc[i].y, data.iloc[i].z
        
        # Parcourir les autres noeuds
        for j in range(i + 1, len(data)):
            
            # Les coordonnées du noeud j
            xj, yj, zj = data.iloc[j].x, data.iloc[j].y, data.iloc[j].z
            
            # Calculer la distance entre les deux noeuds
            dis = distance(xi, yi, zi, xj, yj, zj)

            # Ajouter une arête si les nœuds sont à portée
            if weighted:
                if a_la_portee(xi, yi, zi, xj, yj, zj, portee_km):
                    G.add_edge(int(data.iloc[i].sat_id), int(data.iloc[j].sat_id), weight=dis**2)
            else:
                G.add_edge(int(data.iloc[i].sat_id), int(data.iloc[j].sat_id), weight=1)

    # Écrire le graphe dans un fichier GraphML
    nx.write_graphml(G, output_graphml)


# Dessiner un graphe à partir d'un fichier CSV
def draw_graph_3D(density_file, portee_km, weighted=False):

    # Lire le fichier CSV
    data = pd.read_csv(density_file)
    
    # Normaliser les coordonnées
    data.x = data.x / 1000
    data.y = data.y / 1000
    data.z = data.z / 1000

    # Créer la figure 3D
    fig = plt.figure(figsize=(150, 150))
    ax = fig.add_subplot(111, projection='3d')
    
    # Ajouter les nœuds avec les coordonnées comme attributs de position
    for row in data.itertuples():
        id = int(row.sat_id)
        position = [row.x, row.y, row.z]
        
        # Afficher les noeuds
        ax.scatter(row.x, row.y, row.z, c='b', marker='o', label='Satellite')
        
        # Etiqueter les noeuds
        # ax.text(row.x, row.y, row.z, str(id))
        
    # Ajouter les arêtes en fonction de la portée
    for i in range(len(data)):
        
        # Les coordonnées du noeud i
        xi, yi, zi = data.iloc[i].x, data.iloc[i].y, data.iloc[i].z
        
        # Parcourir les autres noeuds
        for j in range(i + 1, len(data)):
            
            # Les coordonnées du noeud j
            xj, yj, zj = data.iloc[j].x, data.iloc[j].y, data.iloc[j].z

            # Lié les noeuds si ils sont à portée
            if weighted:
                if a_la_portee(xi, yi, zi, xj, yj, zj, portee_km):
                    ax.plot([xi, xj], [yi, yj], [zi, zj], c='g', linestyle='-', linewidth=0.5)
            else:
                ax.plot([xi, xj], [yi, yj], [zi, zj], c='g', linestyle='-', linewidth=0.5)

    # Calculer les limites des axes
    xmin, xmax = data.x.min() - 1, data.x.max() + 1
    ymin, ymax = data.y.min() - 1, data.y.max() + 1
    zmin, zmax = data.z.min() - 1, data.z.max() + 1
    
    # Définir les limites des axes
    ax.set_xlim(xmin, xmax)
    ax.set_ylim(ymin, ymax)
    ax.set_zlim(zmin, zmax)
    
    # Definir les labels
    ax.set_xlabel('X (km)')
    ax.set_ylabel('Y (km)')
    ax.set_zlabel('Z (km)')
    
    # Definir l'aspect du graphe
    ax.set_box_aspect([2, 3, 1])
    
    # Récupérer le nom du fichier
    filename = density_file.split('/')[-1].split('.')[0]
    
    # Le titre du graphe
    plt.title('densité = ' + filename + ', portée = ' + str(portee_km) + 'km' + (' (weighted)' if weighted else ''))

    # Afficher le graphe
    plt.show()


# Calcul du degree moyen
def degree_avg(G, weighted=False):
    if weighted:
        total_weight = 0
        for _, _, data in G.edges.data():
            total_weight += data['weight']
        return total_weight / G.number_of_nodes()
    else:
        return 2 * G.number_of_edges() / G.number_of_nodes()


# La distribution du degré dans un histogramme
def degree_distribution(G, weighted=False):
    degree_sequence = [d for n, d in G.degree(weight='weight' if weighted else None)]
    plt.hist(degree_sequence, bins=10)
    plt.title("Distribution du degré")
    plt.xlabel("degré")
    plt.ylabel("nombre de satellites")
    plt.show()


# La distribution moyenne du degré
def average_degree_distribution(G):
    degrees = dict(G.degree())
    average_degree = sum(degrees.values()) / len(degrees)
    return average_degree


# La distribution du degré de clustering
def clustering_distribution(G):
    clustering_sequence = [c for n, c in nx.clustering(G).items()]
    plt.hist(clustering_sequence, bins=10)
    plt.title("Distribution du degré de clustering")
    plt.xlabel("degré de clustering")
    plt.ylabel("nombre de satellites")
    plt.show()
