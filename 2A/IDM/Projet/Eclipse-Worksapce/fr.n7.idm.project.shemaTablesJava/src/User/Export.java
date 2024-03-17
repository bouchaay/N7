package User;

import java.awt.List;
import java.io.FileWriter;
import java.util.ArrayList;

import ShemaTableElements.ShemaTableA;
import javax.swing.JOptionPane;

public class Export {

    /** ShemaTable de l'entrée */
    ShemaTableA table;

    public Export(ShemaTableA table) {
        this.table = table;
    }

    public void export() {
        // Recuperer les colonnes de la table
        this.table.getColumns();
        // Creer un fichier csv et l'ouvrir en ecriture
        try {
            // demander le nom du fichier avec un JPaneNotif
            String fileName = JOptionPane.showInputDialog(null, "Nom du fichier : ", "Export", JOptionPane.QUESTION_MESSAGE);
            FileWriter writer = new FileWriter(fileName + ".csv");
            // Ecrire les noms des colonnes dans la premiere ligne
            for (int i = 0; i < this.table.getColumns().size(); i++) {
                writer.append(this.table.getColumns().get(i).getName());
                System.out.println(this.table.getColumns().get(i).getName());
                if (i != this.table.getColumns().size() - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");
            // Ecrire les valeurs des colonnes dans les lignes suivantes
            ArrayList<Integer> indicecolNonvide = getIndicecolNonvide();
            for (int i = 0; i < this.table.getColumns().get(0).getValues().size(); i++) {
                for (int j : indicecolNonvide) {
                    System.out.println(this.table.getColumns().get(j).getValues().get(i).toString());
                    writer.append(this.table.getColumns().get(j).getValues().get(i).toString());
                    if (j != this.table.getColumns().size() - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getIndicecolNonvide() {
        ArrayList<Integer> indicecolNonvide = new ArrayList<>();
        for (int i = 0; i < this.table.getColumns().size(); i++) {
            if (this.table.getColumns().get(i).getValues().size() != 0) {
                indicecolNonvide.add(i);
            }
        }
        return indicecolNonvide;
    }

}
