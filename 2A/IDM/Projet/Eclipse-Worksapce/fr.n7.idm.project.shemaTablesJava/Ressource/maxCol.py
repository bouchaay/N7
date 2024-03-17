# Retourner la colonne des maximums de chaque ligne des colonnes
# parametres : colonnes
# retour : colonne
def maxCol(*colonne) :
    n = len(colonne)
    if n < 2 :
        raise ValueError("max: au moins deux colonnes")
    else :
        y = [max(x) for x in zip(*colonne)]
    for x in y:
        print(x)
    return y