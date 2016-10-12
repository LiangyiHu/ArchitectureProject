package com.architecture.project.instruction;

import com.architecture.project.processor.registers.Registers;

/**
 * @author taoranxue on 10/11/16 11:41 PM.
 */
public class TransferInstructions extends AbstractMainInstructions {

    public TransferInstructions(Instruction instruction) {
        super(instruction);
    }


    //Instruction LDR
    private void executeJZ() {
        if (RDATA() == 0) {
            if (I() == 0) {
                Registers.programCounter.setOne((char) (ADDRESS()));
            } else {
                Registers.programCounter.setOne(Registers.fetchMemory((char) (ADDRESS())));
            }
        }
    }

    private void executeJNE() {
        if (RDATA() != 0) {
            if (I() == 0) {
                Registers.programCounter.setOne((char) (ADDRESS()));
            } else {
                Registers.programCounter.setOne(Registers.fetchMemory((char) (ADDRESS())));
            }
        }
    }

    private void executeJCC() {
        int ccIndex = CC();
        if (Registers.conditionCodeRegister.getRegisterByIndex(0).getBitByIndex(ccIndex) > 0) {
            if (I() == 0) {
                Registers.programCounter.setOne((char) (ADDRESS()));
            } else {
                Registers.programCounter.setOne(Registers.fetchMemory((char) (ADDRESS())));
            }
        }
    }

    private void executeJMA() {
        if (I() == 0) {
            Registers.programCounter.setOne((char) (ADDRESS()));
        } else {
            Registers.programCounter.setOne(Registers.fetchMemory((char) (ADDRESS())));
        }
    }

    private void executeJSR() {
        Registers.generalProposeRegisters.storeByRegister(Registers.programCounter.getOne(), 3);
        if (I() == 0) {
            Registers.programCounter.setOne((char) (ADDRESS()));
        } else {
            Registers.programCounter.setOne(Registers.fetchMemory((char) (ADDRESS())));
        }
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
            if (I() == 0) {
                Registers.programCounter.setOne((char) (ADDRESS()));
            } else {
                Registers.programCounter.setOne(Registers.fetchMemory((char) (ADDRESS())));
            }
        }
    }

    private void executeJGE() {
        int rIndex = R();
        if ((int) RDATA() >= 0) {
            if (I() == 0) {
                Registers.programCounter.setOne((char) (ADDRESS()));
            } else {
                Registers.programCounter.setOne(Registers.fetchMemory((char) (ADDRESS())));
            }
        }
    }

}
