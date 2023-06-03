package construction;

/** Classe représentant un bloc de répétition optionnelle.
 * Un bloc de répétition optionnelle est un bloc qui représente une répétition optionnelle d'un autre bloc.
 */
public class BlockOptional extends BlockRepeat {

    /** Nombre de répétitions minimum. */
    static int timesMinimum = 0;
    /** Nombre de répétitions maximum. */
    static int timesMaximum = 1;

    /** Constructeur de la classe BlockOptional.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     * @param lazy Indique si le bloc est lazy.
     */
    public BlockOptional(String id, boolean invert, boolean lazy) {
        super(id, invert, timesMinimum, timesMaximum, lazy);
    }

    @Override
    /** Retourne le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Répéter 0 ou 1 fois";
    }
}