package arquitetura.mips.instructions.entities;

import arquitetura.mips.instructions.PipelineSimulator;
import arquitetura.mips.instructions.entities.enums.OPCode;

public class InstructionImpl {

    OPCode opcode;
    String op1;
    String op2;
    String op3;
    boolean loop;

    public InstructionImpl(OPCode opcode, String op1, String op2, String op3, boolean loop) {
        this.opcode = opcode;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.loop = loop;
    }

    public InstructionImpl(OPCode opcode) {
        this.opcode = opcode;
        this.op1 = "";
        this.op2 = "";
        this.op3 = "";
        this.loop = true;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public OPCode getOpcode() {
        return opcode;
    }

    public void execute(PipelineSimulator pipelineSimulator) {
        pipelineSimulator.log("Execute: " + this);
    }
    public void memory(PipelineSimulator pipelineSimulator) {
        pipelineSimulator.log("Memory: " + this);
    }
    public void write_back(PipelineSimulator pipelineSimulator) {
        pipelineSimulator.log("Write back: " + this);
    }

    @Override
    public String toString() {
        return opcode.name().toLowerCase() + " " +
                op1 + " " +
                op2 + " " +
                op3 + " ";
    }
}