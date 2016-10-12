package com.architecture.project.instruction;

import com.architecture.project.exception.InstructionIndexOutOfBoundsException;
import com.architecture.project.processor.registers.Register;

/**
 * Instruction entity, used to save all instruction content. Subclass of {@linkplain Register}
 *
 * @author taoranxue on 9/15/16 3:45 PM.
 */
public class Instruction extends Register {
    private static final int OP_NUMBER = 6;

    /**
     * Default constructor initializes data zero.
     */
    public Instruction() {
        this.data = 0;
    }

    /**
     * Construct with {@code String} data.
     *
     * @param s string data.
     */
    public Instruction(String s) {
        super(s);
    }

    /**
     * Construct with based number.
     *
     * @param s    number string.
     * @param base base number.
     */
    public Instruction(String s, int base) {
        super(s, base);
    }

    /**
     * Construct with {@code Character}.
     *
     * @param c char number.
     */
    public Instruction(char c) {
        super(c);
    }

    /**
     * Get the first 6 bits of the instruction as the operator code.
     *
     * @return operator code.
     */
    public int getOperatorCode() {
        return subInstruction(0, OP_NUMBER).parseInt();
    }

    /**
     * Get the sub-data of instruction.
     *
     * @param beginIndex begin index.
     * @param endIndex   end index.
     * @return sub instruction.
     */
    public Instruction subInstruction(int beginIndex, int endIndex) {
        Instruction ins = new Instruction(this.data);
        if (beginIndex < 0) {
            throw new InstructionIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > WORD_LENGTH) {
            throw new InstructionIndexOutOfBoundsException(endIndex);
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new InstructionIndexOutOfBoundsException(subLen);
        }

        ins.data = (char) (ins.data << (beginIndex));
        ins.data = (char) (ins.data >> (beginIndex + WORD_LENGTH - endIndex));

        return ins;
    }
}
