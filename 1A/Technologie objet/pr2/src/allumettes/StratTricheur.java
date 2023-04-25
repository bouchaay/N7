package allumettes;

/**
 * Stratégie tricheur.
 * Consiste à ne laisser que 2 allumettes au joueur adverse.
 * Implémente l'interface Strategie.
 * @see Strategie
 * @author Ayoub Bouchama
 */
public class StratTricheur implements Strategie {

    /**
     * Constructeur de la stratégie tricheur.
     */
    public StratTricheur() {
    }

    /**
     * Obtenir le nom de la stratégie.
     * @return nom de la stratégie
     */
    public String getNom() {
        return "tricheur";
    }

    /**
     * Obtenir le nombre d'allumettes à retirer.
     * @param jeu le jeu en cours
     * @param joueurCourant le joueur courant
     * @return nombre d'allumettes à retirer
     */
    public int getPrise(Jeu jeu, Joueur joueurCourant) {
        assert jeu != null : "Le jeu ne doit pas être null.";
        try {
            if (jeu.getNombreAllumettes() > 2) {
                System.out.println("[Je triche...]");
                jeu.retirer(jeu.getNombreAllumettes() - 2);
                System.out.println("[Allumettes restantes : "
                		+ jeu.getNombreAllumettes() + "]");
                return 1;
            } else {
                // retourner 1 s'il reste 1 allumette.
                return 1;
            }
        } catch (CoupInvalideException e) {
            System.out.println("Message d'erreur: "
            		+ e.getMessage() + " (CoupInvalideException)");
        }
        // Retour par défaut juste pour que le code compile.
        return 0;
    }
}
