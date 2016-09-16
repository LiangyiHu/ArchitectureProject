package com.architecture.project.instruction;

import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import com.architecture.project.utils.ProjectUtils;

/**
 * @author taoranxue on 9/15/16 3:41 PM.
 */
public class MainInstruction {
    private static final int OP_NUMBER = 6;
    private static final int[] LR_INSTRUCTION = {1, 2, 3, 41, 42};
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

            switch (operatorCode) {
                case 1:
                    char data = 0;
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
                    Registers.generalProposeRegisters.storeByRegister(data, r_num);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 41:
                    break;
                case 42:
                    break;


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
