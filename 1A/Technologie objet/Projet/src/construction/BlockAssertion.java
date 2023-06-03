package construction;

/** Classe représentant un bloc de type assertion.
 * Un bloc de type assertion est un bloc qui correspond à une assertion.
 */
public abstract class BlockAssertion extends Block {
    
    /** La couleur du bloc */
    static String color = "green";

    /* valeur de l'assertion */
    String value;

    /** Constructeur de la classe BlockAssertion.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     */
    public BlockAssertion(String id, boolean invert, String value) {
        super(color, id, invert);
        this.value = value;
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
        return "Assertion";
    }

    /** Retourne la valeur de l'assertion
     * @return La valeur de l'assertion
     */
    public String getValue() {
        return this.value;
    }

    /** Modifie la valeur de l'assertion
     * @param value La nouvelle valeur de l'assertion
     */
    public void setValue(String value) {
        this.value = value;
    }

    /** Retourne le fragment de regex correspondant à une assertion
     * @param assertionType Le type d'assertion
     * @param value La valeur de l'assertion
     * @return Le fragment de regex correspondant au bloc
     */
    abstract public String toRegexFragment();
}