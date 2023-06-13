-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Spécification du module arbre préfixe

with Files;

generic
    
    -- type de la valeur des arrêtes
    type T_Valeur_Arete is private;

    -- type de la valeur des noeuds
    type T_Valeur_Noeud is private;

    -- type d'un valeur de noeud vide
    V_Noeud_Vide : T_Valeur_Noeud;

    -- type d'un valeur d'arrête vide (racine)
    V_Arete_Vide : T_Valeur_Arete;

    -- nombre de fils d'un noeud
    NbFilsMax : Natural;

package Arbre is

    -- Instanciation d'une file d'éléments de type T_Valeur_Arete
	package T_File_Abr is new Files (T_Element => T_Valeur_Arete, Cap => 100);
	use T_File_Abr;

    type T_Arbre is limited private;

    -- Initialise un arbre avec la racine.
    -- paramètre A : l'arbre à initialiser
    procedure Initialiser (A : out T_Arbre);

        -- Renvoie le nombre de noeuds de l'arbre
    -- paramètre A : l'arbre à tester
    -- renvoie : le nombre de noeuds de l'arbre
    function Taille (A : T_Arbre) return Natural;

    -- Renvoie si un noeud est une feuille
    -- paramètre A : l'arbre à tester
    -- renvoie : vrai si le noeud est une feuille
    function Est_Feuille (A : T_Arbre) return Boolean;

    -- Renvoie vrai si la valeur d'un noeud est présente dans l'arbre
    -- paramètre A : l'arbre à tester
    -- paramètre V : la valeur du noeud
    -- renvoie : vrai si la valeur d'un noeud est présente dans l'arbre
    function Valeur_Noeud_Presente (A : T_Arbre; V : T_Valeur_Noeud) return Boolean;

    -- Renvoie vrai si la clé est présente dans l'arbre
    -- paramètre A : l'arbre à tester
    -- paramètre C : la clé
    -- renvoie : vrai si la clé est présente dans l'arbre
    function Cle_Presente (A : T_Arbre; C : T_File_Abr.T_File) return Boolean;

    -- Renvoie la valeur associée à la clé
    -- paramètre A : l'arbre à tester
    -- paramètre C : la clé
    -- renvoie : la valeur associée à la clé
    function Valeur (A : T_Arbre; C : T_File_Abr.T_File) return T_Valeur_Noeud;

    -- Ajoute une valeur à l'arbre
    -- paramètre A : l'arbre à modifier
    -- C : la clé à ajouter
    procedure Ajouter (A : in out T_Arbre; C : T_File_Abr.T_File; V : T_Valeur_Noeud);

    -- Supprime une valeur de l'arbre
    -- paramètre A : l'arbre à modifier
    -- paramètre V : la valeur du noeud
    procedure Supprimer (A : in out T_Arbre; V : T_Valeur_Noeud);

    -- Affiche l'arbre
    -- paramètre A : l'arbre à traiter
    generic
        with procedure Traiter_Noeud (N : T_Valeur_Noeud; V : T_Valeur_Arete; C : T_File_Abr.T_File);
    procedure Afficher (A : T_Arbre; Niveau : Integer);

    -- Affiche les clés de l'arbre
    -- paramètre A : l'arbre à traiter
    generic
        with procedure AfficherCle (C : T_File_Abr.T_File);
    procedure AfficherTousCles (A : T_Arbre; C : in out T_File_Abr.T_File);

private

    -- Type des noeuds
    type T_Noeud;

    -- Pointeur vers un noeud
    type T_Arbre is access T_Noeud;

    package T_File_Fils is new Files (T_Element => T_Arbre , Cap => NbFilsMax);
	use T_File_Fils;

    -- Structure d'un noeud
    type T_Noeud is record
        Information_Noeud : T_Valeur_Noeud;
        Information_Arete : T_Valeur_Arete;
        Cle : T_File_Abr.T_File;
        Fils : T_File_Fils.T_File;
        ContientInformation : Boolean;
    end record;

end Arbre;