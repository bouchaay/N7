package ShemaTableElements;
import java.util.ArrayList;
import java.util.List;

/** 
 * Classe représentant une colonne du shemaTable
 * @param <T> Le type de la colonne
 */
public class ColumnA <T> {

    /** Le nom de la colonne */
	private String name;
    /** L'identifiant de la colonne */
    private int id;
    /** Le type de la colonne */
    private ColumnDataTypeA dataType;
    /** La contrainte de la colonne */
    private ConstraintA constraintFile;
    /** La liste des valeurs de la colonne */
    private List<T> values;

    /**
     * Constructeur de la classe Column
     * @param name Le nom de la colonne
     * @param id L'identifiant de la colonne
     * @param dataType Le type de la colonne
     * @param constraintFile La contrainte de la colonne
     */
    public ColumnA(String name, int id, ColumnDataTypeA dataType, ConstraintA constraintFile) {
        this.name = name;
        this.id = id;
        this.dataType = dataType;
        this.constraintFile = constraintFile;
        this.values = new ArrayList<>();
    }
    
    /**
     * Constructeur de la classe  avec une liste de valeurs
     * @param name Le nom de la colonne
     * @param id L'identifiant de la colonne
     * @param dataType Le type de la colonne
     * @param constraintFile La contrainte de la colonne
     * @param values La liste des valeurs de la colonne
     */
    public ColumnA(String name, int id, ColumnDataTypeA dataType, ConstraintA constraintFile, List<T> values) {
        this.name = name;
        this.id = id;
        this.dataType = dataType;
        this.constraintFile = constraintFile;
        this.values = values;
    }

    public ColumnA(String name, int id,  List<T> value) {
        this.name = name;
        this.id = id;
        this.values = value;
       
    }

	/**
     * Retourne le nom de la colonne
     * @return Le nom de la colonne
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne l'identifiant de la colonne
     * @return L'identifiant de la colonne
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le type de la colonne
     * @return Le type de la colonne
     */
    public ColumnDataTypeA getDataType() {
        return dataType;
    }

    /**
     * Retourne la contrainte de la colonne
     * @return La contrainte de la colonne
     */
    public ConstraintA getConstraintFile() {
        return constraintFile;
    }

    /**
     * Retourne la liste des valeurs de la colonne
     * @return La liste des valeurs de la colonne
     */
    public List<T> getValues() {
        return values;
    }

    /**
     * Ajoute une valeur à la liste des valeurs de la colonne
     * @param value La valeur à ajouter
     */
    public void addValue(T value) {
        this.values.add(value);
    }

    /**
     * Retourne la valeur à l'indice i de la liste des valeurs de la colonne
     * @param i L'indice de la valeur à retourner
     * @return La valeur à l'indice i de la liste des valeurs de la colonne
     */
    public T getValue(int i) {
        return this.values.get(i);
    }

    /**
     * Retourne la taille de la liste des valeurs de la colonne
     * @return La taille de la liste des valeurs de la colonne
     */
    public int getSize() {
        return this.values.size();
    }

    /**
     * Modifie le nom de la colonne
     * @param name Le nouveau nom de la colonne
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifie l'identifiant de la colonne
     * @param id Le nouvel identifiant de la colonne
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Modifie le type de la colonne
     * @param dataType Le nouveau type de la colonne
     */
    public void setDataType(ColumnDataTypeA dataType) {
        this.dataType = dataType;
    }

    /**
     * Modifie la contrainte de la colonne
     * @param constraintFile La nouvelle contrainte de la colonne
     */
    public void setConstraintFile(ConstraintA constraintFile) {
        this.constraintFile = constraintFile;
    }

	public void setValue (T value) {
        this.values.add(value);
    }

    public void setValues (List<T> values) {
        this.values = values;
    }
}