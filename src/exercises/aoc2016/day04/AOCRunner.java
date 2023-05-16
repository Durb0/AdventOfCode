package exercises.aoc2016.day04;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import utilities.errors.NotAcceptedValue;
import utilities.A_AOC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
    private record RoomData(String name, int id, String checksum){}

    @Override
    public void test() {
        if(isExample) {
            super.test("1514", "404");
        } else {
            super.test("137896", "501");
        }
    }

    @Override
    public void run() {
        List<RoomData> realRooms = getRealRooms();
        solution1 = realRooms.stream()
                .map(RoomData::id)
                .mapToInt(Integer::intValue)
                .sum();
        solution2 = realRooms.stream()
                .map(AOCRunner::uncypherRoomName)
                .filter(nameIdPair -> nameIdPair.getLeft().contains("northpole"))
                .map(Pair::getRight)
                .findFirst()
                .orElse(0);
    }

    private List<RoomData> getRealRooms() {
        List<RoomData> realRooms = new ArrayList<>();
        for(String line : inputList) {
            RoomData roomData = extractRoomData(line);
            if(isRealRoom(roomData.name, roomData.checksum)) {
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


    private boolean isRealRoom(String name, String checksum) {
        Map<Character, Integer> frequency = new HashMap<>();
        for(char c : name.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        String newCheckSum = frequency.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // filter by char
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) // filter by frequency
                .map(Map.Entry::getKey) // get char only
                .map(String::valueOf) // convert to string
                .filter(Predicate.not("-"::equals)) // remove '-'
                .limit(5) // get top five
                .collect(Collectors.joining(""));

        return newCheckSum.equals(checksum);
    }

    private static Pair<String, Integer> uncypherRoomName(RoomData roomData) {
        String input = roomData.name;
        StringBuilder output = new StringBuilder();

        for (int position = 0; position < input.length(); position++) {
            char character = input.charAt(position);
            char newChar = shiftCharacter(character, roomData.id);
            output.append(newChar);
        }

        return new ImmutablePair<>(output.toString(), roomData.id);
    }

    private static char shiftCharacter(char character, int shift) {
        if (Character.isLetter(character)) {
            int delta = (character - 'a' + shift) % 26;
            return (char) ('a' + delta);
        }
        return  ' ';
    }
}