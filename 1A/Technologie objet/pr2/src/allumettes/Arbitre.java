package allumettes;

/**
 * Arbitre d'un jeu d'allumettes.
 * L'arbitre est chargé de faire jouer deux joueurs
 * et de déterminer le vainqueur.
 * @author Ayoub Bouchama
 */
public class Arbitre {

    /** Premier joueur. */
    private Joueur premierJoueur;

    /** Deuxième joueur. */
    private Joueur deuxiemeJoueur;

    /** Mode confiant. */
    private boolean confiant;

    /**
     * Constructeur de l'arbitre.
     * @param premierJoueur le premier joueur
     * @param deuxiemeJoueur le deuxième joueur
     */
    public Arbitre(Joueur premierJoueur, Joueur deuxiemeJoueur) {
        assert premierJoueur != null : "joueur 1 doit être non null";
        assert deuxiemeJoueur != null : "joueur 2 doit être non null";
        this.premierJoueur = premierJoueur;
        this.deuxiemeJoueur = deuxiemeJoueur;
        this.confiant = false;
    }

    /**
     * Obtenir le premier joueur.
     * @return le premier joueur
     */
    public Joueur getPremierJoueur() {
        return this.premierJoueur;
    }

    /**
     * Obtenir le deuxième joueur.
     * @return le deuxième joueur
     */
    public Joueur getDeuxiemeJoueur() {
        return this.deuxiemeJoueur;
    }

    /** Définir le premier joueur.
     * @param premierJoueur le premier joueur
     */
    public void setPremierJoueur(Joueur joueurNouveau) {
        assert premierJoueur != null : "joueur 1 doit être non null";
        this.premierJoueur = joueurNouveau;
    }

    /** Définir le deuxième joueur.
     * @param deuxiemeJoueur le deuxième joueur
     */
    public void setDeuxiemeJoueur(Joueur joueurNouveau) {
        assert deuxiemeJoueur != null : "joueur 2 doit être non null";
        this.deuxiemeJoueur = joueurNouveau;
    }

    /**
     * Obtenir le mode confiant.
     * @return le mode confiant
     */
    public boolean getModeConfiance() {
        return this.confiant;
    }

    /** Activer le mode confiant. */
    public void activerConfiance() {
        this.confiant = true;
    }

    /**
     * Lancer une partie.
     * @param jeu le jeu à jouer
     * @throws CoupInvalideException
     */
    public void arbitrer(Jeu jeu) throws CoupInvalideException {
        assert jeu != null : "le jeu ne doit pas être null";

        // Création des joueurs
        Joueur joueurCourant = premierJoueur;
        Joueur joueurSuivant = deuxiemeJoueur;

        // Si l'arbitre n'est pas confiant, on crée une procuration
        Procuration jeuProxy = new Procuration(jeu);

    try {
        // Le jeu continue tant qu'il reste plus d'une allumette
        while (jeu.getNombreAllumettes() > 0) {

            int prise;
            // On demande au joueur courant de jouer
            do {
                // On affiche le nombre d'allumettes restantes
                System.out.print("Allumettes restantes : "
                    + jeu.getNombreAllumettes() + "\n");

                // Si l'arbitre n'est pas confiant, on utilise la procuration
                if (!this.confiant) {
                    prise = joueurCourant.getPrise(jeuProxy);
                } else {
                    prise = joueurCourant.getPrise(jeu);
                }

                // On affiche le nombre d'allumettes prises
                afficherPrise(prise, joueurCourant);

                // Si la prise n'est pas valide, on affiche un message d'erreur
                if (!priseValide(jeu, prise)) {
                    afficherErreur(jeu, prise);
                } else {
                    System.out.println();
                }

            } while (!priseValide(jeu, prise));

            // On retire les allumettes prises du jeu
            jeu.retirer(prise);

            // On inverse les joueurs
            Joueur joueurAttente = joueurCourant;
            joueurCourant = joueurSuivant;
            joueurSuivant = joueurAttente;
        }
    } catch (OperationInterditeException e) {
        System.out.println("Abandon de la partie car "
        		+ joueurCourant.getNom() + " triche !");
        Runtime.getRuntime().exit(1);
    }

        // On affiche le vainqueur de la partie
        afficherResultat(joueurCourant, joueurSuivant);
    }

    /**
     * Afficher résultat de la partie.
     * @param vainqueur le joueur vainqueur
     * @param perdant   le joueur perdant
     */
    public void afficherResultat(Joueur vainqueur, Joueur perdant) {
        System.out.println(perdant.getNom() + " perd !");
        System.out.println(vainqueur.getNom() + " gagne !");
    }

    /**
     * Vérifier si la prise est valide.
     * @param jeu   le jeu
     * @param prise la prise
     * @return true si la prise est valide, false sinon
     */
    public boolean priseValide(Jeu jeu, int prise) {
        return (prise >= 1 & prise <= Jeu.PRISE_MAX & prise <= jeu.getNombreAllumettes());
    }

    /** Afficher le message d'erreur.
     * @param prise la prise
     * @param jeu   le jeu
     */
    public void afficherErreur(Jeu jeu, int prise) {
        System.out.print("Impossible ! Nombre invalide : " + prise);
            if (prise > jeu.getNombreAllumettes()) {
                System.out.println(" (> " + jeu.getNombreAllumettes() + ")\n");
            } else if (prise > Jeu.PRISE_MAX) {
                System.out.println(" (> " + Jeu.PRISE_MAX + ")\n");
            } else if (prise < 1) {
                System.out.println(" (< 1)\n");
            }
    }

    /** afficher le nombre d'allumettes prises.
     * @param prise le nombre d'allumettes prise
     * @param joueur le joueur
     */
    public void afficherPrise(int prise, Joueur joueur) {
        System.out.print(joueur.getNom() + " prend "
                    + prise + " allumette");
        if (prise != 1 && prise != 0) {
            System.out.print("s");
        }
        System.out.print(".\n");
    }
}
