(* type : int -> 'a -> 'a list
  construit une liste constituee de n fois passé en param le meme element e passé en param
  paramètre : n le nombre de répitition et la taille de la liste, e l'élément à dupliquer
  précondition : n >= 0 
  renvoie : la liste de taille n contenant l'élément e*)
let rec l_taille n e = if n <= 0 then [] else e::(l_taille (n-1) e)

(* Tests : *)
let%test _ = l_taille 0 1 = []
let%test _ = l_taille 1 5 = [5]
let%test _ = l_taille 5 1 = [1; 1; 1; 1; 1]
let%test _ = l_taille (-1) 5 = []

(* L'exception ArgumentInvalide *)
exception ArgumentInvalide

(* get : int -> 'a list -> 'a *)
(* Renvoie le i-ième élément d'une liste *)
(* La tête de la liste est à la position 0 *)
(* Erreur : l'exception ArgumentInvalide est levée si i est négatif *)
(* ou plus grand que la taille de la liste *)
let get i liste = 
  let rec aux i liste = match liste with
    | [] -> raise ArgumentInvalide
    | t::q -> if i = 0 then t else aux (i-1) q
  in
  if i < 0 then raise ArgumentInvalide else aux i liste

(* Tests : *)

let%test _ = get 0 [ 5; 7; 3; 2 ] = 5
let%test _ = get 1 [ 5; 7; 3; 2 ] = 7
let%test _ = get 2 [ 5; 7; 3; 2 ] = 3
let%test _ = get 3 [ 5; 7; 3; 2 ] = 2
let%test _ = get 0 [ true; false; true ] = true
let%test _ = get 1 [ true; false; true ] = false
let%test _ = get 2 [ true; false; true ] = true

let%test _ =
  try
    let _ = get (-10) [] in
    false
  with ArgumentInvalide -> true

let%test _ =
  try
    let _ = get (-10) [ 1; 2; 3 ] in
    false
  with ArgumentInvalide -> true

let%test _ =
  try
    let _ = get (-10) [ 'a'; 'b'; 'c' ] in
    false
  with ArgumentInvalide -> true

let%test _ =
  try
    let _ = get 10 [] in
    false
  with ArgumentInvalide -> true

let%test _ =
  try
    let _ = get 10 [ 1; 2; 3 ] in
    false
  with ArgumentInvalide -> true

let%test _ =
  try
    let _ = get 10 [ 'a'; 'b'; 'c' ] in
    false
  with ArgumentInvalide -> true


(* set : int -> 'a list -> 'a -> 'a list *)
(* "Modifie" le i-ième élément d'une liste *)
(* La tête de la liste est à la position 0 *)
(* Pas de modification sur la liste s'il n'y a pas de i-ième élément*)
let set i liste elem = List.mapi (fun j x -> if j = i then elem else x) liste

(* Tests : *)

let%test _ = set 0 [ 5; 7; 3; 2 ] 10 = [ 10; 7; 3; 2 ]
let%test _ = set 1 [ 5; 7; 3; 2 ] 10 = [ 5; 10; 3; 2 ]
let%test _ = set 2 [ 5; 7; 3; 2 ] 10 = [ 5; 7; 10; 2 ]
let%test _ = set 3 [ 5; 7; 3; 2 ] 10 = [ 5; 7; 3; 10 ]
let%test _ = set 7 [ 5; 7; 3; 2 ] 10 = [ 5; 7; 3; 2 ]
let%test _ = set (-3) [ 5; 7; 3; 2 ] 10 = [ 5; 7; 3; 2 ]
let%test _ = set 0 [ true; false; true ] false = [ false; false; true ]
let%test _ = set 1 [ true; false; true ] false = [ true; false; true ]
let%test _ = set 2 [ true; false; true ] false = [ true; false; false ]
let%test _ = set 10 [ true; false; true ] false = [ true; false; true ]
let%test _ = set (-10) [ true; false; true ] false = [ true; false; true ]
