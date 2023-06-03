package panels;
import java.awt.*;
import javax.swing.*;
import buttons.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Classe panel pour les boutons.
 * prend en paramètre 2 champs de texte.
 * prend en paramètre un panel.
 * prend en paramètre une liste de boutons.
 * prend en paramètre une liste de boutons simples.
 */
public class ButtonsPanel {

    /** Nombre de boutons par rangée. */
    private static final int NB_BOUTONS_PAR_RANGEE = 4;
    /** Nombre de rangées. */
    private static final int NB_RANGEE = 4;
    /** la zone de text des expressions régulières. */
    private JTextPane textPane;
    /** Champ de texte du string à tester. */
    private JTextPane textFieldPane;
    /** Panel des boutons. */
    private JPanel panelBoutons;
    /** Liste des boutons. */
    private List<ButtonClickAbstract> buttonsClick;
    /** Liste des boutons simples. */
    private List<ButtonClickAbstract> buttonsClickSimple;

    /** Constructeur.
     * 
     * @param textPane la zone de text des expressions régulières
     * @param textFieldPane Champ de texte du string à tester
     */
    public ButtonsPanel(JTextPane textPane, JTextPane textFieldPane) {
        this.textPane = textPane;
        this.textFieldPane = textFieldPane;
        this.panelBoutons = new JPanel(new GridLayout(NB_RANGEE, NB_BOUTONS_PAR_RANGEE, 5, 5));
        this.buttonsClick = new ArrayList<>();
        this.buttonsClickSimple = new ArrayList<>();
        // les boutons du panel
        final ButtonDelete button_Id_0 = new ButtonDelete(this.textPane);
        final ButtonAlphanumeriqueOrUnderscore button_Id_1 = new ButtonAlphanumeriqueOrUnderscore(ButtonAlphanumeriqueOrUnderscore.returnButtonType(), this.textPane);
        final ButtonAnyCharacter button_Id_2 = new ButtonAnyCharacter(ButtonAnyCharacter.returnButtonType(), this.textPane);
        final ButtonCaptureGroup button_Id_3 = new ButtonCaptureGroup(ButtonCaptureGroup.returnButtonType(), this.textPane);
        final ButtonCaptureGroupReference button_Id_4 = new ButtonCaptureGroupReference(ButtonCaptureGroupReference.returnButtonType(), this.textPane);
        final ButtonCharacterRange button_Id_5 = new ButtonCharacterRange(ButtonCharacterRange.returnButtonType(), this.textPane);
        final ButtonDigit button_Id_6 = new ButtonDigit(ButtonDigit.returnButtonType(), this.textPane);
        final ButtonLine button_Id_7 = new ButtonLine(ButtonLine.returnButtonType(), this.textPane);
        final ButtonLiteral button_Id_8 = new ButtonLiteral(ButtonLiteral.returnButtonType(), this.textPane);
        final ButtonLookAhead button_Id_9 = new ButtonLookAhead(ButtonLookAhead.returnButtonType(), this.textPane);
        final ButtonLookBehind button_Id_10 = new ButtonLookBehind(ButtonLookBehind.returnButtonType(), this.textPane);
        final ButtonNewLine button_Id_11 = new ButtonNewLine(ButtonNewLine.returnButtonType(), this.textPane);
        final ButtonOneOrMore button_Id_12 = new ButtonOneOrMore(ButtonOneOrMore.returnButtonType(), this.textPane);
        final ButtonOptional button_Id_13 = new ButtonOptional(ButtonOptional.returnButtonType(), this.textPane);
        final ButtonOr button_Id_14 = new ButtonOr(ButtonOr.returnButtonType(), this.textPane);
        final ButtonRawRegex button_Id_15 = new ButtonRawRegex(ButtonRawRegex.returnButtonType(), this.textPane);
        final ButtonRepeat button_Id_16 = new ButtonRepeat(ButtonRepeat.returnButtonType(), this.textPane);
        final ButtonRepeatAtLeast button_Id_17 = new ButtonRepeatAtLeast(ButtonRepeatAtLeast.returnButtonType(), this.textPane);
        final ButtonRepeatAtMost button_Id_18 = new ButtonRepeatAtMost(ButtonRepeatAtMost.returnButtonType(), this.textPane);
        final ButtonRepeatExactly button_Id_19 = new ButtonRepeatExactly(ButtonRepeatExactly.returnButtonType(), this.textPane);
        final ButtonUnicodeClass button_Id_20 = new ButtonUnicodeClass(ButtonUnicodeClass.returnButtonType(), this.textPane);
        final ButtonWord button_Id_21 = new ButtonWord(ButtonWord.returnButtonType(), this.textPane);
        final ButtonZeroOrMore button_Id_22 = new ButtonZeroOrMore(ButtonZeroOrMore.returnButtonType(), this.textPane);
        final ButtonMatch button_Id_23 = new ButtonMatch(this.textPane, this.textFieldPane);
        // Ajouter les boutons a la liste
        buttonsClick.add(button_Id_1);
        buttonsClick.add(button_Id_2);
        buttonsClick.add(button_Id_3);
        buttonsClick.add(button_Id_4);
        buttonsClick.add(button_Id_5);
        buttonsClick.add(button_Id_6);
        buttonsClick.add(button_Id_7);
        buttonsClick.add(button_Id_8);
        buttonsClick.add(button_Id_9);
        buttonsClick.add(button_Id_10);
        buttonsClick.add(button_Id_11);
        buttonsClick.add(button_Id_12);
        buttonsClick.add(button_Id_13);
        buttonsClick.add(button_Id_14);
        buttonsClick.add(button_Id_15);
        buttonsClick.add(button_Id_16);
        buttonsClick.add(button_Id_17);
        buttonsClick.add(button_Id_18);
        buttonsClick.add(button_Id_19);
        buttonsClick.add(button_Id_20);
        buttonsClick.add(button_Id_21);
        buttonsClick.add(button_Id_22);
        
        // Ajouter les boutons simples au tableau
        buttonsClickSimple.add(button_Id_1);
        buttonsClickSimple.add(button_Id_2);
        buttonsClickSimple.add(button_Id_3);
        buttonsClickSimple.add(button_Id_4);
        buttonsClickSimple.add(button_Id_6);
        buttonsClickSimple.add(button_Id_7);
        buttonsClickSimple.add(button_Id_8);
        buttonsClickSimple.add(button_Id_9);
        buttonsClickSimple.add(button_Id_10);
        buttonsClickSimple.add(button_Id_11);
        buttonsClickSimple.add(button_Id_12);
        buttonsClickSimple.add(button_Id_13);
        buttonsClickSimple.add(button_Id_14);
        buttonsClickSimple.add(button_Id_15);
        buttonsClickSimple.add(button_Id_19);
        buttonsClickSimple.add(button_Id_20);
        buttonsClickSimple.add(button_Id_21);
        buttonsClickSimple.add(button_Id_22);
        // Ajouter les boutons simples au panel
        // Parcourir les boutons de la liste non-simples et les ajouter au panel
        for (final ButtonClickAbstract buttonClick : buttonsClick) {
            JButton bouton = new JButton(buttonClick.getName());
            ColorConverter buttonColor = new ColorConverter(buttonClick.getColor());
            bouton.setBackground(buttonColor.convertirColor());
            Dimension dimension21 = new Dimension(200, 100);
            bouton.setPreferredSize(dimension21);
            panelBoutons.add(bouton);

            bouton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	if (textPane.getText().equals("Entrer votre expression régulière ici...")) {
                		textPane.setText("");
                	}
                    buttonClick.actionPerformed(e);
                }
            });
        }

        // Ajoute le bouton Effacer
        JButton boutonEffacer = new JButton(button_Id_0.getName());
        ColorConverter buttonColorEffacer = new ColorConverter(button_Id_0.getColor());
        boutonEffacer.setBackground(buttonColorEffacer.convertirColor());
        Dimension dimensionEffacer = new Dimension(200, 100);
        boutonEffacer.setPreferredSize(dimensionEffacer);
        panelBoutons.add(boutonEffacer);

        boutonEffacer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button_Id_0.actionPerformed(e);
            }
        });

        // Ajoute le bouton Match
        JButton boutonMatch = new JButton(button_Id_23.getName());
        ColorConverter buttonColorMatch = new ColorConverter(button_Id_23.getColor());
        boutonMatch.setBackground(buttonColorMatch.convertirColor());
        Dimension dimensionMatch = new Dimension(200, 100);
        boutonMatch.setPreferredSize(dimensionMatch);
        panelBoutons.add(boutonMatch);

        boutonMatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button_Id_23.actionPerformed(e);
            }
        });
    }

    /** Retourne le panelBoutons. 
     * @return le panelBoutons
     */
    public JPanel getPanel() {
        return panelBoutons;
    }

    /** Modifie le panelBoutons. 
     * @param panel le panelBoutons a modifier
     */
    public void setPanel(JPanel panel) {
        this.panelBoutons = panel;
    }

    /** Retourne la liste des boutons.
     * @return la liste des boutons
     */
    public List<ButtonClickAbstract> getButtonsClick() {
        return buttonsClick;
    }

    /** Modifie la liste des boutons.
     * @param buttonsClick la liste des boutons a modifier
     */
    public void setButtonsClick(ArrayList<ButtonClickAbstract> buttonsClick) {
        this.buttonsClick = buttonsClick;
    }

    /** Retourne la liste des boutons simples.
     * @return la liste des boutons simples
     */
    public List<ButtonClickAbstract> getButtonsClickSimple() {
        return buttonsClickSimple;
    }

    /** Modifie la liste des boutons simples.
     * @param buttonsClickSimple la liste des boutons simples a modifier
     */
    public void setButtonsClickSimple(ArrayList<ButtonClickAbstract> buttonsClickSimple) {
        this.buttonsClickSimple = buttonsClickSimple;
    }

    /** Retourne le pane des expressions.
     * @return le pane des expressions
     */
    public JTextPane getTextField() {
        return textPane;
    }

    /** Modifie le pane des expressions.
     * @param textField le pane des expressions a modifier
     */
    public void setTextField(JTextPane textField) {
        this.textPane = textField;
    }

    /** Retourne le pane des strings à tester.
     * @return le pane des strings à tester
     */
    public JTextPane getTextFieldPane() {
        return textFieldPane;
    }

    /** Modifie le pane des strings à tester.
     * @param textFieldPane le pane des strings à tester a modifier
     */
    public void setTextFieldPane(JTextPane textFieldPane) {
        this.textFieldPane = textFieldPane;
    }
}
