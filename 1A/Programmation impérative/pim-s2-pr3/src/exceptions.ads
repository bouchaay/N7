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

    -- Noeud non trouvé
    Noeud_Inexistant : exception;

    -- Noeud déjà existant
    Noeud_Existant : exception;

    -- Erreur de l'usage
    Usage_Error : exception;

    -- Exception sur la taille des files
    Tailles_Differentes : exception;

    -- Exception sur la clé
    Cle_Inexistante : exception;

end Exceptions;