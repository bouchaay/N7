package construction;

/** Classe représentant un bloc de caractère unicode.
 * Un bloc de caractère unicode est un bloc qui représente un caractère unicode.
 */
public class BlockUnicodeClass extends BlockCharacterRange {

    /** Unicode chaine de caractères. */
    private String unicodeClass;

    /** Constructeur de la classe BlockUnicodeClass.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     * @param unicodeClass Unicode chaine de caractères.
     */
    public BlockUnicodeClass(String id, boolean invert, String unicodeStr) {
        super(id, invert, unicodeStr);
    }

    @Override
    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Caractère unicode";
    }

    @Override
    /** fragment de regex correspondant à un caractère unicode.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        //return "\\p{" + unicodeClass + "}";
        return "\\p{" + unicodeClass + "}";
    }
}