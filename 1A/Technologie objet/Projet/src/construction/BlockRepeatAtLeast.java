package construction;

/** Classe représentant un bloc de répétition au moins.
 * Un bloc de répétition au moins est un bloc qui répète au moins un nombre de fois un bloc.
 */
public class BlockRepeatAtLeast extends BlockRepeat {

    /** Le nombre de répétitions */
    private int times;

    /** Constructeur de la classe BlockRepeatAtLeast
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param times Le nombre de répétitions
     * @param lazy Si le bloc est paresseux ou non
     */
    public BlockRepeatAtLeast(String id, boolean invert, int times, boolean lazy) {
        super(id, invert, times, Integer.MAX_VALUE, lazy);
        this.times = times;
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Répéter au moins a fois";
    }
}