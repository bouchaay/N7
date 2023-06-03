package construction;

/** Classe représentant un bloc de type ligne.
 * Un bloc de type ligne est un bloc qui correspond à une ligne.
 */
public class BlockLine extends BlockSurrounded {
    
    /** Constructeur de la classe BlockLine
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public BlockLine(String id, boolean invert) {
        super(id, invert);
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Ligne";
    }

    /** Retourne le fragment de regex correspondant à une ligne
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        return "^$";
    }
}