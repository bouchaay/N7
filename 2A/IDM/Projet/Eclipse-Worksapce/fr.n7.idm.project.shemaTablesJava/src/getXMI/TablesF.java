package getXMI;

import java.util.List;
import java.util.ArrayList;
import ShemaTableElements.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.*;

public class TablesF {

	/** Indice de la table courante */
	private static int tableId = 1;
	
    /** La liste des shemma de table créés */
    private List<ShemaTableA> shemaTables;
    
    /** Les ressources des algorithmes */
    private static ResourceA algoRessource = new ResourceA("/Ressource");

    /**
     * Constructeur de la classe Tables
     */
    public TablesF() {
        this.shemaTables = new ArrayList<>();
    }
    
    /**
     * Constructeur de la classe Tables
     */
    public TablesF(List<ShemaTableA> shemaTables) {
        this.shemaTables = shemaTables;
    }

    /**
     * Getter de la liste des shema de table
     * @return
     */
    public List<ShemaTableA> getShemaTables() {
        return shemaTables;
    }

    /**
     * Setter de la liste des shema de table
     * @param shemaTables
     */
    public void setShemaTables(List<ShemaTableA> shemaTables) {
        this.shemaTables = shemaTables;
    }

    /**
     * Ajoute un shema de table à la liste des shema de table
     * @param shemaTable
     */
    public void addShemaTable(ShemaTableA shemaTable) {
        this.shemaTables.add(shemaTable);
    }

    /**
     * Supprime un shema de table de la liste des shema de table
     * @param shemaTable
     */
    public void removeShemaTable(ShemaTableA shemaTable) {
        this.shemaTables.remove(shemaTable);
    }

    /**
     * Retourne le shema de table associé au nom de table donné
     * @param tableName
     * @return
     */
    public ShemaTableA getShemaTable(String tableName) {
        for (ShemaTableA shemaTable : shemaTables) {
            if (shemaTable.getName().equals(tableName)) {
                return shemaTable;
            }
        }
        return null;
    }

    /**
     * Retourne le shema de table associé à l'id de table donné
     * @param tableId
     * @return
     */
    public ShemaTableA getShemaTable(int tableId) {
        for (ShemaTableA shemaTable : shemaTables) {
            if (shemaTable.getId() == tableId) {
                return shemaTable;
            }
        }
        return null;
    }

    /**
     * Supprimer la table par ID
     * @param tableId
     */
    public void removeShemaTable(int tableId) {
        for (ShemaTableA shemaTable : shemaTables) {
            if (shemaTable.getId() == tableId) {
                shemaTables.remove(shemaTable);
                break;
            }
        }
    }

    /**
     * Supprimer une table par nom
     * @param tableName
     */
    public void removeShemaTable(String tableName) {
        for (ShemaTableA shemaTable : shemaTables) {
            if (shemaTable.getName().equals(tableName)) {
                shemaTables.remove(shemaTable);
                break;
            }
        }
    }

    /**
     * Le prochain ID de table disponible
     * @return
     */
    public int nextTableId() {
        int maxId = 0;
        for (ShemaTableA shemaTable : shemaTables) {
            if (shemaTable.getId() > maxId) {
                maxId = shemaTable.getId();
            }
        }
        return maxId + 1;
    }

    /**
     * Interface Graphique pour gerer les tables
     */
    public void tablesGUI() {
        // Créer une nouvelle JFrame
        JFrame frame = new JFrame("Tables");
        
     // Ajoutez un écouteur de fenêtre pour détecter la fermeture
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                synchronized (shemaTables) {
                    shemaTables.notify();  // Signale que la GUI est terminée
                }
            }
        });

        // Créer un nouveau JPanel
        JPanel panel = new JPanel();

        // Créer un nouveau GridBagLayout
        GridBagLayout gbl = new GridBagLayout();
        panel.setLayout(gbl);

        // Créer un nouveau GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();

        // Créer un nouveau JButton pour ajouter une table
        JButton addTableButton = new JButton("Add Table");
        addTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        		TableInfos table = new TableInfos(tableId);
        		tableId++;
        		ShemaTableA shemaTable = table.getTable();
        		new ColumnEditor(shemaTable, algoRessource.getFolderPath(), shemaTables);
                addShemaTable(shemaTable);
            }
        });

        // Créer un nouveau JButton pour supprimer une table
        JButton removeTableButton = new JButton("Remove Table");
        removeTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shemaTables.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Aucune table n'a été créée", "Tables", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String[] tableNames = new String[shemaTables.size()];
                for (int i = 0; i < shemaTables.size(); i++) {
                    tableNames[i] = shemaTables.get(i).getName();
                }
                String tableName = (String) JOptionPane.showInputDialog(null, "Nom de la table à supprimer : ", "Tables", JOptionPane.QUESTION_MESSAGE, null, tableNames, tableNames[0]);
                if (tableName != null) {
                    int idTableCourante = getShemaTable(tableName).getId();
                    removeShemaTable(tableName);
                    // decrementer les id des tables suivantes
                    for (int i = idTableCourante; i < shemaTables.size(); i++) {
                        shemaTables.get(i).setId(shemaTables.get(i).getId() - 1);
                    }
                }
            }
        });

        // Créer un nouveau JButton pour afficher les tables
        JButton showTablesButton = new JButton("Show Tables");
        showTablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] tableNames = new String[shemaTables.size()];
                if (shemaTables.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Aucune table n'a été créée", "Tables", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                for (int i = 0; i < shemaTables.size(); i++) {
                    tableNames[i] = shemaTables.get(i).getName();
                }
                String tableName = (String) JOptionPane.showInputDialog(null, "Nom de la table à afficher : ", "Tables", JOptionPane.QUESTION_MESSAGE, null, tableNames, tableNames[0]);
                if (tableName != null) {
                    ShemaTableA shemaTable = getShemaTable(tableName);
                    JOptionPane.showMessageDialog(null, "Name: " + shemaTable.getName() + " - Id: " +shemaTable.getId() , "Tables", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Créer un boutton pour valider l'enregistrement des tables dans la liste et fermer la fenetre
        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
            }
        });

        // Régler la fenetre mainteant pour l afficher apres
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ajouter les composants au JPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(addTableButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(removeTableButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(showTablesButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(validerButton, gbc);

        // Ajouter le JPanel à la JFrame
        frame.add(panel);

        // Afficher la fenetre
        frame.setVisible(true);
    }
}
