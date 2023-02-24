import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Color;

/**
 * Classe de test des contructeurs de la classe Cercle.
 * 
 * @author Ayoub Bouchama <ayoub.bouchama@etu.toulouse-inp.fr>
 */

public class CercleTest {

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	// Points de test
	private Point p1, p2;

	// Cercles de test
	private Cercle C1, C2;

	public void memescoordonnees(Point p1, Point p2) {
		assertEquals("Les coordonnées du point ne sont pas les mêmes", p1.getX(), p2.getX(), EPSILON);
		assertEquals("Les coordonnées du point ne sont pas les mêmes", p1.getY(), p2.getY(), EPSILON);
	}

	// Test utilisant différents points pour chaque cercle

	@Test
	public void E11() {
		p1 = new Point(1, 2);
		p2 = new Point(3, 4);
		C1 = new Cercle(p1, 5);
		C2 = new Cercle(p2, 6);
		memescoordonnees(C1.getCentre(), p1);
		memescoordonnees(C2.getCentre(), p2);
		assertEquals("Le rayon du cercle n'est pas le même", C1.getRayon(), 5, EPSILON);
		assertEquals("Le rayon du cercle n'est pas le même", C2.getRayon(), 6, EPSILON);
		try {
			C1 = new Cercle(p1, -5);
			fail("Le rayon du cercle ne peut pas être négatif");
		} catch (AssertionError e) {
			// OK
		}
		try {
			C1 = new Cercle(p1, 0);
			fail("Le rayon du cercle ne peut pas être nul");
		} catch (AssertionError e) {
			// OK
		}
		try {
			C1 = new Cercle(null, 5);
			fail("Le centre du cercle ne peut pas être null");
		} catch (AssertionError e) {
			// OK
		}
	}

	@Test
	public void E12() {
		p1 = new Point(1, 2);
		p2 = new Point(3, 4);
		C1 = new Cercle(p1, p2);
		C2 = new Cercle(p2, p1);
		memescoordonnees(C1.getCentre(), new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2));
		memescoordonnees(C2.getCentre(), new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2));
		assertEquals("Le rayon du cercle n'est pas le même", C1.getRayon(), p1.distance(p2)/2, EPSILON);
		assertEquals("Le rayon du cercle n'est pas le même", C2.getRayon(), p1.distance(p2)/2, EPSILON);
		try {
			C1 = new Cercle(p1, null);
			fail("Le centre du cercle ne peut pas être null");
		} catch (AssertionError e) {
			// OK
		}
		try {
			C1 = new Cercle(null, p1);
			fail("Le centre du cercle ne peut pas être null");
		} catch (AssertionError e) {
			// OK
		}
		try {
			C1 = new Cercle(p1, p1);
			fail("Le rayon du cercle ne peut pas être nul");
		} catch (AssertionError e) {
			// OK
		}
	}

	@Test
	public void E13() {
		p1 = new Point(1, 2);
		p2 = new Point(3, 4);
		C1 = new Cercle(p1, p2, Color.RED);
		C2 = new Cercle(p2, p1, Color.BLUE);
		memescoordonnees(C1.getCentre(), new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2));
		memescoordonnees(C2.getCentre(), new Point((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2));
		assertEquals("Le rayon du cercle n'est pas le même", C1.getRayon(), p1.distance(p2)/2, EPSILON);
		assertEquals("Le rayon du cercle n'est pas le même", C2.getRayon(), p1.distance(p2)/2, EPSILON);
		assertEquals("La couleur du cercle n'est pas la même", C1.getCouleur(), Color.RED);
		assertEquals("La couleur du cercle n'est pas la même", C2.getCouleur(), Color.BLUE);
		try {
			C1 = new Cercle(p1, null, Color.RED);
			fail("Le centre du cercle ne peut pas être null");
		} catch (AssertionError e) {
			// OK
		}
		try {
			C1 = new Cercle(null, p1, Color.RED);
			fail("Le centre du cercle ne peut pas être null");
		} catch (AssertionError e) {
			// OK
		}
		try {
			C1 = new Cercle(p1, p1, Color.RED);
			fail("Le rayon du cercle ne peut pas être nul");
		} catch (AssertionError e) {
			// OK
		}
		try {
			C1 = new Cercle(p1, p2, null);
			fail("La couleur du cercle ne peut pas être null");
		} catch (AssertionError e) {
			// OK
		}
	}

}
