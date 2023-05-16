package exercises.aoc2019.day01;

import utilities.A_AOC;

import java.util.List;

/**
 * <pre>
 * AdventOfCode 2019 day 1's instructions are <a href="https://adventofcode.com/2019/day/1">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2019/day/1/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(34241, 51316);
        } else {
            super.test(3515171, 5269882);
        }
    }

    @Override
    public void run() {
        List<Integer> list = inputList.stream()
                .map(Integer::parseInt)
                .toList();
        solution1 = sumList(operateOnList(list));

        solution2 = operateRecursive(list);
    }

    private int operateRecursive(List<Integer> list) {
        List<Integer> newList = operateOnList(list);
        int sum = sumList(newList);
        if (sum != 0) {
            sum += operateRecursive(newList);
        }
        return sum;
    }

    private int sumList(List<Integer> list) {
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Integer> operateOnList(List<Integer> list) {
        return list.stream()
                .map(integer -> {
                    int sum = integer / 3 - 2;
                    return Math.max(sum, 0);
                })
                .toList();
    }
}