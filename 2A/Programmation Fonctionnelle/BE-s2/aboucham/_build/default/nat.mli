(* signature pour les entiers naturels *)

(* type des chiffres utilisés pour représenter un naturel *)
type digit

(* type des entiers naturels ; le poids faible doit être en tête *)
type t

(* affiche une représentation d'un chiffre passé en paramètre *)
val print_digit : digit -> unit

(* représent le nombre 0 *)
val zero : t

(* renvoie vrai si le nombre représenté est 0 *)
val is_zero : t -> bool

(* ajoute 1 à un entier *)
val inc : t -> t

(* décrémente un entier de 1 ; si l'entier représentait 0, renvoyer ce même nombre (ne pas lever d'exception) *)
val dec : t -> t

(* additionne deux entiers binaires *)
val add : t -> t -> t

(* renvoie l'int correspondant au naturel en paramètre *)
val to_int : t -> int

(* affiche un naturel tel qu'il est codé (p. ex. `Nat_bin.(print (inc (inc zero))))` pourrait afficher `[ 0 1 ]` si les naturels sont implementés via `Nat_bin`. *)
val print : t -> unit

(* construit un naturel à partir d'un int positif ou nul passé en paramètre *)
val of_int : int -> t
