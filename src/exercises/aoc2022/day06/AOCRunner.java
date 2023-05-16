package exercises.aoc2022.day06;

import utilities.A_AOC;

/**
 * <pre>
 * AdventOfCode 2022 day 6's instructions are <a href="https://adventofcode.com/2022/day/6">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/6/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(5, 23);
        } else {
            super.test(1896, 3452);
        }
    }

    @Override
    public void run() {
        for (String item : inputList) {
            solution1 = findFirstPacket(item, 4);
            solution2 = findFirstPacket(item, 14);
        }
    }

    private int findFirstPacket(String item, int size) {
        int incr = 0;
        while (incr < item.length() - size) {
            String subStr = item.substring(incr, incr + size);
            long count = subStr.chars().distinct().count();
            if (count == size) {
                return incr + size;
            }
            incr++;
        }
        return 0;
    }
}
