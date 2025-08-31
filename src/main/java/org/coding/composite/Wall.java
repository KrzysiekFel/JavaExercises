package org.coding.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private final List<Block> allBlocks;

    public Wall(List<Block> allBlocks) {
        this.allBlocks = allBlocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return this.findBlockByColorHelper(color, this.allBlocks);
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return this.findBlocksByMaterialHelper(material, this.allBlocks);
    }

    @Override
    public int count() {
        return this.countHelper(this.allBlocks);
    }

    private Optional<Block> findBlockByColorHelper(String color, List<Block> blocks) {
        for (Block block : blocks) {
            if (block.getColor().equalsIgnoreCase(color)) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock compositeBlock) {
                Optional<Block> internalCompositeBlock = this.findBlockByColorHelper(color, compositeBlock.getBlocks());
                if (internalCompositeBlock.isPresent()) {
                    return internalCompositeBlock;
                }
            }
        }
        return Optional.empty();
    }

    private List<Block> findBlocksByMaterialHelper(String material, List<Block> blocks) {
        List<Block> foundedBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getMaterial().equalsIgnoreCase(material)) {
                foundedBlocks.add(block);
            }
            if (block instanceof CompositeBlock compositeBlock) {
                foundedBlocks.addAll(this.findBlocksByMaterialHelper(material, compositeBlock.getBlocks()));
            }
        }
        return foundedBlocks;
    }

    private int countHelper(List<Block> blocks) {
        int counter = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                counter++; // if adding composite block is not needed -> TO DELETE
                counter += this.countHelper(compositeBlock.getBlocks());
            } else {
                counter++;
            }
        }
        return counter;
    }
}
