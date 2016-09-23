package com.architecture.project.exception;

/**
 * Memory addressing out of bounds exception.
 *
 * @author taoranxue on 9/15/16 6:57 PM.
 */
public class MemoryAddressingOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Constructs an <code>MemoryAddressingOutOfBoundsException</code> with no
     * detail message.
     * @param index out bound index.
     */
    public MemoryAddressingOutOfBoundsException(int index) {
        super("memory addressing index out of range: " + index);
    }
}