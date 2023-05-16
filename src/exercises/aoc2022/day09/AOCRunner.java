package exercises.aoc2022.day09;

import utilities.A_AOC;

/**
 * <pre>
 * AdventOfCode 2022 day 9's instructions are <a href="https://adventofcode.com/2022/day/9">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/9/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private final GridOfMoves grid2 = new GridOfMoves(2);
    private final GridOfMoves grid10 = new GridOfMoves(10);

    @Override
    public void test() {
        if(isExample) {
            super.test(13, 1);
        } else {
            super.test(5883, 2367);
        }
    }

    @Override
    public void run() {
        for (String item : inputList) {
            String direction = item.split(" ")[0];
            int move = Integer.parseInt(item.split(" ")[1]);

            grid2.move(direction, move);
            grid10.move(direction, move);
        }
        solution1 = grid2.countQueue();
        solution2 = grid10.countQueue();
    }
}
