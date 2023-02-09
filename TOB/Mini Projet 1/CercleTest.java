import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.awt.Color;

/** Classe de test des contructeurs de la classe Cercle.
  * 
  @author Ayoub Bouchama <ayoub.bouchama@etu.toulouse-inp.fr>
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
	
	/** Initialiser les variables de test */
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
	
	/** Tester le premier constructeur de la classe cercle qui construit un cercle à partir
	 * de son centre et de son rayon
	 */
	@Test public void testConstructeurAvecCentreEtRayon() {

		// Tester la validité du 1er constructeur de la classe Cercle

		assertEquals(centre, C1.getCentre());
        assertEquals(rayon, C1.getRayon(), EPSILON);
        assertEquals(Color.BLUE, C1.getCouleur());
    }

	/** Tester le deuxième constructeur de la classe cercle qui construit un cercle à partir
	 * de deux points diamètralement opposés appartenant au cercle
	 */
    @Test public void testConstructeurAvecDeuxPointsDiametralementOpposes() {
        
		// Initialiser les variables de test
		
		Point centreAttendu = new Point(5, 0);
        double rayonAttendu = 5;

		// Tester la validité du 2ème constructeur de la classe Cercle

        assertEquals(centreAttendu.getX(), C2.getCentre().getX(), EPSILON);
        assertEquals(centreAttendu.getY(), C2.getCentre().getY(), EPSILON);
        assertEquals(rayonAttendu, C2.getRayon(), EPSILON);
        assertEquals(Color.BLUE, C2.getCouleur());
    }

	/** Tester le deuxième constructeur de la classe cercle qui construit un cercle à partir
	 * de deux points diamètralement opposés appartenant au cercle et de sa couleur
	 */
    @Test public void testConstructeurAvecDeuxPointsDiametralementOpposesEtCouleur() {
        
		// Initialiser les variables de test.

		Point centreAttendu = new Point(5, 0);
        double rayonAttendu = 5;
        Color couleurAttendu = couleur;
        
		// Tests de la validité du 3ème constructeur de la classe Cercle
		assertEquals(centreAttendu.getX(), C3.getCentre().getX(), EPSILON);
		assertEquals(centreAttendu.getY(), C3.getCentre().getY(), EPSILON);
        assertEquals(rayonAttendu, C3.getRayon(), EPSILON);
        assertEquals(couleurAttendu, C3.getCouleur());
    }
}
