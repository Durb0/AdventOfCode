package utilities;

import lombok.Data;

@Data
public class LauncherArgs {
    int day;
    int year;
    boolean print;
    boolean isExample;
    boolean getFromURL;
    boolean testAll;
    String message;

    public LauncherArgs() {
        this(1, 2015, false, false, false, "");
    }

    public LauncherArgs(int day, int year, boolean print, boolean isExample, boolean testAll, String message) {
        this.day = day;
        this.year = year;
        this.print = print;
        this.isExample = isExample;
        this.message = message;
        this.testAll = testAll;
    }
}
