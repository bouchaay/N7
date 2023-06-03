package construction;

/** Classe représentant un bloc de type groupe de capture.
 * Un bloc de type groupe de capture est un bloc qui correspond à un groupe de capture.
 */
public class BlockCaptureGroup extends Block {

    /** La couleur du bloc */
    private static String color = "mint";

    /** La valeur du bloc */
    // TODO ça devrait être un objet Expression
    // TODO on devrait stocker le nom de la capture
    String value;

    /** Constructeur de la classe BlockCaptureGroup.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     */
    public BlockCaptureGroup(String id, boolean invert, String value) {
        super(color, id, invert);
        this.value = value;
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    public String getColor() {
        return color;
    }

    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Groupe de capture";
    }

    /** fragment de regex correspondant à un bloc de capture.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        return "(" + this.value + ")";
    }
}
