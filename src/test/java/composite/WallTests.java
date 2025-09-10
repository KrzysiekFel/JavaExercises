package composite;

import org.coding.composite.Block;
import org.coding.composite.CompositeBlock;
import org.coding.composite.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WallTests {
    private static Block redMetalBlock;
    private static Block redPaperBlock;
    private static Block blueMetalBlock;
    private static Block bluePaperBlock;
    private static Block yellowMetalBlock;
    private static Block yellowPaperBlock;
    private static Block multicoloredTransparent;
    private static CompositeBlock blueMetalCompositeBlock;
    private static CompositeBlock redPaperCompositeBlock;
    private static CompositeBlock yellowPaperNestedCompositeBlock;
    private static Wall wall;

    @BeforeAll
    public static void beforeEachTest() {
        redMetalBlock = Mockito.mock(Block.class);
        Mockito.when(redMetalBlock.getColor()).thenReturn("RED");
        Mockito.when(redMetalBlock.getMaterial()).thenReturn("Metal");

        redPaperBlock = Mockito.mock(Block.class);
        Mockito.when(redPaperBlock.getColor()).thenReturn("red");
        Mockito.when(redPaperBlock.getMaterial()).thenReturn("paper");

        blueMetalBlock = Mockito.mock(Block.class);
        Mockito.when(blueMetalBlock.getColor()).thenReturn("Blue");
        Mockito.when(blueMetalBlock.getMaterial()).thenReturn("METAL");

        bluePaperBlock = Mockito.mock(Block.class);
        Mockito.when(bluePaperBlock.getColor()).thenReturn("Blue");
        Mockito.when(bluePaperBlock.getMaterial()).thenReturn("PAPer");

        yellowMetalBlock = Mockito.mock(Block.class);
        Mockito.when(yellowMetalBlock.getColor()).thenReturn("yellow");
        Mockito.when(yellowMetalBlock.getMaterial()).thenReturn("metal");

        yellowPaperBlock = Mockito.mock(Block.class);
        Mockito.when(yellowPaperBlock.getColor()).thenReturn("yellow");
        Mockito.when(yellowPaperBlock.getMaterial()).thenReturn("paper");

        multicoloredTransparent = Mockito.mock(Block.class);
        Mockito.when(multicoloredTransparent.getColor()).thenReturn("multicolored");
        Mockito.when(multicoloredTransparent.getMaterial()).thenReturn("transparent");

        blueMetalCompositeBlock = Mockito.mock(CompositeBlock.class);
        Mockito.when(blueMetalCompositeBlock.getBlocks())
                .thenReturn(new ArrayList<>(List.of(redPaperBlock, redMetalBlock, blueMetalBlock)));
        Mockito.when(blueMetalCompositeBlock.getColor()).thenReturn("BLUE");
        Mockito.when(blueMetalCompositeBlock.getMaterial()).thenReturn("metal");

        redPaperCompositeBlock = Mockito.mock(CompositeBlock.class);
        Mockito.when(redPaperCompositeBlock.getBlocks()).thenReturn(new ArrayList<>(
                List.of(redPaperBlock, multicoloredTransparent, yellowPaperBlock, blueMetalBlock, yellowMetalBlock,
                        redPaperBlock)));
        Mockito.when(redPaperCompositeBlock.getColor()).thenReturn("BLUE");
        Mockito.when(redPaperCompositeBlock.getMaterial()).thenReturn("paper");

        yellowPaperNestedCompositeBlock = Mockito.mock(CompositeBlock.class);
        Mockito.when(yellowPaperNestedCompositeBlock.getBlocks()).thenReturn(
                new ArrayList<>(List.of(redPaperCompositeBlock, yellowPaperBlock, blueMetalBlock, yellowMetalBlock)));
        Mockito.when(yellowPaperNestedCompositeBlock.getColor()).thenReturn("BLUE");
        Mockito.when(yellowPaperNestedCompositeBlock.getMaterial()).thenReturn("paper");

        wall = new Wall(new ArrayList<>(
                List.of(blueMetalCompositeBlock, yellowMetalBlock, yellowPaperNestedCompositeBlock, redMetalBlock)));

    }

    @Test
    void shouldFindBlueBlockNotNested() {
        Optional<Block> result = wall.findBlockByColor("blue");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(Optional.of(blueMetalCompositeBlock), result);
    }

    @Test
    void shouldFindRedBlockNested() {
        Optional<Block> result = wall.findBlockByColor("red");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(Optional.of(redPaperBlock), result);
    }

    @Test
    void shouldFindYellowBlockAfterOtherNested() {
        Optional<Block> result = wall.findBlockByColor("yellow");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(Optional.of(yellowMetalBlock), result);
    }

    @Test
    void shouldFindMulticoloredBlockDoubleNested() {
        Optional<Block> result = wall.findBlockByColor("multicolored");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(Optional.of(multicoloredTransparent), result);
    }

    @Test
    void shouldFindMetalBlocks() {
        // GIVEN
        List<Block> expectedMetalBlocks =
                List.of(blueMetalCompositeBlock, redMetalBlock, blueMetalBlock, yellowMetalBlock, blueMetalBlock,
                        yellowMetalBlock, blueMetalBlock, yellowMetalBlock, redMetalBlock);

        // WHEN
        List<Block> result = wall.findBlocksByMaterial("metal");
        // tutaj też dajemy np mockito when then

        // THEN
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(expectedMetalBlocks, result);
    }

    @Test
    void shouldFindPaperBlocks() {
        List<Block> expectedPaperBlocks =
                List.of(redPaperBlock, yellowPaperNestedCompositeBlock, redPaperCompositeBlock, redPaperBlock,
                        yellowPaperBlock, redPaperBlock, yellowPaperBlock);

        List<Block> result = wall.findBlocksByMaterial("paper");

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(expectedPaperBlocks, result);
    }

    @Test
    void shouldFindTransparentBlocks() {
        List<Block> expectedTransparentBlocks = List.of(multicoloredTransparent);

        List<Block> result = wall.findBlocksByMaterial("transparent");

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(expectedTransparentBlocks, result);
    }

    @Test
    public void shouldCountAllBlocks() {
        int result = wall.count();

        Assertions.assertEquals(17, result);
    }

}
