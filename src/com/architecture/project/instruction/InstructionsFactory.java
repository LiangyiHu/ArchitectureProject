package com.architecture.project.instruction;

import com.architecture.project.utils.ProjectUtils;

/**
 * Instructions construct factory.
 *
 * @author taoranxue on 9/15/16 3:41 PM.
 */
public class InstructionsFactory {
    private static final int[] LOAD_STORE = {001, 002, 003, 041, 042};
    private static final int[] ARITHMETIC_MAIN = {004, 005, 006, 007, 020,021, 022,023,024,025};
    private static final int[] TRANSFER = {010, 011, 012, 013, 014,015, 016,017};

    private Instruction instruction;


    /**
     * Construct factory class with instruction of the service class.
     *
     * @param instruction the instruction to be executed.
     */
    public InstructionsFactory(Instruction instruction) {
        this.instruction = instruction;
    }

    /**
     * Instantiate the particular instruction service class.
     *
     * @return instruction service class.
     */
    public Instructions getInstructions() {
        if (ProjectUtils.inArrays(this.instruction.getOperatorCode(), LOAD_STORE)) {
            System.out.println(this.instruction);
            return new LoadStoreInstructions(this.instruction);
        }
        else if(ProjectUtils.inArrays(this.instruction.getOperatorCode(), ARITHMETIC_MAIN)){
            System.out.println(this.instruction);
            return new ArithmeticMainInstructions(this.instruction);
        }
        else if(ProjectUtils.inArrays(this.instruction.getOperatorCode(), TRANSFER)){
            System.out.println(this.instruction);
            return new TransferInstructions(this.instruction);
        }

        // ... other instructions
        return null;
    }

    /**
     * Get instruction.
     *
     * @return instruction.
     */
    public Instruction getInstruction() {
        return instruction;
    }

    /**
     * Set instruction.
     *
     * @param instruction instruction.
     */
    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

}
