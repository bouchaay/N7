package User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import ShemaTableElements.ColumnA;
import ShemaTableElements.ColumnDataTypeA;

public class Import {

    private List<ColumnA<String>> columns;

    public Import() {
        this.columns = new ArrayList<>();
    }

    public List<ColumnA<String>> getColumns() {
        return this.columns;
    }

    public void updateData() {
        JOptionPane.showMessageDialog(null, "Importer");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner le fichier CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV (*.csv)", "csv"));

        // Affichage de la boîte de dialogue
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                boolean firstLine = true;

                while ((line = reader.readLine()) != null) {
                    if (!firstLine) {
                        String[] parts = line.split(",");

                    
                        // Start filling columns from the first column of CSV
                        for (int i = 0; i < parts.length; i++) {
                            if (i < this.columns.size()) {
                                // If the column already exists in the list, update its values
                                List<String> values = this.columns.get(i).getValues();
                                values.add(parts[i]);
                                ColumnA<String> column = this.columns.get(i);
                                column.setValues(values);
                                this.columns.set(i, column);
                            } else {
                                // If the column doesn't exist in the list, create a new column
                                ColumnA<String> column = new ColumnA<>(parts[i], i, ColumnDataTypeA.STRING, null, new ArrayList<>());
                                column.getValues().add(parts[i]);  // Add the first value
                                this.columns.add(column);
                            }
                        }
                    }
                    firstLine = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
