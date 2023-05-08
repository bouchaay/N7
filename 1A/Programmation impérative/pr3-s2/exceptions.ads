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

    Arbre_Sature : exception;

end Exceptions;