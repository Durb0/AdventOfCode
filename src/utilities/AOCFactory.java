package utilities;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AOCFactory {
    private AOCFactory() {
    }

    /**
     * <pre>
     *     Using a day and a year, return AOC implementation to use.
     *     Example : getAOC(2022, 01) will return the AOC implementation of the AdventOfCode of 2022/12/01
     * </pre>
     *
     * @param year : the year of the AdventOfCode puzzle
     * @param day  : the day of the AdventOfCode puzzle
     * @param isExample : if true, use the example input
     *
     * @return the AOC implementation to use
     *
     * @throws ClassNotFoundException if year or day doesn't match an exercise already done
     * @throws NoSuchMethodException should not be thrown
     * @throws InvocationTargetException should not be thrown
     * @throws InstantiationException should not be thrown
     * @throws IllegalAccessException should not be thrown
     */
    public static A_AOC getAOC(int year, int day, boolean isExample) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        String z = (day < 10) ? "0" : "";
        String classPath = "exercises.aoc" + year + ".day" + z + day + ".AOCRunner";

        // Get the AOC class to run
        Class<?> classRef = Class.forName(classPath);
        A_AOC aoc = (A_AOC) classRef.getDeclaredConstructor().newInstance();
        aoc.setExample(isExample);
        aoc.setDay(day);
        aoc.setYear(year);
        aoc.setInput();

        return aoc;
    }
}
