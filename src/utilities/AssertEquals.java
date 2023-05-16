package utilities;

public class AssertEquals {
    private AssertEquals(){}
    public static void equals(Object solution, Object expected) throws AssertionError {
        if(solution != expected && !solution.toString().equals(expected.toString())) {
            throw new AssertionError("Solution " + solution + " not equals to expected " + expected);
        }
    }
}
