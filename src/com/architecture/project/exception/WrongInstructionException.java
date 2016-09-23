package com.architecture.project.exception;

/**
 * Wrong instruction exception.
 *
 * @author taoranxue on 9/18/16 1:10 AM.
 */
public class WrongInstructionException extends RuntimeException {
    /**
     * Constructs an <code>WrongInstructionException</code> with no
     * detail message.
     */
    public WrongInstructionException() {
        throw new RuntimeException("Wrong Instruction!!!");
    }
}
