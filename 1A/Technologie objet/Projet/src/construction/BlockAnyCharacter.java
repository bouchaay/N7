package construction;

/** Classe représentant un bloc de caractère quelconque.
 * Un bloc de caractère quelconque est un bloc qui représente un caractère quelconque.
 */
public class BlockAnyCharacter extends BlockCharacterRange {
    
    /** Constructeur de la classe BlockAnyCharacter.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     */
    public BlockAnyCharacter(String id, boolean invert) {
        super(id, invert, ".");
    }

    @Override
    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Caractère quelconque";
    }

    @Override
    /** fragment de regex correspondant à un caractère quelconque.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        return ".";
    }
}