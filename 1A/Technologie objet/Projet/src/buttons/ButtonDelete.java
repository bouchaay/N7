package buttons;
import java.awt.event.*;
import javax.swing.*;

/** Classe action pour les boutons de type effacer.
 * prend en paramètre un champ de texte.
 */
public class ButtonDelete implements ActionListener {
    
    /** Champ de texte associé au bouton. */
    private JTextPane textField;
    /** Nom du bouton. */
    private String name;
    /** Id du bouton. */
    private String id;
    /** Couleur du bouton. */
    private String color;

    /** Constructeur.
     * 
     * @param textField Champ de texte associé au bouton
     */
    public ButtonDelete(JTextPane textField) {
        this.textField = textField;
        this.name = "Effacer";
        this.id = "Id-0";
        this.color = "blue";
    }

    /** Retourne le nom du bouton.
     * 
     * @return Nom du bouton
     */
    public String getName() {
        return name;
    }

    /** Retourne l'id du bouton.
     * 
     * @return Id du bouton
     */
    public String getId() {
        return id;
    }

    /** Retourne la couleur du bouton.
     * 
     * @return Couleur du bouton
     */
    public String getColor() {
        return color;
    }

    /** Action du bouton.
     * 
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText("");
    }
}
