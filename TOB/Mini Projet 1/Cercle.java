import java.awt.Color;

/**
 * La classe Cercle permet de modéliser un cercle dans un plan équipé d'un repère cartésien.
 * Un cercle est caractérisé par son centre, son rayon et sa couleur
 * La classe permet de créer des cercles en utilisant différentes méthodes, de les afficher,
 * de les translater et de récupérer différentes propriétés telles que
 * leur périmètre, leur aire, leur diamètre et leur couleur
 * Elle implémente l'interface Mesurable2D, qui définit les méthodes de calcul d'aire et de périmètre
 * @see Point
 * @see Mesurable2D
 * @author Ayoub Bouchama
 */
public class Cercle implements Mesurable2D {
    /** Le centre du cercle. */
	private Point centre;
    
	/** Le rayon du cercle. */
    private double rayon;
    
    /** La couleur du cercle. */
    private Color couleur;

    /** La constante pi. */
    public static final double PI = Math.PI;

    /** Construire un cercle à partir du centre et du rayon, de couleur est bleue par défaut.
     * @param centre Le centre du cercle
     * @param rayon  Le Rayon du cercle
     */
    public Cercle(Point centre, double rayon) {
        assert centre != null : "le centre ne doit pas être nul";
        assert rayon > 0 : "le rayon doit être strictement positif";
        this.centre = new Point(centre.getX(), centre.getY());
        this.rayon = rayon;
        this.couleur = Color.BLUE;
    }

    /** Construire un cercle à partir de deux points diamétralement opposés, de couleur bleue par défaut.
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
     * Construire un cercle à partir de deux points diamétralement opposés, de couleur bleue par défaut.
     * @param p1 Premier point
     * @param p2 Deuxieme point
     * @param couleur La couleur du cercle
     */
    public Cercle(Point p1, Point p2, Color couleur) {
    	this(p1, p2);
    	assert couleur != null : "la couleur ne doit pas être nulle";
        this.couleur = couleur;
    }

    /** Translater le cercle.
     * @param dx Translation suivant l'axe des X
     * @param dy Translation suivant l'axe des Y
     */
    public void translater(double dx, double dy) {
        this.centre.translater(dx, dy);
    }

    /** Récuperer le centre du cercle.
     * @return centre du cercle
     */
    public Point getCentre() {
        return new Point(this.centre.getX(), this.centre.getY());
    }

    /** Récuperer le rayon du cercle.
     * @return rayon du cercle
     */
    public double getRayon() {
        return this.rayon;
    }

    /** Récuperer le diamétre du cercle.
     * @return diamètre dun cercle
     */
    public double getDiametre() {
        return this.rayon * 2;
    }

    /** Vérifier si un point est à l'intérieur du cercle.
     * @param p Point à tester
     * @return True si le point est à l'intérieur du cercle
     */
    public boolean contient(Point p) {
        assert p != null : "p ne doit pas être nul";
        return this.centre.distance(p) <= this.rayon;
    }

    /** Calculer le périmétre du cercle.
     * @return périmétre du cercle
     */
    public double perimetre() {
        return 2 * PI * this.rayon;
    }

    /** Calculer l'aire du cercle.
     * @return aire du cercle
     */
    public double aire() {
        return PI * Math.pow(this.rayon, 2);
    }

    /** Récupérer la couleur du cercle.
     * @return couleur du cercle
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /** Modifier la couleur du cercle.
     * @param couleur La couleur à mettre
     */
    public void setCouleur(Color couleur) {
        assert couleur != null : "la couleur ne doit pas être nulle";
        this.couleur = couleur;
    }

    /** Créer un cercle à partir d'un point constituant le centre et un autre formant le rayon entre eux deux.
     * @param p1 Premier point
     * @param p2 Deuxieme point
     * @return cercle créé
     */
    public static Cercle creerCercle(Point p1, Point p2) {
        assert p1 != null : "p1 ne doit pas être nul";
        assert p2 != null : "p2 ne doit pas être nul";
        assert p1.distance(p2) > 0 : "p1 et p2 doivent être différent";
        Cercle cercle = new Cercle(p1, p1.distance(p2));
        return cercle;
    }

    /** Afficher le cercle.
     * @return affiche du cercle
     */
    public String toString() {
    	return "C" + this.rayon + "@" + centre.toString();
    }

    /** Modifier le rayon du cercle.
     * @param rayon Le rayon à mettre
     */
    public void setRayon(double rayon) {
        assert rayon > 0 : "le rayon doit être strictement positive";
        this.rayon = rayon;
    }

    /** Modifier le diamètre du cercle.
     * @param diametre diamètre à mettre
     */
    public void setDiametre(double diametre) {
        assert diametre > 0 : "le diamètre doit être strictement positive";
        this.rayon = diametre / 2;
    }
}
