package com.architecture.project.instruction;

import com.architecture.project.exception.InstructionIndexOutOfBoundsException;
import com.architecture.project.processer.registers.Register;

/**
 * @author taoranxue on 9/15/16 3:45 PM.
 */
public class Instruction extends Register {
    private static final int OP_NUMBER = 6;

    public Instruction() {
        this.data = 0;
    }

    public Instruction(String s) {
        super(s);
    }

    public Instruction(char c) {
        super(c);
    }

    public int getOperatorCode() {
        return subInstruction(0, OP_NUMBER).parseInt();
    }

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
