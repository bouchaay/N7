package getXMI;
import ShemaTableElements.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ReferencedColumnEditor extends JFrame {

    private JTextField foreignTableNameTextField;
    private JTextField foreignColumnIdTextField;
    private JTextField newColumnNameTextField;
    private String foreignTableName;
    private int foreignColumnId;
    private String newColumnName;
    private List<ShemaTableA> tables;
    private ShemaTableA currentTable;
    private String resourcePath;

    public ReferencedColumnEditor(List<ShemaTableA> tables, ShemaTableA currentTable, String resourcePath){
    	this.tables = tables;
    	this.currentTable = currentTable;
    	this.resourcePath = resourcePath;
        initComponents();
        createAndShowGUI();
    }

    private void initComponents() {
        // Création des composants
        JLabel foreignTableNameLabel = new JLabel("Foreign Table Name:");
        foreignTableNameTextField = new JTextField();
        foreignTableNameTextField.setColumns(20); // Ajustez la taille en fonction de vos besoins

        JLabel foreignColumnIdLabel = new JLabel("Foreign Column ID:");
        foreignColumnIdTextField = new JTextField();
        foreignColumnIdTextField.setColumns(20); // Ajustez la taille en fonction de vos besoins
        
        JLabel newColumnNameLabel = new JLabel("New Column Name:");
        newColumnNameTextField = new JTextField();
        newColumnNameTextField.setColumns(20); // Ajustez la taille en fonction de vos besoins

        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validerButtonClicked();
            }
        });

        // Configuration de la mise en page
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Ajout des composants à la JFrame
        add(foreignTableNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(foreignTableNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(foreignColumnIdLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(foreignColumnIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(validerButton, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(newColumnNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(newColumnNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        add(validerButton, gbc);
    }

    private void createAndShowGUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Foreign Key Frame");
        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    }

    private void validerButtonClicked() {
        // Ajoutez votre logique de validation ici
        if (foreignTableNameTextField.getText().isEmpty() || foreignColumnIdTextField.getText().isEmpty() || newColumnNameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.foreignTableName = foreignTableNameTextField.getText();
        try {
            this.foreignColumnId = Integer.parseInt(foreignColumnIdTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "L'ID de la colonne doit être un entier", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.newColumnName = newColumnNameTextField.getText();
        TablesA shemaTables = new TablesA(tables);
        ShemaTableA foreignTable = shemaTables.getShemaTableByName(this.foreignTableName);
        ColumnA foreignColumn;
        try {
            foreignColumn = foreignTable.getColumnById(foreignColumnId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "La colonne n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ReferencedColumnA newRefColumn = new ReferencedColumnA(this.newColumnName, this.currentTable.getNbColumns(), foreignColumn.getDataType(), new ConstraintA(""), foreignTable, foreignColumn);
        currentTable.addColumn(newRefColumn);
        this.dispose();
        new ColumnEditor(currentTable, this.resourcePath, tables);
    }
    
    public String getForeignTableName() {
    	return this.foreignTableName;
    }
    
    public int getForeignColumnId() {
    	return this.foreignColumnId;
    }
}