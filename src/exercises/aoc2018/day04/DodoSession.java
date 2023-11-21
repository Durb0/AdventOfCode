package exercises.aoc2018.day04;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class DodoSession {
    private Long guardID;
    private Date start;
    private Date end;

    public Long calculateDodoTime(){
        return TimeUnit.MILLISECONDS.toMinutes(end.getTime() - start.getTime());
    }
}
