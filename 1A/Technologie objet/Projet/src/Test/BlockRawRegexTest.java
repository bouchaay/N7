package Test;

import construction.*;

import org.junit.Assert;
import org.junit.Test;

public class BlockRawRegexTest {

    @Test
    public void testGetName() {
        BlockRawRegex block = new BlockRawRegex("block1", false, "\\d{3}");
        String expectedName = "Regex brute";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockRawRegex block = new BlockRawRegex("block1", false, "\\d{3}");
        String expectedRegex = "\\d{3}";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}
