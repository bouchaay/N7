# Appliquer l'arc cosinus à une colonne
# Parametres: une colonne
# Retour: une colonne
import math
def acos(col) :
    # Afficher chaque valeur dans une ligne
    for x in col:
        x = math.acos(x)
        print(x)
    return col