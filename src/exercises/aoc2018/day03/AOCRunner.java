package exercises.aoc2018.day03;

import utilities.errors.NoSuchElementInListException;
import utilities.A_AOC;
import exercises.objects.Position;
import exercises.objects.Shape;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * <pre>
 * AdventOfCode 2018 day 3's instructions are <a href="https://adventofcode.com/2018/day/3">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2018/day/3/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if (isExample) {
            super.test(4, 3);
        } else {
            super.test(104712, 840);
        }
    }

    @Override
    public void run() {
        List<Shape> shapes = parseInputAsShapes(inputList);
        Map<Position, Integer> claimedCells = getClaimedCells(shapes);
        Shape shape = getNonOverlappingShape(shapes, claimedCells);

        solution1 = countOverlappingCells(claimedCells);
        solution2 = shape.getId();
    }

    private static long countOverlappingCells(Map<Position, Integer> claimedCells) {
        return claimedCells.keySet().stream()
                .filter(position -> isOverlapping(position, claimedCells))
                .count();
    }

    private List<Shape> parseInputAsShapes(List<String> list) {
        return list.stream()
                .map(AOCRunner::extractIntArray)
                .map(this::extractShape)
                .toList();
    }

    private static int[] extractIntArray(String line) {
        String[] splitLine = line.split("\\D+"); // ["", "ID", "X", "Y", "A", "B"]
        return Arrays.stream(splitLine)
                .filter(Predicate.not(String::isEmpty)) // ["ID", "X", "Y", "A", "B"]
                .mapToInt(Integer::parseInt) // [ID, X, Y, A, B]
                .toArray();
    }

    private Shape extractShape(int[] items) {
        // items = [ID, X, Y, A, B]
        Shape shape = new Shape(items[0]);
        Position firstPosition = new Position(items[1], items[2]);
        shape.setRectangle(firstPosition, items[3], items[4]);
        return shape;
    }

    private Map<Position, Integer> getClaimedCells(List<Shape> shapes) {
        Map<Position, Integer> claimedCells = new HashMap<>();
        for (Shape shape : shapes) {
            for (Position position : shape.getPositions()) {
                claimedCells.put(position, claimedCells.getOrDefault(position, 0) + 1);
            }
        }
        return claimedCells;
    }

    private Shape getNonOverlappingShape(List<Shape> shapes, Map<Position, Integer> claimedCells) {
        for (Shape shape : shapes) {
            boolean nonOverlapping = true;
            for (Position position : shape.getPositions()) {
                if (isOverlapping(position, claimedCells)) {
                    nonOverlapping = false;
                    break;
                }
            }
            if (nonOverlapping) {
                return shape;
            }
        }
        throw new NoSuchElementInListException();
    }

    private static boolean isOverlapping(Position position, Map<Position, Integer> claimedCells) {
        return claimedCells.get(position) > 1;
    }
}