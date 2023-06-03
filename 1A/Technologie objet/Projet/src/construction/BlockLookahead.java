package construction;

/** Classe représentant un bloc de type après, il y a...
 * Un bloc de type après, il y a... est un bloc qui correspond à ce qui suit le mot recherché.
 */
public class BlockLookahead extends BlockAssertion {
	

    /** Constructeur de la classe BlockLookahead
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public BlockLookahead(String id, boolean invert, String value) {
        super(id, invert, value);
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Après, il y a...";
    }

    /** Retourne le fragment de regex correspondant au bloc après, il y a...
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        return "(?=" + this.value + ")";
    }
}