package construction;

/** Classe représentant un bloc de type retour à la ligne.
 * Un bloc de type retour à la ligne est un bloc qui correspond à un retour à la ligne.
 */
public class BlockNewLine extends BlockCharacterRange {
    
    /** Constructeur de la classe BlockNewline.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     */
    public BlockNewLine(String id, boolean invert) {
        super(id, invert, "\\r?\\n");
    }

    @Override
    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Retour à la ligne";
    }

    @Override
    /** fragment de regex correspondant au retour à la ligne.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        return "\\r?\\n";
    }
}