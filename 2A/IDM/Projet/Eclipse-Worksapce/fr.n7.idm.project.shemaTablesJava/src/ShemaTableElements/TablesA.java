package ShemaTableElements;
import java.util.ArrayList;
import java.util.List;

/** Liste des shema de tables */
public class TablesA {

    /** Listes des shema de tables */
    private List<ShemaTableA> shemaTables;

    /**
     * Constructeur de la classe Tables
     * @param shemaTables Les shema de tables
     */
    public TablesA(List<ShemaTableA> shemaTables) {
        this.shemaTables = shemaTables;
    }

    /**
     * Constructeur de la classe Tables (nouvelle liste de shema de tables vide)
     */
    public TablesA() {
        this.shemaTables = new ArrayList<ShemaTableA>();
    }

    /**
     * Retourne les shema de tables
     * @return Les shema de tables
     */
    public List<ShemaTableA> getShemaTables() {
        return shemaTables;
    }

    /**
     * Modifie les shema de tables
     * @param shemaTables Les nouveaux shema de tables
     */
    public void setShemaTables(List<ShemaTableA> shemaTables) {
        this.shemaTables = shemaTables;
    }

    /**
     * Ajoute un shema de table
     * @param shemaTable Le shema de table a ajouter
     */
    public void addShemaTable(ShemaTableA shemaTable) {
        this.shemaTables.add(shemaTable);
    }

    /**
     * Supprime un shema de table
     * @param shemaTable Le shema de table a supprimer
     */
    public void removeShemaTable(ShemaTableA shemaTable) {
        this.shemaTables.remove(shemaTable);
    }

    /**
     * Retourne le shema de table a l'indice i
     * @param i L'indice du shema de table
     * @return Le shema de table a l'indice i
     */
    public ShemaTableA getShemaTable(int i) {
        return this.shemaTables.get(i);
    }

    /**
     * Retourne le nombre de shema de tables
     * @return Le nombre de shema de tables
     */
    public int size() {
        return this.shemaTables.size();
    }

    /**
     * Retourne le shema de table dont le nom est name
     * @param name Le nom du shema de table
     * @return Le shema de table dont le nom est name
     */
    public ShemaTableA getShemaTableByName(String name) {
        for (ShemaTableA shemaTable : this.shemaTables) {
            if (shemaTable.getName().equals(name)) {
                return shemaTable;
            }
        }
        return null;
    }
}
