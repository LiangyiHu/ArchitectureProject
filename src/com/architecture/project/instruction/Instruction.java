package com.architecture.project.instruction;

import com.architecture.project.exception.InstructionIndexOutOfBoundsException;
import com.architecture.project.processer.registers.Register;

/**
 * @author taoranxue on 9/15/16 3:45 PM.
 */
public class Instruction extends Register {

    public Instruction() {
        this.data = 0;
    }

    public Instruction(String s) {
        super(s);
    }

    public Instruction(char c) {
        super(c);
    }

    public Instruction subInstruction(int beginIndex, int endIndex) {
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

        this.data = (char)(data << (beginIndex));
        System.out.println(Integer.toBinaryString(data));
        this.data = (char)(data >> (beginIndex + WORD_LENGTH - endIndex));

        return this;
    }
}
