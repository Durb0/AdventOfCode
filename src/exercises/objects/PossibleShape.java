package exercises.objects;

import lombok.Data;

import java.util.List;

@Data
public class PossibleShape {
    List<Integer> lengths;

    public PossibleShape(List<Integer> lengths) {
        this.lengths = lengths;
    }


    public boolean isValid() {
        int max = lengths.stream().mapToInt(Integer::intValue).max().orElse(0);
        int sumMin = lengths.stream().mapToInt(Integer::intValue).sum() - max;
        return max < sumMin;
    }
}
