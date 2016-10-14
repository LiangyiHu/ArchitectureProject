package com.architecture.project.instruction;

import com.architecture.project.processor.Processor;
import com.architecture.project.processor.registers.Registers;

/**
 * @author taoranxue on 10/11/16 11:41 PM.
 */
public class ArithmeticMainInstructions extends AbstractMainInstructions {

    public ArithmeticMainInstructions(Instruction instruction) {
        super(instruction);
    }

    private void executeAMR() {
        short data = (short)Registers.fetchMemory((char) EFFECTIVEADDRESS());
        int sum = (int) RDATA() + (int) data;
        setFlowConditionCode(sum);
        //add to Register

        Registers.generalProposeRegisters.storeByRegister((char) sum, R());
    }

    private void executeSMR() {
        short data = (short) Registers.fetchMemory((char) EFFECTIVEADDRESS());
        int sum = (int) RDATA() - (int) data;
        setFlowConditionCode(sum);
        //add to Register

        Registers.generalProposeRegisters.storeByRegister((char) sum, R());
    }

    private void executeAIR() {
        int sum = (int) RDATA() + IMMEDIATE();
        setFlowConditionCode(sum);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister((char) sum, R());
    }

    private void executeSIR() {
        int sum = (int) RDATA() - IMMEDIATE();
        setFlowConditionCode(sum);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister((char) sum, R());
    }

    private void executeMLT() {
        short xData = (short) ((char) RXDATA());
        short yData = (short) ((char) RYDATA());
        int data = (int) xData * (int) yData;
        char highOrder = (char) (data >> 16);
        char lowOrder = (char) (data);
        //add to Register
        Registers.generalProposeRegisters.storeByRegister(highOrder, RX());
        Registers.generalProposeRegisters.storeByRegister(lowOrder, RX() + 1);
    }

    private void executeDVD() {
        int xData = RXDATA();
        int yData = RYDATA();
        char quotient, remainder;
        setDivZeroConditionCode(yData);
        if (yData == 0) {
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

    private void executeTRR() {
        int xData = RXDATA();
        int yData = RYDATA();
        setEqualOrNotConditionCode(xData, yData);
    }

    private void executeAND() {
        Registers.generalProposeRegisters.storeByRegister((char) (RXDATA() & RYDATA()), RX());
    }

    private void executeORR() {
        Registers.generalProposeRegisters.storeByRegister((char) (RXDATA() | RYDATA()), RX());
    }

    private void executeNOT() {
        Registers.generalProposeRegisters.storeByRegister((char) (~RXDATA()), RX());
    }

}
