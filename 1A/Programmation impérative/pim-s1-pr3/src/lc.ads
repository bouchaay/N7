------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Specification du module LC 					|
------------------------------------------------------------------------------------------------*	


-- Definition d'une table de routage sous forme d'une liste
-- chainee (LC).
generic
	type T_Cle is private;
	type T_Donnee is private;

package LC is

	type T_LC is limited private;
	
	-- TRT => Table De Routage.
	-- Initialiser une TRT.  La TRT est vide.
	procedure Initialiser(TRT: out T_LC) with
		Post => Est_Vide (TRT);


	-- Est-ce qu'une TRT est vide ?
	function Est_Vide (TRT : T_LC) return Boolean;


	-- Obtenir le nombre d' elements d'une TRT. 
	function Taille (TRT : in T_LC) return Integer with
		Post => Taille'Result >= 0
			and (Taille'Result = 0) = Est_Vide (TRT);


	-- Enregistrer une Donnee associee a une Cle dans une TRT.
	-- Si la cle est deja presente dans la TRT, sa donnee est changee.
	procedure Enregistrer (TRT : in out T_LC ; Cle : in T_Cle ; Donnee : in T_Donnee) with
		Post => Cle_Presente (TRT, Cle) and (La_Donnee (TRT, Cle) = Donnee)   -- donnee inseree
				and (not (Cle_Presente (TRT, Cle)'Old) or Taille (TRT) = Taille (TRT)'Old)
				and (Cle_Presente (TRT, Cle)'Old or Taille (TRT) = Taille (TRT)'Old + 1);

	-- Supprimer la Donnee associee a une Cle dans une TRT.
	-- Exception : Cle_Absente_Exception si Cle n'est pas utilisee dans la TRT.
	procedure Supprimer (TRT : in out T_LC ; Cle : in T_Cle) with
		Post =>  Taille (TRT) = Taille (TRT)'Old - 1 -- un element de moins
			and not Cle_Presente (TRT, Cle);         -- la cle a ete supprimee


	-- Savoir si une Cle est presente dans une TRT.
	function Cle_Presente (TRT : in T_LC ; Cle : in T_Cle) return Boolean;


	-- Obtenir la donnee associee a une Cle dans la TRT.
	-- Exception : Cle_Absente_Exception si Cle n'est pas utilisee dans la TRT.
	function La_Donnee (TRT : in T_LC ; Cle : in T_Cle) return T_Donnee;


	-- Supprimer tous les elements d'une TRT.
	procedure Vider (TRT : in out T_LC) with
		Post => Est_Vide (TRT);


	-- Enregistrer un element a la fin d'une TRT.
	procedure Enregistrer_Fin (TRT : in out T_LC ; Cle : in T_Cle ; Donnee : in T_Donnee) with
		Post => La_Donnee(TRT, Cle) = Donnee and Taille(TRT) = Taille(TRT)'Old + 1;


	-- Supprimer l'element le plus haut d'une TRT.	
	procedure Suppression_Haute (TRT : in out T_LC) with
		Post => Taille(TRT) = Taille(TRT)'Old - 1;


	-- Retrouver la cle et la donnee de la cellule en indice.	
	procedure Element_En_Indice (TRT : in T_LC ; Indice : In Integer ; Cle : Out T_Cle ; Donnee : Out T_Donnee) with
		Post => Cle_Presente(TRT, Cle) and La_Donnee(TRT, Cle) = Donnee;


	-- Appliquer un traitement (Traiter) pour chaque couple d'une TRT.
	generic
		with procedure Traiter (Cle : in T_Cle; Donnee: in T_Donnee);
	procedure Pour_Chaque (TRT : in T_LC);

private

	type T_Cellule;

	type T_LC is access T_Cellule;

	type T_Cellule is
		record
			Cle : T_Cle;
			Donnee : T_Donnee;
			Suivant : T_LC;
		end record;

end LC;

