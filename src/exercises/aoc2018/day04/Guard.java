package exercises.aoc2018.day04;

import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Guard {
    @Getter
    private int guardId;
    private final Map<Integer, Integer> guardSleep = new HashMap<>();

    public Guard(int guardId) {
        this.guardId = guardId;
    }

    public int compareTo(Guard guard) {
        return this.getFullSleepTime() - guard.getFullSleepTime();
    }

    public void incrementSleep(int minute) {
        int newSleep = guardSleep.getOrDefault(minute, 0) + 1;
        guardSleep.put(minute, newSleep);
    }

    public int getFullSleepTime() {
        return guardSleep.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getMostFrequentSleptMinute() {
        return guardSleep.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    public int getBigestFrequency() {
        return guardSleep.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();
    }

    public Integer getMinuteByFrequency(int frequency) {
        if(guardSleep.containsValue(frequency)) {
            return guardSleep.entrySet().stream()
                    .filter(integerIntegerEntry -> integerIntegerEntry.getValue() == frequency)
                    .findFirst()
                    .map(Map.Entry::getKey)
                    .orElseThrow();
        }
        return null;
    }
}
