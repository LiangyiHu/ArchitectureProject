package com.architecture.project.instruction;

import com.architecture.project.processor.registers.Registers;

/**
 * @author taoranxue on 10/11/16 11:41 PM.
 */
public class TransferInstructions extends AbstractMainInstructions {

    public TransferInstructions(Instruction instruction) {
        super(instruction);
    }

    //customize
    private char ADDRESS_8_16() {
        return (char) ADDRESS(8, 16);
    }

    private char IMMEDIATE_6_16() {
        return (char) IMMEDIATE(6, 16);
    }


    //Instruction LDR
    private void executeJZ() {
        if (RDATA() == 0) {
            Registers.programCounter.setOne((char) (EFFECTIVEADDRESS()));
        }
    }

    private void executeJNE() {
        if (RDATA() != 0) {
            Registers.programCounter.setOne((char) (EFFECTIVEADDRESS()));
        }
    }

    private void executeJCC() {
        int ccIndex = CC();
        if (Registers.conditionCodeRegister.getRegisterByIndex(0).getBitByIndex(ccIndex) > 0) {
            Registers.programCounter.setOne((char) (EFFECTIVEADDRESS()));
        }
    }

    private void executeJMA() {
        //Registers.programCounter.setOne((char) (EFFECTIVEADDRESS()));
        Registers.programCounter.setOne((char) IMMEDIATE_6_16());
    }

    private void executeJSR() {
        Registers.generalProposeRegisters.storeByRegister(Registers.programCounter.getOne(), 3);
        Registers.programCounter.setOne((char) (EFFECTIVEADDRESS()));
    }

    private void executeRFS() {
        Registers.programCounter.setOne(Registers.generalProposeRegisters.fetchByRegister(3));
        Registers.generalProposeRegisters.storeByRegister((char) IMMEDIATE(), 0);
    }

    private void executeSOB() {
        int rIndex = R();
        char rData = (char) (RDATA() - 1);
        Registers.generalProposeRegisters.storeByRegister(rData, rIndex);
        if ((int) rData > 0) {
            Registers.programCounter.setOne((char) (EFFECTIVEADDRESS()));
        }
    }

    private void executeJGE() {
        if ((int) RDATA() >= 0) {
            Registers.programCounter.setOne((char) (IMMEDIATE_6_16()));
        }
    }

    private void executeJB() {
        if (R() == 0) {
            char pc = Registers.programCounter.getOne();
            pc -= ADDRESS_8_16();
            Registers.programCounter.setOne(pc);
        }
    }



}
