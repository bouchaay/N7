-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Spécification du package Exceptions

package Exceptions is

    -- Exception sur la date de naissance
    Date_Naissance_Invalide : exception;

    -- Exception sur une file vide
    File_Vide : exception;

    -- Exception sur une file pleine
    File_Pleine : exception;

end Exceptions;