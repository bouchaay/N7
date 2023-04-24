-- Auteur : Ayoub Bouchama
-- Groupe : F
-- SpÃ©cification du module CPAM.
--
with Assures;            use Assures;
with Files;              

package CPAM is

	Nombre_Guichets : constant Integer := 6; -- Nombre de guichets.
	Capacite_File_Accueil : constant Integer := 20; -- CapacitÃ© de la file d'attente de l'accueil.
	Capacite_File_Guichet : constant Integer := 5; -- CapacitÃ© de la file d'attente d'un guichet.

	-- Type d'une CPAM.
	type T_CPAM is private;

	-- Initialiser une CPAM.  Aucun assurÃ© n'est prÃ©sent dans la CPAM.
	procedure Initialiser (CPAM : out T_CPAM);


	-- Un nouvel assurÃ© arrive.  Il attend devant l'accueil
	-- pour savoir oÃ¹ aller.
	procedure Arriver (CPAM : in out T_CPAM ; Assure : in T_Assure);


	-- L'accueil renseigne un assurÃ©. Normalement, l'assurÃ© quitte l'accueil et
	-- attend devant le guichet indiquÃ©.
	procedure Renseigner (CPAM : in out T_CPAM);


	-- Le guichet Numero_Guichet appelle un assurÃ©.
	procedure Appeler (CPAM : in out T_CPAM; Numero_Guichet : in Integer) with
		Pre => 1 <= Numero_Guichet and then Numero_Guichet <= Nombre_Guichets;


	-- Afficher la CPAM.
	-- Afficher les files d'attente de l'accueil et les guichets.
	procedure Afficher (CPAM: in T_CPAM);

private

	-- type d'une file d'attente d'un guichet.
	package T_File_Guichet is new Files (T_Element => T_Assure, Cap => Capacite_File_Guichet);
	use T_File_Guichet;

	-- type d'une file d'attente de l'accueil.
	package T_File_Accueil is new Files (T_Element => T_Assure, Cap => Capacite_File_Accueil);
	use T_File_Accueil;

	-- type d'un tableau de files d'attente de guichets.
	type T_Array_Guichets is array (1 .. Nombre_Guichets) of T_File_Guichet.T_File;

	-- type d'une CPAM.
	type T_CPAM is
		record
			Accueil : T_File_Accueil.T_File;
			Guichets : T_Array_Guichets;
		end record;

end CPAM;
