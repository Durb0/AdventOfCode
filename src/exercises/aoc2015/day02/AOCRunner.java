package exercises.aoc2015.day02;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2015 day 2's instructions are <a href="https://adventofcode.com/2015/day/2">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2015/day/2/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private final List<Present> presents = new ArrayList<>();

    @Override
    public void test() {
        if(isExample) {
            super.test(101, 48);
        } else {
            super.test(1588178, 3783758);
        }
    }

    @Override
    public void run() {
        for (String item : inputList) {
            String[] dimensions = item.split("x");
            int length = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            int height = Integer.parseInt(dimensions[2]);
            Present present = new Present(length, width, height);
            presents.add(present);
        }

        solution1 = presents.stream().map(Present::getPaper).mapToInt(Integer::intValue).sum();
        solution2 = presents.stream().map(Present::getRibbon).mapToInt(Integer::intValue).sum();
    }
}