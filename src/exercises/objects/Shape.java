package exercises.objects;

import exercises.aoc2022.day17.RockShape;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Shape {
    private final List<Position> positions;
    private int id;
    public Shape(int id) {
        this.positions = new ArrayList<>();
        this.id = id;
    }

    public Shape(RockShape rockShape) {
        switch (rockShape.name()) {
            case "R1" -> positions = Position.shape(List.of(
                    new Position(0, 0),
                    new Position(3, 0)));
            case "R2" -> positions = Position.shape(List.of(
                    new Position(1, 0),
                    new Position(1, 2),
                    new Position(0, 1),
                    new Position(2, 1)));
            case "R3" -> positions = Position.shape(List.of(
                    new Position(0, 0),
                    new Position(2, 0),
                    new Position(2, 2)));
            case "R4" -> positions = Position.shape(List.of(
                    new Position(0, 0),
                    new Position(0, 3)));
            case "R5" -> positions = Position.shape(List.of(
                    new Position(0, 0),
                    new Position(0, 1),
                    new Position(1, 1),
                    new Position(1, 0)));
            default -> positions = new ArrayList<>();
        }
    }

    public Shape(List<Position> positions) {
        this.positions = positions;
    }

    public Shape copy() {
        List<Position> copyPositions = new ArrayList<>();
        for (Position position : this.positions) {
            copyPositions.add(new Position(position));
        }
        return new Shape(copyPositions);
    }

    public void initPosition(int x, int y) {
        for(Position position : positions) {
            position.setX(position.getX() + x);
            position.setY(position.getY() + y);
        }
    }

    public int getMinY() {
        return positions.stream()
                .map(Position::getY)
                .min(Integer::compareTo)
                .orElse(0);
    }

    public int getMinX() {
        return positions.stream()
                .map(Position::getX)
                .min(Integer::compareTo)
                .orElse(0);
    }

    public int getMaxX() {
        return positions.stream()
                .map(Position::getX)
                .max(Integer::compareTo)
                .orElse(0);
    }

    public void setRectangle(Position start, int width, int height) {
        int x = start.getX();
        int y = start.getY();
        for(int w = 0; w < width; w++) {
            for(int h = 0; h < height; h++) {
                positions.add(new Position(x + w, y + h));
            }
        }
    }
}
