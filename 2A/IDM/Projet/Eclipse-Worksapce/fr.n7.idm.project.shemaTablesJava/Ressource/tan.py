# Appliquer la tangente à une colonne
# Parametres: une colonne
# Retour: une colonne
import math
def tan(col) :
    y = [math.tan(x) for x in col]
    for x in y:
        print(x)
    return y