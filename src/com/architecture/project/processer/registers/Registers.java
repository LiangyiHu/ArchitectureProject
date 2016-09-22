package com.architecture.project.processer.registers;

import com.architecture.project.memory.MainMemory;

/**
 * contains all static registers
 *
 * @author taoranxue on 9/15/16 8:00 PM.
 */
public final class Registers {
    private Registers() {

    }

    //Initiate all the registers.
    public static GeneralProposeRegisters generalProposeRegisters = new GeneralProposeRegisters();
    public static InstructionRegister instructionRegister = new InstructionRegister();
    public static ProgramCounter programCounter = new ProgramCounter();
    public static IndexRegisters indexRegisters = new IndexRegisters();
    public static MemoryAddressRegister memoryAddressRegister = new MemoryAddressRegister();
    public static MemoryBufferRegister memoryBufferRegister = new MemoryBufferRegister();
    public static MachineStatusRegister machineStatusRegister = new MachineStatusRegister();
    public static MachineFaultRegister machineFaultRegister = new MachineFaultRegister();
    public static ConditionCodeRegister conditionCodeRegister = new ConditionCodeRegister();

    //Reset all the registers.
    public static void resetAll() {
        generalProposeRegisters.reset();
        instructionRegister.reset();
        programCounter.reset();
        indexRegisters.reset();
        memoryAddressRegister.reset();
        memoryBufferRegister.reset();
        machineStatusRegister.reset();
        machineFaultRegister.reset();
        conditionCodeRegister.reset();
    }

    //The process of getting memory, send address to MAR first, then get data from memory and store to MBR
    public static char fetchMemory(char address) {
        memoryAddressRegister.setOne(address);
        char data = MainMemory.fetch(address);
        memoryBufferRegister.setOne(data);
        return data;
    }

    //The process of store memory, set MAR first, then
    public static void storeMemory(char data, char address) {
        memoryAddressRegister.setOne(address);
        memoryBufferRegister.setOne(data);
        MainMemory.store(data, address);
    }
}
