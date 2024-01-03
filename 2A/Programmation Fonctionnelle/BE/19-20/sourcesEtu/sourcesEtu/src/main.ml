open Be

let all_robots = Plateau.[Rjaune; Rbleu; Rvert; Rrouge; Rnoir]

let target_list b =
  List.map
    (fun (x, y, c, t) ->
      let s = Bytes.create 2 in
      Bytes.set s 0 (Wall.char_of_type t);
      Bytes.set s 1 (Wall.char_of_color c);
      Bytes.to_string s,
      let g r = fun p -> Pos.Pos.get p r = (x, y) in
      match c with
      | Tred -> g Rrouge | Tgreen -> g Rvert
      | Tblue -> g Rbleu | Tyellow -> g Rjaune
      | Tany -> fun p -> List.exists (fun r -> g r p) all_robots)
    (Plateau.targets b)

let _ =
  let board, pos = Parse.file "board.lp" in
  let targets = target_list board in
  let pos = ref pos in
  let get_pos () =
    List.fold_left (fun p (r, xy) -> Pos.Pos.set p r xy) Pos.Pos.quelconque !pos in
  let set_pos p = pos := List.map (fun r -> r, Pos.Pos.get p r) all_robots in
  Printf.printf "%s\n%!" (Plateau.to_string board !pos);
  try
    while true do
      Printf.printf "entrer cible : %!";
      let s = let s = ref "" in Scanf.scanf "%s\n" (fun s' -> s := s'); !s in
      try
        let g = List.assoc s targets in
        let p = get_pos () in
        let (sol, new_pos), t =
          Utils.profile (fun () -> Bfs.bfs board p g) in
        set_pos new_pos;
        Printf.printf "solution minimale de taille %d trouvée en %.3gs\n"
          (List.length sol) t;
        Printf.printf "la solution";
        let print_robot_dir (r, d) =
          Printf.printf " %c%c" (Plateau.char_of_robot r) (Plateau.char_of_dir d) in
        List.iter print_robot_dir sol;
        Printf.printf " mène à\n";
        Printf.printf "%s\n%!" (Plateau.to_string board !pos)
      with Not_found ->
        Printf.printf "lu \"%s\" mais cible doit être parmi" s;
        List.iter (fun (s, _) -> Printf.printf " %s" s) targets;
        Printf.printf "\n%!"
    done
  with End_of_file -> ()
