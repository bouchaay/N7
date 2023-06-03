package construction;
import java.util.List;
import java.util.ArrayList;

/** Classe représentant un bloc de type ou.
 * Un bloc de type ou est un bloc qui correspond à l'union de plusieurs blocs.
 */
public class BlockOr extends AbstractBlock {

    /** La couleur du bloc */
    private static String color = "cyan";
    /** Les blocs enfants */
    private List<String> children;

    /** Constructeur de la classe BlockOr
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public BlockOr(String id, boolean invert) {
        super(color, id, invert);
        children = new ArrayList<String>();
    }

    /** Constructeur de la classe BlockOr
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param children Les blocs enfants
     */
    public BlockOr(String id, boolean invert, List<String> children) {
        super(color, id, invert);
        this.children = children;
    }

    /** Ajoute un bloc enfant
     * @param child Le bloc enfant
     */
    public String getColor() {
        return color;
    }

    /** Ajoute un bloc enfant
     * @param child Le bloc enfant
     */
    public void addChild(String child) {
        children.add(child);
    }

    /** Retourne les blocs enfants
     * @return Les blocs enfants
     */
    public List<String> getChildren() {
        return children;
    }

    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Ou";
    }

    /** Retourne le fragment de regex correspondant au bloc
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        String regex = "(";
        for (int i = 0; i < children.size(); i++) {
            regex += children.get(i);
            if (i < children.size() - 1) {
                regex += "|";
            }
        }
        return regex + ")";
    }
}
