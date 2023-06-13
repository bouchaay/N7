with LCA;
-- Définition de structures de données associatives sous forme d'une liste
-- chaînée associative (LCA).
generic
    type T_Cle is private;
    type T_Donnee is private;
    CAPACITE: Integer;
    with function traiter_cle_hachage (Cle : in T_Cle) return Integer;

package TH is

    type T_TH is limited private;

	-- Initialiser une TH.  La TH est vide.
	procedure Initialiser(Th: out T_TH) with
		Post => Est_Vide (Th);

	-- Est-ce qu'un Th est vide ?
	function Est_Vide (Th : T_TH) return Boolean;

	-- Obtenir le nombre d'éléments dans un Th.
	function Taille (Th : in T_TH) return Integer with
		Post => Taille'Result >= 0
            and (Taille'Result = 0) = Est_Vide (Th)
            and Taille'Result <= CAPACITE;

    -- donne la taille de la cellule associée à la Cle.
    -- Definit les Post Condition de Enregister.
    function Taille_Cellule (Th : in T_TH; Cle : in T_Cle) return Integer;


	-- Enregistrer une Donnée associée à une Clé dans une Th.
    -- Si la clé est déjà présente dans la Th, sa donnée est changée;
	procedure Enregistrer (Th : in out T_TH ; Donnee : in T_Donnee; Cle : in T_Cle) with
		Post => Cle_Presente (Th, Cle) and (La_Donnee (Th, Cle) = Donnee)
            and (not (Cle_Presente (Th, Cle)'Old) or Taille_Cellule (Th, Cle) = Taille_Cellule (Th, Cle)'Old)
            and (Cle_Presente (Th, Cle)'Old or Taille_Cellule (Th, Cle) = Taille_Cellule (Th, Cle)'Old + 1);

	-- Supprimer la Donnée associée à une Clé dans une Th.
	-- Exception : Cle_Absente_Exception si Clé n'est pas utilisée dans la Th
	procedure Supprimer (Th : in out T_TH ; Cle : in T_Cle) with
		Post =>  Taille_Cellule (Th, Cle) = Taille_Cellule (Th, Cle)'Old - 1
			and not Cle_Presente (Th, Cle);         -- un element de moins et la clé a été supprimée


	-- Savoir si une Clé est présente dans une Th.
	function Cle_Presente (Th : in T_TH ; Cle : in T_Cle) return Boolean;

    -- Note perso: Cle presente: On cherche l'indice du tableau associée a la cle et on
    -- cherche dans la cellule du Th si la cle est dedant.
    -- On utilise la fonction de hachage!!!


	-- Obtenir la donnée associée à une Cle dans la Th.
	-- Exception : Cle_Absente_Exception si Clé n'est pas utilisée dans la Th
	function La_Donnee (Th : in T_TH ; Cle : in T_Cle) return T_Donnee;


	-- Supprimer tous les éléments d'une Th.
	procedure Vider (Th : in out T_TH) with
		Post => Est_Vide (Th);


	-- Appliquer un traitement (Traiter) pour chaque couple d'uns LCA de la celule associée du Th.
	generic
		with procedure Traiter_TH (Cle : in T_Cle; Donnee: in T_Donnee);
    procedure Pour_Chaque (Th : in out T_TH);



private

    package LCA_TH is new LCA(T_Cle => T_Cle, T_Donnee => T_Donnee);
    use LCA_TH;

    type T_TH is array (1..CAPACITE) of LCA_TH.T_LCA;

end TH;
