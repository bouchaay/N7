type wall_type = Wtop | Wleft

(*  (x, y) position starting at (0, 0) on upper left corner of the
   board, wall_type determines whether:
   * Wtop: the wall is on top of the square
   * Wleft: the wall is on the left of the square *)
type wall = int * int * wall_type

type target_color = Tred | Tgreen | Tblue | Tyellow | Tany
type target_type = Tmoon | Tsaturn | Tstar | Tsun

(*  (x, y) position starting at (0, 0) on upper left corner of the board *)
type target = int * int * target_color * target_type

let char_of_color = function
  | Tred -> 'r' | Tgreen -> 'g' | Tblue -> 'b' | Tyellow -> 'y' | Tany -> '*'

let char_of_type = function
  | Tmoon -> 'C' | Tsaturn -> 'O' | Tstar -> '+' | Tsun -> '*'
