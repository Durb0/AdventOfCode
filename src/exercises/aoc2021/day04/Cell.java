package exercises.aoc2021.day04;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Cell {

    private Integer value;

    private boolean passed;

    public boolean applyNumber(Integer number) {
        if(Objects.equals(number, value)){
            passed = true;
            return true;
        }
        return false;
    }
}
