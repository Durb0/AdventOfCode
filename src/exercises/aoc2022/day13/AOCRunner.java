package exercises.aoc2022.day13;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.json.JSONArray;
import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2022 day 13's instructions are <a href="https://adventofcode.com/2022/day/13">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/13/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private final List<ImmutablePair<JSONArray, JSONArray>> pairPackets = new ArrayList<>();

    @Override
    public void test() {
        if(isExample) {
            super.test(13, 140);
        } else {
            super.test(6568, 19493);
        }
    }

    @Override
    public void run() {
        parseListInPackets(inputList);
        solution1 = getSumOfIndexSorted();

        inputList.add("[[2]]");
        inputList.add("[[6]]");

        List<String> sortedList = sortByArray(inputList);
        solution2 = (sortedList.indexOf("[[2]]") + 1) * (sortedList.indexOf("[[6]]") + 1);
    }

    private void parseListInPackets(List<String> list) {
        for (int index = 0; index < list.size(); index = index + 3) {
            JSONArray json1 = new JSONArray(list.get(index));
            JSONArray json2 = new JSONArray(list.get(index + 1));
            pairPackets.add(new ImmutablePair<>(json1, json2));
        }
    }

    private int getSumOfIndexSorted() {
        int index = 1;
        int sumOfIndex = 0;
        for (ImmutablePair<JSONArray, JSONArray> pair : pairPackets) {
            sumOfIndex += (JSONUtilities.compare(pair.getLeft(), pair.getRight()) <= 0) ? index : 0;
            index++;
        }
        return sumOfIndex;
    }

    private List<String> sortByArray(List<String> list) {
        return list.stream()
                .filter(s -> !s.isBlank())
                .sorted((o1, o2) -> {
                    JSONArray packet1 = new JSONArray(o1);
                    JSONArray packet2 = new JSONArray(o2);
                    return JSONUtilities.compare(packet1, packet2);
                })
                .toList();
    }
}
