package ShemaTableElements;
import java.util.ArrayList;
import java.util.List;

public class CatalogueA {

    /** Le nom du catalogue */
    private String name;
    /** Les algorithmes */
    private List<AlgorithmA> algorithms;

    /**
     * Constructeur de la classe Catalogue
     * @param name Le nom du catalogue
     * @param algorithms Les noms des algorithmes du catalogue
     */
    public CatalogueA(String name, List<AlgorithmA> algorithms) {
        this.name = name;
        this.algorithms = algorithms;
    }

    /**
     * Constructeur de la classe Catalogue (nouveau catalogue vide)
     * @param name Le nom du catalogue
     */
    public CatalogueA(String name) {
        this.name = name;
        this.algorithms = new ArrayList<AlgorithmA>();
    }

    /**
     * Retourne le nom du catalogue
     * @return Le nom du catalogue
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne les noms des algorithmes du catalogue
     * @return Les noms des algorithmes du catalogue
     */
    public List<AlgorithmA> getAlgorithms() {
        return algorithms;
    }

    /**
     * Modifie le nom du catalogue
     * @param name Le nouveau nom du catalogue
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifie les noms des algorithmes du catalogue
     * @param algorithms Les nouveaux noms des algorithmes du catalogue
     */
    public void setAlgorithms(List<AlgorithmA> algorithms) {
        this.algorithms = algorithms;
    }

    /**
     * Ajoute un algorithme au catalogue
     * @param algorithm Le nom de l'algorithme à ajouter
     */
    public void addAlgorithm(AlgorithmA algorithm) {
        this.algorithms.add(algorithm);
    }

    /**
     * Retire un algorithme du catalogue
     * @param algorithm Le nom de l'algorithme à retirer
     */
    public void removeAlgorithm(AlgorithmA algorithm) {
        this.algorithms.remove(algorithm);
    }

    /** Retourner la liste des noms des algorithmes du catalogue
     * @return La liste des noms des algorithmes du catalogue
     */
    public List<String> getAlgorithmsNames() {
        List<String> algorithmsNames = new ArrayList<String>();
        for (AlgorithmA algorithm : this.algorithms) {
            algorithmsNames.add(algorithm.getName());
        }
        return algorithmsNames;
    }
}
