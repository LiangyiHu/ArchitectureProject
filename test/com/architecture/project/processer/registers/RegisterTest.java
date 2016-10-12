package com.architecture.project.processer.registers;

import com.architecture.project.instruction.Instruction;
import com.architecture.project.instruction.InstructionsFactory;
import org.junit.Test;

/**
 * @author taoranxue on 9/15/16 4:26 PM.
 */
public class RegisterTest {
    private Register register = new Register((char) (0b1111000011001100));
    private Instruction instruction = new Instruction((char) (0b1111000011001100));
    private InstructionsFactory mainInstruction = new InstructionsFactory(instruction);

    @Test
    public void testAll() {
        System.out.println(register.toString());
        register.getBitByIndex(1);
//        System.out.println(Integer.toBinaryString(mainInstruction.getOperatorCode()));

    }
}