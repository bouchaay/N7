package analyse;
import panels.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.*;

/** La classe ColorMatched permet de colorer les mots qui correspondent à ce qui a été matché par l'expression régulière.
 * Elle prend en paramètre un JTextPane et une liste de Tuple (x = début du motif, y = fin du motif).
 * La couleur est définie dans la variable color.
 */
public class ColorMatched {

    /** Le JTextPane dans lequel on va colorer les mots qui correspondent à l'expression régulière. */
    JTextPane textFieldPane;
    /** La couleur. */
    private static String color = "green";

    /** Constructeur.
     * @param textFieldPane le JTextPane dans lequel on va colorer les mots qui correspondent à l'expression régulière.
     */
    public ColorMatched(JTextPane textFieldPane) {
        this.textFieldPane = textFieldPane;
    }

    /** Colorie les mots qui correspondent à l'expression régulière.
        * @param matches liste de Tuple (x = début du motif, y = fin du motif).
        * la couleur est définie dans la variable color.
        */
    public void colorMatchedExpressions(ArrayList<Tuple> matches) {
        StyledDocument doc = textFieldPane.getStyledDocument();
        
        // Réinitialiser les attributs de style
        doc.setCharacterAttributes(0, doc.getLength(), doc.getStyle(StyleContext.DEFAULT_STYLE), true);
        
        Style style = textFieldPane.addStyle("expressionCouleur", null);
        ColorConverter cc = new ColorConverter(color);
        Color expressionCouleur = cc.convertirColor();
        StyleConstants.setBackground(style, expressionCouleur);
        
        for (Tuple match : matches) {
            int debut = match.x;
            int fin = match.y;
            doc.setCharacterAttributes(debut, fin - debut, style, false);
        }
    }
}
