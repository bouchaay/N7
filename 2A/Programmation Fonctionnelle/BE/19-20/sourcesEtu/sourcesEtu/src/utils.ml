let with_in_ch input_filename f =
  let in_ch =
    match input_filename with
    | None -> stdin
    | Some filename ->
      try open_in filename
      with Sys_error s ->
        Report.error_loc Location.dummy "%s." s in
  let res = f in_ch in
  begin
    match input_filename with
    | None -> ()
    | Some _ ->
      try close_in in_ch
      with Sys_error s ->
        Report.error_loc Location.dummy "%s." s
  end;
  res

let profile f =
  let be = Unix.gettimeofday () in
  let r = f () in
  let en = Unix.gettimeofday () in
  r, en -. be
