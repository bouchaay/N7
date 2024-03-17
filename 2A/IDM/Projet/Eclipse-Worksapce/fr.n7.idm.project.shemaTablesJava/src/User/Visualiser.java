package User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Visualiser extends JFrame  {

    private JComboBox<String> xComboBox;
    private JComboBox<String> yComboBox;
    private String[] choixSelectionnes;
    private String x;
    private String y;
    private Boolean choixFait = false;

    public Visualiser(String[] choixSelectionnes) {
        // Configuration de la fenêtre
        JFrame frame = new JFrame();
        setTitle("Choix des coordonnées X et Y");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création du panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Labels pour les combobox
        JLabel xLabel = new JLabel("X:");
        JLabel yLabel = new JLabel("Y:");

        // ComboBox pour les choix X et Y
        xComboBox = new JComboBox<>(choixSelectionnes);
        yComboBox = new JComboBox<>(choixSelectionnes);

        // Ajout des composants au panel
        panel.add(xLabel);
        panel.add(xComboBox);
        panel.add(yLabel);
        panel.add(yComboBox);

        // Bouton pour afficher le choix
        JButton showChoiceButton = new JButton("Visualiser le graphe");
        showChoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherChoix();
            }
        });
        panel.add(showChoiceButton);

        // Ajout du panel à la fenêtre
        add(panel);
        setVisible(true);
    }

    private void afficherChoix() {
        String choixX = getChoixX();
        String choixY = getChoixY();

        if (choixX != null && choixY != null) {
            if (choixX.equals(choixY)) {
                JOptionPane.showMessageDialog(this, "Les choix X et Y ne peuvent pas être les mêmes.");
                this.choixFait = false;
            } else {
                // Récupérer les choix X et Y après le clic sur le bouton
                this.x = choixX;
                this.y = choixY;
                this.choixFait = true;
                // Afficher le graphe ou faire d'autres actions avec X et Y ici
                JOptionPane.showMessageDialog(this, "Choix X : " + this.x + "\nChoix Y : " + this.y);
                // Fermer la fenêtre après affichage du choix
                dispose();
            }
        }
    }

    public Boolean getChoixFait() {
        return this.choixFait;
    }

    public String getChoixX() {
        return (String) xComboBox.getSelectedItem();
    }

    public String getChoixY() {
        return (String) yComboBox.getSelectedItem();
    }

    public String getXEx() {
        return this.x;
    }

    public String getYEx() {
        return this.y;
    }

	
}