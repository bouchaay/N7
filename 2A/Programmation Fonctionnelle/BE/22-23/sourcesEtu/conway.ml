(* Exercice 1*)

(* max : int list -> int  *)
(* Paramètre : liste dont on cherche le maximum *)
(* Précondition : la liste n'est pas vide *)
(* Résultat :  l'élément le plus grand de la liste *)
let rec max liste =
  match liste with
    | [] -> failwith "La liste est vide"
    | [x] -> x
    | x::_ -> list.fold_left (fun acc x -> if x > acc then x else acc) x liste

(* TO DO : copier / coller les tests depuis conwayTests.txt *)

(* max_max : int list list -> int  *)
(* Paramètre : la liste de listes dont on cherche le maximum *)
(* Précondition : il y a au moins un élement dans une des listes *)
(* Résultat :  l'élément le plus grand de la liste *)
let max_max liste = max (list.flatten liste)

(* TO DO : copier / coller les tests depuis conwayTests.txt *)

(* suivant : int list -> int list *)
(* Calcule le terme suivant dans une suite de Conway *)
(* Paramètre : le terme dont on cherche le suivant *)
(* Précondition : paramètre différent de la liste vide *)
(* Retour : le terme suivant *)

(* TO DO : copier / coller les tests depuis conwayTests.txt *)


(* Exercice 2*)

(* suivant : int list -> int list *)
(* Calcule le terme suivant dans une suite de Conway *)
(* Paramètre : le terme dont on cherche le suivant *)
(* Précondition : paramètre différent de la liste vide *)
(* Retour : le terme suivant *)

let rec suivant liste =
  match liste with
    | [] -> failwith "La liste est vide"
    | [x] -> [1;x]
    | x::y::reste -> 
      let liste_inter occurence l = 
        match l with
          | [] -> []
          | [x] -> [occurence;x]
          | x::y::reste -> if x = y then liste_inter (occurence+1) (y::reste) else occurence::x::liste_inter 1 (y::reste)
      in liste_inter 1 liste


(* TO DO : copier / coller les tests depuis conwayTests.txt *)

(* suite : int -> int list -> int list list *)
(* Calcule la suite de Conway *)
(* Paramètre taille : le nombre de termes de la suite que l'on veut calculer *)
(* Paramètre depart : le terme de départ de la suite de Conway *)
(* Résultat : la suite de Conway *)

(* TO DO : copier / coller les tests depuis conwayTests.txt *)

(* suite : int -> int list -> int list list *)
(* Calcule la suite de Conway *)
(* Paramètre taille : le nombre de termes de la suite que l'on veut calculer *)
(* Paramètre depart : le terme de départ de la suite de Conway *)
(* Résultat : la suite de Conway *)
let rec suite taille liste_depart =
  match taille with
    | 0 -> []
    | _ -> liste_depart::suite (taille-1) (suivant liste_depart)

(* TO DO : copier / coller les tests depuis conwayTests.txt *)

(* Tests de la conjecture *)
(* "Aucun terme de la suite, démarant à 1, ne comporte un chiffre supérieur à 3" *)
(* TO DO *)
(* Remarque : TO DO *)