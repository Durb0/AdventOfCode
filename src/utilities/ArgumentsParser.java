package utilities;

public class ArgumentsParser {
    private ArgumentsParser(){}

    public static LauncherArgs parseLauncherArgs(String[] args) {
        LauncherArgs argsRecord = new LauncherArgs();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-y", "-year" -> argsRecord.setYear(Integer.parseInt(args[i + 1]));
                case "-d", "-day" -> argsRecord.setDay(Integer.parseInt(args[i + 1]));
                case "-p", "-print" -> argsRecord.setPrint(true);
                case "-e", "-isExample" -> argsRecord.setExample(true);
                case "-t", "-testAll" -> argsRecord.setTestAll(true);
                case "-h", "-help" -> {
                    String message = String.join("\r\n",
                            "",
                            "Available arguments: ",
                            "",
                            "<TYPE, DEFAULT>",
                            "-y, -year <int, 2015>\t: Year of the exercise",
                            "-d, -day <int, 1>\t: Day of the exercise",
                            "-p, -print\t: Print the answer",
                            "-e, -isExample\t: Use the example input",
                            "-t, -testAll\t: Use the example input. Override upper options",
                            "-h\t: Help. Override upper options");
                    argsRecord.setMessage(message);
                }
                default -> {
                    // Do nothing
                }
            }
        }
        return argsRecord;
    }
}
