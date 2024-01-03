(*  Type enumération représentant les robots *)
type robot = Rrouge | Rvert | Rbleu | Rjaune | Rnoir

(*  Type enumération représentant les directions de déplacement possible pour les robots *)
type dir = Dhaut | Dgauche | Dbas | Ddroite

(*  Type des plateaux. *)
type t

(*  Un exemple de plateau (celui de la Figure 1 du sujet). *)
val exemple : t

(*  [pas_de_mur plateau (x, y) dir] retourne vrai ssi il n'y a pas de
   mur sur sur le carré [(x, y)] de [plateau] dans la direction [dir].
   [x] et [y] doivent être positifs ou nul et inférieurs à [dim
   plateau]. Les bords du plateau sont considérés comme des murs. Par
   exemple [pas_de_mur plateau (0, 0) Dgauche] retourne [false]. *)
val pas_de_mur : t -> int * int -> dir -> bool

(*  Quelques autres fonctions que vous n'avez pas besoin d'utiliser. *)

(*  Constructeur. [make dim murs cibles] crée un plateau de taille
   [dim] x [dim] avec les murs et cibles données (des murs sont
   automatiquement ajoutés sur les quatre bords du plateau, pas besoin
   de les lister). *)
val make : int -> Wall.wall list -> Wall.target list -> t

(*  Destructeurs. *)

(*  dim retourne la dimension du plateau *)
val dim : t -> int

(*  walls retourne la liste de murs présents sur le plateau *)
val walls : t -> Wall.wall list

(*  targets retourne la liste des cibles présentes sur le plateau *)
val targets : t -> Wall.target list

(*  Fonctions d'affichage. *)

(*  char_of_robot converti un robot en caractère le représentant pour l'affichage*)
val char_of_robot : robot -> char

(*  char_of_dir converti une direction en caractère la représentant pour l'affichage*)
val char_of_dir : dir -> char

(*  to_string converti un plateau et une liste de robot positionnés dessus en une chaine de caractères *)
val to_string : t -> (robot * (int * int)) list -> string
