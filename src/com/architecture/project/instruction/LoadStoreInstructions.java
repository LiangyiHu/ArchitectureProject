package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;

/**
 * @author taoranxue on 10/11/16 11:41 PM.
 */
public class LoadStoreInstructions extends AbstractMainInstructions {

    public LoadStoreInstructions(Instruction instruction) {
        super(instruction);
    }


    //General method for ORXIA type instructions. Opcode 6 bit, GPR 2 bit, IX 2 bit, I 1 bit, addressing 5 bit
//    private char getDataInORXIA() {
//        char data;
//        if (I() == 0) {
//            if (IX() == 0) {
//                data = Registers.fetchMemory((char) ADDRESS());
//            } else {
//                data = Registers.fetchMemory((char) (ADDRESS() + IXDATA()));
//            }
//        } else {
//            if (IX() == 0) {
//                data = Registers.fetchMemory(Registers.fetchMemory((char) ADDRESS()));
//            } else {
//                data = Registers.fetchMemory(Registers.fetchMemory((char) (ADDRESS() + IXDATA())));
//            }
//        }
//        return data;
//    }


    //Instruction LDR
    private void executeLDR() {
        char data;
        if (I() == 0) {
            if (IX() == 0) {
                data = Registers.fetchMemory((char) ADDRESS());
            } else {
                data = Registers.fetchMemory((char) (ADDRESS() + IXDATA()));
            }
        } else {
            if (IX() == 0) {
                data = Registers.fetchMemory(Registers.fetchMemory((char) ADDRESS()));
            } else {
                data = Registers.fetchMemory(Registers.fetchMemory((char) (ADDRESS() + IXDATA())));
            }
        }
        //load to Register
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    //STR
    private void executeSTR() {
        int effectiveAddress;
        if (I() == 0) {
            if (IX() == 0) {
                effectiveAddress = ADDRESS();
            } else {
                effectiveAddress = ADDRESS() + IXDATA();
            }
        } else {
            if (IX() == 0) {
                effectiveAddress = Registers.fetchMemory((char) ADDRESS());
            } else {
                effectiveAddress = Registers.fetchMemory((char) (IXDATA() + ADDRESS()));
            }
        }
        //Save to memory
        MainMemory.store((char) RDATA(), effectiveAddress);
    }

    //LDA
    private void executeLDA() {
        char data;
        if (I() == 0) {
            if (IX() == 0) {
                data = (char) ADDRESS();
            } else {
                data = (char) (ADDRESS() + IXDATA());
            }
        } else {
            if (IX() == 0) {
                data = Registers.fetchMemory((char) ADDRESS());
            } else {
                data = Registers.fetchMemory((char) (ADDRESS() + IXDATA()));
            }
        }
        //load to Register
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    //LDX
    private void executeLDX() {
        System.out.println(ADDRESS() + " " + IX());
        char data = Registers.fetchMemory((char) ADDRESS());
        Registers.indexRegisters.storeByRegister(data, IX());
    }

    private void executeSTX() {
        if (I() == 0) {
            MainMemory.store((char) IXDATA(), ADDRESS());
        } else {
            MainMemory.store((char) IXDATA(), Registers.fetchMemory((char) ADDRESS()));
        }
    }

//    @Override
//    public void execute() {
//        doExecute();
//    }

//    private void executesAMR() {
//        char data = getDataInORXIA();
//        //add to Register
//        Registers.generalProposeRegisters.storeByRegister((char) (RData + data), R);
//    }
//
//    private void executesSMR() {
//        char data = getDataInORXIA();
//        //add to Register
//        Registers.generalProposeRegisters.storeByRegister((char) (RData - data), R);
//    }
//
//    private void executesAIR() {
//        char data = (char) Immediate;
//        //add to Register
//        Registers.generalProposeRegisters.storeByRegister((char) (RData + data), R);
//    }
//
//    private void executesSIR() {
//        char data = (char) Immediate;
//        //add to Register
//        Registers.generalProposeRegisters.storeByRegister((char) (RData - data), R);
//    }

}
