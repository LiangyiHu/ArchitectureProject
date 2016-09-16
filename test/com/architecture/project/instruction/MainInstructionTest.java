package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import org.junit.Test;

/**
 * @author taoranxue on 9/15/16 8:39 PM.
 */
public class MainInstructionTest {
    private Instruction instruction = new Instruction((char)(0b0000010011001100));
    private MainInstruction mainInstruction = new MainInstruction(instruction);
    @Test
    public void testAll() {
        System.out.println(MainMemory.getData().size());
        MainMemory.store((char)22, 12);
        mainInstruction.execute();
        System.out.println((int)Registers.generalProposeRegisters.fetchByRegister(0));
    }

}