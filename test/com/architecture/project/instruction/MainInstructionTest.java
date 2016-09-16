package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author taoranxue on 9/15/16 8:39 PM.
 */
public class MainInstructionTest {
    private Instruction instruction = new Instruction((char)(0b0001000011001100));
    private MainInstruction mainInstruction = new MainInstruction(instruction);
    @Test
    public void testAll() {
        System.out.println(MainMemory.getData().size());
        MainMemory.store((char)22, 0b10101);
        mainInstruction.execute();
        System.out.println(Registers.generalProposeRegisters.fetchByRegister(0));
    }

}