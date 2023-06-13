with Files;
with Ada.Text_IO;            use Ada.Text_IO;
with Exceptions; 		   use Exceptions;

-- Programme de test du module File.
procedure Test_Files is

	-- Instanciation d'une file de caractÃ¨res.
	package T_FileTest is new Files (T_Element => Character, Cap => 10);
	use T_FileTest;

	procedure Tester_File_Caractere is
		-- File de test.
		F1 : T_FileTest.T_File;
		-- Caractère extrait.
		Caractere : Character;
	begin
		-- Initialisation de la file.
		Initialiser (File => F1);
		pragma Assert (Est_Vide (File => F1), "File non vide");
		pragma Assert (not Est_Pleine (File => F1), "File pleine");
		pragma Assert (Taille (File => F1) = 0, "Taille de la file non nulle");

		-- Insertion de l'element 'A'.
		Inserer (File => F1, Element => 'A');
		pragma Assert (not Est_Vide (File => F1), "File vide");
		pragma Assert (not Est_Pleine (File => F1), "File pleine");
		pragma Assert (Taille (File => F1) = 1, "Taille de la file non nulle");

		-- Insertion de l'element 'B'.
		Inserer (File => F1, Element => 'B');
		pragma Assert (not Est_Vide (File => F1), "File vide");
		pragma Assert (not Est_Pleine (File => F1), "File pleine");
		pragma Assert (Taille (File => F1) = 2, "Taille de la file non nulle");

		-- Insertion de l'element 'C'.
		Inserer (File => F1, Element => 'C');
		pragma Assert (not Est_Vide (File => F1), "File vide");
		pragma Assert (Est_Pleine (File => F1), "File non pleine");
		pragma Assert (Taille (File => F1) = 3, "Taille de la file non nulle");

		-- Extraction de l'element 'A'.
		Caractere := Extraire (File => F1);
		pragma Assert (not Est_Vide (File => F1), "File vide");
		pragma Assert (not Est_Pleine (File => F1), "File pleine");
		pragma Assert (Taille (File => F1) = 2, "Taille de la file non nulle");
		pragma Assert (Caractere = 'A', "CaractÃ¨re extrait incorrect");

		-- Insertion de l'element 'D'.
		Inserer (File => F1, Element => 'D');
		pragma Assert (not Est_Vide (File => F1), "File vide");
		pragma Assert (Est_Pleine (File => F1), "File non pleine");
		pragma Assert (Taille (File => F1) = 3, "Taille de la file non nulle");

		-- Extraction de l'element 'B'.
		Caractere := Extraire (File => F1);
		pragma Assert (not Est_Vide (File => F1), "File vide");
		pragma Assert (not Est_Pleine (File => F1), "File pleine");
		pragma Assert (Taille (File => F1) = 2, "Taille de la file non nulle");
		pragma Assert (Caractere = 'B', "CaractÃ¨re extrait incorrect");

		-- Extraction de l'element 'C'.
		Caractere := Extraire (File => F1);
		pragma Assert (not Est_Vide (File => F1), "File vide");
		pragma Assert (not Est_Pleine (File => F1), "File pleine");
		pragma Assert (Taille (File => F1) = 1, "Taille de la file non nulle");
		pragma Assert (Caractere = 'C', "CaractÃ¨re extrait incorrect");

		-- Extraction de l'element 'D'.
		Caractere := Extraire (File => F1);
		pragma Assert (Est_Vide (File => F1), "File non vide");
		pragma Assert (not Est_Pleine (File => F1), "File pleine");
		pragma Assert (Taille (File => F1) = 0, "Taille de la file non nulle");
		pragma Assert (Caractere = 'D', "CaractÃ¨re extrait incorrect");

		-- Afficher le resultat des tests.
		Put_Line ("Tests du module File : OK");

	end Tester_File_Caractere;


begin
	Tester_File_Caractere;
end Test_Files;
