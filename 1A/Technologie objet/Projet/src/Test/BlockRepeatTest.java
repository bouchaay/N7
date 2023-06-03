package Test;

import org.junit.Assert;
import org.junit.Test;
import construction.*;

public class BlockRepeatTest {

    @Test
    public void testGetName() {
        BlockRepeat block = new BlockRepeat("block1", false, 2, 5, false);
        String expectedName = "Répéter";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockRepeat block = new BlockRepeat("block1", false, 2, 5, false);
        String expectedFragment = "{2,5}";
        String actualFragment = block.toRegexFragment();
        Assert.assertEquals(expectedFragment, actualFragment);
    }

    @Test
    public void testSetLazy() {
        BlockRepeat block = new BlockRepeat("block1", false, 2, 5, false);
        block.setLazy(true);
        boolean expectedLazy = true;
        boolean actualLazy = block.isLazy();
        Assert.assertEquals(expectedLazy, actualLazy);
    }

    @Test
    public void testSetTimesMinimum() {
        BlockRepeat block = new BlockRepeat("block1", false, 2, 5, false);
        block.setTimesMinimum(3);
        int expectedTimesMinimum = 3;
        int actualTimesMinimum = block.getTimesMinimum();
        Assert.assertEquals(expectedTimesMinimum, actualTimesMinimum);
    }

    @Test
    public void testSetTimesMaximum() {
        BlockRepeat block = new BlockRepeat("block1", false, 2, 5, false);
        block.setTimesMaximum(4);
        int expectedTimesMaximum = 4;
        int actualTimesMaximum = block.getTimesMaximum();
        Assert.assertEquals(expectedTimesMaximum, actualTimesMaximum);
    }
}
