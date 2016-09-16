package com.architecture.project.exception;

/**
 * @author taoranxue on 9/15/16 6:57 PM.
 */
public class MemoryAddressingOutOfBoundsException extends IndexOutOfBoundsException {
    public MemoryAddressingOutOfBoundsException(int index) {
        super("memory addressing index out of range: " + index);
    }
}