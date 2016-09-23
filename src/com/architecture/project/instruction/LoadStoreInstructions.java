package com.architecture.project.instruction;

import com.architecture.project.exception.WrongInstructionException;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import com.architecture.project.utils.ProjectUtils;

import java.lang.reflect.Method;

/**
 *
 * @author taoranxue on 9/18/16 12:11 AM.
 * The instruction function and methods of identifying is here
 */
public class LoadStoreInstructions extends Instructions {
    //the meaning of the code structure format: O for opcode, R for GeneralPurposeRegister, X for IndexRegister, I for Indirect, A for Address, Im for Immediate Number.
    //Value followed means how many bit it takes, E.g. OXA628, Opcode takes 6 bit, IX takes 2 bit, Address takes 8 bit.
    //Code format helps save some redundant calculations.

    //Format ORXIA, 62215, Opcode 6 bit, GPR 2 bit, IX 2 bit, I 1 bit, addressing 5 bit, instructions like LDR, STR, LDA, AMR, SMR etc...using this format.
    private static final int[] ORXIA62215_INSTRUCTION = {001, 002, 003, 004, 005};
    //Format OXA628, Opcode 6 bit, IX register 2 bit, address 8 bit, instructions like LDX use this format.
    private static final int[] OXA628_INSTRUCTION = {041};
    //Format OXIA6217, Opcode 6 bit, IX 2 bit, I 1 bit, address 7 bit, instructions like STX use this format.
    private static final int[] OXIA6217_INSTRUCTION = {042};
    //Format ORIm628, Opcode 6 bit, IX 2 bit, Immediate number 8 bit, instructions like AIR and SIR use this format.
    private static final int[] ORIm628_INSTRUCTION = {006, 007};

    //GPR register index, 0-3 is normal value, -1 used to prevent mal-use.
    private int R = -1;
    //Indes register index, 1-3 is normal value.
    private int IX = -1;
    //Indirect sign, value 0 or 1.
    private int I = -1;
    //In the instructions that contains address field, store the value of this field.
    private int Address = -1;
    //In the instructions that contains Immediate number, store the value of this field.
    private int Immediate = -1;
    //The content of General Purpose Register in this operation.
    private int RData = -1;
    //The content of Index Register in this operation.
    private int IXData = -1;

    private LoadStoreInstructions() {

    }

    public LoadStoreInstructions(Instruction instruction) {
        // Operation Code, used to connect functions by the Uppercase string value.
        INSTRUCTION_MAP.put(001, "LDR");
        INSTRUCTION_MAP.put(002, "STR");
        INSTRUCTION_MAP.put(003, "LDA");
        INSTRUCTION_MAP.put(041, "LDX");
        INSTRUCTION_MAP.put(042, "STX");
        INSTRUCTION_MAP.put(004, "AMR");
        INSTRUCTION_MAP.put(005, "SMR");
        INSTRUCTION_MAP.put(006, "AIR");
        INSTRUCTION_MAP.put(007, "SIR");

        //Move operator code;
        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);

        //Parse the instruction, get its GPR, IX, I, Address, Immediate, the contents of the GPR, IX etc...almost all the information needed to execute an instruction.
        if (ProjectUtils.inArrays(getOperatorCode(), ORXIA62215_INSTRUCTION)) {
            R = instruction.subInstruction(6, 8).parseInt();
            RData = Registers.generalProposeRegisters.fetchByRegister(R);

            IX = instruction.subInstruction(8, 10).parseInt();
            IXData = IX > 0 ? Registers.indexRegisters.fetchByRegister(IX) : 0;

            I = instruction.subInstruction(10, 11).parseInt();
            Address = instruction.subInstruction(11, 16).parseInt();


        } else if (ProjectUtils.inArrays(getOperatorCode(), OXA628_INSTRUCTION)) {
            IX = instruction.subInstruction(6, 8).parseInt();
            Address = instruction.subInstruction(8, 16).parseInt();

        } else if (ProjectUtils.inArrays(getOperatorCode(), OXIA6217_INSTRUCTION)) {
            IX = instruction.subInstruction(6, 8).parseInt();
            IXData = IX > 0 ? Registers.indexRegisters.fetchByRegister(IX) : 0;
            I = instruction.subInstruction(8, 9).parseInt();
            Address = instruction.subInstruction(9, 16).parseInt();
        } else if (ProjectUtils.inArrays(getOperatorCode(), ORIm628_INSTRUCTION)) {
            R = instruction.subInstruction(6, 8).parseInt();
            RData = Registers.generalProposeRegisters.fetchByRegister(R);
            Immediate = instruction.subInstruction(9, 16).parseInt();
        } else {
            throw new WrongInstructionException();
        }

    }

    @Override
    //Execute this instruction, match its function
    public void execute() {
        String operateCode = INSTRUCTION_MAP.get(getOperatorCode());
        if (operateCode == null || operateCode.equals("")) {
            throw new WrongInstructionException();
        }
        String methodName = "execute" + operateCode;
        System.out.println(methodName);
        try {
            Method executeMethod = getClass().getDeclaredMethod(methodName);
            executeMethod.invoke(this);
            Registers.programCounter.addPC();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    //General method for ORXIA type instructions. Opcode 6 bit, GPR 2 bit, IX 2 bit, I 1 bit, addressing 5 bit
    private char getDataInORXIA() {
        char data;
        if (I == 0) {
            if (IX == 0) {
                data = Registers.fetchMemory((char) Address);
            } else {
                data = Registers.fetchMemory((char) (Address + IXData));
            }
        } else {
            if (IX == 0) {
                data = Registers.fetchMemory(Registers.fetchMemory((char) Address));
            } else {
                data = Registers.fetchMemory(Registers.fetchMemory((char) (Address + IXData)));
            }
        }
        return data;
    }


    //Instruction LDR
    private void executeLDR() {
        char data = getDataInORXIA();
        //load to Register
        Registers.generalProposeRegisters.storeByRegister(data, R);
    }

    //STR
    private void executeSTR() {
        int effectiveAddress;
        if (I == 0) {
            if (IX == 0) {
                effectiveAddress = Address;
            } else {
                effectiveAddress = Address + IXData;
            }
        } else {
            if (IX == 0) {
                effectiveAddress = Registers.fetchMemory((char) Address);
            } else {
                effectiveAddress = Registers.fetchMemory((char) (IXData + Address));
            }
        }
        //Save to memory
        MainMemory.store((char) RData, effectiveAddress);
    }

    //LDA
    private void executeLDA() {
        char data;
        if (I == 0) {
            if (IX == 0) {
                data = (char) Address;
            } else {
                data = (char) (Address + IXData);
            }
        } else {
            if (IX == 0) {
                data = Registers.fetchMemory((char) Address);
            } else {
                data = Registers.fetchMemory((char) (Address + IXData));
            }
        }
        //load to Register
        Registers.generalProposeRegisters.storeByRegister(data, R);
    }

    //LDX
    private void executeLDX() {
        System.out.println(Address + " " + IX);
        char data = Registers.fetchMemory((char) Address);
        Registers.indexRegisters.storeByRegister(data, IX);
    }

    private void executeSTX() {
        if (I == 0) {
            MainMemory.store((char) IXData, Address);
        } else {
            MainMemory.store((char) IXData, Registers.fetchMemory((char) Address));
        }
    }

    private void executesAMR() {
        char data = getDataInORXIA();
        //add to Register
        Registers.generalProposeRegisters.storeByRegister((char) (RData + data), R);
    }

    private void executesSMR() {
        char data = getDataInORXIA();
        //add to Register
        Registers.generalProposeRegisters.storeByRegister((char) (RData - data), R);
    }

    private void executesAIR() {
        char data = (char) Immediate;
        //add to Register
        Registers.generalProposeRegisters.storeByRegister((char) (RData + data), R);
    }

    private void executesSIR() {
        char data = (char) Immediate;
        //add to Register
        Registers.generalProposeRegisters.storeByRegister((char) (RData - data), R);
    }

}

