package exercises.aoc2021.day01;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2021 day 1's instructions are <a href="https://adventofcode.com/2021/day/1">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2021/day/1/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(7, 5);
        } else {
            super.test(1564, 1611);
        }
    }

    @Override
    public void run() {
        solution1 = countGreaterMeasure(inputList, 1);
        solution2 = countGreaterMeasure(inputList, 3);
    }

    private int countGreaterMeasure(List<String> list, int glide) {
        int count = 0;
        int last = 0;
        List<Integer> glider = new ArrayList<>();
        for (String item : list) {
            glider.add(Integer.parseInt(item));
            if (glider.size() > glide) {
                glider.remove(0);
                int current = glider.stream().reduce(0, Integer::sum);
                if (last < current) {
                    count++;
                }
                last = current;
            }
        }
        return count;
    }
}
