package construction;

/** Classe abstraite des blocs de l'expression régulière.
 * 
 * Cette classe représente un bloc de l'expression régulière.
 * 
 * @author Ayoub Bouchama
 */
public abstract class AbstractBlock {

    /** La couleur du bloc */
    private String color;

    /** L'identifiant du bloc */
    private String id;

    /** Si le bloc est inversé ou non */
    private boolean invert;

    /** Constructeur de la classe AbstractBlock
     * @param color La couleur du bloc
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public AbstractBlock(String color, String id, boolean invert){
        this.color = color;
        this.id = id;
        this.invert = invert;
    }

    /** Retourne l'ID du bloc
     * @return L'ID du bloc
     */
    public String getId() {
        return this.id;
    }

    /** Retourne si le bloc est inversé ou non
     * @return Si le bloc est inversé ou non
     */
    public boolean isInvert() {
        return this.invert;
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    public String getColor() {
        return this.color;
    }

    /** Retourne l'identifiant du bloc
     * @return L'identifiant du bloc
     */
    public abstract String getName();

    /** Retourne le fragment de regex correspondant au bloc
     * @return Le fragment de regex correspondant au bloc
     */
    public abstract String toRegexFragment();
}
