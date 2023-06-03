package construction;
import java.util.List;
import java.util.ArrayList;

/** Classe Block.
 * 
 * Cette classe représente un bloc de type Block.
 * 
 * @author Ayoub Bouchama
 *
*/
public abstract class Block extends AbstractBlock {
  
    /** La liste des blocs enfants */
    private List<AbstractBlock> children;

    /** Constructeur de la classe Block
     * @param color La couleur du bloc
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public Block(String color, String id, boolean invert) {
        super(color, id, invert);
        this.children = new ArrayList<>();
    }

    /** Constructeur de la classe Block
     * prend en paramètre un nombre variable de blocs enfants
     * @param color La couleur du bloc
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param children La liste des blocs enfants
     */
    public Block(String color, String id, boolean invert, AbstractBlock... children) {
        super(color, id, invert);
        this.children = new ArrayList<>();
        for (AbstractBlock child : children) {
            this.children.add(child);
        }
    }

    /** Ajoute un bloc enfant au bloc
     * @param child Le bloc enfant à ajouter
     */
    public void addChild(AbstractBlock child) {
        this.children.add(child);
    }

    /** Retourne la liste des blocs enfants
     * @return La liste des blocs enfants
     */
    public List<AbstractBlock> getChildren() {
        return this.children;
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    public abstract String getColor();

    /** Retourne l'identifiant du bloc
     * @return L'identifiant du bloc
     */
    public abstract String getName();

    /** Retourne le fragment de regex correspondant au bloc
     * @return Le fragment de regex correspondant au bloc
     */
    public abstract String toRegexFragment();
}