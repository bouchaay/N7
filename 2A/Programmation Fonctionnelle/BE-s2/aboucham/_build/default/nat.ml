(* ordre : du moins au plus significatif; le *nombre* 0 est représenté par []; toute liste finit forcément par un One avant le [] *)
type digit = Zero | One
type t = digit list

let zero = []
let is_zero t = t = []

let rec inc t =
  (* ---------- EXERCICE ---------- *)
(* DEBUT REPONSE *)
match t with
  | [] -> [One]
  | Zero :: q -> One :: q
  | One :: q -> Zero :: inc q
(* FIN REPONSE *)

let rec dec t =
  (* ---------- EXERCICE ---------- *)
(* DEBUT REPONSE *)
match t with
| [] -> []
| One :: q -> Zero :: q
| Zero :: q -> One :: dec q
(* FIN REPONSE *)

let rec to_int t =
  (* ---------- EXERCICE ---------- *)
(* DEBUT REPONSE *)
match t with
  | [] -> 0
  | Zero :: q -> 2 * to_int q
  | One :: q -> 1 + 2 * to_int q
(* FIN REPONSE *)

let rec add n1 n2 =
  (* ---------- EXERCICE ---------- *)
(* DEBUT REPONSE *)
match n1, n2 with
  | [], n2 -> n2
  | n1, [] -> n1
  | Zero :: q1, Zero :: q2 -> Zero :: add q1 q2
  | Zero :: q1, One :: q2 -> One :: add q1 q2
  | One :: q1, Zero :: q2 -> One :: add q1 q2
  | One :: q1, One :: q2 -> Zero :: inc (add q1 q2)
(* FIN REPONSE *)

let print_digit = function
  | Zero -> print_string "0 "
  | One -> print_string "1 "

let print (ds : t) : unit =
  print_string "[ ";
  List.iter print_digit ds;
  print_string " ]"

let of_int (n : int) : t =
  if n < 0 then failwith "of_int: paramètre négatif"
  else
    (* algorithme très naïf : on incrémente de façon répétée... *)
    let rec loop acc = function 0 -> acc | n -> loop (inc acc) (n - 1) in
    loop zero n
