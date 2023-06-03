package Test;
import construction.*;
import org.junit.Assert;
import org.junit.Test;

public class BlockUnicodeClassTest {

    @Test
    public void testGetName() {
        BlockUnicodeClass block = new BlockUnicodeClass("block1", false, "\\p{L}");
        String expectedName = "Caractère unicode";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockUnicodeClass block = new BlockUnicodeClass("block1", false, "\\p{L}");
        String expectedFragment = "\\p{L}";
        String actualFragment = block.toRegexFragment();
        Assert.assertEquals(expectedFragment, actualFragment);
    }
}

