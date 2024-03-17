package ShemaTableElements;
import java.util.ArrayList;
import java.util.List;

/** Classe représentant une entrée d'un algorithme */
public class InputA {

    /** La liste des colonnes pour l'entrée de l'algorithme */
    private List<ColumnA<?>> columns;
    /** la constante pour l'entrée de l'algorithme */
    private ConstantA constante;

    /**
     * Constructeur de la classe Input (nouvelle entrée vide)
     */
    public InputA() {
        this.columns = new ArrayList<ColumnA<?>>();
    }

    /**
     * Constructeur de la classe Input (nouvelle entrée vide)
     * @param constante La constante pour l'entrée de l'algorithme
     */
    public InputA(ConstantA constante) {
        this.columns = new ArrayList<ColumnA<?>>();
        this.constante = constante;
    }

    /**
     * Constructeur de la classe Input
     * @param columns La liste des colonnes pour l'entrée de l'algorithme
     * @param constante La constante pour l'entrée de l'algorithme
     */
    public void Input(List<ColumnA<?>> columns, ConstantA constante) {
        this.columns = columns;
        this.constante = constante;
    }

    /**
     * Retourne la liste des colonnes pour l'entrée de l'algorithme
     * @return La liste des colonnes pour l'entrée de l'algorithme
     */
    public List<ColumnA<?>> getColumns() {
        return columns;
    }

    /**
     * Retourne la constante pour l'entrée de l'algorithme
     * @return La constante pour l'entrée de l'algorithme
     */
    public ConstantA getConstant() {
        return constante;
    }

    /**
     * Modifie la liste des colonnes pour l'entrée de l'algorithme
     * @param columns La nouvelle liste des colonnes pour l'entrée de l'algorithme
     */
    public void setColumns(List<ColumnA<?>> columns) {
        this.columns = columns;
    }

    /**
     * Modifie la constante pour l'entrée de l'algorithme
     * @param constante La nouvelle constante pour l'entrée de l'algorithme
     */
    public void setConstant(ConstantA constante) {
        this.constante = constante;
    }

    /**
     * Ajoute une colonne à la liste des colonnes pour l'entrée de l'algorithme
     * @param column La colonne à ajouter
     */
    public void addColumnToInput(ColumnA<?> column) {
        this.columns.add(column);
    }

    /**
     * Retourne le type de donnée des colonnes de l'entrée de l'algorithme (colonne 2)
     * @return Le type de donnée des colonnes de l'entrée de l'algorithme
     */
    public ColumnDataTypeA getDataType() {
        if (columns.size() > 1) {
            return columns.get(1).getDataType();
        } else {
            // retourner le type int
            return ColumnDataTypeA.INTEGER;
        }
    }
}
