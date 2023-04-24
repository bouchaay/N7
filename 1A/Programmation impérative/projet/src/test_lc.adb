------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Test du module LC						|
------------------------------------------------------------------------------------------------*	

with LC;
with Ada.Text_IO; use Ada.Text_IO;
with Ada.Unchecked_Deallocation;
with Exceptions;         use Exceptions;

procedure Test_LC is
   
   package Int_LC is new LC(T_Cle => Integer, T_Donnee => Integer);
   use Int_LC;

   TRT : Int_LC.T_LC;
   Key : Integer;
   Value : Integer;
   Cle_1, Cle_2, Cle_3 : Integer;
   Donnee_1, Donnee_2, Donnee_3 : Integer;

   procedure Test_Initialiser is
   begin
      Initialiser(TRT);
      Pragma Assert(Est_Vide(TRT), "Initialiser test failed.");
   end Test_Initialiser;

   procedure Test_Enregistrer is
   begin
      Key := 1;
      Value := 10;
      Enregistrer(TRT, Key, Value);
      Pragma Assert(Cle_Presente(TRT, Key), "Enregistrer test failed.");
      Pragma Assert(La_Donnee(TRT, Key) = Value, "Enregistrer test failed.");
   end Test_Enregistrer;

   procedure Test_Supprimer is
   begin
      Supprimer(TRT, Key);
      Pragma Assert(not Cle_Presente(TRT, Key), "Supprimer test failed.");
   end Test_Supprimer;

   procedure Test_Vider is
   begin
      Enregistrer(TRT, 1, 10);
      Enregistrer(TRT, 2, 20);
      Enregistrer(TRT, 3, 30);
      Vider(TRT);
      Pragma Assert(Est_Vide(TRT), "Vider test failed.");
   end Test_Vider;

   procedure Test_Enregistrer_Fin is
   begin
      Enregistrer_Fin(TRT, 1, 10);
      Enregistrer_Fin(TRT, 2, 20);
      Enregistrer_Fin(TRT, 3, 30);
      Pragma Assert(La_Donnee(TRT, 3) = 30, "Enregistrer_Fin test failed.");
   end Test_Enregistrer_Fin;

   procedure Test_Suppression_Haute is
   begin
      Suppression_Haute(TRT);
      Pragma Assert(La_Donnee(TRT,2) = 20, "Suppression_Haute test failed.");
      Vider(TRT);
   end Test_Suppression_Haute;

   procedure Test_Element_en_Indice is
   begin
      Initialiser(TRT);
      Key := 1;
      Value := 10;
      Enregistrer(TRT, Key, Value);
      Key := 2;
      Value := 20;
      Enregistrer(TRT, Key, Value);
      Key := 3;
      Value := 30;
      Enregistrer(TRT, Key, Value);

      Element_en_Indice(TRT, 1, Cle_1, Donnee_1);
      Pragma Assert(Cle_1 = 1 and Donnee_1 = 10, "Element_en_Indice test failed.");

      Element_en_Indice(TRT, 2, Cle_2, Donnee_2);
      Pragma Assert(Cle_2 = 2 and Donnee_2 = 20, "Element_en_Indice test failed.");

      Element_en_Indice(TRT, 3, Cle_3, Donnee_3);
      Pragma Assert(Cle_3 = 3 and Donnee_3 = 30, "Element_en_Indice test failed.");
   end Test_Element_en_Indice;
   

begin
   Put_Line("***** DEBUT DU TEST *****");
   Test_Initialiser;
   Test_Enregistrer;
   Test_Supprimer;
   Test_Vider;
   Test_Enregistrer_Fin;
   Test_Suppression_Haute;
   Test_Element_en_Indice;
   Put_Line("***** FIN DU TEST *****");
end Test_LC;

