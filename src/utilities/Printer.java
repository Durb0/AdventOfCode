package utilities;

import static utilities.FileIO.DELIMITER;

public class Printer {

    private Printer() {
    }

    public static void println(Object o) {
        print(o);
        print(DELIMITER);
    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
