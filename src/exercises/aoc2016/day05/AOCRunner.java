package exercises.aoc2016.day05;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import utilities.A_AOC;

/**
 * <pre>
 * AdventOfCode 2016 day 5's instructions are <a href="https://adventofcode.com/2016/day/5">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2016/day/5/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    @Override
    public void test() {
        if(isExample) {
            super.test("18f47a30", "05ace8e3");
        } else {
            super.test("", "437e60fc");
        }
    }

    @Override
    public void run() {
        String doorId = this.inputList.get(0);
        solution1 = this.exo1(doorId);
        solution2 = this.exo2(doorId);
    }

    private String exo1(String doorId){
        StringBuilder soluce = new StringBuilder();
        int index = 0;
        while(soluce.length() != 8){
            String hash = Hashing.md5().hashString(doorId + index, Charsets.UTF_8).toString();
            if(isValidHash(hash)){
                System.out.println("new password find");
                System.out.println("index : " +index);
                System.out.println("hash : " +hash);
                soluce.append(hash.charAt(5));
            }
            index++;
        }
        return soluce.toString();
    }

    private String exo2(String doorId){
        StringBuilder soluce = new StringBuilder("________");
        int index = 0;
        while(soluce.toString().contains("_")){
            String hash = Hashing.md5().hashString(doorId + index, Charsets.UTF_8).toString();
            if(isValidHash(hash)){
                int position = -1;
                try{
                    position = Integer.parseInt(String.valueOf(hash.charAt(5)));
                    if(position < 8 && soluce.charAt(position) == '_'){
                        soluce.setCharAt(position,hash.charAt(6));
                        System.out.println(soluce);
                    }
                } catch (NumberFormatException e){
                    System.out.println(hash.charAt(5) + " n'est pas un numéro; index : "+ index);
                }
            }
            index++;
        }
        return soluce.toString();
    }

    private boolean isValidHash(String hash){
        for(int i = 0; i <= 4; i++){
            if(hash.charAt(i) != '0'){
                return false;
            }
        }
        return true;
    }

}