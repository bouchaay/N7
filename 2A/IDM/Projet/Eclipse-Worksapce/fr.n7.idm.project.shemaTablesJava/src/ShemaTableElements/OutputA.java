package ShemaTableElements;

/** Classe représentant une sortie d'un algorithme */
public class OutputA extends ColumnA {

    /**
     * Constructeur de la classe Output
     * @param name Le nom de la colonne
     * @param dataType Le type de la colonne
     */
    public OutputA(String name, ColumnDataTypeA dataType) {
        super(name, 0, dataType, null);
    }
}
