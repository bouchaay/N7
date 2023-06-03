package Test;

import construction.*;

import org.junit.Assert;
import org.junit.Test;

public class BlockDigitTest {

    @Test
    public void testGetName() {
        BlockDigit block = new BlockDigit("block1", false);
        String expectedName = "Nombre";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockDigit block = new BlockDigit("block1", false);
        String expectedRegex = "\\d";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}
