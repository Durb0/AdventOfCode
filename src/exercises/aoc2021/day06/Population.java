package exercises.aoc2021.day06;
import lombok.EqualsAndHashCode;
import java.util.ArrayList;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
public class Population extends ArrayList<Long>{

    public Population(){
        super(Collections.nCopies(9, 0L));
    }

    public Population(Population population){
        super(population);
    }

    public void addTo(Integer index, Long value){
        this.set(index, this.get(index) + value);
    }

    public Long getNumberOfFish(){
        Long count = 0L;
        for (Long fishs : this) {
            count += fishs;
        }
        return count;
    }

    public Population nextDay(){
        Population newDay = new Population();
        for(int caseIndex = 0; caseIndex < this.size(); caseIndex ++){
            if(caseIndex == 0){
                Long nbOfFish = this.get(0);
                newDay.addTo(6, nbOfFish);
                newDay.addTo(8, nbOfFish);
            } else {
                newDay.addTo(caseIndex - 1, this.get(caseIndex));
            }
        }
        return newDay;
    }

}
