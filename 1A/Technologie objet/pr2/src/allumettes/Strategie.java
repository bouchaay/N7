package allumettes;

/**
 * Interface d'une stratégie de jeu.
 * @author Ayoub Bouchama
 * @see StratExpert
 * @see StratNaif
 * @see StratHumain
 * @see StratRapide
 * @see StratTricheur
 */
public interface Strategie {

    /**
     * Obtenir le nom de la stratégie.
     * @return nom de la stratégie
     */
    String getNom();

    /**
     * Obtenir le nombre d'allumettes à retirer.
     * @param jeu le jeu en cours
     * @param joueurCourant le joueur courant
     * @return nombre d'allumettes à retirer
     */
    int getPrise(Jeu jeu, Joueur joueurCourant);
}
