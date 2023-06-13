-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Implantation du module Arbre.

with Ada.Text_IO;  use Ada.Text_IO;
with Ada.Unchecked_Deallocation;
with Exceptions;   use Exceptions;

package body Arbre is

    -- Procedure de libération de la mémoire.
    procedure Free is
        new Ada.Unchecked_Deallocation (Object => T_Noeud, Name => T_Arbre);

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Comparter si un bout de clé est identique à un clé
    -- Paramètre C1 : Première clé.
    -- Paramètre C2 : Deuxième clé.
    -- Retourne True si une clé est identique à un bout de clé, False sinon.
    function ComparerCleBout (C1 : in out T_File_Abr.T_File; C2 : in out T_File_Abr.T_File) return Boolean is
    CopieCle1 : T_File_Abr.T_File := C1; -- Copie de la première clé.
    CopieCle2 : T_File_Abr.T_File := C2; -- Copie de la deuxième clé.
    begin
        if Taille(File => C1) > Taille(File => C2) then
            for I in 1..Taille(File => C2) loop
                if Extraire(File => CopieCle1) /= Extraire(File => CopieCle2) then
                    return False;
                end if;
            end loop;
        else
            for I in 1..Taille(File => C1) loop
                if Extraire(File => CopieCle1) /= Extraire(File => CopieCle2) then
                    return False;
                end if;
            end loop;
        end if;
        return True;
    end ComparerCleBout;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Comparer deux clés.
    -- Paramètre C1 : Première clé.
    -- Paramètre C2 : Deuxième clé.
    -- Retourne True si les deux clés sont égales, False sinon.
    function ComparerCles (C1 : in out T_File_Abr.T_File; C2 : in out T_File_Abr.T_File) return Boolean is
    CopieCle1 : T_File_Abr.T_File := C1; -- Copie de la première clé.
    CopieCle2 : T_File_Abr.T_File := C2; -- Copie de la deuxième clé.
    begin
        if Taille(File => C1) /= Taille(File => C2) then
            return False;
        else
            for I in 1..Taille(File => C1) loop
                if Extraire(File => CopieCle1) /= Extraire(File => CopieCle2) then
                    return False;
                end if;
            end loop;
        end if;
        return True;
    end ComparerCles;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Créer une nouvelle clé copie d'une autre.
    -- Paramètre C : Clé à copier.
    -- Retourne la copie de la clé.
    function CopierCle (C : in out T_File_Abr.T_File) return T_File_Abr.T_File is
    CopieCleSortie : T_File_Abr.T_File; -- Cle de sortie.
    CopieCleSource : T_File_Abr.T_File := C; -- Cle source.
    ElementExtraire : T_Valeur_Arete; -- Element extrait.
    begin
        Initialiser(File => CopieCleSortie);
        for I in 1..Taille(File => C) loop
            ElementExtraire := Extraire(File => CopieCleSource);
            Inserer(File => CopieCleSortie, Element => ElementExtraire);
        end loop;
        return CopieCleSortie;
    end CopierCle;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Initialisation de l'arbre.
    procedure Initialiser (A : out T_Arbre) is
    CleRacine : T_File_Abr.T_File; -- Clé de la racine.
    TableauFils : T_File_Fils.T_File; -- Tableau des fils.
    begin
        Initialiser(File => CleRacine);
        Initialiser(File => TableauFils);
        A := new T_Noeud'(Information_Arete => V_Arete_Vide, Information_Noeud => V_Noeud_Vide, Cle => CleRacine, Fils => TableauFils, ContientInformation => False);
    end Initialiser;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Retourner le nombre des noeuds qui contiennent une information.
    function Taille (A : T_Arbre) return Natural is
    Taille_Arbre : Natural := 0;
    begin
        if A = null then
            return 0;
        else
            if A.ContientInformation then
                Taille_Arbre := Taille_Arbre + 1;
            end if;
            for I in 1..A.Fils.Taille loop
                Taille_Arbre := Taille_Arbre + Taille (A.Fils.Elements (I));
            end loop;
        end if;
        return Taille_Arbre;
    end Taille;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Retourner si l'arbre est une feuille.
    function Est_Feuille (A : T_Arbre) return Boolean is
    begin
        return A.Fils.Taille = 0;
    end Est_Feuille;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Retourner si la valeur d'un nœud est présente dans l'arbre.
    function Valeur_Noeud_Presente (A : T_Arbre; V : T_Valeur_Noeud) return Boolean is
        Present : Boolean := False;
    begin
        if A = null then
            return False;
        end if;

        if A.Information_Noeud = V then
            Present := True;
        end if;

        if A.Fils.Taille > 0 then
            for I in 1..A.Fils.Taille loop
                if Valeur_Noeud_Presente (A.Fils.Elements (I), V) then
                    Present := True;
                end if;
            end loop;
        end if;

        return Present;
    end Valeur_Noeud_Presente;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Vérifier si une clé est présente dans l'arbre.
    function Cle_Presente (A : T_Arbre; C : T_File_Abr.T_File) return Boolean is
        CopieCle : T_File_Abr.T_File := C; -- Copie de la clé.
    begin
        if A = null then
            return False;
        else
            if ComparerCles (C1 => A.Cle, C2 => CopieCle) then
                return True;
            else
                for I in 1..A.Fils.Taille loop
                    if Cle_Presente (A.Fils.Elements (I), C) then
                        return True;
                    end if;
                end loop;
            end if;
        end if;
        return False;
    end Cle_Presente;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Retourner la valeur d'un noeud.
    function Valeur (A : T_Arbre; C : T_File_Abr.T_File) return T_Valeur_Noeud is
        CopieCle : T_File_Abr.T_File := C; -- Copie de la clé.
        Valeur_Noeud : T_Valeur_Noeud; -- Valeur du noeud.
        Trouve : Boolean := False; -- Indique si la valeur est trouvée.
    begin
        if A = null then
            raise Noeud_Inexistant;
        else
            if ComparerCles (C1 => A.Cle, C2 => CopieCle) then
                Valeur_Noeud := A.Information_Noeud;
                Trouve := True;
                return Valeur_Noeud;
            else
                for I in 1..A.Fils.Taille loop
                    if Cle_Presente (A.Fils.Elements (I), C) then
                        Valeur_Noeud := Valeur (A.Fils.Elements (I), C);
                        Trouve := True;
                        exit;
                    end if;
                end loop;
                if not Trouve then
                    raise Noeud_Inexistant;
                else
                    return Valeur_Noeud;
                end if;
            end if;
        end if;
    end Valeur;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- la variable globale qui contient l'itération courante.
    Iteration : Integer := 1; -- Itération courante.
    Cle_Courante : T_File_Abr.T_File; -- Clé courante.
    TailleCle : Integer; -- Taille de la clé.

    -- Ajouter une clé à l'arbre.
    procedure Ajouter (A : in out T_Arbre; C : T_File_Abr.T_File; V : T_Valeur_Noeud) is

        -- Procedure pour voir si la valeur du noeud est présente dans les fils d'un noeud.
        -- Paramètre A : Noeud dans lequel on cherche.
        -- Paramètre V : Valeur du noeud à chercher.
        -- Retourne l'indice du fils si la valeur est présente, 0 sinon.        
        function Est_Present_Dans_Fils (A : T_Arbre; C : T_File_Abr.T_File) return Integer is
            CopieCleInter : T_File_Abr.T_File := C; -- Copie de la clé.
        begin
            if A.Fils.Taille = 0 then
                return 0;
            else
                for I in 1..A.Fils.Taille loop
                    if ComparerCles (C1 => A.Fils.Elements (I).Cle, C2 => CopieCleInter) then
                        return I;
                    end if;
                end loop;
            end if;
            return 0;
        end Est_Present_Dans_Fils;

        -- Ajouter un fils à l'arbre A.
        -- Paramètre A : Noeud auquel on ajoute un fils.
        -- Paramètre C : Clé du fils à ajouter.
        -- Paramètre VN : Valeur du noeud du fils à ajouter.
        -- Paramètre VA : Valeur de l'arête du fils à ajouter.
        -- Paramètre Contient : Indique si le fils contient une information.
        procedure AjouterFils (A : in out T_Arbre; C : T_File_Abr.T_File; VN : T_Valeur_Noeud; VA : T_Valeur_Arete; Contient : Boolean) is
            B : T_Arbre; -- Noeud à ajouter.
            TableauFils : T_File_Fils.T_File; -- Tableau des fils.
        begin
            Initialiser(File => TableauFils);
            B := new T_Noeud'(Information_Arete => VA, Information_Noeud => VN, Cle => C, Fils => TableauFils, ContientInformation => Contient);
            Inserer(File => A.Fils, Element => B); -- On ajoute le noeud au tableau des fils.
        end AjouterFils;

        -- Paramètres :
        Bout_De_Cle_Courant : T_Valeur_Arete; -- Bout de clé courant.
        FileCopie : T_File_Abr.T_File := C; -- Copie de la clé.
        Indice_Noeud_Present : Integer; -- Indice du noeud présent dans les fils.

    begin

        -- Si on est dans la première itération, on initialise la clé courante.
        if Iteration = 1 then
            Initialiser(File => Cle_Courante); -- On initialise la clé courante.
            TailleCle := Taille(File => C); -- On récupère la taille de la clé.
        end if;

        -- Incrémentation de l'itération.
        Iteration := Iteration + 1;

        -- Récupération du bout de clé courant.
        Bout_De_Cle_Courant := Extraire(File => FileCopie);

        -- Ajout du bout de clé courant à la clé courante.
        Inserer(File => Cle_Courante, Element => Bout_De_Cle_Courant);

        -- Si le noeud n'a pas de fils.
        if A.Fils.Taille = 0 then

            -- Si la clé courante est plus petite que la clé à ajouter, on ajoute un fils vide.
            if Taille(Cle_Courante) < TailleCle then
                AjouterFils (A => A, C => Cle_Courante, VN => V_Noeud_Vide, VA => Bout_De_Cle_Courant, Contient => False); -- On ajoute un fils vide.
                Ajouter (A.Fils.Elements (A.Fils.Taille), FileCopie, V); -- On descend dans l'arbre.
                Iteration := 1;
                return;

            -- Sinon, on ajoute la clé complète.                        
            else
                -- Si la valeur n'est pas présente dans les fils, on ajoute la clé complète.
                if Est_Present_Dans_Fils (A => A, C => Cle_Courante) = 0 then
                    AjouterFils (A => A, C => Cle_Courante, VN => V, VA => Bout_De_Cle_Courant, Contient => True); -- On ajoute la clé complète.
                    Iteration := 1;
                    return;
                -- Sinon, on change la valeur du noeud.
                else
                    Indice_Noeud_Present := Est_Present_Dans_Fils (A => A, C => Cle_Courante); -- On récupère l'indice du noeud présent.
                    A.Fils.Elements (Indice_Noeud_Present).Information_Noeud := V; -- On change la valeur du noeud.
                    A.Fils.Elements (Indice_Noeud_Present).ContientInformation := True; -- On indique que le noeud contient une information.
                    return;
                end if;                                
            end if;

        -- Si le noeud a des fils.
        else
            -- On cherche si le bout de clé courant est présent dans les fils.
            for I in 1..A.Fils.Taille loop
                if A.Fils.Elements (I).Information_Arete = Bout_De_Cle_Courant then
                    if Taille(File => Cle_Courante) = TailleCle then
                        A.Fils.Elements (I).Information_Noeud := V; -- On change la valeur du noeud.
                        A.Fils.Elements (I).ContientInformation := True; -- On indique que le noeud contient une information.
                        Iteration := 1;
                        return;
                    end if;
                    Ajouter (A.Fils.Elements (I), FileCopie, V);
                    Iteration := 1;
                    return;
                end if;
            end loop;

            -- Sinon, on ajoute le bout de clé courant aux fils.
            -- Si la clé courante est plus petite que la clé à ajouter, on ajoute un fils vide.            
            if Taille(Cle_Courante) < TailleCle then
                AjouterFils (A => A, C => Cle_Courante, VN => V_Noeud_Vide, VA => Bout_De_Cle_Courant, Contient => False); -- On ajoute un fils vide.
                Ajouter (A.Fils.Elements (A.Fils.Taille), FileCopie, V); -- On descend dans l'arbre.
            
            -- Sinon, on ajoute la clé complète.
            else
                if Est_Present_Dans_Fils (A => A, C => Cle_Courante) = 0 then
                    AjouterFils (A => A, C => Cle_Courante, VN => V, VA => Bout_De_Cle_Courant, Contient => True); -- On ajoute la clé complète.
                    Iteration := 1;
                    return;
                else
                    Indice_Noeud_Present := Est_Present_Dans_Fils (A => A, C => Cle_Courante); -- On récupère l'indice du noeud présent.
                    A.Fils.Elements (Indice_Noeud_Present).Information_Noeud := V; -- On change la valeur du noeud.
                    A.Fils.Elements (Indice_Noeud_Present).ContientInformation := True; -- On indique que le noeud contient une information.
                    Iteration := 1;
                    return;
                end if;
            end if;
            
        end if;
    end Ajouter;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

                
    -- Supprimer une valeur de l'arbre.
    procedure Supprimer (A : in out T_Arbre; V : T_Valeur_Noeud) is
    begin
        if A = null then
            raise Noeud_Inexistant;
        else
            
            -- Si on trouve la valeur, on la supprime.
            if A.Information_Noeud = V then
                Free (A);

            -- Sinon, on cherche dans les fils.
            else
                for I in 1..A.Fils.Taille loop
                    Supprimer (A.Fils.Elements (I), V);
                end loop;
            end if;
        end if;
    end Supprimer;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Afficher l'arbre.
    procedure Afficher (A : T_Arbre; Niveau : in Integer) is
    begin
        if A = null then
            return;
        else
            Put_Line("Niveau " & Integer'Image(Niveau) & " : ");
            Traiter_Noeud(A.Information_Noeud, A.Information_Arete, A.Cle);
            Put_Line("");
            for I in 1..A.Fils.Taille loop
                Afficher (A.Fils.Elements (I), Niveau + 1);
            end loop;
        end if;
    end Afficher;

---------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------

    -- Afficher les clés de l'arbre.
    procedure AfficherTousCles (A : T_Arbre; C : in out T_File_Abr.T_File) is
    CopieCle : T_File_Abr.T_File := C; -- Copie de la clé.
    begin
        if A = null then
            return;
        else
            if ComparerCleBout (C1 => A.Cle, C2 => CopieCle) and A.ContientInformation then
                AfficherCle(C => A.Cle);
            end if;
            if A.Fils.Taille > 0 then
                for I in 1..A.Fils.Taille loop
                    AfficherTousCles (A.Fils.Elements (I), C);
                end loop;
            else 
                return;
            end if;
        end if;
    end AfficherTousCles;

end Arbre;