# Retourner la colonne des moyennes de chaque ligne des colonnes
# parametres : colonnes
# retour : colonne
def avg(*colonne) :
    n = len(colonne)
    if n < 2 :
        raise ValueError("avg: au moins deux colonnes")
    else :
        y =  [sum(x)/n for x in zip(*colonne)]
    for x in y:
        print(x)
    return y