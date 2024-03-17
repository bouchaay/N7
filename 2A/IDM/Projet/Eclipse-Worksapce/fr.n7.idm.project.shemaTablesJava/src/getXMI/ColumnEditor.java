package getXMI;

import javax.swing.*;

import ShemaTableElements.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ColumnEditor extends JFrame {

    private JTextField columnNameField;
    private JTextField columnIdField;
    private JComboBox<String> dataTypeComboBox;
    private JTextField constraintsPathField;
    private ShemaTableA table;
    private String folderPath;
    private List<ShemaTableA> shemaTables;

    public ColumnEditor(ShemaTableA table, String path, List<ShemaTableA> shemaTables) {
        this.table = table;
        this.folderPath = path;
        this.shemaTables = shemaTables;
        setTitle("Columns Editor");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        addFormField(panel, "Column Name:", columnNameField = new JTextField(20));
        addFormField(panel, "Column Id:", columnIdField = new JTextField(20));
        addFormField(panel, "Column DataType:", dataTypeComboBox = new JComboBox<>(new String[]{"int", "float", "string"}));
        addFormField(panel, "Constraint File Path:", constraintsPathField = new JTextField(20));

        JPanel buttonsPanel = createButtonsPanel();
        panel.add(buttonsPanel);

        add(panel);
        setVisible(true);
    }

    private void addFormField(Container container, String label, JComponent field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel(label));
        panel.add(field);
        container.add(panel);
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        JButton addButton = new JButton("Add Column");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addColumn();
            }
        });

        JButton derivedColumnButton = new JButton("Add Derived Column");
        derivedColumnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new DerivedColumnEditor(table, folderPath, shemaTables);
            }
        });

        JButton refColumnButton = new JButton("Add Referenced Column");
        refColumnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ReferencedColumnEditor(shemaTables, table, folderPath);
            }
        });

        buttonsPanel.add(addButton);
        buttonsPanel.add(derivedColumnButton);
        buttonsPanel.add(refColumnButton);

        return buttonsPanel;
    }

    private void addColumn() {
        if (columnNameField.getText().equals("") || columnIdField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Column Name and Column Id must not be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String columnName = columnNameField.getText();
        int columnId;
        try {
            columnId = Integer.parseInt(columnIdField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Column Id must be an integer", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ColumnDataTypeA columnDataType;
        String dataType = (String) dataTypeComboBox.getSelectedItem();
        if (dataType.equals("int")) {
            columnDataType = ColumnDataTypeA.INTEGER;
        } else if (dataType.equals("float")) {
            columnDataType = ColumnDataTypeA.FLOAT;
        } else if (dataType.equals("string")) {
            columnDataType = ColumnDataTypeA.STRING;
        } else {
            columnDataType = ColumnDataTypeA.STRING;
        }
        String constraintsPath = constraintsPathField.getText();

        switch (columnDataType) {
            case INTEGER:
                table.addColumn(new ColumnA<Integer>(columnName, columnId, columnDataType, new ConstraintA(constraintsPath)));
                break;
            case FLOAT:
                table.addColumn(new ColumnA<Float>(columnName, columnId, columnDataType, new ConstraintA(constraintsPath)));
                break;
            case STRING:
                table.addColumn(new ColumnA<String>(columnName, columnId, columnDataType, new ConstraintA(constraintsPath)));
                break;
            default:
                table.addColumn(new ColumnA<String>(columnName, columnId, columnDataType, new ConstraintA(constraintsPath)));
                break;
        }

        clearFields();
    }

    private void clearFields() {
        columnNameField.setText("");
        columnIdField.setText("");
        dataTypeComboBox.setSelectedIndex(0);
        constraintsPathField.setText("");
    }
}