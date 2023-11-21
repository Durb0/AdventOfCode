package exercises.aoc2022.day15;

import exercises.objects.Position;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Sensor {
    private final Position position;
    private final Position nearestBeaconPosition;
    private List<ImmutablePair<Position, Position>> emptyZones = new ArrayList<>();

    @Override
    public String toString() {
        return this.position.toString() + "; Sensors at : " + nearestBeaconPosition;
    }

    public ImmutablePair<Position, Position> scanEmptyLine(int yCoordinate) {
        int manhattanDistance = position.getManhattanDistance(nearestBeaconPosition);
        if(yCoordinate < position.getY() - manhattanDistance || yCoordinate > position.getY() + manhattanDistance) {
            return null;
        }
        int distance = manhattanDistance - Math.abs(position.getY() - yCoordinate);
        int minX = position.getX() - distance;
        int maxX = position.getX() + distance;

        Position begin = new Position(minX, yCoordinate);
        Position end = new Position(maxX, yCoordinate);
        return new ImmutablePair<>(begin, end);
    }
}
