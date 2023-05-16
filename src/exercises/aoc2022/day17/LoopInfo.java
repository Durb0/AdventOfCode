package exercises.aoc2022.day17;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoopInfo {
    private RockShape rock;
    private int directionIndex;
    private int shapeXPosition;
    private int blocks;
    private int rockIndex;

    public boolean equals(LoopInfo loopInfo) {
        return rock == loopInfo.rock && directionIndex == loopInfo.directionIndex && shapeXPosition == loopInfo.shapeXPosition;
    }

    public String toString() {
        return rock + " " + directionIndex + " " + shapeXPosition + " " + blocks + " " + rockIndex;
    }
}
