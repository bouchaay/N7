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
import frame.AskCharacterRange;
import panels.ColorConverter;

/** Classe action pour les boutons de type caractère dans une plage.
 * prend en paramètre un block et un champ de texte.
 */
public class ButtonCharacterRange extends ButtonClickAbstract implements ActionListener {
	
    /** Block associé au bouton. */
    private BlockCharacterRange block;

    /** Constructeur.
     * 
     * @param block Block associé au bouton
     * @param textField Champ de texte associé au bouton
     */
    public ButtonCharacterRange(BlockCharacterRange block, JTextPane textField) {
        super(block.getId(), block.getName(), block.toRegexFragment(), block.getColor(), false, textField);
        this.block = block;
    }

    /** Retourne le block associé au bouton.
     * 
     * @return Block associé au bouton
     */
    public BlockCharacterRange getBlock() {
        return block;
    }

    /** Modifie le block associé au bouton.
     * 
     * @param block Nouveau block associé au bouton
     */
    public void setBlock(BlockCharacterRange block) {
        this.block = block;
    }

    /** Action du bouton.
     * 
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AskCharacterRange choixGroup = new AskCharacterRange();
        // Une fois la feneêtre fermée, on récupère le choix
        choixGroup.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    if (choixGroup.getLettreInitiale() == null || choixGroup.getLettreFinale() == null || choixGroup.getLettreInitiale().equals("Début") || choixGroup.getLettreFinale().equals("Fin")) {
                        return;
                    }
                    String plageChoisie = choixGroup.getLettreInitiale() + "-" + choixGroup.getLettreFinale();
                    BlockCharacterRange newBlock = new BlockCharacterRange(block.getId(), false, plageChoisie);
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
    public static BlockCharacterRange returnButtonType() {
        return new BlockCharacterRange("Id-5", false, "[VOTRE_RANGE_DE_CARACTERES]");
    } 
}
