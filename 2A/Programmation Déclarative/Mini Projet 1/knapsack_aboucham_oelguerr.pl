/****************************************************/
/*   Auteurs : Ayoub Bouchama, Oussama Elguerraoui  */
/****************************************************/

/****************************************************/
/*         Defintion du prédicat poids/2            */
/****************************************************/
poids(morceau_du_pont_Saint_Pierre, 10).
poids(cachous, 1).
poids(stock_de_ballons_de_rugueubi, 7).
poids(goodies_Airbus_et_Cité_de_l_Espace, 6).
poids(ordinateur_salle_TP_N7, 10).

/****************************************************/
/*         Defintion du prédicat poids_sac/2        */
/****************************************************/
poids_sac([], 0).
poids_sac([Objet|Liste], Poids) :- poids(Objet, PoidsObjet), poids_sac(Liste, PoidsListe), Poids is PoidsObjet + PoidsListe.

/****************************************************/
/*         Defintion du prédicat sous_liste/2       */
/****************************************************/
sous_liste([], []).
sous_liste([T|Q1], [T|Q2]) :- sous_liste(Q1, Q2).
sous_liste([_|Q1], [T|Q2]) :- sous_liste(Q1,[T|Q2]).

/****************************************************/
/*         Defintion du prédicat acceptable/2       */
/****************************************************/
acceptable(L, S) :- sous_liste(L, S), poids_sac(S, Poids), Poids =< 20.

/****************************************************/
/*    Defintion du prédicat meilleur_poids/2 V1     */
/****************************************************/
meilleur_poids_1(L, S1) :- acceptable(L, S1), poids_sac(S1, P1), \+ (acceptable(L, S2), poids_sac(S2, P2), P2 > P1).

/****************************************************/
/*     Defintion du prédicat utilite_meilleur/2     */
/****************************************************/
/* Prédicats utilites */
utilite(morceau_du_pont_Saint_Pierre, 1).
utilite(cachous, 2).
utilite(stock_de_ballons_de_rugueubi, 1).
utilite(goodies_Airbus_et_Cité_de_l_Espace, 3).
utilite(ordinateur_salle_TP_N7, 2).
/* Prédicat utilite_sac */
utilite_sac([], 0).
utilite_sac([Objet|Liste], Utilite) :- utilite(Objet, UtiliteObjet), utilite_sac(Liste, UtiliteListe), Utilite is UtiliteObjet + UtiliteListe.
/* Prédicat utilite_meilleur */
utilite_meilleur(L, S1) :- acceptable(L, S1), utilite_sac(S1, U1), \+ (acceptable(L, S2), utilite_sac(S2, U2), U2 > U1).

/****************************************************/
/*    Defintion du prédicat meilleur_poids/2 V2     */
/****************************************************/
/* a- Inefficacité du prédicat meilleur_poids/2 V1 : L'utilisation de la négation par echec entraine des boucles de recherches inutiles */
/* b- Le prédicat 'p' génère toutes les combinaisons possibles de sous-listes de R, et teste si elles sont acceptables par 'q'. */
/*  - Le prédicat 'q' est le prédicateur de 'p' qui permet de filtrer les sous-liste de R générées par 'p' selon des critères. */
q([],_,_) :- fail.
q([X|_],[],[X]).
q([X|RR],[X|RC],S) :- q(RR,RC,S).
q([X|_],[Y|RC],S) :- X \= Y, S = [X,Y|RC].
p(R,C,[C]) :- \+ (q(R,C,_)).
p(R,C,[C|V]) :- q(R,C,S), p(R,S,V).
/* Poids maximal */
p1_sup_p2(S1, S2) :- poids_sac(S1, P1), acceptable(L, S2), poids_sac(S2, P2), acceptable(L, S1), P1 > P2.
poids_max(L, S, [H|T]) :- p1_sup_p2(S, H), poids_max(L, S, T).
/* Meilleur poids */
meilleur_poids_2(L, S) :- p(L, [], X), poids_max(L, S, X).
