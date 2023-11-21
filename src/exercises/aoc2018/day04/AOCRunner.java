package exercises.aoc2018.day04;

import utilities.A_AOC;
import utilities.Printer;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

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
        long grosDormeurId = getPlusGrosDormeur(dodoSessions);

        Printer.println("notre gros dormeur est -> " + grosDormeurId);

        long bestMinute = getBestDodoMinute(dodoSessions, grosDormeurId);

        Printer.println("meilleur minute pour " + grosDormeurId + " -> " + bestMinute);

        solution1 = bestMinute * grosDormeurId;

    }

    private long getPlusGrosDormeur(List<DodoSession> dodoSessions) {
        HashMap<Long, Long> leaderBoard = new HashMap<>();

        dodoSessions.forEach(dodoSession -> {
            if (leaderBoard.containsKey(dodoSession.getGuardID())) {
                Long newResult = leaderBoard.get(dodoSession.getGuardID()) + dodoSession.calculateDodoTime();
                leaderBoard.replace(dodoSession.getGuardID(), newResult);
            } else {
                leaderBoard.put(dodoSession.getGuardID(), dodoSession.calculateDodoTime());
            }
        });

        return Collections.max(leaderBoard.entrySet(), Map.Entry.comparingByValue()).getKey();
    }


    private long getBestDodoMinute(List<DodoSession> dodoSessions, Long guardId) {
        //On garde uniquement les dodosessions de notre gros dormeur
        List<DodoSession> listFiltered = dodoSessions.stream()
                .filter(dodoSession -> dodoSession.getGuardID().equals(guardId)).toList();


        Map<Long, Long> calendar = new HashMap<>();
        listFiltered.forEach(
                dodoSession -> {
                    long count = 0;
                    while (count < dodoSession.calculateDodoTime()) {
                        long minute = (count + dodoSession.getStart().getMinutes()) % 60;
                        if (calendar.containsKey(minute)) {
                            long newCount = calendar.get(minute) + 1;
                            calendar.replace(minute, newCount);
                        } else {
                            calendar.put(minute, 1L);
                        }
                        count++;
                    }
                }
        );


        return Collections.max(calendar.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private List<DodoSession> parseInputList(List<String> inputList) {



        List<DodoSession> dodoSessions = new ArrayList<>();

        Map<Date, String> sortedLogs = new TreeMap<>(); //plus utiliser Map (programation par interface)


        //on recupère proprement les logs
        for (String input : inputList) {
            String[] lineCut = input.split("] ");
            String log = lineCut[1];
            String dateString = lineCut[0].substring(1);
            sortedLogs.put(dateParser.parse(dateString, new ParsePosition(0)), log);
        }

        Printer.println(sortedLogs);

        Long guardID = null;
        Date start = null;
        Date end;

        //On crée nos instance de DodoSessions à partir de nos logs triés
        for (Map.Entry<Date, String> log : sortedLogs.entrySet()) {
            String[] logList = log.getValue().split(" ");
            switch (logList[0]) {
                case ("Guard") -> guardID = Long.parseLong(logList[1].substring(1));
                case ("falls") -> start = log.getKey();
                case ("wakes") -> {
                    end = log.getKey();
                    dodoSessions.add(new DodoSession(guardID, start, end));
                }
                default -> Printer.println("Valeur manquante -> " + logList[0]); //faire un Throw
            } // pas opti
        }

        return dodoSessions;
    }
}