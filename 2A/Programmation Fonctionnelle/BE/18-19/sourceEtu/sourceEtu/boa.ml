
(* Interface des règles de réécriture du système BOA *)
module type Regle =
sig

  (* type des identifiants de règles *)
  type tid = int

  (* type des termes *)
  type td

  (* Identifiant de la règle *)
  val id : tid
  
  (* appliquer : td -> td list
    appliquer la règle à un terme td
    retour : liste des termes obtenus *)
  val appliquer : td -> td list
end

module Regle1 : Regle =
struct
  type tid = int
  type td = char list

  let id = 1

  let appliquer t = [List.append ['A']  t]
end

module Regle2 : Regle =
struct
  type tid = int
  type td = char list

  let id = 2

  let appliquer t = [t @ List.tl t]
end

module Regle3 : Regle =
struct
  type tid = int
  type td = char list

  let id = 3

  let rec appliquer t = match t with
    | t1::t2::t3::q -> if ((t1 = 'O') && (t2 = 'O') && (t3 = 'O')) || ((t1 = 'A') && (t2 = 'O') && (t3 = 'A')) then ['A' :: q] @ (List.map (fun x -> t1 ::x)) (appliquer (t2 :: t3 :: q))
    else (List.map (fun x -> t1 ::x)) (appliquer (t2 :: t3 :: q))
    | _ -> []
end


module type ArbreReecriture =
sig
  (*
  type tid = int
  type td
  type arbre_reecriture = ...

  val creer_noeud : ...

  val racine : ...
  val fils : ..

  val appartient : td -> arbre_reecriture -> bool
  *)
end