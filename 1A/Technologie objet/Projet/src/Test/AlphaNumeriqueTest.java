package Test;
import org.junit.Test;

import construction.*;
import junit.framework.Assert;

public class AlphaNumeriqueTest {
	@Test
    public void testGetName() {
        BlockAlphanumericOrUnderscore block = new BlockAlphanumericOrUnderscore("block1", false);
        String expectedName = "Alphanumérique ou souligné";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockAlphanumericOrUnderscore block = new BlockAlphanumericOrUnderscore("block1", false);
        String expectedRegex = "\\w";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}


