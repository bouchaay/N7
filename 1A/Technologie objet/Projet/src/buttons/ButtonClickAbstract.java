package buttons;
import java.awt.event.ActionEvent;
import javax.swing.*;

/** Classe abstraite pour les boutons.
 * prend en paramètre un block et un champ de texte.
 */
public abstract class ButtonClickAbstract {

    /** Id du bouton. */
    private String id;
    /** Nom du bouton. */
    private String name;
    /** Regex du bouton. */
    private String regex;
    /** Couleur du bouton. */
    private String color;
    /** Bouton simple ou complexe. */
    private boolean simpleButton;
    /** Champ de texte associé au bouton. */
    private JTextPane textField;

    /** Constructeur.
     * 
     * @param id Id du bouton
     * @param name Nom du bouton
     * @param regex Regex du bouton
     * @param color Couleur du bouton
     * @param simpleButton Bouton simple ou complexe
     * @param textField Champ de texte associé au bouton
     */
    public ButtonClickAbstract(String id, String name, String regex, String color, boolean simpleButton, JTextPane textField) {
        this.id = id;
        this.name = name;
        this.regex = regex;
        this.color = color;
        this.simpleButton = simpleButton;
        this.textField = textField;
    }

    /** Retourne le nom du bouton.
     * 
     * @return Nom du bouton
     */
    public String getName() {
        return name;
    }

    /** Retourne la couleur du bouton.
     * 
     * @return Couleur du bouton
     */
    public String getColor() {
        return color;
    }

    /** Retourne l'id du bouton.
     * 
     * @return Id du bouton
     */
    public String getId() {
        return id;
    }

    /** Retourne le champ de texte associé au bouton.
     * 
     * @return Champ de texte associé au bouton
     */
    public JTextPane getTextPane() {
        return textField;
    }

    /** Retourne la regex du bouton.
     * 
     * @return Regex du bouton
     */
    public String getRegex() {
        return regex;
    }

    /** Retourne si le bouton est simple ou complexe.
     * 
     * @return Bouton simple ou complexe
     */
    public boolean isSimpleButton() {
        return simpleButton;
    }

    /** Modifie le nom du bouton.
     * 
     * @param name Nouveau nom du bouton
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Modifie la couleur du bouton.
     * 
     * @param color Nouvelle couleur du bouton
     */
    public void setColor(String color) {
        this.color = color;
    }

    /** Modifie l'id du bouton.
     * 
     * @param id Nouvel id du bouton
     */
    public void setId(String id) {
        this.id = id;
    }

    /** Modifie le champ de texte associé au bouton.
     * 
     * @param textField Nouveau champ de texte associé au bouton
     */
    public void setTextField(JTextPane textField) {
        this.textField = textField;
    }

    /** Modifie la regex du bouton.
     * 
     * @param regex Nouvelle regex du bouton
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /** Modifie si le bouton est simple ou complexe.
     * 
     * @param simpleButton Bouton simple ou complexe
     */
    public void setSimpleButton(boolean simpleButton) {
        this.simpleButton = simpleButton;
    }

    /** Action du bouton.
     * 
     * @param e ActionEvent
     */
    abstract public void actionPerformed(ActionEvent e);
}
