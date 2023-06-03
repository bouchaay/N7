package construction;

/** Classe représentant un bloc de type mot.
 * Un bloc de type mot est un bloc qui correspond à un mot.
 */
public class BlockAlphanumericOrUnderscore extends BlockCharacterRange {

    /** La liste des caractères du bloc. */
    static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";

    /** Constructeur de la classe BlockAlphanumericOrUnderscore.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     */
    public BlockAlphanumericOrUnderscore(String id, boolean invert) {
        super(id, invert, characters);
    }

    @Override
    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Alphanumérique ou souligné";
    }

    @Override
    /** Le fragment de regex correspondant au bloc.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        return "\\w";
    }
}