------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Test du module Arbre						|		
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Unchecked_Deallocation;
with Arbre; use Arbre;
with Exceptions; use Exceptions;
with IP_ADRESS; use IP_ADRESS;
with Ada.Strings.Unbounded;    use Ada.Strings.Unbounded;
with Ada.Text_IO.Unbounded_IO; use Ada.Text_IO.Unbounded_IO;


procedure Test_Arbre is
   Tree : T_LA;
   Key : T_Adresse_IP;
   Value : Unbounded_String;
   Found : Boolean;

procedure Test_Initialiser is
begin
   -- Initialize the tree
   Initialiser(Tree);

   -- Check the size of the tree
   pragma Assert(Taille(Tree) = 0);
   Put_Line("Test_Initialiser : OK");
end Test_Initialiser;

procedure Test_Enregistrer is
begin
   -- Insert some key-value pairs into the tree
   Key := 123456;
   Value :=To_Unbounded_String( "Node 1");
   Enregistrer(0, Key, Value, Tree);

   Key := 234567;
   Value :=To_Unbounded_String( "Node 2");
   Enregistrer(0, Key, Value, Tree);

   Key := 345678;
   Value :=To_Unbounded_String( "Node 3");
   Enregistrer(0, Key, Value, Tree);

   -- Check the size of the tree
   pragma Assert(Taille(Tree) = 3);
   Put_Line("Test_Enregistrer : OK");
end Test_Enregistrer;

procedure Test_Traiter is
begin
   -- Search for a key in the tree
   Key := 234567;
   Traiter(0, Key, Value, Found, Tree);
   pragma Assert(Found and Value = To_Unbounded_String( "Node 2"));

   -- Search for a key that doesn't exist in the tree
   Key := 999999;
   Traiter(0, Key, Value, Found, Tree);
   pragma Assert(not Found);
   Put_Line("Test_Traiter : OK");
end Test_Traiter;

procedure Test_Supprimer is
begin
   -- Remove a key from the tree
   Key := 234567;
   Supprimer(0, Tree);
   pragma Assert(Taille(Tree) = 2);
   Put_Line("Test_Supprimer : OK");
end Test_Supprimer;

procedure Test_Afficher is
begin
   -- Display the tree
   Afficher(Tree);
   Put_Line("Test_Afficher : OK");
end Test_Afficher;

begin
   Put_Line("***** DEBUT DU TEST *****");
   Test_Initialiser;
   Test_Enregistrer;
   Test_Traiter;
   Test_Supprimer;
   Test_Afficher;
   Put_Line("***** FIN DU TEST *****");
end Test_Arbre;

