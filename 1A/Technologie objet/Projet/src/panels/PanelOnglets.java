package panels;
import javax.swing.*;
import java.awt.Dimension;
import java.io.IOException;
import frame.*;

/** Classe qui permet de créer le panel des onglets. */
public class PanelOnglets {

  /* La fenêtre principale. */
	private Menu menu;

  /** Constructeur de la classe PanelOnglets.
   * @param frame La fenêtre principale.
 * @throws IOException 
   */
  public PanelOnglets(Menu menu) throws IOException {
	  this.menu = menu;
    JPanel p1 = new JPanel();
    
    JTabbedPane onglets = new JTabbedPane();
    // selon la taille de la fenêtre
    onglets.setPreferredSize(new Dimension(500, 500));
    onglets.add("Regex", p1);
    onglets.add("Ajouter", new Menu());

    //Obliger de préciser javax.swing.event sinon il prends la mauvaise librairie
    onglets.addChangeListener(new javax.swing.event.ChangeListener() {
        public void stateChanged(javax.swing.event.ChangeEvent e) {
          //On détecte si l'onglet sélectionné est "Ajouter"
            if (onglets.getTitleAt(onglets.getSelectedIndex()).equals("Ajouter")) {
                onglets.setSelectedIndex(0);
                onglets.insertTab("Nouveau", null, new JPanel(), null, onglets.getTabCount()-1);
                onglets.setSelectedIndex(onglets.getTabCount()-2);
            }
        }
    });
    //Ajouter les onglets au frame
    menu.add(onglets);
  }

  public Menu getMenu() {
    return menu;
  }
}
