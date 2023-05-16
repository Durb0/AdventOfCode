package utilities;

public class Printer {

    private Printer() {
    }

    public static void println(Object o) {
        print(o);
        print("\r\n");
    }

    public static void print(Object o) {
        System.out.print(o);
    }
}
