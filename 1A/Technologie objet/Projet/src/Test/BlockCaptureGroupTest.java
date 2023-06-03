package Test;

import construction.*;

import org.junit.Assert;
import org.junit.Test;

public class BlockCaptureGroupTest {

    @Test
    public void testGetName() {
        BlockCaptureGroup block = new BlockCaptureGroup("block1", false, "expression");
        String expectedName = "Groupe de capture";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockCaptureGroup block = new BlockCaptureGroup("block1", false, "expression");
        String expectedRegex = "(expression)";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}

