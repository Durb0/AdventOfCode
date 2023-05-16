package exercises.aoc2018.day04;

import utilities.errors.NoSuchElementInListException;
import utilities.errors.NotAcceptedValue;
import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * AdventOfCode 2018 day 4's instructions are <a href="https://adventofcode.com/2018/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2018/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    private static final String GUARD_ID = "]\\D*(\\d+)";
    private static final String MINUTE = ":(\\d+)";

    @Override
    public void test() {
        if (isExample) {
            super.test("240", "4455");
        } else {
            super.test("38813", "141071");
        }
    }

    @Override
    public void run() {
        sortInput();
        List<Guard> guards = parseInput();
        Guard bigestSleeper = getBigestSleeper(guards);
        solution1 = bigestSleeper.getGuardId() * bigestSleeper.getMostFrequentSleptMinute();
        solution2 = computeSolution2(guards);
    }

    private void sortInput() {
        inputList = inputList.stream().sorted(String::compareTo).toList();
    }

    private List<Guard> parseInput() {
        List<Guard> guards = new ArrayList<>();
        int currentGuardId = 0;
        String sleepingLine = "";
        for (String line : inputList) {
            if (isBeginShift(line)) {
                currentGuardId = extractData(line, GUARD_ID);
            } else if (isWakeUp(line)) {
                extractGuard(guards, currentGuardId, sleepingLine, line);
            } else {
                sleepingLine = line;
            }
        }
        return guards;
    }

    private boolean isBeginShift(String line) {
        return line.contains("begins shift");
    }

    private boolean isWakeUp(String line) {
        return line.contains("wakes up");
    }

    private void extractGuard(List<Guard> guards, int currentGuardId, String sleepingLine, String wakeUpLine) {
        int timeSleep = extractData(sleepingLine, MINUTE);
        int timeWakeUp = extractData(wakeUpLine, MINUTE);
        for (int minute = timeSleep; minute < timeWakeUp; minute++) {
            Guard guard = guards.stream()
                    .filter(guard1 -> guard1.getGuardId() == currentGuardId)
                    .findFirst()
                    .orElse(new Guard(currentGuardId));
            if (!guards.contains(guard)) {
                guards.add(guard);
            }
            guard.incrementSleep(minute);
        }
    }

    private int extractData(String line, String information) {
        Pattern pattern = Pattern.compile(information);
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        throw new NotAcceptedValue(line);
    }

    private static Guard getBigestSleeper(List<Guard> guards) {
        return guards.stream()
                .max(Guard::compareTo)
                .orElseThrow(NoSuchElementException::new);
    }

    private int computeSolution2(List<Guard> guards) {
        int bigestFrequencyOfThemAll = guards.stream()
                .map(Guard::getBigestFrequency)
                .max(Integer::compareTo)
                .orElseThrow();

        for(Guard guard : guards) {
            Integer minuteByFrequency = guard.getMinuteByFrequency(bigestFrequencyOfThemAll);
            if(guard.getMinuteByFrequency(bigestFrequencyOfThemAll) != null) {
                return guard.getGuardId() * minuteByFrequency;
            }
        }

        throw new NoSuchElementInListException();
    }
}