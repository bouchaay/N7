------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Implantation du module LC 					|
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO;            use Ada.Text_IO;
with Exceptions;         use Exceptions;
with Ada.Unchecked_Deallocation;

package body LC is

	procedure Free is
		new Ada.Unchecked_Deallocation (Object => T_Cellule, Name => T_LC);

	-- Initialiser TRT.
	procedure Initialiser(TRT: out T_LC) is
	begin
		TRT := null;
	end Initialiser;

	-- Vérifier si Sda est vide.
	function Est_Vide (TRT : T_LC) return Boolean is
	begin
		return TRT = null;
	end Est_Vide;

	-- Calculer la taille de TRT.
	function Taille (TRT : in T_LC) return Integer is
	begin
		if TRT = null then
			return 0;
		else
			return 1+Taille(TRT.All.Suivant);
		end if;
	end Taille;

	-- Ajouter un élément à TRT.
	procedure Enregistrer (TRT : in out T_LC ; Cle : in T_Cle ; Donnee : in T_Donnee) is
		A : T_LC := TRT;
	begin
			if A = null then
				 -- Ajouter l'élément au début de TRT s'il est vide.
				TRT := new T_Cellule'(Cle, Donnee, TRT);
			elsif A.All.Cle = Cle then
				-- Mettre à jour les données si la clé est déjà présente dans TRT.
				A.All.Donnee := Donnee;
			else
				-- Continuer à chercher la clé dans TRT.
				A := A.All.Suivant;
				Enregistrer(A, Cle, Donnee);
			end if;
	end Enregistrer;

	-- Vérifier si la clé est présente dans TRT.
	function Cle_Presente (TRT : in T_LC ; Cle : in T_Cle) return Boolean is
	begin
		if TRT = null then
			return False;
		elsif TRT.All.Cle = Cle then
			return True;
		else
			return Cle_Presente(TRT.All.Suivant, Cle);
		end if;
	end Cle_Presente;

	-- Obtenir les données associées à la clé dans TRT.
	function La_Donnee (TRT : in T_LC ; Cle : in T_Cle) return T_Donnee is
	begin
		if TRT = null then
			raise Cle_Absente_Exception;
		elsif TRT.All.Cle = Cle then
			return TRT.All.Donnee;
		else
			return La_Donnee(TRT.All.Suivant, Cle);
		end if;
	end La_Donnee;

	-- Supprimer un élément de TRT.
	procedure Supprimer (TRT : in out T_LC ; Cle : in T_Cle) is
		A : T_LC := TRT;
	begin
		if TRT = null then
			 -- Lever une exception si la clé n'est pas présente dans Sda
			raise Cle_Absente_Exception;
		elsif TRT.All.Cle = Cle then
			TRT := TRT.All.Suivant;
			Free(A);
		else
			Supprimer(TRT.All.Suivant, Cle);
		end if;
	end Supprimer;

	-- Vider TRT.
	procedure Vider (TRT : in out T_LC) is
	begin
		if TRT = null then
			Free(TRT);
		else
			Vider(TRT.All.Suivant);
		end if;
		Free(TRT);
	end Vider;

	-- Ajouter un élément à la fin de TRT.
	procedure Enregistrer_Fin (TRT : in out T_LC ; Cle : in T_Cle ; Donnee : in T_Donnee) is
	begin
		if TRT = null then
			TRT := new T_Cellule'(Cle => Cle, Donnee => Donnee, Suivant => null);
		else
			Enregistrer_Fin(TRT.All.Suivant, Cle, Donnee);
		end if;
	end Enregistrer_Fin;
	
	-- Supprimer l'élément le plus haut dans TRT.
	procedure Suppression_Haute (TRT : in out T_LC) is
		A : T_LC := TRT;
	begin
		if TRT = null then
			raise Liste_Vide_Exception;
		else
			TRT := TRT.All.Suivant;
			Free(A);
		end if;
	end Suppression_Haute;

	-- Obtenir Cle et Donnee de l'element d'indice indice.
	procedure Element_En_Indice (TRT : in T_LC; Indice : in Integer; Cle : out T_Cle; Donnee : out T_Donnee) is
		A : T_LC := TRT;
		Counter : Integer := 0;
	begin
		while Counter < Indice loop
			A := A.All.Suivant;
			Counter := Counter + 1;
		end loop;

		Cle := A.All.Cle;
		Donnee := A.All.Donnee;
	end Element_En_Indice;

	-- appliquer le traitement traiter pour chaque cellule de TRT.
	procedure Pour_Chaque (TRT : in T_LC) is
		A : T_LC := TRT;
	begin
		while A /= null loop
			begin
				Traiter(A.All.Cle, A.All.Donnee);
			exception
				when others => Put_Line("Erreur de traitement.");
			end;
			A := A.All.Suivant;
		end loop;
	end Pour_Chaque;

end LC;

