package panels;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/** Classe qui permet de créer le panel de l'expression régulière.
 * @author Ayoub Bouchama
 */
public class ExpressionPanel {

    /* Le panel de l'expression régulière.*/
    private JPanel panel;
    /* Le label de l'expression régulière. */
    private JLabel label;
    /* Le champ de l'expression régulière. */
    private JTextPane textPane;
    /* La dimension du panel. */
    private static Dimension panelDimension = new Dimension(1000, 75);

    /** Constructeur de la classe ExpressionPanel. */
    public ExpressionPanel() {
        this.panel = new JPanel(new GridLayout(1,1, 5, 5));
        panel.setBackground(Color.decode("#0B0B3B"));
        this.label = new JLabel("Expression régulière");
        label.setForeground(Color.WHITE);
        panel.add(label);

        this.textPane = new JTextPane();
        Border border = new LineBorder(Color.WHITE, 1);
        textPane.setBorder(border);
        textPane.setText("Entrer votre expression régulière ici..."); // Définir le texte initial du JTextPane
        textPane.setFont(new Font("Arial", Font.PLAIN, 14));
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(Color.decode("#4f577f"));
        textPane.addFocusListener( new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textPane.getText().equals("Entrer votre expression régulière ici...")) {
                    textPane.setText("");
                    textPane.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textPane.getText().isEmpty()) {
                    textPane.setText("Entrer votre expression régulière ici...");
                    textPane.setForeground(Color.WHITE);
                }
            }
        });
        
        // Créer un JScroolPane pour le JJTextPane de l'expression régulière
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(panelDimension);
        panel.add(scrollPane);
    }

    /** Méthode qui permet de récupérer le panel de l'expression régulière.
     * @return Le panel de l'expression régulière.
     */
    public JPanel getPanel() {
        return panel;
    }

    /** Méthode qui permet de récupérer le label de l'expression régulière.
     * @return Le label de l'expression régulière.
     */
    public JLabel getLabel() {
        return label;
    }

    /** Méthode qui permet de récupérer le champ de l'expression régulière.
     * @return Le champ de l'expression régulière.
     */
    public JTextPane getTextPane() {
        return textPane;
    }

    /** Méthode qui permet de récupérer la dimension du panel de l'expression régulière.
     * @return La dimension du panel de l'expression régulière.
     */
    public Dimension getPanelDimension() {
        return panelDimension;
    }

    /** Méthode qui permet de modifier le panel de l'expression régulière.
     * @param panel Le nouveau panel de l'expression régulière.
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /** Méthode qui permet de modifier le label de l'expression régulière.
     * @param label Le nouveau label de l'expression régulière.
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /** Méthode qui permet de modifier le champ de l'expression régulière.
     * @param textPane Le nouveau champ de l'expression régulière.
     */
    public void settextPane(JTextPane textPane) {
        this.textPane = textPane;
    }

    /** Méthode qui permet de modifier la dimension du panel de l'expression régulière.
     * @param panelDimension La nouvelle dimension du panel de l'expression régulière.
     */
    public void setPanelDimension(Dimension panelDimension) {
        ExpressionPanel.panelDimension = panelDimension;
    }
}
