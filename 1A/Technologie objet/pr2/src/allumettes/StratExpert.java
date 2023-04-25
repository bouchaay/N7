package allumettes;

/**
 * Stratégie experte.
 * La stratégie consiste à prendre le nombre d'allumettes nécessaire pour
 * gagner.
 * Implémente l'interface Strategie.
 * @see Strategie
 * @author Ayoub Bouchama
 */
public class StratExpert implements Strategie {

    /** Nombre utilisé pour la stratégie experte. */
    private static final int EXPERTISENUMBER = 4;
    /**
     * Obtenir le nom de la stratégie.
     * @return nom de la stratégie
     */
    public String getNom() {
        return "expert";
    }

    /**
     * Obtenir le nombre d'allumettes à retirer.
     * Si J1@Expert et J2@Expert, J2 gagne.
     * @param jeu le jeu en cours
     * @return nombre d'allumettes à retirer
     */
    public int getPrise(Jeu jeu, Joueur joueurCourant) {
        assert jeu != null : " Le jeu ne doit pas être null.";
        int prise;
        int nbAllumettes = jeu.getNombreAllumettes();
        if (nbAllumettes % EXPERTISENUMBER == 2 || nbAllumettes % EXPERTISENUMBER == 1) {
            prise = 1;
        } else if (nbAllumettes % EXPERTISENUMBER == Jeu.PRISE_MAX || nbAllumettes == 2) {
            prise = 2;
        } else {
            prise = Jeu.PRISE_MAX;
        }
        return prise;
    }
}
