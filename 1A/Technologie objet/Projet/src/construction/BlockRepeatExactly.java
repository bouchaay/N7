package construction;

/** Classe représentant un bloc de répétition exacte.
 * Un bloc de répétition exacte est un bloc qui représente une répétition exacte d'un autre bloc.
 */
public class BlockRepeatExactly extends BlockRepeat {
    
    /** Nombre de répétitions. */
    private int times;

    /** Constructeur de la classe BlockRepeatExactly.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     * @param times Nombre de répétitions.
     * @param lazy Si le bloc est paresseux ou non.
     */
    public BlockRepeatExactly(String id, boolean invert, int times, boolean lazy) {
        super(id, invert, times, times, lazy);
        this.times = times;
    }

    @Override
    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Répéter a fois";
    }
}