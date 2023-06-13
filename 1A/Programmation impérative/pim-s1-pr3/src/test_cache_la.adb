------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Test du module Cache_LA						|		
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;
with Ada.Text_IO.Unbounded_IO; use Ada.Text_IO.Unbounded_IO;
with IP_ADRESS; use IP_ADRESS;
with Cache_LA; use Cache_LA;

procedure Test_Cache_LA is
   Cache : Cache_LA.T_Cache;
   Key : T_Adresse_IP;
   Value : Unbounded_String;
   Found : Boolean;

procedure Test_Initialiser_Cache_LA is
begin
      Initialiser_Cache_LA(Cache, 2);
      pragma Assert(Taille_Cache_LA(Cache) = 0);
      pragma Assert(Visite_Noeud_LA(Cache) = 0);
      pragma Assert(Errors_LA(Cache) = 0);
   Put_Line("Test_Initialiser_Cache_LA : OK");
end Test_Initialiser_Cache_LA;

procedure Test_Afficher_Statistiques_LA is
begin
   Initialiser_Cache_LA(Cache, 2);
   Changer_Visite_Errors(Cache, 100, 25);
   Afficher_Statistiques_LA(Cache);
   -- Output should be:
   -- Taille du cache : 0
   -- Nombre de demandes de routes : 100
   -- Taux de défauts de cache : 0.25
   Put_Line("Test_Afficher_Statistiques_LA : OK");
end Test_Afficher_Statistiques_LA;

procedure Test_Lire_Cache_LA is
begin
   Initialiser_Cache_LA(Cache, 2);

   -- Test inserting values into the cache
   Key := 123456;
   Value := To_Unbounded_String("Node 1");
   Lire_Cache_LA(Cache, Key, Value, Found);
   pragma Assert(Taille_Cache_LA(Cache) = 1);

   Key := 234567;
   Value := To_Unbounded_String("Node 2");
   Lire_Cache_LA(Cache, Key, Value, Found);
   pragma Assert(Taille_Cache_LA(Cache) = 2);

   -- Test reading values from the cache
   Key := 123456;
   Value := To_Unbounded_String("");
   Lire_Cache_LA(Cache, Key, Value, Found);
   pragma Assert(Found = True);
   pragma Assert(Value = To_Unbounded_String("Node 1"));

   Key := 345678;
   Value := To_Unbounded_String("");
   Lire_Cache_LA(Cache, Key, Value, Found);
   pragma Assert(Found = False);

   -- Test eviction from the cache
   Key := 456789;
   Value := To_Unbounded_String("Node 3");
   Lire_Cache_LA(Cache, Key, Value, Found);
   pragma Assert(Taille_Cache_LA(Cache) = 2);
   Key := 123456;
   Value := To_Unbounded_String("");
   Lire_Cache_LA(Cache, Key, Value, Found);
   pragma Assert(Found = True);
   pragma Assert(Value = To_Unbounded_String("Node 1"));
   Put_Line("Test_Lire_Cache_LA : OK");
end Test_Lire_Cache_LA;

procedure Test_Afficher_Cache_LA is
begin
   Initialiser_Cache_LA(Cache, 2);
   Key := 123456;
   Value := To_Unbounded_String("Node 1");
   Lire_Cache_LA(Cache, Key, Value, Found);
   Key := 234567;
   Value := To_Unbounded_String("Node 2");
   Lire_Cache_LA(Cache, Key, Value, Found);
   Afficher_Cache_LA(Cache);
   -- Output should display the contents of the cache in the format "Key: Value"
   Put_Line("Test_Afficher_Cache_LA : OK");
end Test_Afficher_Cache_LA;

begin
   Put_Line("***** DEBUT DU TEST *****");
   Test_Initialiser_Cache_LA;
   Test_Afficher_Statistiques_LA;
   Test_Lire_Cache_LA;
   Test_Afficher_Cache_LA;
   Put_Line("***** FIN DU TEST *****");
end Test_Cache_LA;
