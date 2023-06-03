package panels;
import java.awt.Color;

/** Classe de conversion de String en Color. */
public class ColorConverter {

    /** Couleur en String. */
    String color;

    /** Constructeur.
     * 
     * @param color Couleur en String
     */
    public ColorConverter(String color) {
        this.color = color;
    }

    /** Retourne la couleur en String.
     * 
     * @return Couleur en String
     */
    public String getColor() {
        return this.color;
    }

    /** Retourne la couleur en Color.
     * 
     * @return Couleur en Color
     */
    public Color convertirColor() {
        switch (this.color) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
            case "orange":
                return Color.ORANGE;
            case "pink":
                return Color.PINK;
            case "cyan":
                return Color.CYAN;
            case "magenta":
                return Color.MAGENTA;
            case "gray":
                return Color.GRAY;
            case "lightGray":
                return Color.LIGHT_GRAY;
            case "darkGray":
                return Color.DARK_GRAY;
            case "white":
                return Color.WHITE;
            case "black":
                return Color.BLACK;
            case "mint":
                return Color.decode("#BDFCC9");
            case "transparent":
                return Color.decode("#0B0B3B");
            default:
                return Color.decode(color);
        }
    }
}
