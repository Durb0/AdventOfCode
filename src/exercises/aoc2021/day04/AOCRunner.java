package exercises.aoc2021.day04;

import utilities.A_AOC;
import utilities.errors.NoSuchElementInListException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2021 day 4's instructions are <a href="https://adventofcode.com/2021/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2021/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    private final List<Bingo> grids = new ArrayList<>();

    @Override
    public void test() {
        if(isExample) {
            super.test(4512, 1924);
        } else {
            super.test(54275, 13158);
        }
    }

    @Override
    public void run() {
        List<String> plays = new ArrayList<>(Arrays.asList(inputList.get(0).split(",")));

        List<String> gridLines = new ArrayList<>();
        for (String str : inputList.subList(2, inputList.size())) {
            if (str.equals("")) {
                grids.add(new Bingo(gridLines));
                gridLines = new ArrayList<>();
            } else {
                gridLines.add(str);
            }
        }
        grids.add(new Bingo(gridLines));

        play(plays);
        Bingo bingo = getFirstWinner(plays);
        solution1 = result(bingo);

        Collections.reverse(plays);
        Bingo bingo2 = getFirstWinner(plays);
        solution2 = result(bingo2);
    }

    private Bingo getFirstWinner(List<String> plays) {
        for (String play : plays) {
            List<Bingo> winnerGrid = grids.stream().filter(bingo -> bingo.getWinPlay().equals(play)).toList();
            if (!winnerGrid.isEmpty()) {
                return winnerGrid.get(0);
            }
        }
        throw new NoSuchElementInListException();
    }

    private int result(Bingo bingo) {
        int sum = bingo.getGridContentByCheck(false).stream().map(Integer::parseInt).mapToInt(Integer::intValue).sum();
        return sum * Integer.parseInt(bingo.getWinPlay());
    }

    private void play(List<String> plays) {
        for (String play : plays) {
            List<Bingo> notWinGrid = grids.stream().filter(bingo -> !bingo.isWin()).toList();
            for (Bingo grid : notWinGrid) {
                grid.play(play);
            }
        }
    }

}
