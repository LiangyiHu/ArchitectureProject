package com.architecture.project.instruction.sequence;

import com.architecture.project.processor.Processor;
import com.architecture.project.processor.registers.Register;
import com.architecture.project.processor.registers.Registers;

/**
 * Register file for 4 GPRs and 3 IXRs.
 *
 * @author taoranxue on 11/10/16 5:18 PM.
 */
public class SeqRegisterFile {
    //reg(dstE,   valE,   dstM,   valM,   srcA,   valA,    srcB,   valB   )
    private char dstE;
    private char valE;
    private char dstM;
    private char valM;
    private char srcA;
    private char valA;
    private char srcB;
    private char valB;

    private char eaxData = 0;
    private boolean eaxWrite = false;
    private char ebxData = 0;
    private boolean ebxWrite = false;
    private char ecxData = 0;
    private boolean ecxWrite = false;
    private char edxData = 0;
    private boolean edxWrite = false;
    private char ix1Data = 0;
    private boolean ix1Write = false;
    private char ix2Data = 0;
    private boolean ix2Write = false;
    private char ix3Data = 0;
    private boolean ix3Write = false;

    boolean change;

    public SeqRegisterFile() {
        change = false;
    }

    public void set(char dstE, char valE, char dstM, char valM, char srcA, char valA, char srcB, char valB) {
        this.dstE = dstE;
        this.valE = valE;
        this.dstM = dstM;
        this.valM = valM;
        this.srcA = srcA;
        this.valA = valA;
        this.srcB = srcB;
        this.valB = valB;
        this.change = true;
    }

//    private char regRead(char regId) {
//        if (regId == Registers.EAX) {
//            return Registers.generalProposeRegisters.fetchByRegister(0);
//        }
//        if (regId == Registers.EBX) {
//            return Registers.generalProposeRegisters.fetchByRegister(1);
//        }
//        if (regId == Registers.ECX) {
//            return Registers.generalProposeRegisters.fetchByRegister(2);
//        }
//        if (regId == Registers.EDX) {
//            return Registers.generalProposeRegisters.fetchByRegister(3);
//        }
//        if (regId == Registers.IX1) {
//            return Registers.indexRegisters.fetchByRegister(1);
//        }
//        if (regId == Registers.IX2) {
//            return Registers.indexRegisters.fetchByRegister(2);
//        }
//        if (regId == Registers.IX3) {
//            return Registers.indexRegisters.fetchByRegister(3);
//        }
//        return 0;
//    }

//    private void reg(char regId, char regData, boolean regWrite) {
//        if (regWrite) {
//            if (regId == Registers.EAX) {
//                Registers.generalProposeRegisters.storeByRegister(regData, 0);
//            }
//            if (regId == Registers.EBX) {
//                Registers.generalProposeRegisters.storeByRegister(regData, 1);
//            }
//            if (regId == Registers.ECX) {
//                Registers.generalProposeRegisters.storeByRegister(regData, 2);
//            }
//            if (regId == Registers.EDX) {
//                Registers.generalProposeRegisters.storeByRegister(regData, 3);
//            }
//            if (regId == Registers.IX1) {
//                Registers.indexRegisters.storeByRegister(regData, 1);
//            }
//            if (regId == Registers.IX2) {
//                Registers.indexRegisters.storeByRegister(regData, 2);
//            }
//            if (regId == Registers.IX3) {
//                Registers.indexRegisters.storeByRegister(regData, 3);
//            }
//        }
//    }

//    public void start() {
//        if (change) {
//            reg(Registers.EAX, eaxData, eaxWrite);
//            reg(Registers.EBX, ebxData, ebxWrite);
//            reg(Registers.ECX, ecxData, ecxWrite);
//            reg(Registers.EDX, edxData, edxWrite);
//            reg(Registers.IX1, ix1Data, ix1Write);
//            reg(Registers.IX2, ix2Data, ix2Write);
//            reg(Registers.IX3, ix3Data, ix3Write);
//
//            valA = (srcA == Registers.EAX) ? regRead(srcA)
//                    : (srcA == Registers.EBX) ? regRead(srcA)
//                    : (srcA == Registers.ECX) ? regRead(srcA)
//                    : (srcA == Registers.EDX) ? regRead(srcA)
//                    : (srcA == Registers.IX1) ? regRead(srcA)
//                    : (srcA == Registers.IX2) ? regRead(srcA)
//                    : (srcA == Registers.IX3) ? regRead(srcA) : 0;
//
//            valB = (srcB == Registers.EAX) ? regRead(srcB)
//                    : (srcB == Registers.EBX) ? regRead(srcB)
//                    : (srcB == Registers.ECX) ? regRead(srcB)
//                    : (srcB == Registers.EDX) ? regRead(srcB)
//                    : (srcB == Registers.IX1) ? regRead(srcB)
//                    : (srcB == Registers.IX2) ? regRead(srcB)
//                    : (srcB == Registers.IX3) ? regRead(srcB) : 0;
//
//            eaxData = (dstM == Registers.EAX) ? valM : valE;
//            ebxData = (dstM == Registers.EBX) ? valM : valE;
//            ecxData = (dstM == Registers.ECX) ? valM : valE;
//            edxData = (dstM == Registers.EDX) ? valM : valE;
//            ix1Data = (dstM == Registers.IX1) ? valM : valE;
//            ix2Data = (dstM == Registers.IX2) ? valM : valE;
//            ix3Data = (dstM == Registers.IX3) ? valM : valE;
//
//            eaxWrite = (dstM == Registers.EAX) || (dstE == Registers.EAX);
//            ebxWrite = (dstM == Registers.EBX) || (dstE == Registers.EBX);
//            ecxWrite = (dstM == Registers.ECX) || (dstE == Registers.ECX);
//            edxWrite = (dstM == Registers.EDX) || (dstE == Registers.EDX);
//            ix1Write = (dstM == Registers.IX1) || (dstE == Registers.IX1);
//            ix2Write = (dstM == Registers.IX2) || (dstE == Registers.IX2);
//            ix3Write = (dstM == Registers.IX3) || (dstE == Registers.IX3);
//        }
//    }

    public char getValA() {
        return valA;
    }

    public char getValB() {
        return valB;
    }
}
