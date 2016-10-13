package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processor.registers.Registers;

/**
 * Miscellaneous instruction.
 *
 * @author taoranxue on 9/18/16 12:05 AM.
 */
public class MiscellaneousInstructions extends Instructions {
    private int Rcustomized = -1; //this R get 4~6th bits of an instruction, it's a customer defined, use in caution.
    private int IX = -1;
    private int IMMEDIATE = -1;

    public MiscellaneousInstructions(Instruction instruction){
        //Move operator code;
        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);
        Rcustomized = instruction.subInstruction(4, 6).parseInt();
        IX=instruction.subInstruction(6, 8).parseInt();
        IMMEDIATE = instruction.subInstruction(8, 16).parseInt();
    }
    //execute LLD, long load
    private void executeLLD() {
        char data;
        if(IX==0){
            data = Registers.fetchMemory((char)IMMEDIATE);
        }
        else{data = Registers.fetchMemory((char)( IMMEDIATE + Registers.indexRegisters.fetchByRegister(IX)));}
        Registers.generalProposeRegisters.storeByRegister(data, Rcustomized);
    }

    //execute LST, long store
    private void executeLST() {
        int effectiveAddress;
        char data=Registers.generalProposeRegisters.fetchByRegister(Rcustomized);
            if (IX== 0) {
                effectiveAddress = IMMEDIATE;
            } else {
                effectiveAddress = IMMEDIATE + Registers.indexRegisters.fetchByRegister(IX);
            }
        MainMemory.store(data, effectiveAddress);

        }

}
