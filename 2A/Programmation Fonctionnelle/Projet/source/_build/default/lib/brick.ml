open Graphics
open Random

(* Type pour modéliser l'etat de la brique *)
type state = Normal | Touched | Destroyed

(* Type pour modéliser la brique *)

(* Interface du module Brick *)
module type Brick =
sig
  type position (* Coin inférieur gauche *)
  type size (* Taille de la brique *)
  type power (* Puissance de la brique *)
  type color (* Couleur de la brique *)
  type brick (* Type de la brique *)

  (* Crée une brique *)
  val create : position -> size -> power -> color -> brick
  (* Renvoie la position de la brique *)
  val get_position : brick -> position
  (* Renvoie la taille de la brique *)
  val get_size : brick -> size
  (* Renvoie la puissance de la brique *)
  val get_power : brick -> power
  (* Renvoie la couleur de la brique *)
  val get_brick_color : brick -> color
  (* Renvoie l'état de la brique *)
  val get_state : brick -> state
  (* Modifie l'état de la brique *)
  val set_position : brick -> position -> brick
  (* Modifie la taille de la brique *)
  val set_size : brick -> size -> brick
  (* Modifie la puissance de la brique *)
  val set_power : brick -> power -> brick
  (* Modifie la couleur de la brique *)
  val set_brick_color : brick -> color -> brick
  (* Modifie l'état de la brique *)
  val set_state : brick -> state -> brick
  (* Renvoie la brique après un coup de balle *)
  val set_brick : brick -> position -> color -> state -> brick
  (* Renvoie si la brique est détruite *)
  val is_destroyed : brick -> bool
  (* Renvoie si la brique est touchée *)
  val is_touched : brick -> bool
  (* Renvoie si la brique est normale *)
  val is_normal : brick -> bool
  (* Générer une ligne de briques *)
  val generate_brick_line : position -> position -> float -> int -> brick list
  (* Générer des lignes de briques *)
  val generate_brick_lines : position -> position -> int -> int -> brick list list
  (* Dessiner une brique *)
  (* Dessiner une brique *)
  val draw_brick : brick -> unit
  (* L'espace entre les briques suivant x *)
  val spacing_x : float
  (* L'espace entre les briques suivant y *)
  val spacing_y : float
  (* La largeur d'une brique *)

end

(* Module Brick *)
module Brick : Brick =
struct
  type position = float * float
  type size = float * float
  type power = int
  type color = Graphics.color
  type brick = position * size * power * color * state

  (* Getters *)
  let create position size power color = (position, size, power, color, Normal)
  let get_position (position, _, _, _, _) = position
  let get_size (_, size, _, _, _) = size
  let get_power (_, _, power, _, _) = power
  let get_brick_color (_, _, _, color, _) = color
  let get_state (_, _, _, _, state) = state

  (* Setters *)
  let set_position (position, size, power, color, state) new_position = (new_position, size, power, color, state)
  let set_size (position, size, power, color, state) new_size = (position, new_size, power, color, state)
  let set_power (position, size, power, color, state) new_power = (position, size, new_power, color, state)
  let set_brick_color (position, size, power, color, state) new_color = (position, size, power, new_color, state)
  let set_state (position, size, power, color, state) new_state = (position, size, power, color, new_state)
  let set_brick (position, size, power, color, state) new_position new_color new_state = (new_position, size, power, new_color, new_state)

  (* Retourner L'etat de la brique *)
  let is_destroyed (_, _, _, _, state) = state = Destroyed
  let is_touched (_, _, _, _, state) = state = Touched
  let is_normal (_, _, _, _, state) = state = Normal

  (* Les espaces entre les briques *)
  let spacing_x = 5.
  let spacing_y = 5.

  (* Générer une ligne de briques *)
  let generate_brick_line position_depart position_fin brick_height nb_bricks =
    let space = spacing_x in (* Espace entre les briques *)
    let width = fst position_fin -. fst position_depart in (* Largeur de la ligne *)
    let brick_width = (width -. (float_of_int nb_bricks -. 1.) *. space) /. float_of_int nb_bricks in (* Largeur d'une brique *)
    let rec aux position nb_bricks color =
      if nb_bricks = 0 then []
      else
        let brick = create position (brick_width, brick_height) (Random.int 3 + 1) color in
        brick :: aux (fst position +. brick_width +. space, snd position) (nb_bricks - 1) color
    in
    let chosen_color = Graphics.rgb (Random.int 256) (Random.int 256) (Random.int 256) in
    aux position_depart nb_bricks chosen_color

  (* Générer des lignes de briques *)
  let generate_brick_lines position_depart position_fin nb_lines nb_bricks =
    let space = spacing_y in (* Espace entre les briques *)
    let height = snd position_fin -. snd position_depart in (* Hauteur de la ligne *)
    let brick_height = (height -. (float_of_int nb_lines -. 1.) *. space) /. float_of_int nb_lines in (* Hauteur d'une brique *)
    let rec aux position nb_lines =
      if nb_lines = 0 then []
      else
        let brick_line = generate_brick_line position (fst position_fin, snd position +. brick_height) brick_height nb_bricks in
        brick_line :: aux (fst position, snd position +. brick_height +. space) (nb_lines - 1)
    in
    aux position_depart nb_lines

  (* Dessiner une brique *)
  let draw_brick ((x, y), (w, h), _, color, _) =
    set_color color;
    fill_rect (int_of_float x) (int_of_float y) (int_of_float w) (int_of_float h)

end

