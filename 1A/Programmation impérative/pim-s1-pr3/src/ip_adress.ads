------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Sp√©cification du module IP_ADRESS 				|
------------------------------------------------------------------------------------------------*	

with Ada.Text_IO;      use Ada.Text_IO;

package IP_ADRESS is

  Type T_Adresse_IP is mod 2**32;

  -- Afficher une adresse IP sous la forme "999.999.999.999".
  procedure Show_IP (IP : in T_Adresse_IP);

  -- Ajouter une adresse IP au fichier File.
  procedure Add_IP (File : in out File_Type; IP : in T_Adresse_IP);

  -- Recuperer une adresse IP du fichier File.
  function Get_IP (File : in out File_Type) return T_Adresse_IP;
   
  -- Transformer 4 bit d'une adresse ip d'un type String ‡ un type T_Adresse_IP 
  procedure To_T_Adresse_IP (OCTET_1 : in String; OCTET_2 : in String; OCTET_3 : in String; OCTET_4 : in String; IP : out T_Adresse_IP );

end IP_ADRESS;
