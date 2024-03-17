# Appliquer la racine carrée à une colonne
# Parametres: une colonne
# Retour: une colonne
import math
def sqrt(col) :
    y = [math.sqrt(x) for x in col]
    for x in y:
        print(x)
    return y