(* combinaison : int -> int list -> int list list
  k combinaison d'une liste l qui contient des nombres 
  parametre : k : le nombre et l : la liste de nombre
  resultat : une liste de liste qui contient les combinaisons de k elements de l
*)

(* Algorithme *)
let rec combinaison k l = 
  match k, l with
  | 0, _ -> [[]]
  | _, [] -> failwith "La liste est vide"
  | k, t::q -> (List.map (fun x -> t::x) (combinaison (k-1) q)) @ (combinaison k q);;

(* Tests *)
let%test _ = combinaison 0 [1;2;3;4] = [[]]
let%test _ = combinaison 1 [1;2;3;4] = [[1]; [2]; [3]; [4]]
let%test _ = combinaison 2 [1;2;3;4] = [[1; 2]; [1; 3]; [1; 4]; [2; 3]; [2; 4]; [3; 4]]
let%test _ = combinaison 3 [1;2;3;4] = [[1; 2; 3]; [1; 2; 4]; [1; 3; 4]; [2; 3; 4]]
let%test _ = combinaison 4 [1;2;3;4] = [[1; 2; 3; 4]]


