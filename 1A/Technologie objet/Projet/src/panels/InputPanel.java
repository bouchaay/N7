package panels;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/** Classe qui permet de créer le panel de l'expression à tester. */
public class InputPanel {
    
    /** Le panel de l'expression à tester. */
    private JPanel panel;
    /** Le label de l'expression à tester. */
    private JLabel label;
    /** Le champ de l'expression à tester. */
    private JTextPane textPane; // Remplacer JTextField par JTextPane
    /** La dimension du panel. */
    private static Dimension panelDimension = new Dimension(1000, 150);

    /** Constructeur de la classe InputPanel. */
    public InputPanel() {
        this.panel = new JPanel(new GridLayout(1,1, 5, 5));
        panel.setBackground(Color.decode("#0B0B3B"));
        //TODO : A décommenter une fois que le bouton supprimera le texte du JTextPane
        this.label = new JLabel("String pour le test");
        label.setForeground(Color.WHITE);
        panel.add(label);
        this.textPane = new JTextPane(); // Remplacer JTextField par JTextPane
        Border border = new LineBorder(Color.WHITE, 1);
        // Attribution de la bordure au JTextPane
        textPane.setBorder(border);
        textPane.setText("Entrez l'expression de votre choix pour le test..."); // Définir le texte initial du JTextPane
        textPane.setFont(new Font("Arial", Font.PLAIN, 14));
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(Color.decode("#4f577f"));
        textPane.addFocusListener( new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textPane.getText().equals("Entrez l'expression de votre choix pour le test...")) {
                    textPane.setText("");
                    textPane.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textPane.getText().isEmpty()) {
                    textPane.setText("Entrez l'expression de votre choix pour le test...");
                    textPane.setForeground(Color.WHITE);
                }
            }
        });
        
        // Créer un JScroolPane pour le JJTextPane des tests
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(panelDimension);
        panel.add(scrollPane);
    }

    /** Méthode qui permet de récupérer le panel de l'expression à tester.
     * @return Le panel de l'expression à tester.
     */
    public JPanel getPanel() {
        return panel;
    }

    /** Méthode qui permet de récupérer le label de l'expression à tester.
     * @return Le label de l'expression à tester.
     */
    public JLabel getLabel() {
        return label;
    }

    /** Méthode qui permet de récupérer le champ de l'expression à tester.
     * @return Le champ de l'expression à tester.
     */
    public JTextPane getTextPane() { // Remplacer JTextField par JTextPane
        return textPane;
    }

    /** Méthode qui permet de récupérer la dimension du panel de l'expression à tester.
     * @return La dimension du panel de l'expression à tester.
     */
    public Dimension getPanelDimension() {
        return panelDimension;
    }

    /** Méthode qui permet de modifier le panel de l'expression à tester.
     * @param panel Le nouveau panel de l'expression à tester.
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /** Méthode qui permet de modifier le label de l'expression à tester.
     * @param label Le nouveau label de l'expression à tester.
     */
    public void setLabel(JLabel label) {
        this.label = label;
    }

    /** Méthode qui permet de modifier le champ de l'expression à tester.
     * @param textPane Le nouveau champ de l'expression à tester.
     */
    public void setTextPane(JTextPane textPane) { // Remplacer JTextField par JTextPane
        this.textPane = textPane;
    }

    /** Méthode qui permet de modifier la dimension du panel de l'expression à tester.
     * @param panelDimension La nouvelle dimension du panel de l'expression à tester.
     */
    public void setPanelDimension(Dimension panelDimension) {
        InputPanel.panelDimension = panelDimension;
    }
}
