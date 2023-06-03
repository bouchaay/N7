package panels;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/** Classe qui permet de créer le panel de la recherche d'expression régulière.
 * @author Ayoub Bouchama
 */
public class RegexSearchPanel {

    /** Le panel de la recherche d'expression régulière.*/
    private JPanel panel;
    /** Le label de la recherche d'expression régulière. */
    private JLabel label;
    /** Le champ de la recherche d'expression régulière. */
    private JTextField textField;
    /** La dimension du panel. */
    private static Dimension panelDimension = new Dimension(1000, 25);

    /** Constructeur de la classe RegexSearchPanel. */
    public RegexSearchPanel() {
        this.panel = new JPanel(new GridLayout(1,1, 5, 5));
        panel.setBackground(Color.decode("#0B0B3B"));
        this.label = new JLabel("Recherche d'expression régulière");
        label.setForeground(Color.WHITE);
        panel.add(label);
        this.textField = new JTextField("Entrez le nom de l'expression régulière...");
        Border border = new LineBorder(Color.WHITE, 1);
        textField.setBorder(border);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.WHITE);
        textField.setBackground(Color.decode("#4f577f"));
        textField.addFocusListener( new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Entrez le nom de l'expression régulière...")) {
                    textField.setText("");
                    textField.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Entrez le nom de l'expression régulière...");
                    textField.setForeground(Color.WHITE);
                }
            }
        });
        panel.add(textField);
        panel.setPreferredSize(panelDimension);
    }

    /** Méthode qui permet de récupérer le panel de la recherche d'expression régulière.
     * @return Le panel de la recherche d'expression régulière.
     */
    public JPanel getPanel() {
        return panel;
    }

    /** Méthode qui permet de récupérer le label de la recherche d'expression régulière.
     * @return Le label de la recherche d'expression régulière.
     */
    public JLabel getLabel() {
        return label;
    }

    /** Méthode qui permet de récupérer le champ de la recherche d'expression régulière.
     * @return Le champ de la recherche d'expression régulière.
     */
    public JTextField getTextField() {
        return textField;
    }

    /** Méthode qui permet de récupérer la dimension du panel de la recherche d'expression régulière.
     * @return La dimension du panel de la recherche d'expression régulière.
     */
    public Dimension getPanelDimension() {
        return panelDimension;
    }

    /** Méthode qui permet de modifier le panel de la recherche d'expression régulière.
     * @param panel Le nouveau panel de la recherche d'expression régulière.
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /** Méthode qui permet de modifier le label de la recherche d'expression régulière.
     * @param label Le nouveau label de la recherche d'expression régulière.
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /** Méthode qui permet de modifier le champ de la recherche d'expression régulière.
     * @param textField Le nouveau champ de la recherche d'expression régulière.
     */
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    /** Méthode qui permet de modifier la dimension du panel de la recherche d'expression régulière.
     * @param panelDimension La nouvelle dimension du panel de la recherche d'expression régulière.
     */
    public void setPanelDimension(Dimension panelDimension) {
        RegexSearchPanel.panelDimension = panelDimension;
    }
}
