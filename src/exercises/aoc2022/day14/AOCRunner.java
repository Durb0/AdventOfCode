package exercises.aoc2022.day14;

import utilities.A_AOC;
import exercises.objects.Position;

import java.util.List;
import java.util.stream.Stream;

/**
 * <pre>
 * AdventOfCode 2022 day 14's instructions are <a href="https://adventofcode.com/2022/day/14">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/14/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(24, 93);
        } else {
            super.test(728, 27623);
        }
    }

    @Override
    public void run() {
        Cavern cavern = new Cavern(getDepth(inputList));
        cavern.setDropPosition(new Position(500, 0));
        cavern.fillWithAir();

        addRocks(cavern, inputList);

        cavern.dropSandUntilAbyss();
        solution1 = cavern.countSand();

        cavern.fillBottomWithRocks();
        cavern.dropSandUntilFull();
        solution2 = cavern.countSand();
    }

    private int getDepth(List<String> list) {
        return list.stream().flatMap(
                item -> Stream.of(item.split(" -> "))
                        .map(coordinates -> coordinates.split(",")[1])
                        .map(Integer::parseInt)
        ).max(Integer::compareTo).orElse(0);
    }

    private void addRocks(Cavern cavern, List<String> list) {
        for (String walls : list) {
            String[] wallCoordinates = walls.split(" -> ");
            for (int index = 0; index < wallCoordinates.length - 1; index++) {
                Position beginWall = new Position(wallCoordinates[index], ",");
                Position endWall = new Position(wallCoordinates[index + 1], ",");
                cavern.addRocks(beginWall, endWall);
            }
        }
    }
}
