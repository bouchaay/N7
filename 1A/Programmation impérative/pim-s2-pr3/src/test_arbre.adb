with Arbre;
with Ada.Text_IO;            use Ada.Text_IO;
with Exceptions; 		   use Exceptions;
with Files;                 
with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;

-- Programme de test de l'arbre préfixé*
procedure Test_Arbre is

    -- Instanciation du package
    package Arbre_Test is new Arbre(T_Valeur_Arete => Character, T_Valeur_Noeud => Character, V_Noeud_Vide => '-', V_Arete_Vide => ' ', NbFilsMax => 26);
    use Arbre_Test;

    -- Affichage d'une cle
    -- Parametre Cle : la cle a afficher
    procedure AfficherCle(Cle : in Arbre_Test.T_File_Abr.T_File) is
    CopieCle : Arbre_Test.T_File_Abr.T_File := Cle;
    begin
        while Arbre_Test.T_File_Abr.Taille(CopieCle) > 0 loop
            Put(Arbre_Test.T_File_Abr.Extraire(CopieCle));
        end loop;
    end AfficherCle;

    -- Affichage d'un noeud
    -- Parametre InfoNoeud : la valeur du noeud a afficher
    -- Parametre InfoArete : la valeur de l'arete a afficher
    -- Parametre Cle : la cle a afficher
    procedure AfficherNoeud(InfoNoeud : in Character; InfoArete : in Character; Cle : in Arbre_Test.T_File_Abr.T_File) is
    begin
        Put("La valeur du noeud est : ");
        Put_Line(Character'Image(InfoNoeud));
        Put("La valeur de l'arete est : ");
        Put_Line(Character'Image(InfoArete));
        Put("La valeur de la cle est : ");
        AfficherCle(Cle);
    end AfficherNoeud;

    -- Affichage de l'arbre
    procedure AfficherArbre is new Arbre_Test.Afficher(Traiter_Noeud => AfficherNoeud);

    -- Test de l'arbre
    procedure Test_De_Arbre is
        Arbre : Arbre_Test.T_Arbre;
        Cle1 : Arbre_Test.T_File_Abr.T_File;
        Cle2 : Arbre_Test.T_File_Abr.T_File;
        Cle3 : Arbre_Test.T_File_Abr.T_File;
        Cle4 : Arbre_Test.T_File_Abr.T_File;
        Cle5 : Arbre_Test.T_File_Abr.T_File;
    begin

        -- Debut des tests
        Put_Line("");
        Put_Line("************** Debut du test **************");
        Put_Line("");

        ---------------------------------------------------------------------------------------------------------------------------
        ---------------------------------------------------------------------------------------------------------------------------

        Put_Line("************** Test de la fonction Ajouter **************");
        Put_Line("");

        -- Initialisation de l'arbre
        Initialiser(A => Arbre);

        -- Tests
        pragma Assert (Taille(Arbre) = 0, "La taille de l'arbre n'est pas 0");
        pragma Assert (Est_Feuille(Arbre), "L'arbre n'est pas une feuille");

        -- Initialiser une cle
        T_File_Abr.Initialiser(File => Cle1);
        T_File_Abr.Inserer(File => Cle1, Element => 'a');
        T_File_Abr.Inserer(File => Cle1, Element => 'y');
        T_File_Abr.Inserer(File => Cle1, Element => 'o');
        T_File_Abr.Inserer(File => Cle1, Element => 'u');
        T_File_Abr.Inserer(File => Cle1, Element => 'b');

        -- Ajouter une cle
        Ajouter(A => Arbre, C => Cle1, V => '1');

        -- Tests
        pragma Assert (Taille(Arbre) = 1);
        pragma Assert (not Est_Feuille(Arbre));
        pragma Assert (Valeur_Noeud_Presente(Arbre, '1'));

        -- Initialiser une cle
        T_File_Abr.Initialiser(File => Cle2);
        T_File_Abr.Inserer(File => Cle2, Element => 'a');
        T_File_Abr.Inserer(File => Cle2, Element => 'y');
        T_File_Abr.Inserer(File => Cle2, Element => 'a');

        -- Ajouter une cle
        Ajouter(A => Arbre, C => Cle2, V => '2');

        -- Tests
        pragma Assert (Taille(Arbre) = 2);
        pragma Assert (not Est_Feuille(Arbre));
        pragma Assert (Valeur_Noeud_Presente(Arbre, '2'));

        -- Initialiser une cle
        T_File_Abr.Initialiser(File => Cle3);
        T_File_Abr.Inserer(File => Cle3, Element => 'E');
        T_File_Abr.Inserer(File => Cle3, Element => 'n');
        T_File_Abr.Inserer(File => Cle3, Element => 's');
        T_File_Abr.Inserer(File => Cle3, Element => 'e');
        T_File_Abr.Inserer(File => Cle3, Element => 'e');
        T_File_Abr.Inserer(File => Cle3, Element => 'i');
        T_File_Abr.Inserer(File => Cle3, Element => 'h');
        T_File_Abr.Inserer(File => Cle3, Element => 't');

        -- Ajouter une cle
        Ajouter(A => Arbre, C => Cle3, V => '3');

        -- Tests
        pragma Assert (Taille(Arbre) = 3);
        pragma Assert (not Est_Feuille(Arbre));
        pragma Assert (Valeur_Noeud_Presente(Arbre, '3'));

        -- Affichage de l'arbre
        Put_Line("Premier affichage de l'arbre");
        Put_Line("");
        AfficherArbre(Arbre, 0);
        Put_Line("---------------------------------------------------------------------------------");

        -- Ajouter une cle
        T_File_Abr.Initialiser(File => Cle4);
        T_File_Abr.Inserer(File => Cle4, Element => 'E');
        T_File_Abr.Inserer(File => Cle4, Element => 'n');
        T_File_Abr.Inserer(File => Cle4, Element => 's');

        -- Ajouter une cle
        Ajouter(A => Arbre, C => Cle4, V => '4');

        -- Tests
        pragma Assert (Taille(Arbre) = 4);
        pragma Assert (Valeur_Noeud_Presente(Arbre, '4'));

        -- On va remarquer que la valeur du noeud correspondant a la cle 'Ens' va prendre la valeur '4' alors qu'il était vide ( '-' )
        -- Affichage de l'arbre
        Put_Line("Deuxieme affichage de l'arbre :");
        Put_Line("");
        AfficherArbre(Arbre, 0);
        Put_Line("---------------------------------------------------------------------------------");

        -- Ajouter la meme cle mais avec une valeur differente
        Ajouter(A => Arbre, C => Cle4, V => '5');

        -- On va remarquer que la valeur du noeud correspondant a la cle 'Ens' va prendre la valeur '5' alors qu'il était '4'
        -- Affichage de l'arbre
        Put_Line("Troisieme affichage de l'arbre :");
        Put_Line("");
        AfficherArbre(Arbre, 0);
        Put_Line("---------------------------------------------------------------------------------");

        -- Ajour d'une cle
        T_File_Abr.Initialiser(File => Cle5);
        T_File_Abr.Inserer(File => Cle5, Element => 'E');
        T_File_Abr.Inserer(File => Cle5, Element => 'n');
        T_File_Abr.Inserer(File => Cle5, Element => 'n');
        T_File_Abr.Inserer(File => Cle5, Element => 'a');

        -- Ajouter une cle
        Ajouter(A => Arbre, C => Cle5, V => '6');

        -- Affichage de l'arbre
        Put_Line("Quatrieme affichage de l'arbre :");
        Put_Line("");
        AfficherArbre(Arbre, 0);

        ---------------------------------------------------------------------------------------------------------------------------
        ---------------------------------------------------------------------------------------------------------------------------

        Put_Line("");
        Put_Line("************** Test de la fonction Cle_Presente **************");
        Put_Line("");

        pragma Assert (Cle_Presente(Arbre, Cle1));
        pragma Assert (Cle_Presente(Arbre, Cle2));
        pragma Assert (Cle_Presente(Arbre, Cle3));
        pragma Assert (Cle_Presente(Arbre, Cle4));
        pragma Assert (Cle_Presente(Arbre, Cle5));

        if Cle_Presente(Arbre, Cle1) then
            Put_Line("La cle 'ayoub' est presente dans l'arbre");
        else
            Put_Line("La cle 'ayoub' n'est pas presente dans l'arbre");
        end if;

        if Cle_Presente(Arbre, Cle5) then
            Put_Line("La cle 'Enna' est presente dans l'arbre");
        else
            Put_Line("La cle 'Enna' n'est pas presente dans l'arbre");
        end if;

        ------------------------------------------------------------------------------------------------------------
        ------------------------------------------------------------------------------------------------------------

        Put_Line("");
        Put_Line("************** Test de la fonction Valeur **************");
        Put_Line("");

        pragma Assert (Valeur(Arbre, Cle1) = '1');
        pragma Assert (Valeur(Arbre, Cle2) = '2');
        pragma Assert (Valeur(Arbre, Cle3) = '3');
        pragma Assert (Valeur(Arbre, Cle4) = '5');
        pragma Assert (Valeur(Arbre, Cle5) = '6');

        Put_Line("La valeur de la cle 'ayoub' est : " & Character'Image(Valeur(Arbre, Cle1)));
        Put_Line("La valeur de la cle 'aya' est : " & Character'Image(Valeur(Arbre, Cle2)));
        Put_Line("La valeur de la cle 'Enseeiht' est : " & Character'Image(Valeur(Arbre, Cle3)));
        Put_Line("La valeur de la cle 'Ens' est : " & Character'Image(Valeur(Arbre, Cle4)));
        Put_Line("La valeur de la cle 'Enna' est : " & Character'Image(Valeur(Arbre, Cle5)));

        ---------------------------------------------------------------------------------------------------------------------------
        ---------------------------------------------------------------------------------------------------------------------------
        
        -- Fin des tests
        Put_Line("");
        Put_Line("************** Fin du test **************");
    end Test_De_Arbre;

begin
    Test_De_Arbre;
end Test_Arbre;
