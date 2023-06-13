-- Auteur : Xavier Crégut <prenom.nom@enseeiht.fr>
-- Programme de simulation de la CPAM.

with Ada.Text_IO;        use Ada.Text_IO;
with Assures;            use Assures;
with CPAM;               use CPAM;


procedure Simulation_CPAM is

	-- Afficher le menu.
	procedure Afficher_Menu is
	begin
		Put_Line (" a) un assuré arrive");
		Put_Line (" r) l'accueil renseigne un assuré");
		for N in 1..Nombre_Guichets loop
			Put_Line (Integer'Image (N) & ") l'agent du guichet"
					& Integer'Image (N) & " reçoit un nouvel assuré");
		end loop;
		Put_Line (" Q) quitter le simulateur");
	end Afficher_Menu;


	-- Demander le choix de l'utilisateur.
	procedure Demander_Choix (Choix : out Character) is
	begin
		Put ("Votre choix : ");
		Get (Choix);
		Skip_Line;
	end Demander_Choix;


	-- Traiter le choix de l'utilisateur.
	procedure Traiter_Choix (CPAM : in out T_CPAM; Choix: in Character) is
		Nouvel_Assure : T_Assure;
		Numero_Guichet : Integer;
	begin
		case Choix is
			when 'a' =>	-- un nouvel usager arrive
				Saisir (Nouvel_Assure);
				Arriver (CPAM, Nouvel_Assure);

			when 'r' =>	-- l'acceuil renseigne un assuré
				Renseigner (CPAM);

			when '1'..Character'Val (Character'Pos ('0') + Nombre_Guichets) =>
				-- l'agent du guichet Choix reçoit un assuré
				Numero_Guichet := Character'Pos (Choix) - Character'Pos ('0');
				Appeler (CPAM, Numero_Guichet);

			when 'Q' =>	-- Quitter
				Null;

			when others =>
				Put_Line ("Je n'ai pas compris. Pas d'action " & Choix);
		end case;
	end Traiter_Choix;


	CPAM : T_CPAM;
	Choix: Character;
begin

	Initialiser (CPAM);
	loop
		New_Line; Afficher(CPAM); New_Line;
		Afficher_Menu;
		Demander_Choix (Choix);
		Traiter_Choix (CPAM, Choix);

		exit when Choix = 'Q';
	end loop;

end Simulation_CPAM;
