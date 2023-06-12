with Ada.Command_Line; use Ada.Command_Line;
with Exceptions;           use Exceptions;
with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Text_IO.Unbounded_IO;   use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Unbounded;    use Ada.Strings.Unbounded;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;


package body CLI is

    procedure UserGuide is
    begin
        Put_Line("Usage: ./main <mot> <nom du fichier>");
        Put_Line("mot: mot a rechercher");
        Put_Line("nom du fichier: nom du fichier a analyser");
    end UserGuide;

    procedure ParseCmdLine (Mot : out Unbounded_String; Fichier : out Unbounded_String) is
        begin
            if Argument_Count < 2 then
                raise Usage_Error;
            end if;
            Mot := To_Unbounded_String(Argument(1));
            Fichier := To_Unbounded_String(Argument(2));
        exception
            when Usage_Error =>
                UserGuide;
                raise;
        end ParseCmdLine;
end CLI;