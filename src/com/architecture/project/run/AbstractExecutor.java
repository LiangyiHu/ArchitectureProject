package com.architecture.project.run;

import com.architecture.project.instruction.Instruction;
import com.architecture.project.instruction.InstructionsFactory;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processor.registers.Registers;

import java.util.List;

/**
 * Abstract parent class of executor class.
 *
 * @author taoranxue on 9/18/16 9:39 PM.
 */
public abstract class AbstractExecutor {
    private List<Character> instructionList;
    private int startAddress;
    private boolean step = false;

    public boolean isStep() {
        return step;
    }

    public void setStep(boolean step) {
        this.step = step;
    }

    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }

    public void setInstructionList(List<Character> instructionList) {
        this.instructionList = instructionList;
    }

    public void start() {
        char address = Registers.programCounter.getOne();
        char instruction = MainMemory.fetch(address);
        Registers.instructionRegister.setOne(instruction);
        Registers.programCounter.addPC();
        if (instruction == 0) return;
        System.out.println(Integer.toBinaryString(instruction));
        new InstructionsFactory(new Instruction(instruction)).getInstructions().execute();
        if (!isStep()) start();
    }


    /**
     * load to memory
     */
    public void load() {
        MainMemory.store(instructionList, startAddress);
    }

}
