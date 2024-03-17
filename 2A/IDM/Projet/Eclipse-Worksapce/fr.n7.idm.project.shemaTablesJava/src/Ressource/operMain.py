import sub 
import matplotlib.pyplot as plt
import numpy as np
from flask import Flask, request, jsonify

import sub 
import add
import avg
import acos
import asin
import atan
import div
import multiply
import sqrt
import sin
import cos
import tan

app = Flask(__name__)
@app.route('/operation', methods=['POST'])
def operation():
    
    # Récupérer les données POST depuis l'application Java
    data = request.get_json()
    print(data)
    
    
    colonne = data['Colonne']
    print(colonne)
    nameAlgo = data['nomAlgorithme']
    coLInt = striToInt(colonne)
    #coLInt = colonne
    
    if nameAlgo == 'sub':
        result = sub.sub(coLInt[0],coLInt[1])
    elif nameAlgo == 'add':
        result = add.add(coLInt)
    elif nameAlgo == 'avg':
        result = avg.avg(coLInt)
    elif nameAlgo == 'min':
        result = min(coLInt)
    elif nameAlgo == 'max':
        result = max(coLInt)
    elif nameAlgo == 'acos':
        result = acos(coLInt)
    elif nameAlgo == 'asin':
        result = asin(coLInt)
    elif nameAlgo == 'atan':
        result = atan(coLInt)
    elif nameAlgo == 'div':
        result = div(coLInt)
    elif nameAlgo == 'multiply':
        result = multiply(coLInt)
    elif nameAlgo == 'sqrt':
        result = sqrt(coLInt)
    elif nameAlgo == 'sin':
        result = sin(coLInt)
    elif nameAlgo == 'cos':
        result = cos(coLInt)
    elif nameAlgo == 'tan':
        result = tan(coLInt)
    else :
        result = coLInt
        
    
    # Retourner la liste des colonnes à l'application Java
    return jsonify(result)

def striToInt(liste):
    for i in range(len(liste)):
        for j in range(len(liste[i])):
            liste[i][j] = float(liste[i][j])
    return liste


if __name__ == '__main__':
    app.run(port=5001)
    