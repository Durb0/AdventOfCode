package exercises.aoc2016.day05;

import org.apache.hc.client5.http.utils.Hex;
import utilities.A_AOC;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
            super.test("f77a0e6e", "");
        }
    }

    @Override
    public void run() {
        String door = inputList.get(0);
        solution1 = findDoorPassword(door);
        solution2 = findDoorPassword2(door);
    }

    private static String findDoorPassword(String input) {
        StringBuilder passwordBuilder = new StringBuilder();
        int attempt = 0;
        MessageDigest md = getMD5();
        while (passwordBuilder.length() < 8) {
            String output = "";
            while(!output.startsWith("00000")) {
                byte[] result = md.digest((input + attempt).getBytes());
                output = Hex.encodeHexString(result);
                attempt++;
            }
            passwordBuilder.append(output.charAt(5));
        }
        return passwordBuilder.toString();
    }

    private static String findDoorPassword2(String input) {
        Map<Integer, String> hashes = new HashMap<>();
        int attempt = 0;
        MessageDigest md = getMD5();
        while (hashes.size() < 8) {
            String output = "";
            while(!output.startsWith("00000")) {
                byte[] result = md.digest((input + attempt).getBytes());
                output = Hex.encodeHexString(result);
                attempt++;
            }
            try {
                int index = Character.getNumericValue(output.charAt(5));
                if (index < 8 && !hashes.containsKey(index)) {
                    hashes.put(index, String.valueOf(output.charAt(6)));
                }
            } catch (Exception ignored) {
                // Ignored
            }
        }
        return String.join("", hashes.values());
    }

    private static MessageDigest getMD5() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}