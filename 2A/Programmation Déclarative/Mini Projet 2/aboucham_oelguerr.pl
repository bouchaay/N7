% Auteurs : Ayoub Bouchama (L2), Oussama El Guerraoui (L2)

:- include(libtp2).

% --------------------------------------------------------------------------- %
%                          Solve v1 (Version 1)                               %
% --------------------------------------------------------------------------- %
% domaine([], [], C) vérifie les domaines des variables de décision.
domaine([], [], _).
domaine([T1|Q1], [T2|Q2], C) :-
    M is C - T2,
    fd_domain(T1, 0, M),
    domaine(Q1, Q2, C).

% chevauchement_des_paires([], [], [], T4, T5, T6) vérifie si les paires de carrés se chevauchent.
chevauchement_des_paires([], [], [], _, _, _).
chevauchement_des_paires([T1|Q1], [T2|Q2], [T3|Q3], T4, T5, T6) :-
    T5 + T6 #=< T2 #\/ T1 + T3 #=< T4 #\/ T2 + T3 #=< T5 #\/ T4 + T6 #=< T1,
    chevauchement_des_paires(Q1, Q2, Q3, T4, T5, T6).

% chevauchement([], [], []) vérifie si les carrés se chevauchent.
chevauchement([], [], []).
chevauchement([T1|Q1], [T2|Q2], [T3|Q3]) :-
    chevauchement_des_paires(Q1, Q2, Q3, T1, T2, T3),
    chevauchement(Q1, Q2, Q3).

% solve_v1(Num, Xs, Ys, B) résout l'instance Num et affecte les listes de variables de décision Xs et Ys et le nombre de backtracks B. (sans contraintes redondantes)
solve_v1(Num, Xs, Ys, B) :-
    data(Num, T, Ts), % Récupérer les données de l'instance Num
    length(Ts, Len),
    length(Xs, Len),
    length(Ys, Len),
    domaine(Xs, Ts, T), % Les domaines des variables dans les listes Xs et Ys
    domaine(Ys, Ts, T), % Les domaines des variables dans les listes Xs et Ys
    chevauchement(Xs, Ys, Ts), % Contraintes de non chevauchement
    fd_labeling(Xs, [backtracks(B)]), % Calculer la solution et les backtracks
    fd_labeling(Ys, [backtracks(B)]), % Calculer la solution et les backtracks
    printsol('tiles01.txt', Xs, Ys, Ts). % Écrire les résultats dans le fichier tiles01.txt

% --------------------------------------------------------------------------- %
%                          Solve v2 (Version 2)                               %
% --------------------------------------------------------------------------- %

% liste_multiple([], [], []) calcule le produit des éléments de deux listes.
liste_multiple([], [], []).
liste_multiple([T1|Q1], [T2|Q2], R) :-
    T #= T1 * T2,
    liste_multiple(Q1, Q2, R1),
    R = [T|R1].

% somme_elements([], 0) calcule la somme des éléments d'une liste.
somme_elements([], 0).
somme_elements([T|Q], S) :-
    somme_elements(Q, S1),
    S #= S1 + T.

% contraintes_redondantes_par_unite([], [], [], V) vérifie si les contraintes sont redondantes par unité.
contraintes_redondantes_par_unite([], [], [], _).
contraintes_redondantes_par_unite( [T1|Q1], [T2|Q2], [T3|Q3], V) :-
    T3 #<=> (T1 #=< V #/\ V #< T1 + T2),
    contraintes_redondantes_par_unite( Q1, Q2, Q3, V).

% contraintes_redondantes(Cap, Cap, _, _) vérifie si les contraintes sont redondantes.
contraintes_redondantes(Cap, Cap, _, _).
contraintes_redondantes(Cap, Ver, Xs, Ts) :-
    contraintes_redondantes_par_unite( Xs, Ts, B, Ver),
    liste_multiple(Ts, B, M),
    somme_elements(M, Cap),
    Next #= Ver + 1,
    contraintes_redondantes(Cap, Next, Xs, Ts).

%  solve_v2(Num, Xs, Ys, B) résout l'instance Num et affecte les listes de variables de décision Xs et Ys et le nombre de backtracks B. (avec contraintes redondantes)
solve_v2(Num, Xs, Ys, B) :- 
	data(Num, T, Ts),
	length(Ts, Len),
	length(Xs, Len),
	length(Ys, Len),
	domaine(Xs, Ts, T),
	domaine(Ys, Ts, T), 
    chevauchement(Xs, Ys, Ts), 
	contraintes_redondantes(T, 0, Xs, Ts), % Contraintes redondantes
	contraintes_redondantes(T, 0, Ys, Ts), % Contraintes redondantes
	fd_labeling(Xs, [backtracks(B)]),
	fd_labeling(Ys, [backtracks(B)]),
	printsol('tiles02.txt', Xs, Ys, Ts). % Écrire les résultats dans le fichier tiles02.txt

% Pour solve en version 1, B = 2
% Pour solve en version 2, B = 0
% Donc les contraintes redondantes ont permis de réduire le nombre de backtracks.

% --------------------------------------------------------------------------- %
%                          Solve v3 (Version 3)                               %
% --------------------------------------------------------------------------- %

%  solve_v3(Num, Xs, Ys, Goal, Criterion, B, NbSol) résout l'instance Num et affecte les listes de variables de décision Xs et Ys, le nombre de backtracks B et le nombre de solutions NbSol. (avec contraintes redondantes)
solve_v3(Num, Xs, Ys, Goal, Criterion, B, NbSol) :-
	data(Num, T, Ts),
	length(Ts, Len),
	length(Xs, Len),
	length(Ys, Len),
	domaine(Xs, Ts, T),
	domaine(Ys, Ts, T), 
    chevauchement(Xs, Ys, Ts), 
	contraintes_redondantes(T, 0, Xs, Ts), % Contraintes redondantes
	contraintes_redondantes(T, 0, Ys, Ts), % Contraintes redondantes
    labeling(Xs, Ys, Goal, Criterion, B, NbSol), % Stratégie de recherche
	printsol('tiles03.txt', Xs, Ys, Ts). % Écrire les résultats dans le fichier tiles02.txt

% Num = 1 & Goal : assign -> B = 0 / NbSol = 1
% Num = 1 & Goal : indomain -> B = 0 / NbSol = 1
% Num = 2 & Goal : assign -> B = 805 / NbSol = 1
% Num = 2 & Goal : indomain -> B = 9038 / NbSol = 1

% --------------------------------------------------------------------------- %
%                          Solve v4_1 (Version 4 per)                         %
% --------------------------------------------------------------------------- %
% Rupture de la symétrie de permutation entre les petits carrés de même taille.
rupture_symetrie_1([_], [_], [_]).
rupture_symetrie_1([T1, S1|Q1], [T2, S2|Q2], [T3, S3|Q3]) :-
    (S3 #\= T3) #\/ ((S2 #< T2 #\/ S1 #< T1) #/\ S1 #=< T1),
    rupture_symetrie_1([S1|Q1], [S2|Q2], [S3|Q3]).

% solve_v4_1 avec rupture de symétrie de permutation.
solve_v4_1(Num, Xs, Ys, Goal, Criterion, B, NbSol) :- 
	data(Num, T, Ts),
	length(Ts, Len),
	length(Xs, Len),
	length(Ys, Len),
	domaine(Xs, Ts, T),
	domaine(Ys, Ts, T),
    chevauchement(Xs, Ys, Ts),
	contraintes_redondantes(T, 0, Xs, Ts),
	contraintes_redondantes(T, 0, Ys, Ts),
    rupture_symetrie_1(Xs, Ys, Ts),
    labeling(Xs, Ys, Goal, Criterion, B, NbSol),
    printsol('tiles04_1.txt', Xs, Ys, Ts).

% Test : solve_v4_1(1, Xs, Ys, assign, minmin, B, NbSol).
% -------------------------------
% Instance 1:
% solve_v3 & Goal: assign -> B = 479 / NbSol = 480 
% solve_v4_1 & Goal: assign -> B = 9 / NbSol = 4
% -------------------------------
% Instance 2:
% solve_v4_1 & Goal: assign -> B = 29902 / NbSol = 10216

% --------------------------------------------------------------------------- %
%                          Solve v4_2 (Version 4 geo)                               %
% --------------------------------------------------------------------------- %
% Rupture des symétrie géométriques en restreignant la position du centre du plus grand carré dans le quart inférieur gauche.
rupture_symetrie_2(_, [], [], []).
rupture_symetrie_2(C, [T1|Q1], [T2|Q2], [T3|Q3]) :-
    L_2 is div(T3, 2),
    S_2 is div(C, 2),
    (T1 + L_2 #=< S_2 #/\ T2 + L_2 #=< S_2).

% solve_v4_2(Num, Xs, Ys, Goal, Criterion, B, NbSol) avec rupture de symétrie géométrique et de permutation.
solve_v4_2(Num, Xs, Ys, Goal, Criterion, B, NbSol) :- 
    data(Num, T, Ts),
    length(Ts, Len),
    length(Xs, Len),
    length(Ys, Len),
    domaine(Xs, Ts, T),
    domaine(Ys, Ts, T),
    rupture_symetrie_2(T, Xs, Ys, Ts), % Rupture de symétrie géométrique
    chevauchement(Xs, Ys, Ts), % Contraintes de non chevauchement
    contraintes_redondantes(T, 0, Xs, Ts),
	contraintes_redondantes(T, 0, Ys, Ts),
    rupture_symetrie_1(Xs, Ys, Ts), % Rupture de symétrie de permutation
    labeling(Xs, Ys, Goal, Criterion, B, NbSol),
    printsol('tiles04_2.txt', Xs, Ys, Ts).

% Test : solve_v4_2(1, Xs, Ys, assign, minmin, B, NbSol).
% -------------------------------
% Instance 1:
% solve_v4_2 & Goal: assign -> B = 2 / NbSol = 1
% -------------------------------
% Instance 2:
% solve_v4_2 & Goal: assign -> B = 8554 / NbSol = 2554
