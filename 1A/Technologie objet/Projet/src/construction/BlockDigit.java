package construction;

/** Classe représentant un bloc de type nombre.
 * Un bloc de type nombre est un bloc qui correspond à un nombre.
 */
public class BlockDigit extends BlockCharacterRange {
    
    /** Constructeur de la classe BlockDigit.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     */
    public BlockDigit(String id, boolean invert) {
        super(id, invert, "\\d");
    }

    @Override
    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Nombre";
    }

    @Override
    /** fragment de regex correspondant à un nombre.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        return "\\d";
    }
}