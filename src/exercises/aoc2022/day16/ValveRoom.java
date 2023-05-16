package exercises.aoc2022.day16;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Getter
public class ValveRoom {
    private final String name;
    private final int flow;
    private final Map<String, Integer> connectedRoomsDistance;
    @Setter
    private int openTime = 1;

    public ValveRoom(ValveRoom valveRoom, int openTime) {
        this.name = valveRoom.name;
        this.flow = valveRoom.flow;
        this.connectedRoomsDistance = valveRoom.getConnectedRoomsDistance();
        this.openTime = openTime;
    }

    public static List<ValveRoom> copyList(List<ValveRoom> valveRooms) {
        return new ArrayList<>(valveRooms);
    }

    public Integer getRoomDistance(String roomName) {
        return connectedRoomsDistance.get(roomName);
    }

    public int getFlow() {
        return flow * openTime;
    }

    public void addConnectedRoomDistance(String valveRoomName, int distance) {
        connectedRoomsDistance.put(valveRoomName, distance);
    }
}
