# Utilisation de l'abre préfixe pour le calcul du nombre d'occurence d'un mot dans un fichier

## Utilisation

1. Pour utiliser le programme, il faut d'abord compiler les fichiers 'files.adb' et 'arbre.adb' (ou compiler directement le fichier 'test_arbre.adb').

2. Le fichier 'test_arbre.adb' contient des tests du module Arbre, principalement des tests pour la fonction Ajouter qui insère un nœud dans l'arbre, la fonction Valeur qui retourne la valeur du nœud associée à une clé, et des affichages de l'arbre à chaque étape d'insertion pour observer les différences. Le test contient chaque affichage où l'on affiche les nœuds (arêtes, clés, valeur du nœud) de chaque niveau.

3. Ensuite, il faut compiler les fichiers 'CLI.adb' et 'main.adb' (ou utiliser 'gnatmake main.adb' pour compiler tous les fichiers).

4. Il faut ensuite disposer du fichier dans le même répertoire et lancer la commande :
./main &lt;mot&gt; &lt;fichier&gt;
où 'mot' est le mot à rechercher et 'fichier' est le nom du fichier de recherche.

5. Si le mot existe, le nombre d'occurence du mot va s'afficher, sinon le programme va signaler que ce mot n'existe pas.

## Réponse à la question 5

Je pense que la structure arborescente est très efficace dans les algorithmes de recherche grâce à sa capacité à organiser les données de manière hiérarchique. Elle permet une recherche rapide car elle réduit le nombre de comparaisons nécessaires entre nœuds à chaque étape. 

Cependant, je pense que la suppression d'une donnée dans l'arbre peut être complexe. car lorsque nous devons supprimer un nœud, nous devons également supprimer tous ses descendants (ses fils, leurs fils…). Cependant, si nous souhaitons conserver les descendants du nœud que nous supprimons, cela peut poser un problème. Il peut être difficile de gérer cette situation et de maintenir la structure de l'arbre cohérente tout en préservant les fils du nœud supprimé. Cela nécessite une manipulation délicate de l'arbre et peut potentiellement affecter les performances de l'opération de suppression.

J’ai pensé que pour remédier à ce problème on peut juste désactiver le nœud au lieu de le supprimer pour préserver le chemin de passage vers ses fils.

Une amélioration possible pour réduire le temps de recherche dans l'arbre préfixe est d'indexer les éléments de l'arbre. Plutôt que de parcourir tous les nœuds pour effectuer une recherche, on peut utiliser des indices associés à chaque nœud pour accéder directement aux fils correspondants. Cela permet d'obtenir rapidement la valeur associée au nœud sans avoir à parcourir l'ensemble de l'arbre. Par exemple, dans notre application de l'arbre préfixe, au lieu d'insérer les fils de chaque nœud de manière aléatoire, on peut utiliser le code de chaque bout de clé (arête) pour accéder directement aux fils appropriés, facilitant ainsi la récupération des valeurs des nœuds.
