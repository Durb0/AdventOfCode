package exercises.aoc2017.day01;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2017 day 1's instructions are <a href="https://adventofcode.com/2017/day/1">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2017/day/1/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(3, 2);
        } else {
            super.test(1253, 1278);
        }
    }

    @Override
    public void run() {
        List<Integer> list = inputList.get(0)
                .chars()
                .mapToObj(c -> (char) c)
                .map(Character::getNumericValue)
                .toList();
        List<Integer> list2 = new ArrayList<>(list.subList(1, list.size()));
        list2.add(list.get(0));
        solution1 = sumSameChars(list, list2);

        List<Integer> subList1 = list.subList(0, list.size() / 2);
        List<Integer> subList2 = list.subList(list.size() / 2, list.size());
        solution2 = sumSameChars(subList1, subList2) * 2;
    }

    private static int sumSameChars(List<Integer> list1, List<Integer> list2) {
        int sum = 0;
        for (int index = 0; index < list1.size(); index++) {
            if (list1.get(index).equals(list2.get(index))) {
                sum += list1.get(index);
            }
        }
        return sum;
    }
}