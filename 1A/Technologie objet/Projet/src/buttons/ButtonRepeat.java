package buttons;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import construction.*;
import frame.AskRepetition;
import panels.ColorConverter;

/** Classe action pour les boutons de type répétition.
 * prend en paramètre un block et un champ de texte.
 */
public class ButtonRepeat extends ButtonClickAbstract implements ActionListener {

    /** Block associé au bouton. */
    private BlockRepeat block;

    /** Constructeur.
     * 
     * @param block Block associé au bouton
     * @param textField Champ de texte associé au bouton
     */
    public ButtonRepeat(BlockRepeat block, JTextPane textField) {
        super(block.getId(), block.getName(), block.toRegexFragment(), block.getColor(), false, textField);
        this.block = block;
    }

    /** Retourne le block associé au bouton.
     * 
     * @return Block associé au bouton
     */
    public BlockRepeat getBlock() {
        return block;
    }

    /** Modifie le block associé au bouton.
     * 
     * @param block Nouveau block associé au bouton
     */
    public void setBlock(BlockRepeat block) {
        this.block = block;
    }

    /** Action du bouton.
     * 
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AskRepetition choixGroup = new AskRepetition();
        // Une fois la feneêtre fermée, on récupère le choix
        choixGroup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    if (choixGroup.getMin() == null || choixGroup.getMax() == null || choixGroup.getMin().equals("Min") || choixGroup.getMax().equals("Max")) {
                        return;
                    }
                    String regexFragment = "{" + choixGroup.getMin() + "," + choixGroup.getMax() + "}";
                    ColorConverter colorConverter = new ColorConverter(block.getColor());
                    Color colorBlock = colorConverter.convertirColor();
                    StyleContext styleContext = StyleContext.getDefaultStyleContext();
                    AttributeSet attributes = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, colorBlock);
                    int offset = getTextPane().getCaretPosition();
                    getTextPane().getStyledDocument().insertString(offset, regexFragment, attributes);
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    /** Retourne le block associé au bouton.
     * 
     * @return Block associé au bouton
     */
    public static BlockRepeat returnButtonType () {
        return new BlockRepeat("Id-16", false, 0, 0, false);
    }
}
