package exercises.aoc2020.day02;

import utilities.A_AOC;

import java.util.List;

/**
 * <pre>
 * AdventOfCode 2020 day 2's instructions are <a href="https://adventofcode.com/2020/day/2">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2020/day/2/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(2, 1);
        } else {
            super.test(638, 699);
        }
    }

    @Override
    public void run() {
        List<PasswordChecker> passwordCheckers = inputList.stream().map(PasswordChecker::new).toList();
        solution1 = passwordCheckers.stream()
                .mapToInt(passwordChecker -> passwordChecker.isValidByCountChar() ? 1 : 0)
                .sum();
        solution2 = passwordCheckers.stream()
                .mapToInt(passwordChecker -> passwordChecker.isValidByPresence() ? 1 : 0)
                .sum();
    }
}