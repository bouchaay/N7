with Ada.Text_IO;     		use Ada.Text_IO;
with Ada.Integer_Text_IO;    	use Ada.Integer_Text_IO;
with Ada.Strings.Unbounded; 	use Ada.Strings.Unbounded;
with Exceptions;	 	use Exceptions;
with Ada.Strings;               use Ada.Strings;
with Ada.Text_IO.Unbounded_IO;  use Ada.Text_IO.Unbounded_IO;
with Ada.Exceptions;            use Ada.Exceptions;	-- pour Exception_Messagebounded_IO;
with CLI;			use CLI;
with Arbre;	
with Ada.Command_Line; use Ada.Command_Line;

procedure Main is

    package ArbreOccurences is new Arbre(T_Cle => Unbounded_String, T_Donnee => Integer);

    ArbreDeRecherche : ArbreOccurences.T_ABR;
    FichierType : File_Type;
    FichierNom : Unbounded_String;
    Mot : Unbounded_String;
    LigneActuelle : Unbounded_String;
    MotActuel : Unbounded_String;
    NombreOccurences : Integer;
    CleRacine : Unbounded_String := To_Unbounded_String("Racine");
    CleRacinePere : Unbounded_String := To_Unbounded_String("Sans Père");

    procedure Incrementer(A : in out ArbreOccurences.T_ABR; Cle : in Unbounded_String) is
        Donnee : Integer;
    begin
        Donnee := ArbreOccurences.La_Donnee(A, Cle);
        ArbreOccurences.Changer_Donnee(A, Cle, Donnee + 1);
    end Incrementer;

    begin

    -- Parsing des arguments
    ParseCmdLine(Mot, FichierNom);

    -- Création de l'arbre
    ArbreOccurences.Initialiser(ArbreDeRecherche);

    -- Initialiser la racine
    ArbreOccurences.Inserer(ArbreDeRecherche, CleRacine, CleRacinePere, 0);

    -- Ouvrir le fichier
    Open(FichierType, In_File, To_String(FichierNom));

    -- Lire le fichier
    while not End_Of_File(FichierType) loop
        Get_Line(FichierType, LigneActuelle);
        -- Prendre chaque mot de la ligne et tester s'il existe dans l'arbre
        for i in 1..To_String(LigneActuelle)'Length loop
            if To_String(LigneActuelle)(i) = ' ' or To_String(LigneActuelle)(i) = '.' or To_String(LigneActuelle)(i) = ',' or To_String(LigneActuelle)(i) = ';' or To_String(LigneActuelle)(i) = ':' or To_String(LigneActuelle)(i) = '!' or To_String(LigneActuelle)(i) = '?' or To_String(LigneActuelle)(i) = '-' or To_String(LigneActuelle)(i) = '\' then
                if To_String(MotActuel)'Length > 0 then
                    -- On a un mot, on l'insère dans l'arbre
                    if ArbreOccurences.Cle_Presente(A => ArbreDeRecherche, C => MotActuel) then
                        Incrementer(ArbreDeRecherche, MotActuel);
                    else
                        ArbreOccurences.Inserer(ArbreDeRecherche, MotActuel, CleRacine, 1);
                    end if;
                    MotActuel := To_Unbounded_String("");
                end if;
            else
                MotActuel := MotActuel & To_String(LigneActuelle)(i);
            end if;
        end loop;
    end loop;
    
    -- Afficher le nombre d'occurences du mot
    NombreOccurences := ArbreOccurences.La_Donnee(ArbreDeRecherche, Mot);
    Put_Line("Le mot " & To_String(Mot) & " apparait " & Integer'Image(NombreOccurences) & " fois dans le fichier " & To_String(FichierNom));

    -- Fermer le fichier
    Close(FichierType);

    -- Destruction de l'arbre
    ArbreOccurences.Detruire(ArbreDeRecherche);

end Main;
