module M (* : Ral_intf.S *) = struct
  type 'a t = Nil | Zero of ('a * 'a) t | One of 'a * ('a * 'a) t

  exception Empty

  let empty = Nil


  (* let rec cons x ral =
    match ral with
    | Nil -> One (x, Nil)
    | Zero tl -> One (x, tl)
    | One (y, tl) -> Zero (cons (x, y) tl) *)


  (* let rec head ral =
    match ral with
    | Nil -> raise Empty
    | Zero tl -> head_branch tl
    | One (x, _) -> x

  and head_branch br =
    match br with
    | Nil -> raise Empty
    | Zero res -> head_branch res
    | One (x, _) -> match x with (a, _) -> a
  *)

  let rec tail ral =
    match ral with
    | Nil -> raise Empty
    | Zero tl -> tl
    | One (_, tl) -> tl

  (* let rec get n ral =
    match n, ral with
    | 0, One (x, _) -> x
    | _, Zero tl -> get (n lsr 1) tl
    | _, One (_, tl) -> get (n-1) tl
    | _, _ -> raise Empty
  *)
end
