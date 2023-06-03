package Test;
import construction.*;

import org.junit.Assert;
import org.junit.Test;

public class BlockLookaheadTest {

    @Test
    public void testGetName() {
        BlockLookahead block = new BlockLookahead("block1", false, "abc");
        String expectedName = "Après, il y a...";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockLookahead block = new BlockLookahead("block1", false, "abc");
        String expectedRegex = "(?=abc)";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}
