with Ada.Text_IO;     		use Ada.Text_IO;
with Ada.Integer_Text_IO;    	use Ada.Integer_Text_IO;
with Ada.Strings.Unbounded; 	use Ada.Strings.Unbounded;
with Exceptions;	 	use Exceptions;
with Ada.Strings;               use Ada.Strings;
with Ada.Text_IO.Unbounded_IO;  use Ada.Text_IO.Unbounded_IO;
with Ada.Strings.Fixed;
with Ada.Exceptions;            use Ada.Exceptions;
with CLI;			use CLI;
with Arbre;	
with Ada.Command_Line; use Ada.Command_Line;

procedure Main is

    -- Instanciation de l'arbre
    package ArbreOccurences is new Arbre(T_Valeur_Arete => Unbounded_String, T_Valeur_Noeud => Unbounded_String, V_Noeud_Vide => To_Unbounded_String("-"), V_Arete_Vide => To_Unbounded_String(" "), NbFilsMax => 26);

    -- Variables

    ArbreDeRecherche : ArbreOccurences.T_Arbre; -- Arbre de recherche
    FichierType : File_Type; -- Fichier à lire
    FichierNom : Unbounded_String; -- Nom du fichier à lire
    Mot : Unbounded_String; -- Mot à rechercher
    FileDuMot : ArbreOccurences.T_File_Abr.T_File; -- File du mot à rechercher
    LigneActuelle : Unbounded_String; -- Ligne actuelle du fichier
    MotActuel : ArbreOccurences.T_File_Abr.T_File; -- Mot actuel
    NombreOccurences : Integer; -- Nombre d'occurences du mot
    CharactereActuel : Unbounded_String; -- Caractère actuel

    -- Vérifie si un caractère est un caractère spécial (Des caractères doivent être ajoutés si besoin)
    -- Paramètre Caractere : Caractère à tester
    -- Retourne True si le caractère est un caractère spécial, False sinon
    function EstCaractereSpecial(Caractere : Character) return Boolean is
    begin
        if Caractere = ' ' or Caractere = '.' or Caractere = ',' or Caractere = ';' or Caractere = ':' or Caractere = '!' or Caractere = '?' or Caractere = '-' or Caractere = '\' or Caractere = '"' or Caractere = '(' or Caractere = ')' or Caractere = '/' then
            return True;
        else
            return False;
        end if;
    end EstCaractereSpecial;

    -- Transforme un caractère en string
    -- Paramètre Carac : Caractère à transformer
    -- Retourne le caractère sous forme de string
    function Char_To_String(Carac : Character) return String is
        Ma_Chaine : string(1..1);
    begin
        Ma_chaine(1) := Carac;
        return Ma_Chaine;
    end Char_To_String;

    -- Incrémente la valeur d'une clé dans l'arbre
    -- Paramètre Arbre : Arbre dans lequel on veut incrémenter la valeur
    -- Paramètre Cle : Clé dont on veut incrémenter la valeur
    procedure Incrementer_Valeur(Arbre : in out ArbreOccurences.T_Arbre; Cle : ArbreOccurences.T_File_Abr.T_File) is
        Valeur : Unbounded_String;
        ValeurInt : Integer;
    begin
        Valeur := ArbreOccurences.Valeur(Arbre, Cle);
        ValeurInt := Integer'Value(to_String(Valeur)) + 1;
        Valeur := To_Unbounded_String(Integer'Image(ValeurInt));
        ArbreOccurences.Ajouter(Arbre, Cle, Valeur);
    end Incrementer_Valeur;

    -- Affiche un mot (file)
    -- Paramètre Mot : Mot à afficher
    procedure AfficherMot(Mot : ArbreOccurences.T_File_Abr.T_File) is
        Caractere : Unbounded_String;
        CopieMot : ArbreOccurences.T_File_Abr.T_File := Mot;
    begin
        while ArbreOccurences.T_File_Abr.Taille(CopieMot) > 0 loop
            Caractere := ArbreOccurences.T_File_Abr.Extraire(CopieMot);
            Put(Caractere);
        end loop;
    end AfficherMot;

begin

    -- Parsing des arguments
    ParseCmdLine(Mot, FichierNom);

    -- Initialiser la file du mot actuel
    ArbreOccurences.T_File_Abr.Initialiser(MotActuel);

    -- Transformer le mot à rechercher en file
    ArbreOccurences.T_File_Abr.Initialiser(FileDuMot);
    for i in 1..To_String(Mot)'Length loop
        CharactereActuel := To_Unbounded_String(Char_To_String(To_String(Mot)(i)));
        ArbreOccurences.T_File_Abr.Inserer(FileDuMot, CharactereActuel);
    end loop;

    -- Afficher le mot à rechercher
    Put("Mot a rechercher : ");
    AfficherMot(FileDuMot);
    Put_Line("");

    -- Création de l'arbre (stocker tout les mots du fichier avec leur nombre d'occurences)
    ArbreOccurences.Initialiser(ArbreDeRecherche);

    -- Ouvrir le fichier
    Open(FichierType, In_File, To_String(FichierNom));

    -- Lire le fichier
    while not End_Of_File(FichierType) loop

        -- Lire une ligne
        Get_Line(FichierType, LigneActuelle);

        -- Prendre chaque mot de la ligne et tester s'il existe dans l'arbre
        for i in 1..To_String(LigneActuelle)'Length loop

            -- On prend le mot actuel si on rencontre un caractère spécial
            if EstCaractereSpecial(To_String(LigneActuelle)(i)) then

                -- On a un mot, on le cherche dans l'arbre
                if ArbreOccurences.T_File_Abr.Taille(MotActuel) > 0 then

                    -- On incrémente le nombre d'occurences du mot si il est déjà présent dans l'arbre
                    if ArbreOccurences.Cle_Presente(A => ArbreDeRecherche, C => MotActuel) then
                        Incrementer_Valeur(ArbreDeRecherche, MotActuel);

                    -- Sinon on l'ajoute à l'arbre (avec 1 occurence)
                    else
                        ArbreOccurences.Ajouter(ArbreDeRecherche, MotActuel, To_Unbounded_String("1"));
                    end if;

                    -- On vide le mot actuel
                    ArbreOccurences.T_File_Abr.Vider(MotActuel);
                end if;
            
            -- Sinon, on ajoute le caractère au mot actuel
            else
                CharactereActuel := To_Unbounded_String(Char_To_String(To_String(LigneActuelle)(i))); -- On transforme le caractère en string
                ArbreOccurences.T_File_Abr.Inserer(MotActuel, CharactereActuel); -- On l'insère dans le mot actuel
            end if;
        end loop;
    end loop;
    
    -- Afficher le nombre d'occurences du mot (On a géré l'exception si le mot n'est pas présent dans l'arbre)
    begin
        NombreOccurences := Integer'Value(To_String(ArbreOccurences.Valeur(ArbreDeRecherche, FileDuMot)));
        Put_Line("Le mot " & To_String(Mot) & " apparait " & Integer'Image(NombreOccurences) & " fois dans le fichier " & To_String(FichierNom));
    exception
        when Noeud_Inexistant => Put_Line("Le mot " & To_String(Mot) & " n'apparait pas dans le fichier " & To_String(FichierNom));
        when others => Put_Line("Erreur inconnue");
    end;

    -- Fermer le fichier
    Close(FichierType);

end Main;
