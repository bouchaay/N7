import sys
import json

def max_of_lists(input_list):
    # Vérifier si la liste d'entrée est vide
    if not input_list:
        return []

    # Transposer la liste d'entrée pour obtenir les colonnes au lieu des lignes
    transposed_list = list(map(list, zip(*input_list)))

    # Appliquer la fonction max à chaque colonne
    result_list = [max(column) for column in transposed_list]

    # Transposer à nouveau pour revenir à la forme initiale
    result_list = [result_list]

    return list(map(list, zip(*result_list)))

# Lire la liste d'entrée depuis l'entrée standard
input_data = json.load(sys.stdin)

# Appeler la fonction
output_data = max_of_lists(input_data)

# Imprimer la liste de sortie sur la sortie standard
print(json.dumps(output_data))
