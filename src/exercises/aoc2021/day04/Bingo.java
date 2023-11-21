package exercises.aoc2021.day04;

import lombok.Data;
import utilities.Printer;

import java.util.ArrayList;
import java.util.List;

@Data
public class Bingo {

    private List<Grid> grids = new ArrayList<>(List.of());

    private List<Integer> numbers;


    List<Grid> copyGrids(){
        return grids;
    }
    Grid findWinner(){
        List<Grid> gridsTemp = copyGrids();
        for (Integer number : numbers){
            for(Grid grid : gridsTemp){
                if(grid.applyNumber(number)){
                    Printer.println("Winner");
                    grid.printGrid();
                    return grid;
                }
            }
        }
        return null;
    }

    Grid findLoser(){
        List<Grid> gridsTemp = copyGrids();
        for (Integer number: numbers){
            Grid grid = getGridLoser(gridsTemp, number);
            if (grid != null) return grid;
        }
        return null;
    }

    private static Grid getGridLoser(List<Grid> grids, Integer number) {
        for(int g = grids.size() - 1; g >= 0; g--){
            Grid grid = grids.get(g);
            if(grid.applyNumber(number)){
                if(grids.size() == 1){
                    Printer.println("Loser");
                    grid.printGrid();
                    return grid;
                }
                else{
                    grids.remove(grid);
                }
            }
        }
        return null;
    }
}
