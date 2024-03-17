package ShemaTableElements;
import java.util.ArrayList;
import java.util.List;

public class ShemaTableA {

    /** Nom de la table */
	private String name;
    /** Liste des colonnes de la table */
    private int id;
    /** Liste des colonnes de la table */
    private List<ColumnA<?>> columns;
    /** Catalogue associé à la table */
    private CatalogueA catalogue;
    
    /** Constructeur de la classe ShemaTable
     * @param name Le nom de la table
     * @param id L'identifiant de la table
     */
    public ShemaTableA(String name, int id, CatalogueA catalogue) {
        this.name = name;
        this.id = id;
        this.catalogue = catalogue;
        this.columns = new ArrayList<>();
        this.columns.add(new ColumnA<Integer>("Id", 0, ColumnDataTypeA.INTEGER, new ConstraintA("")));
    }

    /** Constructeur de la classe ShemaTable
     * @param name Le nom de la table
     * @param id L'identifiant de la table
     * @param columns La liste des colonnes de la table
     */
    public ShemaTableA(String name, int id, List<ColumnA<?>> columns) {
        this.name = name;
        this.id = id;
        this.columns = columns;
    }

    /**
     * Retourne le nom de la table
     * @return Le nom de la table
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne l'identifiant de la table
     * @return L'identifiant de la table
     */
    public int getId() {
        return id;
    }
    
    /**
     * Modifie l'id du shematable
     * @param nouveau id
     */
    public void setId(int id) {
    	this.id = id;
    }

    /**
     * Retourne le catalogue associé à la table
     * @return Le catalogue associé à la table
     */
    public CatalogueA getCatalogue() {
        return catalogue;
    }

    /**
     * Retourne la liste des colonnes de la table
     * @return La liste des colonnes de la table
     */
    public List<ColumnA<?>> getColumns() {
        return columns;
    }

    /**
     * Ajoute une colonne à la table
     * @param column La colonne à ajouter
     */
    public void addColumn(ColumnA<?> column) {
        this.columns.add(column);
    }

    public void setColumnName(String name, ColumnA<?> column) {
        int i = 0;
        for (ColumnA<?> col : this.columns) {
            if (col.getName().equals(name)) {
                this.columns.set(i, column);
                break;
            }
            i++;
        }
    }

    /**
     * Supprime la colonne d'indice index
     * @param index L'indice de la colonne à supprimer
     */
    public void removeColumn(int index) {
        this.columns.remove(index);
    }

    /**
     * Retourne la colonne d'indice index
     * @param index L'indice de la colonne à retourner
     * @return La colonne d'indice index
     */
    public ColumnA<?> getColumnById(int columnId) {
        for (ColumnA<?> column : this.columns) {
            if (column.getId() == columnId) {
                return column;
            }
        }
        return null;
    }

    /**
     * Retourner une colonne par son nom
     * @param name Le nom de la colonne à retourner
     * @return La colonne de nom name
     */
    public ColumnA<?> getColumnByName(String name) {
        ColumnA<?> columnA = null;
        for (ColumnA<?> column : this.columns) {
            if (column.getName().equals(name)) {
                columnA = column;
                break;
            } 
        }
        return columnA;
        
    }

    public void setColumnWithName(String name, List<?> values) {
        int i = 0;
        for (ColumnA<?> col : this.columns) {
            if (col.getName().equals(name)) {
                this.columns.set(i, new ColumnA<>(name, i, values));
                break;
            }
            i++;
        }
    }
    
    public int getNbColumns() {
    	return this.columns.size();
    }
}