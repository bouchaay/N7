package frame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Classe de la fenêtre "À propos".
 * Affiche les informations sur le logiciel.
 */
public class AboutFrame extends JFrame {
    

	/** Constructeur de la fenêtre "À propos".
	 * Contient les informations sur le logiciel.
	 * @throws IOException
	 */
	public AboutFrame() throws IOException {
	    super("À propos");
	    setSize(400, 300);
	    setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Définir l'icon de la fenêtre
        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);
	    
	    JLabel titleLabel = new JLabel("Reg7");
	    titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
	    titleLabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    JLabel developersLabel = new JLabel("<html>Développé par : <br> Alexandre Trotel, Ayoub Bouchama, Clément Cognard, Clément Safer, Ewen Le Bihan, Florent Puy, Gauthier Rancoule, Ilyasse Alioui, Raphaël Giudice</html>");
	    developersLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
	    developersLabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    JLabel dateLabel = new JLabel("Dernière mise à jour : 17 avril 2023");
	    dateLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
	    dateLabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    JPanel mainPanel = new JPanel(new BorderLayout());
	    mainPanel.add(titleLabel, BorderLayout.NORTH);
	    mainPanel.add(developersLabel, BorderLayout.CENTER);
	    mainPanel.add(dateLabel, BorderLayout.SOUTH);
	    
	    getContentPane().add(mainPanel);
	    setVisible(true);
	}
}
