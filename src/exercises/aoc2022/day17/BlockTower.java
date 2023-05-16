package exercises.aoc2022.day17;

import exercises.objects.Position;
import exercises.objects.Shape;

import java.util.ArrayList;
import java.util.List;

public class BlockTower {
    private static final int WIDTH = 7;
    private final List<Position> blocks = new ArrayList<>();

    public BlockTower(List<Position> bottom) {
        blocks.addAll(bottom);
    }

    public int size() {
        return blocks.stream()
                .map(Position::getY)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public void add(Shape currentShape) {
        blocks.addAll(currentShape.getPositions());
    }

    public List<Position> getBlocks() {
        return blocks;
    }

    public int getWidth() {
        return WIDTH;
    }
}
