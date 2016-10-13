package com.architecture.project.instruction;

import com.architecture.project.processor.registers.Registers;

/**
 * Created by larry on 10/12/2016.
 */
public class ShiftRotateInstructions extends Instructions {
    private int R = -1;
    private int AL = -1;
    private int LR = -1;
    private int COUNT = -1;

    public ShiftRotateInstructions(Instruction instruction) {
        //Move operator code;
        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);
        R = instruction.subInstruction(6, 8).parseInt();
        AL = instruction.subInstruction(8, 9).parseInt();
        LR = instruction.subInstruction(9, 10).parseInt();
        COUNT = instruction.subInstruction(12, 16).parseInt();
    }

    //Instruction SRC
    private void executeSRC() {
        //arithmetic shift
        if (AL == 0) {
            short data = (short) Registers.generalProposeRegisters.fetchByRegister(R);
            //right shift
            if (LR == 0) {
                data = (short) (data >> COUNT);
            } else {
                data = (short) (data << COUNT);
            }
            Registers.generalProposeRegisters.storeByRegister((char) data, R);
        }
        //logic shift
        else {
            char rData = Registers.generalProposeRegisters.fetchByRegister(R);
            //right shift
            if (LR == 0) {
                rData = (char) (rData >> COUNT);
            }
            //left shift
            else {
                rData = (char) (rData << COUNT);
            }
            Registers.generalProposeRegisters.storeByRegister(rData, R);

        }
    }


    //Instruction RRC
    private void executeRRC() {
        int signBit;
        char data= Registers.generalProposeRegisters.fetchByRegister(R);
        //logical rotate
        //right Rotate
        if (LR == 0) {
            for (int i = 0; i <COUNT; i++) {
                signBit=1&data;
                data=(char)(data>>1);
                data=(char)((signBit<<15)|data);
            }
        }
        //left Rotate
        else {
            for (int i = 0; i <COUNT; i++) {
                signBit=1&(data>>15);
                data=(char)(data<<1);
                data=(char)(signBit|data);
            }
        }
        Registers.generalProposeRegisters.storeByRegister(data,R);
    }
}
