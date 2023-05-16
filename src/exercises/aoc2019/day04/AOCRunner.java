package exercises.aoc2019.day04;

import utilities.A_AOC;

import java.util.Arrays;
import java.util.Collections;
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
            super.test("2", "1");
        } else {
            super.test("1169", "757");
        }
    }

    @Override
    public void run() {
        String[] split = inputList.get(0).split("-");
        int min = Integer.parseInt(split[0]);
        int max = Integer.parseInt(split[1]);
        solution1 = countValidPasswords(min, max, false);
        solution2 = countValidPasswords(min, max, true);
    }

    private int countValidPasswords(int min, int max, boolean checkBiggerGroup) {
        int countValidPassword = 0;
        for(int passwordIncr = min; passwordIncr <= max; passwordIncr++) {
            String password = String.valueOf(passwordIncr);
            countValidPassword += checkPassword(password, checkBiggerGroup) ? 1 : 0;
        }
        return countValidPassword;
    }

    private boolean checkPassword(String password, boolean checkBiggerGroup) {
        return isSorted(password) && containsDoublon(password, checkBiggerGroup);
    }

    private boolean isSorted(String password) {
        char[] passwordChars = password.toCharArray();
        char[] sortedChars = passwordChars.clone();
        Arrays.sort(sortedChars);
        return Arrays.equals(sortedChars, passwordChars);
    }

    private boolean containsDoublon(String password, boolean checkBiggerGroup) {
        List<Character> passwordChars = password.chars()
                .mapToObj(value -> (char) value)
                .toList();

        for(char c : passwordChars) {
            int frequency = Collections.frequency(passwordChars, c);
            if(frequency == 2 || frequency > 2 && !checkBiggerGroup) {
                return true;
            }
        }
        return false;
    }
}