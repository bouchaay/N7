package buttons;
import java.awt.event.*;
import java.util.*;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import analyse.*;

/** Classe action pour les boutons de type match.
 * prend en paramètre un champ de texte et un panel.
 */
public class ButtonMatch {
	
    /** Champ de texte associé à l'expression régulière. */
	private JTextPane textFieldExpression;
    /** Champ de texte associé au texte à analyser. */
    private JTextPane textFieldPane;
    /** Nom du bouton. */
    private String name;
    /** Id du bouton. */
    private String id;
    /** Couleur du bouton. */
    private String color;

    /** Constructeur.
     * 
     * @param textFieldExpression Champ de texte associé à l'expression régulière
     * @param textFieldPane Champ de texte associé au texte à analyser
     */
    public ButtonMatch(JTextPane textFieldExpression, JTextPane textFieldPane) {
        this.textFieldExpression = textFieldExpression;
        this.textFieldPane = textFieldPane;
        this.name = "Matcher";
        this.id = "Id-23";
        this.color = "green";
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
    public void actionPerformed(ActionEvent e) {
        try {
            findMatch fm = new findMatch(textFieldExpression.getText(), textFieldPane.getText());
            ColorMatched cm = new ColorMatched(textFieldPane);
            cm.colorMatchedExpressions(fm.getMatches());
        
            // Créer une nouvelle fenetre pour afficher les résultats du match
            JFrame frame = new JFrame("Résultats du match");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            JTextArea textField = new JTextArea("");
            textField.setEditable(false);
            ArrayList<Tuple> matches = fm.getMatches();
            for (int i = 0; i < matches.size(); i++) {
                // l'élément matché en string
                Tuple match = matches.get(i);
                String matched = textFieldPane.getText().substring(match.getX(), match.getY());
                // Afficher les indices de début et de fin du match
                textField.setText(textField.getText() + "Match " + (i + 1) + " : " + matches.get(i).getX() + "-" + matches.get(i).getY() + " => " + matched + "\n");
            }
            frame.add(textField);
            frame.setVisible(true);
        } catch (PatternSyntaxException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de syntaxe dans l'expression régulière", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
