package construction;

/** Classe représentant un bloc de type avant, il y a...
 * Un bloc de type avant, il y a... est un bloc qui correspond à une chaine de caractère précédant le mot recherché.
 */
public class BlockLookbehind extends BlockAssertion {

    /** La valeur du bloc */
    String value;

    /** Constructeur de la classe BlockLookbehind
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public BlockLookbehind(String id, boolean invert, String value) {
        super(id, invert, value);
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Avant, il y a...";
    }

    @Override
    /** Retourne le fragment de regex correspondant au bloc avant, il y a...
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        return "(?<=" + this.value + ")";
    }
}