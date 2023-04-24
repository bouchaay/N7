-- with Ada.Text_IO;            use Ada.Text_IO;
with SDA_Exceptions;         use SDA_Exceptions;
with Ada.Unchecked_Deallocation;

package body LCA is

	procedure Free is
		new Ada.Unchecked_Deallocation (Object => T_Cellule, Name => T_LCA);


	procedure Initialiser(Sda: out T_LCA) is
	begin
		Sda := Null ;
	end Initialiser;
	
	function Est_Vide (Sda : T_LCA) return Boolean is
	begin
		return Sda = Null ;
	end;
	function Taille (Sda : in T_LCA) return Integer is
	s : Integer := 0 ;
       	E : T_LCA ;
	begin
		E := Sda ;
		while E /= Null loop
			s := s + 1 ;
			E := E.all.suivant ;
		end loop;
		return s ;
	end Taille;


	procedure Enregistrer (Sda : in out T_LCA ; Cle : in T_Cle ; Donnee : in T_Donnee) is
        Nouvelle_Cellule : T_LCA;
	begin
	if Sda = Null then
	
		-- Initialiser une nouvelle cellule
	
		Nouvelle_Cellule := new T_Cellule;
		Nouvelle_Cellule.all.Cle := Cle ;
		Nouvelle_Cellule.all.Donnee := Donnee ;
		Nouvelle_Cellule.all.Suivant := Sda ;
        
		-- Changer Sda (elle pointe sur la nouvelle cellule)
	
		Sda := Nouvelle_Cellule;
	else
	       if Sda.all.Cle = Cle then
	                Sda.all.Donnee := Donnee;
	       else Enregistrer(Sda.all.Suivant, Cle, Donnee);
	       end if;
	end if;
	end Enregistrer;


	function Cle_Presente (Sda : in T_LCA ; Cle : in T_Cle) return Boolean is
        T : Boolean := False ;
        begin
	if Sda = Null then
		return False;
	else
		if Sda.all.Cle = Cle then
			return True;
		else
			return Cle_Presente(Sda.all.Suivant, Cle);
		end if;
	end if;
	end Cle_Presente;
          	  

	function La_Donnee (Sda : in T_LCA ; Cle : in T_Cle) return T_Donnee is
	begin
		if Sda = Null then
		    raise Cle_Absente_Exception;
		else
		    if Sda.all.Cle = Cle then
		        return Sda.all.Donnee;
		    else
		        return La_Donnee(Sda.all.Suivant, Cle);
		    end if;
		end if;
	end La_Donnee;

	procedure Supprimer (Sda : in out T_LCA ; Cle : in T_Cle) is
	
	A_Supprimer : T_LCA;
	begin
		if Sda = Null then
		    raise Cle_Absente_Exception;
		else
		    if Sda.all.Cle = Cle then

	            A_Supprimer := Sda;
	            Sda := Sda.all.Suivant;
	            Free (A_Supprimer);

		    else
		        Supprimer(Sda.all.SUivant, Cle);
		    end if;
		end if;
	end Supprimer;


	procedure Vider (Sda : in out T_LCA) is
	begin
		if Not est_Vide(Sda) then
			vider(Sda.all.Suivant);
			free(Sda);
		end if;
	end Vider;
	
	procedure Pour_Chaque (Sda : in T_LCA) is
	begin
		if Sda /= Null then
		    Traiter (Sda.all.Cle, Sda.all.Donnee);
		    Pour_Chaque (Sda.all.Suivant);
		else
		    Null;
		end if;
		exception
		    when others => Null;
	end Pour_Chaque;


end LCA;
