package exercises.aoc2022.day16;

import utilities.A_AOC;
import utilities.Timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * AdventOfCode 2022 day 16's instructions are <a href="https://adventofcode.com/2022/day/16">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2022/day/16/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private Volcano volcano;
    private static final String START_ROOM_NAME = "AA";

    @Override
    public void test() {
        if(isExample) {
            super.test(1651, 1707);
        } else {
            super.test(2029, 2723);
        }
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        timer.date();
        
        initVolcano(inputList);

        ValveRoom startRoom = volcano.getValveRoom(START_ROOM_NAME);
        startRoom.setOpenTime(30);

        solution1 = volcano.getMaxPressure(startRoom, 1);
        timer.time();

        // Will run in 26 minutes (quite long...)
        startRoom.setOpenTime(26);
        solution2 = volcano.getMaxPressure(startRoom, 2);
        timer.time();
    }

    /**
     * Set default graph to the Volcano
     *
     * @param list The input to parse
     */
    private void initVolcano(List<String> list) {
        volcano = new Volcano();
        list.stream().map(AOCRunner::cleanInput).forEach(this::addRoom);
        volcano.computeDistances(START_ROOM_NAME);
    }

    /**
     * Clear the input so we can have something more useful
     *
     * @param input : Something like -> alve AA has flow rate=0; tunnels lead to valves DD, II, BB
     * @return : Something like -> AA;O;DD,II,BB
     */
    private static String cleanInput(String input) {
        return input
                .replace("Valve ", "")
                .replace(" has flow rate=", ";")
                .replace("s ", " ")
                .replace(" tunnel lead to valve ", "")
                .replace(", ", ",");
    }

    /**
     * Turn an input string into a new room
     *
     * @param input : Something like -> AA;O;DD,II,BB
     */
    private void addRoom(String input) {
        String[] params = input.split(";");
        String name = params[0];
        int flow = Integer.parseInt(params[1]);
        Map<String, Integer> connectedRooms = new HashMap<>();
        for (String connectedRoomsStr : params[2].split(",")) {
            connectedRooms.put(connectedRoomsStr, 1);
        }
        ValveRoom valveRoom = new ValveRoom(name, flow, connectedRooms);
        volcano.addValveRoom(name, valveRoom);
    }
}
