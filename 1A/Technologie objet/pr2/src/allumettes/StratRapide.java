package allumettes;

/**
 * Stratégie rapide.
 * La stratégie consiste à prendre le maximum d'allumettes possible.
 * Implémente l'interface Strategie.
 * @see Strategie
 * @author Ayoub Bouchama
 */
public class StratRapide implements Strategie {

    /**
     * Obtenir le nom de la stratégie.
     * @return nom de la stratégie
     */
    public String getNom() {
        return "rapide";
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
        return Math.min(nbAllumettes, Jeu.PRISE_MAX);
    }
}
