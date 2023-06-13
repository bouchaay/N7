------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Implantation du module IP_ADRESS 				|
------------------------------------------------------------------------------------------------*	

with Ada.Integer_Text_IO;    use Ada.Integer_Text_IO;
with Exceptions;           use Exceptions;

package body IP_ADRESS is
   
    UN_OCTET: constant T_Adresse_IP := 2 ** 8;

	procedure Show_IP (IP : T_Adresse_IP) is
	begin	
		-- Boucle de 0 à 2 pour itérer sur les 3 octets de l'adresse IP
      		for i in 0..2 loop
        		--Afficher l'octet i en utilisant l'opérateur modulo pour obtenir le reste de la division entière
        		--diviser par la puissance de 256 pour obtenir l'octet i,
        		Put(Natural (IP/256**(3-i) mod 256), 1);
        		Put(".");
      		end loop;
      		--Afficher le dernier octet
      		put(Natural (IP mod 256), 1);
      
     		--Ajouter un espace pour une meilleure lisibilité
      		Put(" ");
    	end Show_IP;
    


	procedure Add_IP (File : in out File_Type ; IP :   T_Adresse_IP) is
    	begin
      		-- Boucle de 0 à 2 pour itérer sur les 3 octets de l'adresse IP
      		for i in 0..2 loop
        		--Ajouter l'octet i en utilisant l'opérateur modulo pour obtenir le reste de la division entière
        		--diviser par la puissance de 256 pour obtenir l'octet i, dans le fichier passé en paramètre
        		Put(File, Natural (IP / 256**(3-i) mod 256), 1);
        		Put(File, ".");
      		end loop;
       		--Ajouter le dernier octet dans le fichier
      		put(File, Natural (IP mod 256), 1);
      		--Ajouter un espace pour une meilleure lisibilité
      		Put(File, " ");
    	end Add_IP;
    

	function Get_IP(File : in out File_Type) return T_Adresse_IP is
      		n : Integer;
      		Lien : Character;
      		IP : T_Adresse_IP := 0;
    	begin
    		-- Boucle de 0 à 3 pour itérer sur les 4 octets de l'adresse IP
      		for i in 0..3 loop
        		--Lire l'octet i dans le fichier passé en paramètre
        		Get(File, n);
        		-- Ajouter l'octet i en utilisant l'opérateur de décalage de bits pour ajouter l'octet à l'adresse IP
        		IP :=  T_Adresse_IP(n) + IP*256;
        		if i < 3 then
          			Get(File, Lien);
          			--Vérifier que les séparateurs sont des points
          			if Lien /= '.' then
            				Put_Line("Syntax Error In IP Adress. Must Be '.' !");
          			end if;
        		end if;
      		end loop;
      		--Retourner l'adresse IP
      		return IP;
   end Get_IP;
   
   
    procedure To_T_Adresse_IP (OCTET_1 : in String; OCTET_2 : in String; OCTET_3 : in String; OCTET_4 : in String; IP : out T_Adresse_IP ) is
    begin
        IP := T_Adresse_IP'Value(OCTET_1);
        IP := IP * UN_OCTET + T_Adresse_IP'Value(OCTET_2);
        IP := IP * UN_OCTET + T_Adresse_IP'Value(OCTET_3);
        IP := IP * UN_OCTET + T_Adresse_IP'Value(OCTET_4);
    end To_T_Adresse_IP;


end IP_ADRESS;
