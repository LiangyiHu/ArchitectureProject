package com.architecture.project.exception;

/**
 * Instruction index out of bounds exception.
 *
 * @author taoranxue on 9/15/16 6:57 PM.
 */
public class InstructionIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public InstructionIndexOutOfBoundsException(int index) {
        super("Instruction index out of range: " + index);
    }
}
