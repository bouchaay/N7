import java.awt.Color;

/**
 * Cercle modélise un cercle dans un plan équipé d'un repère cartésien,
 * constitué d'un centre, un rayon et une couleur. Le cercle peut etre affiché,
 * translaté et modifié. De plus un cercle peut etre construit par
 * plusieurs manières.
 * 
 * @author Ayoub Bouchama <ayoub.bouchama@etu.toulouse-inp.fr>
 */

class Cercle implements Mesurable2D {

    private Point centre; // Centre du cercle
    private double rayon; // Rayon du cercle
    private Color couleur; // Couleur du cercle

    public static final double PI = Math.PI; // La constante PI

    /**
     * Constructeur qui crée un cercle a partir de son centre et son rayon.
     * Sa couleur est bleue par défaut.
     * 
     * @param centre Centre du cercle
     * @param rayon  Rayon du cercle
     */
    public Cercle(Point centre, double rayon) {
        assert centre != null : "le centre ne doit pas être nul";
        assert rayon > 0 : "le rayon doit être strictement positif";
        this.centre = new Point(centre.getX(), centre.getY());
        this.rayon = rayon;
        this.couleur = Color.BLUE;
    }

    /**
     * Constructeur de la classe Cercle qui crée un cercle a partir de deux points
     * diametralement opposés.
     * Sa couleur est bleue par défaut.
     * 
     * @param p1 Premier point
     * @param p2 Deuxieme point
     */
    public Cercle(Point p1, Point p2) {
        assert p1 != null : "p1 ne doit pas être nul";
        assert p2 != null : "p2 ne doit pas être nul";
        assert p1.distance(p2) > 0 : "p1 et p2 doivent être différent";
        this.centre = new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
        this.rayon = p1.distance(p2) / 2;
        this.couleur = Color.BLUE;
    }

    /**
     * Constructeur de la classe cercle qui crée un cercle à partir de deux points
     * diamétralement opposés et sa couleur
     * 
     * @param p1      Premier point
     * @param p2      Deuxieme point
     * @param couleur La couleur du cercle
     */
    public Cercle(Point p1, Point p2, Color couleur) {
        assert p1 != null : "p1 ne doit pas être nul";
        assert p2 != null : "p2 ne doit pas être nul";
        assert p1.distance(p2) > 0 : "p1 et p2 doivent être différent";
        assert couleur != null : "la couleur ne doit pas être nulle";
        this.centre = new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
        this.rayon = p1.distance(p2) / 2;
        this.couleur = couleur;
    }

    /**
     * Translater le cercle
     * 
     * @param dx Translation suivant x
     * @param dy Translation suivant y
     */
    public void translater(double dx, double dy) {
        this.centre.translater(dx, dy);
    }

    /**
     * Récuperer le centre du cercle
     * 
     * @return Centre du cercle
     */
    public Point getCentre() {
        return new Point(this.centre.getX(), this.centre.getY());
    }

    /**
     * Récuperer le rayon du cercle
     * 
     * @return Rayon du cercle
     */
    public double getRayon() {
        return this.rayon;
    }

    /**
     * Récuperer le diamétre du cercle
     * 
     * @return Diamètre dun cercle
     */
    public double getDiametre() {
        return this.rayon * 2;
    }

    /**
     * Savoir si un point est à l'intérieur
     * 
     * @param p Point à tester
     * @return True si le point est à l'intérieur du cercle
     */
    public boolean contient(Point p) {
        assert p != null : "p ne doit pas être nul";
        return this.centre.distance(p) <= this.rayon;
    }

    /**
     * Calculer le périmétre du cercle
     * 
     * @return Périmétre du cercle
     */
    public double perimetre() {
        return 2 * PI * this.rayon;
    }

    /**
     * Calculer l'aire du cercle
     * 
     * @return Aire du cercle
     */
    public double aire() {
        return PI * Math.pow(this.rayon, 2);
    }

    /**
     * Récupérer la couleur du cercle
     * 
     * @return Couleur du cercle
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Modifier la couleur du cercle
     * 
     * @param couleur La couleur a mettre
     */
    public void setCouleur(Color couleur) {
        assert couleur != null : "la couleur ne doit pas être nulle";
        this.couleur = couleur;
    }

    /**
     * Créer un cercle à partir d'un point constituant le centre et un autre formant le rayon entre eux deux
     * 
     * @param p1 Premier point
     * @param p2 Deuxieme point
     * @return Le cercle créé
     */
    public static Cercle creerCercle(Point p1, Point p2) {
        assert p1 != null : "p1 ne doit pas être nul";
        assert p2 != null : "p2 ne doit pas être nul";
        assert p1.distance(p2) > 0 : "p1 et p2 doivent être différent";
        Cercle cercle = new Cercle(p1, p1.distance(p2));
        return cercle;
    }

    /**
     * Afficher le cercle dans le terminal
     * 
     * @return L'affichage du cercle
     */
    public String toString() {
    	return "C" + this.rayon + "@(" + this.centre.getX() + ", " + this.centre.getY() + ")";
    }

    /**
     * Modifier le rayon du cercle
     * 
     * @param rayon Le rayon à mettre
     */
    public void setRayon(double rayon) {
        assert rayon > 0 : "le rayon doit être strictement positive";
        this.rayon = rayon;
    }

    /**
     * Modifier le diamètre du cercle
     * 
     * @param diametre Le diamètre à mettre
     */
    public void setDiametre(double diametre) {
        assert diametre > 0 : "le diamètre doit être strictement positive";
        this.rayon = diametre / 2;
    }
}
