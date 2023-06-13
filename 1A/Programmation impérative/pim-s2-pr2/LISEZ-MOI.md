# Mini-Projet-2 (Session 2)

Nom    : Bouchama

Prénom : Ayoub

Groupe : F

## Consigne : 

Dans ce fichier, il faut répondre à la place des "..." et sur les lignes qui les suivent.  Le texte déjà écrit doit être conservé !

## Réponse aux questions du sujet

### Quelle est l'opération de la première version de la file qui n'est pas efficace et pourquoi ?

La première version du programme souffre d'une inefficacité au niveau de l'extraction d'un élément du tableau. En effet, après avoir extrait le premier élément du tableau, une boucle est utilisée pour décaler tous les autres éléments vers la gauche, afin de remplir l'espace vide laissé par l'élément extrait. Cependant, cette opération augmente considérablement la complexité de la fonction, surtout lorsque la taille du tableau est importante.

Pour être plus précis, cette opération de décalage implique de parcourir tous les éléments à partir de la position de l'élément extrait, ce qui prend du temps et ralentit l'exécution du programme. En outre, si le tableau est très grand, cette boucle de décalage peut prendre un temps considérable, ce qui peut devenir problématique pour les performances globales du programme.

Ainsi, pour améliorer l'efficacité de cette opération d'extraction, il convient d'adopter la deuxième version qui ne nécessite pas de décaler de tous les éléments du tableau. Mais qui consiste à utiliser une file circulaire avec un indice de début et un indice de fin pour éviter la boucle de décalage des éléments mais il suffit plutot d'incrémenter l'indice de début après extraction


### Pourquoi les indices Début et Fin ne peuvent pas être utilisés pour déterminer si une file est vide ou pleine.

Car les indices varient tout le temps, une fois il peuvent etre égaux, une autre l'indice du début peut etre supérieur à celui de la fin, et un parfois on peut avoir le contraire (Debut < Fin). Donc les indices de début et fin ne suivent pas une règle précise qui va nous permettre de determiner si une file est pleine ou vide.

### Que se passe-t-il si on veut ajouter un nouvel élément dans la file V2 et que l'indice de Fin est sur le dernier élément du tableau ?

Lorsqu'on souhaite ajouter un nouvel élément dans la file V2 et que l'indice de Fin est positionné sur le dernier élément du tableau, cela signifie que la file est potentiellement pleine. Dans ce cas, il est nécessaire de tester si la file est pleine. Dans le cas contraire, on ajoute le nouveau élément à l'occurence du premier indice du tableau et on modifie l'indice de fin vers l'indice 1.


### Pourquoi la version 2 de la file est plus efficace que la version 1 ?

L'utilisation d'une file circulaire (files-v2) simplifie l'extraction d'un élément de la file en évitant de décaler tous les éléments vers la gauche à chaque fois. Donc, cette version est plus optimale que la version v1.



## Difficultés

### Indiquer les difficultés rencontrées (s'il y en a eues).

Dans ma première implémentation de la procedure initialiser, j'avais cru qu'il fallait nécessairement initialiser les éléments d'un tableau avant de l'utiliser ce qui m'a causé un retard et des implémentations incorrectes. Ainsi, j'avais des erreurs de index check failed dans simulation_cpam. Mais, en reprenant le sujet dés le début, j'ai réussi à tout implémenter d'un seul coup.

## Remarques diverses

##### Cette partie est facultative. Elle vous permet de donner toute information que vous jugez utile.

