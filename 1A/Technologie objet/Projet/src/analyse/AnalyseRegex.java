package analyse;
import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.*;
import javax.swing.JTextPane;
import javax.swing.text.*;
import panels.*;
import construction.*;

/** Classe pour l'analyse d'une expression régulière. */
public class AnalyseRegex {

    /** Constructeur. */
    public AnalyseRegex() {
    }

    /**
     * Vérifier si une expression régulière est valide.
     * 
     * @param regex Expression régulière
     * @return Vrai si l'expression régulière est valide, faux sinon
     * @throws PatternSyntaxException
     */
    public static boolean regexValide(String regex) {
        try {
            Pattern.compile(regex);
            return true;
        } catch (PatternSyntaxException e) {
            throw new PatternSyntaxException(regex, regex, 0);
        }
    }

    private static String generateID() {
        // TODO
        return "id";
    }

    public static AbstractBlock analyser(String regex) {
        regexValide(regex);

        if (regex.startsWith("(")) {
            // TODO matcher: (?:), (?<name>)
            int i = regex.lastIndexOf(")");

            if (regex.charAt(1) == '?') {
                if (regex.charAt(2) == ':') {
                    return new BlockCaptureGroup(generateID(), false, regex.substring(3, i));
                }
                if (regex.charAt(2) == '=') {
                    return new BlockLookahead(generateID(), false, regex.substring(2, i));
                }

                if (regex.charAt(2) == '<') {
                    if (regex.charAt(3) == '=') {
                        return new BlockLookbehind(generateID(), false, regex.substring(3, i));
                    }
                    int j = regex.lastIndexOf(">");
                    String captureReferenceName = regex.substring(3, j);
                    return new BlockCaptureGroup(generateID(), false, regex.substring(j, i));
                }
            }
            return new BlockCaptureGroup(generateID(), false, regex.substring(1, i));
        }

        if (regex.startsWith("[")) {
            int i = regex.lastIndexOf("]");
            return new BlockCharacterRange(generateID(), false, regex.substring(1, i));
        }

        // TODO handle lazy * and +

        if (regex.endsWith("*")) {
            return new BlockZeroOrMore(generateID(), false, false);
        }

        if (regex.endsWith("+")) {
            return new BlockOneOrMore(generateID(), false, false);
        }

        if (regex.endsWith("?")) {
            return new BlockOptional(generateID(), false, false);
        }

        return new BlockLiteral(generateID(), false, regex);
    }

    /**
     * Retourner la listes des différents expressions régulières simples contenues
     * dans une expression régulière.
     * 
     * @param regex Expression régulière
     * @return Liste des expressions régulières
     */
    public static ArrayList<String> getComposantes(String regex) {
        ArrayList<String> composantes = new ArrayList<String>();
        String regexSimple = "";
        int i = 0;
        while (i < regex.length()) {
            if (regex.charAt(i) == '[') {
                regexSimple = regexSimple + regex.charAt(i);
                i++;
                while (regex.charAt(i) != ']') {
                    regexSimple = regexSimple + regex.charAt(i);
                    i++;
                }
                regexSimple = regexSimple + regex.charAt(i);
                composantes.add(regexSimple);
                regexSimple = "";
                i++;
            } else if (regex.charAt(i) == '(') {
                regexSimple = regexSimple + regex.charAt(i);
                i++;
                while (regex.charAt(i) != ')') {
                    regexSimple = regexSimple + regex.charAt(i);
                    i++;
                }
                regexSimple = regexSimple + regex.charAt(i);
                composantes.add(regexSimple);
                regexSimple = "";
                i++;
            } else if (regex.charAt(i) == '{') {
                regexSimple = regexSimple + regex.charAt(i);
                i++;
                while (regex.charAt(i) != '}') {
                    regexSimple = regexSimple + regex.charAt(i);
                    i++;
                }
                regexSimple = regexSimple + regex.charAt(i);
                composantes.add(regexSimple);
                regexSimple = "";
                i++;
            } else if (regex.charAt(i) == '\\') {
                regexSimple = regexSimple + regex.charAt(i);
                i++;
                regexSimple = regexSimple + regex.charAt(i);
                composantes.add(regexSimple);
                regexSimple = "";
                i++;
            } else if ((regex.charAt(i) == '.') || (regex.charAt(i) == '*') || (regex.charAt(i) == '+')
                    || (regex.charAt(i) == '?') || (regex.charAt(i) == '|')) {
                regexSimple = regexSimple + regex.charAt(i);
                composantes.add(regexSimple);
                regexSimple = "";
                i++;
            } else {
                while (i < regex.length() && regex.charAt(i) != '[' && regex.charAt(i) != '(' && regex.charAt(i) != '{'
                        && regex.charAt(i) != '\\' && regex.charAt(i) != '.' && regex.charAt(i) != '*'
                        && regex.charAt(i) != '+' && regex.charAt(i) != '?' && regex.charAt(i) != '|') {
                    regexSimple = regexSimple + regex.charAt(i);
                    i++;
                }
                composantes.add(regexSimple);
                regexSimple = "";
            }
        }
        return composantes;
    }

    /**
     * Retourner la liste des couleurs.
     * 
     * @return Liste des couleurs
     */
    public static ArrayList<String> tableCouleur() {
        ArrayList<String> couleurs = new ArrayList<String>();
        couleurs.add("red");
        couleurs.add("blue");
        couleurs.add("orange");
        couleurs.add("green");
        couleurs.add("yellow");
        couleurs.add("pink");
        couleurs.add("cyan");
        couleurs.add("magenta");
        couleurs.add("gray");
        couleurs.add("black");
        return couleurs;
    }

    /**
     * Colorer une expression régulière.
     * 
     * @param regex    Expression régulière
     * @param textPane JTextPane
     * @throws BadLocationException
     */
    public static void colorerRegex(String regex, JTextPane textPane) throws BadLocationException {
        ArrayList<String> composantes = getComposantes(regex);
        ArrayList<String> couleurs = tableCouleur();
        for (int i = 0; i < composantes.size(); i++) {
            ColorConverter colorConverter = new ColorConverter(couleurs.get(i % couleurs.size()));
            Color color = colorConverter.convertirColor();
            StyleContext styleContext = StyleContext.getDefaultStyleContext();
            AttributeSet attributes = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                    color);
            int offset = textPane.getCaretPosition();
            textPane.getDocument().insertString(offset, composantes.get(i), attributes);
        }
    }
}
