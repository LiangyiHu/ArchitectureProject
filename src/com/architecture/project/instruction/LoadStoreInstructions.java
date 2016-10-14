package com.architecture.project.instruction;

import com.architecture.project.Application;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processor.registers.Registers;

/**
 * @author taoranxue on 10/11/16 11:41 PM.
 */
public class LoadStoreInstructions extends AbstractMainInstructions {

    public LoadStoreInstructions(Instruction instruction) {
        super(instruction);
    }

    //Customize
    public int R_10_12() {
        return R(10, 12);
    }

    public int RDATA_10_12() {
        return RDATA(10, 12);
    }


    //Instruction LDR
    private void executeLDR() {
        char data;
        data = Registers.fetchMemory((char) EFFECTIVEADDRESS());
        //load to Register
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    //STR
    private void executeSTR() {
        //Save to memory
        Registers.storeMemory((char) RDATA(), (char) EFFECTIVEADDRESS());
    }

    //LDA
    private void executeLDA() {
        //load to Register
        Registers.generalProposeRegisters.storeByRegister((char) EFFECTIVEADDRESS(), R());
    }

    //LDX
    private void executeLDX() {
        char data = Registers.fetchMemory((char) ADDRESS());
        if (I() == 0) {
            Registers.indexRegisters.storeByRegister(data, IX());
        } else {
            Registers.indexRegisters.storeByRegister(Registers.fetchMemory(data), IX());
        }
    }

    private void executeSTX() {
        if (I() == 0) {
            Registers.storeMemory(IXDATA(), (char) ADDRESS());
        } else {
            Registers.storeMemory(IXDATA(), Registers.fetchMemory((char) ADDRESS()));
        }
    }

    private void executeLDIR() {
        char address = (char) (IXDATA() + RDATA_10_12());
        Registers.generalProposeRegisters.storeByRegister(Registers.fetchMemory(address), R());
    }

    private void executeSTRR() {
        char address = (char) (IXDATA() + RDATA_10_12());
        Registers.storeMemory(Registers.generalProposeRegisters.fetchByRegister(R()), address);

    }

    private void executeSRR() {
        int data = RXDATA() - RYDATA();
        //TODO Overflow?
        Registers.generalProposeRegisters.storeByRegister((char) data, RX());
    }
}
