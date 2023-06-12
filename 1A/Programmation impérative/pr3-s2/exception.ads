-- Auteur : Ayoub Bouchama
-- Groupe : F
-- Spécification du package Exceptions

package Exceptions is

    -- Exception sur une clé existante
    Cle_Existante : exception;

    -- Exception sur une clé inexistante
    Cle_Inexistante : exception;

    -- Exception sur chemin inexistant
    Chemin_Inexistant : exception;

    -- Exception sur une racine inexistante
    Racine_Inexistante : exception;

    -- Exception sur un arbre saturé
    Arbre_Sature : exception;

    -- Exception sur une suppression de racine
    Suppression_Racine : exception;

    Usage_Error : exception;

end Exceptions;