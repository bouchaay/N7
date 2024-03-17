package Frame;
import ShemaTableElements.*;
import User.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

/** Classe qui permet de créer une fenêtre à partir d'une table. */
public class TableToFrame extends JFrame {

    /** La JTable */
    private JTable jTable;
    /** Le modèle de tableau */
    private DefaultTableModel tableModel;
    /** La table */
	private ShemaTableA table;
    /** L'ensemble des tbales */
    private TablesA shemaTables;

    /**
     * Constructeur de la classe TableToFrame
     * @param table La table
     */
    public TableToFrame(ShemaTableA table, List<ShemaTableA> shemaTables) {
		this.table = table;
        this.shemaTables = new TablesA(shemaTables);
        tableModel = new DefaultTableModel();

        // Créer la JTable et l'attacher au modèle de tableau
        jTable = new JTable(tableModel);

        // Ajouter la JTable à un JScrollPane pour permettre le défilement si nécessaire
        JScrollPane scrollPane = new JScrollPane(jTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Ajouter des boutons pour les opérations d'ajout et de suppression
        JPanel buttonPanel = new JPanel();
        JButton addRowButton = new JButton("Ajouter Ligne");
        JButton removeRowButton = new JButton("Supprimer Ligne");
    
        // Ajouter les écouteurs d'événements pour les boutons
        addRowButton.addActionListener(e -> ajouterLigne());
        removeRowButton.addActionListener(e -> supprimerLigne());

        // Ajouter les boutons au panneau
        buttonPanel.add(addRowButton);
        buttonPanel.add(removeRowButton);
    
		// Créer la barre de menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Créer le menu "Fichier"
        JMenu fichierMenu = new JMenu("Fichier");
        menuBar.add(fichierMenu);

        // Créer le menu "visaualiser"
        JMenu visualiseMenu = new JMenu("visualiser");
        menuBar.add(visualiseMenu);

        // Ajouter l'option "Graphe"
        JMenuItem graphMenuItem = new JMenuItem("Courbe");
        graphMenuItem.addActionListener(e -> visualiser(0)); 
        visualiseMenu.add(graphMenuItem );
        
        // Ajouter l'option "Histogramme"
        JMenuItem histogrammeMenuItem = new JMenuItem("Histogramme");
        histogrammeMenuItem.addActionListener(e -> visualiser(1));
        visualiseMenu.add(histogrammeMenuItem);

        // Ajouter l'option "Importer"
        JMenuItem importerMenuItem = new JMenuItem("Importer");
        importerMenuItem.addActionListener(e -> impoorterf()); 
        fichierMenu.add(importerMenuItem);

        // Ajouter l'option "Exporter"
        JMenuItem exporterMenuItem = new JMenuItem("Exporter");
        exporterMenuItem.addActionListener(e -> exporterf()); 
        fichierMenu.add(exporterMenuItem);

        // Ajouter l'option Enregistrer
        JMenuItem enregistrerMenuItem = new JMenuItem("Enregistrer");
        enregistrerMenuItem.addActionListener(e -> Enregistrer());
        fichierMenu.add(enregistrerMenuItem);
        
        // Ajouter le panneau de boutons au panneau principal
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		// Initialiser le modèle de tableau par défaut
		initialiserTableau();
		
        // Définir les propriétés de la fenêtre
        this.setTitle("Table");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
        this.setVisible(true);
    }

    /** Méthode pour initialiser le modèle de tableau par défaut. */
    private void initialiserTableau() {
        List<ColumnA<?>> columns = table.getColumns();
		for (ColumnA<?> c : columns) {
			tableModel.addColumn(String.valueOf(c.getName()));
		}
    }

    /** Méthode pour importer un fichier. */
	private void impoorterf() {
		Import imp = new Import();
		imp.updateData();
        List<ColumnA<String>> columns = imp.getColumns();
		mettreAJourTableauImporter(columns);
	}

	/** Méthode pour mettre à jour le tableau.
	 * @param columns Les colonnes à ajouter ou mettre à jour dans le tableau
	 */
	private void mettreAJourTableau(List<ColumnA<String>> columns) {
	    // Récupérer les colonnes de la table
	    List<ColumnA<?>> tableColumns = table.getColumns();
	    // Nombre de colonnes du tableau importé
	    int nbColTab = columns.size();
	    // Nombre de colonnes de notre table
	    int nbCol = tableColumns.size();

	    if (nbCol >= nbColTab) {
	        // Ajouter la première colonne avec les positions des lignes
	        ColumnA<String> firstColumn = new ColumnA<>("id", 0, ColumnDataTypeA.STRING, null, new ArrayList<>());
	        List<String> positions = new ArrayList<>();
	        for (int row = 0; row < columns.get(0).getValues().size(); row++) {
	            positions.add(String.valueOf(row + 1));  // Ajouter 1 pour commencer à partir de 1
	        }
	        firstColumn.setValues(positions);
	        tableColumns.set(0, firstColumn);

	        // Mettre à jour les valeurs des colonnes existantes à partir de la deuxième colonne
	        for (int i = 1; i < nbColTab; i++) {
	            ColumnA<String> currentColumn = (ColumnA<String>) tableColumns.get(i);
	            currentColumn.setValues(columns.get(i).getValues());
	            tableColumns.set(i, currentColumn);
	        }

	        // Mettre à jour l'affichage du tableau
	        updateTableModel(tableColumns);
	    } else {
	        // Le tableau importé a plus de colonnes que notre table, non adapté à notre table
	        initialiserTableau();
	        JOptionPane.showMessageDialog(this, "Le tableau importé n'est pas adapté à notre table");
	    }
	}
	
	/** Méthode pour mettre à jour le tableau.
	 * @param columns Les colonnes à ajouter ou mettre à jour dans le tableau
	 */
	private void mettreAJourTableauImporter(List<ColumnA<String>> columns) {
	    // Récupérer les colonnes de la table
	    List<ColumnA<?>> tableColumns = table.getColumns();
	    // Nombre de colonnes du tableau importé
	    int nbColTab = columns.size();
	    // Nombre de colonnes de notre table
	    int nbCol = tableColumns.size();

	    if (nbCol >= nbColTab) {
	        // Ajouter la première colonne avec les positions des lignes
	        ColumnA<String> firstColumn = new ColumnA<>("id", 0, ColumnDataTypeA.STRING, null, new ArrayList<>());
	        List<String> positions = new ArrayList<>();
	        for (int row = 0; row < columns.get(0).getValues().size(); row++) {
	            positions.add(String.valueOf(row + 1));  // Ajouter 1 pour commencer à partir de 1
	        }
	        firstColumn.setValues(positions);
	        tableColumns.set(0, firstColumn);

	        // Mettre à jour les valeurs des colonnes existantes à partir de la deuxième colonne
	        for (int i = 1; i < nbColTab; i++) {
	            ColumnA<String> currentColumn = (ColumnA<String>) tableColumns.get(i);
	            currentColumn.setValues(columns.get(i-1).getValues());
	            tableColumns.set(i, currentColumn);
	        }

            // Ajouter la dernière colonne du fichier importé
            ColumnA<String> lastColumn = (ColumnA<String>) tableColumns.get(nbColTab);
            lastColumn.setValues(columns.get(nbColTab-1).getValues());
            tableColumns.set(nbColTab, lastColumn);

            // Refraicher les données du shematable surtout les colonnes référencées d'autre table.
            //refreshReferencedColumns();
	        // Mettre à jour l'affichage du tableau
	        updateTableModel(tableColumns);
	    } else {
	        // Le tableau importé a plus de colonnes que notre table, non adapté à notre table
	        initialiserTableau();
	        JOptionPane.showMessageDialog(this, "Le tableau importé n'est pas adapté à notre table");
	    }
	}

    /** Refraicher les données du shematable surtout les colonnes référencées d'autre table. */
    //public void refreshReferencedColumns() {
        //for (ShemaTableA shemaTable : shemaTables.getShemaTables()) {
          //  List<ColumnA<?>> columns = shemaTable.getColumns();
            //for (ColumnA<?> column : columns) {
              //  if (column instanceof ReferencedColumnA) {
                //    ReferencedColumnA referencedColumn = (ReferencedColumnA) column;
                  //  ShemaTableA referencedTable = shemaTables.getShemaTable(referencedColumn.getForeignTable().getId());
                    //int id = referencedColumn.getForeignColumn().getId();
                    //ColumnA<?> foreignColumn = referencedTable.getColumnById(id);
                    // insérer les valeurs de la colonne référencée
                    //referencedColumn.setValues(foreignColumn.getValues());
                    // mettre à jour la colonne référencée
                    //table.setColumnName(referencedColumn.getName(), referencedColumn);
                //}
            //}
        //}
        //updateTableModel(table.getColumns());
    //}

	/** Méthode pour mettre à jour le modèle de tableau.
	 * @param columns Les colonnes à ajouter ou mettre à jour dans le modèle
	 */
	private void updateTableModel(List<ColumnA<?>> columns) {
	    // Effacer le modèle actuel
	    tableModel.setColumnCount(0);

	    // Ajouter les colonnes mises à jour au modèle
	    for (ColumnA<?> column : columns) {
	        tableModel.addColumn(String.valueOf(column.getName()), column.getValues().toArray());
	    }
	}




    /** Méthode pour enregistrer les données. */
    private void Enregistrer() {
        for (ColumnA c : table.getColumns()) {
        	if (!(c.getId()==0) && !(c.getName().equals("id"))) {
        		List<?> values = getColumn(c.getName());
        		ColumnA column = new ColumnA(c.getName(), c.getId(), c.getDataType(), c.getConstraintFile(), values);
        		this.table.setColumnName(c.getName(), column);
        	}
        }
        
        CatalogueA catalogue = table.getCatalogue();
        List<AlgorithmA> algorithms = catalogue.getAlgorithms();
        if (algorithms.size() != 0) {
        for (int i = 0; i < algorithms.size(); i++) {
            Ressource.OperationA op = new Ressource.OperationA(table);
            System.out.println("algo " + algorithms.get(i).getName());
            String list = op.excOperation(i);
            List<?> columnCalcu = getValueInt(list);
            String nameAlg = algorithms.get(i).getOutput().getName() ;
            this.table.setColumnWithName(nameAlg, columnCalcu);
        }
        }

        List<ColumnA<String>> columnstr = toColumnsStr(table.getColumns());
        mettreAJourTableau(columnstr); 
        JOptionPane.showMessageDialog(this, "Enregistrer");
    }

    /** Méthode pour convertir une chaîne en liste de nombres à virgule flottante.
     * @param colum La chaîne à convertir
     * @return La liste de nombres à virgule flottante
     */
    private List<String> getValueInt (String colum){
        List<String> columnc = new ArrayList<>();

        // Supprimer les crochets d'ouverture et de fermeture
        colum = colum.substring(1, colum.length() - 1);

        // Diviser la chaîne en un tableau de chaînes en utilisant la virgule comme délimiteur
        String[] elements = colum.split(",");
        for (String element : elements) {
            try {
                double valeur = Double.parseDouble(element.trim());
                columnc.add(String.valueOf(valeur));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                // Handle the case where conversion fails (invalid string)
            }
        }

        // Display the list of floating-point numbers
        System.out.println(columnc);
        return columnc;
    }

    /** Méthode pour visualiser les données.
     * @param typeGraphique Le type de graphique
     */
    private void visualiser(int typeGraphique) {

        try {
            Runtime.getRuntime().exec("python tracer_graphique.py");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ColumnA<?>> columns = table.getColumns();
        String[] choixSelectionnes = new String[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            choixSelectionnes[i] = columns.get(i).getName();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()  {
                Visualiser vis = new Visualiser(choixSelectionnes);
                // Attendre jusqu'à ce que la fenêtre soit fermée
                vis.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        // Utiliser les phrases dans un autre code
                        String choixX = vis.getXEx();
                        String choixY = vis.getYEx();
                        List<?> xs = getColumn(choixX);
                        List<?> ys = getColumn(choixY);
                        Graphe graph = new Graphe(xs, ys, typeGraphique);
                        graph.creerGraphe();
                    }
                });
            }
        });
    }

    /** Méthode pour convertir une chaîne en liste de nombres à virgule flottante.
     * @param colum La chaîne à convertir
     * @return La liste de nombres à virgule flottante
     */
    public List<Integer> strToInt (List<String> list){
        List<Integer> listInt = new ArrayList<Integer>();
        for (String str : list) {
            listInt.add(Integer.parseInt(str));
        }
        return listInt;
    }

    /** Méthode pour exporter les données. */
	private void exporterf() {
		new Export(this.table).export();
		JOptionPane.showMessageDialog(this, "Exporter");
	}
    // Méthode pour ajouter une ligne au tableau
    private void ajouterLigne() {
        Vector<Object> emptyRow = new Vector<>();
        int rowCount = tableModel.getRowCount();
        emptyRow.add(rowCount + 1);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            emptyRow.add("");
        }
        tableModel.addRow(emptyRow);
    }

    /** Méthode pour supprimer une ligne du tableau. */
    private void supprimerLigne() {
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une ligne à supprimer.");
        }
    }

    /** Méthode pour récupérer une colonne du tableau.
     * @param columnName Le nom de la colonne
     * @return La colonne
     */
    public List<?> getColumn(String columnName) {
        List<Object> column = new ArrayList<>(); // Specify the type of List as Object
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            column.add((Object) tableModel.getValueAt(i, tableModel.findColumn(columnName))); // Explicitly cast the value to Object
        }
        return column;
    }

    /** Méthode pour convertir une liste en liste de chaînes.
     * @param list La liste à convertir
     * @return La liste de chaînes
     */
    public List<String> toStr (List<?> list){
        List<String> listStr = new ArrayList<String>();
        for (Object str : list) {
            listStr.add(String.valueOf(str));
        }
        return listStr;
    }

    /** Transformer une colonne en colonne de valeur string.
     * @param colum la colonne à transformer
     * @return la nouvelle colonne
     */
    public ColumnA<String> toColumnStr (ColumnA<?> column){
        List<String> listStr = toStr(column.getValues());
        ColumnA<String> columnStr = new ColumnA<String>(column.getName(), column.getId(), ColumnDataTypeA.STRING, null, listStr);
        return columnStr;
    }

    /** Transofrmer un liste des colonnes en liste de colonnes de valeur string.
     * @param columns la liste des colonnes
     * @return la nouvelle liste des colonnes
    */
    public List<ColumnA<String>> toColumnsStr (List<ColumnA<?>> columns){
        List<ColumnA<String>> columnsStr = new ArrayList<ColumnA<String>>();
        for (ColumnA<?> column : columns) {
            columnsStr.add(toColumnStr(column));
        }
        return columnsStr;
    }
}