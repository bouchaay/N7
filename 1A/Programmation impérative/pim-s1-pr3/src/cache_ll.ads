------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	spécification du module Cache_LL 				|
------------------------------------------------------------------------------------------------*	

with Ada.Strings;                 use Ada.Strings;
with LC;
with IP_ADRESS;                   use IP_ADRESS;
with Ada.Strings.Unbounded;       use Ada.Strings.Unbounded;

package Cache_LL is

    type T_Cache is limited private;
    type T_Cellule is limited private;


	-- Initialiser le cache.
    	procedure Initialiser_Cache_LL (Cache : in out T_Cache; Size_Max : Integer);
    
	-- Afficher les statistiques du cache.
	procedure Afficher_Statistiques_LL (Cache : in T_Cache);
    	
	-- Afficher le cache.
	procedure Afficher_Cache_LL (Cache : in T_Cache; Politique : in Unbounded_String);
    	

	procedure Lire_Cache_LL (Cache : in out T_Cache; Destination : in T_Adresse_IP; Politique : Unbounded_String ; IP_Interface : out Unbounded_String; Found : out Boolean);
    
	-- Ajouter un element au cache.
	procedure Ajouter_Cache_LL (Cache : in out T_Cache; Destination : in T_Adresse_IP; IP_Interface : in Unbounded_String; Politique : in Unbounded_String);
    
	-- Incrementer le nombre de defauts
	procedure Incrementer_CALLS_LL (Cache : in out T_Cache);
       
   procedure Incrementer_ERRORS_LL (Cache : in out T_Cache);
	-- Calculer la taille du cache.
	function Taille_Cache_LL (Cache : in T_Cache) return Integer;
    
	-- Verifier si le cache est vide.
	function Est_Vide_Cache_LL (Cache : in T_Cache) return Boolean;
    
	-- Retourner le nombre d'appel d'une destination
	function N_CALLS_Destination_LL (Cache : in T_Cache; Destination : in T_Adresse_IP) return Integer;

private

    	type T_Cellule is record
        	IP_Interface : Unbounded_String;
        	N_CALLS_IP : Integer;
    	end record;

    	package ADRESS_CELLULE_LL is new LC (T_Cle => T_Adresse_IP, T_Donnee => T_Cellule);
    	use ADRESS_CELLULE_LL;

    	type T_Cache is record
        	L : T_LC;
        	Size_Max : Integer;
        	N_CALLS : Integer;
        	N_ERRORS : Integer;
    	end record;

end Cache_LL;
