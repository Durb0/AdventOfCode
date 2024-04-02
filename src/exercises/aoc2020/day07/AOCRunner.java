package exercises.aoc2020.day07;

import utilities.A_AOC;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * AdventOfCode 2020 day 7's instructions are <a href="https://adventofcode.com/2019/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2019/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if (isExample) {
            super.test("4", "126");
        } else {
            super.test("265", "14177");
        }
    }

    @Override
    public void run(){
        String bagName= "shiny gold";
        ArrayList<Bag> bags = this.parseInput();
        solution1 = this.inHowManyBag(bags,bagName);
        solution2 = this.howManyBags(bags, bagName) - 1;
    }

    private Integer inHowManyBag(ArrayList<Bag> bags, String bagName){
        Set<String> step = new HashSet<>(List.of(bagName));
        Set<String> totals = new HashSet<>(List.of());
        while(!step.isEmpty()){
            Set<String> newStep = new HashSet<>(List.of());
            for(Bag bag : bags){
                for(Map.Entry<String,Integer> subBag: bag.getSubBags().entrySet()){
                    if(step.contains(subBag.getKey())){
                        newStep.add(bag.getName());
                    }
                }
            }
            totals.addAll(newStep);
            step = newStep;
        }
        return totals.size();
    }

    private Integer howManyBags(ArrayList<Bag> bags, String bagName) {
        int count = 1;
        try{
            Bag myBag = this.findBag(bags, bagName);
            for(Map.Entry<String, Integer> subBag: myBag.getSubBags().entrySet()){
                count += subBag.getValue() * this.howManyBags(bags, subBag.getKey());
            }
            return count;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Bag findBag(ArrayList<Bag> bags, String name) throws Exception {
        for(Bag bag: bags){
            if(Objects.equals(bag.getName(), name)){
                return bag;
            }
        }
        throw new Exception("Bag "+name+" not found");
    }


    private ArrayList<Bag> parseInput(){
        ArrayList<Bag> bags = new ArrayList<>();
        for(String input : inputList) {
            Pattern pattern = Pattern.compile("^(?<name>\\w+ \\w+) bags contain (?<subBags>(?:\\d+ \\w+ \\w+ bags?(?:, )?)+|no other bags).$");
            Matcher matcher = pattern.matcher(input);
            if(matcher.matches()) {
                Bag bag = new Bag(matcher.group("name"));
                List<String> subBagsInputs = List.of(matcher.group("subBags").split(", ?"));
                for(String subBagsInput: subBagsInputs){
                    Pattern subPattern = Pattern.compile("^(?<number>\\d)\\s(?<name>\\w+\\s\\w+)\\sbags?$");
                    Matcher subMatcher = subPattern.matcher(subBagsInput);
                    if(subMatcher.matches()){
                        bag.addSubBag(subMatcher.group("name"), Integer.valueOf(subMatcher.group("number")));
                    }
                }
                bags.add(bag);
            }
        }
        return bags;
    }
}