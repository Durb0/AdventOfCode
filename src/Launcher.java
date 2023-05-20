import utilities.AOCFactory;
import utilities.A_AOC;
import utilities.FileIO;
import utilities.LauncherArgs;
import utilities.Printer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;

import static utilities.ArgumentsParser.parseLauncherArgs;

/**
 * Join AdventOfCode <a href="https://adventofcode.com">here</a>
 */
public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        LauncherArgs launcherArgs = parseLauncherArgs(args);
        saveLocalSession();

        String message = launcherArgs.getMessage();
        if (!message.isEmpty()) {
            Printer.println(message);
            return;
        }

        if (launcherArgs.isTestAll()) {
            runAllAOC(launcherArgs);
        } else {
            runAOC(launcherArgs);
        }
    }

    private static void runAllAOC(LauncherArgs launcherArgs) {
        for (int year = 2015; year < Year.now().getValue(); year++) {
            for (int day = 1; day <= 25; day++) {
                try {
                    launcherArgs.setYear(year);
                    launcherArgs.setDay(day);
                    Launcher.runAOC(launcherArgs);
                    Printer.println("Day " + day + " year " + year + " OK");
                } catch (ClassNotFoundException e) {
                    // Nothing
                } catch (Exception e) {
                    Printer.println(e.getMessage());
                }
            }
        }
    }

    private static void runAOC(LauncherArgs launcherArgs) throws ClassNotFoundException {
        A_AOC aoc;
        int year = launcherArgs.getYear();
        int day = launcherArgs.getDay();
        boolean isExample = launcherArgs.isExample();

        try {
            aoc = AOCFactory.getAOC(year, day, isExample);
        } catch (Exception e) {
            throw new ClassNotFoundException();
        }

        aoc.run();
        aoc.test();

        boolean print = launcherArgs.isPrint();
        if (print) {
            Printer.println("Solution 1 : " + aoc.getSolution1());
            Printer.println("Solution 2 : " + aoc.getSolution2());
        }
    }

    private static void saveLocalSession() throws IOException {
        try {
            FileIO.saveLocalSession();
        } catch (IOException e) {
            FileIO.createLocalSession();
            throw new FileNotFoundException("Votre fichier localSession a été créé, veuillez le renseigner");
        }
    }
}
