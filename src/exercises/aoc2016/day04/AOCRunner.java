package exercises.aoc2016.day04;

import org.apache.commons.lang3.StringUtils;
import utilities.A_AOC;
import utilities.errors.NotAcceptedValue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <pre>
 * AdventOfCode 2016 day 4's instructions are <a href="https://adventofcode.com/2016/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2016/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if (isExample) {
            super.test("1514", "404");
        } else {
            super.test("137896", "501");
        }
    }

    @Override
    public void run() {
        List<RoomData> realRooms = getRealRooms();
        solution1 = realRooms.stream()
                .map(RoomData::getId)
                .mapToInt(Integer::intValue)
                .sum();
        solution2 = realRooms.stream()
                .filter(roomData -> roomData.uncypherRoomName().contains("northpole"))
                .map(RoomData::getId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    if (list.isEmpty()) {
                        throw new NoSuchElementException("No rooms are related to the North Pole");
                    } else if (list.size() > 1) {
                        throw new IllegalArgumentException("Too many elements related to the North Pole");
                    }
                    return list.get(0);
                }));
    }

    private List<RoomData> getRealRooms() {
        List<RoomData> realRooms = new ArrayList<>();
        for (String line : inputList) {
            RoomData roomData = extractRoomData(line);
            if (roomData.isReal()) {
                realRooms.add(roomData);
            }
        }
        return realRooms;
    }

    private RoomData extractRoomData(String line) {
        Pattern patternAfterName = Pattern.compile("\\d+.+");
        Matcher matcherAfterName = patternAfterName.matcher(line);
        if (matcherAfterName.find()) {
            String afterName = matcherAfterName.group();
            String name = StringUtils.removeEnd(line, afterName);
            String[] split = afterName.split("\\[");
            int id = Integer.parseInt(split[0]);
            String checksum = StringUtils.removeEnd(split[1], "]");
            return new RoomData(id, name, checksum);
        }
        throw new NotAcceptedValue(line);
    }
}