package analyse;
import java.util.regex.*;
import java.util.ArrayList;
 
/** La classe findMatch permet de trouver toutes les occurences d'un motif.
 * Elle prend en paramètre un motif et une chaine de caractères dans laquelle chercher le motif.
 */
public class findMatch {

    /** Liste de Tuple (x = début du motif, y = fin du motif). */
    private ArrayList<Tuple> matches;
    
    /**
     * Trouve toutes les occurences d'un motif dans une chaine de caractères et le 
     * stocke dans une liste de Tuple (x = début du motif, y = fin du motif).
     * 
     * Exemple d'utilisation :
     *  ArrayList<Tuple> matches = fm.getMatches();
        for (Tuple match : matches) {
            System.out.println(match.x + " " + match.y);
        }
    
     * @param regex motif à chercher
     * @param input chaine de caractères dans laquelle chercher le motif
     */
    public findMatch(String regex, String input) {
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            matches = new ArrayList<Tuple>();
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();   
                matches.add(new Tuple(start, end));
            }
        } catch (PatternSyntaxException e) {
            throw new PatternSyntaxException(input, regex, e.getIndex());
        }
    }

    /** Retourne la liste de Tuple (x = début du motif, y = fin du motif).
     * @return la liste de Tuple (x = début du motif, y = fin du motif).
    */
    public ArrayList<Tuple> getMatches() {
        return matches;
    }
}
