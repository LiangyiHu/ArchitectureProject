package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import com.architecture.project.utils.ProjectUtils;

/**
 * @author taoranxue on 9/15/16 3:41 PM.
 *
 */
public class MainInstruction {
    private static final int OP_NUMBER = 6;
    private static final int[] LR_INSTRUCTION = {01, 02, 03};
    private Instruction instruction;

    public MainInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public void execute() {
        int operatorCode = getOperatorCode();
        if (ProjectUtils.inArrays(operatorCode, LR_INSTRUCTION)) {
            int r_num = instruction.subInstruction(6, 8).parseInt();
            char r_data = Registers.generalProposeRegisters.fetchByRegister(r_num);

            int ix_num = instruction.subInstruction(8, 10).parseInt();
            char ix_data = Registers.indexRegisters.fetchByRegister(ix_num);

            int I = instruction.subInstruction(10, 11).parseInt();
            int address = instruction.subInstruction(11, 16).parseInt();
            System.out.println("ins: " + Integer.toBinaryString(instruction.getData()));
            System.out.println("ix_num: " + ix_num);
            System.out.println("ix_data: " + (int)ix_data);
            System.out.println("address: " + address);
            char data;
            int effectiveAddress;

            switch (operatorCode) {
                case 01:
                    if (I == 0) {
                        if (ix_num == 0) {
                            data = MainMemory.fetch(address);
                        } else {
                            data = MainMemory.fetch(address + ix_data);
                        }
                    } else {
                        if (ix_num == 0) {
                            data = MainMemory.fetch(MainMemory.fetch(address));
                        } else {
                            data = MainMemory.fetch(MainMemory.fetch(address + ix_data));
                        }
                    }
                    //load to Register
                    System.out.println("the data to be loaded is: "+(int)data);
                    Registers.generalProposeRegisters.storeByRegister(data, r_num);
                break;
                case 02:
                    if (I == 0) {
                        if (ix_num == 0) {
                            effectiveAddress=address;
                        } else {
                            effectiveAddress=address+ix_data;
                        }
                    } else {
                        if (ix_num == 0) {
                            effectiveAddress=MainMemory.fetch(address);
                        } else {
                            effectiveAddress=MainMemory.fetch(ix_data+address);
                        }
                    }
                    //Save to memory
                    MainMemory.store(r_data, effectiveAddress);
                    System.out.println("value: "+r_data+"has been stored to memory location: "+effectiveAddress);
                    break;
                case 03:
                    if (I == 0) {
                        if (ix_num == 0) {
                            data = (char)address;
                        } else {
                            data = (char)(address + ix_data);
                        }
                    } else {
                        if (ix_num == 0) {
                            data = MainMemory.fetch(address);
                        } else {
                            data = MainMemory.fetch(address + ix_data);
                        }
                    }
                    //load to Register
                    System.out.println("the data to be loaded is: "+(int)data);
                    Registers.generalProposeRegisters.storeByRegister(data, r_num);
                    break;
            }
        }
        if (operatorCode==041){
            int ix_num = instruction.subInstruction(6, 8).parseInt();
            int address = instruction.subInstruction(8, 16).parseInt();
            char data=MainMemory.fetch(address);
            Registers.indexRegisters.storeByRegister(data,ix_num);
        }
        if (operatorCode==042){
            int ix_num = instruction.subInstruction(6, 8).parseInt();
            char ix_data = Registers.indexRegisters.fetchByRegister(ix_num);
            int I = instruction.subInstruction(8, 9).parseInt();
            int address = instruction.subInstruction(9, 16).parseInt();
            //char data=MainMemory.fetch(address);
            if(I==0){
                MainMemory.store(ix_data, address);
            }
            else {
                MainMemory.store(ix_data, MainMemory.fetch(address));
            }
        }
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public int getOperatorCode() {
        return instruction.subInstruction(0, OP_NUMBER).parseInt();
    }
}
