package exercises.aoc2022.day12;

import utilities.A_AOC;
import exercises.objects.Position;

import java.util.List;

/**
 * <pre>
 * AdventOfCode 2022 day 12's instructions are <a href="https://adventofcode.com/2022/day/12">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/12/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    private final HeightMap grid = new HeightMap();

    @Override
    public void test() {
        if(isExample) {
            super.test(31, 29);
        } else {
            super.test(423, 416);
        }
    }

    @Override
    public void run() {
        Cell lastCell = initiateGridGetLastCell(inputList);
        grid.calculateGridFromStartAndEnd();
        grid.setFromStart(true);
        solution1 = ((lastCell != null) ? lastCell.getDistanceFromStart() : -1);

        solution2 = grid.getGrid().values().stream()
                .filter(cell -> cell.getHeight() == 'a')
                .map(Cell::getDistanceFromEnd)
                .filter(integer -> integer > -1)
                .min(Integer::compareTo)
                .orElse(0);
    }

    private Cell initiateGridGetLastCell(List<String> list) {
        Cell lastCell = null;
        int y = 0;
        for (String line : list) {
            int x = 0;
            for (char c : line.toCharArray()) {
                Position position = new Position(x, y);
                Cell cell = new Cell(position, c);
                grid.addCell(position, cell);
                if (c == 'S') {
                    cell.setHeight('a');
                    cell.setDistance(0, true);
                }
                if (c == 'E') {
                    cell.setHeight('z');
                    cell.setDistance(0, false);
                    lastCell = cell;
                }
                x++;
            }
            y++;
        }
        return lastCell;
    }
}
