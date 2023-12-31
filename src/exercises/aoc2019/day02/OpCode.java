package exercises.aoc2019.day02;

public enum OpCode {
    ADD(1),
    MUL(2),
    END(99);

    private final int code;

    OpCode(int code) {
        this.code = code;
    }

    public static OpCode fromValue(int code) {
        for (OpCode opCode : values()) {
            if (opCode.code == code) {
                return opCode;
            }
        }
        throw new IllegalArgumentException("Il n'y a pas de OpCode pour " + code);
    }
}