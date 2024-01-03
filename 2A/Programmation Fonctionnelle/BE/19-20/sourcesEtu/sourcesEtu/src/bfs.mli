open Be

type solution = (Plateau.robot * Plateau.dir) list

(*  [bfs b init_pos goal] returns [s, new_pos] where [s] is a shortest
   solution to go from [init_pos] to a position [new_pos] satifying
   [goal] on board [b]. *)
val bfs : Plateau.t -> Pos.Pos.t -> (Pos.Pos.t -> bool) -> solution * Pos.Pos.t
