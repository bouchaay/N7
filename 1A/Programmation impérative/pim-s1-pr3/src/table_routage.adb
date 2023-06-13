------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Implantation du module TABLE_ROUTAGE 				|
------------------------------------------------------------------------------------------------*	

with Ada.Strings;               use Ada.Strings;	-- pour Both utilis� par Trim
with Ada.Text_IO;               use Ada.Text_IO;
with Ada.Integer_Text_IO;       use Ada.Integer_Text_IO;
with Ada.Strings.Unbounded;     use Ada.Strings.Unbounded;
with Ada.Text_IO.Unbounded_IO;  use Ada.Text_IO.Unbounded_IO;
with Ada.Command_Line;          use Ada.Command_Line;
with Ada.Exceptions;            use Ada.Exceptions;

package body TABLE_ROUTAGE is
   

function Line_Split (Un_String: in Unbounded_String; Delimiter : in Character) return T_Tableau_Un_String is
      Str_Holder : constant String := To_String(Trim(Un_String, both));
      Maximum : constant Integer := 10;
      Counter : Integer := 1;
      Tab : T_Tableau_Un_String;
   begin
      for i in Str_Holder'Range loop
         if Str_Holder(i) = Delimiter or Str_Holder(i) = Character'Val(9) then -- Le Character'Val(9) permet de convertir la valeur enti�re 9 en caract�re, c'est le code ASCII d'une tabulation.
            if i+1 <= Str_Holder'Last and then (Str_Holder(i+1) = Delimiter or Str_Holder(i+1) = Character'Val(9)) then
               null;
            else
               if Counter < Maximum then
                        Counter := Counter + 1;
               else
                  null;
               end if;
            end if;
         else
            Append(Tab.Components(Counter), Str_Holder(i));
         end if;       
      end loop;
      Tab.Taille_Tab := 0;
      for j in 1..Maximum loop
         if Tab.Components(j) /= To_Unbounded_String("") then
            Tab.Taille_Tab := Tab.Taille_Tab + 1;
         end if;
      end loop;
      for k in 1..Tab.Taille_Tab loop
            Tab.Components(k) := Trim(Tab.Components(k), both);
      end loop;
      return Tab;
   end Line_Split;

 	procedure Show_Line (Cle : T_Adresse_IP  ; Donnee : T_Donnee) is
    	begin
      		Show_IP(Cle);
      		-- Afficher l'adresse IP
      		Show_IP(Donnee.Masque);
      		-- Afficher le masque de sous-réseau
      		Put(To_String(Donnee.Destination));
      		-- Afficher la destination de l'adresse IP
      		New_Line;
    	end Show_Line; 

	procedure Show_Table (Table : T_LC ; Line : Integer) is
   		procedure Show_Table_Line is new Pour_Chaque(Traiter => Show_Line);
    	begin
      		-- Afficher le titre de la table
      		Put("table : (ligne "); put(Line, 1); Put(")");
      		-- Afficher chaque ligne de la table en utilisant la procédure Show_Table_Line
      		Show_Table_Line(Table); 
    	end Show_Table;
    
 

	function Compare_Table(Table : T_LC ; IP : T_Adresse_IP) return Unbounded_String is
      		Mask_IP : T_Adresse_IP;
      		Mask_Max : T_Adresse_IP := 0;
      		Out_Interface : Unbounded_String := To_Unbounded_String("Erreur routage");

      		-- Procédure interne pour comparer une ligne de la table
      		procedure Compare_Line(Cle : T_Adresse_IP ; Donnee : T_Donnee) is
      		begin
        		-- Appliquer le masque de sous-réseau à l'adresse IP donnée
        		Mask_IP := IP and Donnee.Masque;
        		-- Si le masque de sous-réseau de cette ligne est supérieur ou égal à celui déjà enregistré
        		-- et que l'adresse IP masquée est égale à l'adresse IP de cette ligne
        		if Mask_IP = Cle and Donnee.Masque >= Mask_Max then
          			-- Mise à jour de l'interface de sortie et du masque de sous-réseau maximal
          			Mask_Max := Donnee.Masque;
            			Out_Interface := Donnee.Destination;
            	        end if;
      		end Compare_Line;

      		-- Utilisation de la procédure générique Pour_Chaque pour parcourir la table et appeler la procédure Compare_Line pour chaque ligne
		procedure Browse_Table is new Pour_Chaque(Traiter => Compare_Line);
    	begin
  		-- Parcourir la table et appeler Compare_Line pour chaque ligne
  		Browse_Table(Table);
  		-- Retourner l'interface de sortie
  		return Out_Interface;
	end Compare_Table;


	procedure Initialiser_Table(Table : in out T_LC ; File_Table : in out File_Type ) is
  		
      procedure Add_Line (Table : in out T_LC ; Line : in Unbounded_String) is
         Destination_Un_String : Unbounded_String;
         Mask_Un_String : Unbounded_String;
         IP : T_Adresse_IP;
         Mask : T_Adresse_IP;
         Interface_Sortie : Unbounded_String;
         Tableau_Un_String : T_Tableau_Un_String;
         Line_Table : T_Donnee;
      begin
         Tableau_Un_String := Line_Split(Line, ' '); -- Contient la destination, le masque et l'interface de sortie.
         
         -- Retrouver la destination, le masque, et l'interface de sortie en Unbounded_String.
         Destination_Un_String := Tableau_Un_String.Components(1);
         Mask_Un_String := Tableau_Un_String.Components(2);
         Interface_Sortie := Tableau_Un_String.Components(3);
		
         -- Retrouver la destination en T_Adresse_IP.
         Tableau_Un_String := Line_Split(Destination_Un_String, '.');
         To_T_Adresse_IP(To_String(Tableau_Un_String.Components(1)),To_String(Tableau_Un_String.Components(2)),To_String(Tableau_Un_String.Components(3)), To_String(Tableau_Un_String.Components(4)), IP);
		
		
         -- Retrouver la destination en T_Adresse_IP.
         Tableau_Un_String := Line_Split(Mask_Un_String, '.');
         To_T_Adresse_IP(To_String(Tableau_Un_String.Components(1)),To_String(Tableau_Un_String.Components(2)),To_String(Tableau_Un_String.Components(3)), To_String(Tableau_Un_String.Components(4)), Mask);
		

         -- Stockage de la ligne courante dans une variable temporaire.
         Line_Table := (Mask, Interface_Sortie);
         
         -- Ajout de la ligne courante à la table
         Enregistrer_Fin(Table, IP, Line_Table);

		end Add_Line;
      Line : Unbounded_String;
   begin
  		-- Initialisation de la table
  		Initialiser(Table);
  		-- Boucle de lecture des entrées de la table depuis le fichier
  		loop
         -- R�cuperer la ligne courante du fichier table.txt
         Line := Get_Line(File_Table);
         -- Enregistrer les composantes de ce fichier (Adresse, Masque et Interface) dans la table de routage
         Add_Line(Table, Line);
       exit when End_Of_File(File_Table);
       end loop;
  
  	exception
    		-- Gestion de l'exception de fin de fichier 
    		when End_Error =>
      			Put ("Trop de blanc à la fin du fichier.");
      			null;
	end Initialiser_Table;

	procedure Vider_Table (Table : in out T_LC) is
    	begin
        	Vider(Table); -- Vider la table en utiliser la procedure vider du module LC.
    	end Vider_Table;
	
    
end TABLE_ROUTAGE;


