import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class MainPy {

    public MainPy() {
        super();
        System.out.println("Hello World");
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        // Créer la liste de listes en Java
        List<List<Integer>> inputList = new ArrayList<>();
        inputList.add(List.of(1, 2, 3));
        inputList.add(List.of(4, 5, 6));
        inputList.add(List.of(7, 8, 9));

        // Convertir la liste Java en JSON
        String inputJson = toJSON(inputList);

        // Exécuter le script Python en tant que processus externe
        Process process = Runtime.getRuntime().exec("python script.py");

        // Obtenir le flux de sortie du processus Python
        OutputStream outputStream = process.getOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        writer.write(inputJson);
        writer.close();

        // Lire la sortie du processus Python
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String outputJson = reader.readLine();

        // Convertir la sortie JSON en liste Java
        List<List<Integer>> outputList = fromJSON(outputJson);

        // Afficher la liste de sortie en Java
        System.out.println(outputList);

        // Attendre la fin du processus Python
        process.waitFor();
    }

    private static String toJSON(List<List<Integer>> list) {
        // Convertir la liste Java en JSON
        // Vous pouvez utiliser une bibliothèque JSON comme Jackson ou Gson pour cela
        // Dans cet exemple, nous utilisons une simple concaténation de chaînes
        StringBuilder json = new StringBuilder("[");
        for (List<Integer> sublist : list) {
            json.append("[");
            for (Integer value : sublist) {
                json.append(value).append(",");
            }
            json.deleteCharAt(json.length() - 1); // Supprimer la virgule finale
            json.append("],");
        }
        json.deleteCharAt(json.length() - 1); // Supprimer la virgule finale
        json.append("]");
        return json.toString();
    }

    private static List<List<Integer>> fromJSON(String json) {
        // Convertir le JSON en liste Java
        // Vous pouvez utiliser une bibliothèque JSON comme Jackson ou Gson pour cela
        // Dans cet exemple, nous utilisons une conversion manuelle
        List<List<Integer>> result = new ArrayList<>();
        String[] rows = json.substring(1, json.length() - 1).split("\\],\\[");
        for (String row : rows) {
            String[] values = row.split(",");
            List<Integer> sublist = new ArrayList<>();
            for (String value : values) {
                sublist.add(Integer.parseInt(value));
            }
            result.add(sublist);
        }
        return result;
    }
}
