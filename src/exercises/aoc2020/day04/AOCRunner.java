package exercises.aoc2020.day04;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2019 day 4's instructions are <a href="https://adventofcode.com/2019/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2019/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if (isExample) {
            super.test("10", "6");
        } else {
            super.test("245", "133");
        }
    }

    @Override
    public void run() {
        List<Passport> passports = parseInput();
        solution1 = countValidPasswords(passports, false);
        solution2 = countValidPasswords(passports, true);
    }

    private List<Passport> parseInput() {
        List<Passport> passports = new ArrayList<>();
        List<String> reformatedInput = Arrays.stream(String.join(" ", inputList).split("\\s{2,}")).toList();
        for (String passportStr : reformatedInput) {
            try {
                passports.add(new Passport(passportStr));
            } catch (Exception e) {
                // nothing
            }
        }
        return passports;
    }

    private int countValidPasswords(List<Passport> passports, boolean strongCheck) {
        return passports.stream()
                .map(passport -> passport.isValid(strongCheck) ? 1 : 0)
                .mapToInt(Integer::intValue)
                .sum();
    }
}