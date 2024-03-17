package Main;
import ShemaTableElements.*;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel {

	List<ShemaTableA> lesTables; 
	public UserTableModel() {

			this.lesTables = new ArrayList<>();
			// Catalogue de la table
			CatalogueA catalogue_1 = new CatalogueA("IFT_CAT");
			
			// La table d'id 1
			ShemaTableA table_1 = new ShemaTableA("IFT", 1, catalogue_1);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_1_1 =  new ConstraintA("");

			// Création de la colonne courante d'id 1
			ColumnA<String> currentColumn_1_1 = new ColumnA<String>("Date", 1, ColumnDataTypeA.STRING, contraintes_1_1);

			// Ajouter la colonne d'id 1 à la table
			table_1.addColumn(currentColumn_1_1);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_1_2 =  new ConstraintA("");

			// Création de la colonne courante d'id 2
			ColumnA<Float> currentColumn_1_2 = new ColumnA<Float>("Open", 2, ColumnDataTypeA.FLOAT, contraintes_1_2);

			// Ajouter la colonne d'id 2 à la table
			table_1.addColumn(currentColumn_1_2);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_1_3 =  new ConstraintA("");

			// Création de la colonne courante d'id 3
			ColumnA<Float> currentColumn_1_3 = new ColumnA<Float>("High", 3, ColumnDataTypeA.FLOAT, contraintes_1_3);

			// Ajouter la colonne d'id 3 à la table
			table_1.addColumn(currentColumn_1_3);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_1_4 =  new ConstraintA("");

			// Création de la colonne courante d'id 4
			ColumnA<Float> currentColumn_1_4 = new ColumnA<Float>("Low", 4, ColumnDataTypeA.FLOAT, contraintes_1_4);

			// Ajouter la colonne d'id 4 à la table
			table_1.addColumn(currentColumn_1_4);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_1_5 =  new ConstraintA("");

			// Création de la colonne courante d'id 5
			ColumnA<Float> currentColumn_1_5 = new ColumnA<Float>("Close", 5, ColumnDataTypeA.FLOAT, contraintes_1_5);

			// Ajouter la colonne d'id 5 à la table
			table_1.addColumn(currentColumn_1_5);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_1_6 =  new ConstraintA("");

			// Création de la colonne courante d'id 6
			ColumnA<Float> currentColumn_1_6 = new ColumnA<Float>("Adj Close", 6, ColumnDataTypeA.FLOAT, contraintes_1_6);

			// Ajouter la colonne d'id 6 à la table
			table_1.addColumn(currentColumn_1_6);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_1_7 =  new ConstraintA("");

			// Création de la colonne courante d'id 7
			ColumnA<Integer> currentColumn_1_7 = new ColumnA<Integer>("Volume", 7, ColumnDataTypeA.INTEGER, contraintes_1_7);

			// Ajouter la colonne d'id 7 à la table
			table_1.addColumn(currentColumn_1_7);
			// Ajouter la table à la liste des tables
			lesTables.add(table_1);
			// Catalogue de la table
			CatalogueA catalogue_2 = new CatalogueA("OFT_CAT");

			// L'input de l'algorithme actuel
			InputA input_avg_2 = new InputA();

			// Les colonnes de l'input
			ConstraintA currentColConstr_avg_2_3 = new ConstraintA("");
			ColumnA<Float> currentColumn_avg_2_3 = new ColumnA<Float>("Min", 3, ColumnDataTypeA.FLOAT, currentColConstr_avg_2_3);
			input_avg_2.addColumnToInput(currentColumn_avg_2_3);
			ConstraintA currentColConstr_avg_2_4 = new ConstraintA("");
			ColumnA<Float> currentColumn_avg_2_4 = new ColumnA<Float>("Max", 4, ColumnDataTypeA.FLOAT, currentColConstr_avg_2_4);
			input_avg_2.addColumnToInput(currentColumn_avg_2_4);

			// Créer l'algorithme
			AlgorithmA currentAlgo_avg_2 = new AlgorithmA(input_avg_2, "avg", "/Ressource/avg.py");
			catalogue_2.addAlgorithm(currentAlgo_avg_2);

			// L'input de l'algorithme actuel
			InputA input_sub_2 = new InputA();

			// Les colonnes de l'input
			ConstraintA currentColConstr_sub_2_5 = new ConstraintA("");
			ColumnA<Float> currentColumn_sub_2_5 = new ColumnA<Float>("Fermeture", 5, ColumnDataTypeA.FLOAT, currentColConstr_sub_2_5);
			input_sub_2.addColumnToInput(currentColumn_sub_2_5);
			ConstraintA currentColConstr_sub_2_2 = new ConstraintA("");
			ColumnA<Float> currentColumn_sub_2_2 = new ColumnA<Float>("Ouverture", 2, ColumnDataTypeA.FLOAT, currentColConstr_sub_2_2);
			input_sub_2.addColumnToInput(currentColumn_sub_2_2);

			// Créer l'algorithme
			AlgorithmA currentAlgo_sub_2 = new AlgorithmA(input_sub_2, "sub", "/Ressource/sub.py");
			catalogue_2.addAlgorithm(currentAlgo_sub_2);
			
			// La table d'id 2
			ShemaTableA table_2 = new ShemaTableA("OFT", 2, catalogue_2);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_1 =  new ConstraintA("");

			// Création de la colonne courante d'id 1
			ColumnA<String> currentColumn_2_1 = new ColumnA<String>("Date", 1, ColumnDataTypeA.STRING, contraintes_2_1);

			// Ajouter la colonne d'id 1 à la table
			table_2.addColumn(currentColumn_2_1);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_2 =  new ConstraintA("");

			// Création de la colonne courante d'id 2
			ColumnA<Float> currentColumn_2_2 = new ColumnA<Float>("Ouverture", 2, ColumnDataTypeA.FLOAT, contraintes_2_2);

			// Ajouter la colonne d'id 2 à la table
			table_2.addColumn(currentColumn_2_2);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_3 =  new ConstraintA("");

			// Création de la colonne courante d'id 3
			ColumnA<Float> currentColumn_2_3 = new ColumnA<Float>("Min", 3, ColumnDataTypeA.FLOAT, contraintes_2_3);

			// Ajouter la colonne d'id 3 à la table
			table_2.addColumn(currentColumn_2_3);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_4 =  new ConstraintA("");

			// Création de la colonne courante d'id 4
			ColumnA<Float> currentColumn_2_4 = new ColumnA<Float>("Max", 4, ColumnDataTypeA.FLOAT, contraintes_2_4);

			// Ajouter la colonne d'id 4 à la table
			table_2.addColumn(currentColumn_2_4);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_5 =  new ConstraintA("");

			// Création de la colonne courante d'id 5
			ColumnA<Float> currentColumn_2_5 = new ColumnA<Float>("Fermeture", 5, ColumnDataTypeA.FLOAT, contraintes_2_5);

			// Ajouter la colonne d'id 5 à la table
			table_2.addColumn(currentColumn_2_5);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_6 =  new ConstraintA("");

			// Création de la colonne courante d'id 6
			ColumnA<Integer> currentColumn_2_6 = new ColumnA<Integer>("Volume", 6, ColumnDataTypeA.INTEGER, contraintes_2_6);

			// Ajouter la colonne d'id 6 à la table
			table_2.addColumn(currentColumn_2_6);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_7 =  new ConstraintA("");

			// Création de la colonne courante d'id 7
			ColumnA<Float> currentColumn_2_7 = new ColumnA<Float>("Moyenne", 7, ColumnDataTypeA.INTEGER, contraintes_2_7);

			// Ajouter la colonne d'id 7 à la table
			table_2.addColumn(currentColumn_2_7);

			// Fichier des contraintes de la colonne courante
			ConstraintA contraintes_2_8 =  new ConstraintA("");

			// Création de la colonne courante d'id 8
			ColumnA<Float> currentColumn_2_8 = new ColumnA<Float>("Outputavg", 8, ColumnDataTypeA.INTEGER, contraintes_2_8);

			// Ajouter la colonne d'id 8 à la table
			table_2.addColumn(currentColumn_2_8);
			// Ajouter la table à la liste des tables
			lesTables.add(table_2);
	}

	public void setTables(List<ShemaTableA> tabs) {
		this.lesTables = tabs;
	}

	public List<ShemaTableA> getTables() {
		return this.lesTables;
	}
}

