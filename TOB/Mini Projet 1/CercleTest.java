import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.awt.Color;

/** Classe de test des contructeurs de la classe Cercle.
  * 
  @author Ayoub Bouchama
  */

public class CercleTest {

		// précision pour les comparaisons réelle
		public final static double EPSILON = 0.001;
	
		// Les points du sujet
		private Point centre, p1, p2;

		// Les cercles du sujet
		private Cercle C1, C2, C3;

		// Le rayon du sujet	
		double rayon;
		
		// La couleur du sujet	
		Color couleur;
	
/** Initialiser les variables de test. */
@Before public void setUp() {
			
	// Construire les points
	centre 	= 	new Point(0, 0);
	p1 		= 	new Point(0, 0);
	p2 		= 	new Point(10, 0);
			
	// Initialiser le rayon
	rayon 	= 	5;
		
	// Initialiser la couleur
	couleur = 	Color.RED;
			
	// Construire les cercles
	C1 		= 	new Cercle(centre, rayon);
	C2 		= 	new Cercle(p1, p2);
	C3 		= 	new Cercle(p1, p2, couleur);			
}
	
/** Vérifier si deux points ont mêmes coordonnées.
* @param p1 le premier point
* @param p2 le deuxième point
*/
static void memesCoordonnees(String message, Point p1, Point p2) {
	assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
	assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
}
	
/** Tester le constructeur du cercle à partir de son centre et de son rayon. */
@Test public void testE11() {

	// Tester la validité du 1er constructeur de la classe Cercle
	memesCoordonnees("Constructeur E11 incorrecte", centre, C1.getCentre());
	assertEquals(rayon, C1.getRayon(), EPSILON);
        assertEquals(Color.BLUE, C1.getCouleur());
    }

/** Tester le constructeur du cercle à partir de deux points diamètralement opposés appartenant au cercle. */
@Test public void testE12() {
        
	// Initialiser les variables de test		
	Point centreAttendu = new Point(5, 0);
	double rayonAttendu = 5;

	// Tester la validité du 2ème constructeur de la classe Cercle
        memesCoordonnees("Constructeur E12 incorrecte", centreAttendu, C2.getCentre());
        assertEquals(rayonAttendu, C2.getRayon(), EPSILON);
        assertEquals(Color.BLUE, C2.getCouleur());
}

/** Tester le constructeur du cercle à partir de deux points diamètralement opposés appartenant au cercle et de sa couleur. */
@Test public void testE13() {
        
	// Initialiser les variables de test.

	Point centreAttendu = new Point(5, 0);
        double rayonAttendu = 5;
        Color couleurAttendu = couleur;
        
	// Tests de la validité du 3ème constructeur de la classe Cercle
	memesCoordonnees("Constructeur E13 incorrecte", centreAttendu, C3.getCentre());
	assertEquals(rayonAttendu, C3.getRayon(), EPSILON);
        assertEquals(couleurAttendu, C3.getCouleur());
    }
}
