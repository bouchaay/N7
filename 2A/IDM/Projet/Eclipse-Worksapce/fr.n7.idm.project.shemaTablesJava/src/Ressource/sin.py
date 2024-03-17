# Appliquer le sinus à une colonne
# Parametres: une colonne
# Retour: une colonne
import math
def sin(col) :
    y = [math.sin(x) for x in col]
    for x in y:
        print(x)
    return y