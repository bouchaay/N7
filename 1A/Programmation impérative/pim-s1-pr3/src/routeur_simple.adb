------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Routeur simple (sans cache)					|
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO;     		use Ada.Text_IO;
with Ada.Integer_Text_IO;    	use Ada.Integer_Text_IO;
with Ada.Strings.Unbounded; 	use Ada.Strings.Unbounded;
with Exceptions;	 	use Exceptions;
with Ada.Strings;               use Ada.Strings;
with Ada.Text_IO.Unbounded_IO;  use Ada.Text_IO.Unbounded_IO;
with Ada.Exceptions;            use Ada.Exceptions;	-- pour Exception_Messagebounded_IO;
with IP_ADRESS;			use IP_ADRESS;
with TABLE_ROUTAGE;		use TABLE_ROUTAGE;
with CLI;			use CLI;
with Ada.Command_Line; use Ada.Command_Line;


procedure routeur_simple is

    
        
      -- Commencer le traitement des commandes du fichier de paquetage In_Package_File.

      In_Package_File : File_Type; -- Fichier de paquetage.
      Out_Result_File : File_Type; -- Fichier Resultat.
      Table_Data_File : File_Type; -- Fichiers des donnees pour initialiser la table de routage.
      Size_Cache : Integer; -- Taille du cache.
      Show_Stats : Boolean; -- Affichage des statistiques.
      Politique : Unbounded_string; -- Definir la politique du cache.
      Name_File_Results: Unbounded_string; -- Nom du fichier resultat.
      Name_File_Package: Unbounded_string; -- Nom du fichier de paquetage.
      Name_File_Table: Unbounded_string; -- Nom du fichiers des donnees pour la table de routage.
      Commande : unbounded_String; -- Commandes dans le fichier de paquetage.
      Numero_Ligne : Integer; -- Numero de la ligne de commande dans le fichier de paquetage.
      Table : LC_TABLE.T_LC; -- Table de routage.
      IP : T_Adresse_Ip; -- Adresse IP.
      Command_End : Boolean; -- Fin du fichier paquetage.

begin

    Argument_Parsing(Ada.Command_Line.Argument_Count, Size_Cache, Politique, Show_Stats, Name_File_Table, Name_File_Package, Name_File_Results);

    Create(Out_Result_File, Out_File, To_string(Name_File_Results)); -- Creer le fichier resultat.
    Open(In_Package_File, In_File, To_string(Name_File_Package)); -- Ouvrir le fichier de paquetage.

    Open(Table_Data_File, In_File, To_String(Name_File_Table)); -- Ouvrir le fichier des donnees pour initialiser la table de routage. 
    Initialiser_Table(Table, Table_Data_File);
    close(Table_Data_File); -- Fermer le fichier de la table de routage.

    Command_End := False;

    while not End_Of_File(In_Package_File) and then not Command_End loop

      begin
        Numero_ligne := Integer(Line(In_Package_File));
        IP := Get_IP(In_Package_File); -- Recuperer l'adresse IP du fichier de paquetage.
        Show_IP(IP); New_Line; -- Afficher l'adresse IP sous la forme "999.999.999.999".
        Put(Out_Result_File, Compare_Table(Table, IP)); -- Recuperer l'interface de sortie de l'adresse IP et l'inserer avec cette derniere dans le fichier resultat.
        New_Line(Out_Result_File); -- Retour a la ligne.
        Skip_Line(In_Package_File); -- Passer a la ligne suivante.

      exception
          when DATA_ERROR => -- la ligne courante du fichier du paquetage n'a pas la forme d'une adresse IP.
	          Commande := Get_line(In_Package_File); -- Recuperer la commande.
	          Trim(Commande,both);
            if Commande = "TABLE" then
              Show_Table(Table,Numero_ligne); -- Afficher la table de routage.
            elsif Commande = "QUIT" then
              Command_End := true; -- Quitter le programme et fin du routage.
            else
              Put_Line("Commande inconnue à la ligne " & Integer'Image(Numero_ligne));
            end if;
        when E : others =>
          Put_Line("Syntax Error In The In_Package_File In The Line : " & Integer'Image(Numero_Ligne));
          Put_Line("Error : " & Exception_Message(E));
          raise Format_Error;

      end;
    end loop;
   close(Out_Result_File);
   close(In_Package_File);
   Vider_Table(Table);
   put("Fin du routage");
end routeur_simple;
