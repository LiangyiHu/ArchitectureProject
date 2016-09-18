package com.architecture.project.exception;

/**
 * @author taoranxue on 9/18/16 1:10 AM.
 */
public class WrongInstructionException extends RuntimeException {
    public WrongInstructionException() {
        throw new RuntimeException("Wrong Instruction!!!");
    }
}
