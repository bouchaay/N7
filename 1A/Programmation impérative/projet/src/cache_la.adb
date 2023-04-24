------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Implantation du module Cache_LA 				|
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO; Use Ada.Text_IO;

package body Cache_LA is

    procedure Initialiser_Cache_LA(Cache : in out T_Cache ; Size_Max : in Integer) is
    begin
        Initialiser(Cache.Arbre); -- Initialiser le cache.
        Cache.Size := 0; -- Mise a jour de la taille du cache a 0.
        Cache.Size_Max := Size_Max; -- Mise a jour de la taille max du cache a 0.
        Cache.Visite_Noeud := 0; -- Mise a jour du nombre de visite du cache a 0.
        Cache.Errors := 0; -- Mise a jour du nombre d'erreurs du cache a 0.
    end Initialiser_Cache_LA;

    procedure Afficher_Statistiques_LA(Cache : in T_Cache) is
        Error_Rate : Float;
    begin
        Put_Line("Taille du cache : " & Integer'Image(Cache.Visite_Noeud)); -- Afficher la taille du cache.
        Put_Line("Nombre de visites de la route : " & Integer'Image(Cache.Visite_Noeud)); -- Afficher le nombre de visites de route.
        Error_Rate := Float(Cache.Errors) / Float(Cache.Visite_Noeud); 
        Put_Line("Taux d'erreurs du cache : " & Float'Image(Error_Rate)); -- Calcul du taux de defauts du cache.
    end Afficher_Statistiques_LA;

    procedure Lire_Cache_LA(Cache : in out T_Cache ; Destination : in T_Adresse_IP ; IP_Interface : out Unbounded_String ; Found : out Boolean) is
    begin
        Traiter(Cache.Visite_Noeud, Destination, IP_Interface, Found, Cache.Arbre);
        Cache.Visite_Noeud := Cache.Visite_Noeud + 1;
        if not Found then
            Cache.Errors := Cache.Errors + 1; -- Incrementer le nombre d'erreurs du cache si la route n'est pas trouvé.
        else
            if Cache.Size = Cache.Size_Max then 
                Supprimer(Cache.Visite_Noeud, Cache.Arbre); -- Supprimer une route si le cache est plein
            else
                Cache.Size := Cache.Size + 1; -- Incrementer la taille du cache.
            end if;
            Enregistrer(Cache.Visite_Noeud, Destination, IP_Interface, Cache.Arbre); -- Enregistrer la nouvelle route.
        end if;
    end Lire_Cache_LA;

    procedure Afficher_Cache_LA(Cache : in T_Cache) is
    begin
        Afficher(Cache.Arbre); -- Afficher le cache.
   end Afficher_Cache_LA;
   
   function Taille_Cache_LA(Cache : in T_Cache) return Integer is
   begin
      return Cache.Size;
   end Taille_Cache_LA;
   
   function Visite_Noeud_LA(Cache : in T_Cache) return Integer is
   begin
      return Cache.Visite_Noeud;
   end Visite_Noeud_LA;
   
   function Errors_LA(Cache : in T_Cache) return Integer is
   begin
      return Cache.Errors;
   end Errors_LA;
   
   procedure Changer_Visite_Errors(Cache : in out T_Cache ; Visite : in Integer ; Errors : in Integer) is
   begin
      Cache.Visite_Noeud := Visite;
      Cache.Errors := Errors;
   end Changer_Visite_Errors;
      

end Cache_LA;
