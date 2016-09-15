package com.architecture.project.processer.registers;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author taoranxue on 9/15/16 4:26 PM.
 */
public class RegisterTest {
    private Register register = new Register((char)(0b1111000011001100));
    @Test
    public void testAll() {
        System.out.println(register.toString());
        System.out.println(register.subRegister(2,4));

    }
}