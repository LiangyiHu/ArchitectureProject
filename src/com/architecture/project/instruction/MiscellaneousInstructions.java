package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processor.registers.Registers;

/**
 * Miscellaneous instruction.
 *
 * @author taoranxue on 9/18/16 12:05 AM.
 */
public class MiscellaneousInstructions extends AbstractMainInstructions {
    //private int Rcustomized = -1; //this R get 4~6th bits of an instruction, it's a customer defined, use in caution.
    //private int IX = -1;
    //private int IMMEDIATE = -1;

    public MiscellaneousInstructions(Instruction instruction) {
        super(instruction);
        //Move operator code;
        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);
    }

    //Customize
    public int R_4_6() {
        return R(4, 6);
    }

    public int RDATA_4_6() {
        return RDATA(4, 6);
    }

    public int IX_6_8() {
        return R(6, 8);
    }

    public int IMMEDIATE_8_16() {
        return IMMEDIATE(8, 16);
    }

    //execute LLD, long load
    private void executeLLD() {
        char data;
        if (IX_6_8() == 0) {
            data = Registers.fetchMemory((char) IMMEDIATE_8_16());
        } else {
            data = Registers.fetchMemory((char) (IMMEDIATE_8_16() + RDATA_4_6()));
        }
        Registers.generalProposeRegisters.storeByRegister(data, R_4_6());
    }

    //execute LST, long store
    private void executeLST() {
        int effectiveAddress;
        char data = Registers.generalProposeRegisters.fetchByRegister(R_4_6());
        if (IX_6_8() == 0) {
            effectiveAddress = IMMEDIATE_8_16();
        } else {
            effectiveAddress = IMMEDIATE_8_16() + RDATA_4_6();
        }
        MainMemory.store(data, effectiveAddress);

    }



}
