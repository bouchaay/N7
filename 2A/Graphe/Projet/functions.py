# Description: This file contains all the functions used.
import pandas as pd
import networkx as nx
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np

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
    G = nx.Graph()

    # Ajouter les nœuds avec les coordonnées comme attributs de position
    for row in data.itertuples():
        id = int(row.sat_id)
        G.add_node(str(id), pos=str((row.x, row.y, row.z)))

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
            if a_la_portee(xi, yi, zi, xj, yj, zj, portee_km):
                if weighted:
                    G.add_edge(int(data.iloc[i].sat_id), int(data.iloc[j].sat_id), weight=dis**2)
                else:
                    G.add_edge(int(data.iloc[i].sat_id), int(data.iloc[j].sat_id), weight=1)

    # Écrire le graphe dans un fichier GraphML
    nx.write_graphml(G, output_graphml)
    

# Retourner un graphe
def return_graph_2D(density_file, portee_km, weighted=False):

    # Lire le fichier CSV
    data = pd.read_csv(density_file)
    
    # Normaliser les coordonnées
    data.x = data.x / 1000
    data.y = data.y / 1000
    data.z = data.z / 1000

    # Créer un graphe dirigé
    G = nx.Graph()

    # Ajouter les nœuds avec les coordonnées comme attributs de position
    for row in data.itertuples():
        id = int(row.sat_id)
        G.add_node(id, pos=(row.x, row.y))

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
            if a_la_portee(xi, yi, zi, xj, yj, zj, portee_km):
                if weighted:
                    G.add_edge(int(data.iloc[i].sat_id), int(data.iloc[j].sat_id), weight=dis**2)
                else:
                    G.add_edge(int(data.iloc[i].sat_id), int(data.iloc[j].sat_id), weight=1)
                    
    return G

# Calculer le degé d'un noeud
def degree(G, node):
    degree = 0
    for edge in G.edges():
        if node in edge:
            degree += 1
    return degree

# Calculer le degé moyen d'un graphe
def degreeMoyen(G):
    somme = 0
    for node in G.nodes():
        somme += degree(G, node)
    return somme/len(G.nodes())

# afficher le degré moyen d'un graphe
def affiche_degreeMoyenne(G, nomegraph):
    m = degreeMoyen(G)
    print(f"Le degré moyen du graphe {nomegraph} est : {m}")

# Calculer la distribution des degrés d'un graphe
def degreeDistribution(G):
    distribution = []
    for node in G.nodes():
        distribution.append(degree(G, node))
    return distribution

# Afficher la distribution des degrés d'un graphe
def affiche_degreeDistribution(G, nomegraph):
    distribution = degreeDistribution(G)
    nodes = list(G.nodes())
    plt.figure(figsize=(10, 6))
    sns.histplot(distribution, color='skyblue', bins=20, kde=False)
    plt.xlabel("Degré", fontsize=12)
    plt.ylabel("Nombre de nœuds", fontsize=12)
    xlim = plt.xlim(1, max(distribution))
    plt.title(f"Distribution des degrés du graphe {nomegraph}", fontsize=14)
    plt.grid(True, linestyle='--', alpha=0.5)
    plt.show()

# Calculer le coefficient de clustering local d'un noeud
def coefficientClustering_local(graphe, noeud):
    voisins = list(graphe.neighbors(noeud))
    nombre_liens = 0
    for voisin1 in voisins:
        for voisin2 in voisins:
            if voisin1 != voisin2 and graphe.has_edge(voisin1, voisin2):
                nombre_liens += 1
    if len(voisins) > 1:
        coefficient_local = 2 * nombre_liens / (len(voisins) * (len(voisins) - 1))
        return coefficient_local
    else:
        return 0
    
# Calculer la moyenne du degré de clustering
def moyenClustering_global(graphe):
    somme_coefficients = 0
    nombre_noeuds = len(graphe)
    for noeud in graphe.nodes():
        coefficient_local = coefficientClustering_local(graphe, noeud)
        somme_coefficients += coefficient_local
    if nombre_noeuds > 2:
        coefficient_global = somme_coefficients / nombre_noeuds
        return coefficient_global
    else:
        return 0
    
# afficher moyenne du degré de clustering
def affiche_moyenClustering_global(G, nomegraph):
    m = moyenClustering_global(G)
    print(f"Le coefficient de clustering global du graphe {nomegraph} est : {m}")

# Distribution du degré de clustering
def distributionClustering(graphe):
    distribution = []
    for noeud in graphe.nodes():
        distribution.append(coefficientClustering_local(graphe, noeud))
    return distribution

# afficher Distribution du degré de clustering
def affiche_distributionClustering(G, nomegraph):
    distribution = distributionClustering(G)
    nodes = list(G.nodes())
    plt.figure(figsize=(10, 6))
    sns.histplot(distribution, color='skyblue', bins=20, kde=False)
    plt.xlabel("Coefficient de clustering", fontsize=12)
    plt.ylabel("Nombre de nœuds", fontsize=12)
    xlim = plt.xlim(0, max(distribution))
    plt.title(f"Distribution du coefficient de clustering du graphe {nomegraph}", fontsize=14)
    plt.grid(True, linestyle='--', alpha=0.5)
    plt.show()

# calculer l'ensemble des cliques d'un graphe avec leur ordre
def cliques(G):
    return list(nx.clique.enumerate_all_cliques(G))

# Afficher les cliques avec leurs ordres
def affiche_cliques(G, nomegraph):
    cliqueLis = cliques(G)
    for clique in cliqueLis:
        print(f"Clique du graphe {nomegraph} : {clique}, Ordre : {len(clique)}")
        

# Recherche en profondeur des composantes connexes
def rechercheComposanteConn12(graph, node, composanteConnexe, visited):
    if node not in visited:
        visited.add(node)
        composanteConnexe.append(node)
        for neighbor in graph.neighbors(node):
            rechercheComposanteConn12(graph, neighbor, composanteConnexe, visited)

# Calcul des composantes connexes
def composantesConnexes1(graph):
    composantesCon = [] 
    visited = set()
    for node in graph.nodes():
        if node not in visited:
            composanteCon = []
            rechercheComposanteConn12(graph, node, composanteCon, visited)
            composantesCon.append(composanteCon)
    return composantesCon

# afficher les composantes connexes
def affiche_composantes_connexes1(G, nomegraph):
    composantesConnexes = composantesConnexes1(G)
    print(f"Composantes connexes du graphe {nomegraph}")
    for composanteConnexe in composantesConnexes:
        print(f"Composante connexe : {composanteConnexe}, Ordre : {len(composanteConnexe)}")    
    print(f"Nombre de composantes connexes : {len(composantesConnexes)}")
    
    
#Longueur des chemins les plus courts en utilisant l'algorithme de Dijkstra        
def dijkstra(graph, source, destination):
    lengths, paths = nx.single_source_dijkstra(graph, source)

    # Vérifier si paths_destination est une liste ou un dictionnaire
    if destination not in lengths:
        shortest_path_length = float('inf')
        shortest_path = None
        shortest_path_count = None
    elif isinstance(paths[destination], list):
        shortest_path_length = lengths[destination]
        shortest_path = paths[destination]
        shortest_path_count = len(shortest_path) - 1
    else:
        shortest_path_length = lengths[destination]
        shortest_path = paths[destination]
        shortest_path_count = np.array([len(path) - 1 for path in shortest_path.values()])
    return shortest_path_length, shortest_path, shortest_path_count

# Tableau des longueurs des chemins les plus courts
def tableauLongueur(G):
    n = len(list(G.nodes()))
    tableau = np.zeros((n, n))
    for i in range(n):
        for j in range(n):
            Longueur, distribution, nbSaut = dijkstra(G, i, j)
            tableau[i][j] = Longueur
    return tableau

# Tableau des chemins les plus courts
def tableChemin(G):
    n = len(G.nodes())
    tableau = []
    for i in range(n):
        for j in range(i+1, n):
            Longueur, distribution, nbSaut = dijkstra(G, i, j)
            tableau.append(distribution)
    return tableau

# supprimer les chemins vides
def supprimerNoneChemins(tableau):
    for i in range(len(tableau)):
        if tableau[i] == None:
            tableau[i] = []
    return tableau

# afficher les chemins les plus courts
def affiche_tableChemin(G, nomegraph):
    tableau = supprimerNoneChemins(tableChemin(G))
    tableauL = tableauLongueur(G)
    for i in range(len(tableau)):
        if tableau[i] != []:
            print(f"Le chemin le plus court de {tableau[i][0]} a {tableau[i][-1]} est : ", end = ' ')
            for j in range(len(tableau[i])):
                print(f"{tableau[i][j]}" , end = ' ')
            print("-> Le nombre de saut est : ", len(tableau[i]) - 1)
            print("Longueur du chemin : ", tableauL[tableau[i][0]][tableau[i][-1]])
            print("---------------------------------------------")
    print("Le nombre de chemins est : ", len(tableau))
    
# Tableau des sauts
def tabeleSaut(G):
    n = len(G.nodes())
    tableau = np.zeros((n, n))
    for i in range(n):
        for j in range(n):
            Longueur, distribution, nbSaut = dijkstra(G, i, j)
            tableau[i][j] = nbSaut
    return tableau