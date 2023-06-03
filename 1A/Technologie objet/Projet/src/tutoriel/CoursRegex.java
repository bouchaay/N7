package tutoriel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/** Classe des slides du tutoriel.
 * Affiche les différents slides du tutoriel.
 */
public class CoursRegex extends JFrame {

    /** Le cardLayout permet de gérer les différents slides. */
    private CardLayout cardLayout;
    /** Le panel qui contient les différents slides. */
    private JPanel cardsPanel;
    /** L'index du slide actuel. */
    private int currentIndex;

    /** Constructeur.
     * Création de la fenêtre et ajout des différents panneaux.
     * @throws IOException
     */
    public CoursRegex() throws IOException {
        super("Tutoriel des expressions régulières");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#0B0B3B"));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true);

        // Définir l'icon de la fenêtre
        BufferedImage icon = ImageIO.read(getClass().getResource("reg7.png"));
        this.setIconImage(icon);

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        currentIndex = 0;

        // Slide Introduction
        LabelsCours slideTitle1 = new LabelsCours(StringVarCours.S1);
        cardsPanel.add(slideTitle1.returnTitleLabel(), "slide1");

        LabelsCours slideContent1 = new LabelsCours(StringVarCours.I1);
        cardsPanel.add(slideContent1.returnTextLabel(), "slide2");

        // Slide Méta-caractères
        LabelsCours slideTitle2 = new LabelsCours(StringVarCours.S2);
        cardsPanel.add(slideTitle2.returnTitleLabel(), "slide3");

        LabelsCours slideContent2 = new LabelsCours(StringVarCours.I2);
        cardsPanel.add(slideContent2.returnTextLabel(), "slide4");

        LabelsCours tabMeta = new LabelsCours(StringVarCours.tabMetaCarac);
        cardsPanel.add(tabMeta.returnTab(), "slide5");

        // Full Stop
        LabelsCours slideSousTitle1 = new LabelsCours(StringVarCours.SS1);
        cardsPanel.add(slideSousTitle1.returnSousTitleLabel(), "slide6");

        LabelsCours slideContent3 = new LabelsCours(StringVarCours.I3);
        cardsPanel.add(slideContent3.returnTextLabel(), "slide7");

        // Inclusion de caractères
        LabelsCours slideSousTitle2 = new LabelsCours(StringVarCours.SS2);
        cardsPanel.add(slideSousTitle2.returnSousTitleLabel(), "slide8");

        LabelsCours slideContent4 = new LabelsCours(StringVarCours.I4);
        cardsPanel.add(slideContent4.returnTextLabel(), "slide9");

        // Répétition
        LabelsCours slideSousTitle3 = new LabelsCours(StringVarCours.SS3);
        cardsPanel.add(slideSousTitle3.returnSousTitleLabel(), "slide10");

        LabelsCours slideContent5 = new LabelsCours(StringVarCours.I5);
        cardsPanel.add(slideContent5.returnTextLabel(), "slide11");

        // Asthérisque
        LabelsCours slideContent6 = new LabelsCours(StringVarCours.I6);
        cardsPanel.add(slideContent6.returnTextLabel(), "slide12");

        // Plus
        LabelsCours slideContent7 = new LabelsCours(StringVarCours.I7);
        cardsPanel.add(slideContent7.returnTextLabel(), "slide13");

        // Le point d'interrogation ?
        LabelsCours slideContent8 = new LabelsCours(StringVarCours.I8);
        cardsPanel.add(slideContent8.returnTextLabel(), "slide14");

        // Les accolades {}
        LabelsCours slideSousTitle4 = new LabelsCours(StringVarCours.SS4);
        cardsPanel.add(slideSousTitle4.returnSousTitleLabel(), "slide15");

        LabelsCours slideContent9 = new LabelsCours(StringVarCours.I9);
        cardsPanel.add(slideContent9.returnTextLabel(), "slide16");

        // Groupement de caractères
        LabelsCours slideSousTitle5 = new LabelsCours(StringVarCours.SS5);
        cardsPanel.add(slideSousTitle5.returnSousTitleLabel(), "slide17");

        LabelsCours slideContent10 = new LabelsCours(StringVarCours.I10);
        cardsPanel.add(slideContent10.returnTextLabel(), "slide18");

        // Alternation
        LabelsCours slideSousTitle6 = new LabelsCours(StringVarCours.SS6);
        cardsPanel.add(slideSousTitle6.returnSousTitleLabel(), "slide19");
        
        LabelsCours slideContent11 = new LabelsCours(StringVarCours.I11);
        cardsPanel.add(slideContent11.returnTextLabel(), "slide20");

        // Les caractères d'échappement
        LabelsCours slideSousTitle7 = new LabelsCours(StringVarCours.SS7);
        cardsPanel.add(slideSousTitle7.returnSousTitleLabel(), "slide21");

        LabelsCours slideContent12 = new LabelsCours(StringVarCours.I12);
        cardsPanel.add(slideContent12.returnTextLabel(), "slide22");

        // Ancres
        LabelsCours slideSousTitle8 = new LabelsCours(StringVarCours.SS8);
        cardsPanel.add(slideSousTitle8.returnSousTitleLabel(), "slide23");

        LabelsCours slideContent13 = new LabelsCours(StringVarCours.I13);
        cardsPanel.add(slideContent13.returnTextLabel(), "slide24");

        // Listes de caractères abrégées
        LabelsCours slideSousTitle9 = new LabelsCours(StringVarCours.S3);
        cardsPanel.add(slideSousTitle9.returnSousTitleLabel(), "slide25");

        LabelsCours slideContent14 = new LabelsCours(StringVarCours.tabCaracAbrege);
        cardsPanel.add(slideContent14.returnTextLabel(), "slide26");

        // Recherche
        LabelsCours slideSousTitle10 = new LabelsCours(StringVarCours.S4);
        cardsPanel.add(slideSousTitle10.returnSousTitleLabel(), "slide27");

        LabelsCours slideContent15 = new LabelsCours(StringVarCours.I14);
        cardsPanel.add(slideContent15.returnTextLabel(), "slide28");

        // Recherche en avant positive
        LabelsCours slideSousTitle11 = new LabelsCours(StringVarCours.SS9);
        cardsPanel.add(slideSousTitle11.returnSousTitleLabel(), "slide29");

        LabelsCours slideContent16 = new LabelsCours(StringVarCours.I15);
        cardsPanel.add(slideContent16.returnTextLabel(), "slide30");

        // Recherche en avant négative
        LabelsCours slideSousTitle12 = new LabelsCours(StringVarCours.SS10);
        cardsPanel.add(slideSousTitle12.returnSousTitleLabel(), "slide31");

        LabelsCours slideContent17 = new LabelsCours(StringVarCours.I16);
        cardsPanel.add(slideContent17.returnTextLabel(), "slide32");

        // Recherche en arrière positive
        LabelsCours slideSousTitle13 = new LabelsCours(StringVarCours.SS11);
        cardsPanel.add(slideSousTitle13.returnSousTitleLabel(), "slide33");

        LabelsCours slideContent18 = new LabelsCours(StringVarCours.I17);
        cardsPanel.add(slideContent18.returnTextLabel(), "slide34");

        // Recherche en arrière négative
        LabelsCours slideSousTitle14 = new LabelsCours(StringVarCours.SS12);
        cardsPanel.add(slideSousTitle14.returnSousTitleLabel(), "slide35");

        LabelsCours slideContent19 = new LabelsCours(StringVarCours.I18);
        cardsPanel.add(slideContent19.returnTextLabel(), "slide36");

        // Drapeau
        LabelsCours slideSousTitle15 = new LabelsCours(StringVarCours.S5);
        cardsPanel.add(slideSousTitle15.returnSousTitleLabel(), "slide37");

        LabelsCours slideContent20 = new LabelsCours(StringVarCours.I19);
        cardsPanel.add(slideContent20.returnTextLabel(), "slide38");

        // Insensible à la casse
        LabelsCours slideSousTitle16 = new LabelsCours(StringVarCours.SS13);
        cardsPanel.add(slideSousTitle16.returnSousTitleLabel(), "slide39");

        LabelsCours slideContent21 = new LabelsCours(StringVarCours.I20);
        cardsPanel.add(slideContent21.returnTextLabel(), "slide40");

        // Correpondance globale
        LabelsCours slideSousTitle17 = new LabelsCours(StringVarCours.SS14);
        cardsPanel.add(slideSousTitle17.returnSousTitleLabel(), "slide41");

        LabelsCours slideContent22 = new LabelsCours(StringVarCours.I21);
        cardsPanel.add(slideContent22.returnTextLabel(), "slide42");

        // Multiligne
        LabelsCours slideSousTitle18 = new LabelsCours(StringVarCours.SS15);
        cardsPanel.add(slideSousTitle18.returnSousTitleLabel(), "slide43");

        LabelsCours slideContent23 = new LabelsCours(StringVarCours.I22);
        cardsPanel.add(slideContent23.returnTextLabel(), "slide44");

        // Fin du tutoriel
        LabelsCours slideSousTitle19 = new LabelsCours(StringVarCours.S6);
        cardsPanel.add(slideSousTitle19.returnTitleLabel(), "slide45");

        JButton nextButton = new JButton("Suivant");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;
                if (currentIndex >= cardsPanel.getComponentCount()) {
                    currentIndex = 0;
                }
                cardLayout.show(cardsPanel, "slide" + (currentIndex + 1));
            }
        });

        JButton previousButton = new JButton("Précédent");
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex--;
                if (currentIndex < 0) {
                    // ne rien faire
                    currentIndex = 0;
                }
                cardLayout.show(cardsPanel, "slide" + (currentIndex + 1));
            }
        });

        JButton homeButton = new JButton("Fermer");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButton downloadButton = new JButton("Télécharger");
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = "regex.pdf";
                String filePath = "/tutoriel/regex.pdf";  // Chemin relatif du fichier par rapport au package

                // Créer un sélecteur de fichiers
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int option = fileChooser.showSaveDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    // Récupérer le répertoire sélectionné par l'utilisateur
                    File selectedDirectory = fileChooser.getSelectedFile();

                    // Créer le chemin complet du fichier de destination
                    String destinationPath = selectedDirectory.getAbsolutePath() + File.separator + fileName;

                    // Récupérer le flux d'entrée du fichier à partir du package
                    InputStream inputStream = getClass().getResourceAsStream(filePath);

                    if (inputStream != null) {
                        // Faites ce que vous souhaitez avec le flux d'entrée, par exemple, enregistrez-le sur le disque
                        try (OutputStream outputStream = new FileOutputStream(destinationPath)) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = inputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                            JOptionPane.showMessageDialog(null, "Téléchargement Terminé !", null, JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        // Le fichier n'a pas été trouvé dans le package spécifié
                        JOptionPane.showMessageDialog(null, "Téléchargement Impossible !", null, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });




        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(200, 50));
        buttonPanel.add(previousButton, BorderLayout.WEST);
        buttonPanel.add(nextButton, BorderLayout.EAST);
        buttonPanel.add(homeButton, BorderLayout.CENTER);
        buttonPanel.add(downloadButton, BorderLayout.SOUTH);

        // Créer un conteneur pour le buttonPanel avec une bordure de 2cm
        JPanel buttonPanelContainer = new JPanel(new BorderLayout());
        buttonPanelContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanelContainer.add(buttonPanel);

        add(cardsPanel, BorderLayout.CENTER);
        add(buttonPanelContainer, BorderLayout.SOUTH);

        setVisible(true);
    }

    /** Retourne le cardLayout.
     * @return cardLayout
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /** Retourne le panel contenant les cartes.
     * @return cardsPanel
     */
    public JPanel getCardsPanel() {
        return cardsPanel;
    }

    /** Retourne l'index de la carte courante.
     * @return currentIndex
     */
    public int getCurrentIndex() {
        return currentIndex;
    }

    /** Modifie l'index de la carte courante.
     * @param currentIndex
     */
    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
