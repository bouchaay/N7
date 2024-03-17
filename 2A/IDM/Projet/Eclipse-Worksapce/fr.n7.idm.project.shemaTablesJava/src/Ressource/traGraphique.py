import matplotlib.pyplot as plt
import numpy as np
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/tracer_graphique', methods=['POST'])
def tracer_graphique():
    # Récupérer les données POST depuis l'application Java
    data = request.get_json()

    # Extraire les listes x et y
    x = data['x']
    y = data['y']

    # Extraire la variable pour le type de graphique
    type_graphique = data.get('type_graphique', 0)

    if type_graphique == 1:
        # Tracer un histogramme
        plt.hist(x, bins='auto', alpha=0.7, rwidth=0.85)
        plt.xlabel('Axe X')
        plt.ylabel('Fréquence')
        plt.title('Histogramme tracé depuis Python')
    else:
        # Tracer le graphique
        plt.plot(x, y)
        plt.xlabel('Axe X')
        plt.ylabel('Axe Y')
        plt.title('Graphique tracé depuis Python')

    # Sauvegarder le graphique dans un fichier temporaire
    chemin_fichier = 'graphique_temp.png'
    plt.savefig(chemin_fichier)
    plt.close()

    # Retourner le chemin du fichier à l'application Java
    return jsonify({"chemin_fichier": chemin_fichier})

if __name__ == '__main__':
    app.run(port=5000)
