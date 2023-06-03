package construction;

/** Classe représentant un bloc de type caractère de classe.
 * Un bloc de type caractère de classe est un bloc qui correspond à des caractères.
 */
public class BlockCharacterRange extends TerminalBlock {

    /** La couleur du bloc */
    private static String color = "magenta";
    /** Le caractère de la classe */
    private String characters;

    /** Constructeur de la classe BlockCharacterRange
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param characters Le caractère de la classe
     */
    public BlockCharacterRange(String id, boolean invert, String characters) {
        super(color, id, invert);
        this.characters = characters;
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    public String getCharacter() {
        return this.characters;
    }

    /** Modifie le caractère de la classe
     * @param characters Le nouveau caractère de la classe
     */
    public void setCharacter(String characters) {
        this.characters = characters;
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
        return "Plage de caractères";
    }

    /** Retourne le fragment de regex correspondant au bloc
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        //if (this.isInvert()) {
        //    return "[^" + this.characters + "]";
        //} else {
        //    return "[" + this.characters + "]";
        //}
        return "[" + this.characters + "]";
    }
}
