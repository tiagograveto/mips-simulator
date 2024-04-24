package arquitetura.mips.instructions.entities;

import arquitetura.mips.instructions.PipelineSimulator;
import arquitetura.mips.instructions.entities.enums.OPCode;

public class AddInstruction extends InstructionImpl {

    public AddInstruction(OPCode opcode, String op1, String op2, String op3, boolean loop) {
        super(opcode, op1, op2, op3, loop);
    }

    public void execute(PipelineSimulator pipelineSimulator) {
        super.memory(pipelineSimulator);
    }

    public void memory(PipelineSimulator pipelineSimulator) {
        super.memory(pipelineSimulator);
    }

    public void write_back(PipelineSimulator pipelineSimulator) {
        super.memory(pipelineSimulator);
        int op2 = Integer.parseInt(this.op2.replace("R", ""));
        int op3 = Integer.parseInt(this.op3);
        pipelineSimulator.getRegisters()[op2] = op3;
        if (this.loop) {

        }
    }
}
