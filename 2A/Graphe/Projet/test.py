import functions as fun
import unittest
import networkx as nx

G_unweighted = 'graphs/graph_low_20km.graphml'
G_weighted = 'graphs/graph_low_20km_weighted.graphml'

G1 = nx.read_graphml(G_unweighted)
G2 = nx.read_graphml(G_weighted)

def test():
    
    degreeMoyen = fun.degree_avg(G1)
    print('Degree moyen de G1: ', degreeMoyen)
    fun.degree_distribution(G1)
    fun.clustering_distribution(G1)
    
    degreeMoyenValue = fun.degree_avg(G2, weighted=True)
    print('Degree moyen de G2: ', degreeMoyenValue)
    fun.degree_distribution(G2, weighted=True)
    fun.clustering_distribution(G2)
    
    
test()