package utilities;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

import static utilities.FileIO.DELIMITER;

/**
 * AdventOfCode generic interface that is returned by the AOCFactory
 * Must be used to run a program
 */
public abstract class A_AOC {
    /**
     * Contains the input for the exercise
     */
    protected List<String> inputList;

    @Getter
    protected Object solution1;
    @Getter
    protected Object solution2;
    @Getter
    @Setter
    private int day;
    @Getter
    @Setter
    private int year;
    @Setter
    protected boolean isExample;
    @Setter
    protected boolean getFromURL;

    /**
     * Set the basic input for the instance
     *
     * @param file : The input file path (located in resources) to run
     * @throws IOException if the input file does not exist
     */
    public void readInputFile(String file) throws IOException {
        inputList = FileIO.readListFromFile(file);
    }

    /**
     * Set the basic input for the instance using the AOC URL
     *
     * @param url : The input file path (located in resources) to run
     * @throws IOException if the input file does not exist
     */
    public void readInputURL(String url) throws IOException {
        inputList = FileIO.readListFromURL(url);
        String inputStr = String.join(DELIMITER, inputList);
        FileIO.writeInFile(getPuzzleInputPath(), inputStr);
    }

    /**
     * Compare the solution with the expected solution
     *
     * @param expectedPart1 : The expected output for solution 1
     * @param expectedPart2 : The expected output for solution 2
     */
    protected void test(Object expectedPart1, Object expectedPart2) {
        String date = "Day " + day + " year " + year;
        try {
            AssertEquals.equals(this.solution1, expectedPart1);
        } catch (AssertionError e) {
            Printer.println(date);
            Printer.println(e);
        }
        try {
            AssertEquals.equals(this.solution2, expectedPart2);
        } catch (AssertionError e) {
            Printer.println(date);
            Printer.println(e);
        }
    }

    public abstract void test();

    /**
     * Run a program using a file as data input
     * The legacy variable inputList contains the data inputs
     */
    public abstract void run();

    public void setInput() throws IOException {
        // If possible, get the input from the URL, else from our local files
        if(!isExample) {
            getPuzzleInput();
        } else {
            readInputFile(getExampleInputPath());
        }
    }

    private void getPuzzleInput() throws IOException {
        if(getFromURL) {
            try {
                readInputURL(getPuzzleInputURL());
            } catch (Exception e) {
                readInputFile(getPuzzleInputPath());
            }
        } else {
            readInputFile(getPuzzleInputPath());
        }
    }

    private String getPuzzleInputURL() {
        return "https://adventofcode.com/" + year + "/day/" + day + "/input";
    }

    private String getExampleInputPath() {
        return "example_input/" + year + "/aoc" + day + ".txt";
    }

    private String getPuzzleInputPath() {
        return "puzzle_input/" + year + "/aoc" + day + ".txt";
    }
}
