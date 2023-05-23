package exercises.aoc2016.day05;

import org.apache.commons.codec.digest.DigestUtils;
import utilities.A_AOC;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * AdventOfCode 2016 day 5's instructions are <a href="https://adventofcode.com/2016/day/5">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2016/day/5/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test("18f47a30", "05ace8e3");
        } else {
            super.test("f77a0e6e", "999828ec");
        }
    }

    @Override
    public void run() {
        String door = inputList.get(0);
        solution1 = findDoorPassword(door, false);
        solution2 = findDoorPassword(door, true);
    }

    private static String findDoorPassword(String input, boolean loadUniques) {
        Map<Integer, String> hashes = new HashMap<>();
        int attempt = 0;
        while (hashes.size() < 8) {
            String output = "";
            while(!output.startsWith("00000")) {
                output =  DigestUtils.md5Hex(input + attempt);
                attempt++;
            }
            saveNextChar(hashes, output, loadUniques);
        }
        return String.join("", hashes.values());
    }

    private static void saveNextChar(Map<Integer, String> hashes, String output, boolean loadUniques) {
        if(loadUniques) {
            try {
                int index = Character.getNumericValue(output.charAt(5));
                if (index < 8 && !hashes.containsKey(index)) {
                    hashes.put(index, String.valueOf(output.charAt(6)));
                }
            } catch (Exception ignored) {
                // Ignored
            }
        } else {
            hashes.put(hashes.size(), String.valueOf(output.charAt(5)));
        }
    }
}