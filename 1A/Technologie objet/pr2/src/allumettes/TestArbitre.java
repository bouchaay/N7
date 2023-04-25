package allumettes;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Tests unitaires de la classe Arbitre.
 * 
 * @see Arbitre
 * @see Joueur
 * @see Jeu
 * @see Strategie
 * 
 * @author Ayoub Bouchama
 */
public class TestArbitre {
    /** Premier joueur. */
    private Joueur j1;
    /** Deuxième joueur. */
    private Joueur j2;
    /** Jeu d'allumettes. */
    private SujetReel jeu;

    /** Initialisation des tests. */
    @Before
    public void setUp() {
        j1 = new Joueur("Joueur 1", new StratRapide());
        j2 = new Joueur("Joueur 2", new StratNaif());
        jeu = new SujetReel(20);
    }

    /** Test du constructeur. */
    @Test
    public void testConstructeur() {
        Arbitre arbitre = new Arbitre(j1, j2);
        assertEquals(j1, arbitre.getPremierJoueur());
        assertEquals(j2, arbitre.getDeuxiemeJoueur());
    }

    /** Test de l'activation du mode confiant. */
    @Test
    public void testActiverConfiance() {
        Arbitre arbitre = new Arbitre(j1, j2);
        assertFalse(arbitre.getModeConfiance());
        arbitre.activerConfiance();
        assertTrue(arbitre.getModeConfiance());
    }

    /** Test de la prise valide. */
    @Test
    public void testPriseValide() {
        Arbitre arbitre = new Arbitre(j1, j2);
        assertTrue(arbitre.priseValide(jeu, 1));
        assertTrue(arbitre.priseValide(jeu, Jeu.PRISE_MAX));
        assertFalse(arbitre.priseValide(jeu, 0));
        assertFalse(arbitre.priseValide(jeu, Jeu.PRISE_MAX + 1));
        assertFalse(arbitre.priseValide(jeu, 100));
    }

    /** Test de la prise valide en mode confiant. */
    @Test
    public void testArbitrer() throws CoupInvalideException {
        Arbitre arbitre = new Arbitre(j1, j2);
        arbitre.activerConfiance();
        arbitre.arbitrer(jeu);
        assertTrue(jeu.getNombreAllumettes() == 0);
    }
}
