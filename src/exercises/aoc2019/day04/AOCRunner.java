package exercises.aoc2019.day04;


import utilities.A_AOC;
import utilities.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * AdventOfCode 2019 day 4's instructions are <a href="https://adventofcode.com/2019/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2019/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {
    Pattern regexDoublon = Pattern.compile(".*(\\d)\\1+.*");
    Pattern regexDoublon2 = Pattern.compile("^(((\\d)*(\\d)+)?(?!\\4))(\\d)\\5(?!\\5)\\d*$");
    Pattern regexIncrease = Pattern.compile("^0*1*2*3*4*5*6*7*8*9*$");


    @Override
    public void test() {
        if (isExample) {
            super.test("62", "1");
        } else {
            super.test("931", "0");
        }
    }

    @Override
    public void run(){
        List<Integer> interval = parseInput();

        long start1 = System.currentTimeMillis();
        List<Integer> s1 = countValidPasswords(interval, false);
        long end1 = System.currentTimeMillis();
        Printer.println("temps s1 -> " + (end1 - start1));

        solution1 = s1.size();
        solution2 = countValidPasswords(interval, true).size();

        Printer.println(solution2);
    }

    private List<Integer> countValidPasswords(List<Integer> interval, boolean s2){
        int n = interval.get(0);
        List<Integer> count = new ArrayList<>(List.of());
        while(n <= interval.get(1)){
            boolean ci = checkIncrease(n);
            boolean cd = s2 ? checkDoublon2(n) : checkDoublon(n);
            if(!ci || !cd){
                if(!ci){
                    n = applyIncrease(n);
                    Printer.println("increase de n -> "+ n);
                }
                else{
                    n = s2 ? applyDoublon2(n) : applyDoublon(n);
                    Printer.println("ajout d'un doublon à n -> " + n);
                }
            }
            else{
                Printer.println("valid -> " + n);
                count.add(n);
                n++;
            }

        }
        return count;
    }

    private int applyIncrease(int n){
        List<Integer> digits = intToList(n);
        int valuePlate = -1;
        for (int i = 1; i < digits.size(); i++){
            if(valuePlate == -1){
                if(digits.get(i) < digits.get(i-1)){
                    valuePlate = digits.get(i-1);
                    digits.set(i, valuePlate);
                }
            }
            else{
                digits.set(i, valuePlate);
            }
        }
        String concatStr = listToInt(digits);

        return Integer.parseInt(concatStr);
    }



    private int applyDoublon(int n){
        List<Integer> digits = intToList(n);
        int lastDigit = digits.get(digits.size() - 1);
        int beforeLastDigitIndice = digits.size() - 2;
        digits.set(beforeLastDigitIndice, lastDigit);

        String concatStr = digits.stream()
                .map(Object::toString).collect(Collectors.joining(""));

        return Integer.parseInt(concatStr);
    }

    private int applyDoublon2(int number){
        int numberTemp = number;
        boolean valid = false;
        while (!valid){
            numberTemp++;
            valid = checkDoublon2(numberTemp);
        }
        return numberTemp;
    }

    private boolean checkDoublon(int number){
        return regexDoublon.matcher(Integer.toString(number)).find();
    }

    private boolean checkDoublon2(int number){
        return regexDoublon2.matcher(Integer.toString(number)).find();
    }


    private boolean checkIncrease(int number){
        return regexIncrease.matcher(Integer.toString(number)).find();
    }


    private List<Integer> intToList(Integer integer){
        return new java.util.ArrayList<>(Integer.toString(integer).chars().map(c -> c - '0').boxed().toList());
    }

    private String listToInt(List<Integer> listToConcat){
        return listToConcat.stream()
                .map(Object::toString).collect(Collectors.joining(""));
    }

    private List<Integer> parseInput(){
        return Arrays.stream(inputList.get(0).split("-"))
                .map(Integer::parseInt)
                .toList();
    }
}