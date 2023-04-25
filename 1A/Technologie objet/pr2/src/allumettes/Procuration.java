package allumettes;

/** Procuration d'un jeu d'allumettes.
 * @author Ayoub Bouchama
 * @see Jeu
 */
public class Procuration implements Jeu {

    /** Le jeu réel. */
    private Jeu jeuReel;

    /**
     * Créer une procuration.
     * @param jeu le jeu à jouer
     */
    public Procuration(Jeu jeu) {
        this.jeuReel = jeu;
    }

    /**
     * Obtenir le nombre d'allumettes restantes.
     * @return nombre d'allumettes restantes
     */
    public int getNombreAllumettes() {
        return jeuReel.getNombreAllumettes();
    }

    /**
     * Lève une exception.
     * @throws CoupInvalideException
     * @throws OperationInterditeException toujours
     */
    public void retirer(int nombre) throws CoupInvalideException {
        throw new OperationInterditeException();
    }
}
