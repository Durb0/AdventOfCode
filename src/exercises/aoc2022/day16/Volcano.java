package exercises.aoc2022.day16;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Volcano {
    private static final int TIME_TO_OPEN = 1;
    private final Map<String, ValveRoom> valveRooms;
    private int maxPressure;

    public Volcano() {
        valveRooms = new HashMap<>();
    }

    public ValveRoom getValveRoom(String name) {
        return valveRooms.get(name);
    }

    public void addValveRoom(String name, ValveRoom valveRoom) {
        this.valveRooms.put(name, valveRoom);
    }

    /**
     * For each room, get the distance to each different rooms
     */
    public void computeDistances(String startRoom) {
        for (ValveRoom valveRoom : this.valveRooms.values()) {
            recursiveSetDistance(valveRoom.getName(), valveRoom, 0);
        }
        List<String> toDelete = this.valveRooms.keySet().stream()
                .filter(s -> this.valveRooms.get(s).getFlow() == 0)
                .filter(s -> !s.equals(startRoom))
                .toList();
        for(String delete : toDelete) {
            this.valveRooms.remove(delete);
        }
    }

    /**
     * For each room directly connected, get each other rooms and set distance + 1
     *
     * @param roomFrom : The room we are currently parsing
     * @param roomTo   : The next room
     * @param distance : The distance to set if inferior to previous one
     */
    private void recursiveSetDistance(String roomFrom, ValveRoom roomTo, int distance) {
        Integer savedDistance = roomTo.getConnectedRoomsDistance().get(roomFrom);
        if (savedDistance == null || savedDistance > distance || distance == 1) {
            if (distance != 1) {
                roomTo.addConnectedRoomDistance(roomFrom, distance);
            }
            roomTo.getConnectedRoomsDistance().keySet().stream()
                    .filter(s -> roomTo.getRoomDistance(s) == 1)
                    .forEach(nextRoomName -> recursiveSetDistance(roomFrom, valveRooms.get(nextRoomName), distance + 1));
        }
    }

    /**
     * For a starting room and a number of groups, parse the whole graph to find the best pressure we can possibly have
     *
     * @param startRoom : The room we shall start
     * @param groups    : The number of groups
     * @return : The max pressure possible
     */
    public Integer getMaxPressure(ValveRoom startRoom, int groups) {
        List<Voyager> voyagers = new ArrayList<>();
        for (int group = 0; group < groups; group++) {
            Voyager voyager = new Voyager(group, startRoom);
            voyagers.add(voyager);
        }
        maxPressure = 0;
        List<ValveRoom> neverVisitedRooms = valveRooms.values().stream().filter(valveRoom -> !valveRoom.equals(startRoom)).toList();
        computeMaxPressure(voyagers, neverVisitedRooms);
        return maxPressure;
    }

    public void computeMaxPressure(List<Voyager> voyagers, List<ValveRoom> neverVisitedRooms) {
        if (isMaxPressure(voyagers, neverVisitedRooms)) {
            return;
        }
        List<Pair<Voyager, ValveRoom>> cartesianProduct = getCartesianProduct(voyagers, neverVisitedRooms);
        boolean isComplete = neverVisitedRooms.isEmpty() || visitAllRoomsWithAllGroups(cartesianProduct, Voyager.copyList(voyagers), neverVisitedRooms);
        if (isComplete) {
            int currentPressure = Voyager.getPressure(voyagers);
            maxPressure = Integer.max(maxPressure, currentPressure);
        }
    }

    private boolean visitAllRoomsWithAllGroups(List<Pair<Voyager, ValveRoom>> cartesianProduct, List<Voyager> voyagers, List<ValveRoom> neverVisitedRooms) {
        boolean isComplete = true;
        for (Pair<Voyager, ValveRoom> coupleVVR : cartesianProduct) {
            Voyager voyager = coupleVVR.getLeft();
            ValveRoom room = coupleVVR.getRight();
            int newTimeBeforeBoom = newTimeAfterMoveAndOpen(voyager.getTimeLeft(), voyager.getLastRoom(), room);
            List<Pair<Voyager, ValveRoom>> subCartesianProduct = getSubCartesianProduct(cartesianProduct, voyager);
            if (neverVisitedRooms.contains(room) && newTimeBeforeBoom >= 0) {
                List<ValveRoom> newNeverVisitedRooms = ValveRoom.copyList(neverVisitedRooms);
                newNeverVisitedRooms.remove(room);
                ValveRoom nextRoom = visitNextRoom(room.getName(), newTimeBeforeBoom);
                List<Voyager> newVoyagers = Voyager.copyList(voyagers);
                setVoyagerNextRoom(newVoyagers, voyager, nextRoom);
                if (!subCartesianProduct.isEmpty()) {
                    visitAllRoomsWithAllGroups(subCartesianProduct, newVoyagers, newNeverVisitedRooms);
                } else {
                    computeMaxPressure(newVoyagers, newNeverVisitedRooms);
                }
                isComplete = false;
            }
        }
        return isComplete;
    }

    private boolean isMaxPressure(List<Voyager> voyagers, List<ValveRoom> nextRooms) {
        int currentPressure = Voyager.getPressure(voyagers);
        int maxTime = voyagers.stream()
                .mapToInt(Voyager::getTimeLeft)
                .max()
                .orElse(0);
        int possibleMax = nextRooms.stream()
                .mapToInt(room -> room.getFlow() * maxTime)
                .sum();
        return currentPressure + possibleMax <= maxPressure;
    }

    /**
     * Combine all the possibilities
     *
     * @param voyagers  : the first list to combine
     * @param nextRooms : the second list to combine
     */
    private static List<Pair<Voyager, ValveRoom>> getCartesianProduct(List<Voyager> voyagers, List<ValveRoom> nextRooms) {
        List<Pair<Voyager, ValveRoom>> cartesianProduct = new ArrayList<>();
        for (Voyager voyager : voyagers) {
            for (ValveRoom nextRoom : nextRooms) {
                Pair<Voyager, ValveRoom> newPair = new ImmutablePair<>(voyager, nextRoom);
                cartesianProduct.add(newPair);
            }
        }
        return cartesianProduct;
    }

    /**
     * Get a new cartesian product excluding a value
     *
     * @param cartesianProduct : the map to reduce
     * @param voyager          : the value to remove
     * @return : cartesianProduct excluding voyager
     */
    private static List<Pair<Voyager, ValveRoom>> getSubCartesianProduct(List<Pair<Voyager, ValveRoom>> cartesianProduct, Voyager voyager) {
        return cartesianProduct.stream()
                .filter(pair -> pair.getLeft() != voyager)
                .toList();
    }

    private ValveRoom visitNextRoom(String nextRoomName, int newTimeBeforeBoom) {
        return new ValveRoom(getValveRoom(nextRoomName), newTimeBeforeBoom);
    }

    private int newTimeAfterMoveAndOpen(int timeBeforeBoom, ValveRoom currentRoom, ValveRoom nextRoom) {
        return timeBeforeBoom - (currentRoom.getRoomDistance(nextRoom.getName()) + TIME_TO_OPEN);
    }

    private void setVoyagerNextRoom(List<Voyager> voyagers, Voyager voyager, ValveRoom nextRoom) {
        voyagers.stream()
                .filter(voyager1 -> voyager1.getId() == voyager.getId())
                .findFirst()
                .ifPresent(value -> value.addRoom(nextRoom));
    }
}
