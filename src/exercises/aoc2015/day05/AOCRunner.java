package exercises.aoc2015.day05;

import utilities.A_AOC;

/**
 * <pre>
 * AdventOfCode 2015 day 3's instructions are <a href="https://adventofcode.com/2015/day/3">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2015/day/3/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private final String[] forbiddens = new String[] {"ab", "cd", "pq", "xy"};

    record RecordValidPassword(int solution1, int solution2){}

    @Override
    public void test() {
        if(isExample) {
            super.test("2", "3");
        } else {
            super.test("238", "69");
        }
    }

    @Override
    public void run() {
        RecordValidPassword recordValidPassword = countValidPassword();
        solution1 = recordValidPassword.solution1;
        solution2 = recordValidPassword.solution2;
    }

    private RecordValidPassword countValidPassword() {
        int solution1 = 0;
        int solution2 = 0;
        for(String password : inputList) {
            if(!containsForbidden(password) && containsDoublon(password) && containsThreeVowels(password)) {
                solution1++;
            }
            if(containsTwoPairs(password) && containsDoublonWithBetween(password)) {
                solution2++;
            }
        }
        return new RecordValidPassword(solution1, solution2);
    }

    private boolean containsForbidden(String password) {
        for(String forbidden : forbiddens) {
            if(password.contains(forbidden)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsThreeVowels(String password) {
        int countVowels = 0;
        for(char currentChar : password.toCharArray()) {
            if("aeiou".contains(String.valueOf(currentChar))) {
                countVowels++;
            }
            if(countVowels >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDoublon(String password) {
        char[] passwordChars = password.toCharArray();

        for(int i = 0; i < passwordChars.length - 1; i++) {
            char currentChar = passwordChars[i];
            char nextChar = passwordChars[i+1];
            if(currentChar == nextChar) {
                return true;
            }
        }
        return false;
    }

    private boolean containsTwoPairs(String password) {
        for(int i = 0; i < password.length()-2; i++) {
            String subStr = password.substring(i, i+2);
            String testPassword = password.replace(subStr, "");
            if(testPassword.length() <= password.length() - 4) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDoublonWithBetween(String password) {
        char[] passwordChars = password.toCharArray();

        for(int i = 0; i < passwordChars.length - 2; i++) {
            char currentChar = passwordChars[i];
            char nextChar = passwordChars[i+2];
            if(currentChar == nextChar) {
                return true;
            }
        }
        return false;
    }
}