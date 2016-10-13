package com.architecture.project.instruction;

import com.architecture.project.utils.ProjectUtils;

/**
 * Instructions construct factory.
 *
 * @author taoranxue on 9/15/16 3:41 PM.
 */
public class InstructionsFactory {
    private static final int[] LOAD_STORE = {001, 002, 003, 041, 042};
    private static final int[] ARITHMETIC_MAIN = {004, 005, 006, 007, 020, 021, 022, 023, 024, 025};
    private static final int[] TRANSFER = {010, 011, 012, 013, 014, 015, 016, 017};
    private static final int[] SHIFT_ROTATE = {031,032};
    private static final int[] MISCELLANEOUS={070,071,072,073,074,075,076,077};
    private static final int[] IO_INSTRUCTIONS = {061, 062, 063};

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
        } else if (ProjectUtils.inArrays(this.instruction.getOperatorCode(), ARITHMETIC_MAIN)) {
            System.out.println(this.instruction);
            return new ArithmeticMainInstructions(this.instruction);
        } else if (ProjectUtils.inArrays(this.instruction.getOperatorCode(), TRANSFER)) {
            System.out.println(this.instruction);
            return new TransferInstructions(this.instruction);
        } else if(ProjectUtils.inArrays(this.instruction.getOperatorCode(), SHIFT_ROTATE)){
            System.out.println(this.instruction);
            return new ShiftRotateInstructions(this.instruction);
        } else if(ProjectUtils.inArrays(this.instruction.getOperatorCode(), MISCELLANEOUS)){
            System.out.println(this.instruction);
            return new MiscellaneousInstructions(this.instruction);
        } else if(ProjectUtils.inArrays(this.instruction.getOperatorCode(), IO_INSTRUCTIONS)) {
            System.out.println(this.instruction);
            return new IOInstructions(this.instruction);
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
