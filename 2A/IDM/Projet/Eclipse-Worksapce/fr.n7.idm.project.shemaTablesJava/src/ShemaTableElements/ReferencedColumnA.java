package ShemaTableElements;

public class ReferencedColumnA extends ColumnA {

    /** La table étrangère */
	private ShemaTableA foreignTable;
    /** La colonne étrangère */
    private ColumnA<?> foreignColumn;

    /**
     * Constructeur de la classe ReferencedColumn
     * @param name Le nom de la colonne
     * @param id L'identifiant de la colonne
     * @param dataType Le type de la colonne
     * @param constraintFile La contrainte de la colonne
     * @param foreignTable La table étrangère
     * @param foreignColumn La colonne étrangère
     */
    public ReferencedColumnA(String name, int id, ColumnDataTypeA dataType, ConstraintA constraintFile, ShemaTableA foreignTable, ColumnA<?> foreignColumn) {
        super(name, id, dataType, constraintFile, foreignColumn.getValues());
        this.foreignTable = foreignTable;
        this.foreignColumn = foreignColumn;
    }

    /**
     * Retourne la table étrangère
     * @return La table étrangère
     */
    public ShemaTableA getForeignTable() {
        return foreignTable;
    }

    /**
     * Retournela colonne étrangère
     * @return La colonne étrangère
     */
    public ColumnA<?> getForeignColumn() {
        return foreignColumn;
    }
}
