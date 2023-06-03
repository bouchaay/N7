package construction;

/** Classe représentant un bloc de type répétition au moins une fois.
 * Un bloc de répétition au moins une fois est un bloc qui répète au moins une fois un bloc.
 */
public class BlockOneOrMore extends BlockRepeatAtLeast {

    /** Le nombre minimum de répétitions */
    static int timesMinimum = 1;

    /** Constructeur de la classe BlockOneOrMore
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param lazy Si le bloc est paresseux ou non
     */
    public BlockOneOrMore(String id, boolean invert, boolean lazy) {
        super(id, invert, timesMinimum, lazy);
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Répéter au moins une fois";
    }
}
