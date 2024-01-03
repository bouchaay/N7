(* Exercice 3 *)
module type Expression = sig
  (* Type pour représenter les expressions *)
  type exp


  (* eval : exp -> int
    Fonction qui calcule la valeur d'une expression
    paramètre e : l'expression à évaluer
    précondition : diviseur != 0
    résultat : la valeur de l'expression
  *)

end

(* Exercice 4 *)
module ExpressionArbreBinaire : Expression = struct

  type op = Moins | Plus | Mult | Div
  type exp = Binaire of exp * op * exp | Entier of int

  (* eval *)
  let rec eval exp =
    match exp with
    | Entier i -> i
    | Binaire (e1, op, e2) ->
      match op with
      | Moins -> (eval e1) - (eval e2)
      | Plus -> (eval e1) + (eval e2)
      | Mult -> (eval e1) * (eval e2)
      | Div -> match e2 with
        | Entier 0 -> failwith "Division par 0"
        | _ -> (eval e1) / (eval e2)


(* Exercice 5 *)
module ExpressionArbreNAire : Expression = struct

  type op = Moins | Plus | Mult | Div
  type exp = Naire of op * exp list | Valeur of int

(* bienformee : exp -> bool *)
(* Vérifie qu'un arbre n-aire représente bien une expression n-aire *)
(* c'est-à-dire que les opérateurs d'addition et multiplication ont au moins deux opérandes *)
(* et que les opérateurs de division et soustraction ont exactement deux opérandes.*)
(* Paramètre : l'arbre n-aire dont ont veut vérifier si il correspond à une expression *)
let rec bienformee exp = match exp with
  | Valeur _ -> true
  | Naire (op, exps) ->
    match op with ->
    | Moins | Div -> List.length exps = 2
    | Plus | Mult -> List.length exps >= 2

(* eval : exp-> int *)
(* Calcule la valeur d'une expression n-aire *)
(* Paramètre : l'expression dont on veut calculer la valeur *)
(* Précondition : l'expression est bien formée *)
(* Résultat : la valeur de l'expression *)
let rec eval_bienformee exp = match exp with ->
  | Valeur i -> i
  | Naire (op, exps) -> match op with ->
    | Plus -> List.fold_left (fun acc e -> acc + (eval e)) 0 exps
    | Mult -> List.fold_left (fun acc e -> acc * (eval e)) 1 exps
    | Moins -> match exps with
      | [e1; e2] -> (eval_bienformee e1) - (eval_bienformee e2)
      | _ -> failwith "Erreur d'expression"
    | Div -> match exps with
      | [e1; e2] -> match (eval_bienformee e2) with
        | Valeur 0 -> failwith "Division par 0"
        | _ -> (eval_bienformee e1) / (eval_bienformee e2)
      | _ -> failwith "Erreur d'expression"

  (* eval *)
  let rec eval exp =
    match exp with
    | Valeur i -> i
    | Naire (op, exps) ->
      match op with
      | Moins -> (eval (List.hd exps)) - (eval (List.hd (List.tl exps)))
      | Plus -> List.fold_left (fun acc e -> acc + (eval e)) 0 exps
      | Mult -> List.fold_left (fun acc e -> acc * (eval e)) 1 exps
      | Div -> match exps with
        | [e1; e2] -> (eval e1) / (eval e2)
        | _ -> failwith "Erreur d'expression"

  
end

(* Exercice 6 *)