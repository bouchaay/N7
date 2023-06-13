------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	spécification du module Cache_LA 				|
------------------------------------------------------------------------------------------------*	

with IP_ADRESS; use IP_ADRESS;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;
with Arbre; use Arbre;

package Cache_LA is

    type T_Cache is limited private;

    -- Initialiser le cache.
    procedure Initialiser_Cache_LA(Cache: in out T_Cache ; Size_Max : in Integer);

    -- Afficher les statistiques du cache.
    Procedure Afficher_Statistiques_LA(Cache : in T_Cache);

    -- Lire une route du cache.
    procedure Lire_Cache_LA(Cache : in out T_Cache ; Destination : in T_Adresse_IP ; IP_Interface : out Unbounded_String ; Found : out Boolean);

    -- Afficher le cache.
   procedure Afficher_Cache_LA(Cache : in T_Cache);
   
   -- Pour les tests du Cache_LA
   
   function Taille_Cache_LA(Cache : in T_Cache) return Integer;
   
   function Visite_Noeud_LA(Cache : in T_Cache) return Integer;
   
   function Errors_LA(Cache : in T_Cache) return Integer;
   
   procedure Changer_Visite_Errors(Cache : in out T_Cache ; Visite : in Integer ; Errors : in Integer);

private

    type T_Cache is record
        Arbre : T_LA; -- Arbre
        Size : Integer; -- Taille de l'arbre
        Size_Max : Integer; -- Taille max de l'arbre
        Visite_Noeud : Integer; -- Nombre de visite de noeud
        Errors : Integer; -- Nombre d'erreurs
    end record;

end Cache_LA;
