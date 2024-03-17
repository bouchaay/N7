package Ressource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.charset.StandardCharsets;

import ShemaTableElements.*;

public class OperationA {
    private ShemaTableA table;

    public OperationA(ShemaTableA table) {
        this.table = table;
    }

    public ShemaTableA getTable() {
        return table;
    }

    public List<String> getColInNames (int indiceAlgo){
        AlgorithmA algo = table.getCatalogue().getAlgorithms().get(indiceAlgo);
        InputA input = algo.getInput();
        List<String> colInNames = new ArrayList<String>();
        List<ColumnA<?>> colIn = input.getColumns();
        for (ColumnA<?> col : colIn) {
            colInNames.add(col.getName());
        }
        return colInNames;
    }

    public List<List<?>> getColInValues (int indiceAlgo){
        List<List<?>> colInValues = new ArrayList<>();
        List<String> colInNames = getColInNames(indiceAlgo);
        for (String colName : colInNames) {
            ColumnA<?> col = table.getColumnByName(colName);
            if (col != null) {
                colInValues.add(col.getValues());
            }
        }
        return colInValues;
    }

    /*public List<List<?>> columnsToLignes (int indiceAlgo){
        List<List<?>> colInValues = getColInValues(indiceAlgo);
        List<List<?>> lignes = new ArrayList<>();
        int nbCol = colInValues.size();
        int nbLignes = colInValues.get(0).size();
        for (int i = 0; i < nbLignes; i++) {
            List<Object> ligne = new ArrayList<>();
            for (int j = 0; j < nbCol; j++) {
                ligne.add(colInValues.get(j).get(i));
            }
            lignes.add(ligne);
        }
        return lignes;   
    } */

    public String excOperation(int indiceAlgo) {
        List<List<?>> colInValues = getColInValues(indiceAlgo);
        String nameAlg = table.getCatalogue().getAlgorithms().get(indiceAlgo).getName();
        System.out.println("colInValues : " + colInValues);
        System.out.println("nameAlg : " + nameAlg);

        try {
            // Définir l'URL de votre serveur Flask
            String url = "http://localhost:5001/operation";

            // Créer l'objet URL
            URL obj = new URL(url);

            // Ouvrir une connexion HttpURLConnection
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configurer la connexion pour une requête POST
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Activer l'envoi de données
            con.setDoOutput(true);

            // Construire le corps de la requête JSON
           // String jsonInputString = "{\"Colonne\": " + colInValues + ", \"nomAlgorithme\": " + nameAlg + "}";

            // Construire les données JSON à envoyer manuellement
            String jsonInputString = "{\"Colonne\":[";
            for (List<?> col : colInValues) {
                jsonInputString += "[";
                for (Object value : col) {
                    jsonInputString += "\"" + value.toString() + "\",";
                }
                jsonInputString = jsonInputString.substring(0, jsonInputString.length() - 1); // Supprimer la dernière virgule
                jsonInputString += "],";
            }
            if (!colInValues.isEmpty()) {
                jsonInputString = jsonInputString.substring(0, jsonInputString.length() - 1); // Supprimer la dernière virgule
            }
            jsonInputString += "], \"nomAlgorithme\":\"" + nameAlg + "\"}";
            System.out.println("jsonInputString : " + jsonInputString);

            // Envoyer les données JSON dans le corps de la requête
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(jsonInputString);
                wr.flush();
            }

            // Récupérer la réponse du serveur
            int responseCode = con.getResponseCode();
            System.out.println("Code de réponse : " + responseCode);

            // Lire la réponse du serveur
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Afficher la réponse du serveur
                System.out.println("Réponse du serveur : " + response.toString());

                // Utiliser la réponse comme nécessaire dans votre application Java
                // Par exemple, vous pouvez convertir la chaîne en un type approprié
                // (par exemple, double) si c'est ce que vous attendez
                //double resultValue = Double.parseDouble(response.toString());
                //System.out.println("Valeur résultante : " + resultValue);
                return response.toString();
            }
        } catch (Exception e) {
            return null;
        }
    }
    
}
