package exercises.aoc2016.day04;

import org.apache.commons.lang3.StringUtils;
import utilities.A_AOC;
import utilities.errors.NotAcceptedValue;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                .filter(nameIdPair -> nameIdPair.uncypherRoomName().contains("northpole"))
                .map(RoomData::getId)
                .findFirst()
                .orElse(0);
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
        Pattern pattern = Pattern.compile("\\d+.+");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String match = matcher.group();
            String name = StringUtils.removeEnd(line, match);
            String[] split = match.split("\\[");
            int id = Integer.parseInt(split[0]);
            String checksum = StringUtils.removeEnd(split[1], "]");
            return new RoomData(name, id, checksum);
        }
        throw new NotAcceptedValue(line);
    }
}