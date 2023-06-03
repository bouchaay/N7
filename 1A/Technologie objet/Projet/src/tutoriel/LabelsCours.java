package tutoriel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/** Classe pour créer les labels des cours.
 * Permet de créer des labels avec des polices différentes.
 * Crée des textArea pour les titres, les sous-titres et les textes.
 */
public class LabelsCours {

    /** Le texte du label. */
    private String label;

    /** Constructeur.
     * @param label Le texte du label.
     */
    public LabelsCours(String label) {
        this.label = label;
    }

    /** Retourne le texte du label.
     * @return Le texte du label.
     */
    public String getLabel() {
        return label;
    }

    /** Modifie le texte du label.
     * @param label Le nouveau texte du label.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /** Retourne le label sous forme de textArea pour le contenu.
     * @return Le label sous forme de textArea.
     */
    public JTextArea returnTextLabel() {
        JTextArea textArea = new JTextArea(label);
        textArea.setFont(new Font("Roboto", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(200, 0));
        textArea.setForeground(Color.black);
        textArea.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        textArea.setEditable(false);
        return textArea;
    }

    /** Retourne le label sous forme de textArea pour les titres.
     * @return Le label sous forme de textArea.
     */
    public JTextArea returnTitleLabel() {
        JTextArea textArea = new JTextArea(label);
        textArea.setFont(new Font("Vectis", Font.BOLD, 25));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.black);
        textArea.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.black));
        return textArea;
    }

    /** Retourne le label sous forme de textArea pour les sous-titres.
     * @return Le label sous forme de textArea.
     */
    public JTextArea returnSousTitleLabel() {
        JTextArea textArea = new JTextArea(label);
        textArea.setFont(new Font("Vectis", Font.BOLD, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.black);
        textArea.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        textArea.setEditable(false);
        return textArea;
    }

    /** Retourne le label sous forme de textArea pour les tableaux.
     * @return Le label sous forme de textArea.
     */
    public JTextArea returnTab() {
        JTextArea textArea = new JTextArea(label);
        textArea.setFont(new Font("Roboto", Font.BOLD, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.black);
        textArea.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createLineBorder(Color.black));
        return textArea;
    }
}
