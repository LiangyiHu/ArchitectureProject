package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import org.junit.Test;

/**
 * @author taoranxue on 9/15/16 8:39 PM.
 */
public class MainInstructionTest {

    private Instruction instruction = new Instruction((char)(0b0000011111100110));
    private InstructionsFactory mainInstruction = new InstructionsFactory(instruction);
    @Test
    public void testAll() {
        Registers.indexRegisters.storeByRegister((char)10,3);
        //System.out.println(MainMemory.getData().size());
        MainMemory.store((char)2, 6);
        MainMemory.store((char)5000, 12);
        MainMemory.store((char)12, 16);
        mainInstruction.execute();
        System.out.println("the fourth GPR value is: "+(int)Registers.generalProposeRegisters.fetchByRegister(3));
        System.out.println("the third IX value is: "+(int)Registers.indexRegisters.fetchByRegister(3));
        MainMemory.printMemory(0,20);
    }

}