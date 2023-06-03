package construction;

/** Classe représentant un bloc littéral.
 * Un bloc littéral est un bloc qui correspond à une expression littérale.
 */
public class BlockLiteral extends TerminalBlock {

    /** La couleur du bloc */
    static String color = "white";
    /** L'expression littérale */
    String literal;
    /** Liste des caractères spéciaux */
    static String specialCharacters = "\\.*+?|^${}[]()";

    /** Constructeur de la classe BlockLiteral
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param literal L'expression littérale
     */
    public BlockLiteral(String id, boolean invert, String literal) {
        super(color, id, invert);
        this.literal = literal;
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    public String getColor() {
        return color;
    }

    /** Retourne l'identifiant du bloc
     * @return L'identifiant du bloc
     */
    public String getName() {
        return "Expression literalle";
    }

    /** Retourne le fragment de regex correspondant au bloc
     * Interprète les caractères spéciaux en ajoutant un antislash devant
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        String regex = "";
        for (int i = 0; i < literal.length(); i++) {
            if (specialCharacters.contains(literal.substring(i, i + 1))) {
                regex += "\\";
            }
            regex += literal.substring(i, i + 1);
        }
        return regex;
    }
}