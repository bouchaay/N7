(* Exercice 1 *)

(* 1. Compléter. *)

let tous_les_robots : Plateau.robot list = [](* À FAIRE *)

let toutes_les_directions : Plateau.dir list = [](* À FAIRE *)

(* Exercice 2 *)

module type P = sig
  (*  Type pour représer les positions des cinq robots sur le plateau.
     Deux valeurs [p1] et [p2] de type [t] qui représentent les même
     positions de robot (i.e., pour tout robot [r], [get p1 r]
     et [get p2 r] retournent la même position) doivent être égales
     (i.e., [p1 = p2] retourne [true]). *)
  type t

  (*  Une valeur quelconque. *)
  val quelconque : t

  (*  La position des robots dans la Figure 1 du sujet. *)
  val positions_sujet : t

  (*  [get pos robot] retourne la position (x, y) (c.f. Plateau.mli)
     de [robot] dans [pos]. *)
  val get : t -> Plateau.robot -> int * int

  (*  [set pos robot (x, y)] retourne la position [pos] avec le robot
     [robot] maintenant à la position ([x], [y])
     (c.f. Plateau.mli). *)
  val set : t -> Plateau.robot -> int * int -> t
end

(* 1. Décommenter et compléter. *)

(* module PosSimple : P = struct
 *   (\* À FAIRE *\)
 * end
 *
 * (\* Ne pas modifier la ligne suivante avant l'exercice 4 *\)
 * module Pos : P = PosSimple *)

(* Exercice 3 *)

module type NP = sig
  type t

  (*  [voisin (x, y) dir] retourne les coordonnées de la case
     adjacente à [(x, y)] dans la direction [dir]. Les coordonnées
     retournées peuvent être en dehors du plateau (ou même
     négatives). *)
  val voisin : int * int -> Plateau.dir -> int * int

  (*  [libre pos (x, y)] retourne [true] ssi aucun des robots dans
     [pos] n'occupe la case [(x, y)]. *)
  val libre : t -> int * int -> bool

  (*  [prochain_xy_dir plateau pos (x, y) dir] retourne [(x', y')], la
     position la plus lointaine qu'un robot en [(x, y)] peut atteindre
     dans la direction [dir] sans traverser de mur du [plateau] ni de
     robot dans [pos]. [(x', y')] est égal à [(x, y)] si le robot ne
     peut pas bouger dans la direction [dir]. [(x, y)] peut être dans [pos]. *)
  val prochain_xy_dir : Plateau.t -> t -> int * int -> Plateau.dir -> int * int

  (*  [prochaines_pos_dir plateau pos robot] retourne la liste des
     positions atteignables par un mouvement valide de [robot] sur
     [plateau] avec tous les robots (y compris [robot]) dans le
     spositions [pos]. Tous les éléments d ela liste retournée sotn de
     la forme [((r, d), p)] avec [r] = [robot], [d] la direction dans
     laquelle [robot] a bougé et [p] la nouvelle position atteinte par
     c emouvement ([p] doit être différent de [pos]). *)
  val prochaines_pos_robot :
    Plateau.t -> t -> Plateau.robot -> ((Plateau.robot * Plateau.dir) * t) list

  (*  [prochaines_pos plateau pos] retourne la liste de toutes les
     positions atteignables sur [plateau] en exactement un mouvement
     depuis [pos]. Le résultat enregistre également quel robot bouge
     et dans quelle direction. *)
  val prochaines_pos : Plateau.t -> t -> ((Plateau.robot * Plateau.dir) * t) list
end

(* 1. Décommenter et compléter. *)

(* module MakeNextPos (Pos : P) : NP with type t = Pos.t = struct
 *   (\* À FAIRE *\)
 *
 *   (\* Pour tester *\)
 *   let positions_sujet = Pos.positions_sujet
 *   let plateau_vide = Plateau.make 16 [] []
 *   let plateau_sujet = Plateau.exemple
 * end *)

(* Exercice 4 *)

(* 1. Décommenter et compléter. *)

(* module PosMasque : P = struct
 *   (\* À FAIRE *\)
 * end *)

(* 2. Pour pouvoir compiler le programme complet avec PosMasque,
   décommenter la ligne suivante et commenter la ligne
   "module Pos : P = PosSimple" plus haut. *)
(* module Pos : P = PosMasque *)
