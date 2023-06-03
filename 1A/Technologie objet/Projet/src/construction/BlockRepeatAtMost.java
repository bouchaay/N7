package construction;

/** Classe représentant un bloc de répétition au plus.
 * Un bloc de répétition au plus est un bloc qui répète un bloc au plus un certain nombre de fois.
 */
public class BlockRepeatAtMost extends BlockRepeat {

    /** Le nombre de répétitions */
    private int times;

    /** Constructeur de la classe BlockRepeatAtMost
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param times Le nombre de répétitions
     * @param lazy Si le bloc est paresseux ou non
     */
    public BlockRepeatAtMost(String id, boolean invert, int times, boolean lazy) {
        super(id, invert, 0, times, lazy);
        this.times = times;
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Répéter au plus a fois";
    }
}