exception Error

let kstr_loc k str loc =
  Format.kfprintf
    (fun ff ->
      Format.kfprintf
        (fun ff -> Format.fprintf ff "\n%!"; k ff)
        ff)
    Format.err_formatter
    "%a%s" Location.fprint loc str

let error_loc loc = kstr_loc (fun _ -> raise Error) "Error: " loc
