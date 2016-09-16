package com.architecture.project.instruction;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author taoranxue on 9/15/16 4:24 PM.
 */
public class InstructionTest {
    private Instruction instruction = new Instruction((char)(0b0000010011001100));
    @Test
    public void testAll() {
        char c = instruction.subInstruction(11, 16).getData();
        System.out.println(Integer.toBinaryString(c));
    }

}