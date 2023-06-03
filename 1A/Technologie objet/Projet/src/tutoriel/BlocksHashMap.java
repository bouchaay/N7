package tutoriel;
import java.util.HashMap;

/** Dictionnaire des blocs.
 * Contient un block string clé et son explication string.
 */
public class BlocksHashMap {

    /** Clé du dictionnaire. */
    private String regexString;
    /** Valeur du dictionnaire. */
    private String explanationString;

    /** Constructeur.
     * @param regexString Clé du dictionnaire.
     * @param explanationString Valeur du dictionnaire.
     */
    public BlocksHashMap(String regexString, String explanationString) {
        this.regexString = regexString;
        this.explanationString = explanationString;
    }

    /** Getter.
     * @return Clé du dictionnaire.
     */
    public String getRegexString() {
        return regexString;
    }

    /** Setter.
     * @param regexString Clé du dictionnaire.
     */
    public void setRegexString(String regexString) {
        this.regexString = regexString;
    }

    /** Getter.
     * @return Valeur du dictionnaire.
     */
    public String getExplanationString() {
        return explanationString;
    }

    /** Setter.
     * @param explanationString Valeur du dictionnaire.
     */
    public void setExplanationString(String explanationString) {
        this.explanationString = explanationString;
    }

    /** Le dictionnaire contenant tous les expressions regex possibles et leurs explications.
     * @return Le dictionnaire contenant tous les expressions regex possibles et leurs explications.
     */
    public static HashMap<String, String> dictionnaireRegex() {
        HashMap<String, String> dictionnaire = new HashMap<>();
        dictionnaire.put(".*", "Tous les caractères");
        dictionnaire.put("\\d", "Un chiffre");
        dictionnaire.put("\\D", "Un caractère qui n'est pas un chiffre");
        dictionnaire.put("\\w", "Un caractère alphanumérique");
        dictionnaire.put("\\W", "Un caractère qui n'est pas alphanumérique");
        dictionnaire.put("\\s", "Un espace");
        dictionnaire.put("\\S", "Un caractère qui n'est pas un espace");
        dictionnaire.put("\\b", "Un mot");
        dictionnaire.put("\\B", "Un caractère qui n'est pas un mot");
        dictionnaire.put("\\t", "Une tabulation");
        dictionnaire.put("\\n", "Un retour à la ligne");
        dictionnaire.put("\\r", "Un retour chariot");
        dictionnaire.put("\\f", "Un saut de page");
        dictionnaire.put("\\v", "Une tabulation verticale");
        dictionnaire.put("\\cX", "Un caractère de contrôle");
        dictionnaire.put("\\xhh", "Un caractère hexadécimal");
        dictionnaire.put("\\uhhhh", "Un caractère unicode");
        dictionnaire.put("\\Q", "Début de la zone de citation");
        dictionnaire.put("\\E", "Fin de la zone de citation");
        dictionnaire.put("\\A", "Le début de la chaîne");
        dictionnaire.put("\\Z", "La fin de la chaîne");
        dictionnaire.put("\\z", "La fin de la chaîne");
        dictionnaire.put("\\G", "Le dernier match");
        dictionnaire.put("[A-Z]", "Un caractère entre A et Z");
        dictionnaire.put("[^A-Z]", "Un caractère qui n'est pas entre A et Z");
        dictionnaire.put("[A-Za_z]", "Un caractère entre A et Z ou entre a et z");
        dictionnaire.put("[^A-Za_z]", "Un caractère qui n'est pas entre A et Z ou entre a et z");
        dictionnaire.put("[0-9]", "Un chiffre entre 0 et 9");
        dictionnaire.put("[^0-9]", "Un caractère qui n'est pas entre 0 et 9");
        dictionnaire.put("[A-Za-z0-9]", "Un caractère entre A et Z ou entre a et z ou entre 0 et 9");
        dictionnaire.put("[^A-Za-z0-9]", "Un caractère qui n'est pas entre A et Z ou entre a et z ou entre 0 et 9");
        dictionnaire.put("[A-Za-z0-9_]", "Un caractère entre A et Z ou entre a et z ou entre 0 et 9 ou _");
        dictionnaire.put("[^A-Za-z0-9_]", "Un caractère qui n'est pas entre A et Z ou entre a et z ou entre 0 et 9 ou _");
        dictionnaire.put("[A-Za-z0-9_\\-]", "Un caractère entre A et Z ou entre a et z ou entre 0 et 9 ou _ ou -");
        dictionnaire.put("[^A-Za-z0-9_\\-]", "Un caractère qui n'est pas entre A et Z ou entre a et z ou entre 0 et 9 ou _ ou -");
        dictionnaire.put("[A-Za-z0-9_\\-\\.]", "Un caractère entre A et Z ou entre a et z ou entre 0 et 9 ou _ ou - ou .");
        dictionnaire.put("[^A-Za-z0-9_\\-\\.]", "Un caractère qui n'est pas entre A et Z ou entre a et z ou entre 0 et 9 ou _ ou - ou .");
        dictionnaire.put("[A-Za-z0-9_\\-\\.\\+]", "Un caractère entre A et Z ou entre a et z ou entre 0 et 9 ou _ ou - ou . ou +");
        dictionnaire.put("+", "Un ou plusieurs");
        dictionnaire.put("*", "Zéro ou plusieurs");
        dictionnaire.put("?", "Zéro ou un");
        return dictionnaire;
    }
}
