-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Implantation du module Files.

with Ada.Text_IO;  use Ada.Text_IO;
with Exceptions;   use Exceptions;

package body Files is

	-- Initialisation d'une file.
	procedure Initialiser (File : out T_File) is
	begin
		File.Taille := 0;
		File.Debut := 1;
		File.Fin := 0;
	end Initialiser;

	-- Est ce que la file est vide ?
	function Est_Vide (File : in T_File) return Boolean is
	begin
		return File.Taille = 0;
	end Est_Vide;

	-- Est ce que la file est pleine ?
	function Est_Pleine (File : in T_File) return Boolean is
	begin
		return File.Taille = Cap;
	end Est_Pleine;

	-- Nombre d'elements dans la file.
	function Taille(File : in T_File) return Integer is
	begin
		return File.Taille;
	end Taille;

	-- Inserer un element dans la file.
	procedure Inserer (File : in out T_File; Element : in T_Element) is
	begin
		if Est_Pleine (File) then
			Raise File_Pleine;
		else
			File.Taille := File.Taille + 1;
			File.Fin := (File.Fin + 1) mod Cap;
			if File.Fin = 0 then
				File.Fin := Cap;
			end if;
			File.Elements (File.Fin) := Element;
		end if;
	end Inserer;

	-- Extraire un element de la file.
	function Extraire (File : in out T_File) return T_Element is
		ElementExtraire : T_Element;
	begin
		if Est_Vide (File) then
			Raise File_Vide;
		else
			ElementExtraire := File.Elements (File.Debut);
			File.Taille := File.Taille - 1;
			if File.Taille = 0 then
				File.Debut := 1;
				File.Fin := 0;
			else
				File.Debut := (File.Debut + 1) mod Cap;
				if File.Debut = 0 then
					File.Debut := Cap;
				end if;
			end if;
			return ElementExtraire;
		end if;
	end Extraire;

	-- Traiter les elements de la file.
	procedure Traiter_File (File : in T_File) is
		IndiceElement : Integer;
		begin
			for Index in 1 .. File.Taille loop
				IndiceElement := (Index + File.Debut - 1) mod Cap;
				Traiter_Element (File.Elements (IndiceElement));
				Put_Line ("");
			end loop;
	end Traiter_File;

end Files;
