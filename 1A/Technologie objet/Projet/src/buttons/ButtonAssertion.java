package buttons;
import java.awt.event.*;
import javax.swing.*;
import construction.*;

/** Classe action pour les boutons de type assertion.
 * prend en paramètre un block, un champ de texte, un panel et une fenêtre.
 */
public class ButtonAssertion extends ButtonClickAbstract implements ActionListener {

    /** Block associé au bouton. */
    private BlockAssertion block;
    /** Panel associé au bouton. */
    private JPanel panel;
    /** Fenêtre associée au bouton. */
    private JFrame frame;

    /** Constructeur.
     * 
     * @param block Block associé au bouton
     * @param textField Champ de texte associé au bouton
     * @param panel Panel associé au bouton
     * @param frame Fenêtre associée au bouton
     */
    public ButtonAssertion(BlockAssertion block, JTextPane textField, JPanel panel, JFrame frame) {
        super(block.getId(), block.getName(), "", block.getColor(), false, textField);
        this.block = block;
        this.panel = panel;
        this.frame = frame;
    }

    /** Retourne le block associé au bouton.
     * 
     * @return Block associé au bouton
     */
    public BlockAssertion getBlock() {
        return block;
    }

    /** Modifie le block associé au bouton.
     * 
     * @param block Nouveau block associé au bouton
     */
    public void setBlock(BlockAssertion block) {
        this.block = block;
    }

    /** Retourne le panel associé au bouton.
     * 
     * @return Panel associé au bouton
     */
    public JPanel getPanel() {
        return panel;
    }

    /** Modifie le panel associé au bouton.
     * 
     * @param panel Nouveau panel associé au bouton
     */
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /** Retourne la fenêtre associée au bouton.
     * 
     * @return Fenêtre associée au bouton
     */
    public JFrame getFrame() {
        return frame;
    }

    /** Modifie la fenêtre associée au bouton.
     * 
     * @param frame Nouvelle fenêtre associée au bouton
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
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
