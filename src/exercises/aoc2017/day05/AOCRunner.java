package exercises.aoc2017.day05;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2017 day 5's instructions are <a href="https://adventofcode.com/2017/day/5">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2017/day/5/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if (isExample) {
            super.test("5", "10");
        } else {
            super.test("339351", "24315397");
        }
    }

    @Override
    public void run() {
        List<Integer> list = this.parseInput();
        solution1 = findSolution(list, new Strategy1());
        solution2 = findSolution(list, new Strategy2());
    }


    public String findSolution(List<Integer> list, Strategy strategy) {
        List<Integer> numbers = new ArrayList<>(list);
        int count = 0;
        int indice = 0;
        while (indice < numbers.size() && indice >= 0) {
            int currentValue = numbers.get(indice);
            int newValue = strategy.run(currentValue);
            numbers.set(indice, newValue);
            indice += currentValue;
            count++;
        }
        return String.valueOf(count);
    }


    private List<Integer> parseInput() {
        return inputList.stream()
                .mapToInt(Integer::parseInt)
                .boxed().toList();
    }
}

interface Strategy {
    int run(int value);
}

class Strategy1 implements Strategy{

    @Override
    public int run(int value){
        return value + 1;
    }
}

class Strategy2 implements Strategy{

    @Override
    public int run(int value){
        return value >= 3 ? value - 1 : value + 1;
    }
}


