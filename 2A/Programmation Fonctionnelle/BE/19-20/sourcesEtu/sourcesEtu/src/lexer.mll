{
open Parser
   
exception Lexing_error of string
}

let digit = ['0'-'9']
let blank = [' ' '\r' '\t']
let nl = ['\r']?['\n']

rule token = parse
  | nl { Lexing.new_line lexbuf; token lexbuf }
  | blank+ { token lexbuf }
  | '(' { LPAR }
  | ')' { RPAR }
  | ';' { SEMICOL }
  | ',' { COMA }
  | '.' { DOT }
  | "dim" { DIM }
  | "barrier" { BARRIER }
  | "available_target" { AVAILABLETARGET }
  | "robot" { ROBOT }
  | "initial_pos" { INITIALPOS }
  | "red" { RED }
  | "green" { GREEN }
  | "blue" { BLUE }
  | "yellow" { YELLOW }
  | "black" { BLACK }
  | "any" { ANY }
  | "moon" { MOON }
  | "saturn" { SATURN }
  | "star" { STAR }
  | "sun" { SUN }
  | '-'? ('0' | (['1'-'9'] digit*)) as n
      { let n =
          try int_of_string n
          with Failure _ ->
            raise (Lexing_error "constant overflow") in
        INT n }
  | eof { EOF }
  | _ { raise (Lexing_error "unknown char") }
