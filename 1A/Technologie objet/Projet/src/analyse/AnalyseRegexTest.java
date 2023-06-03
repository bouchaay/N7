package analyse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import construction.AbstractBlock;
import construction.BlockOneOrMore;

public class AnalyseRegexTest {
    @Before
    public void setUp() {
        // TODO
    }

    @Test
    public void testAnalyseSimple() {
        AbstractBlock actual = AnalyseRegex.analyser("a+");
        assertTrue("is instance of OneOrMore", actual instanceof BlockOneOrMore);
    }

    public static void main(String[] args) {
        AbstractBlock actual = AnalyseRegex.analyser("a+");
        System.out.printf("thing is %s\n", actual.toRegexFragment());
    }
}
