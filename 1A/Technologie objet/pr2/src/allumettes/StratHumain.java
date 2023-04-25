package allumettes;

import java.util.Scanner;

/**
 * Stratégie humaine.
 * La stratégie consiste à demander à l'utilisateur de saisir le nombre
 * d'allumettes à retirer.
 * Implémente l'interface Strategie.
 * @see Strategie
 * @author Ayoub Bouchama
 */
public class StratHumain implements Strategie {

    /** Scanner pour lire les données saisies par l'utilisateur. */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Constructeur de la stratégie humaine.
     */
    public StratHumain() {
    }

    /**
     * Obtenir le nom de la stratégie.
     * @return nom de la stratégie
     */
    public String getNom() {
        return "humain";
    }

    /**
     * Obtenir le nombre d'allumettes à retirer.
     * @param jeu le jeu en cours
     * @param joueurCourant le joueur courant
     * @return nombre d'allumettes à retirer
     */
    public int getPrise(Jeu jeu, Joueur joueurCourant) {
        assert jeu != null : "Le jeu ne doit pas être null.";
        int prise = 0;
        System.out.print(joueurCourant.getNom() + ", combien d'allumettes ? ");
        String input = sc.nextLine();
        try {
            prise = Integer.parseInt(input);
            return prise;
        } catch (NumberFormatException e) {
            if (input.equals("triche")) {
                tricher(jeu);
            } else if (input.equals("strategy")) {
                changerStrategie(joueurCourant);
            } else {
                System.out.println("Vous devez donner un entier.");
            }
            return joueurCourant.getStrategie().getPrise(jeu, joueurCourant);
        }
    }

    /**
     * Tricher en retirant une allumette.
     * @param jeu le jeu en cours
     */
    public static void tricher(Jeu jeu) {
        assert jeu != null : "Le jeu ne doit pas être null.";
        try {
            if (jeu.getNombreAllumettes() == 1) {
                System.out.println("[Impossible de tricher, plus qu'une allumette !]");
            } else {
                jeu.retirer(1);
                System.out.println("[Une allumette en moins, plus que "
                		+ jeu.getNombreAllumettes() + ". Chut !]");
            }
        } catch (CoupInvalideException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    /** Changer de stratégie.
     * @param joueurCourant le joueur courant
     */
    public static void changerStrategie(Joueur joueurCourant) {
        assert joueurCourant != null;
        System.out.println("Changement de stratégie pour "
                    + joueurCourant.getNom());
        System.out.print("Nouvelle stratégie : ");
        String newStrategyStr = sc.nextLine();
        Strategie newStrategy = Jouer.strategieParNom(newStrategyStr);
        joueurCourant.setStrategie(newStrategy);
        System.out.println("Stratégie changée ! ");
    }
}
