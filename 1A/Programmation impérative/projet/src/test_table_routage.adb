------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Test du module TABLE_ROUTAGE 					|
------------------------------------------------------------------------------------------------*	


with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with TABLE_ROUTAGE; use TABLE_ROUTAGE;
with Exceptions; use Exceptions;
with IP_ADRESS; use IP_ADRESS;
with Ada.Text_IO.Unbounded_IO; use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;

procedure Test_Table_Routage is
   
   Table : LC_TABLE.T_LC;
   IP : T_Adresse_IP;
   Destination : Unbounded_String;
   File_Table : File_Type;
   
   procedure Test_Initialiser_Table(Table : out LC_TABLE.T_LC) is
      -- Test de la fonction Initialiser_Table
   begin
      Open(File_Table, In_File, "table_routage_test.txt");
      Initialiser_Table(Table, File_Table);
      Close(File_Table);
   end Test_Initialiser_Table;

   procedure Test_Show_Table(Table : in LC_TABLE.T_LC) is
      -- Test de la fonction Show_Table
   begin
      Show_Table(Table,1);
   end Test_Show_Table;

   procedure Test_Compare_Table(Table : in LC_TABLE.T_LC ; IP : out T_Adresse_IP ; Destination : out Unbounded_String) is
      -- Test de la fonction Compare_Table
   begin
      IP := 16842752;
      Destination := Compare_Table(Table, IP);
      Put_Line("Destination trouvée : " & Destination);
   end Test_Compare_Table;
begin
   Put_Line("***** DEBUT DU TEST *****");
   Test_Initialiser_Table(Table);
   Test_Show_Table(Table);
   Test_Compare_Table(Table, IP, Destination);
   Put_Line("***** FIN DU TEST *****");
end Test_Table_Routage;
