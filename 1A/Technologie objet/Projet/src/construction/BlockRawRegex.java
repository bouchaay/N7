package construction;

/** Classe représentant un bloc de type mot.
 * Un bloc de type mot est un bloc qui correspond à un mot.
 */
public class BlockRawRegex extends TerminalBlock {

    /** La couleur du bloc */
    static private String color = "transparent";
    /** La regex */
    private String regex;

    /** Constructeur de la classe BlockRawRegex
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param regex La regex
     */
    public BlockRawRegex(String id, boolean invert, String regex) {
        super(color, id, invert);
        this.regex = regex;
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    public String getColor() {
        return color;
    }

    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Regex brute";
    }

    /** Retourne le fragment de regex correspondant au bloc
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        return regex;
    }
}
