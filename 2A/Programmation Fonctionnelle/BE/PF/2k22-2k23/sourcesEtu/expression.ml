(* Exercice 3 *)
module type Expression = sig
  (* Type pour représenter les expressions *)
  type exp


  (* eval : exp -> int
      fonction qui permet d'évaluer la valeur d'une expression donnée 
      parametres : - L'expresion à évaluer
      résultat : la valeur de l'expression
      error : division / 0
  *)
  val eval : exp -> int
end

(* Exercice 4 *)
module ExpAvecArbreBinaire : Expression = 
struct 
  type op = Moins | Plus | Mult | Div
  type exp = Binaire of exp * op * exp | Entier of int

  let rec eval =  fun exp_tree -> match exp_tree with 
    | Entier(x) -> x
    | Binaire (subtree_left, op, subtree_right) -> 
      match op with 
        | Moins -> (eval subtree_left) - (eval subtree_right) 
        | Plus -> (eval subtree_left) + (eval subtree_right) 
        | Mult -> (eval subtree_left) * (eval subtree_right) 
        | Div -> 
          if ((eval subtree_right) = 0) then raise (Failure "" )
          else(eval subtree_left) / (eval subtree_right) 
  (* Tests : TO DO *)
  let%test _ = eval (Entier 5) = 5
  let%test _ = eval (Binaire ((Entier 2), Plus, (Entier 3))) = 5
  let%test _ = eval (Binaire ((Entier 2), Moins, (Entier 3))) = -1
  let%test _ = eval (Binaire ((Entier 2), Mult, (Entier 3))) = 6
  let%test _ = eval (Binaire ((Entier 2), Div, (Entier 3))) = 0

  let%test _ = try let _ = eval (Binaire ((Entier 2), Div, (Entier 0))) in false with Failure ""-> true 

  let%test _ = eval (Binaire (
    Binaire ( 
      (Entier 3),
      Plus,
      (Entier 4)
    ),
    Moins,
    (Entier 12)
    )
  ) = -5
end 


(* Exercice 5 *)
module ExpAvecArbreNaire : Expression = 
struct 
  type op = Moins | Plus | Mult | Div
  type exp = Naire of op * exp list | Valeur of int

  (* bienformee : exp -> bool *)
(* Vérifie qu'un arbre n-aire représente bien une expression n-aire *)
(* c'est-à-dire que les opérateurs d'addition et multiplication ont au moins deux opérandes *)
(* et que les opérateurs de division et soustraction ont exactement deux opérandes.*)
(* Paramètre : l'arbre n-aire dont ont veut vérifier si il correspond à une expression *)
let rec bienformee = fun exp_tree -> match exp_tree with
  | Valeur (_) -> true
  | Naire(op, exp_list) ->  let current = match op with 
    | Plus | Mult -> ((List.length exp_list) >= 2)
    | Moins | Div -> ((List.length exp_list) == 2)
  in current &&  (List.for_all bienformee exp_list) 

let en1 = Naire (Plus, [ Valeur 3; Valeur 4; Valeur 12 ])
let en2 = Naire (Moins, [ en1; Valeur 5 ])
let en3 = Naire (Mult, [ en1; en2; en1 ])
let en4 = Naire (Div, [ en3; Valeur 2 ])
let en1err = Naire (Plus, [ Valeur 3 ])
let en2err = Naire (Moins, [ en1; Valeur 5; Valeur 4 ])
let en3err = Naire (Mult, [ en1 ])
let en4err = Naire (Div, [ en3; Valeur 2; Valeur 3 ])

let%test _ = bienformee en1
let%test _ = bienformee en2
let%test _ = bienformee en3
let%test _ = bienformee en4
let%test _ = not (bienformee en1err)
let%test _ = not (bienformee en2err)
let%test _ = not (bienformee en3err)
let%test _ = not (bienformee en4err)

(* eval : exp-> int *)
(* Calcule la valeur d'une expression n-aire *)
(* Paramètre : l'expression dont on veut calculer la valeur *)
(* Précondition : l'expression est bien formée *)
(* Résultat : la valeur de l'expression *)

(* let eval_bienformee = fun _ -> assert false *)

let rec eval_bienformee =  fun exp_tree -> match exp_tree with
  | Valeur(x) -> x 
  | Naire(op, exp_list) -> match op with 
    | Plus -> List.fold_right (fun e acc -> eval_bienformee(e) + acc) exp_list 0 
    | Moins -> List.fold_right (fun e acc -> eval_bienformee(e) - acc) exp_list 0 
    | Mult -> List.fold_right (fun e acc -> eval_bienformee(e) * acc) exp_list 1 
    | Div -> List.fold_right (fun e acc -> eval_bienformee(e) / acc) exp_list 1

let%test _ = eval_bienformee en1 = 19
let%test _ = eval_bienformee en2 = 14
let%test _ = eval_bienformee en3 = 5054
let%test _ = eval_bienformee en4 = 2527

(* Définition de l'exception Malformee *)
exception Malformee


(* eval : exp-> int *)
(* Calcule la valeur d'une expression n-aire *)
(* Paramètre : l'expression dont on veut calculer la valeur *)
(* Résultat : la valeur de l'expression *)
(* Exception  Malformee si le paramètre est mal formé *)


let eval  = fun exp_tree -> 
  if (bienformee exp_tree) then (eval_bienformee exp_tree)
  else raise Malformee

let%test _ = eval en1 = 19
let%test _ = eval en2 = 14
let%test _ = eval en3 = 5054
let%test _ = eval en4 = 2527

let%test _ =
  try
    let _ = eval en1err in
    false
  with Malformee -> true

let%test _ =
  try
    let _ = eval en2err in
    false
  with Malformee -> true

let%test _ =
  try
    let _ = eval en3err in
    false
  with Malformee -> true

let%test _ =
  try
    let _ = eval en4err in
    false
  with Malformee -> true
end 