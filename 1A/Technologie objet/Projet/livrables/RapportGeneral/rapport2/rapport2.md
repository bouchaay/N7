# Rapport de projet reg7 — itération 2

## Objectifs et fonctionnalités
Cette application a pour objectif de rendre les expressions régulières plus accessibles et d'en faciliter l'apprentissage grâce à une approche visuelle basée sur des blocs. Cette approche a été testée sur le terrain par des acteurs reconnus par l'Éducation Nationale, tels que Scratch. Cependant, contrairement à Scratch, nous ne négligeons pas la réalité des expressions régulières. Nous mettons également en avant la véritable expression régulière, ce qui permet à l'apprenant de développer ses compétences pour lire et écrire ses propres expressions simples à l'avenir, sans avoir besoin d'un logiciel tiers.

Cette application peut également être utile aux programmeurs expérimentés qui souhaitent comprendre une expression particulièrement complexe et la tester facilement.
## Vision Générale

Après avoir terminé la première version de l'interface graphique de notre logiciel reg7, qui permet de construire des expressions régulières, nous nous sommes concentrés sur divers aspects du côté back-end et front-end de l'application. Ces aspects comprennent la correction des bugs, la gestion des exceptions et l'implémentation des composants manquants dans l'interface utilisateur, tels que la barre de menu, l'ajout de l'icône et la fenêtre d'accueil et l'amélioration des composantes graphiques existantes.

## Travail réalisé

### Correction des bugs et gestion des exceptions

Dans un premier temps, nous avons consacré du temps à identifier et corriger les bugs présents dans le logiciel. Cela impliquait de passer en revue le code existant, de rechercher les erreurs et de les résoudre afin d'assurer le bon fonctionnement de l'application. Nous avons également effectué des tests approfondis pour vérifier que les corrections apportées ne créaient pas de nouveaux problèmes.

En parallèle, nous avons travaillé sur la gestion des exceptions. Nous avons examiné les différentes situations susceptibles de provoquer des erreurs dans le logiciel et avons mis en place des mécanismes pour les capturer et les gérer de manière appropriée. L'objectif était d'améliorer la robustesse de l'application en évitant les plantages et en fournissant des messages d'erreur clairs et informatifs aux utilisateurs en cas de problème.

### Fonctionnalités ajoutées au menu de construction

Nous avons pris des mesures pour améliorer l'interface utilisateur en identifiant les composants manquants et en les ajoutant. Tout d'abord, nous avons créé une barre de menu intuitive qui regroupe les fonctionnalités du logiciel, offrant aux utilisateurs un accès facile aux différentes options telles que l'importation, l'exportation et la copie. De plus, nous avons ajouté une icône distinctive pour le logiciel, ce qui facilite son identification et sa différenciation parmi les autres applications sur le bureau des utilisateurs.

Par ailleurs, nous avons apporté des améliorations spécifiques aux JTextPanes. Nous avons ajouté des placeholders attrayants pour rendre leur apparence plus élégante. De plus, nous avons inclus des JScrollPanes pour permettre le défilement du contenu des JTextPanes, assurant ainsi une meilleure expérience utilisateur lors de la manipulation des expressions régulières.

Une autre amélioration importante que nous avons apportée est la coloration syntaxique des expressions régulières insérées dans le JTextPane. Cette fonctionnalité associe la couleur de chaque composante de l'expression régulière avec celle du bouton correspondant, offrant ainsi une visualisation claire et compréhensible de l'expression analysée.

Ces ajouts et améliorations contribuent à une interface utilisateur plus conviviale et esthétiquement plaisante, améliorant ainsi l'expérience globale de l'utilisation du logiciel.

### Fenetre pour introduire les nouveaux utilisateurs aux expressions régulières

En plus de cela, nous avons également ajouté une fenêtre d'introduction aux expressions régulières pour les utilisateurs novices dans ce domaine. Cette fenêtre présente les différentes expressions régulières disponibles et les connaissances préalables nécessaires avant de commencer à utiliser le logiciel. Elle offre ainsi une présentation détaillée des concepts et des fonctionnalités clés liés aux expressions régulières.

### Première version de l'analyseur

Une première version de l'interface de l'analyseur a été créée, comprenant une barre de menu fonctionnelle, un JTextPane pour saisir l'expression régulière et un bouton de validation pour lancer l'analyse. L'objectif est de vérifier si l'expression régulière est syntaxiquement correcte. Si tel est le cas, elle sera décomposée en ses différentes composantes et chaque composante sera colorée différemment. De plus, une fenêtre s'affichera pour expliquer le rôle de chaque composante.
