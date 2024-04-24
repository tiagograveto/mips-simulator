package arquitetura.mips.instructions;

import arquitetura.mips.instructions.handlers.MIPSConverter;
import arquitetura.mips.instructions.entities.InstructionImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Classe para representar o simulador de pipeline
public class PipelineSimulator {

    private List<String> instructionMemory;
    private String stringFetch;
    private Map<String, Integer> fillValues;
    private int programCounter;
    private int[] registers;

    private InstructionImpl toExecute;
    private InstructionImpl toMemory;
    private InstructionImpl toWriteBack;

    public int[] getRegisters() {
        return registers;
    }

    public PipelineSimulator() {
        instructionMemory = new ArrayList<>();
        fillValues = new LinkedHashMap<>();
        programCounter = 0;
        stringFetch = "";
        registers = new int[32];
    }

    public void log(String str) {
        System.out.println(str);
    }

    public void loadProgram(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\" + filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");

                if (line.contains(".fill")) {
                    fillValues.put(parts[0], Integer.valueOf(parts[2]));
                }
                else {
                    instructionMemory.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void fetch(int time) {
        this.stringFetch = instructionMemory.get(time);
        System.out.println("Busca: " + stringFetch);
    }

    public InstructionImpl decode(int time) {
        String commandLine = MIPSConverter.replaceInstruction(fillValues, instructionMemory.get(time));
        System.out.println("Decodificando: " + commandLine);
        InstructionImpl instruction = MIPSConverter.convertLineToInstruction(commandLine);
        this.toExecute = instruction;
        return instruction;
    }

    public void executing(int time) {
        this.toExecute.execute(this);
    }

    public void memory(int time) {
        this.toMemory.memory(this);
    }

    public void write_back(int time) {
        this.toWriteBack.write_back(this);
    }


    // Método para executar o pipeline
    public void runPipeline() throws InterruptedException {
        while (true) {
            programCounter++;

            fetch(programCounter);

            if (programCounter >= 2) {
                InstructionImpl instruction = decode(programCounter - 1);
            }

            System.out.println();
            Thread.sleep(500);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PipelineSimulator simulator = new PipelineSimulator();
        simulator.loadProgram("programa.txt");
        simulator.runPipeline();
    }
}
