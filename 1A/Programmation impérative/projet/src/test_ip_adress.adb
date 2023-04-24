------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Test du module IP_ADRESS					|
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Integer_Text_IO; use Ada.Integer_Text_IO;
with IP_ADRESS; use IP_ADRESS;
with Exceptions; use Exceptions;
with Ada.Text_IO.Unbounded_IO; use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;

procedure Test_IP_Adress is
   procedure Test_Show_IP is
      -- Test de la fonction Show_IP
      IP : T_Adresse_IP;
   begin
      IP := 16842752;
      Show_IP(IP);
      Put_Line("(attendu : 1.01.0.0)");
   end Test_Show_IP;

   procedure Test_Add_IP is
      -- Test de la fonction Add_IP
      File_IP : File_Type;
      IP : T_Adresse_IP;
   begin
      Open(File_IP, Out_File, "ip_test.txt");
      IP := 16842752;
      Add_IP(File_IP, IP);
      Close(File_IP);
   end Test_Add_IP;

   procedure Test_Get_IP is
      -- Test de la fonction Get_IP
      File_IP : File_Type;
      IP : T_Adresse_IP;
   begin
      Open(File_IP, In_File, "ip_test.txt");
      IP := Get_IP(File_IP);
      Close(File_IP);

      -- VÃ©rification de la valeur de retour de Get_IP
      pragma Assert(IP = 16842752, "Erreur de lecture de l'adresse IP");
   end Test_Get_IP;
   

   procedure Test_To_T_Adresse_IP is
      Entier1, Entier2, Entier3, Entier4 : String := "192";
      IP : T_Adresse_IP;
   begin
      To_T_Adresse_IP(Entier1, Entier2, Entier3, Entier4, IP);
   -- Vérifier si IP est égal à l'adresse IP attendue
   pragma Assert(IP = 192, "To_T_Adresse_IP Test failed");
end Test_To_T_Adresse_IP;


begin
   Put_Line("***** DEBUT DU TEST *****");
   Test_Show_IP;
   Test_Add_IP;
   Test_Get_IP;
   Test_To_T_Adresse_IP;
   Put_Line("***** FIN DU TEST *****");
end Test_Ip_Adress;


