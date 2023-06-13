with Ada.Command_Line; use Ada.Command_Line;
with Ada.Strings.Unbounded;    use Ada.Strings.Unbounded;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;

package CLI is

    -- Procedure d'affichage du guide d'utilisation
    procedure UserGuide;

    -- Procedure de parsing des arguments
    procedure ParseCmdLine (Mot : out Unbounded_String; Fichier : out Unbounded_String);

end CLI;