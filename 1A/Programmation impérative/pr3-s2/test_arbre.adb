with Arbre;
with Ada.Text_IO;            use Ada.Text_IO;
with Exceptions; 		   use Exceptions;
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;

-- Programme de test de l'arbre préfixé*
procedure Test_Arbre is

    -- Instanciation du package
    package Arbre_Test is new Arbre(T_Cle => Integer, T_Donnee => Character);
    use Arbre_Test;

    procedure Tester_Arbre_Fonctionnel is

        -- Déclaration des variables
        A : Arbre_Test.T_ABR;
        Donnee : Character;
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

        -- Vérifier la présence des clés
        pragma Assert(Cle_Presente(A, 1));
        pragma Assert(Cle_Presente(A, 2));
        pragma Assert(Cle_Presente(A, 3));
        pragma Assert(Cle_Presente(A, 4));

        -- Construction du mot 'AYOUB'
        Put_Line("");
        Put_Line("> Construction du mot 'AYOUB' : ");
        Put("> Mot Construit : ");
        -- Récupérer la donnée de la clé 1
        Donnee := La_Donnee(A, 1);
        pragma Assert(Donnee = 'A');
        Put (Donnee);

        -- Récupérer la donnée de la clé 4
        Donnee := La_Donnee(A, 4);
        pragma Assert(Donnee = 'Y');
        Put (Donnee);

        -- Récupérer la donnée de la clé 5
        Donnee := La_Donnee(A, 5);
        pragma Assert(Donnee = 'O');
        Put (Donnee);

        -- Récupérer la donnée de la clé 6
        Donnee := La_Donnee(A, 6);
        pragma Assert(Donnee = 'U');
        Put (Donnee);

        -- Récupérer la donnée de la clé 7
        Donnee := La_Donnee(A, 7);
        pragma Assert(Donnee = 'B');
        Put (Donnee);

        Put_Line("");
        Put_Line("***************************************************************");
        Put_line("Test de l'arbre fonctionnel : OK");
        Put_Line("***************************************************************");

        -- Destruction de l'arbre
        Detruire(A);
        pragma Assert(Taille(A) = 0);
        pragma Assert(Est_Vide(A));
        pragma Assert(Est_Feuille(A));

    end Tester_Arbre_Fonctionnel;
begin
    Tester_Arbre_Fonctionnel;
end Test_Arbre;





