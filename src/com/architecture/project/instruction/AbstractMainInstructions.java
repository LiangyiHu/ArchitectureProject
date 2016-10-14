package com.architecture.project.instruction;

import com.architecture.project.processor.registers.Registers;

/**
 * @author taoranxue on 10/12/16 12:05 AM.
 */
public abstract class AbstractMainInstructions extends Instructions {
    private Instruction instruction;
    //GPR register index, 0-3 is normal value, -1 used to prevent mal-use.
    private int R = -1;
    //Indes register index, 1-3 is normal value.
    private int IX = -1;
    //Indirect sign, value 0 or 1.
    private int I = -1;
    //In the instructions that contains address field, store the value of this field.
    private int ADDRESS = -1;


    public AbstractMainInstructions(Instruction instruction) {
        this.instruction = instruction;
        //Move operator code;
        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);
        R = instruction.subInstruction(6, 8).parseInt();
        IX = instruction.subInstruction(8, 10).parseInt();
        I = instruction.subInstruction(10, 11).parseInt();
        ADDRESS = instruction.subInstruction(11, 16).parseInt();
    }

    public int R() {
        return R;
    }

    public char RDATA() {
        return Registers.generalProposeRegisters.fetchByRegister(R);
    }

    public int IX() {
        return IX;
    }

    public char IXDATA() {
        return IX > 0 ? Registers.indexRegisters.fetchByRegister(IX) : 0;
    }

    public int I() {
        return I;
    }

    public int ADDRESS() {
        return ADDRESS;
    }

    public int EFFECTIVEADDRESS() {
        if (I == 0) {
            return IXDATA() + ADDRESS;
        } else {
            return Registers.fetchMemory((char) (IXDATA() + ADDRESS));
        }
    }

    public int IMMEDIATE() {
        return ADDRESS();
    }

    public int RX() {
        return R();
    }

    public int RXDATA() {
        return Registers.generalProposeRegisters.fetchByRegister(RX());
    }

    public int RY() {
        return IX();
    }

    public int RYDATA() {
        return Registers.generalProposeRegisters.fetchByRegister(RY());
    }

    public int CC() {
        return R();
    }


    //CUSTOMIZE
    public int R(int start, int end) {
        return instruction.subInstruction(start, end).parseInt();
    }

    public int RDATA(int start, int end) {
        return Registers.generalProposeRegisters.fetchByRegister(R(start, end));
    }

    public int IX(int start, int end) {
        return R(start, end);
    }

    public int IXDATA(int start, int end) {
        return Registers.indexRegisters.fetchByRegister(IX(start, end));
    }

    public int ADDRESS(int start, int end) {
        return R(start, end);
    }

    public int IMMEDIATE(int start, int end) {
        return ADDRESS(start, end);
    }
}
