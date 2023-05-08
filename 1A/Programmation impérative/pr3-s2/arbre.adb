-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Implantation du module Arbre.

with Ada.Text_IO;  use Ada.Text_IO;
with Exceptions;   use Exceptions;

package body Arbre is

    -- Initialisation de l'arbre.
    procedure Initialiser (A : out T_ABR) is
    begin
        A := null;
        A.all.NombreFils := 0;
        A.all.NombreDonnee := -1;
    end Initialiser;

    -- Vériier si l'arbre est vide.
    function Est_Vide (A : in T_ABR) return Boolean is
    begin
        return A = null;
    end Est_Vide;

    -- Vérifier si le noeud est une feuille.
    function Est_Feuille (A : in T_ABR) return Boolean is
    begin
        return A.NombreFils = 0;
    end Est_Feuille;

    -- La taille de l'arbre.
    function Taille (A : in T_ABR) return Integer is
        Size : Integer;
    begin
        if A = null then
            return 0;
        else
            Size := 1;
            for I in 1 .. A.NombreFils loop
                Size := Size + Taille (A.Fils(I));
            end loop;
            return Size;
        end if;
    end Taille;

    -- Si une clé existe dans l'arbre.
    function Cle_Presente (A : in T_ABR; C : in T_Cle) return Boolean is
    begin
        if A = null then
            return False;
        else
            for I in 1 .. A.NombreFils loop
                if Cle_Presente (A.Fils(I) , C) then
                    return True;
                end if;
            end loop;
            return False;
        end if;
    end Cle_Presente;

    -- Insérer un noeud dans l'arbre.
    procedure Inserer (A : in out T_ABR; C : in T_Cle; CP : in T_Cle; D : in T_Donnee) is
    begin
        if A = null then
            A = new T_Noeud'(Cle => C, NombreFils => 0, NombreDonnee => -1);
            return;
        else
            for I in 1 .. A.NombreFils loop
                if A.Fils(I).Cle = CP then
                    if A.Fils(I).NombreFils < Max_Fils then
                        A.Fils(I).NombreFils := A.Fils(I).NombreFils + 1;
                        A.Fils(I).NombreDonnee := A.Fils(I).NombreDonnee + 1;
                        A.Fils(I).Fils(A.Fils(I).NombreFils).Cle := C;
                        A.Fils(I).Fils(A.Fils(I).NombreFils).Donnee(A.Fils(I).NombreDonnee) := D;
                        A.Fils(I).Fils(A.Fils(I).NombreFils).NombreFils := 0;
                        return;
                    else
                        raise Arbre_Sature;
                    end if;
                end if;
            end loop;
            raise Cle_Inexistante;
        end if;
    end Inserer;

    -- Suprrimer un noeud de l'arbre. Si le pere est supprimé on supprime aussi ses fils.
    procedure Supprimer (A : in out T_ABR; C : in T_Cle) is
    begin
        if A = null then
            raise Cle_Inexistante;
        else
            for I in 1 .. A.NombreFils loop
                if A.Fils(I).Cle = C then
                    A.Fils(I) := null;
                    A.NombreFils := A.NombreFils - 1;
                    Supprimer (A.Fils(I), A.Fils(I).Cle);
                    return;
                else
                    Supprimer (A.Fils(I), C);
                    return;
                end if;
            end loop;
            raise Cle_Inexistante;
        end if;
    end Supprimer;

    -- Rechercher la valeur des arrêtes en utilisant la fonction Chemin.
    function La_Donnee (A : in T_ABR; C : in T_Cle) return T_Array_Donnee is
    TabDonnee : T_Array_Donnee;
    J : Integer := 1;
    begin
    if A = null or else (Est_Feuille (A) and then A.Cle /= C) then
        raise Cle_Inexistante;
    else if A.Cle = C then
        return TabDonnee;
    else
        for I in 1 .. A.NombreFils loop
            if Cle_Presente (A.Fils(I), C) then
                TabDonnee(J) := A.Fils(I).Donnee(1);
                J := J + 1;
                return La_Donnee (A.Fils(I), C);
            end if;
        end loop;
        raise Cle_Inexistante;
    end if;
    end if;
    end La_Donnee;

    -- Traiter un noeud.
    procedure Traiter (A : in T_ABR) is
    begin
        Traiter_Noeud (A.Cle, A.Donnee);
        for I in 1 .. A.NombreFils loop
            Traiter (A.Fils(I));
        end loop;
    end Traiter;

end Arbre;



