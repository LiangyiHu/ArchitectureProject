package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import com.architecture.project.utils.ProjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author taoranxue on 9/15/16 3:41 PM.
 */
public class InstructionsFactory {
    private static final int[] LOAD_STORE = {001, 002, 003, 041, 042};

    private Instruction instruction;


    /**
     *
     * @param instruction
     */
    public InstructionsFactory(Instruction instruction) {
        this.instruction = instruction;
    }

    public Instructions getInstructions() {
        if (ProjectUtils.inArrays(this.instruction.getOperatorCode(), LOAD_STORE)) {
            return new LoadStoreInstructions(this.instruction);
        }
        // ... other instructions
        return null;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

}
