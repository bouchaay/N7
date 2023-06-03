package Test;
import construction.*;

import org.junit.Assert;
import org.junit.Test;

public class BlockLiteralTest {

    @Test
    public void testGetName() {
        BlockLiteral block = new BlockLiteral("block1", false, "Hello");
        String expectedName = "Expression littérale";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockLiteral block = new BlockLiteral("block1", false, "Hello+World");
        String expectedRegex = "Hello\\+World";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}
