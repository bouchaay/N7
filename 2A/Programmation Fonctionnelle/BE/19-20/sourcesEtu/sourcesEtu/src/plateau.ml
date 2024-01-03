open Wall

type square = {
    top : bool;
    left : bool;
    bottom : bool;
    right : bool
  }

type t = {
    dim : int;
    walls : square array array;
    targets : target list;
    orig_walls : wall list;
  }

type robot = Rrouge | Rvert | Rbleu | Rjaune | Rnoir

type dir = Dhaut | Dgauche | Dbas | Ddroite

let make dim walls targets =
  let empty_square =
    { top = true; left = true; bottom = true; right = true } in
  let a = Array.make_matrix dim dim empty_square in
  let set_false x y = function
    | Dhaut -> a.(x).(y) <- { a.(x).(y) with top = false }
    | Dgauche -> a.(x).(y) <- { a.(x).(y) with left = false }
    | Dbas -> a.(x).(y) <- { a.(x).(y) with bottom = false }
    | Ddroite -> a.(x).(y) <- { a.(x).(y) with right = false } in
  for i = 0 to dim - 1 do
    set_false i 0 Dhaut;
    set_false i (dim - 1) Dbas;
    set_false 0 i Dgauche;
    set_false (dim - 1) i Ddroite
  done;
  List.iter
    (function
     | x, y, Wtop ->
        if x >= 0 && x < dim && y > 0 && y < dim then
          begin set_false x y Dhaut; set_false x (y - 1) Dbas end
     | x, y, Wleft ->
        if x > 0 && x < dim && y >= 0 && y < dim then
          begin set_false x y Dgauche; set_false (x - 1) y Ddroite end)
    walls;
  { dim = dim; walls = a; targets = targets; orig_walls = walls }

let dim b = b.dim

let walls b = b.orig_walls

let targets b = b.targets

let pas_de_mur b (x, y) = function
  | Dhaut -> b.walls.(x).(y).top
  | Dgauche -> b.walls.(x).(y).left
  | Dbas -> b.walls.(x).(y).bottom
  | Ddroite -> b.walls.(x).(y).right

let exemple =
  make 16
    [(2, 2, Wleft); (10, 10, Wleft); (12, 2, Wleft); (11, 3, Wtop);
     (11, 14, Wleft); (12, 0, Wleft); (14, 9, Wleft); (9, 8, Wleft);
     (1, 2, Wtop); (7, 8, Wleft); (13, 15, Wleft); (3, 6, Wleft);
     (12, 11, Wleft); (9, 11, Wtop); (15, 6, Wtop); (8, 7, Wtop);
     (2, 9, Wleft); (1, 15, Wtop); (7, 9, Wtop); (5, 9, Wtop); (6, 4, Wtop);
     (3, 7, Wtop); (0, 12, Wtop); (5, 13, Wleft); (13, 3, Wtop); (8, 9, Wtop);
     (4, 1, Wtop); (10, 4, Wleft); (10, 5, Wtop); (7, 3, Wleft); (8, 13, Wleft);
     (2, 14, Wleft); (7, 7, Wtop); (14, 3, Wleft); (15, 12, Wtop);
     (12, 5, Wtop); (4, 1, Wleft); (12, 11, Wtop); (2, 9, Wtop); (14, 10, Wtop);
     (5, 8, Wleft); (7, 7, Wleft); (4, 13, Wtop); (2, 0, Wleft); (9, 7, Wleft);
     (0, 6, Wtop); (6, 15, Wleft); (8, 13, Wtop); (10, 14, Wtop);
     (12, 5, Wleft)]
    [(6, 3, Tyellow, Tstar); (13, 3, Tyellow, Tmoon); (11, 2, Tred, Tsaturn);
     (10, 4, Tgreen, Tstar); (4, 1, Tred, Tmoon); (9, 10, Tgreen, Tsaturn);
     (10, 14, Tblue, Tmoon); (1, 2, Tgreen, Tsun); (4, 13, Tblue, Tstar);
     (2, 9, Tgreen, Tmoon); (14, 9, Tyellow, Tsun); (3, 6, Tblue, Tsaturn);
     (5, 8, Tyellow, Tsaturn); (12, 11, Tred, Tstar); (1, 14, Tred, Tsun);
     (12, 5, Tblue, Tsun); (8, 13, Tany, Tsun)]

let char_of_robot = function
  | Rrouge -> 'R' | Rvert -> 'G' | Rbleu -> 'B' | Rjaune -> 'Y' | Rnoir -> 'K'

let char_of_dir = function
  | Dhaut -> '^' | Dgauche -> '<' | Dbas -> 'v' | Ddroite -> '>'

let to_string b p =
  let a = Array.make (2 * b.dim + 1) Bytes.empty in
  for i = 0 to 2 * b.dim do a.(i) <- Bytes.make (4 * b.dim + 1) ' ' done;
  (* draw contour *)
  let draw_hline i st en c =
    Bytes.set a.(i) (4 * (st - 1)) '+';
    for j = st to en do
      Bytes.set a.(i) (4 * j - 3) c;
      Bytes.set a.(i) (4 * j - 2) c;
      Bytes.set a.(i) (4 * j - 1) c;
      Bytes.set a.(i) (4 * j) '+'
    done in
  let draw_vline j st en c =
    Bytes.set a.(2 * (st - 1)) (j) '+';
    for i = st to en do
      Bytes.set a.(2 * i - 1) j c;
      Bytes.set a.(2 * i) j '+'
    done in
  draw_hline 0 1 b.dim '-';
  draw_hline (2 * b.dim) 1 b.dim '-';
  draw_vline 0 1 b.dim '|';
  draw_vline (4 * b.dim) 1 b.dim '|';
  (* and + everywhere to distinguish squares *)
  for j = 1 to b.dim - 1 do draw_vline (4 * j) 1 b.dim ' ' done;
  (* and walls *)
  let draw_wall (x, y, anchor) = match anchor with
    | Wtop ->
       Bytes.set a.(2 * y) (4 * x + 1) '-';
       Bytes.set a.(2 * y) (4 * x + 2) '-';
       Bytes.set a.(2 * y) (4 * x + 3) '-'
    | Wleft ->
       Bytes.set a.(2 * y + 1) (4 * x) '|' in
  List.iter draw_wall b.orig_walls;
  (* and targets *)
  let draw_target (x, y, c, t) =
    Bytes.set a.(2 * y + 1) (4 * x + 2) (char_of_type t);
    Bytes.set a.(2 * y + 1) (4 * x + 3) (char_of_color c) in
  List.iter draw_target b.targets;
  (* and robots in p *)
  let draw_robot (r, (x, y)) =
    Bytes.set a.(2 * y + 1) (4 * x + 1) '<';
    Bytes.set a.(2 * y + 1) (4 * x + 2) (char_of_robot r);
    Bytes.set a.(2 * y + 1) (4 * x + 3) '>' in
  List.iter draw_robot p;
  (* assemble output string *)
  Bytes.to_string (Bytes.concat (Bytes.of_string "\n") (Array.to_list a))
