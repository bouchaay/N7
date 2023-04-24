-- Auteur : Xavier Crégut <prenom.nom@enseeiht.fr>
-- Implantation du module Assure

with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with Ada.Text_IO;           use Ada.Text_IO;

package body Assures is


	procedure Initialiser (Assure : out T_Assure ;
		Numero : in Integer ;
		Mois_Naissance : in Integer)
	is
	begin
		Assure.Numero := Numero;
		Assure.Mois_Naissance := Mois_Naissance;
	end Initialiser;



	procedure Afficher (Assure : in T_Assure) is
	begin
		Put ("Assuré n°" & Integer'Image (Assure.Numero)
				& " :" & Integer'Image (Assure.Mois_Naissance));
	end;



	function Le_Numero (Assure : T_Assure) return Integer is
	begin
		return Assure.Numero;
	end Le_Numero;



	function Le_Mois_Naissance (Assure : in T_Assure) return Integer is
	begin
		return Assure.Mois_Naissance;
	end Le_Mois_Naissance;



	-- Saisir un entier avec contrôle.
	procedure Saisir_Entier (N : out Integer ; Message : in String) is
		Saisie_OK : Boolean;
	begin
		loop
			begin
				Saisie_OK := False;

				-- Saisir un entier
				Put (Message);
				Get (N);
				Skip_Line;
				Saisie_OK := True;

			exception
				when Data_Error =>
					Put_Line ("C'est un entier qui est attendu.");
					Skip_Line;
			end;
			exit when Saisie_OK;
		end loop;
	end Saisir_Entier;



	procedure Saisir (Assure : out T_Assure) is
		Mois : Integer;
		Saisie_OK : Boolean;
	begin
		-- Saisir le numéro
		Saisir_Entier (Assure.Numero, "Numéro : ");
		Saisie_OK := True;

		-- Saisir le mois de naissance
		loop
			-- Demander le mois de naissance
			Saisir_Entier (Mois, "Mois de naissance : ");

			-- Verifier la validité du mois saisi
			Saisie_OK := 1 <= Mois and then Mois <= 12;

			if not Saisie_OK then
				Put_Line ("Erreur. Le mois doit être compris entre 1 et 12 !");
				Put_Line ("Recommencez !");
			else
				Null;
			end if;

			exit when Saisie_OK;
		end loop;
		pragma Assert (1 <= Mois and then Mois <= 12);
		Assure.Mois_Naissance := Mois;
	end Saisir;


end Assures;
