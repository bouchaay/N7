package construction;

/** Classe abstraite représentant un bloc terminal.
 * Un bloc terminal est un bloc qui ne peut pas être composé d'autres blocs.
 */
abstract public class TerminalBlock extends AbstractBlock {

    /** Constructeur de la classe TerminalBlock
     * @param color La couleur du bloc
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public TerminalBlock(String color, String id, boolean invert) {
        super(color, id, invert);
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    abstract public String getColor();

    /** Retourne l'identifiant du bloc
     * @return L'identifiant du bloc
     */
    abstract public String getName();

    /** Retourne le fragment de regex correspondant au bloc
     * @return Le fragment de regex correspondant au bloc
     */
    abstract public String toRegexFragment();
}