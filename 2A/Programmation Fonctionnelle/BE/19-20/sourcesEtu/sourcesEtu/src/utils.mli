(*  Various utility functions. *)

(*  Same as [with_out_ch] for input channels (with stdin instead of stdout). *)
val with_in_ch : string option -> (in_channel -> 'b) -> 'b

(*  [profile f] executes the function [f] and returns both its result
    and the execution time in second. *)
val profile : (unit -> 'a) -> 'a * float
