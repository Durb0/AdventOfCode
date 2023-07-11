package exercises.aoc2021.day04;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Grid {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private List<List<Cell>> grid = new ArrayList<>(List.of());

    private Integer finalNumber = 0;

    boolean applyNumber(Integer number){
        for(int r = 0; r < grid.size(); r++){
            List<Cell> row = grid.get(r);
            for(int c = 0; c < row.size(); c++){
                Cell cell = row.get(c);
                if(cell.applyNumber(number) && (checkBingo(r, c))){
                        finalNumber = number;
                        return true;

                }
            }
        }
        return false;
    }

    boolean checkBingo(int row, int column){
        int verticalCount = countAxis(row, column, 1,0);
        int horizontalCount = countAxis(row, column, 0, 1);
        if (horizontalCount == grid.size()){
            return true;
        }
        return verticalCount == grid.size();
    }

    int countAxis(int row,int column,int vertical, int horizontal){
        return countDirection(row, column, vertical, horizontal) + countDirection(row, column, -vertical, -horizontal) +1;
    }

    int countDirection(int row, int column, int vertical, int horizontal){
        int newRow = row + vertical;
        int newColumn = column + horizontal;
        if(newRow >= grid.size() || newRow < 0 || newColumn >= grid.size() || newColumn < 0){
            return 0;
        }
        if(!grid.get(newRow).get(newColumn).isPassed()){
            return 0;
        }
        return 1 + countDirection(newRow, newColumn, vertical, horizontal);

    }

    void printGrid(){
        for(int r = 0; r < grid.size(); r++){
            List<Cell> row = grid.get(r);
            for(int c = 0; c < row.size(); c++ ) {
                Cell cell = row.get(c);
                if(cell.getValue() < 10){
                    System.out.print(" ");
                }
                if(cell.isPassed()){
                    System.out.print(ANSI_BLUE + cell.getValue() + ANSI_RESET + " ");
                }
                else{
                    System.out.print(ANSI_WHITE + cell.getValue() + ANSI_RESET + " ");
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    int result(){
        return finalNumber * sumNotPassed();
    }

    int sumNotPassed(){
        int res = 0;
        for(int r = 0; r < grid.size(); r++){
            List<Cell> row = grid.get(r);
            for(int c = 0; c < row.size(); c++){
                Cell cell = row.get(c);
                res += !cell.isPassed() ? cell.getValue() : 0;
            }
        }
        return res;
    }


}

