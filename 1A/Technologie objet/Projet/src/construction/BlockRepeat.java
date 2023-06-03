package construction;

/** Classe représentant un bloc de répétition.
 * Un bloc de répétition est un bloc qui représente une répétition d'un autre bloc.
 */
public class BlockRepeat extends Block {

    /** La couleur du bloc */
    static private String color = "orange";
    /** Nombre minimum de répétitions. */
    private int timesMinimum;
    /** Nombre maximum de répétitions. */
    private int timesMaximum;
    /** Indique si le bloc est lazy. */
    private boolean lazy;

    /** Constructeur de la classe BlockRepeat.
     * @param id Identifiant du bloc.
     * @param invert Inversion du bloc.
     * @param timesMinimum Nombre minimum de répétitions.
     * @param timesMaximum Nombre maximum de répétitions.
     * @param lazy Indique si le bloc est lazy.
     */
    public BlockRepeat(String id, boolean invert, int timesMinimum, int timesMaximum, boolean lazy) {
        super(color, id, invert);
        this.timesMinimum = timesMinimum;
        this.timesMaximum = timesMaximum;
        this.lazy = lazy;
    }

    /** Retourne la couleur du bloc
     * @return La couleur du bloc
     */
    public String getColor() {
        return color;
    }

    /** Le nom du bloc.
     * @return Le nom du bloc.
     */
    public String getName() {
        return "Répéter";
    }

    /** Retourne si le bloc est lazy.
     * @return Si le bloc est lazy.
     */
    public boolean isLazy() {
        return lazy;
    }

    /** Change si le bloc est lazy.
     * @param lazy Si le bloc est lazy.
     */
    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    /** Retourne le nombre minimum de répétitions.
     * @return Le nombre minimum de répétitions.
     */
    public int getTimesMinimum() {
        return timesMinimum;
    }

    /** Retourne le nombre maximum de répétitions.
     * @return Le nombre maximum de répétitions.
     */
    public int getTimesMaximum() {
        return timesMaximum;
    }

    /** change le nombre minimum de répétitions.
     * @param timesMinimum Le nouveau nombre minimum de répétitions.
     */
    public void setTimesMinimum(int timesMinimum) {
        this.timesMinimum = timesMinimum;
    }

    /** change le nombre maximum de répétitions.
     * @param timesMaximum Le nouveau nombre maximum de répétitions.
     */
    public void setTimesMaximum(int timesMaximum) {
        this.timesMaximum = timesMaximum;
    }

    /** fragment de regex correspondant à un bloc de répétition.
     * @return Le fragment de regex correspondant au bloc.
     */
    public String toRegexFragment() {
        assert timesMaximum >= 0 && timesMinimum >= 0 && timesMinimum <= timesMaximum;
        String fragment = "";
        if (timesMinimum == 0 && timesMaximum == 0) {
            fragment = "";
        } else if (timesMinimum == 0 && timesMaximum == 1) {
            fragment = "?";
        } else if (timesMinimum == 0 && timesMaximum == Integer.MAX_VALUE) {
            fragment = "*";
        } else if (timesMinimum == 1 && timesMaximum == Integer.MAX_VALUE) {
            fragment = "+";
        } else if (timesMinimum == timesMaximum) {
            fragment = "{" + timesMinimum + "}";
        } else {
            fragment = "{" + timesMinimum + "," + timesMaximum + "}";
        }
        return fragment;
    }
}
