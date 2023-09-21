(*  pgcd : renvoie le pgcd de deux entiers positifs
    type : int -> int -> int
    pre : a <> 0 ou b <> 0
    param : a et b sont des entiers positifs
    retour : l'entier positif qui est le pgcd de a et b'
*)

(* Fonction pgcd *)
let rec pgcd a b =
  let valeur_absolue x = if x < 0 then -x else x in
    if valeur_absolue a = valeur_absolue b then a
    else if a = 0 && b <> 0 then valeur_absolue b
    else if a <> 0 && b = 0 then valeur_absolue a
    else if a > b then pgcd (a-b) b
    else if a < b then pgcd a (b-a)
    else failwith "Erreur : les deux nombres sont nuls";;

(* Tests *)
let%test _ = pgcd 5 5 = 5;;
let%test _ = pgcd 0 5 = 5;;
let%test _ = pgcd 5 0 = 5;;
let%test _ = pgcd -2 5 = 1;;
let%test _ = pgcd -12 -8 = 4;;
let%test _ = pgcd 12 -8 = 4;;
let%test _ = pgcd 1 5 = 1;;
let%test _ = pgcd 1 1 = 1;;