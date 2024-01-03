open Util
open Mem

(* get_assoc: int -> (int*'a) list -> 'a -> 'a
fonction qui renvoie la valeur associée à une clef dans la liste ou une valeur par defaut si la clef n'existe pas
parametre e : int, la clef de la donnée dans la liste
parametre l : (int * 'a) list, une liste representant la memoire 
parametre def : 'a, une valeur par defaut si la clef n existe pas
precondition : e >= 0
resultat : la valeur associée à la clef e ou def si e n exsite pas dans la liste
 *)
(*let rec get_assoc e l def = match List.filter (fun a -> fst(a) = e) l with
    | [] -> def
    | t::_ -> snd(t)*)

let rec get_assoc e l def = match l with
    | t::_ when fst(t) = e  -> if (snd(t) = _0) then def else snd(t)
    | _::q -> get_assoc e q def
    | [] -> raise OutOfBound

(* Tests unitaires : TODO *)
let%test _ = get_assoc 1 [(1, 'b'); (5, 'c')] 'a' = 'c' 

(* set_assoc : TODO
 *)
let rec set_assoc e l x = match l with
    | t::q when fst(t) = e -> (e, x)::q
    | t::q -> t::(set_assoc e q x)
    | [] -> raise OutOfBound
    

(* Tests unitaires : TODO *)


module AssocMemory : Memory =
struct
    (* Type = liste qui associe des adresses (entiers) à des valeurs (caractères) *)
    type mem_type = (int * char) list

    (* Un type qui contient la mémoire + la taille de son bus d'adressage *)
    type mem = int * mem_type

    (* Nom de l'implémentation *)
    let name = "assoc"

    (* Taille du bus d'adressage *)
    let bussize (bs, _) = bs

    (* Taille maximale de la mémoire *)
    let size (bs, _) = pow2 bs

    (* Taille de la mémoire en mémoire *)
    let allocsize (bs, m) =  2 * List.length m

    (* Nombre de cases utilisées *)
    let busyness (bs, m) = 
        List.fold_left (fun acc (_, v) -> if v = _0 then acc else acc + 1) 0 m

    (* Construire une mémoire vide *)
    let clear bs = 
        let rec clear_aux n = 
            if ( n <= 0 ) then [(0, _0)]
            else (clear_aux (n-1))@[(n, _0)]
        in
        let m = clear_aux ((pow2 bs)-1) in (bs, m)
       
    (* Lire une valeur *)
    let read (bs, m) addr = get_assoc addr m _0

    (* Écrire une valeur *)
    let write (bs, m) addr x = bs, (set_assoc addr m x)
end
