------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Routeur avec cache (Arbre)					|
------------------------------------------------------------------------------------------------*		

with Ada.Text_IO; 		use Ada.Text_IO;
with Ada.Integer_Text_IO;	use Ada.Integer_Text_IO;
with Ada.Strings.Unbounded; 	use Ada.Strings.Unbounded;
with Exceptions; 		use Exceptions;
with Ada.Strings; 		use Ada.Strings;
with Ada.Text_IO.Unbounded_IO; 	use Ada.Text_IO.Unbounded_IO;
with Ada.Exceptions; 		use Ada.Exceptions;
with IP_ADRESS; 		use IP_ADRESS;
with TABLE_ROUTAGE; 		use TABLE_ROUTAGE;
with CLI; 			use CLI;
with Cache_LA; 			use Cache_LA;
with Ada.Command_Line; use Ada.Command_Line;


procedure routeur_la is

  -- Fichiers de paquetage et de résultats.
  In_Package_File : File_Type;
  Out_Result_File : File_Type;
  Table_Data_File : File_Type;

  -- Variables de configuration.
  Size_Cache : Integer;
  Show_Stats : Boolean;
  Name_File_Results : Unbounded_String;
  Name_File_Package : Unbounded_String;
  Name_File_Table : Unbounded_String;

  -- Variables pour la gestion des commandes.
  Commande : Unbounded_String;
  Numero_Ligne : Integer;

  -- Variables pour la table de routage et l'adresse IP.
  Table : LC_TABLE.T_LC;
  IP : T_Adresse_IP;
  Command_End : Boolean;

  -- Variables pour le cache.
  Cache : T_Cache;
  Found : Boolean;
  IP_Interface : Unbounded_String;

  Politique : Unbounded_String := To_Unbounded_String("LRU");
begin
  -- Parser les arguments de la ligne de commande.
  Argument_Parsing(Ada.Command_Line.Argument_Count, Size_Cache, Politique, Show_Stats, Name_File_Table, Name_File_Package, Name_File_Results);

  -- Créer et ouvrir les fichiers de paquetage et de résultats.
  Create(Out_Result_File, Out_File, To_string(Name_File_Results));
  Open(In_Package_File, In_File, To_string(Name_File_Package));

  -- Initialiser la table de routage à partir du fichier de données.
  Open(Table_Data_File, In_File, To_string(Name_File_Table));
  Initialiser_Table(Table, Table_Data_File);
  Close(Table_Data_File);

  -- Initialiser le cache.
  Initialiser_Cache_LA(Cache, Size_Cache);

  Command_End := False;

  -- Traitement des commandes du fichier de paquetage.
  while not End_Of_File(In_Package_File) and then not Command_End loop
    begin
      -- Lire le numéro de ligne et l'adresse IP.
      Numero_Ligne := Integer(Line(In_Package_File));
      IP := Get_IP(In_Package_File);
      -- Vérifier si l'adresse IP est présente dans le cache, et l'enregistrer si c'est le cas.
      Lire_Cache_LA(Cache, IP, IP_Interface, Found);

      if Found then
        -- L'adresse IP est présente dans le cache, donc on l'utilise.
        Put(Out_Result_File, IP_Interface);
      else
        -- L'adresse IP n'est pas présente dans le cache, donc on la cherche dans la table de routage.
        Put(Out_Result_File, Compare_Table(Table, IP));
      end if;

      New_Line(Out_Result_File);
      -- Passer à la ligne suivante du fichier de paquetage.
      Skip_Line(In_Package_File);

   exception
   when DATA_ERROR =>
    -- La ligne courante du fichier de paquetage n'est pas une adresse IP valide.
    Commande := Get_Line(In_Package_File);
    Numero_Ligne := Integer(Line(In_Package_File));
    -- Exécuter la commande.
     if commande = "TABLE" then
       Show_Table(Table,Numero_ligne); -- Afficher la table de routage.
    elsif Commande = To_Unbounded_String("QUIT") then
      Command_End := True; -- Quitter le routage.
    elsif Commande = To_Unbounded_String("CACHE") then
      Afficher_Cache_LA(Cache); -- Afficher le cache.
    else
      Put_Line("Commande inconnue à la ligne " & Integer'Image(Numero_ligne)); -- Afficher le numero la ligne de la commande inconnue.
    end if;
   when E : others =>
      Put_Line("Syntax Error In The In_Package_File In The Line : " & Integer'Image(Numero_Ligne));
      Put_Line("Error : " & Exception_Message(E));
      raise Format_Error;

   end;
   end loop;
   if Show_Stats then
      Afficher_Statistiques_LA(Cache);
   end if;
   -- Fermer tous les fichiers.
   close(Out_Result_File);
   close(In_Package_File);
   Vider_Table(Table);
   put("Fin du routage");
end routeur_la;

