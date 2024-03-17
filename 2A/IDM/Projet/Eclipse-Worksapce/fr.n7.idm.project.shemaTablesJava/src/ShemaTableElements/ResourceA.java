package ShemaTableElements;
import java.util.List;
import java.io.File;
import java.util.ArrayList;

public class ResourceA {

    /** folderPath de la ressource */
    private String folderPath;
    /** Liste des noms des algorithmes de la ressource */
    private List<String> algorithmsNames;

    /**
     * Constructeur de la classe Resource
     * @param folderPath
     */
    public ResourceA(String folderPath) {
        this.folderPath = folderPath;
        this.algorithmsNames = new ArrayList<>();

        // Aller chercher les noms des algorithmes dans le dossier
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] algorithmFiles = folder.listFiles();

            if (algorithmFiles != null) {
                for (File algorithmFile : algorithmFiles) {
                    // Vérifier si le fichier est un fichier régulier (pas un dossier)
                    if (algorithmFile.isFile()) {
                        // Ajouter le nom de l'algorithme sans l'extension au tableau
                        String algorithmName = removeFileExtension(algorithmFile.getName());
                        algorithmsNames.add(algorithmName);
                    }
                }
            }
        }
    }

    private String removeFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(0, lastDotIndex);
        }
        return fileName;
    }

    /**
     * Getter de la liste des noms des algorithmes de la ressource
     * @return
     */
    public List<String> getAlgorithmsNames() {
        return algorithmsNames;
    }

    /**
     * Getter du folderPath de la ressource
     * @return
     */
    public String getFolderPath() {
        return folderPath;
    }

    /**
     * Setter de la liste des noms des algorithmes de la ressource
     * @param algorithmsNames
     */
    public void setAlgorithmsNames(List<String> algorithmsNames) {
        this.algorithmsNames = algorithmsNames;
    }

    /**
     * Setter du folderPath de la ressource
     * @param folderPath
     */
    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    /**
     * Ajoute un nom d'algorithme à la liste des noms des algorithmes de la ressource
     * @param algorithmName
     */
    public void addAlgorithmName(String algorithmName) {
        this.algorithmsNames.add(algorithmName);
    }

    /**
     * Supprime un nom d'algorithme de la liste des noms des algorithmes de la ressource
     * @param algorithmName
     */
    public void removeAlgorithmName(String algorithmName) {
        this.algorithmsNames.remove(algorithmName);
    }

    /**
     * Affiche les informations de la ressource
     */
    public void print() {
        System.out.println("Ressource : " + this.folderPath);
        System.out.println("Algorithms : ");
        for (String algorithmName : this.algorithmsNames) {
            System.out.println("\t" + algorithmName);
        }
    }

    /** Voir si un algorithme existe ou pas */
    
}
