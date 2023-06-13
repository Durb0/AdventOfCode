package exercises.aoc2018.day04;

import utilities.A_AOC;
import utilities.Printer;
import utilities.errors.NoSuchElementInListException;
import utilities.errors.NotAcceptedValue;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * AdventOfCode 2018 day 4's instructions are <a href="https://adventofcode.com/2018/day/4">here</a>
 * Exercise's input is <a href="https://adventofcode.com/2018/day/4/input">here</a>
 * </pre>
 */
public class AOCRunner extends A_AOC {

    SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //notre formatter pour recuperer les dates

    @Override
    public void test() {
        if (isExample) {
            super.test(240, 4455);
        } else {
            super.test(63509, null);
        }
    }

    @Override
    public void run() {
        List<DodoSession> dodoSessions = parseInputList(inputList);
        Long grosDormeurId = getPlusGrosDormeur(dodoSessions);

        Printer.println("notre gros dormeur est -> " + grosDormeurId);

        Long bestMinute = getBestDodoMinute(dodoSessions, grosDormeurId);

        Printer.println("meilleur minute pour " + grosDormeurId+ " -> " + bestMinute);

        solution1 = bestMinute * grosDormeurId;

    }

    private Long getPlusGrosDormeur(List<DodoSession> dodoSessions) {
        HashMap<Long, Long> leaderBoard = new HashMap<>();

        dodoSessions.forEach(dodoSession -> {
                    if (leaderBoard.containsKey(dodoSession.getGuardID())) {
                        Long newResult = leaderBoard.get(dodoSession.getGuardID()) + dodoSession.calculateDodoTime();
                        leaderBoard.put(dodoSession.getGuardID(), newResult);
                    } else {
                        leaderBoard.put(dodoSession.getGuardID(), dodoSession.calculateDodoTime());
                    }
        });

        return Collections.max(leaderBoard.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    private Long getBestDodoMinute(List<DodoSession> dodoSessions, Long guardId){
        //On garde uniquement les dodosessions de notre gros dormeur
        dodoSessions = dodoSessions.stream()
                .filter(dodoSession -> dodoSession.getGuardID().equals(guardId)).toList();


        HashMap<Long, Long> calendar = new HashMap<>();
        dodoSessions.forEach(
                dodoSession -> {
                    Long count = 0L;
                    while(count < dodoSession.calculateDodoTime()){
                        Long minute = (count + dodoSession.getStart().getMinutes()) % 60;
                        if(calendar.containsKey(minute)){
                            Long newCount = calendar.get(minute) + 1;
                            calendar.put(minute,newCount);
                        }
                        else{
                            calendar.put(minute,1L);
                        }
                        count++;
                    }
                }
        );


        return Collections.max(calendar.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private List<DodoSession> parseInputList(List<String> inputList) {

        Long guardID = null;
        Date start = null;
        Date end = null;

        List<DodoSession> dodoSessions = new ArrayList<>();

        HashMap<Date,String> logs = new HashMap<>();


        //on recupère proprement les logs
        for (String input :
                inputList) {
            String[] lineCut = input.split("]");
            String log = lineCut[1].substring(1);
            String dateString = lineCut[0].substring(1);
            logs.put(dateParser.parse(dateString,new ParsePosition(0)), log);
        }

        //on trie les logs selon leurs dates
        TreeMap<Date,String> sortedLogs = new TreeMap<Date,String>(logs);
        Printer.println(sortedLogs);


        //On crée nos instance de DodoSessions à partir de nos logs triés
        for(Map.Entry<Date,String> log : sortedLogs.entrySet()){
                String[] logList = log.getValue().split(" ");
                switch (logList[0]) {
                    case ("Guard") -> guardID = Long.parseLong(logList[1].substring(1));
                    case ("falls") -> start = log.getKey();
                    case ("wakes") -> {
                        end = log.getKey();
                        dodoSessions.add(new DodoSession(guardID,start,end));
                    }
                    default -> Printer.println("Valeur manquante -> "+ logList[0]);
                }
        }
//

        return dodoSessions;
    }
}