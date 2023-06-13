------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Spécification du module CLI 					|
------------------------------------------------------------------------------------------------*		

with Ada.Command_Line; use Ada.Command_Line;
with Ada.Strings.Unbounded;    use Ada.Strings.Unbounded;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;



package CLI is

	-- Cette procédure affiche le guide d'utilisation de l'application.
	
	procedure User_Guide;


	-- Cette procédure permet de parser les arguments de la ligne de commande et de les stocker dans les variables appropriées.
	-- Argument_Count : le nombre d'arguments passés en ligne de commande.
	-- Cache_Size : la taille du cache (sortie).
	-- Policy : la politique utilisée par le cache (sortie).
	-- Stat : True si les statistiques doivent être affichées, False sinon (sortie).
	-- Data_File_Name : le nom du fichier contenant les routes de la table de routage (sortie).
	-- In_Package_File_Name : le nom du fichier contenant les paquets à router (sortie).
	-- Out_Result_File_Name : le nom du fichier contenant les résultats de routage (sortie).
	 
	procedure Argument_Parsing(
        	Argument_Count : 	in Integer;
        	Cache_Size :     	out Integer;
        	Policy :         	out Unbounded_String;
        	Stat :           	out Boolean;
        	Data_File_Name :     	out Unbounded_String;
        	In_Package_File_Name : 	out Unbounded_String;
		Out_Result_File_Name : 	out Unbounded_String);

end CLI;

