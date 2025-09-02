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
    void shouldFindCorrectColorBlock() {
        Assertions.assertEquals(Optional.of(blueMetalCompositeBlock), wall.findBlockByColor("blue"));
        Assertions.assertEquals(Optional.of(redPaperBlock), wall.findBlockByColor("red"));
        Assertions.assertEquals(Optional.of(yellowMetalBlock), wall.findBlockByColor("yellow"));
        Assertions.assertEquals(Optional.of(multicoloredTransparent), wall.findBlockByColor("multicolored"));
    }

    @Test
    void shouldFindCorrectMaterialBlocks() {
        List<Block> expectedMetalBlocks =
                List.of(blueMetalCompositeBlock, redMetalBlock, blueMetalBlock, yellowMetalBlock, blueMetalBlock,
                        yellowMetalBlock, blueMetalBlock, yellowMetalBlock, redMetalBlock);
        List<Block> expectedPaperBlocks =
                List.of(redPaperBlock, yellowPaperNestedCompositeBlock, redPaperCompositeBlock, redPaperBlock,
                        yellowPaperBlock, redPaperBlock, yellowPaperBlock);
        List<Block> expectedTransparentBlocks = List.of(multicoloredTransparent);

        Assertions.assertEquals(expectedMetalBlocks, wall.findBlocksByMaterial("metal"));
        Assertions.assertEquals(expectedPaperBlocks, wall.findBlocksByMaterial("paper"));
        Assertions.assertEquals(expectedTransparentBlocks, wall.findBlocksByMaterial("transparent"));
    }

    @Test
    public void shouldCountAllBlocks() {
        Assertions.assertEquals(17, wall.count());
    }

}
