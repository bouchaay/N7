package construction;

/** Classe représentant un bloc de répétition de 0 à n fois.
 * Un bloc de répétition de 0 à n fois est un bloc qui peut être répété 0 fois ou plus.
 */
public class BlockZeroOrMore extends BlockRepeatAtLeast {

    /** Le nombre minimum de répétitions */
    private static int timesMinimum = 0;

    /** Constructeur de la classe BlockZeroOrMore
     * @param id L'identifiant du bloc
     * @param invert Si le bloc est inversé ou non
     * @param lazy Si le bloc est paresseux ou non
     */
    public BlockZeroOrMore(String id, boolean invert, boolean lazy) {
        super(id, invert, timesMinimum, lazy);
    }

    @Override
    /** Retourne le nom du bloc
     * @return Le nom du bloc
     */
    public String getName() {
        return "Répéter 0 ou plus de fois";
    }
}