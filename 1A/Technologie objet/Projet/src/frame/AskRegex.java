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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import panels.ColorConverter;

/** Classe de la fenetre pour demander le choix des groupes. */
public class AskRegex extends JFrame {

    /** Champ de texte du choix. */
    private JTextPane textField;
    /** le choix. */
    private String choix;
    /** Si le choix est terminé. */
    private boolean isOk;

    /** Constructeur de la fenêtre de choix du groupe.
     * @throws IOException
     */
    public AskRegex() {

        super("Entrer votre choix");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.isOk = false;
        
     try {
        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Votre choix");
        Dimension dimLabel = new Dimension(100, 24);
        label.setPreferredSize(dimLabel);

        this.textField = new JTextPane();
        textField.setText("Entrer votre choix ici...");
        textField.setEditable(true);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(50, 50));
        textField.setForeground(Color.BLACK);
        textField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Entrer votre choix ici...")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Entrer votre choix ici...");
                    textField.setForeground(Color.BLACK);
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
                choix = textField.getText();
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane(textField);
        Dimension scrollDimension = new Dimension(400, 50);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(scrollDimension);

        panel.add(label);
        panel.add(scrollPane);
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
    public JTextPane getTextField() {
        return textField;
    }

    /** Modifie le champ de texte.
     * 
     * @param textField Nouveau champ de texte
     */
    public void setTextField(JTextPane textField) {
        this.textField = textField;
    }

    /** Retourne le choix.
     * 
     * @return Choix
     */
    public String getChoix() {
        return choix;
    }

    /** Modifie le choix.
     * 
     * @param choix Nouveau choix
     */
    public void setChoix(String choix) {
        this.choix = choix;
    }

    /** Retourne si le choix est valide.
     * 
     * @return Choix valide
     */
    public boolean isOk() {
        return isOk;
    }
}
