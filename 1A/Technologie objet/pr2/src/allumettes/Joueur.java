package allumettes;

/**
 * Un joueur de jeu d'allumettes.
 * Un joueur est caractérisé par son nom et sa stratégie.
 * @author Ayoub Bouchama
 */
public class Joueur {

    /** Nom du joueur. */
    private String nom;

    /** Stratégie du joueur. */
    private Strategie strategie;

    /**
     * Créer un joueur.
     * @param nom nom du joueur
     * @param strategie stratégie du joueur
     */
    public Joueur(String nom, Strategie strategie) {
        assert nom != null : " Le nom ne doit pas être null.";
        assert strategie != null : " La stratégie ne doit pas être null.";
        this.nom = nom;
        this.strategie = strategie;
    }

    /**
     * Obtenir le nom du joueur.
     * @return nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /** modifier le nom du joueur.
     * @param nom le nouveau nom du joueur
     */
    public void setNom(String nom) {
        assert nom != null : " Le nom ne doit pas être null.";
        this.nom = nom;
    }

    /**
     * Obtenir la stratégie du joueur.
     * @return la stratégie du joueur
     */
    public Strategie getStrategie() {
        return strategie;
    }

    /** modifier la stratégie du joueur.
     * @param strategie la nouvelle stratégie du joueur
     */
    public void setStrategie(Strategie strategie) {
        assert strategie != null : " La stratégie ne doit pas être null.";
        this.strategie = strategie;
    }

    /**
     * Obtenir le nombre d'allumettes à retirer.
     * @param jeu le jeu en cours
     * @return nombre d'allumettes à retirer
     */
    public int getPrise(Jeu jeu) {
        assert jeu != null : " Le jeu ne doit pas être null.";
        assert jeu.getNombreAllumettes() > 0 : " Le nombre "
        		+ "d'allumettes doit être supérieur à 0.";
        Strategie strategieActuel = this.strategie;
        return strategieActuel.getPrise(jeu, this);
    }
}
