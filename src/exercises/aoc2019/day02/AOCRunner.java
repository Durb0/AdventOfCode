package exercises.aoc2019.day02;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * <pre>
 * AdventOfCode 2019 day 2's instructions are <a href="https://adventofcode.com/2019/day/2">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2019/day/2/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    public static final int AWAITED_OUTPUT = 19690720;

    @Override
    public void test() {
        if(isExample) {
            super.test(3500, 1202);
        } else {
            super.test(7210630, 3892);
        }
    }

    @Override
    public void run() {
        List<Integer> memory = Stream.of(inputList.get(0).split(","))
                .map(Integer::parseInt)
                .toList();

        if(isExample) {
            solution1 = computeWithValues(memory, 9, 10);
            solution2 = 1202; // Cannot be tested
        } else {
            solution1 = computeWithValues(memory, 12, 2);
            solution2 = computeUntilAwaited(memory);
        }
    }

    private static int computeUntilAwaited(List<Integer> memory) {
        int output;
        int noun = 0;
        int verb = 0;
        while (true) {
            output = computeWithValues(memory, noun, verb);
            if (output == AWAITED_OUTPUT) {
                return noun * 100 + verb;
            } else if (noun < verb) {
                noun++;
            } else {
                verb++;
                noun = 0;
            }
        }
    }

    private static int computeWithValues(List<Integer> memory, int noun, int verb) {
        List<Integer> currentMemory = new ArrayList<>(memory);
        currentMemory.set(1, noun);
        currentMemory.set(2, verb);
        for (int blocPosition = 0; blocPosition < currentMemory.size() / 4; blocPosition++) {
            Integer outputValue = computeAtPosition(currentMemory, blocPosition);
            if (outputValue != null) {
                return outputValue;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    private static Integer computeAtPosition(List<Integer> currentMemory, int blocPosition) {
        OpCode opCode = OpCode.fromValue(currentMemory.get(blocPosition * 4));
        if(opCode == OpCode.END) {
            return currentMemory.get(0);
        }
        int currentNoun = currentMemory.get(blocPosition * 4 + 1);
        int currentVerb = currentMemory.get(blocPosition * 4 + 2);
        int outputAddress = currentMemory.get(blocPosition * 4 + 3);
        int valueAtNoun = currentMemory.get(currentNoun);
        int valueAtVerb = currentMemory.get(currentVerb);
        switch (opCode) {
            case ADD -> currentMemory.set(outputAddress, valueAtNoun + valueAtVerb);
            case MUL -> currentMemory.set(outputAddress, valueAtNoun * valueAtVerb);
            default -> throw new OutOfMemoryError();
        }
        return null;
    }
}