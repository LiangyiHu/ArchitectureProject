package com.architecture.project.instruction;

import com.architecture.project.processor.registers.Register;
import com.architecture.project.processor.registers.Registers;

/**
 * @author taoranxue on 12/2/16 12:19 AM.
 */
public class FloatingVectorInstructions extends AbstractMainInstructions {
    public FloatingVectorInstructions(Instruction instruction) {
        super(instruction);
    }




    //FADD
    private void executeFADD() {
        Double a = 0.1;
        short data = (short) Registers.fetchMemory((char) EFFECTIVEADDRESS());
        int sum = (int) RDATA() + (int) data;
        setFlowConditionCode(sum);
        //add to Register

        Registers.generalProposeRegisters.storeByRegister((char) sum, R());
    }
}
