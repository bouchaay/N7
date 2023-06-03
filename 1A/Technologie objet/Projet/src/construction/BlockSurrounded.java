package construction;

/** Classe représentant un bloc entouré. */
public abstract class BlockSurrounded extends Block {

    /** La couleur du bloc */
    private static String color = "green";

    /** Le bloc à entourer.
     * @param id L'identifiant du bloc.
     * @param invert Si le bloc est inversé ou non.
     */
    public BlockSurrounded(String id, boolean invert) {
        super(color, id, invert);
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
        return "Bloc entouré";
    }

    /** Retourne le fragment de regex correspondant au bloc
     * @return Le fragment de regex correspondant au bloc
     */
    abstract public String toRegexFragment();
}