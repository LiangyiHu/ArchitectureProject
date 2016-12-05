package com.architecture.project.instruction;

import com.architecture.project.processor.registers.Registers;
import com.architecture.project.utils.Floating;

/**
 * @author taoranxue on 12/2/16 12:19 AM.
 */
public class FloatingVectorInstructions extends AbstractMainInstructions {
    public FloatingVectorInstructions(Instruction instruction) {
        super(instruction);
    }


    //FADD
    private void executeFADD() {
        char data = Registers.fetchMemory((char) EFFECTIVEADDRESS());
        Floating a = new Floating(Registers.floatingPointRegisters.fetchByRegister(R()));
        Floating b = new Floating(data);
        Floating res = a.add(b);
        //add to Register
        Registers.floatingPointRegisters.storeByRegister((char) res.toBinary(), R());
    }

    //FSUB
    private void executeFSUB() {
        char data = Registers.fetchMemory((char) EFFECTIVEADDRESS());
        Floating a = new Floating(Registers.floatingPointRegisters.fetchByRegister(R()));
        Floating b = new Floating(data);
        Floating res = a.sub(b);
        //add to Register
        Registers.floatingPointRegisters.storeByRegister((char) res.toBinary(), R());
    }

    //VADD
    private void executeVADD() {
        char addressStart1 = (char) EFFECTIVEADDRESS();
        char addressStart2 = (char) EFFECTIVEADDRESS();
        char len = RDATA();
        System.out.println((int)len);
        System.out.println((int)addressStart1);
        System.out.println((int)addressStart2);
        for (int i = 0; i <= len; ++i) {
            char data1 = Registers.fetchMemory((char) addressStart2);
            char data2 = Registers.fetchMemory((char) addressStart1);
            Registers.storeMemory((char) (data1 + data2), addressStart1);
            addressStart1 += 2;
            addressStart2 += 2;
        }

    }

    //VSUB
    private void executeVSUB() {
        char addressStart1 = (char) EFFECTIVEADDRESS();
        char addressStart2 = (char) EFFECTIVEADDRESS();
        char len = RDATA();
        for (int i = 0; i <= len; ++i) {
            char data1 = Registers.fetchMemory((char) addressStart2);
            char data2 = Registers.fetchMemory((char) addressStart1);
            Registers.storeMemory((char) (data1 - data2), addressStart1);
            addressStart1 += 2;
            addressStart2 += 2;
        }
    }


    //CNVRT
    private void executeCNVRT() {
        int F = RDATA();
        char data = Registers.fetchMemory((char) EFFECTIVEADDRESS());
        if (F == 0) {
            Registers.floatingPointRegisters.storeByRegister(data, R());
        } else {
            Floating f = new Floating(data);
            Registers.floatingPointRegisters.storeByRegister(f.toBinary(), R());
        }
    }

    //LDFR
    private void executeLDFR() {

    }

    //STFR
    private void executeSTFR() {

    }
}
