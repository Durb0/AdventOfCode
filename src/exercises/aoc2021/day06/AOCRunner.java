package exercises.aoc2021.day06;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * AdventOfCode 2021 day 6's instructions are <a href="https://adventofcode.com/2021/day/6">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2021/day/6/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private final Map<Integer, Long> fishes = new HashMap<>();
    private final List<Long> newbornFishes = new ArrayList<>();

    @Override
    public void test() {
        if(isExample) {
            super.test(5934, 26984457539L);
        } else {
            super.test(386755, 1732731810807L);
        }
    }

    @Override
    public void run() {
        int days1 = 80;
        int days2 = 256;
        
        for (String item : inputList) {
            Arrays.stream(item.split(",")).map(Integer::parseInt).forEach(fishDay -> fishes.compute(fishDay, (k, v) -> (v != null) ? v + 1 : 1));

            for (int day = 0; day < days1; day++) {
                live(day);
            }
            solution1 = fishes.values().stream().reduce(Long::sum).orElse(0L);

            for (int day = days1; day < days2; day++) {
                live(day);
            }
            solution2 = fishes.values().stream().reduce(Long::sum).orElse(0L);
        }
    }

    private void live(int day) {
        int dayOfWeek = day % 7;
        long babiesCantProduct = (day - 2 > 0) ? newbornFishes.get(day - 2) : 0;
        long newFishes = (fishes.get(dayOfWeek) != null) ? fishes.get(dayOfWeek) - babiesCantProduct : 0;
        newbornFishes.add(newFishes);
        fishes.compute((day + 2) % 7, (k, v) -> (v != null) ? v + newFishes : newFishes);
    }

}
