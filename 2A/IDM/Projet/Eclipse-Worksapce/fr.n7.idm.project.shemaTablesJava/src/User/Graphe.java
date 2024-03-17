package User;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Graphe {
    private List<?> x;
    private List<?> y;
    private int typeGraphique;

    public Graphe(List<?> x, List<?> y, int typeGraphique) {
        this.x = x;
        this.y = y;
        this.typeGraphique = typeGraphique;
    }

    public void creerGraphe() {
        // Appeler l'API Python pour tracer le graphique
        String cheminFichier = appelerAPIPython(this.x, this.y, this.typeGraphique);

        // À ce stade, vous pouvez utiliser le chemin du fichier pour afficher le graphique dans votre application Java
        System.out.println("Chemin du fichier du graphique : " + cheminFichier);
    }


    private static String appelerAPIPython(List<?> x, List<?> y, int typeGraphique) {
        try {
            // Construire l'URL de l'API Python
            String url = "http://localhost:5000/tracer_graphique";
            URL obj = new URL(url);

            

            // Créer la connexion
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configurer la requête HTTP POST
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Construire les données JSON à envoyer
            String jsonInputString = "{\"x\": " + x + ", \"y\": " + y + ", \"type_graphique\": " + typeGraphique + "}";

            // Envoyer les données
            con.getOutputStream().write(jsonInputString.getBytes("UTF-8"));

            // Lire la réponse de l'API Python
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Retourner le chemin du fichier du graphique
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}