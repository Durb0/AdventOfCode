package exercises.aoc2015.day01;

import utilities.A_AOC;

/**
 * <pre>
 * AdventOfCode 2015 day 1's instructions are <a href="https://adventofcode.com/2015/day/1">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2015/day/1/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(-3, 1);
        } else {
            super.test(232, 1783);
        }
    }

    @Override
    public void run() {
        String item = inputList.get(0);

        long parenthesesOpen = item.chars().filter(value -> value == '(').count();
        long parenthesesClose = item.length() - parenthesesOpen;
        solution1 = parenthesesOpen - parenthesesClose;

        int basementIndex = 1;
        int floor = 0;
        for (char c : item.toCharArray()) {
            floor += (c == '(') ? 1 : -1;
            if (floor == -1) {
                break;
            }
            basementIndex++;
        }
        solution2 = basementIndex;
    }
}