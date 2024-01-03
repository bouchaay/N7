{

  open TokenJava
(*  open String *)
(*  open Str *)
  exception LexicalError

}

(* Macro-definitions *)
let minuscule = ['a'-'z']
let majuscule = ['A'-'Z']
let chiffre = ['0'-'9']
let chiffreNonNul = ['1'-'9']
let chiffreEtUnderscore = chiffre | '_'
let integerTypeSuffix = ['l' 'L']

(* --------------------- LES NOMBRES ENTIERS --------------------- *)
(* Nombre entier decimal *)
let decimalIntegerLiteral = ('0' | chiffreNonNul chiffre* | chiffreNonNul chiffreEtUnderscore* chiffre) integerTypeSuffix?
(*Nombre hexadécimal *)
let hexDigit = ['0'-'9' 'a'-'f' 'A'-'F']
let hexDigitEtUnderscore = hexDigit | '_'
let hexIntegerLiteral = "0x" hexDigit hexDigitEtUnderscore* hexDigit integerTypeSuffix?
(* Nombre octal *)
let octalDigit = ['0'-'7']
let octalDigitEtUnderscore = octalDigit | '_'
let octalIntegerLiteral = "0" octalDigitEtUnderscore* octalDigit integerTypeSuffix?
(* Nombre binaire *)
let binaryDigit = ['0'-'1']
let binaryDigitEtUnderscore = binaryDigit | '_'
let binaryIntegerLiteral = "0" ['b' 'B'] binaryDigit binaryDigitEtUnderscore* binaryDigit integerTypeSuffix?

(* --------------------- LES NOMBRES FLOTTANTS --------------------- *)
let exponentIndicator = ['e' 'E']
let sign = ['+' '-']
let floatTypeSuffix = ['f' 'F' 'd' 'D']
let exponentPart = exponentIndicator sign? decimalIntegerLiteral
(*DecimalFloatingPointLiteral*)
let floatingPointLiteral = (decimalIntegerLiteral '.' decimalIntegerLiteral? exponentPart? floatTypeSuffix?) | (decimalIntegerLiteral exponentPart? floatTypeSuffix?) | ('.' decimalIntegerLiteral exponentPart? floatTypeSuffix?) | (decimalIntegerLiteral exponentPart floatTypeSuffix?) | (decimalIntegerLiteral exponentPart? floatTypeSuffix)
(*HexadecimalFloatingPointLiteral*)
let hexSignificand = hexNumeral ('.' hexNumeral?)? | "0" [xX] hexDigit hexDigitEtUnderscore* '.' hexDigit hexDigitEtUnderscore*?
let binaryExponentIndicator = ['p' 'P'] sign? decimalIntegerLiteral
let HexadecimalFloatingPointLiteral = hexSignificand binaryExponentIndicator floatTypeSuffix?

(* --------------------- LES CARACTERES --------------------- *)
let octalEscape = octalDigit | octalDigit octalDigit | ['0'-'3'] octalDigit octalDigit
let escapeSequence = ['b' 't' 'n' 'f' 'r' '"' ''' '\'] | octalEscape
let carac = ^['\' '''] | '\' escapeSequence

let alphabet = minuscule | majuscule
let alphanum = alphabet | chiffre | '_'
let commentaireBloc = "/*" [^'*']* '*'*+ [^'/' '*']* '*'*+ '/'
let commentaireLigne = "//" [^'\n']* '\n'

(* Analyseur lexical : expression reguliere { action CaML } *)
rule lexer = parse
(* Espace, tabulation, passage a ligne, etc : consommes par l'analyse lexicale *)
  | ['\n' '\t' ' ']+    { lexer lexbuf }
(* Commentaires consommes par l'analyse lexicale *)
  | commentaireBloc  	{ lexer lexbuf }
  | commentaireLigne	{ lexer lexbuf }
(* Structures de blocs *)
  | "("                 { PAROUV }
  | ")"                 { PARFER }
  | "["                 { CROOUV }
  | "]"                 { CROFER }
  | "{"                 { ACCOUV }
  | "}"                 { ACCFER }
(* Separateurs *)
  | ","                 { VIRG }
  | ";"                 { PTVIRG }
(* Operateurs booleens *)
  | "||"                { OPOU }
  | "&&"                { OPET }
  | "!"                 { OPNON }
(* Operateurs comparaisons *)
  | "=="                { OPEG }
  | "!="                { OPNONEG }
  | "<="                { OPSUPEG }
  | "<"                 { OPSUP }
  | ">="                { OPINFEG }
  | ">"                 { OPINF }
(* Operateurs arithmetiques *)
  | "+"                 { OPPLUS }
  | "-"                 { OPMOINS }
  | "*"                 { OPMULT }
  | "/"                 { OPDIV }
  | "%"                 { OPMOD }
  | "."                 { OPPT }
  | "="                 { ASSIGN }
  | "new"               { NOUVEAU }
(* Mots cles : types *)
  | "bool"              { BOOL }
  | "char"              { CHAR }
  | "float"             { FLOAT }
  | "int"               { INT }
  | "String"            { STRING }
  | "void"              { VOID }
(* Mots cles : instructions *)
  | "while"		{ TANTQUE }
  | "if"		{ SI }
  | "else"		{ SINON }
  | "return"		{ RETOUR }
(* Mots cles : constantes *)
  | "true"		{ (BOOLEEN true) }
  | "false"		{ (BOOLEEN false) }
  | "null"		{ VIDE }
(* Nombres entiers *)
  | decimalIntegerLiteral | octalIntegerLiteral | hexIntegerLiteral | binaryIntegerLiteral as texte   { (ENTIER (int_of_string texte)) }
(* Nombres flottants *)
  | floatingPointLiteral | HexadecimalFloatingPointLiteral as texte     { (FLOTTANT (float_of_string texte)) }
(* Caracteres *)
  | carac as texte                   { CARACTERE texte.[1] }
(* Chaines de caracteres : A COMPLETER *)
  | '"' _* '"' as texte                  { CHAINE texte }
(* Identificateurs *)
  | majuscule alphanum* as texte              { TYPEIDENT texte }
  | minuscule alphanum* as texte              { IDENT texte }
  | eof                                       { FIN }
  | _                                         { raise LexicalError }

{

}
