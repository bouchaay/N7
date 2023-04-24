-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Implantation du module CPAM.

with Ada.Text_IO;        use Ada.Text_IO;
with Exceptions; 	   use Exceptions;

package body CPAM is

	-- Initialiser des files d'attente
	procedure Initialiser (CPAM : out T_CPAM) is
	begin
		Initialiser(File => CPAM.Accueil);
		for I in 1 .. Nombre_Guichets loop
			Initialiser(File => CPAM.Guichets(I));
		end loop;
	end;

	-- Arriver d'un assuré
	procedure Arriver (CPAM : in out T_CPAM ; Assure : in T_Assure) is
	begin
		Inserer(File => CPAM.Accueil, Element => Assure);
	end Arriver;

	-- Renseigner un assuré
	procedure Renseigner (CPAM : in out T_CPAM) is
		Assure : T_Assure;
		MoisDeNaissance : Integer;
	begin
		Assure := Extraire(File => CPAM.Accueil);
		MoisDeNaissance := Le_Mois_Naissance(Assure);
		case MoisDeNaissance is
			when 1 | 2 =>
				Inserer(File => CPAM.Guichets(1), Element => Assure);
			when 3 | 4 =>
				Inserer(File => CPAM.Guichets(2), Element => Assure);
			when 5 | 6 =>
				Inserer(File => CPAM.Guichets(3), Element => Assure);
			when 7 | 8 =>
				Inserer(File => CPAM.Guichets(4), Element => Assure);
			when 9 | 10 =>
				Inserer(File => CPAM.Guichets(5), Element => Assure);
			when 11 | 12 =>
				Inserer(File => CPAM.Guichets(6), Element => Assure);
			when others =>
				Raise Date_Naissance_Invalide;
		end case;
	end Renseigner;

	-- Appeler un assuré à un guichet
	procedure Appeler (CPAM : in out T_CPAM; Numero_Guichet : in Integer) is
		Assure : T_Assure;
	begin
		Assure := Extraire(File => CPAM.Guichets(Numero_Guichet));
	end Appeler;

	-- Afficher les files d'attente
	procedure Afficher (CPAM: in T_CPAM) is
		-- Afficher la file d'attente de l'accueil
		procedure AfficherFileAcceuil is new T_File_Accueil.Traiter_File(Traiter_Element => Assures.Afficher);
		-- Afficher la file d'attente d'un guichet
		procedure AfficherFileGuichet is new T_File_Guichet.Traiter_File(Traiter_Element => Assures.Afficher);
	begin
		Put_Line("File d'attente de l'accueil :");
		AfficherFileAcceuil(File => CPAM.Accueil);
		Put_Line("");
		for I in 1 .. Nombre_Guichets loop
			Put_Line("File d'attente du guichet " & Integer'Image(I) & " :");
			AfficherFileGuichet(File => CPAM.Guichets(I));
		end loop;
	end;

end CPAM;
