package exercises.aoc2017.day05;

import utilities.A_AOC;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * AdventOfCode 2017 day 5's instructions are <a href="https://adventofcode.com/2017/day/5">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2017/day/5/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if (isExample) {
            super.test("5", "10");
        } else {
            super.test("339351", "24315397");
        }
    }

    @Override
    public void run() {
        List<Integer> list = this.parseInput();
        solution1 = getSolution(list, false);
        solution2 = getSolution(list, true);
    }


    public String getSolution(List<Integer> list, boolean partTwo){
        List<Integer> myList = new ArrayList<>(list);
        int count = 0;
        int indice = 0;
        while (indice < myList.size() && indice >= 0){
            int currentValue = myList.get(indice);
            int newIndice = indice + currentValue;
            int newValue = partTwo && currentValue >= 3 ? currentValue -1 : currentValue + 1;
            myList.set(indice,newValue);
            indice = newIndice;
            count++;
        }
        return String.valueOf(count);
    }


    private List<Integer> parseInput(){
        List<Integer> res = new ArrayList<>();
        for(String item: inputList){
            res.add(Integer.parseInt(item));
        }
        return res;
    }
}