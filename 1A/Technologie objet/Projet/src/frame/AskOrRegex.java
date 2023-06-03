package frame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import panels.ColorConverter;

/** Classe de la fenetre pour demander le choix des choix pour le ou. */
public class AskOrRegex extends JFrame {

    /** Champ de texte du premier choix. */
    private JTextPane textField1;
    /** Champ de texte du deuxième choix. */
    private JTextPane textField2;
    /** liste des choix. */
    private List<String> listChoix;
    /** liste des champs de texte. */
    private List<JTextPane> list;

    /** Constructeur de la fenêtre de choix de la plage de caractères.
     * @throws IOException
     */
    public AskOrRegex() {

        super("Entrer votre choix");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.list = new ArrayList<JTextPane>();
        this.listChoix = new ArrayList<String>();
        JPanel panel = new JPanel();
        
     try {
        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);

        JLabel label = new JLabel("Votre choix");
        Dimension dimLabel = new Dimension(100, 24);
        label.setPreferredSize(dimLabel);

        this.textField1 = new JTextPane();
        textField1.setText("Choix");
        textField1.setEditable(true);
        textField1.setFont(new Font("Arial", Font.PLAIN, 14));
        textField1.setPreferredSize(new Dimension(100, 50));
        textField1.setForeground(Color.BLACK);
        textField1.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals("Choix")) {
                    textField1.setText("");
                    textField1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setText("Choix");
                    textField1.setForeground(Color.BLACK);
                }
            }
        });

        this.textField2 = new JTextPane();
        textField2.setText("Choix");
        textField2.setEditable(true);
        textField2.setFont(new Font("Arial", Font.PLAIN, 14));
        textField2.setPreferredSize(new Dimension(100, 50));
        textField2.setForeground(Color.BLACK);
        textField2.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField2.getText().equals("Choix")) {
                    textField2.setText("");
                    textField2.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField2.getText().isEmpty()) {
                    textField2.setText("Choix");
                    textField2.setForeground(Color.BLACK);
                }
            }
        });

        list.add(textField1);
        list.add(textField2);

        JButton ajouterChoix = new JButton("Ajouter");
        ColorConverter colorConverter = new ColorConverter("green");
        Color color = colorConverter.convertirColor();
        ajouterChoix.setBackground(color);
        ajouterChoix.setForeground(Color.WHITE);
        Dimension dimButton = new Dimension(100, 24);
        ajouterChoix.setPreferredSize(dimButton);
        ajouterChoix.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextPane textField = new JTextPane();
                textField.setText("Choix");
                textField.setEditable(true);
                textField.setFont(new Font("Arial", Font.PLAIN, 14));
                textField.setPreferredSize(new Dimension(100, 50));
                textField.setForeground(Color.BLACK);
                textField.addFocusListener(new java.awt.event.FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (textField.getText().equals("Choix")) {
                            textField.setText("");
                            textField.setForeground(Color.BLACK);
                        }
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                        if (textField.getText().isEmpty()) {
                            textField.setText("Choix");
                            textField.setForeground(Color.BLACK);
                        }
                    }
                });
                list.add(textField);
                // ajouter le textField au panel avant les deux boutons
                panel.add(textField, panel.getComponentCount() - 2);
                pack();
                repaint();
            }
        });

        JButton button = new JButton("Valider");
        ColorConverter colorConverter1 = new ColorConverter("green");
        Color color1 = colorConverter1.convertirColor();
        button.setBackground(color1);
        button.setForeground(Color.WHITE);
        Dimension dimButton1 = new Dimension(100, 24);
        button.setPreferredSize(dimButton1);
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                for (JTextPane textField : list) {
                    listChoix.add(textField.getText());
                }
                dispose();
            }
        });

        panel.add(label);
        panel.add(textField1);
        panel.add(textField2);
        panel.add(ajouterChoix);
        panel.add(button);

        this.setContentPane(panel);
        pack();

        this.setVisible(true);
     } catch (Exception e2) {
			e2.printStackTrace();
     }
    }

    /** Retourne le choix de l'utilisateur.
     * @return Le choix de l'utilisateur
     */
    public List<String> getChoix() {
        return listChoix;
    }
}
