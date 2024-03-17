package ShemaTableElements;

/** Représente une constante */
public class ConstantA {

    /** La constante */
    private int value;

    /**
     * Constructeur de la classe Constant
     * @param value La constante
     */
    public ConstantA(int value) {
        this.value = value;
    }

    /**
     * Retourne la constante
     * @return La constante
     */
    public int getValue() {
        return value;
    }

    /**
     * Modifie la constante
     * @param value La nouvelle constante
     */
    public void setValue(int value) {
        this.value = value;
    }
}
