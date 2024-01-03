(*Contrat exercie 1
Fonction qui prend en paramètre un ordre, un élément et une liste trié pour mettre l'élément dans la liste 0
Type : ('a -> 'a -> bool) -> 'a -> 'a list -> 'a list
Paramètre: ordre ('a -> 'a -> bool), élément ('a) et liste ('a list)
Précondition: la liste est trié
Résultat: une liste trié contenant le nouvel élément
*)

(* Algorithme *)
let rec tri_ex_1 ordre element liste = match liste with
  |[] -> [element]
  |t::q -> if ordre element t then element::liste else t::(tri_ex_1 ordre element q);;

(* Tests *)
let%test _ = tri_ex_1 (fun x y -> x < y) 1 [] = [1];;
let%test _ = tri_ex_1 (fun x y -> x < y) 1 [2] = [1;2];;
let%test _ = tri_ex_1 (fun x y -> x < y) 1 [1] = [1;1];;
let%test _ = tri_ex_1 (fun x y -> x < y) 5 [1;2;3;4;6;8] = [1;2;3;4;5;6;8];;
let%test _ = tri_ex_1 (fun x y -> x > y) 1 [] = [1];;
let%test _ = tri_ex_1 (fun x y -> x > y) 1 [2] = [2;1];;
let%test _ = tri_ex_1 (fun x y -> x > y) 1 [1] = [1;1];;
let%test _ = tri_ex_1 (fun x y -> x > y) 5 [1;2;3;4;6;8] = [8;6;5;4;3;2;1];;

(* ------------------------------------------------------------------------- *)

(*Contrat exercie 2
Fonction qui prend en paramètre un ordre et une liste et tri la liste selon cette ordre
Type : ('a -> 'a -> bool) -> 'a list -> 'a list
Paramètre: ordre ('a -> 'a -> bool) et liste ('a list)
Résultat: une liste trié
*)

(* Algorithme *)
let rec tri_ex_2 ordre liste =  List.fold_left (fun acc x -> tri_ex_1 ordre x acc) [] liste;;

(* Tests *)
let%test _ = tri_ex_2 (fun x y -> x < y) [] = [];;
let%test _ = tri_ex_2 (fun x y -> x < y) [1] = [1];;
let%test _ = tri_ex_2 (fun x y -> x < y) [9;5;4;3;8;9;5;2] = [2;3;4;5;5;8;9;9];;
let%test _ = tri_ex_2 (fun x y -> x > y) [] = [];;
let%test _ = tri_ex_2 (fun x y -> x > y) [1] = [1];;
let%test _ = tri_ex_2 (fun x y -> x > y) [9;5;4;3;8;9;5;2] = [9;9;8;5;5;4;3;2];;
