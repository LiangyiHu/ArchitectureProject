package com.architecture.project.instruction;

import com.architecture.project.devices.IOBus;
import com.architecture.project.processor.Processor;
import com.architecture.project.processor.registers.Registers;

/**
 * Created by larry on 10/13/2016.
 */
public class IOInstructions extends AbstractMainInstructions {
    //Customize
    public int DEVICEID() {
        return R(11, 16);
    }

    public IOInstructions(Instruction instruction) {
        super(instruction);
        //Move operator code;
        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);
    }


    private void executeIN() {
        char data = Processor.ioBus.getNext(DEVICEID());
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }

    private void executeOUT() {
        char data = Registers.generalProposeRegisters.fetchByRegister(R());
        Processor.ioBus.write(DEVICEID(), data);
    }

    private void executeCHK() {
        char data = Processor.ioBus.ReadDeviceStatus(DEVICEID());
        Registers.generalProposeRegisters.storeByRegister(data, R());
    }
}
