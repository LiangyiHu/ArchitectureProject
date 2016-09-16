package com.architecture.project.exception;

/**
 * @author taoranxue on 9/15/16 8:16 PM.
 */
public class RegistersIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public RegistersIndexOutOfBoundsException(int index) {
        super("Register list index out of range: " + index);
    }
}
