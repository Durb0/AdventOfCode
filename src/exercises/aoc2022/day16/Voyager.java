package exercises.aoc2022.day16;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Voyager {
    private final int id;
    private List<ValveRoom> visitedRooms;
    private int timeLeft;
    private ValveRoom lastRoom;

    public Voyager(int id, ValveRoom startRoom) {
        this.id = id;
        visitedRooms = new ArrayList<>();
        this.addRoom(startRoom);
    }

    public Voyager(Voyager voyager) {
        this(voyager.id, null);
        this.visitedRooms = ValveRoom.copyList(voyager.visitedRooms);
        this.lastRoom = voyager.lastRoom;
        this.timeLeft = this.visitedRooms.stream()
                .mapToInt(ValveRoom::getOpenTime)
                .min()
                .orElse(0);
    }

    public static List<Voyager> copyList(List<Voyager> voyagers) {
        List<Voyager> copyOfVoyagers = new ArrayList<>();
        for (Voyager voyager : voyagers) {
            copyOfVoyagers.add(new Voyager(voyager));
        }
        return copyOfVoyagers;
    }

    public static int getPressure(List<Voyager> voyagers) {
        // TODO : Supprimable ?
        return voyagers.stream()
                .mapToInt(voyager -> voyager.visitedRooms.stream()
                        .mapToInt(ValveRoom::getFlow)
                        .sum())
                .sum();
    }

    public void addRoom(ValveRoom room) {
        if(room == null) {
            return;
        }
        this.visitedRooms.add(room);
        this.lastRoom = room;
        timeLeft = room.getOpenTime();
    }

    public ValveRoom getLastRoom() {
        return lastRoom;
    }
}
