package exercises.aoc2022.day01;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static utilities.FileIO.DELIMITER;

/**
 * <pre>
 * AdventOfCode 2022 day 1's instructions are <a href="https://adventofcode.com/2022/day/1">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/1/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private List<Integer> backpacks = new ArrayList<>();

    @Override
    public void test() {
        if(isExample) {
            super.test(24000, 45000);
        } else {
            super.test(70613, 205805);
        }
    }

    @Override
    public void run() {
        createBackPacks(inputList);
        Collections.sort(backpacks);
        solution1 = backpacks.get(backpacks.size() - 1);
        solution2 = backpacks.subList(backpacks.size() - 3, backpacks.size())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void createBackPacks(List<String> list) {
        String baseInput = String.join(DELIMITER, list);
        String[] backpacksStr = baseInput.split(DELIMITER + DELIMITER);

        backpacks = new ArrayList<>(Stream.of(backpacksStr)
                .map(backpack -> Stream.of(backpack.split(DELIMITER))
                        .mapToInt(Integer::parseInt)
                        .sum()
                )
                .toList());
    }
}