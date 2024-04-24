package arquitetura.mips.instructions.entities.enums;

public enum OPCode {
    NOOP(0),
    ADDI(3),
    BEQ(3),
    ADD(3),
    DONE(1)
    ;

    private int lenght;

    public int getLenght() {
        return lenght;
    }

    public String toString() {
        return name().toLowerCase();
    }

    public static OPCode fromString(String string) {
        for (OPCode opCode : values()) {
            if (opCode.name().equalsIgnoreCase(string)) {
                return opCode;
            }
        }
        return null;
    }

    OPCode(int i) {
        this.lenght = i;
    }
}
