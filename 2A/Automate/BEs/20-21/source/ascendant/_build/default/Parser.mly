%{

(* Partie recopiee dans le fichier CaML genere. *)
(* Ouverture de modules exploites dans les actions *)
(* Declarations de types, de constantes, de fonctions, d'exceptions exploites dans les actions *)

%}

/* Declaration des unites lexicales et de leur type si une valeur particuliere leur est associee */

%token UL_MACHINE
%token UL_ACCOUV UL_ACCFER
%token UL_PT 
%token UL_EVENT UL_REGION UL_STATE UL_FROM UL_TO UL_ON UL_STARTS UL_ENDS 

/* Defini le type des donnees associees a l'unite lexicale */

%token <string> UL_IDENT

/* Unite lexicale particuliere qui represente la fin du fichier */

%token UL_FIN

/* Type renvoye pour le nom terminal document */
%type <unit> machine

/* Le non terminal document est l'axiome */
%start machine

%% /* Regles de productions */

machine : UL_MACHINE UL_IDENT UL_ACCOUV UL_ACCFER { (print_endline "machine : MACHINE IDENT ACCOUV ACCFER") }
        | UL_MACHINE UL_IDENT UL_ACCOUV UL_IDENT event UL_ACCFER { (print_endline "machine : MACHINE IDENT ACCOUV IDENT event ACCFER") }
        | UL_MACHINE UL_IDENT UL_ACCOUV transition UL_ACCFER { (print_endline "machine : MACHINE IDENT ACCOUV transition ACCFER") }
        | UL_MACHINE UL_IDENT UL_ACCOUV region UL_ACCFER { (print_endline "machine : MACHINE IDENT ACCOUV region ACCFER") }

event : UL_EVENT UL_IDENT { (print_endline "event : EVENT IDENT") }

transition : UL_FROM nom_qualifie UL_TO nom_qualifie UL_ON UL_IDENT { (print_endline "transition : FROM nom_qualifie TO nom_qualifie ON IDENT") }

nom_qualifie : UL_IDENT { (print_endline "nom_qualifie : IDENT") } | UL_PT UL_IDENT { (print_endline "nom_qualifie : PT IDENT") }
        
region : UL_REGION UL_IDENT UL_ACCOUV etat UL_ACCFER { (print_endline "region : REGION IDENT ACCOUV etat ACCFER") }
        | UL_REGION UL_IDENT UL_ACCOUV UL_ACCFER { (print_endline "region : REGION IDENT ACCOUV ACCFER") }

etat : UL_STATE UL_IDENT UL_STARTS { (print_endline "etat : STATE IDENT STARTS") }
        | UL_STATE UL_IDENT UL_ENDS { (print_endline "etat : STATE IDENT ENDS") }
        | UL_STATE UL_IDENT { (print_endline "etat : STATE IDENT") }
        | UL_STATE UL_IDENT UL_STARTS UL_ENDS { (print_endline "etat : STATE IDENT STARTS ENDS") }
        | UL_STATE UL_IDENT UL_STARTS UL_ACCOUV UL_ACCFER { (print_endline "etat : STAT IDENT STARTS ACCOUV ACCFER") }
        | UL_STATE UL_IDENT UL_STARTS UL_ACCOUV region UL_ACCFER { (print_endline "etat : STAT IDENT STARTS ACCOUV region ACCFER") }
        | UL_STATE UL_IDENT UL_ENDS UL_ACCOUV UL_ACCFER { (print_endline "etat : STATE IDENT ENDS ACCOUV ACCFER") }
        | UL_STATE UL_IDENT UL_ENDS UL_ACCOUV region UL_ACCFER { (print_endline "etat : STATE IDENT ENDS ACCOUV region ACCFER") }
        | UL_STATE UL_IDENT UL_STARTS UL_ENDS UL_ACCOUV UL_ACCFER { (print_endline "etat : STATE IDENT STARTS ENDS ACCOUV ACCFER") }
        | UL_STATE UL_IDENT UL_STARTS UL_ENDS UL_ACCOUV region UL_ACCFER { (print_endline "etat : STATE IDENT STARTS ENDS ACCOUV region ACCFER") }
        | UL_STATE UL_IDENT UL_ACCOUV UL_ACCFER { (print_endline "etat : STATE IDENT ACCOUV ACCFER") }
        | UL_STATE UL_IDENT UL_ACCOUV region UL_ACCFER { (print_endline "etat : STATE IDENT ACCOUV region ACCFER") }



%%
