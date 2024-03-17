# Appliquer l'arc sinus à une colonne
# Parametres: une colonne
# Retour: une colonne
import math
def asin(col) :
    y = [math.asin(x) for x in col]
    for x in y:
        print(x)
    return y