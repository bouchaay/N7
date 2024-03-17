# Appliquer l'arc tangente à une colonne
# Parametres: une colonne
# Retour: une colonne
import math
def atan(col) :
    y = [math.atan(x) for x in col]
    for x in y:
        print(x)
    return y