------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Implantation du module Cache_LL 				|
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO;              use Ada.Text_IO;
with Exceptions;               use Exceptions;
with Ada.Text_IO.Unbounded_IO; use Ada.Text_IO.Unbounded_IO;

package body Cache_LL is

    procedure Initialiser_Cache_LL (Cache : in out T_Cache; Size_Max : Integer) is
    begin
        Initialiser(Cache.L); -- Initialiser le cache.
        Cache.Size_Max := Size_Max; -- Mise a jour de la taille max du cache a 0.
        Cache.N_CALLS := 0; -- Mise a jour du nombre d'appel d'une route a 0.
        Cache.N_ERRORS := 0; -- Mise a jour du nombre d'erreur a 0.
    end Initialiser_Cache_LL;

    procedure Afficher_Statistiques_LL (Cache : in T_Cache) is
    begin
        Put_Line("Nombre d'appels du cache : " & Integer'Image(Cache.N_CALLS));
        Put_Line("Nombre d'erreurs du cache : " & Integer'Image(Cache.N_ERRORS));
        if Cache.N_CALLS = 0 then
            Put_Line("ERROR WHILE CALCULATING THE DEFAUT !");
        else
            Put_Line("ERROR RATE : " & Float'Image(Float(Cache.N_ERRORS) / Float(Cache.N_CALLS)));
        end if;
    end Afficher_Statistiques_LL;


    procedure Afficher_Cache_LL (Cache : in T_Cache; Politique : in Unbounded_String) is

        procedure Afficher_Liste (Cle: T_Adresse_IP; Donnee : T_Cellule) is
        begin
            Show_IP(Cle);
            Put("- ");
            Put(Donnee.IP_Interface);
            if Politique = To_Unbounded_String("LFU") then
                Put(" - " & Integer'Image(Donnee.N_CALLS_IP));
            end if;
            New_Line;
        end Afficher_Liste;

        procedure Afficher_Cache_Liste is new Pour_Chaque(Traiter => Afficher_Liste);

    begin
        if Est_Vide(Cache.L) then
            Put_Line("EMPTY CACHE");
        else
            Put("Destination - Interface");
            if Politique = To_Unbounded_String("LFU") then
                Put(" - Nb Appels");
            end if;
            New_Line;
            Afficher_Cache_Liste(Cache.L);
        end if;
    end Afficher_Cache_LL;


    procedure Lire_Cache_LL (Cache : in out T_Cache; Destination : in T_Adresse_IP; Politique : Unbounded_String; IP_Interface : out Unbounded_String; Found : out Boolean) is
        Donnee : T_Cellule;
    begin
        if Cle_Presente(Cache.L, Destination) then
            Donnee :=  La_Donnee(Cache.L, Destination);
            IP_Interface := Donnee.IP_Interface;
            Cache.N_CALLS := Cache.N_CALLS + 1;
            Found := True;
            
            if Politique = To_Unbounded_String("LRU") then
                Supprimer(Cache.L, Destination);
                Enregistrer_Fin(Cache.L, Destination, Donnee);
            elsif Politique = To_Unbounded_String("FIFO") then 
                null;
            elsif Politique = To_Unbounded_String("LFU") then
                Donnee.N_CALLS_IP := Donnee.N_CALLS_IP + 1;
                Enregistrer(Cache.L, Destination, Donnee);
            else
                raise Politique_Invalide;
            end if;
        else
            Found := False;
        end if;
    end Lire_Cache_LL;


    procedure Ajouter_Cache_LL (Cache : in out T_Cache; Destination : in T_Adresse_IP; IP_Interface : in Unbounded_String; Politique : in Unbounded_String) is
        Cellule : constant T_Cellule := T_Cellule'(IP_Interface, 0);
        Min_Cellule : T_Cellule;
        Min : Integer;
        Dest : T_Adresse_IP;
        Min_Dest : T_Adresse_IP;
    begin
        Enregistrer_Fin(Cache.L, Destination, Cellule);
        if Taille(Cache.L) > Cache.Size_Max then
            if Politique = To_Unbounded_String("LRU") or Politique = To_Unbounded_String("FIFO") then
                Suppression_Haute(Cache.L);
            elsif Politique = To_Unbounded_String("LFU") then
                Element_En_Indice(Cache.L, 0, Min_Dest, Min_Cellule);
                Min := Min_Cellule.N_CALLS_IP;
                for i in 1..(Taille(Cache.L)-1) loop
                    Element_En_Indice(Cache.L, i, Dest, Min_Cellule);
                    if Min_Cellule.N_CALLS_IP < Min then
                        Min := Min_Cellule.N_CALLS_IP;
                        Min_Dest := Dest;
                    end if;
                end loop;
                Supprimer(Cache.L, Min_Dest);
            else
                raise Politique_Invalide;
            end if;
        else
            null;
        end if;
    end Ajouter_Cache_LL;


    procedure Incrementer_ERRORS_LL (Cache : in out T_Cache) is
    begin
        Cache.N_ERRORS := Cache.N_ERRORS + 1;
    end Incrementer_ERRORS_LL;


   procedure Incrementer_CALLS_LL (Cache : in out T_Cache) is
   begin
      Cache.N_CALLS := Cache.N_CALLS + 1;
   end;
   
    function Taille_Cache_LL (Cache : in T_Cache) return Integer is
    begin
        return Taille(Cache.L);
    end Taille_Cache_LL;


    function Est_Vide_Cache_LL (Cache : in T_Cache) return Boolean is
    begin
        return Est_Vide(Cache.L);
    end Est_Vide_Cache_LL;

    function N_CALLS_Destination_LL (Cache : in T_Cache; Destination : in T_Adresse_IP) return Integer is
        Donnee : T_Cellule;
    begin
        if Cle_Presente(Cache.L, Destination) then
            Donnee := La_Donnee(Cache.L, Destination);
            return Donnee.N_CALLS_IP;
        else
            return 0;
        end if;
    end N_CALLS_Destination_LL;

end Cache_LL;
