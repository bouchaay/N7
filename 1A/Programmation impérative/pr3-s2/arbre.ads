-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Spécification du module Arbre Préfixe

generic

    -- Type de la clé de l'arbre
    type T_Cle is private;

    -- Type de donnée associé à l'arrète
    type T_Donnee is private;

package Arbre is

    -- Type abstrait d'arbre préfixe
    type T_ABR is limited private;

    -- Intialiser un arbre vide
    procedure Initialiser (A : out T_ABR) with
        Post => Est_Vide (A);

    -- Est ce que l'arbre est vide ?
    function Est_Vide (A : in T_ABR) return Boolean;

    -- Est ce que le noeud est une feuille ?
    function Est_Feuille (A : in T_ABR) return Boolean with
        Pre => not Est_Vide (A);

    -- Taille de l'arbre
    function Taille (A : in T_ABR) return Integer;

    -- Présence d'une clé dans l'arbre
    function Cle_Presente (A : in T_ABR; C : in T_Cle) return Boolean with
        Pre => not Est_Vide (A);

    -- Insérer une clé et une valeur dans l'arbre
    procedure Inserer (A : in out T_ABR; C : in T_Cle; CP : in T_Cle; D : in T_Donnee) with
        Post => not Est_Vide (A);

    -- Supprimer une clé et sa valeur de l'arbre
    procedure Supprimer (A : in out T_ABR; C : in T_Cle) with
        Pre => not Est_Vide (A);

    -- Supprimer une clé de l'arbre
    procedure Detruire (A : in out T_ABR) with
        Pre => not Est_Vide (A);

    -- Rechercher une clé dans l'arbre
    function La_Donnee (A : in T_ABR; C : in T_Cle) return T_Donnee with
        Pre => not Est_Vide (A);

    -- Changer la donnée associée à un noeud
    procedure Changer_Donnee (A : in out T_ABR; C : in T_Cle; D : in T_Donnee) with
        Pre => not Est_Vide (A);

    -- Traiter les éléments de l'arbre
    generic
        with procedure Traiter_Noeud (A : T_ABR; C : in T_Cle);
        procedure Traiter (A : T_ABR);

private

    -- Type d'un noeud de l'arbre
    type T_Noeud;

    -- Type d'un pointeur vers un noeud de l'arbre
    type T_ABR is access T_Noeud;

    -- Le noeud de l'arbre
    type T_Noeud is record
        Cle : T_Cle;
        Donnee : T_Donnee;
        Racine : Boolean;
        Pere : T_ABR;
        Premier_Fils : T_ABR;
        Frere_Suivant : T_ABR;
    end record;

end Arbre;
