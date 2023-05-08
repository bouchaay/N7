with Arbre;
with Ada.Text_IO;            use Ada.Text_IO;
with Exceptions; 		   use Exceptions;

-- Programme de test de l'arbre préfixé*
procedure Test_Arbre is

    -- Instanciation du package
    package Arbre_Test is new Arbre(T_Cle => Integer, T_Donnee => Character, Cle_Racine => 0, Max_Fils => 10, Max_Donnee => 100);
    use Arbre_Test;

    procedure Tester_Arbre_Fonctionnel is
        A : Arbre_Test.T_ABR;
        TabDonnees : Arbre_Test.T_Array_Donnee;
    begin
        Initialiser(A);
        pragma Assert(Taille(A) = 0);
        pragma Assert(Est_Vide(A));
        pragma Assert(Est_Feuille(A));
        
        -- Initialiser la racine de l'arbre
        Inserer(A, 0, 0, '0');
        pragma Assert(Taille(A) = 1);
        pragma Assert(not Est_Vide(A));
        pragma Assert(Est_Feuille(A));

        -- Ajouter un fils à la racine
        Inserer(A, 1, 0, 'A');
        pragma Assert(Taille(A) = 2);
        pragma Assert(not Est_Vide(A));
        pragma Assert(not Est_Feuille(A));

        -- Ajouter un fils à la racine
        Inserer(A, 2, 0, 'B');
        pragma Assert(Taille(A) = 3);
        pragma Assert(not Est_Vide(A));
        pragma Assert(not Est_Feuille(A));

        -- Ajouter un fils à la racine
        Inserer(A, 3, 0, 'C');
        pragma Assert(Taille(A) = 4);
        pragma Assert(not Est_Vide(A));
        pragma Assert(not Est_Feuille(A));

        -- Ajouter un fils au fils 1
        Inserer(A, 4, 1, 'Y');
        pragma Assert(Taille(A) = 5);
        pragma Assert(not Est_Vide(A));
        pragma Assert(not Est_Feuille(A));

        -- Ajouter des clé aux fils pour construire le mot 'AYOUB'
        Inserer(A, 5, 4, 'O');
        Inserer(A, 6, 5, 'U');
        Inserer(A, 7, 6, 'B');
        pragma Assert(Taille(A) = 8);
        pragma Assert(not Est_Vide(A));
        pragma Assert(not Est_Feuille(A));

        -- Récupérer la donnée de la clé 7
        TabDonnees := La_Donnee(A, 7);
        pragma Assert(TabDonnees(1) = 'A');
        pragma Assert(TabDonnees(2) = 'Y');
        pragma Assert(TabDonnees(3) = 'O');
        pragma Assert(TabDonnees(4) = 'U');
        pragma Assert(TabDonnees(5) = 'B');

        Put_line("Test de l'arbre fonctionnel : OK");
    end Tester_Arbre_Fonctionnel;
begin
    Tester_Arbre_Fonctionnel;
end Test_Arbre;





