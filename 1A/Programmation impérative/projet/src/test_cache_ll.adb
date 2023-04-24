------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Test du module Cache_LL						|		
------------------------------------------------------------------------------------------------*	

with Cache_LL;                 use Cache_LL;
with Ada.Text_IO;              use Ada.Text_IO;
with Ada.Strings.Unbounded;    use Ada.Strings.Unbounded;
with Ada.Text_IO.Unbounded_IO; use Ada.Text_IO.Unbounded_IO;

procedure Test_Cache_LL is
    Cache : T_Cache;
    IP_Interface : Unbounded_String;
    Found : Boolean;
begin
    Initialiser_Cache_LL(Cache, 3);
    pragma assert (Taille_Cache_LL(Cache) = 0);
    Afficher_Cache_LL(Cache, To_Unbounded_String("LRU"));
    New_Line;
    Put_Line("Le cache a été initialisé avec succès !");
    New_Line;
    

    Put_Line("***** DEBUT DU TEST LRU *****");
    Put_Line("Taille_Max = 3");
    New_Line;


    Ajouter_Cache_LL(Cache, 0, To_Unbounded_String("eth0"), To_Unbounded_String("LRU"));  -- 0.0.0.0 : eth0
    Afficher_Cache_LL(Cache, To_Unbounded_String("LRU"));
    Put_Line("la route '0.0.0.0 : eth0' a été ajouté avec succès dans le cache !");

    Ajouter_Cache_LL(Cache, 1, To_Unbounded_String("eth1"), To_Unbounded_String("LRU"));  -- 0.0.0.1 : eth1
    Afficher_Cache_LL(Cache, To_Unbounded_String("LRU")); New_Line;
    Put_Line("la route '0.0.0.1 : eth1' a été ajouté avec succès dans le cache !"); New_Line;

    Ajouter_Cache_LL(Cache, 2, To_Unbounded_String("eth2"), To_Unbounded_String("LRU"));  -- 0.0.0.2 : eth2
    Afficher_Cache_LL(Cache, To_Unbounded_String("LRU"));
    Put_Line("la route '0.0.0.2 : eth2' a été ajouté avec succès dans le cache !");


    Lire_Cache_LL(Cache, 0, To_Unbounded_String("LRU"), IP_Interface, Found);
    -- 0.0.0.2 : eth2
    -- 0.0.0.1 : eth1
    -- 0.0.0.0 : eth0
    pragma assert(Found);
    Put_Line("La route de destination 0 a été lue avec succès !");

    Lire_Cache_LL(Cache, 1, To_Unbounded_String("LRU"), IP_Interface, Found);
    -- 0.0.0.2 : eth2
    -- 0.0.0.1 : eth1
    -- 0.0.0.0 : eth0
    pragma assert(Found);
    Put_Line("La route de destination 1 a été lue avec succès !");

   
    Lire_Cache_LL(Cache, 255, To_Unbounded_String("LRU"), IP_Interface, Found);
    pragma assert(not Found);
    Put_Line("la route 0.0.0.255 n'existe pas dans le cache !");
    Afficher_Cache_LL(Cache, To_Unbounded_String("LRU"));
    New_Line;
    Put_Line("Le cache contient les routes '0.0.0.0 : eth0', '0.0.0.1 : eth1' et '0.0.0.2 : eth2'.");
    New_Line;

    Ajouter_Cache_LL(Cache, 3, To_Unbounded_String("eth3"), To_Unbounded_String("LRU"));  -- Ajouter une nouvelle route alors que le cache est plein.
    -- 0.0.0.0 : eth0
    -- 0.0.0.1 : eth1
    -- 0.0.0.3 : eth3
    Put_Line("Cache plein !");
    Put_Line("La route '0.0.0.2 : eth2' a été suprimée du cache.");
    Afficher_Cache_LL(Cache, To_Unbounded_String("LRU"));
    New_Line;

    Incrementer_ERRORS_LL(Cache);
    Afficher_Statistiques_LL(Cache);
    New_Line;
    Put_Line("L'affichage des statistiques été effectué avec succès !");
    New_Line;

    Put_Line("Le Comportement LRU est validé.");
    New_Line;

    
    Put_Line("***** DEBUT DU TEST FIFO *****");
    Put_Line("Taille_Max = 4");
    New_Line;

    
    Initialiser_Cache_LL(Cache, 4);
    Ajouter_Cache_LL(Cache, 0, To_Unbounded_String("eth0"), To_Unbounded_String("FIFO")); -- 0.0.0.0 : eth0
    Ajouter_Cache_LL(Cache, 1, To_Unbounded_String("eth1"), To_Unbounded_String("FIFO")); -- 0.0.0.1 : eth1
    Ajouter_Cache_LL(Cache, 2, To_Unbounded_String("eth2"), To_Unbounded_String("FIFO")); -- 0.0.0.2 : eth2
    Ajouter_Cache_LL(Cache, 3, To_Unbounded_String("eth3"), To_Unbounded_String("FIFO")); -- 0.0.0.3 : eth3
    Put_Line("Cache :");
    Afficher_Cache_LL(Cache, To_Unbounded_String("FIFO"));
    New_Line;

    Lire_Cache_LL(Cache, 0, To_Unbounded_String("FIFO"), IP_Interface, Found);
    -- Aucun changement
    pragma assert(Found);
    Put_Line("La route de destination 0 a été lue avec succès !");
    Afficher_Cache_LL(Cache, To_Unbounded_String("FIFO"));
    New_Line;

    Ajouter_Cache_LL(Cache, 4, To_Unbounded_String("eth4"), To_Unbounded_String("FIFO"));
    -- 0.0.0.1 : eth1
    -- 0.0.0.2 : eth2
    -- 0.0.0.3 : eth3
    -- 0.0.0.4 : eth4
    Put_Line("Cache plein !");
    Put_Line("La route '0.0.0.0 : eth0' a été suprimée du cache.");
    Afficher_Cache_LL(Cache, To_Unbounded_String("FIFO"));
    New_Line;

    Put_Line("Le comportement FIFO est validé.");
    New_Line;

    
    Put_Line("***** DEBUT DU TEST LFU *****");
    Put_Line("Taille_Max = 4");
    New_Line;


    Initialiser_Cache_LL(Cache, 4);
    Ajouter_Cache_LL(Cache, 0, To_Unbounded_String("eth0"), To_Unbounded_String("LFU")); -- 0.0.0.0 : eth0 - 0
    Ajouter_Cache_LL(Cache, 1, To_Unbounded_String("eth1"), To_Unbounded_String("LFU")); -- 0.0.0.1 : eth1 - 0
    Ajouter_Cache_LL(Cache, 2, To_Unbounded_String("eth2"), To_Unbounded_String("LFU")); -- 0.0.0.2 : eth2 - 0
    Ajouter_Cache_LL(Cache, 3, To_Unbounded_String("eth3"), To_Unbounded_String("LFU")); -- 0.0.0.3 : eth3 - 0
 
    Put_Line("Cache :");
    Afficher_Cache_LL(Cache, To_Unbounded_String("LFU")); New_Line;

    Lire_Cache_LL(Cache, 0, To_Unbounded_String("LFU"), IP_Interface, Found);
    Lire_Cache_LL(Cache, 0, To_Unbounded_String("LFU"), IP_Interface, Found);
    Lire_Cache_LL(Cache, 0, To_Unbounded_String("LFU"), IP_Interface, Found);
    Lire_Cache_LL(Cache, 3, To_Unbounded_String("LFU"), IP_Interface, Found); 
    Lire_Cache_LL(Cache, 3, To_Unbounded_String("LFU"), IP_Interface, Found);
    Lire_Cache_LL(Cache, 2, To_Unbounded_String("LFU"), IP_Interface, Found);
    -- 0.0.0.0 : eth0 - 3
    -- 0.0.0.1 : eth1 - 0
    -- 0.0.0.2 : eth2 - 1
    -- 0.0.0.3 : eth3 - 2
    Afficher_Cache_LL(Cache, To_Unbounded_String("LFU"));
    New_Line;

    Ajouter_Cache_LL(Cache, 4, To_Unbounded_String("eth4"), To_Unbounded_String("LFU"));
    Put_Line("Cache plein !");
    Put_Line("La route '0.0.0.1 : eth1' a été suprimée du cache.");
    Afficher_Cache_LL(Cache, To_Unbounded_String("LFU"));
    New_Line;

    Put_Line("Le comportement LFU est validé.");
    New_Line;

    Put_Line("***** FIN DU TEST *****");
end Test_Cache_LL;
