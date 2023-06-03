package frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import panels.ColorConverter;

/** Classe de la fenetre pour demander le choix de la plage de caractères. */
public class AskCharacterRange extends JFrame {

    /** Champ de texte de la première lettre. */
    private JTextPane textField1;
    /** Champ de texte de la deuxième lettre. */
    private JTextPane textField2;
    /** la première lettre. */
    private String choix1;
    /** la deuxième lettre. */
    private String choix2;
    /** Si le choix est terminé. */
    private boolean isOk;

    /** Constructeur de la fenêtre de choix de la plage de caractères.
     * @throws IOException
     */
    public AskCharacterRange() {

        super("Entrer votre choix");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.isOk = false;
        
     // Définir l'icon de la fenêtre
       try {
        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);
        
        JPanel panel = new JPanel();

        JLabel label = new JLabel("Votre choix");
        Dimension dimLabel = new Dimension(100, 24);
        label.setPreferredSize(dimLabel);

        this.textField1 = new JTextPane();
        textField1.setText("Début");
        textField1.setEditable(true);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));
        textField1.setPreferredSize(new Dimension(50, 50));
        textField1.setForeground(Color.BLACK);
        textField1.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Début")) {
                    textField1.setText("");
                    textField1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setText("Début");
                    textField1.setForeground(Color.BLACK);
                }
            }
        });

        this.textField2 = new JTextPane();
        textField2.setText("Fin");
        textField2.setEditable(true);
        textField2.setFont(new Font("Arial", Font.PLAIN, 14));
        textField2.setPreferredSize(new Dimension(50, 50));
        textField2.setForeground(Color.BLACK);
        textField2.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Fin")) {
                    textField2.setText("");
                    textField2.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setText("Fin");
                    textField2.setForeground(Color.BLACK);
                }
            }
        });

        JButton button = new JButton("Valider");
        ColorConverter colorConverter = new ColorConverter("green");
        Color color = colorConverter.convertirColor();
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        Dimension dimButton = new Dimension(100, 24);
        button.setPreferredSize(dimButton);
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isOk = true;
                choix1 = textField1.getText();
                choix2 = textField2.getText();
                dispose();
            }
        });

        panel.add(label);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(button);

        this.setContentPane(panel);
        pack();

        this.setVisible(true);
       } catch (Exception e2) {
			e2.printStackTrace();
       }
    }

    /** Retourne le champ de texte.
     * 
     * @return Champ de texte
     */
    public JTextPane getTextPane1() {
        return textField1;
    }

    /** Modifie le champ de texte.
     * 
     * @param textField Nouveau champ de texte
     */
    public void setTextPane1(JTextPane textField) {
        this.textField1 = textField;
    }

    /** Retourne le champ de texte.
     * 
     * @return Champ de texte
     */
    public JTextPane getJTextPane2() {
        return textField2;
    }

    /** Modifie le champ de texte.
     * 
     * @param textField Nouveau champ de texte
     */
    public void setJTextPane2(JTextPane textField) {
        this.textField2 = textField;
    }

    /** Retourne le choix.
     * 
     * @return Choix
     */
    public String getLettreInitiale() {
        return choix1;
    }

    /** Modifie le choix.
     * 
     * @param choix Nouveau choix
     */
    public void setLettreInitiale(String choix) {
        this.choix1 = choix;
    }

    /** Retourne le choix.
     * 
     * @return Choix
     */
    public String getLettreFinale() {
        return choix2;
    }

    /** Modifie le choix.
     * 
     * @param choix Nouveau choix
     */

    public void setLettreFinale(String choix) {
        this.choix2 = choix;
    }

    /** Retourne si le choix est valide.
     * 
     * @return Choix valide
     */
    public boolean isOk() {
        return isOk;
    }
}
