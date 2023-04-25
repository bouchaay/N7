package allumettes;

/**
 * Premier jeu d'allumettes.
 * Implémente l'interface Jeu.
 * @see Jeu
 * @author Ayoub Bouchama
 */
public class SujetReel implements Jeu {

    /** Nombre d'allumettes restantes. */
    private int nombreAllumettes;

    /**
     * Créer un jeu d'allumettes.
     * @param nombreAllumettes nombre d'allumettes dans le jeu
     */
    public SujetReel(int nombreAllumettes) {
        assert nombreAllumettes > 0 : "Nombre d'allumettes invalide";
        this.nombreAllumettes = nombreAllumettes;
    }

    /**
     * Affiche le nombre d'allumettes restantes.
     * @return le nombre d'allumettes restantes
     */
    public String toString() {
        return "Il reste " + this.nombreAllumettes + " allumettes";
    }

    /**
     * Obtenir le nombre d'allumettes restantes.
     * @return nombre d'allumettes restantes
     */
    public int getNombreAllumettes() {
        return this.nombreAllumettes;
    }

    /**
     * Obtenir le nombre maximum d'allumettes à retirer.
     * @return nombre maximum d'allumettes à retirer
     */
    public void retirer(int nombre) {
        assert nombre > 0 && nombre <= PRISE_MAX
                : "Nombre d'allumettes à retirer invalide";
        nombreAllumettes -= nombre;
    }
}
