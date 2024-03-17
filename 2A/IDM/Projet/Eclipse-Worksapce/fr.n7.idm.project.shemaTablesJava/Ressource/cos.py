# Appliquer le cosinus à une colonne
# Parametres: une colonne
# Retour: une colonne
import math
def cos(col) :
    y =  [math.cos(x) for x in col]
    for x in y:
        print(x)
    return y