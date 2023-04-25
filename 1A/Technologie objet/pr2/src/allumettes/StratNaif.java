package allumettes;

import java.util.Random;

/**
 * Stratégie naïve.
 * La stratégie consiste à prendre un nombre aléatoire d'allumettes entre 1 et
 * PRISE_MAX.
 * Implémente l'interface Strategie.
 * @see Strategie
 * @author Ayoub Bouchama
 */
public class StratNaif implements Strategie {

    /**
     * Obtenir le nom de la stratégie.
     * @return nom de la stratégie
     */
    public String getNom() {
        return "naif";
    }

    /**
     * Obtenir le nombre d'allumettes à retirer.
     * @param jeu le jeu en cours
     * @param joueurCourant le joueur courant
     * @return nombre d'allumettes à retirer
     */
    public int getPrise(Jeu jeu, Joueur joueurCourant) {
        assert jeu != null : " Le jeu ne doit pas être null.";
        int nbAllumettes = jeu.getNombreAllumettes();
        Random rand = new Random();
        int nbPrises = rand.nextInt(Jeu.PRISE_MAX) + 1;
        return Math.min(nbAllumettes, nbPrises);
    }
}
