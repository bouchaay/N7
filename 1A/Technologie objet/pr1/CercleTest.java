import org.junit.Test;
import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Before;

/**
 * Classe de test des contructeurs de la classe Cercle.
 * 
 * @author Ayoub Bouchama <ayoub.bouchama@etu.inp-n7.fr>
 */

public class CercleTest {

	// La précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	// Les rayons du cercle
	public static final double R1 = 2;
	public static final double R2 = 5;

	// Les points du test
	private Point A, B, C, D, E, F, G;

	// Les cercles du test
	private Cercle C0, C1, C2, C3, C4;

	@Before
	public void setPoint() {
		A = new Point(0, 0);
		B = new Point(2, 0);
		C = new Point(-2, 0);
		D = new Point(0, -2);
		E = new Point(0, 2);
		F = new Point(-3, 0);
		G = new Point(7, 0);

		C0 = new Cercle(A, R1); // Cercle crée par E
		C1 = new Cercle(C, B); // Cercle crée par E12
		C2 = new Cercle(E, D, Color.RED); // Cercle crée par E13
		C3 = new Cercle(B, R2);
		C4 = new Cercle(F, G, Color.GRAY);
	}

	/**
	 * methode qui teste si deux points sont les memes.
	 * 
	 * @param p1 le premier point
	 * @param p2 le deuxieme point
	 */
	static void memescoordonnees(Point p1, Point p2) {
		assertEquals("Les coordonnées du point ne sont pas les mêmes", p1.getX(), p2.getX(), EPSILON);
		assertEquals("Les coordonnées du point ne sont pas les mêmes", p1.getY(), p2.getY(), EPSILON);
	}

	/**
	 * methode qui teste si deux cercles ont les memes coordonnees et le meme rayon.
	 * 
	 * @param X le premier cercle
	 * @param Y le deuxieme cercle
	 */
	static void memesCercles(Cercle X, Cercle Y) {
		memescoordonnees(X.getCentre(), Y.getCentre());
		assertEquals(X.getRayon(), Y.getRayon(), EPSILON);
	}

	/**
	 * methode qui translate deux cercles.
	 * 
	 * @param X  le premier cercle
	 * @param Y  le deuxieme cercle
	 * @param dx la translation en x
	 * @param dy la translation en y
	 */
	static void translaterCercle(Cercle X, Cercle Y, double dx, double dy) {
		X.translater(dx, dy);
		Y.translater(dx, dy);
	}

	/** Test du constructeur Cercle(Point centre, double rayon). */
	@Test
	public void E11() {
		memescoordonnees(C0.getCentre(), new Point(0, 0));
		assertEquals("Les cercles n'ont pas le meme rayon", C1.getRayon(), 2, EPSILON);
		assertEquals("Les cercles n'ont pas la meme couleur", C1.getCouleur(), Color.BLUE);
	}

	/** Test du constructeur Cercle(Point p1, Point p2). */
	@Test
	public void testerE12() {
		memesCercles(C0, C1);
		assertEquals("Les cercles C0 et C1 n'ont pas la meme couleur", C0.getCouleur(), C1.getCouleur());
		translaterCercle(C0, C1, 5, -5);
		memesCercles(C0, C1);
		C1.setCouleur(Color.RED);
		assertFalse(C0.getCouleur() == C1.getCouleur());
	}

	/** Test du constructeur Cercle(Point p1, Point p2, Color couleur). */
	@Test
	public void testerE13() {
		memesCercles(C0, C2);
		assertEquals("La couleur du cercle doit etre rouge", C2.getCouleur(), Color.RED);
		translaterCercle(C0, C2, 3, -1);
		memesCercles(C0, C2);
		C2.setCouleur(Color.GREEN);
		assertFalse(C0.getCouleur() == C2.getCouleur());
		memesCercles(C3, C4);
		C3.setCouleur(Color.YELLOW);
		assertEquals(C3.getCouleur(), Color.YELLOW);
	}
}
