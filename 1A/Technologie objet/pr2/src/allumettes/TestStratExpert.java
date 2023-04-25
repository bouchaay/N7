package allumettes;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test de la stratégie experte.
 * 
 * @see Strategie
 * @see StratExpert
 * @see TestStratExpert
 * @author Ayoub Bouchama
 */
public class TestStratExpert {

    /** Jeux d'allumettes. */
    SujetReel jeu1, jeu2, jeu3, jeu4, jeu5, jeu6, jeu7, jeu8, jeu9, jeu10, jeu11, jeu12, jeu13;
    /** Stratégie experte. */
    Strategie strat;
    /** Joueur j. */
    Joueur j;

    /** Initialisation des tests. */
    @Before
    public void setUp() {
        jeu1 = new SujetReel(1);
        jeu2 = new SujetReel(2);
        jeu3 = new SujetReel(3);
        jeu4 = new SujetReel(4);
        jeu5 = new SujetReel(5);
        jeu6 = new SujetReel(6);
        jeu7 = new SujetReel(7);
        jeu8 = new SujetReel(8);
        jeu9 = new SujetReel(9);
        jeu10 = new SujetReel(10);
        jeu11 = new SujetReel(11);
        jeu12 = new SujetReel(12);
        jeu13 = new SujetReel(13);
        strat = new StratExpert();
    }

    /** Test de la méthode getNom. */
    @Test
    public void testGetNom() {
        assertTrue(strat.getNom().equals("expert"));
    }

    /** Test de la méthode getPrise. */
    @Test
    public void testGetPrise() {
        assertEquals(1, strat.getPrise(jeu1, j));
        assertEquals(1, strat.getPrise(jeu2, j));
        assertEquals(2, strat.getPrise(jeu3, j));
        assertEquals(3, strat.getPrise(jeu4, j));
        assertEquals(1, strat.getPrise(jeu5, j));
        assertEquals(1, strat.getPrise(jeu6, j));
        assertEquals(2, strat.getPrise(jeu7, j));
        assertEquals(3, strat.getPrise(jeu8, j));
        assertEquals(1, strat.getPrise(jeu9, j));
        assertEquals(1, strat.getPrise(jeu10, j));
        assertEquals(2, strat.getPrise(jeu11, j));
        assertEquals(3, strat.getPrise(jeu12, j));
        assertEquals(1, strat.getPrise(jeu13, j));
    }
        
}
