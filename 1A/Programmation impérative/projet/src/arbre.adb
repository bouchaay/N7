------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Implantation du module Arbre					|		
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO; use Ada.Text_IO;
with Ada.Unchecked_Deallocation;
with Exceptions; use Exceptions;


package body Arbre is

    	procedure Free is
        	new Ada.Unchecked_Deallocation(Object => T_Noeud, Name => T_LA);

    	-- Initialiser l'arbre.
    	procedure Initialiser(Arbre : in out T_LA) is
    	begin
        	Arbre := null;
    	end Initialiser;

    	-- Calculer la taille de l'arbre
    	function Taille(Arbre : in T_LA) return Integer is
    	begin
        	if Arbre = null then
            		return 0;
        	else
            		return 1 + Taille(Arbre.All.Fils_Gauche) + Taille(Arbre.All.Fils_Droit);
        	end if;
    	end Taille;

    	-- Supprimer l'element avec le plus petit nombre de visites.
    	procedure Supprimer(Noeud_Actuel : in Integer ; Arbre : in out T_LA) is
    		Min : integer := Noeud_Actuel;
    		Cle : T_Adresse_IP := 0;

		-- Calculer la cle de l'element avec le plus petit nombre de visite.
        	procedure Min_Rec(Arbre : in T_LA ; Min : in out Integer ; Cle : out T_Adresse_IP) is
        	begin
			-- Vérifie si l'arbre est nul
            		if Arbre = Null then
                		-- Ne fait rien si l'arbre est nul
				Null;
            		else
				-- Vérifie si le noeud courant n'a pas de fils gauche ni de fils
                		if Arbre.All.Fils_Gauche = Null and Arbre.All.Fils_Droit = Null then
                    			-- Compare Visite_Noeud avec la valeur minimale actuelle
					if Min > Arbre.All.Visite_Noeud then
                        			-- Met à jour la valeur minimale et la clé correspondante si c'est inférieur
						Min := Arbre.All.Visite_Noeud;
                        			Cle := Arbre.All.Cle;
                    			else
                        			-- Ne fait rien si la valeur n'est pas inférieure
						Null;
                    			end if;
                		else
					-- Appelle récursivement la procédure pour continuer à parcourir l'arbre en visitant les fils gauche et droit.
                    			Min_Rec(Arbre.All.Fils_Gauche , Min , Cle);
                    			Min_Rec(Arbre.All.Fils_Droit , Min , Cle);
                		end if;
            		end if;
        	end Min_Rec;

		-- Supprimer l'element associe a une cle.
		procedure Supprimer_Cle(Arbre : in out T_LA ; Cle : in T_Adresse_IP) is
    			-- Déclaration des variables locales
    			Bit : T_Adresse_IP;
    			Arbre_a_Liberer : T_LA;
		begin
    			-- Vérifie si le noeud courant n'a pas de fils gauche et de fils droit
    			if Arbre.All.Fils_Gauche = Null and Arbre.All.Fils_Droit = Null then
        			-- Détruit le noeud courant
        			Free(Arbre);
    			else
        			-- Calcule la valeur de "Bit" pour déterminer si la clé recherchée se trouve à droite ou à gauche
        			Bit := Cle / 2**31;
        			if Bit = 1 then
            				-- Appelle récursivement la procédure pour continuer à parcourir l'arbre à droite
            				Supprimer_Cle(Arbre.All.Fils_Droit , Cle * 2);
        			else
            				-- Appelle récursivement la procédure pour continuer à parcourir l'arbre à gauche
            				Supprimer_Cle(Arbre.All.Fils_Gauche , Cle * 2);
        			end if;
        			-- Vérifie si le noeud courant n'a pas de fils gauche et de fils droit
        			if Arbre.All.Fils_Gauche = Null and Arbre.All.Fils_Droit = Null then
            				-- Détruit le noeud courant
            				Free(Arbre);
        			else
            				-- Vérifie si le fils gauche est nul
            				if Arbre.All.Fils_Gauche /= Null and Arbre.All.Fils_Droit = Null then
                				-- Détruit le noeud courant
                				Arbre_a_Liberer := Arbre;
                				Arbre := Arbre.All.Fils_Gauche;
                				Arbre.All.Cle := Arbre.All.Cle / 2;
                				Free(Arbre_a_Liberer);
            				else
                				-- Vérifie si le fils droit est nul
                				if Arbre.All.Fils_Gauche = Null and Arbre.All.Fils_Droit /= Null then
                    					-- Détruit le noeud courant
                    					Arbre_a_Liberer := Arbre;
                    					Arbre := Arbre.All.Fils_Droit;
                    					Arbre.All.Cle := Arbre.All.Cle / 2;
                    					Free(Arbre_a_Liberer);
                				else
                   					-- Sinon ne fait rien
                   					Null;
                				end if;
            				end if;
        			end if;
    			end if;
		end Supprimer_Cle;
        
    	begin
        	Min_Rec(Arbre , Min , Cle); -- Calculer la cle la moisn visite
        	Supprimer_Cle(Arbre , Cle); -- Supprimer cette cle
    	end Supprimer;

	procedure Traiter(Noeud_Actuel : in Integer ; Cle : in T_Adresse_IP ; Valeur : out Unbounded_String ; Found : out Boolean ; Arbre : in out T_LA) is

    		--Procedure qui va vérifier si les informations recherchées existent dans l'arbre
    		procedure Traiter_Rec(N_Actuel : in Integer ; Cle : in T_Adresse_IP ; Valeur : out Unbounded_String ; Found : out Boolean ; Arbre : in T_LA) is
        		Bit : T_Adresse_IP;

    			--Procedure qui va comparer les données
        		procedure Compare_DATA(N_Actuel : in Integer ; Cle : in T_Adresse_IP ; Valeur : out Unbounded_String ; Found : out Boolean ; Arbre : in T_LA) is
            		begin
                		if Arbre.All.Cle = Cle then
                    			-- Si la clé est trouvée on met à jour la valeur et on indique que c'est trouvé
                    			Valeur := Arbre.All.Valeur;
                    			Found := True;
                    			Arbre.All.Visite_Noeud := N_Actuel;
                		else
                    			-- Sinon, on indique qu'il n'a pas été trouvé
                    			Valeur := To_Unbounded_String("NOT FOUND");
                    			Found := False;
                		end if;
            		end Compare_DATA;
    		begin
        		--Vérifie si l'arbre est nul
        		if Arbre = Null then
            			Valeur := To_Unbounded_String("NOT FOUND");
            			Found := False;
        		else if Arbre.All.Fils_Droit = Null and Arbre.All.Fils_Gauche = Null then
            			Compare_DATA(N_Actuel , Cle , Valeur , Found , Arbre);
        		else
            			Bit := Cle / 2**31;
            			if Bit = 1 then
                			Traiter_Rec(N_Actuel , Cle * 2 , Valeur , Found , Arbre.All.Fils_Droit);
            			else
                			Traiter_Rec(N_Actuel , Cle * 2 , Valeur , Found , Arbre.All.Fils_Gauche);
            			end if;
            			--Fais rien 
            			Null;
        		end if;
        		end if;
    		end Traiter_Rec;
	begin
    		--Vérifie si l'arbre est nul
    		if Arbre = Null then
        		Valeur := To_Unbounded_String("EMPTY");
        		Found := False;
    		else
        		Traiter_Rec(Noeud_Actuel , Cle , Valeur , Found , Arbre);
    		end if;
	end Traiter;
    
	procedure Enregistrer(Noeud_Actuel : in Integer ; Cle : in T_Adresse_IP ; Valeur : in Unbounded_String ; Arbre : in out T_LA) is
    		--Variable qui stockera le bit de poids faible de la clé
    		Bit : T_Adresse_IP;
    		--Variable pour copier le noeud actuel
    		Copy : T_LA;
	begin
    		--Si l'arbre est nul
    		if Arbre = Null then
        		--On crée un nouveau noeud
        		Arbre := new T_Noeud'(Cle, Valeur, Noeud_Actuel, null, null);
    		else if Arbre.All.Fils_Droit = Null and Arbre.All.Fils_Gauche = Null then
        		--on prend le bit de poids faible de la clé
        		Bit := Arbre.All.Cle / 2**31;
        		-- on copie le noeud actuel
        		Copy := Arbre;
        		-- On crée un nouveau noeud qui devient la racine
        		Arbre := new T_Noeud'(0, To_Unbounded_String("INVALIDE"), 0, null, null);
        		if Bit = 1 then
            			--Si le bit de poids faible est 1, le noeud existant devient fils droit
            			Arbre.All.Fils_Droit := Copy;
        		else
            			--Sinon, il devient fils gauche
            			Arbre.All.Fils_Gauche := Copy;
        		end if;
    		else
        		--On prend le bit de poids faible de la clé
        		Bit := Cle / 2**31;
        		if Bit = 1 then
            			--Si le bit de poids faible est 1, on enregistre le nouveau noeud comme fils droit
            			Enregistrer(Noeud_Actuel , Cle * 2 , Valeur , Arbre.All.Fils_Droit);
        		else
            			--Sinon, on enregistre le nouveau noeud
                	Enregistrer(Noeud_Actuel , Cle * 2 , Valeur , Arbre.All.Fils_Gauche);
            		end if;
        	end if;
        	end if;
    	end Enregistrer;

	procedure Afficher(Arbre : in T_LA) is
    		--Procédure récursive pour parcourir l'arbre
    		procedure Afficher_Rec(Arbre : in T_LA; Way : in T_Adresse_IP; Deepth : in Integer) is
        		--Variable pour stocker le bit de poids faible de la clé
        		Bit : T_Adresse_IP;
    		begin
        		--Si l'arbre est nul
        		if Arbre = Null then
            			--On ne fait rien
            			Null;
        		else if Arbre.All.Fils_Droit = Null and Arbre.All.Fils_Gauche = Null then
            			--Si on est à un noeud feuille, on affiche la clé et la valeur
            			Show_IP(Way * 2**Deepth + Arbre.All.Cle / 2**Deepth);
            			Put_Line(" " & To_String(Arbre.All.Valeur));
        		else
            			--On prend le bit de poids faible de la clé
            			Bit := Arbre.All.Cle / 2**31;
            			if Bit = 1 then
                			--Si le bit de poids faible est 1, on va au fils droit
                			Afficher_Rec(Arbre.All.Fils_Droit , Way + 2**(31-Deepth) , Deepth + 1);
            			else
                			--Sinon, on va au fils gauche
                			Afficher_Rec(Arbre.All.Fils_Gauche , Way , Deepth + 1);
            			end if;
                end if;
             end if ;   
    		end Afficher_Rec;
        --On appelle la procédure récursive en lui passant l'arbre, la clé initiale et la profondeur initiale
    begin    
    		Afficher_Rec(Arbre , 0 , 0);
	end Afficher;
    
end Arbre;
