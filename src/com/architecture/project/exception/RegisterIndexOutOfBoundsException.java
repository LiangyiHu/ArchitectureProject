package com.architecture.project.exception;

/**
 * Register out of bounds exception.
 *
 * @author taoranxue on 9/15/16 3:52 PM.
 */
public class RegisterIndexOutOfBoundsException extends IndexOutOfBoundsException {
    /**
     * Constructs an <code>RegisterIndexOutOfBoundsException</code> with no
     * detail message.
     * @param index out bound index.
     */
    public RegisterIndexOutOfBoundsException(int index) {
        super("Register index out of range: " + index);
    }
}
