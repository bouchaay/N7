with Ada.Text_IO;     		use Ada.Text_IO;
with Ada.Integer_Text_IO;    	use Ada.Integer_Text_IO;
with Ada.Strings.Unbounded; 	use Ada.Strings.Unbounded;
with Exceptions;	 	use Exceptions;
with Ada.Strings;               use Ada.Strings;
with Ada.Text_IO.Unbounded_IO;  use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Fixed;
with Ada.Exceptions;            use Ada.Exceptions;
with Arbre;	

procedure Suggestion is

    -- Instanciation de l'arbre
    package ArbreSuggestion is new Arbre(T_Valeur_Arete => Unbounded_String, T_Valeur_Noeud => Integer, V_Noeud_Vide => 0, V_Arete_Vide => To_Unbounded_String(" "), NbFilsMax => 1000000);
    use ArbreSuggestion;

    -- Vérifie si un caractère est un espace
    -- Paramètre Caractere : Caractère à tester
    -- Retourne True si le caractère est un caractère spécial, False sinon
    function EstEspace(Caractere : Character) return Boolean is
    begin
        if Caractere = ' ' then
            return True;
        else
            return False;
        end if;
    end EstEspace;

    -- Afficher les suggestions d'un mot (Afficher arbre)
    -- Paramètre Arbre : Arbre à afficher
    procedure AfficherSuggestion(Mot : ArbreSuggestion.T_File_Abr.T_File) is
        Caractere : Unbounded_String;
        CopieMot : ArbreSuggestion.T_File_Abr.T_File := Mot;
    begin
        while ArbreSuggestion.T_File_Abr.Taille(CopieMot) > 0 loop
            Caractere := ArbreSuggestion.T_File_Abr.Extraire(CopieMot);
            Put(Caractere);
        end loop;
        Put_Line("");
    end AfficherSuggestion;

    procedure AfficherLesSuggestions is new ArbreSuggestion.AfficherTousCles(AfficherCle => AfficherSuggestion);


    -- Usage du programme
    procedure Usage is
    begin
        Put_Line("Choisissez une option :");
        Put_Line("1 : Afficher les suggestions d'un mot");
        Put_Line("2 : Quitter");
    end Usage;

    -- Transforme un caractère en string
    -- Paramètre Carac : Caractère à transformer
    -- Retourne le caractère sous forme de string
    function Char_To_String(Carac : Character) return String is
        Ma_Chaine : string(1..1);
    begin
        Ma_chaine(1) := Carac;
        return Ma_Chaine;
    end Char_To_String;

    -- Variables

    Dictionnaire : ArbreSuggestion.T_Arbre; -- Arbre contenant les mots du dictionnaire
    FichierType : File_Type; -- Fichier contenant les mots du dictionnaire
    FichierNom : String := "dictionnaire.txt"; -- Nom du fichier contenant les mots du dictionnaire
    MotSaisi : Unbounded_String; -- Mot saisi par l'utilisateur
    FileDuMot : ArbreSuggestion.T_File_Abr.T_File; -- File du mot saisi
    LigneActuelle : Unbounded_String; -- Ligne actuelle du fichier
    MotActuel : ArbreSuggestion.T_File_Abr.T_File; -- Mot actuel
    CharactereActuel : Unbounded_String; -- Caractère actuel
    Quitter : Boolean := False; -- Booléen permettant de quitter le programme
    Compteur : Integer := 1; -- Compteur

begin

    -- Ouvrir le fichier contenant les mots du dictionnaire (dictionnaire.txt)
    Open(FichierType, In_File, FichierNom);

    -- Initialiser l'arbres des suggestions
    ArbreSuggestion.Initialiser(Dictionnaire);

    -- Initialiser la file du mot actuel
    ArbreSuggestion.T_File_Abr.Initialiser(MotActuel);

    -- Remplir l'arbre des suggestions
    while not End_Of_File(FichierType) loop

        -- Lire la ligne actuelle
        Get_Line(FichierType, LigneActuelle);

        -- Parcourir la ligne et diviser les mots séparé par des espaces
        for i in 1..To_String(LigneActuelle)'Length loop
            
            -- tester si le caractère actuel est un espace
            if EstEspace(To_String(LigneActuelle)(i)) then

                -- Si la taille du mot actuel est supérieur à 0
                if ArbreSuggestion.T_File_Abr.Taille(MotActuel) > 0 then

                    -- Si le mot actuel n'est pas déjà présent dans l'arbre des suggestions
                    if not ArbreSuggestion.Cle_Presente(Dictionnaire, MotActuel) then

                        -- Ajouter le mot actuel à l'arbre des suggestions
                        ArbreSuggestion.Ajouter(Dictionnaire, MotActuel, Compteur);

                        -- Incrémenter le compteur
                        Compteur := Compteur + 1;
                    
                    end if;

                    -- Vider le mot actuel
                    ArbreSuggestion.T_File_Abr.Vider(MotActuel);
                end if;
            
            -- Si le caractère actuel n'est pas un espace    
            else 
                CharactereActuel := To_Unbounded_String(Char_To_String(To_String(LigneActuelle)(i)));
                ArbreSuggestion.T_File_Abr.Inserer(MotActuel, CharactereActuel);
            end if;
        end loop;
    end loop;

    -- Fermer le fichier contenant les mots du dictionnaire
    Close(FichierType);

    -- Initialiser la file du mot saisi
    ArbreSuggestion.T_File_Abr.Initialiser(FileDuMot);

    -- Tant que l'utilisateur ne veut pas quitter
    while not Quitter loop

        Usage;

        -- Lire le mot saisi par l'utilisateur
        Get_Line(MotSaisi);

        if To_String(MotSaisi) = "1" then
            Put_Line("Entrez un mot :");
            Get_Line(MotSaisi);

            -- Inserer le mot saisi dans la file du mot saisi
            for i in 1..To_String(MotSaisi)'Length loop
                CharactereActuel := To_Unbounded_String(Char_To_String(To_String(MotSaisi)(i)));
                ArbreSuggestion.T_File_Abr.Inserer(FileDuMot, CharactereActuel);
            end loop;

            -- Afficher les suggestions
            Put_Line("Suggestions :");
            AfficherLesSuggestions(Dictionnaire, FileDuMot);

            -- Vider la file du mot saisi
            ArbreSuggestion.T_File_Abr.Vider(FileDuMot);

        elsif To_String(MotSaisi) = "2" then
            -- Vider toutes les files
            ArbreSuggestion.T_File_Abr.Vider(FileDuMot);
            ArbreSuggestion.T_File_Abr.Vider(MotActuel);
            Quitter := True;
        
        else
            Put_Line("Option invalide !");
        end if;
    end loop;
end Suggestion;

