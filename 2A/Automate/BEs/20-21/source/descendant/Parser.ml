open Tokens

(* Type du résultat d'une analyse syntaxique *)
type parseResult =
  | Success of inputStream
  | Failure
;;

(* accept : token -> inputStream -> parseResult *)
(* Vérifie que le premier token du flux d'entrée est bien le token attendu *)
(* et avance dans l'analyse si c'est le cas *)
let accept expected stream =
  match (peekAtFirstToken stream) with
    | token when (token = expected) ->
      (Success (advanceInStream stream))
    | _ -> Failure
;;

(* Définition de la monade  qui est composée de : *)
(* - le type de donnée monadique : parseResult  *)
(* - la fonction : inject qui construit ce type à partir d'une liste de terminaux *)
(* - la fonction : bind (opérateur >>=) qui combine les fonctions d'analyse. *)

(* inject inputStream -> parseResult *)
(* Construit le type de la monade à partir d'une liste de terminaux *)
let inject s = Success s;;

(* parseIdent : inputStream -> parseResult *)
(* Analyse du non terminal ident *)
let parseIdent stream = match (peekAtFirstToken stream) with
  | UL_IDENT _ -> Success (advanceInStream stream)
  | _ -> Failure

(* bind : 'a m -> ('a -> 'b m) -> 'b m *)
(* bind (opérateur >>=) qui combine les fonctions d'analyse. *)
(* ici on utilise une version spécialisée de bind :
   'b  ->  inputStream
   'a  ->  inputStream
    m  ->  parseResult
*)
(* >>= : parseResult -> (inputStream -> parseResult) -> parseResult *)
let (>>=) result f =
  match result with
    | Success next -> f next
    | Failure -> Failure
;;

(* parseMachine : inputStream -> parseResult *)
(* Analyse du non terminal Programme *)
let rec parseMachine stream =
  (print_string "Machine -> ");
  (match (peekAtFirstToken stream) with
    | UL_MACHINE ->
      (print_endline "machine ident { Sc }"); inject stream >>= accept UL_MACHINE >>= parseIdent >>= accept UL_ACCOUV >>= parseSc >>= accept UL_ACCFER
    | _ -> Failure)

    (* parseSc : inputStream -> parseResult *)
    (* Analyse du non terminal Sc *)
and parseSc stream =
  (print_string "Sc -> ");
  (match (peekAtFirstToken stream) with
    | UL_ACCFER -> (print_endline ""); inject stream
    | UL_EVENT | UL_FROM | UL_REGION -> (print_endline "C Sc"); inject stream >>= parseC >>= parseSc
    | _ -> Failure)

(* parseC : inputStream -> parseResult *)
(* Analyse du non terminal C *)
and parseC stream =
  (print_string "C -> ");
  (match (peekAtFirstToken stream) with
    | UL_EVENT -> (print_endline "event ident"); inject stream >>= accept UL_EVENT >>= parseIdent
    | UL_FROM -> (print_endline "from Nq to Nq on ident"); inject stream >>= accept UL_FROM >>= parseNq >>= accept UL_TO >>= parseNq >>= accept UL_ON >>= parseIdent
    | UL_REGION -> (print_endline "R"); inject stream >>= parseR
    | _ -> Failure)

(* parseNq : inputStream -> parseResult *)
(* Analyse du non terminal Nq *)
and  parseNq stream =
  (print_string "Nq -> ");
  (match (peekAtFirstToken stream) with
    | UL_IDENT _ -> (print_endline "ident Sq"); inject stream >>= parseIdent >>= parseSq
    | _ -> Failure)

(* parseSq : inputStream -> parseResult *)
(* Analyse du non terminal Sq *)
and parseSq stream =
  (print_string "Sq -> ");
  (match (peekAtFirstToken stream) with
    | UL_TO | UL_ON -> (print_endline ""); inject stream
    | UL_PT -> (print_endline ". ident Sq"); inject stream >>= accept UL_PT >>= parseIdent >>= parseSq
    | _ -> Failure)

(* parseE : inputStream -> parseResult *)
(* Analyse du non terminal E *)
and parseR stream =
  (print_string "R -> ");
  (match (peekAtFirstToken stream) with
    | UL_REGION -> (print_endline "region ident { E Se }"); inject stream >>= accept UL_REGION >>= parseIdent >>= accept UL_ACCOUV >>= parseE >>= parseSe >>= accept UL_ACCFER
    | _ -> Failure)

(* parseE : inputStream -> parseResult *)
(* Analyse du non terminal E *) 
and parseSe stream =
  (print_string "Se -> ");
  (match (peekAtFirstToken stream) with
    | UL_ACCFER -> (print_endline ""); inject stream
    | UL_STATE -> (print_endline "E Se"); inject stream >>= parseE >>= parseSe
    | _ -> Failure)

(* parseE : inputStream -> parseResult *)
(* Analyse du non terminal E *)
and parseE stream =
  (print_string "E -> ");
  (match (peekAtFirstToken stream) with
    | UL_STATE -> (print_endline "state ident Es Ee Ec"); inject stream >>= accept UL_STATE >>= parseIdent >>= parseEs >>= parseEe >>= parseEc
    | _ -> Failure)

(* parseEs : inputStream -> parseResult *)
(* Analyse du non terminal Es *)
and parseEs stream =
  (print_string "Es -> ");
  (match (peekAtFirstToken stream) with
    | UL_ENDS | UL_ACCOUV | UL_ACCFER | UL_STATE -> (print_endline ""); inject stream
    | UL_STARTS -> (print_endline "starts"); inject stream >>= accept UL_STARTS
    | _ -> Failure)

(* parseEe : inputStream -> parseResult *)
(* Analyse du non terminal Ee *)
and parseEe stream =
  (print_string "Ee -> ");
  (match (peekAtFirstToken stream) with
    | UL_ACCOUV | UL_ACCFER | UL_STATE -> (print_endline ""); inject stream
    | UL_ENDS -> (print_endline "ends"); inject stream >>= accept UL_ENDS
    | _ -> Failure)

(* parseEc : inputStream -> parseResult *)
(* Analyse du non terminal Ec *)
and parseEc stream =
  (print_string "Ec -> ");
  (match (peekAtFirstToken stream) with
    | UL_ACCFER | UL_STATE -> (print_endline ""); inject stream
    | UL_ACCOUV -> (print_endline "{ R Sr }"); inject stream >>= accept UL_ACCOUV >>= parseR >>= parseSr >>= accept UL_ACCFER
    | _ -> Failure)

(* parseSr : inputStream -> parseResult *)
(* Analyse du non terminal Sr *)
and parseSr stream =
  (print_string "Sr -> ");
  (match (peekAtFirstToken stream) with
    | UL_ACCFER -> (print_endline ""); inject stream
    | UL_REGION -> (print_endline "R Sr"); inject stream >>= parseR >>= parseSr
    | _ -> Failure)
