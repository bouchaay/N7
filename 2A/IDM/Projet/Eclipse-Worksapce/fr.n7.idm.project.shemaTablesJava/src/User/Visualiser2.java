package User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Visualiser2 extends JFrame {
    private JTextField phraseAField;
    private JTextField phraseBField;
    private String phraseA;
    private String phraseB;

    public Visualiser2() {
        // Configuration de la fenêtre
        setTitle("Interface avec deux cases A et B");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Labels et champs de texte pour les phrases A et B
        add(new JLabel("Phrase A:"));
        phraseAField = new JTextField();
        phraseAField.setColumns(10);  // Ajustez la largeur ici
        add(phraseAField);

        add(new JLabel("Phrase B:"));
        phraseBField = new JTextField();
        phraseBField.setColumns(10);  // Ajustez la largeur ici
        add(phraseBField);

        // Bouton Valider
        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les phrases saisies
                phraseA = phraseAField.getText();
                phraseB = phraseBField.getText();

                // Fermer la fenêtre après validation
                dispose();
            }
        });
        add(validerButton);

        // Afficher la fenêtre
        setVisible(true);
    }

    public String getPhraseA() {
        return phraseA;
    }

    public String getPhraseB() {
        return phraseB;
    }

    
}
