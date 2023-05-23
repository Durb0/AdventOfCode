package exercises.aoc2016.day01;

import exercises.objects.Direction;
import exercises.objects.Position;
import utilities.A_AOC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2016 day 1's instructions are <a href="https://adventofcode.com/2016/day/1">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2016/day/1/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private Position alreadyVisited = null;

    @Override
    public void test() {
        if(isExample) {
            super.test(8, 4);
        } else {
            super.test(307, 165);
        }
    }

    @Override
    public void run() {
        List<String> item = Arrays.stream(inputList.get(0).split(", ")).toList();
        Direction direction = Direction.NORTH;
        Position startPosition = new Position(0, 0);
        List<Position> positions = new ArrayList<>();
        positions.add(startPosition);

        for (String step : item) {
            String turn = step.substring(0, 1);
            int distance = Integer.parseInt(step.substring(1));
            direction = direction.turnLR(turn);
            getNewPositions(positions, direction, distance);
        }

        Position finalPosition = positions.get(positions.size() - 1);
        solution1 = finalPosition.getManhattanDistance(startPosition);

        assert alreadyVisited != null;
        solution2 = alreadyVisited.getManhattanDistance(startPosition);
    }

    private void getNewPositions(List<Position> positions, Direction direction, int distance) {
        Position oldPosition = positions.get(positions.size() - 1);
        int x = oldPosition.getX() + getXDistance(direction, distance);
        int y = oldPosition.getY() + getYDistance(direction, distance);
        Position newPosition = new Position(x, y);
        List<Position> interval = Position.interval(oldPosition, newPosition);
        interval.subList(1, interval.size()).forEach(position -> {
            if (positions.contains(position) && alreadyVisited == null) {
                alreadyVisited = position;
            }
            positions.add(position);
        });
    }

    private static int getXDistance(Direction direction, int distance) {
        return (direction == Direction.EAST) ? distance :
                (direction == Direction.WEST) ? -distance : 0;
    }

    private static int getYDistance(Direction direction, int distance) {
        return (direction == Direction.NORTH) ? distance :
                (direction == Direction.SOUTH) ? -distance : 0;
    }
}