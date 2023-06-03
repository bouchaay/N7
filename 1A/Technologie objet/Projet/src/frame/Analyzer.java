package frame;
import panels.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Fenêtre principale de l'analyseur d'expression régulière. */
public class Analyzer extends JFrame {

    /** Constructeur. 
     * @throws IOException
     */
    public Analyzer() throws IOException {
        super("Reg7 - Analyseur");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        

        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);

        AnalyzerPanel analyzerPanel = new AnalyzerPanel();
        new AnalyzerMenuBar(this, analyzerPanel.getTextFieldExpression());
        this.setContentPane(analyzerPanel.getPanExpression());
        pack();
        this.setVisible(true);
    }
}

