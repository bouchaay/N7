-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Spécification du module Arbre Préfixe

generic

    -- Type de clé
    type T_Cle is private;

    -- Type de donnée associé à l'arrète
    type T_Donnee is private;

    -- Valeur de la clé racine
    Cle_Racine : T_Cle;

    -- Maximum de fils
    Max_Fils : Integer;

    -- Maximum de données
    Max_Donnee : Integer;

package Arbre is

    -- Type abstrait d'arbre préfixe
    type T_ABR is limited private;

    -- Type tableau de valeur des arrètes.
    type T_Array_Donnee is array (1 .. Max_Donnee) of T_Donnee;

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

    -- Supprimer une clé de l'arbre
    procedure Supprimer (A : in out T_ABR; C : in T_Cle) with
        Pre => not Est_Vide (A);

    -- Rechercher une clé dans l'arbre
    function La_Donnee (A : in T_ABR; C : in T_Cle) return T_Array_Donnee with
        Pre => not Est_Vide (A);

    -- Traiter les éléments de l'arbre
    generic
        with procedure Traiter_Noeud (C : in T_Cle; V : in T_Array_Donnee);
        procedure Traiter (A : T_ABR);

private

    -- Type d'un noeud de l'arbre
    type T_Noeud;

    -- Type d'un pointeur vers un noeud de l'arbre
    type T_ABR is access T_Noeud;

    -- Type pour les fils de chaque noeud
    type T_Array_Fils is array (1 .. Max_Fils) of T_ABR;

    -- Le noeud de l'arbre
    type T_Noeud is record
        Cle : T_Cle;
        Donnee : T_Array_Donnee;
        Fils : T_Array_Fils;
        NombreFils : Integer;
        NombreDonnee : Integer;  -- -1 si c'est une racine
    end record;

end Arbre;
