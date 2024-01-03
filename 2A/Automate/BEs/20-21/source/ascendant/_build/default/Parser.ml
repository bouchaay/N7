
module MenhirBasics = struct
  
  exception Error
  
  let _eRR =
    fun _s ->
      raise Error
  
  type token = 
    | UL_TO
    | UL_STATE
    | UL_STARTS
    | UL_REGION
    | UL_PT
    | UL_ON
    | UL_MACHINE
    | UL_IDENT of (
# 18 "Parser.mly"
       (string)
# 22 "Parser.ml"
  )
    | UL_FROM
    | UL_FIN
    | UL_EVENT
    | UL_ENDS
    | UL_ACCOUV
    | UL_ACCFER
  
end

include MenhirBasics

# 1 "Parser.mly"
  

(* Partie recopiee dans le fichier CaML genere. *)
(* Ouverture de modules exploites dans les actions *)
(* Declarations de types, de constantes, de fonctions, d'exceptions exploites dans les actions *)


# 43 "Parser.ml"

type ('s, 'r) _menhir_state = 
  | MenhirState03 : ('s _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_state
    (** State 03.
        Stack shape : UL_IDENT.
        Start symbol: machine. *)

  | MenhirState11 : (('s, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_state
    (** State 11.
        Stack shape : UL_REGION UL_IDENT UL_IDENT.
        Start symbol: machine. *)

  | MenhirState15 : (('s, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_state
    (** State 15.
        Stack shape : UL_REGION UL_IDENT UL_IDENT.
        Start symbol: machine. *)

  | MenhirState20 : (('s, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_state
    (** State 20.
        Stack shape : UL_REGION UL_IDENT UL_IDENT.
        Start symbol: machine. *)

  | MenhirState24 : (('s, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_state
    (** State 24.
        Stack shape : UL_REGION UL_IDENT UL_IDENT.
        Start symbol: machine. *)

  | MenhirState36 : (('s _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_cell1_UL_FROM, _menhir_box_machine) _menhir_state
    (** State 36.
        Stack shape : UL_IDENT UL_FROM.
        Start symbol: machine. *)

  | MenhirState41 : ((('s _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_cell1_UL_FROM, _menhir_box_machine) _menhir_cell1_nom_qualifie, _menhir_box_machine) _menhir_state
    (** State 41.
        Stack shape : UL_IDENT UL_FROM nom_qualifie.
        Start symbol: machine. *)


and ('s, 'r) _menhir_cell1_nom_qualifie = 
  | MenhirCell1_nom_qualifie of 's * ('s, 'r) _menhir_state * (unit)

and ('s, 'r) _menhir_cell1_UL_FROM = 
  | MenhirCell1_UL_FROM of 's * ('s, 'r) _menhir_state

and ('s, 'r) _menhir_cell1_UL_IDENT = 
  | MenhirCell1_UL_IDENT of 's * ('s, 'r) _menhir_state * (
# 18 "Parser.mly"
       (string)
# 92 "Parser.ml"
)

and 's _menhir_cell0_UL_IDENT = 
  | MenhirCell0_UL_IDENT of 's * (
# 18 "Parser.mly"
       (string)
# 99 "Parser.ml"
)

and ('s, 'r) _menhir_cell1_UL_REGION = 
  | MenhirCell1_UL_REGION of 's * ('s, 'r) _menhir_state

and _menhir_box_machine = 
  | MenhirBox_machine of (unit) [@@unboxed]

let _menhir_action_01 =
  fun () ->
    (
# 46 "Parser.mly"
                                   ( (print_endline "etat : STATE IDENT STARTS") )
# 113 "Parser.ml"
     : (unit))

let _menhir_action_02 =
  fun () ->
    (
# 47 "Parser.mly"
                                    ( (print_endline "etat : STATE IDENT ENDS") )
# 121 "Parser.ml"
     : (unit))

let _menhir_action_03 =
  fun () ->
    (
# 48 "Parser.mly"
                            ( (print_endline "etat : STATE IDENT") )
# 129 "Parser.ml"
     : (unit))

let _menhir_action_04 =
  fun () ->
    (
# 49 "Parser.mly"
                                              ( (print_endline "etat : STATE IDENT STARTS ENDS") )
# 137 "Parser.ml"
     : (unit))

let _menhir_action_05 =
  fun () ->
    (
# 50 "Parser.mly"
                                                          ( (print_endline "etat : STAT IDENT STARTS ACCOUV ACCFER") )
# 145 "Parser.ml"
     : (unit))

let _menhir_action_06 =
  fun () ->
    (
# 51 "Parser.mly"
                                                                 ( (print_endline "etat : STAT IDENT STARTS ACCOUV region ACCFER") )
# 153 "Parser.ml"
     : (unit))

let _menhir_action_07 =
  fun () ->
    (
# 52 "Parser.mly"
                                                        ( (print_endline "etat : STATE IDENT ENDS ACCOUV ACCFER") )
# 161 "Parser.ml"
     : (unit))

let _menhir_action_08 =
  fun () ->
    (
# 53 "Parser.mly"
                                                               ( (print_endline "etat : STATE IDENT ENDS ACCOUV region ACCFER") )
# 169 "Parser.ml"
     : (unit))

let _menhir_action_09 =
  fun () ->
    (
# 54 "Parser.mly"
                                                                  ( (print_endline "etat : STATE IDENT STARTS ENDS ACCOUV ACCFER") )
# 177 "Parser.ml"
     : (unit))

let _menhir_action_10 =
  fun () ->
    (
# 55 "Parser.mly"
                                                                         ( (print_endline "etat : STATE IDENT STARTS ENDS ACCOUV region ACCFER") )
# 185 "Parser.ml"
     : (unit))

let _menhir_action_11 =
  fun () ->
    (
# 56 "Parser.mly"
                                                ( (print_endline "etat : STATE IDENT ACCOUV ACCFER") )
# 193 "Parser.ml"
     : (unit))

let _menhir_action_12 =
  fun () ->
    (
# 57 "Parser.mly"
                                                       ( (print_endline "etat : STATE IDENT ACCOUV region ACCFER") )
# 201 "Parser.ml"
     : (unit))

let _menhir_action_13 =
  fun () ->
    (
# 37 "Parser.mly"
                          ( (print_endline "event : EVENT IDENT") )
# 209 "Parser.ml"
     : (unit))

let _menhir_action_14 =
  fun () ->
    (
# 32 "Parser.mly"
                                                  ( (print_endline "machine : MACHINE IDENT ACCOUV ACCFER") )
# 217 "Parser.ml"
     : (unit))

let _menhir_action_15 =
  fun () ->
    (
# 33 "Parser.mly"
                                                                 ( (print_endline "machine : MACHINE IDENT ACCOUV IDENT event ACCFER") )
# 225 "Parser.ml"
     : (unit))

let _menhir_action_16 =
  fun () ->
    (
# 34 "Parser.mly"
                                                             ( (print_endline "machine : MACHINE IDENT ACCOUV transition ACCFER") )
# 233 "Parser.ml"
     : (unit))

let _menhir_action_17 =
  fun () ->
    (
# 35 "Parser.mly"
                                                         ( (print_endline "machine : MACHINE IDENT ACCOUV region ACCFER") )
# 241 "Parser.ml"
     : (unit))

let _menhir_action_18 =
  fun () ->
    (
# 41 "Parser.mly"
                        ( (print_endline "nom_qualifie : IDENT") )
# 249 "Parser.ml"
     : (unit))

let _menhir_action_19 =
  fun () ->
    (
# 41 "Parser.mly"
                                                                                    ( (print_endline "nom_qualifie : PT IDENT") )
# 257 "Parser.ml"
     : (unit))

let _menhir_action_20 =
  fun () ->
    (
# 43 "Parser.mly"
                                                     ( (print_endline "region : REGION IDENT ACCOUV etat ACCFER") )
# 265 "Parser.ml"
     : (unit))

let _menhir_action_21 =
  fun () ->
    (
# 44 "Parser.mly"
                                                 ( (print_endline "region : REGION IDENT ACCOUV ACCFER") )
# 273 "Parser.ml"
     : (unit))

let _menhir_action_22 =
  fun () ->
    (
# 39 "Parser.mly"
                                                                    ( (print_endline "transition : FROM nom_qualifie TO nom_qualifie ON IDENT") )
# 281 "Parser.ml"
     : (unit))

let _menhir_print_token : token -> string =
  fun _tok ->
    match _tok with
    | UL_ACCFER ->
        "UL_ACCFER"
    | UL_ACCOUV ->
        "UL_ACCOUV"
    | UL_ENDS ->
        "UL_ENDS"
    | UL_EVENT ->
        "UL_EVENT"
    | UL_FIN ->
        "UL_FIN"
    | UL_FROM ->
        "UL_FROM"
    | UL_IDENT _ ->
        "UL_IDENT"
    | UL_MACHINE ->
        "UL_MACHINE"
    | UL_ON ->
        "UL_ON"
    | UL_PT ->
        "UL_PT"
    | UL_REGION ->
        "UL_REGION"
    | UL_STARTS ->
        "UL_STARTS"
    | UL_STATE ->
        "UL_STATE"
    | UL_TO ->
        "UL_TO"

let _menhir_fail : unit -> 'a =
  fun () ->
    Printf.eprintf "Internal failure -- please contact the parser generator's developers.\n%!";
    assert false

include struct
  
  [@@@ocaml.warning "-4-37-39"]
  
  let rec _menhir_goto_machine : type  ttv_stack. ttv_stack -> _ -> _menhir_box_machine =
    fun _menhir_stack _v ->
      MenhirBox_machine _v
  
  let rec _menhir_run_48 : type  ttv_stack. ttv_stack _menhir_cell0_UL_IDENT -> _ -> _menhir_box_machine =
    fun _menhir_stack _tok ->
      match (_tok : MenhirBasics.token) with
      | UL_ACCFER ->
          let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
          let _v = _menhir_action_17 () in
          _menhir_goto_machine _menhir_stack _v
      | _ ->
          _eRR ()
  
  let rec _menhir_goto_etat : type  ttv_stack. (ttv_stack, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT -> _ -> _ -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _tok ->
      match (_tok : MenhirBasics.token) with
      | UL_ACCFER ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
          let MenhirCell1_UL_REGION (_menhir_stack, _menhir_s) = _menhir_stack in
          let _ = _menhir_action_20 () in
          _menhir_goto_region _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s _tok
      | _ ->
          _eRR ()
  
  and _menhir_goto_region : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_machine) _menhir_state -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s _tok ->
      match _menhir_s with
      | MenhirState03 ->
          _menhir_run_48 _menhir_stack _tok
      | MenhirState24 ->
          _menhir_run_26 _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | MenhirState20 ->
          _menhir_run_22 _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | MenhirState15 ->
          _menhir_run_17 _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | MenhirState11 ->
          _menhir_run_13 _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | _ ->
          _menhir_fail ()
  
  and _menhir_run_26 : type  ttv_stack. (ttv_stack, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT -> _ -> _ -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _tok ->
      match (_tok : MenhirBasics.token) with
      | UL_ACCFER ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
          let _ = _menhir_action_12 () in
          _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | _ ->
          _eRR ()
  
  and _menhir_run_22 : type  ttv_stack. (ttv_stack, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT -> _ -> _ -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _tok ->
      match (_tok : MenhirBasics.token) with
      | UL_ACCFER ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
          let _ = _menhir_action_08 () in
          _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | _ ->
          _eRR ()
  
  and _menhir_run_17 : type  ttv_stack. (ttv_stack, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT -> _ -> _ -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _tok ->
      match (_tok : MenhirBasics.token) with
      | UL_ACCFER ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
          let _ = _menhir_action_06 () in
          _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | _ ->
          _eRR ()
  
  and _menhir_run_13 : type  ttv_stack. (ttv_stack, _menhir_box_machine) _menhir_cell1_UL_REGION _menhir_cell0_UL_IDENT _menhir_cell0_UL_IDENT -> _ -> _ -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _tok ->
      match (_tok : MenhirBasics.token) with
      | UL_ACCFER ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
          let _ = _menhir_action_10 () in
          _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | _ ->
          _eRR ()
  
  let rec _menhir_run_04 : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_machine) _menhir_state -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | UL_IDENT _v ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          (match (_tok : MenhirBasics.token) with
          | UL_ACCOUV ->
              let _tok = _menhir_lexer _menhir_lexbuf in
              (match (_tok : MenhirBasics.token) with
              | UL_STATE ->
                  let _menhir_stack = MenhirCell1_UL_REGION (_menhir_stack, _menhir_s) in
                  let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v) in
                  let _tok = _menhir_lexer _menhir_lexbuf in
                  (match (_tok : MenhirBasics.token) with
                  | UL_IDENT _v_0 ->
                      let _tok = _menhir_lexer _menhir_lexbuf in
                      (match (_tok : MenhirBasics.token) with
                      | UL_STARTS ->
                          let _tok = _menhir_lexer _menhir_lexbuf in
                          (match (_tok : MenhirBasics.token) with
                          | UL_ENDS ->
                              let _tok = _menhir_lexer _menhir_lexbuf in
                              (match (_tok : MenhirBasics.token) with
                              | UL_ACCOUV ->
                                  let _tok = _menhir_lexer _menhir_lexbuf in
                                  (match (_tok : MenhirBasics.token) with
                                  | UL_REGION ->
                                      let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v_0) in
                                      _menhir_run_04 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState11
                                  | UL_ACCFER ->
                                      let _tok = _menhir_lexer _menhir_lexbuf in
                                      let _ = _menhir_action_09 () in
                                      _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                                  | _ ->
                                      _eRR ())
                              | UL_ACCFER ->
                                  let _ = _menhir_action_04 () in
                                  _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                              | _ ->
                                  _eRR ())
                          | UL_ACCOUV ->
                              let _tok = _menhir_lexer _menhir_lexbuf in
                              (match (_tok : MenhirBasics.token) with
                              | UL_REGION ->
                                  let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v_0) in
                                  _menhir_run_04 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState15
                              | UL_ACCFER ->
                                  let _tok = _menhir_lexer _menhir_lexbuf in
                                  let _ = _menhir_action_05 () in
                                  _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                              | _ ->
                                  _eRR ())
                          | UL_ACCFER ->
                              let _ = _menhir_action_01 () in
                              _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                          | _ ->
                              _eRR ())
                      | UL_ENDS ->
                          let _tok = _menhir_lexer _menhir_lexbuf in
                          (match (_tok : MenhirBasics.token) with
                          | UL_ACCOUV ->
                              let _tok = _menhir_lexer _menhir_lexbuf in
                              (match (_tok : MenhirBasics.token) with
                              | UL_REGION ->
                                  let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v_0) in
                                  _menhir_run_04 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState20
                              | UL_ACCFER ->
                                  let _tok = _menhir_lexer _menhir_lexbuf in
                                  let _ = _menhir_action_07 () in
                                  _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                              | _ ->
                                  _eRR ())
                          | UL_ACCFER ->
                              let _ = _menhir_action_02 () in
                              _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                          | _ ->
                              _eRR ())
                      | UL_ACCOUV ->
                          let _tok = _menhir_lexer _menhir_lexbuf in
                          (match (_tok : MenhirBasics.token) with
                          | UL_REGION ->
                              let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v_0) in
                              _menhir_run_04 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState24
                          | UL_ACCFER ->
                              let _tok = _menhir_lexer _menhir_lexbuf in
                              let _ = _menhir_action_11 () in
                              _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                          | _ ->
                              _eRR ())
                      | UL_ACCFER ->
                          let _ = _menhir_action_03 () in
                          _menhir_goto_etat _menhir_stack _menhir_lexbuf _menhir_lexer _tok
                      | _ ->
                          _eRR ())
                  | _ ->
                      _eRR ())
              | UL_ACCFER ->
                  let _tok = _menhir_lexer _menhir_lexbuf in
                  let _ = _menhir_action_21 () in
                  _menhir_goto_region _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s _tok
              | _ ->
                  _eRR ())
          | _ ->
              _eRR ())
      | _ ->
          _eRR ()
  
  let rec _menhir_run_42 : type  ttv_stack. ((ttv_stack _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_cell1_UL_FROM, _menhir_box_machine) _menhir_cell1_nom_qualifie -> _ -> _ -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _tok ->
      match (_tok : MenhirBasics.token) with
      | UL_ON ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          (match (_tok : MenhirBasics.token) with
          | UL_IDENT _ ->
              let _tok = _menhir_lexer _menhir_lexbuf in
              let MenhirCell1_nom_qualifie (_menhir_stack, _, _) = _menhir_stack in
              let MenhirCell1_UL_FROM (_menhir_stack, _) = _menhir_stack in
              let _ = _menhir_action_22 () in
              (match (_tok : MenhirBasics.token) with
              | UL_ACCFER ->
                  let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
                  let _v = _menhir_action_16 () in
                  _menhir_goto_machine _menhir_stack _v
              | _ ->
                  _eRR ())
          | _ ->
              _eRR ())
      | _ ->
          _eRR ()
  
  let rec _menhir_run_37 : type  ttv_stack. ttv_stack -> _ -> _ -> (ttv_stack, _menhir_box_machine) _menhir_state -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _menhir_s ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | UL_IDENT _ ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          let _v = _menhir_action_19 () in
          _menhir_goto_nom_qualifie _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _eRR ()
  
  and _menhir_goto_nom_qualifie : type  ttv_stack. ttv_stack -> _ -> _ -> _ -> (ttv_stack, _menhir_box_machine) _menhir_state -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      match _menhir_s with
      | MenhirState41 ->
          _menhir_run_42 _menhir_stack _menhir_lexbuf _menhir_lexer _tok
      | MenhirState36 ->
          _menhir_run_40 _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok
      | _ ->
          _menhir_fail ()
  
  and _menhir_run_40 : type  ttv_stack. ((ttv_stack _menhir_cell0_UL_IDENT, _menhir_box_machine) _menhir_cell1_UL_FROM as 'stack) -> _ -> _ -> _ -> ('stack, _menhir_box_machine) _menhir_state -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer _v _menhir_s _tok ->
      let _menhir_stack = MenhirCell1_nom_qualifie (_menhir_stack, _menhir_s, _v) in
      match (_tok : MenhirBasics.token) with
      | UL_TO ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          (match (_tok : MenhirBasics.token) with
          | UL_PT ->
              _menhir_run_37 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState41
          | UL_IDENT _ ->
              let _tok = _menhir_lexer _menhir_lexbuf in
              let _ = _menhir_action_18 () in
              _menhir_run_42 _menhir_stack _menhir_lexbuf _menhir_lexer _tok
          | _ ->
              _eRR ())
      | _ ->
          _eRR ()
  
  let rec _menhir_run_00 : type  ttv_stack. ttv_stack -> _ -> _ -> _menhir_box_machine =
    fun _menhir_stack _menhir_lexbuf _menhir_lexer ->
      let _tok = _menhir_lexer _menhir_lexbuf in
      match (_tok : MenhirBasics.token) with
      | UL_MACHINE ->
          let _tok = _menhir_lexer _menhir_lexbuf in
          (match (_tok : MenhirBasics.token) with
          | UL_IDENT _v ->
              let _tok = _menhir_lexer _menhir_lexbuf in
              (match (_tok : MenhirBasics.token) with
              | UL_ACCOUV ->
                  let _tok = _menhir_lexer _menhir_lexbuf in
                  (match (_tok : MenhirBasics.token) with
                  | UL_REGION ->
                      let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v) in
                      _menhir_run_04 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState03
                  | UL_IDENT _v_0 ->
                      let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v) in
                      let _menhir_stack = MenhirCell1_UL_IDENT (_menhir_stack, MenhirState03, _v_0) in
                      let _tok = _menhir_lexer _menhir_lexbuf in
                      (match (_tok : MenhirBasics.token) with
                      | UL_EVENT ->
                          let _tok = _menhir_lexer _menhir_lexbuf in
                          (match (_tok : MenhirBasics.token) with
                          | UL_IDENT _ ->
                              let _tok = _menhir_lexer _menhir_lexbuf in
                              let _ = _menhir_action_13 () in
                              (match (_tok : MenhirBasics.token) with
                              | UL_ACCFER ->
                                  let MenhirCell1_UL_IDENT (_menhir_stack, _, _) = _menhir_stack in
                                  let MenhirCell0_UL_IDENT (_menhir_stack, _) = _menhir_stack in
                                  let _v = _menhir_action_15 () in
                                  _menhir_goto_machine _menhir_stack _v
                              | _ ->
                                  _eRR ())
                          | _ ->
                              _eRR ())
                      | _ ->
                          _eRR ())
                  | UL_FROM ->
                      let _menhir_stack = MenhirCell0_UL_IDENT (_menhir_stack, _v) in
                      let _menhir_stack = MenhirCell1_UL_FROM (_menhir_stack, MenhirState03) in
                      let _tok = _menhir_lexer _menhir_lexbuf in
                      (match (_tok : MenhirBasics.token) with
                      | UL_PT ->
                          _menhir_run_37 _menhir_stack _menhir_lexbuf _menhir_lexer MenhirState36
                      | UL_IDENT _ ->
                          let _tok = _menhir_lexer _menhir_lexbuf in
                          let _v = _menhir_action_18 () in
                          _menhir_run_40 _menhir_stack _menhir_lexbuf _menhir_lexer _v MenhirState36 _tok
                      | _ ->
                          _eRR ())
                  | UL_ACCFER ->
                      let _v = _menhir_action_14 () in
                      _menhir_goto_machine _menhir_stack _v
                  | _ ->
                      _eRR ())
              | _ ->
                  _eRR ())
          | _ ->
              _eRR ())
      | _ ->
          _eRR ()
  
end

let machine =
  fun _menhir_lexer _menhir_lexbuf ->
    let _menhir_stack = () in
    let MenhirBox_machine v = _menhir_run_00 _menhir_stack _menhir_lexbuf _menhir_lexer in
    v

# 61 "Parser.mly"
  

# 656 "Parser.ml"
