package exercises.aoc2015.day04;

import org.apache.hc.client5.http.utils.Hex;
import utilities.A_AOC;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 * AdventOfCode 2015 day 4's instructions are <a href="https://adventofcode.com/2015/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2015/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test("1048970", "5714438");
        } else {
            super.test("254575", "1038736");
        }
    }

    @Override
    public void run() {
        String input = inputList.get(0);
        solution1 = findPasswordForLeading(input, "00000");
        solution2 = findPasswordForLeading(input, "000000");
    }

    private static int findPasswordForLeading(String input, String leading) {
        int password = 0;
        String output = "";
        MessageDigest md = getMD5();
        while(!output.startsWith(leading)) {
            password++;
            byte[] result = md.digest((input + password).getBytes());
            output = Hex.encodeHexString(result);
        }
        return password;
    }

    private static MessageDigest getMD5() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}