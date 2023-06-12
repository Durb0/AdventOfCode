package exercises.aoc2017.day04;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * AdventOfCode 2017 day 4's instructions are <a href="https://adventofcode.com/2017/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2017/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    @Override
    public void test() {
        if(isExample) {
            super.test("2", "2");
        } else {
            super.test("386", "208");
        }
    }

    @Override
    public void run() {
        solution1 = getNbValidPassword(false);
        solution2 = getNbValidPassword(true);
    }

    private int getNbValidPassword(boolean checkAnagrams) {
        int countValidPassword = 0;
        for(String line : inputList) {
            String[] words = line.split(" ");
            if(checkAnagrams) {
                words = sortLettersForEachWord(words);
            }
            if(isValid(words)) {
                countValidPassword++;
            }
        }
        return countValidPassword;
    }

    private String[] sortLettersForEachWord(String[] words) {
        List<String> newWords = new ArrayList<>();
        for(String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            newWords.add(new String(chars));
        }
        return newWords.toArray(new String[0]);
    }

    private boolean isValid(String[] words) {
        Set<String> set = new HashSet<>(List.of(words));
        return words.length == set.size();
    }
}