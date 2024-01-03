%token <int> INT
%token LPAR RPAR SEMICOL COMA DOT
%token DIM BARRIER AVAILABLETARGET ROBOT INITIALPOS
%token RED GREEN BLUE YELLOW BLACK ANY
%token MOON SATURN STAR SUN
%token EOF

%type <Be.Plateau.t * (Be.Plateau.robot * (int * int)) list> file
%start file

%%

file:
| board initial_pos EOF { $1, $2 }

dim:
| DIM LPAR INT DOT DOT INT RPAR DOT
    { if $3 <> 1 then raise (Invalid_argument "dimension should be given as \"dim(1..<n>).\"");
      if $6 > 16 then raise (Invalid_argument "dimension should be at most 16");
      $6 }

barrier:
| BARRIER LPAR INT COMA INT COMA INT COMA INT RPAR DOT
    { let x, y, dx, dy = $3, $5, $7, $9 in
      if (dx <> 0 && dy <> 0) || (dx = 0 && dy = 0) then raise (Invalid_argument "barriers should be either horizontal or vertical");
      if dx < 0 then x - 1, y - 1, Be.Wall.Wleft
      else if dx > 0 then x, y - 1, Be.Wall.Wleft
      else if dy < 0 then x - 1, y - 1, Be.Wall.Wtop
      else (* dy > 0 *) x - 1, y, Be.Wall.Wtop
    }

barriers:
| /* empty */ { [] }
| barrier barriers { $1 :: $2 }

target_color:
| RED { Be.Wall.Tred }
| GREEN { Be.Wall.Tgreen }
| BLUE { Be.Wall.Tblue }
| YELLOW { Be.Wall.Tyellow }
| ANY { Be.Wall.Tany }

target_type:
| MOON { Be.Wall.Tmoon }
| SATURN { Be.Wall.Tsaturn }
| STAR { Be.Wall.Tstar }
| SUN { Be.Wall.Tsun }

target:
| AVAILABLETARGET LPAR target_color COMA target_type COMA INT COMA INT RPAR DOT
    { $7 - 1, $9 - 1, $3, $5 }

targets:
| /* empty */ { [] }
| target targets { $1 :: $2 }

board:
| dim barriers targets { Be.Plateau.make $1 $2 $3 }

robot_color:
| RED { Be.Plateau.Rrouge }
| GREEN { Be.Plateau.Rvert }
| BLUE { Be.Plateau.Rbleu }
| YELLOW { Be.Plateau.Rjaune }
| BLACK { Be.Plateau.Rnoir }

robot_colors:
| robot_color { }
| robot_color SEMICOL robot_colors { }

robots:
| /* empty */ { }
| ROBOT LPAR robot_colors RPAR DOT { }

pos:
| INITIALPOS LPAR robot_color COMA INT COMA INT RPAR DOT
    { ($3, ($5 - 1, $7 - 1)) }

poss:
| /* empty */ { [] }
| pos poss { $1 :: $2 }

initial_pos:
| robots poss { $2 }
