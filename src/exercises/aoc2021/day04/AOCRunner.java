package exercises.aoc2021.day04;

import utilities.A_AOC;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * AdventOfCode 2021 day 4's instructions are <a href="https://adventofcode.com/2021/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2021/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test(4512, 1924);
        } else {
            super.test(6592, 31755);
        }
    }

    @Override
    public void run() {
        Bingo myBingo = parseInput();


        Grid winner = myBingo.findWinner();
        solution1 = winner.result();

        Grid loser = myBingo.findLoser();
        solution2 = loser.result();
    }


    public Bingo parseInput(){
        Bingo bingo = new Bingo();
        //parse combinaison
        bingo.setNumbers(Arrays.stream(inputList.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList()));
        //remove first line
        inputList.remove(0);
        inputList.remove(0);
        Grid gridTmp = new Grid();
        for( String line : inputList) {
            if (line.equals("")){
                bingo.getGrids().add(gridTmp);
                gridTmp = new Grid();
            }
            else {
                List<Cell> row = Arrays.stream(line.split(" "))
                        .filter(v -> !v.equals(""))
                        .map(Integer::parseInt)
                        .map(value ->
                            new Cell.CellBuilder()
                                    .value(value)
                                    .passed(false)
                                    .build()
                        )
                        .toList();
                gridTmp.getGrid().add(row);
            }
        }
        bingo.getGrids().add(gridTmp);
        return bingo;
    }

}
