package construction;

/** Classe représentant un bloc de type référence à un groupe de capture.
 * Un bloc de type référence à un groupe de capture est un bloc qui correspond à une référence à un groupe de capture.
 */
public class BlockCaptureGroupReference extends TerminalBlock {

    /** Couleur du bloc. */
    static private String color = "red";
    /** le  groupe de capture. */
    private String group;

    /** Constructeur de la classe BlockCaptureGroupReference.
     * @param id L'identifiant du bloc.
     * @param invert Si le bloc est inversé ou non.
     * @param group Le groupe de capture.
     */
    public BlockCaptureGroupReference(String id, boolean invert, String group) {
        super(color, id, invert);
        this.group = group;
    }

    /** Retourne la couleur du bloc.
     * @return La couleur du bloc.
     */
    public String getColor() {
        return color;
    }

    /** Retourne le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Référence à un groupe de capture";
    }

    /** Retourne le fragment de regex correspondant au bloc.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        try {
            // Si le groupe est un nombre, on retourne le groupe tel quel
            Double.parseDouble(group);
            return "\\" + group;
            // Sinon, on retourne le groupe entre chevrons
        } catch (NumberFormatException e) {
            return "\\k<" + group + ">";
        }
    }
}