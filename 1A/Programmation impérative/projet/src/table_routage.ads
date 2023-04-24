------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Specification du module LC 					|
------------------------------------------------------------------------------------------------*	

with IP_ADRESS;                 use IP_ADRESS;
with LC;
with Ada.Text_IO;               use Ada.Text_IO;
with Ada.Strings.Unbounded;     use Ada.Strings.Unbounded;
with Ada.Strings;               use Ada.Strings;


package TABLE_ROUTAGE is

   Type T_Donnee is record
      Masque : T_Adresse_IP;
      Destination : Unbounded_String;
   end record;
   
   type T_Components is array (1..10) of Unbounded_String;
   type T_Tableau_Un_String is record
      Components : T_Components;
      Taille_Tab : Integer;
   end record;

    package LC_TABLE is new LC (T_Adresse_IP, T_Donnee);
    use LC_TABLE;
    
    -- La fonction split qui separce les composantes de Un_String par rapport à Delimiter.
   function Line_Split (Un_String: in Unbounded_String; Delimiter : in Character) return T_Tableau_Un_String;
   
    -- Afficher une ligne de la table de routage.
    procedure Show_Line (Cle : T_Adresse_IP  ; Donnee : T_Donnee);


    -- Afficher la table de routage entierement.
    procedure Show_Table (Table : T_LC ; Line : Integer);


    -- Retrouver l'interface de sortie convenable de l'adresse IP a router.
    function Compare_Table(Table : T_LC ; IP : T_Adresse_IP) return Unbounded_String;


    -- Initialiser la table de routage avec les elements de table.txt contenu dans File_Table.
    procedure Initialiser_Table(Table : in out T_LC ; File_Table : in out File_Type);


    -- Vider la table de routage.
    procedure Vider_Table (Table : in out T_LC);


end TABLE_ROUTAGE;
