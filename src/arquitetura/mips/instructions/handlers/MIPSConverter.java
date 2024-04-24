package arquitetura.mips.instructions.handlers;

import arquitetura.mips.instructions.entities.InstructionImpl;
import arquitetura.mips.instructions.entities.enums.OPCode;
import arquitetura.mips.instructions.exception.MIPSException;

import java.util.Map;

public class MIPSConverter {

    public static InstructionImpl convertLineToInstruction(String string) {
        string = string.toLowerCase().trim();
        String[] parts = string.trim().split("\\s+");

        if (string.startsWith("noop")) {
            return new InstructionImpl(OPCode.NOOP);
        }
        else if (string.startsWith("loop")) {
            String withoutLoop = string.replace("loop ", "");
            parts = withoutLoop.trim().split("\\s+");
            return new InstructionImpl(OPCode.fromString(parts[0].toLowerCase()), parts[1], parts[2], parts[3], true);
        }
        else if (string.startsWith("addi") || string.startsWith("beq")) {
            return new InstructionImpl(OPCode.fromString(parts[0].toLowerCase()), parts[1], parts[2], parts[3], false);
        }
        else if (string.startsWith("done") && string.equalsIgnoreCase("done halt")) {
            return new InstructionImpl(OPCode.DONE);
        }
        else {
            throw new MIPSException("Houve um erro na formatação da string: " + string);
        }
    }

    public static String replaceInstruction(Map<String, Integer> map, String instruction) {
        for (String str : instruction.split(" ")) {
            for (Map.Entry<String, Integer> entryset : map.entrySet()) {
                if (str.equalsIgnoreCase(entryset.getKey())) {
                    instruction = instruction.replace(entryset.getKey(), entryset.getValue() + "");
                }
            }
        }
        return instruction;
    }
}
