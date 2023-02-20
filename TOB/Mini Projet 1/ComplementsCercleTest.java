import java.awt.Color;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/** Classe de tests exhaustifs de la classe Cercle.
 * 
 * @author Ayoub Bouchama
 */

public class ComplementsCercleTest {

        // précision pour les comparaisons réelle
        public final static double EPSILON = 0.001;

        // Les centres du sujet
        Point centre;
        Point p1;
        Point p2;
        
        // Les cercles du sujet
        Cercle C1;
        Cercle C2;
        Cercle C3;
        
        // Le rayon du sujet
        double rayon;
        
        @Before public void setUp() {
                
                // Initialiser les points du test
                centre 	= 	new Point(0, 0);
                p1 		= 	new Point(0, 0);
                p2 		= 	new Point(10, 0);
                
                // Initialiser le rayon
                rayon            =       5;

                // Initialiser les cercles du test
                C1               =       new Cercle(centre, rayon);
                C2               =       new Cercle(p1, p2);
                C3               =       new Cercle(p1, p2, Color.RED);

        }
        
        /** Vérifier si deux points ont mêmes coordonnées.
   	  	* @param p1 le premier point
   	  	* @param p2 le deuxième point
   	  	*/
        static void memesCoordonnees(String message, Point p1, Point p2) {
        		assertEquals(message + " (x) ", p1.getX(), p2.getX(), EPSILON);
        		assertEquals(message + " (y) ", p1.getY(), p2.getY(), EPSILON);
        }

        /** Tester le premier constructeur de la classe cercle à partir du centre et du rayon. */
        @Test
        public void testCercleConstructeur() {
         
                // Test pour une valeur positive valide
                memesCoordonnees("Constructeur E11 incorrecte", centre, C1.getCentre());
                assertEquals("Constructeur E11 incorrecte", rayon, C1.getRayon(), EPSILON);
  
                // Test pour une valeur négative
                try {
                        C1 = new Cercle(centre, -1);
                        fail("Une exception devrait être levée pour une valeur de rayon négative");
                } 
                catch (AssertionError e) {
                        // Attendue exception pour une valeur de rayon négative
                }
  
                // Test pour une valeur nulle
                try {
                        C1 = new Cercle(centre, 0);
                        fail("Une exception devrait être levée pour une valeur de rayon nulle");
                } 
                catch (AssertionError e) {
                        // Attendue exception pour une valeur de rayon nulle
                }
  
                // Test pour un centre nul
                try {
                        C1 = new Cercle(null, rayon);
                        fail("Une exception devrait être levée pour un centre nul");
                } 
                catch (AssertionError e) {
                        // Attendue exception pour un centre nul
                }
        }

        /** Tester le deuxième constructeur de la classe cercle à partir de deux point diamètralement opposés. */
        @Test
        public void testCercleConstructeur2Points() {
               
                // Test pour des points valides
        		memesCoordonnees("Constructeur E12 incorrecte", C2.getCentre(), new Point(5, 0));
        		assertEquals("Constructeur E12 incorrecte", 5, C2.getRayon(), EPSILON);
                assertEquals("Constructeur E12 incorrecte", Color.BLUE, C2.getCouleur());
                
                // Test pour p1 null
                try {
                        C2 = new Cercle(null, p2);
                        fail("Une exception devrait être levée pour un point nul");
                } catch (AssertionError e) {
                        // Attendue exception pour un point nul
                }
                
                // Test pour p2 null
                try {
                        C2 = new Cercle(p1, null);
                        fail("Une exception devrait être levée pour un point nul");
                } catch (AssertionError e) {
                        // Attendue exception pour un point nul
                }
                
                // Test pour des points identiques
                try {
                        C2 = new Cercle(p1, p1);
                        fail("Une exception devrait être levée pour des points identiques");
                } 
                catch (AssertionError e) {
                        // Attendue exception pour des points identiques
                }
        }

        /** Tester le troisième constructeur de la classe cercle à partir de deux point diamètralement opposés et sa couleur. */
        @Test
        public void testCercleConstructeur2PointsEtCouleur() {

                // Test pour des points valides
        		memesCoordonnees("Constructeur E13 incorrecte", C3.getCentre(), new Point(5, 0));
        		assertEquals("Constructeur E13 incorrecte", 5, C3.getRayon(), EPSILON);
                assertEquals("Constructeur E13 incorrecte", Color.RED, C3.getCouleur());
                
                // Test pour p1 null
                try {
                        C3 = new Cercle(null, p2, Color.RED);
                        fail("Une exception devrait être levée pour un point nul");
                } catch (AssertionError e) {
                        // Attendue exception pour un point nul
                }
                
                // Test pour p2 null
                try {
                        C3 = new Cercle(p1, null, Color.RED);
                        fail("Une exception devrait être levée pour un point nul");
                } catch (AssertionError e) {
                        // Attendue exception pour un point nul
                }
                
                // Test pour des points identiques
                try {
                        C3 = new Cercle(p1, p1, Color.RED);
                        fail("Une exception devrait être levée pour des points identiques");
                } 
                catch (AssertionError e) {
                        // Attendue exception pour des points identiques
                }

                // Test pour une couleur nulle
                try {
                        C3 = new Cercle(p1, p2, null);
                        fail("Une exception devrait être levée pour une couleur nulle");
                } 
                catch (AssertionError e) {
                        // Attendue exception pour une couleur nulle
                }
        }

        /** Tester la methode translater. */
        @Test
        public void testTranslater() {

                // Test translation valide
                C1.translater(2, 3);
                memesCoordonnees("Méthode translater() incorrecte", C1.getCentre(), new Point(2, 3));
            
                // Test translation négative
                C1.translater(-4, -6);
                memesCoordonnees("Méthode translater() incorrecte", C1.getCentre(), new Point(-2, -3));
        }

        /** Tester la methode getCentre. */
        @Test
        public void testGetCentre() {
 
                // Tester getCentre
                memesCoordonnees("Méthode getCentre() incorrecte", C1.getCentre(), centre);
                
                // Translater le centre puis tester getCentre
                C1.translater(2, 2);
                memesCoordonnees("Méthode getCentre() incorrecte", C1.getCentre(), new Point(2, 2));
        }

        /** Tester la methode getRayon. */
        @Test
        public void testGetRayon() {

                // Tester getRayon
                assertEquals("Méthode getRayon() incorrecte", rayon, C1.getRayon(), EPSILON);
                
                // Modifier le rayon puis tester getRayon
                C1.setRayon(20);
                assertEquals("Méthode getRayon() incorrecte", 20, C1.getRayon(), EPSILON);
        }

        /** Tester la methode getDiametre. */
        @Test
        public void testGetDiametre() {

                // Tester getDiametre
                assertEquals(10, C1.getDiametre(), EPSILON);
                
                // Modifier le rayon puis tester getDiametre
                C1.setRayon(20);
                assertEquals(40, C1.getDiametre(), EPSILON);
        }

        /** Tester la methode contient qui indique si un point est contenu dans le cercle ou pas. */
        @Test
        public void testContient() {
               
                // Initialiser les paramétres
                Point pointInterieur = new Point(2, 2);
                Point pointExterieur = new Point(7, 7);

                // Test avec un point à l'intérieur du cercle
                assertTrue("Méthode contient() incorrecte", C1.contient(pointInterieur));
                assertFalse("Méthode contient() incorrect", C1.contient(pointExterieur));

                try {
                        assertTrue(C1.contient(null));
                        fail("Une exception devrait être levée pour un point null");
                }
                catch (AssertionError e) {
                // Attendue exception pour un point null
                }
        }

        /** Tester la methode perimetre qui retourne le perimetre d'un cercle. */
        @Test
        public void testPerimetre() {

                // Tester la methode perimetre
                assertEquals("Méthode perimetre() incorrecte", 31.415926, C1.perimetre(), EPSILON);
                assertEquals("Méthode perimetre() incorrecte", 31.415926, C2.perimetre(), EPSILON);
                assertEquals("Méthode perimetre() incorrecte", 31.415926, C3.perimetre(), EPSILON);
        }

        /** Tester la methode aire qui retourne l'aire d'un cercle. */
        @Test
        public void testAire() {

                // Tester la methode perimetre
                assertEquals("Méthode aire() incorrecte", 78.539816, C1.aire(), EPSILON);
                assertEquals("Méthode aire() incorrecte", 78.539816, C2.aire(), EPSILON);
                assertEquals("Méthode aire() incorrecte", 78.539816, C3.aire(), EPSILON);
        }

        /** Tester la methode getCouleur qui récupère la couleur d'un cercle. */
        @Test
        public void testGetCouleur() {

                // Tester la methode getCouleur sur les cercles C1, C2 et C3
                assertEquals("Méthode getCouleur() incorrecte", Color.BLUE, C1.getCouleur());
                assertEquals("Méthode getCouleur() incorrecte", Color.BLUE, C2.getCouleur());
                assertEquals("Méthode getCouleur() incorrecte", Color.RED, C3.getCouleur());
        }

        /** Tester la methode setCouleur qui modifie la couleur d'un cercle. */
        @Test
        public void testSetCouleur() {

                // Tester setCouleur avec une couleur valide
                C1.setCouleur(Color.GREEN);
                assertEquals("Méthode setCouleur() incorrecte", Color.GREEN, C1.getCouleur());
                C2.setCouleur(Color.RED);
                assertEquals("Méthode setCouleur() incorrecte", Color.RED, C2.getCouleur());

                // Tester setCouleur avec une entrée nulle
                try {
                        C3.setCouleur(null);
                        fail("Une exception devrait être levée pour une couleur nulle");
                } 
                catch (AssertionError e) {
                        // Attendue exception pour une couleur nulle
                }
        }

        /** Tester la methode creerCercle qui crée un cercle à partir du point p1 constituant le centre du cercle et p2 un point appartenant au cercle. */
        @Test
        public void testCreerCercle() {

                // Test pour des points valides
                Cercle cercle = Cercle.creerCercle(p1, p2);
                memesCoordonnees("Le cercle a été mal créé", cercle.getCentre(), p1);
                assertEquals("Le cercle a été mal créé", p1.distance(p2), cercle.getRayon(), EPSILON);

                // Test pour des points identiques
                try {
                        cercle = Cercle.creerCercle(p1, p1);
                        fail("Une exception devrait être levée pour des points identiques");
                }
                catch (AssertionError e) {
                        // Attendue exception pour des points identiques
                }
                
                // Test pour p1 = null
                try {
                        cercle = Cercle.creerCercle(null, p2);
                        fail("Une exception devrait être levée pour un point null");
                }
                catch (AssertionError e) {
                // Attendue exception pour un point null
                }

                // Test pour p2 = null
                try {
                        Cercle.creerCercle(p1, null);
                        fail("Une exception devrait être levée pour un point null");
                }
                catch (AssertionError e) {
                        // Attendue exception pour un point null
                }
        }

        /** Tester la methode toString qui affiche un cercle de la forme Cr@(x, y). */
        @Test
        public void testToString() {

                // Tester la methode toString.
                assertEquals("Le cercle n'a pas été affiché correctement", "C5.0@(0.0, 0.0)", C1.toString());
                assertEquals("Le cercle n'a pas été affiché correctement", "C5.0@(5.0, 0.0)", C2.toString());
                assertEquals("Le cercle n'a pas été affiché correctement", "C5.0@(5.0, 0.0)", C3.toString());
        }

        /** Tester la méthode SetRayon qui modifie le rayon d'un cercle. */
        @Test
        public void testSetRayon() {

                // Test pour une valeur positive valide
                C1.setRayon(10);
                assertEquals("La méthode setRayon() est incorrecte", 10, C1.getRayon(), EPSILON);

                // Test pour une valeur négative
                try {
                        C1.setRayon(-1);
                        fail("Une exception devrait être levée pour une valeur de rayon négative");
                }
                catch (AssertionError e) {
                // Attendue exception pour une valeur de rayon négative
                }

                // Test pour une valeur nulle
                try {
                        C1.setRayon(0);
                        fail("Une exception devrait être levée pour une valeur de rayon nulle");
                }
                catch (AssertionError e) {
                // Attendue exception pour une valeur de rayon nulle
                }
        }

        /** Tester la méthode SetDiametre qui modifie le diamètre d'un cercle. */
        @Test
        public void testSetDiametre() {
                Cercle cercle = new Cercle(centre, rayon);

                // Test pour une valeur positive valide
                cercle.setDiametre(20);
                assertEquals("La méthode setDiamètre() est incorrecte", 10, cercle.getRayon(), EPSILON);

                // Test pour une valeur négative
                try {
                        cercle.setDiametre(-2);
                        fail("Une exception devrait être levée pour une valeur de diamètre négative");
                }
                catch (AssertionError e) {
                        // Attendue exception pour une valeur de diamètre négative
                }

                // Test pour une valeur nulle
                try {
                        cercle.setDiametre(0);
                        fail("Une exception devrait être levée pour une valeur de diamètre nulle");
                }
                catch (AssertionError e) {
                        // Attendue exception pour une valeur de diamètre nulle
                }
        }
        
        /** Tester si E18 est fonctionnelle. */
        @Test
        public void testE18() {
        	memesCoordonnees("E18 est incorrecte", centre, C1.getCentre());
        	C1.getCentre().translater(5, 5);
        	memesCoordonnees("E18 est incorrecte", centre, C1.getCentre());
        	centre.translater(5, 5);
        	memesCoordonnees("E18 est incorrecte", new Point(0, 0), C1.getCentre());
        }
}
