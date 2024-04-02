package exercises.aoc2020.day07;

import lombok.Getter;

import java.util.HashMap;

@Getter
class Bag{
    private String name;
    private HashMap<String,Integer> subBags;

    public Bag(String name){
        this.name = name;
        this.subBags = new HashMap<>();
    }

    public void addSubBag(String name, Integer quantity){
        this.subBags.put(name, quantity);
    }

    public String toString(){
        return "{\n" +
                "   name: " + this.name + "\n" +
                "   subs: " + this.subBags + "\n" +
                "}\n";
    }
}