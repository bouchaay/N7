-- Auteur : Xavier Crégut <prenom.nom@enseeiht.fr>
-- Spécification du module Assure.  Un assuré est caractérisé par son numéro
-- (entier) et son mois de naissance (entier).  On peut l'afficher et le
-- saisir.

with Ada.Text_IO;        use Ada.Text_IO;

package Assures is

	type T_Assure is private;


	-- Initialiser un assuré grâce à son numéro et son mois de naissance.
	procedure Initialiser (Assure : out T_Assure ;
		Numero : in Integer ;
		Mois_Naissance : in Integer)
	with
		Pre => 1 <= Mois_Naissance and Mois_Naissance <= 12 and Numero > 0,
		Post => Le_Mois_Naissance (Assure) = Mois_Naissance
				and Le_Numero (Assure) = Numero;


	-- Afficher l'assuré (son numéro et son mois de naissance)
	procedure Afficher (Assure : in T_Assure);


	-- Le numéro de l'assuré.
	function Le_Numero (Assure : T_Assure) return Integer;


	-- Le mois de naissance de l'assuré
	function Le_Mois_Naissance (Assure : in T_Assure) return Integer with
		Post => 1 <= Le_Mois_Naissance'Result and Le_Mois_Naissance'Result <= 12;


	-- Saisir (au clavier) un assuré (le numéro puis son mois de naissance).
	procedure Saisir (Assure : out T_Assure) with
		Post => 1 <= Le_Mois_Naissance (Assure) and Le_Mois_Naissance (Assure) <= 12;

private

	type T_Assure is
		record
			Numero : Integer;	-- numero de l'assuré
			Mois_Naissance : Integer;	-- le mois de naissance de l'assuré
			-- Invariant : 1 <= Mois_Naissance and Mois_Naissance <= 12;
		end record;

end Assures;
