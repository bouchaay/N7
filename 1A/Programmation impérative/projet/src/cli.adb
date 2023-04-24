------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Implantation du module CLI 					|
------------------------------------------------------------------------------------------------*	

with Ada.Command_Line; use Ada.Command_Line;
with Exceptions;           use Exceptions;
with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Text_IO.Unbounded_IO;   use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Unbounded;    use Ada.Strings.Unbounded;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;


package body CLI is


    procedure User_Guide is
    begin
        Put("User_Guide : ./routeur_simple <options>");
	New_Line;
        Put("Les options sont les suivantes :");
	New_Line;
        Put("  -c <taille> : Définir la taille du cache. La taille du cache est definie a 10 par defaut. Si la taille est 0, donc il n'y a pas de cache.");
	New_Line;
        Put("  -p FIFO|LRU|LFU : Définir la politique utilisée par le cache. La valeur par défaut est FIFO.");
        New_Line;
	Put("  -p <fichier> : Définir le nom du fichier contenant les paquets à router. Par défaut, on utilise le fichier paquets.txt.");
        New_Line;
	Put("  -s : Afficher les statistiques (nombre de défauts de cache, nombre de demandes de route, taux de défaut de cache). C'est l'option activée par défaut.");
	New_Line;
	Put("  -S : Ne pas afficher les statistiques.");
        New_Line;
	Put("  -t <fichier> : Définir le nom du fichier contenant les routes de la table de routage. Par défaut, on utilise le fichier table.txt.");
        New_Line;
	Put("  -r <fichier> : Définir le nom du fichier contenant les résultats (adresse IP destination du paquet et inter-face utilisée). Par défaut, on utilise le fichier resultats.txt.");
    end User_Guide;



    procedure Argument_Parsing(
        Argument_Count : 	in Integer;
        Cache_Size :     	out Integer;
        Policy :         	out Unbounded_String;
        Stat :           	out Boolean;
        Data_File_Name :     	out Unbounded_String;
        In_Package_File_Name : 	out Unbounded_String;
        Out_Result_File_Name : 	out Unbounded_String) is

        Counter : Integer := 1;
        Argument_Position : Unbounded_String;
              
    begin
        -- Initialise les valeurs par défaut.
        Cache_Size := 10;
        Policy := To_Unbounded_String("FIFO");
        Stat := True;
        Data_File_Name := To_Unbounded_String("table.txt");
        In_Package_File_Name := To_Unbounded_String("paquets.txt");
        Out_Result_File_Name := To_Unbounded_String("resultats.txt");

      if Argument_Count = 0 then
         null;    -- On quitte le programme
      else
         while Counter <= Argument_Count loop
            Argument_Position := To_Unbounded_String(Argument(Counter));
            if Argument_Position = "-c" then
                begin
                    Cache_Size := Integer'Value(Argument(Counter+1)); -- Modifier la taille du cache.
                exception
                    when others =>
                        Put_Line("-c doit etre suivi d'un entier. Erreur à la position " & Integer'Image(Counter+1));
                        User_Guide;
                        raise Invalid_Command;
                end;
                Counter := Counter + 2;
            elsif Argument_Position = "-p" then               
                if Counter+1 > Argument_Count then
                    Put_Line("-p necessite un argument à la position " & Integer'Image(Counter));
                    User_Guide;
                    raise Invalid_Command;
                end if;
                if Argument(Counter+1) = "FIFO" or Argument(Counter+1) = "LRU" or Argument(Counter+1) = "LFU" then
                    Policy := To_Unbounded_String(Argument(Counter+1)); -- Modifier la politique du cache.
                else
                    In_Package_File_Name := To_Unbounded_String(Argument(Counter+1)); -- Modifier le nom du fichier paquet contenant les IP a router.
                end if;
                Counter := Counter + 2;
            elsif Argument_Position = "-s" then
               Stat := True; -- Afficher les statistiques du cache.
               Counter := Counter + 1;
            elsif Argument_Position = "-S" then -- Masquer les statistiques du cache.
               Stat := False;
               Counter := Counter + 1;
            elsif Argument_Position = "-t" or Argument_Position = "-r" then
               if Counter + 1 > Argument_Count then
                  Put_Line(Argument(Counter) & " necessite un argument à la position " & Integer'Image(Counter));
                  User_Guide;
                  raise Invalid_Command;
               end if;
               if Argument_Position = "-t" then
                  Data_File_Name := To_Unbounded_String(Argument(Counter+1)); -- Modifier le du table.txt
               else
                  Out_Result_File_Name := To_Unbounded_String(Argument(Counter+1)); -- Modifier le nom du fichier resultats.txt
               end if;
               Counter := Counter + 2;
            else
                Put_Line("Argument inconnu : " & Argument(Counter));
                User_Guide;
                raise Invalid_Command;
            end if;
         end loop;
      end if;
    end Argument_Parsing;

end CLI;

