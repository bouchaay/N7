(*  Parsing of ricochet robot boards. *)

(*  [file filename] returns the board parsed from file [filename] and
    potential initial position of robots it contains.

    Prints a message on standard error and raises [Report.Error] in
    case something bad happens. *)
val file : string -> Be.Plateau.t * (Be.Plateau.robot * (int * int)) list
