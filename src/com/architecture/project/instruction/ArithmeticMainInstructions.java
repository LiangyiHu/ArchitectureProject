package com.architecture.project.instruction;

import com.architecture.project.processor.registers.Registers;

/**
 * @author taoranxue on 10/11/16 11:41 PM.
 */
public class ArithmeticMainInstructions extends AbstractMainInstructions {

    public ArithmeticMainInstructions(Instruction instruction) {
        super(instruction);
    }

    private void executesAMR() {
        char data = Registers.fetchMemory((char) ADDRESS());
        data = (char) (RDATA() + data);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    private void executesSMR() {
        char data = Registers.fetchMemory((char) ADDRESS());
        data = (char) (RDATA() - data);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    private void executesAIR() {
        char data = (char) IMMEDIATE();
        data = (char) (RDATA() + data);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    private void executesSIR() {
        char data = (char) IMMEDIATE();
        data = (char) (RDATA() - data);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    private void executesMLT() {
        long xData = RXDATA();
        long yData = RYDATA();
        long data = xData * yData;
        char highOrder = (char) (data >> 16);
        char lowOrder = (char) (data);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister(highOrder, RX());
        Registers.generalProposeRegisters.storeByRegister(lowOrder, RX() + 1);
    }

    private void executesDVD() {
        int xData = RXDATA();
        int yData = RYDATA();
        char quotient, remainder;
        if (yData == 0) {
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(2, true);
            quotient = (char) 0;
            remainder = (char) 0;
        } else {
            quotient = (char) (xData / yData);
            remainder = (char) (xData % yData);
        }
        //add to Register
        Registers.generalProposeRegisters.storeByRegister(quotient, RX());
        Registers.generalProposeRegisters.storeByRegister(remainder, RX() + 1);
    }

    private void executesTRR() {
        int xData = RXDATA();
        int yData = RYDATA();
        if (xData == yData) {
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(3, true);
        } else {
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(3, false);
        }
    }

    private void executesAND() {
        Registers.generalProposeRegisters.storeByRegister((char) (RXDATA() & RYDATA()), RX());
    }

    private void executesORR() {
        Registers.generalProposeRegisters.storeByRegister((char) (RXDATA() | RYDATA()), RX());
    }

    private void executesNOT() {
        Registers.generalProposeRegisters.storeByRegister((char) (~RXDATA()), RX());
    }

}
