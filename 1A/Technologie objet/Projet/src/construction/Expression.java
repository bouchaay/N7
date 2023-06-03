package construction;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

/** Classe représentant une expression régulière. */
public class Expression {
    
    /** Le chemin du fichier contenant l'expression régulière */
    private String filepath;

    /** La liste des blocs de l'expression régulière */
    private List<AbstractBlock> blocks;

    /** Constructeur de la classe Expression
     * @param filepath Le chemin du fichier contenant l'expression régulière
     * @param block
     */
    public Expression(String filepath) {
        this.filepath = filepath;
        this.blocks = new ArrayList<>();
    }

    /**  Constructeur de la classe Expression
     * prend en paramètre un nombre variable de blocs
     * @param filepath Le chemin du fichier contenant l'expression régulière
     * @param blocks La liste des blocs de l'expression régulière
     */
    public Expression(String filepath, AbstractBlock... blocks) {
        this(filepath);
        this.blocks = new ArrayList<>();
        for (AbstractBlock block : blocks) {
            this.blocks.add(block);
        }
    }

    /** Retourne le chemin du fichier contenant l'expression régulière
     * @return Le chemin du fichier contenant l'expression régulière
     */
    public String getFilepath() {
        return this.filepath;
    }

    /** Ajoute un bloc à l'expression régulière
     * @param block Le bloc à ajouter
     */
    public void addBlock(AbstractBlock block) {
        this.blocks.add(block);
    }
    /** Retourne la liste des blocs de l'expression régulière
     * @return La liste des blocs de l'expression régulière
     */
    public List<AbstractBlock> getBlocks() {
        return this.blocks;
    }

    /** Retourne le fragment de regex correspondant à l'expression régulière
     * @return Le fragment de regex correspondant à l'expression régulière
     */
    public String toRegexFragment() {
        String regex = "";
        for (AbstractBlock block : this.blocks) {
            regex += block.toRegexFragment();
        }
        return regex;
    }

    /** Retourne le pattern de regex correspondant à l'expression régulière
     * @return Le pattern de regex correspondant à l'expression régulière
     */
    public Pattern toRegex() {
        return Pattern.compile(this.toRegexFragment());
    }
}