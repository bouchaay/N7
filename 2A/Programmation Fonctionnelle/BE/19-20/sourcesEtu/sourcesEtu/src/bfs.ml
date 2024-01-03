type solution = (Be.Plateau.robot * Be.Plateau.dir) list

module NP : Be.Pos.NP with type t = Be.Pos.Pos.t =
  Be.Pos.MakeNextPos (Be.Pos.Pos)

let bfs b init_pos goal =
  let reached = Hashtbl.create 2500000 in
  let q = Queue.create () in
  let rec aux dist =
    let sol, sz, p = Queue.pop q in
    if sz > dist then
      Printf.printf "distance %2d #atteints: %d\n%!"
        sz (Hashtbl.length reached);
    let n = NP.prochaines_pos b p in
    let n = List.filter (fun (_, p) -> not (Hashtbl.mem reached p)) n in
    try
      let rd, p = List.find (fun (_, p) -> goal p) n in
      List.rev (rd :: sol), p
    with Not_found ->
      let () = List.iter (fun (_, p) -> Hashtbl.add reached p ()) n in
      let () =
        List.iter (fun (rd, p) -> Queue.push (rd :: sol, sz + 1, p) q) n in
      aux sz in
  if goal init_pos then [], init_pos else
    let () = Hashtbl.add reached init_pos () in
    let () = Queue.push ([], 0, init_pos) q in
    aux (-1)
