package allumettes;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test unitaire de la classe StratRapide.
 * 
 * @see StratRapide
 * @see Jeu
 * @see Strategie
 * @author Ayoub Bouchama
 */
public class TestStratRapide {

    /** Jeu d'allumettes. */
    private SujetReel jeu1, jeu2, jeu3, jeu4, jeu5, jeu6;
    /** Stratégie rapide. */
    private StratRapide strat;
    /** Joueur j. */
    Joueur j;

    /** Initialisation des tests. */
    @Before
    public void setUp() {
        jeu1 = new SujetReel(20);
        jeu2 = new SujetReel(1);
        jeu3 = new SujetReel(2);
        jeu4 = new SujetReel(3);
        jeu5 = new SujetReel(13);
        jeu6 = new SujetReel(1000);
        strat = new StratRapide();
    }

    /** Test de la méthode getNom. */
    @Test
    public void testGetNom() {
        assertTrue(strat.getNom().equals("rapide"));
    }

    /** Test de la méthode getPrise. */
    @Test
    public void testGetPrise() {
        assertEquals(3, strat.getPrise(jeu1, j));
    }

    /** Test de la méthode getPrise. */
    @Test
    public void testGetPrise2() {
        assertEquals(1, strat.getPrise(jeu2, j));
    }

    /** Test de la méthode getPrise. */
    @Test
    public void testGetPrise3() {
        assertEquals(2, strat.getPrise(jeu3, j));
    }

    /** Test de la méthode getPrise. */
    @Test
    public void testGetPrise4() {
        assertEquals(3, strat.getPrise(jeu4, j));
    }

    /** Test de la méthode getPrise. */
    @Test
    public void testGetPrise5() {
        assertEquals(3, strat.getPrise(jeu5, j));
    }

    /** Test de la méthode getPrise. */
    @Test
    public void testGetPrise6() {
        assertEquals(3, strat.getPrise(jeu6, j));
    }
}
