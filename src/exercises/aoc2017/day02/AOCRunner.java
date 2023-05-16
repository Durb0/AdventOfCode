package exercises.aoc2017.day02;

import utilities.errors.NoSuchElementInListException;
import utilities.A_AOC;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2017 day 2's instructions are <a href="https://adventofcode.com/2017/day/2">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2017/day/2/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(18, 9);
        } else {
            super.test(36174, 244);
        }
    }

    @Override
    public void run() {
        int sumMinMax = 0;
        int sumDivisions = 0;
        for (String line : inputList) {
            List<Integer> numbers = Arrays.stream(line.split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();
            sumMinMax += sumMinMax(numbers);
            sumDivisions += sumDivisions(numbers);
        }
        solution1 = sumMinMax;

        solution2 = sumDivisions;
    }

    private static int sumMinMax(List<Integer> list) {
        int min = Collections.min(list);
        int max = Collections.max(list);
        return max - min;
    }

    private int sumDivisions(List<Integer> numbers) {
        for (Integer currentNumber : numbers) {
            int sum = numbers.stream()
                    .filter(number -> isDivisible(currentNumber, number))
                    .filter(number -> !currentNumber.equals(number))
                    .mapToInt(number -> getDivision(currentNumber, number))
                    .sum();
            if(sum > 0) {
                return sum;
            }
        }
        throw new NoSuchElementInListException();
    }

    private static int getDivision(int num1, int num2) {
        return Math.max(num1, num2) / Math.min(num1, num2);
    }

    private static boolean isDivisible(int num1, int num2) {
        return num1 % num2 == 0 || num2 % num1 == 0;
    }
}