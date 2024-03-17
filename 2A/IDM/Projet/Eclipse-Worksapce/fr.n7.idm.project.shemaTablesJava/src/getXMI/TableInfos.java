package getXMI;

import javax.swing.JOptionPane;
import ShemaTableElements.*;

/** Classe pour demander à l'utilisateur le nom de la table */
public class TableInfos {
    
        /** Nom de la table */
        String tableName;
        /** Id de la table */
        int tableId;
        /** Nom du catalogue de la table */
        String catalogueName;
        /** Shema de table associé */
        ShemaTableA table;

        /** Constructeur de la classe TableInfos 
         * Si l'utilisateur ne rentre pas un nomde table, on redemande le nom de la table
         * Si l'utilisateur ne rentre pas un id (entier) de table, on redemande l'id de la table
         * Si l'utilisateur ne rentre pas un nom de catalogue de table, on redemande le nom du catalogue de la table
        */
        public TableInfos(int tableId) {

            do {
                this.tableName = JOptionPane.showInputDialog(null, "Nom de la table : ", "TableInfos", JOptionPane.QUESTION_MESSAGE);
                if (this.tableName == null) {
                    System.exit(0);
                }
            } while (this.tableName.equals(""));
            do {
                this.catalogueName = JOptionPane.showInputDialog(null, "Nom du catalogue de la table : ", "TableInfos", JOptionPane.QUESTION_MESSAGE);
                if (this.catalogueName == null) {
                    System.exit(0);
                }
            } while (this.catalogueName.equals(""));
            this.table = new ShemaTableA(this.tableName, this.tableId, new CatalogueA(this.catalogueName));
        }

        /**
         * Getter du nom de la table
         * @return
         */
        public String getTableName() {
            return tableName;
        }

        /**
         * Getter de l'id de la table
         * @return
         */
        public int getTableId() {
            return tableId;
        }

        /**
         * Getter du nom du catalogue de la table
         * @return
         */
        public String getCatalogueName() {
            return catalogueName;
        }

        /**
         * Getter du shema de table associé
         * @return
         */
        public ShemaTableA getTable() {
            return table;
        }

        /**
         * Setter du nom de la table
         * @param tableName
         */
        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        /**
         * Setter de l'id de la table
         * @param tableId
         */
        public void setTableId(int tableId) {
            this.tableId = tableId;
        }

        /**
         * Setter du nom du catalogue de la table
         * @param catalogueName
         */
        public void setCatalogueName(String catalogueName) {
            this.catalogueName = catalogueName;
        }

        /**
         * Setter du shema de table associé
         * @param table
         */
        public void setTable(ShemaTableA table) {
            this.table = table;
        }
}
