  module M (* : Ral_intf.S *) = struct
  type 'a tree = Leaf of 'a | Node of 'a tree * 'a tree
  type 'a digit = Zero | One of 'a tree
  type 'a t = 'a digit list

  exception Empty

  let empty = []

  (* à définir : cons, head, tail et get *)

  let rec cons : 'a -> 'a t -> 'a t = fun x ral ->
    match ral with
    | [] -> [One (Leaf x)]
    | Zero :: digits -> One (Leaf x) :: digits
    | One tree :: digits -> Zero :: insert_tree x tree digits
  
  (* Insère un élément dans un arbre *)
  and insert_tree : 'a -> 'a tree -> 'a digit list -> 'a digit list = fun x tree digits ->
    match tree with
    | Leaf _ -> One (Node (Leaf x, tree)) :: digits
    | Node (left, right) ->
      let new_left = insert_leaf_left x left in
      if depth right = depth new_left then One (Node (new_left, right)) :: digits
      else Zero :: insert_tree x right (split_left new_left digits)
  
  (* Insère un élément dans le sous-arbre gauche de manière récursive *)
  and insert_leaf_left : 'a -> 'a tree -> 'a tree = fun x tree ->
    match tree with
    | Leaf _ -> Node (Leaf x, tree)
    | Node (left, right) -> Node (insert_leaf_left x left, right)
  
  (* Calcule la profondeur d'un arbre *)
  and depth : 'a tree -> int = function
    | Leaf _ -> 1
    | Node (left, _) -> 1 + depth left
  
  (* Sépare les digits après l'insertion d'un nouvel arbre dans le sous-arbre gauche *)
  and split_left : 'a tree -> 'a digit list -> 'a digit list = fun new_left digits ->
    match digits with
    | [] -> []
    | Zero :: rest -> Zero :: rest
    | One tree :: rest ->
      let new_rest = split_left new_left rest in
      if depth (tree_of_digits new_rest) = depth new_left then One (Node (new_left, tree_of_digits new_rest)) :: rest
      else One tree :: new_rest
  
  (* Crée un arbre à partir d'une liste de digits *)
  and tree_of_digits : 'a digit list -> 'a tree = function
    | [] -> Leaf 0
    | Zero :: _ -> Leaf 0
    | One tree :: rest ->
      match rest with
      | [] -> tree
      | _ -> Node (tree, tree_of_digits rest)
  

  let rec head : 'a t -> 'a = function
    | [] -> raise Empty
    | Zero :: digits -> head digits
    | One (Leaf x) :: _ -> x
    | One (Node (Leaf x, _)) :: _ -> x
    | _ -> failwith "Structure inconnu "


  let rec tail : 'a t -> 'a t = function
    | [] -> raise Empty
    | Zero :: digits -> tail digits
    | One (Leaf _) :: digits -> digits
    | One tree :: digits -> One (adjust (tree)) :: digits 

  (* supprime le premier élément de l'arbre et ajuste la structure *)
  and adjust : 'a tree -> 'a tree = function
    | Node (Leaf _, right)-> right
    | Node (Node (left, right), rest) -> Node (Node (adjust left, right), rest)
    | _ -> failwith "Structure inconnu"

    let rec get : int -> 'a t -> 'a = fun n ral ->
      match n, ral with
      | _, [] -> raise Not_found (* Si la liste est vide, l'élément recherché n'est pas présent *)
      | 0, One (Leaf x) :: _ -> x (* Si l'indice est 0 et la tête de la liste est un arbre contenant une seule feuille, retourne la valeur de la feuille *)
      | _, Zero :: digits -> get (n lsr 1) digits (* décalage à gauche de l indice *)
      | _, One _ :: digits -> get (n - 1) (Zero :: digits) (* Si la tête de liste est un 1, soustrait 1 de l'indice et passe à la suite de la liste *)
end

