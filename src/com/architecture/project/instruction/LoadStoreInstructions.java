package com.architecture.project.instruction;

import com.architecture.project.exception.WrongInstructionException;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import com.architecture.project.utils.ProjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * _________________________________________________
 * |  OpCode   |   R   |   IX   |I |     Address   |
 * |0         5|6     7|8      9|10| 11          15|
 * -------------------------------------------------
 *
 * @author taoranxue on 9/18/16 12:11 AM.
 */
public class LoadStoreInstructions extends Instructions {
    private static final int[] LR_INSTRUCTION = {001, 002, 003};
    private static final int[] LDX_INSTRUCTION = {041};
    private static final int[] STX_INSTRUCTION = {042};

    private int R = -1;
    private int IX = -1;
    private int I = -1;
    private int Address = -1;

    private int RData = -1;
    private int IXData = -1;

    public LoadStoreInstructions(Instruction instruction) {
        // Operation Code
        INSTRUCTION_MAP.put(001, "LDR");
        INSTRUCTION_MAP.put(002, "STR");
        INSTRUCTION_MAP.put(003, "LDA");
        INSTRUCTION_MAP.put(041, "LDX");
        INSTRUCTION_MAP.put(042, "STX");

        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);
        if (ProjectUtils.inArrays(getOperatorCode(), LR_INSTRUCTION)) {
            R = instruction.subInstruction(6, 8).parseInt();
            RData = Registers.generalProposeRegisters.fetchByRegister(R);

            IX = instruction.subInstruction(8, 10).parseInt();
            IXData = Registers.indexRegisters.fetchByRegister(IX);

            I = instruction.subInstruction(10, 11).parseInt();
            Address = instruction.subInstruction(11, 16).parseInt();


        } else if (ProjectUtils.inArrays(getOperatorCode(), LDX_INSTRUCTION)) {
            IX = instruction.subInstruction(6, 8).parseInt();
            Address = instruction.subInstruction(8, 16).parseInt();

        } else if (ProjectUtils.inArrays(getOperatorCode(), STX_INSTRUCTION)) {
            IX = instruction.subInstruction(6, 8).parseInt();
            IXData = Registers.indexRegisters.fetchByRegister(IX);
            I = instruction.subInstruction(8, 9).parseInt();
            Address = instruction.subInstruction(9, 16).parseInt();
        } else {
            throw new WrongInstructionException();
        }

    }

    @Override
    public void execute() {
        String operateCode = INSTRUCTION_MAP.get(getOperatorCode());
        if (operateCode == null || operateCode.equals("")) {
            throw new WrongInstructionException();
        }
        String methodName = "execute" + operateCode;
        try {
            Method executeMethod = getClass().getMethod(methodName);
            executeMethod.invoke(getClass());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    private void executeLDR() {
        char data;
        if (I == 0) {
            if (IX == 0) {
                data = MainMemory.fetch(Address);
            } else {
                data = MainMemory.fetch(Address + IXData);
            }
        } else {
            if (IX == 0) {
                data = MainMemory.fetch(MainMemory.fetch(Address));
            } else {
                data = MainMemory.fetch(MainMemory.fetch(Address + IXData));
            }
        }
        //load to Register
        Registers.generalProposeRegisters.storeByRegister(data, R);
    }

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
                effectiveAddress = MainMemory.fetch(Address);
            } else {
                effectiveAddress = MainMemory.fetch(IXData + Address);
            }
        }
        //Save to memory
        MainMemory.store((char) RData, effectiveAddress);
    }

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
                data = MainMemory.fetch(Address);
            } else {
                data = MainMemory.fetch(Address + IXData);
            }
        }
        //load to Register
        Registers.generalProposeRegisters.storeByRegister(data, R);
    }

    private void executeLDX() {
        char data = MainMemory.fetch(Address);
        Registers.indexRegisters.storeByRegister(data, R);
    }

    private void executeSTX() {
        if (I == 0) {
            MainMemory.store((char) IXData, Address);
        } else {
            MainMemory.store((char) IXData, MainMemory.fetch(Address));
        }
    }
}
