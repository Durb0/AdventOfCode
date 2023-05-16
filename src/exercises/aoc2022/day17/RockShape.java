package exercises.aoc2022.day17;


import exercises.objects.Shape;

public enum RockShape {
    R1, R2, R3, R4, R5;

    private final Shape rockShape;

    RockShape() {
        this.rockShape = new Shape(this);
    }

    public Shape getShape() {
        return rockShape.copy();
    }

    public RockShape next() {
        return switch (this) {
            case R1 -> R2;
            case R2 -> R3;
            case R3 -> R4;
            case R4 -> R5;
            case R5 -> R1;
        };
    }
}
