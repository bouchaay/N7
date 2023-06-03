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
import frame.AskOrRegex;
import panels.ColorConverter;

/** Classe action pour les boutons de type ou.
 * prend en paramètre un block et un champ de texte.
 */
public class ButtonOr extends ButtonClickAbstract implements ActionListener {

    /** Block associé au bouton. */
    private BlockOr block;

    /** Constructeur.
     * 
     * @param block Block associé au bouton
     * @param textField Champ de texte associé au bouton
     */
    public ButtonOr(BlockOr block, JTextPane textField) {
        super(block.getId(), block.getName(), block.toRegexFragment(), block.getColor(), true, textField);
        this.block = block;
    }

    /** Retourne le block associé au bouton.
     * 
     * @return Block associé au bouton
     */
    public BlockOr getBlock() {
        return block;
    }

    /** Modifie le block associé au bouton.
     * 
     * @param block Nouveau block associé au bouton
     */
    public void setBlock(BlockOr block) {
        this.block = block;
    }

    /** Action du bouton.
     * 
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	AskOrRegex choixGroup = new AskOrRegex();
            // Une fois la feneêtre fermée, on récupère le choix
            choixGroup.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if ((choixGroup.getChoix().size() == 0)) {
                        return;
                    }
                    try {
                        BlockOr newBlock = new BlockOr("Id-14", false, choixGroup.getChoix());
                        String regexFragment = newBlock.toRegexFragment();
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
    public static BlockOr returnButtonType () {
        return new BlockOr("Id-14", false);
    }

}
