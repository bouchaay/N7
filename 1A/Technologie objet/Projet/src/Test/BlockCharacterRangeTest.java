package Test;

import construction.*;

import org.junit.Assert;
import org.junit.Test;

public class BlockCharacterRangeTest {

    @Test
    public void testGetName() {
        BlockCharacterRange block = new BlockCharacterRange("block1", false, "abc");
        String expectedName = "Plage de caractères";
        String actualName = block.getName();
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testToRegexFragment() {
        BlockCharacterRange block = new BlockCharacterRange("block1", false, "abc");
        String expectedRegex = "[abc]";
        String actualRegex = block.toRegexFragment();
        Assert.assertEquals(expectedRegex, actualRegex);
    }

    @Test
    public void testGetCharacter() {
        BlockCharacterRange block = new BlockCharacterRange("block1", false, "abc");
        String expectedCharacters = "abc";
        String actualCharacters = block.getCharacter();
        Assert.assertEquals(expectedCharacters, actualCharacters);
    }

    @Test
    public void testSetCharacter() {
        BlockCharacterRange block = new BlockCharacterRange("block1", false, "abc");
        String newCharacters = "def";
        block.setCharacter(newCharacters);
        String expectedCharacters = newCharacters;
        String actualCharacters = block.getCharacter();
        Assert.assertEquals(expectedCharacters, actualCharacters);
    }
}

