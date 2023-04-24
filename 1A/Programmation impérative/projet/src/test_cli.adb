------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Test du module CLI 						|
------------------------------------------------------------------------------------------------*	

with CLI; use CLI;
with Ada.Text_IO;           use Ada.Text_IO;
with Exceptions; 		use Exceptions;
with Ada.Text_IO.Unbounded_IO;   use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Unbounded;    use Ada.Strings.Unbounded;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;


procedure Test_CLI is
   -- Test de la fonction User_Guide
   procedure Test_User_Guide is
   begin
      Put_Line("Test de la fonction User_Guide:");
      User_Guide;
   end Test_User_Guide;

   -- Test de la fonction Argument_Parsing
   procedure Test_Argument_Parsing is
      Argument_Count : constant Integer := 6;
      Cache_Size : Integer;
      Policy : Unbounded_String;
      Stat : Boolean;
      Data_File_Name : Unbounded_String;
      In_Package_File_Name : Unbounded_String;
      Out_Result_File_Name : Unbounded_String;
   begin
      Put_Line("Test de la fonction Argument_Parsing:");
      Argument_Parsing(Argument_Count, Cache_Size, Policy, Stat, Data_File_Name, In_Package_File_Name, Out_Result_File_Name);
      Put("Cache_Size: ");
      Put(Cache_Size);
      Put_Line(" | Policy: " & Policy & " | Stat: " & Boolean'Image(Stat) & " | Data_File_Name: " & Data_File_Name & " | In_Package_File_Name: " & In_Package_File_Name & " | Out_Result_File_Name: " & Out_Result_File_Name);
   end Test_Argument_Parsing;
begin
   Put_Line("***** DEBUT DU TEST *****");
   Test_User_Guide;
   Test_Argument_Parsing;
   Put_Line("***** FIN DU TEST *****");
end Test_CLI;
