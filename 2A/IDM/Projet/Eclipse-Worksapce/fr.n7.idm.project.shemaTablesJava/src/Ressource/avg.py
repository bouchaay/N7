# Retourner la colonne des moyennes de chaque ligne des colonnes
# parametres : colonnes
# retour : colonne

def avg(colonne) :
    n = len(colonne)
    if n < 2 :
        return colonne
    else :
        y =  [sum(x)/n for x in zip(*colonne)]
    for x in y:
        print(x)
    return y

