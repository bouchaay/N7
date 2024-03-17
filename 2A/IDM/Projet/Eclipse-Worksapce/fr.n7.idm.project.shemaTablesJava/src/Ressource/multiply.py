# Multiplier plusieurs colonnes entre elles.
# Parametres: deux colonnes ou plus
# Retour: une colonne
from functools import reduce
def multiply(colonne) :
    n = len(colonne)
    if n < 2 :
        raise ValueError("multiply: au moins deux colonnes")
    else :
        a = [reduce(lambda x,y: x*y, x) for x in zip(*colonne)]
    for x in a:
        print(x)
    return a