-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Implantation du module Arbre.

with Ada.Text_IO;  use Ada.Text_IO;
with Ada.Unchecked_Deallocation;
with Exceptions;   use Exceptions;

package body Arbre is

    procedure Free is
        new Ada.Unchecked_Deallocation (Object => T_Noeud, Name => T_ABR);

    -- Initialisation de l'arbre.
    procedure Initialiser (A : out T_ABR) is
    begin
        A := null;
    end Initialiser;

    -- Vériier si l'arbre est vide.
    function Est_Vide (A : in T_ABR) return Boolean is
    begin
        return A = null;
    end Est_Vide;

    -- Vérifier si le noeud est une feuille.
    function Est_Feuille (A : in T_ABR) return Boolean is
    begin
        return A.all.Premier_Fils = null;
    end Est_Feuille;

    -- La taille de l'arbre.
    function Taille (A : in T_ABR) return Integer is
        Size : Integer;
    begin
        if A = null then
            return 0;
        else
            Size := 1;
            return Size + Taille (A.all.Premier_Fils) + Taille (A.all.Frere_Suivant);
        end if;
    end Taille;

    -- Si une clé existe dans l'arbre.
    function Cle_Presente (A : in T_ABR; C : in T_Cle) return Boolean is
    begin
        if A = null then
            return False;
        else
            if A.Cle = C and A.Racine = False then
                return True;
            else
                return Cle_Presente (A.Frere_Suivant, C) or Cle_Presente (A.Premier_Fils, C);
            end if;
        end if;
    end Cle_Presente;

    -- Insérer un noeud dans l'arbre.
    procedure Inserer (A : in out T_ABR; C : in T_Cle; CP : in T_Cle; D : in T_Donnee) is
    NouveauNoeud : T_ABR;
    begin
        if Cle_Presente (A, C) then
            raise Cle_Existante;
        else if A = null then
            A := new T_Noeud'(C, D, True, null, null, null);
        else
            if A.Cle = CP then
                NouveauNoeud := new T_Noeud'(C, D, False, A, null, null);
                NouveauNoeud.Frere_Suivant := A.Premier_Fils;
                A.Premier_Fils := NouveauNoeud;
            else
                Inserer (A.Frere_Suivant, C, CP, D);
                Inserer (A.Premier_Fils, C, CP, D);
            end if;
        end if;
        end if;
    end Inserer;

    -- Supprimer un noeud de l'arbre.
    procedure Supprimer (A : in out T_ABR; C : in T_Cle) is
    function SupprimerRecurssive (A : in out T_ABR; C : in T_Cle) return Boolean is
    begin
        if A = null then
            return False;
        else
            if A.Cle = C then
                if A.Racine = True then
                    Free (A);
                    A := null;
                else
                    Free (A);
                    A := null;
                    return True;
                end if;
            else
                if SupprimerRecurssive (A.Frere_Suivant, C) then
                    return True;
                else
                    return SupprimerRecurssive (A.Premier_Fils, C);
                end if;
            end if;
        end if;
        return False;
    end SupprimerRecurssive;
    begin
    if not SupprimerRecurssive (A, C) then
        raise Cle_Inexistante;
    end if;
end Supprimer;

    -- Detruire l'arbre.
    procedure Detruire (A : in out T_ABR) is
    begin
        if A /= null then
            Detruire (A.Frere_Suivant);
            Detruire (A.Premier_Fils);
            Free (A);
        end if;
    end Detruire;

    -- Rechercher la valeur de l'arrète d'un noeud.
    function La_Donnee (A : in T_ABR; C : in T_Cle) return T_Donnee is
    Fils : T_ABR;
    begin
    if A = null then
        raise Cle_Inexistante;
    else
        if A.Cle = C then
            return A.Donnee;
        else
            Fils := A.Premier_Fils;
            while Fils /= null loop
                if Fils.Cle = C then
                    return Fils.Donnee;
                end if;
                Fils := Fils.Frere_Suivant;
            end loop;
            return La_Donnee (A.Frere_Suivant, C);
        end if;
    end if;
    end La_Donnee;

    -- Changer la donnée d'une clé
    procedure Changer_Donnee (A : in out T_ABR; C : in T_Cle; D : in T_Donnee) is
    Fils : T_ABR;
    begin
    if A = null then
        raise Cle_Inexistante;
    else
        if A.Cle = C then
            A.Donnee := D;
        else
            Fils := A.Premier_Fils;
            while Fils /= null loop
                if Fils.Cle = C then
                    Fils.Donnee := D;
                end if;
                Fils := Fils.Frere_Suivant;
            end loop;
            Changer_Donnee (A.Frere_Suivant, C, D);
        end if;
    end if;
    end Changer_Donnee;


    -- Rechercher la valeur des

    -- Traiter un noeud.
    procedure Traiter (A : in T_ABR) is
    Fils : T_ABR;
    begin
    if A = null then
        raise Cle_Inexistante;
    else if Est_Feuille (A) then
        Traiter_Noeud (A, A.Cle);
    else
        Traiter_Noeud (A, A.Cle);
        Fils := A.Premier_Fils;
        while Fils /= null loop
            Traiter (Fils);
            Fils := Fils.Frere_Suivant;
        end loop;
    end if;
    end if;
    end Traiter;
        

end Arbre;



