package buttons;
import java.awt.event.*;
import javax.swing.*;
import construction.*;

/** Classe action pour les boutons de type entouré.
 * prend en paramètre un block et un champ de texte.
 */
public class ButtonSurrounded extends ButtonClickAbstract implements ActionListener {

    /** Block associé au bouton. */
    private BlockSurrounded block;

    /** Constructeur.
     * 
     * @param block Block associé au bouton
     * @param textField Champ de texte associé au bouton
     */
    public ButtonSurrounded(BlockSurrounded block, JTextPane textField) {
        super(block.getId(), block.getName(), "", block.getColor(), false, textField);
        this.block = block;
    }

    /** Retourne le block associé au bouton.
     * 
     * @return Block associé au bouton
     */
    public BlockSurrounded getBlock() {
        return block;
    }

    /** Modifie le block associé au bouton.
     * 
     * @param block Nouveau block associé au bouton
     */
    public void setBlock(BlockSurrounded block) {
        this.block = block;
    }

    /** Action du bouton.
     * 
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO
    }
}
