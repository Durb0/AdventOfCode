package exercises.aoc2018.day01;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2018 day 1's instructions are <a href="https://adventofcode.com/2018/day/1">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2018/day/1/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(1, 14);
        } else {
            super.test(582, 488);
        }
    }

    @Override
    public void run() {
        List<Integer> list = inputList.stream().map(item -> {
            char symbol = item.charAt(0);
            int number = Integer.parseInt(item.substring(1));
            return (symbol == '+') ? number : -number;
        }).toList();

        solution1 = list.stream().mapToInt(Integer::intValue).sum();

        Integer firstFetch = null;
        List<Integer> steps = new ArrayList<>();
        steps.add(0);
        int sum = 0;
        int index = 0;
        while (firstFetch == null) {
            sum += list.get(index);
            if(steps.contains(sum)) {
                firstFetch = sum;
            }
            steps.add(sum);
            index = (index + 1) % list.size();
        }

        solution2 = firstFetch;
    }
}