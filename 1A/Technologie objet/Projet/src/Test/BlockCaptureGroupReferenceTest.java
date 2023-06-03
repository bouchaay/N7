package Test;

import construction.*;

import org.junit.Assert;
import org.junit.Test;

public class BlockCaptureGroupReferenceTest {

    @Test
    public void testGetName() {
        BlockCaptureGroupReference block = new BlockCaptureGroupReference("block1", false, "1");
        String expectedName = "Référence à un groupe de capture";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragmentWithNumberGroup() {
        BlockCaptureGroupReference block = new BlockCaptureGroupReference("block1", false, "1");
        String expectedRegex = "\\1";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }

    @Test
    public void testToRegexFragmentWithStringGroup() {
        BlockCaptureGroupReference block = new BlockCaptureGroupReference("block1", false, "group1");
        String expectedRegex = "\\k<group1>";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }
}
