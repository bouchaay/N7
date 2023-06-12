# Calculer les occurences d'un mot dans un texte contenu dans un fichier

## Utilisation
1. Pour utiliser le programme, il faut d'abord compiler les fichiers 'files.adb' et 'arbre.adb' (ou compiler directement le fichier 'test_arbre.adb').

2. Le fichier 'test_arbre.adb' contient des tests du module Arbre, principalement des tests pour la fonction Ajouter qui insère un nœud dans l'arbre, la fonction Valeur qui retourne la valeur du nœud associée à une clé, et des affichages de l'arbre à chaque étape d'insertion pour observer les différences. Le test contient chaque affichage où l'on affiche les nœuds (arêtes, clés, valeur du nœud) de chaque niveau.

3. Ensuite, il faut compiler les fichiers 'CLI.adb' et 'main.adb' (ou utiliser 'gnatmake main.adb' pour compiler tous les fichiers).

4. Il faut ensuite disposer du fichier dans le même répertoire et lancer la commande :
./main <mot> <fichier>
où 'mot' est le mot à rechercher et 'fichier' est le nom du fichier de recherche.