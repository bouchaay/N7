import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;

/**
 * Classe de test des contructeurs de la classe Cercle.
 * 
 * @author Ayoub Bouchama <ayoub.bouchama@etu.toulouse-inp.fr>
 */

public class CercleTest {

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

    	// rayon du cercle
    	public static final double R = 5;

	// Points de test
	private Point A, p1, p2, p3, p4;

	// Cercles de test
	private Cercle C1, C2, C3;

	public void memescoordonnees(Point p1, Point p2) {
		assertEquals("Les coordonnées du point ne sont pas les mêmes", p1.getX(), p2.getX(), EPSILON);
		assertEquals("Les coordonnées du point ne sont pas les mêmes", p1.getY(), p2.getY(), EPSILON);
	}

    @Before
    public void setUp() {
       
        A = new Point(0, 0);
        p1 = new Point(1, 0);
        p2 = new Point(-3, 4);
        p3 = new Point(0, 2);
        p4 = new Point(8, 8);

        C1 = new Cercle(A, R);
        C2 = new Cercle(p1, p2);
        C3 = new Cercle(p3, p4, Color.RED);
    }
	
    /** Test du constructeur Cercle(Point centre, double rayon). */
	@Test
	public void E11() {		
	memescoordonnees(C1.getCentre(), new Point(0, 0));
        assertEquals("Le rayon du cercle n'est pas le même", C1.getRayon(), 5, EPSILON);
        assertEquals("La couleur du cercle n'est pas la même", C1.getCouleur(), Color.BLUE);		
	}

    /** Test du constructeur Cercle(Point p1, Point p2). */
	@Test
	public void E12() {
	memescoordonnees(C2.getCentre(), new Point(-1, 2));
        assertEquals("La couleur du cercle n'est pas la même", C2.getCouleur(), Color.BLUE);
        assertEquals("Le rayon du cercle n'est pas le même", C2.getRayon(), p1.distance(p2)/2, EPSILON);	
	}

    /** Test du constructeur Cercle(Point p1, Point p2, Color couleur). */
	@Test
	public void E13() {
        memescoordonnees(C3.getCentre(), new Point(4, 5));
        assertEquals("La couleur du cercle n'est pas la même", C3.getCouleur(), Color.RED);
        assertEquals("Le rayon du cercle n'est pas le même", C3.getRayon(), p3.distance(p4)/2, EPSILON);
	}
}
