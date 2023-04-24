------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Specification du module Arbre					|		
------------------------------------------------------------------------------------------------*	

with IP_ADRESS;  use IP_ADRESS;
with Ada.Strings.Unbounded;   use Ada.Strings.Unbounded;

package Arbre is

   type T_LA is limited private;

 -- Initialiser l'arbre.
 Procedure Initialiser (Arbre : in out T_LA) with
  	post => taille(Arbre) = 0;

 -- Calculer la taille de l'arbre.
 function Taille (Arbre : in T_LA) return integer;

 -- Supprimer un element de l'arbre.
 Procedure Supprimer (Noeud_Actuel : in Integer ; Arbre : in out T_LA);


 -- Traiter une adresse de l'arbre.
 Procedure Traiter (Noeud_Actuel : in Integer ; Cle : in T_Adresse_IP  ; Valeur : out Unbounded_String ; Found : out Boolean ; Arbre : in out T_LA);


 -- Enregistrer une cle dans l'arbre.
 Procedure Enregistrer (Noeud_Actuel : in Integer ; Cle : in T_Adresse_IP ; Valeur : in Unbounded_String ; Arbre : in out T_LA);


 -- Afficher l'arbre.
 Procedure Afficher (Arbre : in T_LA);


private

 type T_Noeud;

 type T_LA is access T_Noeud;

 type T_Noeud is
    record
        Cle : T_Adresse_IP ;
        Valeur: Unbounded_String;
        Visite_Noeud : Integer;
        Fils_Gauche : T_LA ;
        Fils_Droit : T_LA ;
    end record;

end Arbre;
