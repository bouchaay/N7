package Test;
import construction.*;


import org.junit.Assert;
import org.junit.Test;

public class BlockAnyCharacterTest {

    @Test
    public void testGetName() {
        BlockAnyCharacter block = new BlockAnyCharacter("block1", false);
        String expectedName = "Caractère quelconque";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockAnyCharacter block = new BlockAnyCharacter("block1", false);
        String expectedRegex = ".";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}

