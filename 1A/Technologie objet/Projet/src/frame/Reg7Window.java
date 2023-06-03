package frame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/** Classe de la fenetre d'accueil. */
public class Reg7Window extends JFrame {

    /** Constructeur de la fenêtre principale.
     * Contient le titre et un bouton pour lancer Reg7.
     * @throws Exception
     */
    public Reg7Window() throws Exception {
        super("Reg7");
        
     // Définir l'icon de la fenêtre
        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);

        JLabel title = new JLabel("Reg7", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 80));

        JButton buttonConstruction = new JButton("Constructeur");
        buttonConstruction.setForeground(Color.WHITE);
        // mettre le background du bouton en noir
        buttonConstruction.setBackground(Color.BLACK);
        Dimension dimensionEnterButton = new Dimension(400, 50);
        buttonConstruction.setSize(dimensionEnterButton);
        buttonConstruction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
					new Menu();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });

        JButton buttonAnalyse = new JButton("Analyseur");
        buttonAnalyse.setForeground(Color.WHITE);
        // mettre le background du bouton en noir
        buttonAnalyse.setBackground(Color.BLACK);
        buttonAnalyse.setSize(dimensionEnterButton);
        buttonAnalyse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new Analyzer();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());
        panelButtons.add(buttonConstruction);
        panelButtons.add(buttonAnalyse);

        JPanel panelGlobal = new JPanel();
        panelGlobal.setLayout(new BorderLayout());
        panelGlobal.add(title, BorderLayout.NORTH);
        panelGlobal.add(panelButtons, BorderLayout.CENTER);

        this.setContentPane(panelGlobal);

        pack();
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1000, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
