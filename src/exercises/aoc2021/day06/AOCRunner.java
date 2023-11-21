package exercises.aoc2021.day06;

import utilities.A_AOC;
import java.util.Arrays;
import java.util.List;


/**
 * <pre>
 * AdventOfCode 2021 day 6's instructions are <a href="https://adventofcode.com/2021/day/6">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2021/day/6/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    //Création d'une liste fixe de 8 éléments (pour chaque jour)
    Population population = new Population();

    @Override
    public void test() {
        if(isExample) {
            super.test(5934, 26984457539L);
        } else {
            super.test(353274,1609314870967L);
        }
    }

    @Override
    public void run() {
        this.parseInput();
        solution1 = this.numberOfFishAfter(80);
        solution2 = this.numberOfFishAfter(256);
    }

    private Long numberOfFishAfter(Integer nbDays){
        Population myPopulation = new Population(this.population);
        for(int day = 0; day < nbDays; day++){
            myPopulation = myPopulation.nextDay();
        }
        return myPopulation.getNumberOfFish();
    }
    private void parseInput(){
        List<Integer> input = Arrays.stream(this.inputList.get(0).split(",")).map(Integer::parseInt).toList();
        input.forEach(value -> this.population.set(value, this.population.get(value) + 1));
    }
}
