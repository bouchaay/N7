(*  Module providing functions to print errors in a way the compilation mode
    of Emacs understands while handling verbosity level and locations. *)

(*  Exception raised after printing an error message. *)
exception Error

(*  Prints an error message along with location and raises [Error]. *)
val error_loc : Location.t -> ('a, Format.formatter, unit, 'b) format4 -> 'a
