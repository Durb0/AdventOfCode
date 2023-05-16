package exercises.aoc2015.day03;

import utilities.A_AOC;
import exercises.objects.Direction;
import exercises.objects.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2015 day 3's instructions are <a href="https://adventofcode.com/2015/day/3">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2015/day/3/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private List<Santa> santaTeam;
    private List<Position> housesVisited;

    @Override
    public void test() {
        if(isExample) {
            super.test(4, 3);
        } else {
            super.test(2572, 2631);
        }
    }

    @Override
    public void run() {
        String input = inputList.get(0);

        initPositions(1);
        visitAllHouses(input);
        solution1 = housesVisited.size();

        initPositions(2);
        visitAllHouses(input);
        solution2 = housesVisited.size();
    }

    private void initPositions(int santas) {
        Position initialPosition = new Position(0, 0);
        santaTeam = new ArrayList<>();
        housesVisited = new ArrayList<>();
        housesVisited.add(initialPosition);

        for(int i = 0; i < santas; i++) {
            this.santaTeam.add(new Santa(initialPosition));
        }
    }

    private void visitAllHouses(String input) {
        for(int i = 0; i < input.length(); i++) {
            Direction direction = Direction.charToDirection(input.charAt(i));
            visitHouse(direction, santaTeam.get(i % santaTeam.size()));
        }
    }

    private void visitHouse(Direction cardinal, Santa santa) {
        Position newPosition = new Position(santa.getPosition());
        switch (cardinal) {
            case NORTH -> newPosition.incY();
            case SOUTH -> newPosition.decY();
            case EAST -> newPosition.incX();
            case WEST -> newPosition.decX();
        }

        if (!housesVisited.contains(newPosition)) {
            housesVisited.add(newPosition);
        }
        santa.setPosition(newPosition);
    }
}