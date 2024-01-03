open Graphics

state = Normal | Invincible
module type Paddle =
sig
  type position (* Coin inférieur gauche *)
  type size (* Taille du paddle *)
  type color (* Couleur du paddle *)
  type paddle (* Type du paddle *)

  (* Crée un paddle *)
  val create : position -> size -> color -> state -> paddle
  (* Renvoie la position du paddle *)
  val get_position : paddle -> position
  (* Renvoie la taille du paddle *)
  val get_size : paddle -> size
  (* Renvoie la couleur du paddle *)
  val get_color : paddle -> color
  (* Dessine le paddle *)
  val get_state : paddle -> state
  (* Change la position du paddle *)
  val set_position : paddle -> position -> paddle
  (* Change la taille du paddle *)
  val set_size : paddle -> size -> paddle
  (* Change la couleur du paddle *)
  val set_paddle_color : paddle -> color -> paddle
  (* Change l'état du paddle *)
  val set_state : paddle -> state -> paddle
  (* Renvoie si la paddle est normal *)
  val is_normal : paddle -> bool
  (* Renvoie si la paddle est invincible *)
  val is_invincible : paddle -> bool
  (* Dessine le paddle *)
  val draw : position -> position -> color -> unit
end

module Paddle : Paddle =
struct
  type position = float * float
  type size = float * float
  type color = Graphics.color
  type paddle = position * size * color * state

  (* Getters *)
  let create pos size color state = (pos, size, color, state)
  let get_position (position, _, _, _) = position
  let get_size (_, size, _, _) = size
  let get_color (_, _, color, _) = color
  let get_state (_, _, _, state) = state

  (* Setters *)
  let set_position (position, size, color, state) new_position = (new_position, size, color, state)
  let set_size (position, size, color, state) new_size = (position, new_size, color, state)
  let set_paddle_color (position, size, color, state) new_color = (position, size, new_color, state)
  let set_state (position, size, color, state) new_state = (position, size, color, new_state)

  (* Retourner l'etat de la paddle *)
  let is_normal (_, _, _, state) = state = Normal
  let is_invincible (_, _, _, state) = state = Invincible

  (* Dessine le paddle *)
  let draw (x, y) (width, height) color =
    set_color color;
    fill_rect (int_of_float x) (int_of_float y) (int_of_float width) (int_of_float height)

end