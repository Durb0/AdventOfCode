package exercises.aoc2023.day01;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;


public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(142, 0);
        } else {
            super.test(0, 0);
        }
    }

    @Override
    public void run() {
        solution1 = calibrate();
        solution2 = calibrate2();
    }

    private Integer calibrate(){
        int total = 0;
        for(String line: inputList){
            String filter = line.replaceAll("\\D", "");
            int number = Integer.parseInt(String.valueOf(filter.charAt(0)) + filter.charAt(filter.length() - 1));
            total += number;
        }
        return total;
    }

    private Integer calibrate2(){
        List<Number> numbers = new ArrayList<>(List.of(
                new Number(0,"zero"),
        new Number(1, "one"),
        new Number(2, "two"),
        new Number(3, "three"),
        new Number(4, "four"),
        new Number(5, "five"),
        new Number(6, "six"),
        new Number(7, "sevent"),
        new Number(8, "eight"),
        new Number(9, "nine")
        ));

        return 0;

    }
}
