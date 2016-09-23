package com.architecture.project.processer;

/**
 * ALU similator.
 *
 * @author taoranxue on 9/14/16 11:28 PM.
 */
public final class Arithmetic {
    private Arithmetic() {

    }

    public static char add(char a, char b) {
        return (char) (a + b);
    }

    public static char sub(char a, char b) {
        return (char) (a - b);
    }
}
