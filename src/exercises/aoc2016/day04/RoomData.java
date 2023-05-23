package exercises.aoc2016.day04;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class RoomData {
    private String name;
    private int id;
    private String checksum;

    public boolean isReal() {
        Map<String, Long> charFrequencies = getCharFrequencies();

        String newCheckSum = charFrequencies.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // filter by char
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) // filter by frequency
                .limit(5) // get top five
                .map(Map.Entry::getKey) // convert char to string
                .collect(Collectors.joining(""));

        return newCheckSum.equals(checksum);
    }

    private Map<String, Long> getCharFrequencies() {
        String[] cleanName = name
                .replace("-", "")
                .split("");
        Map<String, Long> frequencies = new HashMap<>();
        List<String> charList = Arrays.asList(cleanName);
        Set<String> charSet = new HashSet<>(charList);

        for (String character : charSet) {
            long frequency = charList.stream().filter(s -> s.equals(character)).count();
            frequencies.put(character, frequency);
        }
        return frequencies;
    }

    public String uncypherRoomName() {
        StringBuilder output = new StringBuilder();

        for (int position = 0; position < name.length(); position++) {
            char character = name.charAt(position);
            char newChar = shiftCharacter(character, id);
            output.append(newChar);
        }

        return output.toString();
    }

    private char shiftCharacter(char character, int shift) {
        if (Character.isLetter(character)) {
            int delta = (character - 'a' + shift) % 26;
            return (char) ('a' + delta);
        }
        return ' ';
    }

}
