package exercises.aoc2022.day17;

import exercises.objects.Position;
import exercises.objects.Shape;
import utilities.A_AOC;
import utilities.errors.NoSuchElementInListException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * <pre>
 * AdventOfCode 2022 day 17's instructions are <a href="https://adventofcode.com/2022/day/17">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/17/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private static final long BIG_NB_ROCKS = 1000000000000L;
    private static final int NB_ROCKS = 2022;
    private final BlockTower blockTower = new BlockTower(Position.interval(new Position(0, 0), new Position(6, 0)));
    private final List<LoopInfo> blocksInLoop = new ArrayList<>();
    private RockShape rock = RockShape.R1;
    private boolean hasFallen;

    @Override
    public void test() {
        if(isExample) {
            super.test(3068, 1514285714288L);
        } else {
            super.test(3100, 1540634005751L);
        }
    }

    @Override
    public void run() {
        char[] directions = inputList.get(0).toCharArray();
        generateBlockTower(directions);

        solution1 = (blockTower.size());

        int loopSize = getLoopInfo(LoopInfo::getRockIndex);
        int nbBlocsInLoop = getLoopInfo(LoopInfo::getBlocks);

        LoopInfo loopInfo = getFirstLoopIndexFromEnd(loopSize);
        long nbLoops = ((BIG_NB_ROCKS - loopInfo.getRockIndex()) / loopSize);

        int sizeBeforeLoop = loopInfo.getBlocks();
        long sizeOfLoop = nbLoops * nbBlocsInLoop;
        solution2 = sizeBeforeLoop + sizeOfLoop;
    }

    private void generateBlockTower(char[] directions) {
        int directionIndex = 0;
        for (int rockIndex = 1; rockIndex <= NB_ROCKS; rockIndex++) {
            Shape currentShape = rock.getShape();
            currentShape.initPosition(2, blockTower.size() + 4);
            hasFallen = false;
            while (!hasFallen) {
                char direction = directions[directionIndex];
                directionIndex = (directionIndex + 1) % directions.length;
                currentShape = blowAndFall(currentShape, direction);
            }
            blockTower.add(currentShape);
            manageBlocksInLoop(directionIndex, currentShape, rockIndex);
            rock = rock.next();
        }
    }

    private Shape blowAndFall(Shape currentShape, char direction) {
        Shape shapeAfterBlow = blow(currentShape, direction);
        Shape shapeAfterFall = fall(shapeAfterBlow);
        hasFallen = shapeAfterFall.getMinY() == currentShape.getMinY();
        return shapeAfterFall;
    }

    private Shape blow(Shape currentShape, char direction) {
        Shape shapeAfterBlow = currentShape.copy();
        shapeAfterBlow.initPosition((direction == '<') ? -1 : 1, 0);
        if (shapeAfterBlow.getMinX() >= 0 && shapeAfterBlow.getMaxX() < blockTower.getWidth() && disjoints(shapeAfterBlow)) {
            return shapeAfterBlow;
        } else {
            return currentShape;
        }
    }

    private Shape fall(Shape currentShape) {
        Shape shapeAfterFall = currentShape.copy();
        shapeAfterFall.initPosition(0, -1);
        if (disjoints(shapeAfterFall)) {
            return shapeAfterFall;
        } else {
            return currentShape;
        }
    }

    private boolean disjoints(Shape shape) {
        return Collections.disjoint(shape.getPositions(), blockTower.getBlocks());
    }

    private void manageBlocksInLoop(int directionIndex, Shape currentShape, int rockIndex) {
        LoopInfo loopInfo = new LoopInfo(rock, directionIndex, currentShape.getMinX(), blockTower.size(), rockIndex);
        blocksInLoop.add(loopInfo);
    }

    private LoopInfo getFirstLoopIndexFromEnd(int loopSize) {
        for (long index = BIG_NB_ROCKS - 1; index > 0; index -= loopSize) {
            if (blocksInLoop.size() > index) {
                return blocksInLoop.get((int) index);
            }
        }
        throw new NoSuchElementInListException();
    }

    private int getLoopInfo(Function<LoopInfo, Integer> callback) {
        LoopInfo loopInfo = getLastElement();
        List<Integer> blocksList = blocksInLoop.stream()
                .filter(loopInfo1 ->
                        loopInfo1.equals(loopInfo)
                )
                .map(callback)
                .toList();
        int blocks1 = blocksList.get(blocksList.size() - 1);
        int blocks2 = blocksList.get(blocksList.size() - 2);
        return blocks1 - blocks2;
    }

    private LoopInfo getLastElement() {
        return blocksInLoop.get(blocksInLoop.size() - 1);
    }
}
