package panels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import analyse.AnalyseRegex;
import exception.ChampRegexVide;
import tutoriel.BlocksHashMap;

/** Panel pour l'analyseur d'expression régulière. */
public class AnalyzerPanel {
	
    /** Zone de texte pour l'expression régulière. */
	private JTextPane textFieldExpression;

    /** Panel pour l'expression régulière. */
    private JPanel panExpression;

    /** Constructeur. */
    public AnalyzerPanel() {
    	this.textFieldExpression = new JTextPane();
    	textFieldExpression.setText("Entrer votre expression régulière ici...");
        this.panExpression = new JPanel();

        JLabel label = new JLabel("Expression régulière :");
        Dimension dimLabel = new Dimension(150, 24);
        label.setPreferredSize(dimLabel);

        Dimension dim = new Dimension(400, 50);
        textFieldExpression.setPreferredSize(dim);
        textFieldExpression.setFont(new Font("Arial", Font.PLAIN, 14));
        textFieldExpression.addFocusListener( new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textFieldExpression.getText().equals("Entrer votre expression régulière ici...")) {
                    textFieldExpression.setText("");
                    textFieldExpression.setForeground(Color.GRAY);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textFieldExpression.getText().isEmpty()) {
                    textFieldExpression.setText("Entrer votre expression régulière ici...");
                    textFieldExpression.setForeground(Color.GRAY);
                }
            }
        });
        
        // Créer un JScroolPane pour le JJTextPane de l'expression régulière
        JScrollPane scrollPane = new JScrollPane(textFieldExpression);
        Dimension scrollDimension = new Dimension(400, 50);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(scrollDimension);

        JButton buttonAnalyse = new JButton("Analyser");
        ColorConverter colorConverter = new ColorConverter("green");
        Color color = colorConverter.convertirColor();
        buttonAnalyse.setBackground(color);
        buttonAnalyse.setForeground(Color.WHITE);
        Dimension dimButton = new Dimension(100, 24);
        buttonAnalyse.setPreferredSize(dimButton);
        buttonAnalyse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String regex = textFieldExpression.getText();
                try {
                    if (regex.equals("Entrer votre expression régulière ici...")) {
                        throw new ChampRegexVide();
                    }
                    boolean valide = AnalyseRegex.regexValide(regex);
                    textFieldExpression.setText("");

                    // Colorer l'expression régulière après l'analyse
                    AnalyseRegex.colorerRegex(regex, textFieldExpression);

                    // Afficher le résultat de l'analyse dans une nouvelle fenêtre
                    JFrame frame = new JFrame();
                    frame.setTitle("Explication de l'expression régulière");
                    frame.setSize(500, 500);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JTextArea textField = new JTextArea(" ");
                    textField.setEditable(false);
                    HashMap<String, String> dictionnaire = BlocksHashMap.dictionnaireRegex();
                    for (String composante : AnalyseRegex.getComposantes(regex)) {
                        if (dictionnaire.containsKey(composante)) {
                            textField.setText(textField.getText() + composante + " : " + dictionnaire.get(composante) + "\n ");
                        } else {
                            textField.setText(textField.getText() + composante + " : " + "Non reconnu" + "\n ");
                        }
                    }
                    frame.add(textField);
                    frame.setVisible(true);

                } catch (PatternSyntaxException e) {
                    // afficher le message dans une boite de dialogue
                    JOptionPane.showMessageDialog(null, "L'expression régulière n'est pas valide", "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                } catch (ChampRegexVide e) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer une expression régulière", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panExpression.add(label);
        panExpression.add(scrollPane);
        panExpression.add(buttonAnalyse);
    }

    /** Retourner le panel pour l'expression régulière.
     * @return le panel pour l'expression régulière.
     */
    public JPanel getPanExpression() {
        return panExpression;
    }

    /** Retourner la zone de texte pour l'expression régulière.
     * @return la zone de texte pour l'expression régulière.
     */
    public JTextPane getTextFieldExpression() {
        return textFieldExpression;
    }
}
