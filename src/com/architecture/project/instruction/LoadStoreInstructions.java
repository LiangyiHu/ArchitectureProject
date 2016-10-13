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
        MainMemory.store((char) RDATA(), EFFECTIVEADDRESS());
    }

    //LDA
    private void executeLDA() {
        //load to Register
        Registers.generalProposeRegisters.storeByRegister((char)EFFECTIVEADDRESS(), R());
    }

    //LDX
    private void executeLDX() {
        char data = Registers.fetchMemory((char) ADDRESS());
        if(I()==0){
            Registers.indexRegisters.storeByRegister(data, IX());
        }
        else {
            Registers.indexRegisters.storeByRegister(Registers.fetchMemory(data), IX());
        }
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
