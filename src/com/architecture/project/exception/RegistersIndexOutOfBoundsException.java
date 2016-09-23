package com.architecture.project.exception;

/**
 * Registers index out of bounds exception
 *
 * @author taoranxue on 9/15/16 8:16 PM.
 */
public class RegistersIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Constructs an <code>RegistersIndexOutOfBoundsException</code> with no
     * detail message.
     * @param index out bound index.
     */
    public RegistersIndexOutOfBoundsException(int index) {
        super("Register list index out of range: " + index);
    }
}
