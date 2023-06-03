package frame;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import panels.*;

/** Classe du menu des blocs. */
public class Menu extends JFrame {
	
    /** Constructeur.
     * Création de la fenêtre et ajout des différents panneaux.
     * @throws IOException
     */
    public Menu() throws IOException {
        super("Reg7 - Constructeur");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#0B0B3B"));
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setResizable(true);

        // Définir l'icon de la fenêtre
        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);
        
        ExpressionPanel panelHaut = new ExpressionPanel();
        InputPanel panelEntree = new InputPanel();

        // Ajouter la barre de menu
        new MenuBarMain(this, panelHaut.getTextPane(), panelEntree.getTextPane());
        
        // Ajouter le panel onglets
        // new PanelOnglets(this);

        // Panel du haut
        add(panelHaut.getPanel(), BorderLayout.NORTH);

        // Panel du milieu
        JPanel panelMilieu = new JPanel(new GridBagLayout()); // Utilisation d'un GridBagLayout pour afficher les panneaux avec des contraintes de taille
        RegexSearchPanel panelMilieuHaut = new RegexSearchPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panelMilieu.add(panelEntree.getPanel(), gbc);
        gbc.gridy = 1;
        panelMilieu.add(panelMilieuHaut.getPanel(), gbc);
        add(panelMilieu, BorderLayout.CENTER);

        // Panel du bas
        ButtonsPanel panelBas = new ButtonsPanel(panelHaut.getTextPane(), panelEntree.getTextPane());
        add(panelBas.getPanel(), BorderLayout.SOUTH);

        pack(); // Appel à la méthode pack() pour redimensionner et afficher correctement les composants
        setVisible(true);
    }
}
