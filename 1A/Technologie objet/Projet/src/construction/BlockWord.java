package construction;

/** Classe représentant un bloc de type mot.
 * Un bloc de type mot est un bloc qui correspond à un mot.
 */
public class BlockWord extends BlockSurrounded {
    
    /** Constructeur de la classe BlockWord
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     */
    public BlockWord(String id, boolean invert) {
        super(id, invert);
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Mot";
    }

    /** Retourne le fragment de regex correspondant à un mot
     * @return Le fragment de regex correspondant au bloc
     */
    public String toRegexFragment() {
        return "\\w+";
    }
}