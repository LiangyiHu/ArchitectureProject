package com.architecture.project.processer.registers;

/**
 * contains all static registers
 * @author taoranxue on 9/15/16 8:00 PM.
 */
public class Registers {
    public static GeneralProposeRegisters generalProposeRegisters = new GeneralProposeRegisters();
    public static InstructionRegister instructionRegister = new InstructionRegister();
    public static ProgramCounter programCounter = new ProgramCounter();
    public static IndexRegisters indexRegisters = new IndexRegisters();
    public static MemoryAddressRegister memoryAddressRegister = new MemoryAddressRegister();
    public static MemoryBufferRegister memoryBufferRegister = new MemoryBufferRegister();
    public static MachineStatusRegister machineStatusRegister = new MachineStatusRegister();
    public static MachineFaultRegister machineFaultRegister = new MachineFaultRegister();
}
