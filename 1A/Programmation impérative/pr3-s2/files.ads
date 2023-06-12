-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Spécification du module Files.

generic
	type T_Element is private;	-- Type des éléments de la file
	Cap : Integer;	-- Capacité maximale de la file

package Files is

	-- Tableau des éléments de la File.
	type T_Array_Element is array (1 .. Cap) of T_Element;

		-- Type de la File.
	type T_File is 
		record
			Taille : Integer;
			Elements : T_Array_Element;
		end record;

	-- Initilaiser une File.  La file est vide.
	procedure Initialiser (File : out T_File) with
		Post => Est_Vide (File);

	-- Est-ce que la File est vide ?
	function Est_Vide (File : in T_File) return Boolean;

	-- Est-ce que la File est pleine ?
	function Est_Pleine (File : in T_File) return Boolean;

	-- Nombre d'éléments dans la File.
	function Taille (File : in T_File) return Integer;

	-- Ajouter un élément à la File.
	procedure Inserer (File : in out T_File; Element : in T_Element) with
		Pre => not Est_Pleine (File),
		Post => not Est_Vide (File);

	-- Extraire un élément de la File.
	function Extraire (File : in out T_File) return T_Element with
		Pre => not Est_Vide (File),
		Post => not Est_Pleine (File);

	-- Vider la File.
	procedure Vider (File : in out T_File) with
		Post => Est_Vide (File);

	-- Traiter tous les éléments de la File.
	generic
		with procedure Traiter_Element (Element : in T_Element);
		procedure Traiter_File (File : in T_File);
		
end Files;
