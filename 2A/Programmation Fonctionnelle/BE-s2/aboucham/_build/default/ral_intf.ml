(* Signature des listes à accès direct (RAL, random-access list). *)
module type S = sig
  (* type des listes à accès direct. *)
  type 'a t

  (* déclaration de l'exception Empty, à lever pour des accès erronés à la liste vide *)
  exception Empty

  (* liste vide *)
  val empty : 'a t

  (* met un élément en tete de liste *)
  val cons : 'a -> 'a t -> 'a t

  (* renvoie l'élément en tête de liste ; lève Empty en cas d'usage sur une liste vide *)
  val head : 'a t -> 'a

  (* renvoie la liste amputée de son élément de tête ; lève Empty en cas d'usage sur une liste vide *)
  val tail : 'a t -> 'a t

  (* `get n l` renvoie le n-ième élément de la liste `l` en comptant à partir de 0 ; si l'élement n'est pas présent, lever l'exception Not_found *)
  val get : int -> 'a t -> 'a
end
