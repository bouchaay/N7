package Test;

import org.junit.Assert;
import org.junit.Test;
import construction.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ExpressionTest {

    @Test
    public void testGetFilepath() {
        String filepath = "regex.txt";
        Expression expression = new Expression(filepath);
        String actualFilepath = expression.getFilepath();
        Assert.assertEquals(filepath, actualFilepath);
    }

    @Test
    public void testAddBlock() {
        Expression expression = new Expression("regex.txt");
        BlockDigit block = new BlockDigit("block1", false);
        expression.addBlock(block);
        List<AbstractBlock> expectedBlocks = new ArrayList<>();
        expectedBlocks.add(block);
        List<AbstractBlock> actualBlocks = expression.getBlocks();
        Assert.assertEquals(expectedBlocks, actualBlocks);
    }

    @Test
    public void testToRegexFragment() {
        Expression expression = new Expression("regex.txt");
        BlockDigit block1 = new BlockDigit("block1", false);
        BlockLiteral block2 = new BlockLiteral("block2", false, "abc");
        expression.addBlock(block1);
        expression.addBlock(block2);
        String expectedRegexFragment = "\\dabc";
        String actualRegexFragment = expression.toRegexFragment();
        Assert.assertEquals(expectedRegexFragment, actualRegexFragment);
    }

    @Test
    public void testToRegex() {
        Expression expression = new Expression("regex.txt");
        BlockDigit block1 = new BlockDigit("block1", false);
        BlockLiteral block2 = new BlockLiteral("block2", false, "abc");
        expression.addBlock(block1);
        expression.addBlock(block2);
        Pattern expectedPattern = Pattern.compile("\\dabc");
        Pattern actualPattern = expression.toRegex();
        Assert.assertEquals(expectedPattern.pattern(), actualPattern.pattern());
    }
}
