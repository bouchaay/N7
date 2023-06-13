------------------------------------------------------------------------------------------------*	
-- Nom du projet	:	Gestion des routeurs avec cache.				|
-- Promotion 		:	2022/2023							|
-- Auteurs 		: 	BOUCHAMA Ayoub ** ElGUERRAOUI Oussama ** MARZOUGUI Achraf	|
-- Encadrant		:	MENDIL Ismail							|
-- Code			:	Specification du module Exceptions 				|
------------------------------------------------------------------------------------------------*	

package Exceptions is
    
	Invalid_Command : exception;
    
	Format_Error : exception;
    
	Cle_Absente_Exception : exception;

	Liste_Vide_Exception : exception;

	Politique_Invalide : exception;
	
end Exceptions;


