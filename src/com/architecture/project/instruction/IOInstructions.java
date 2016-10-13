package com.architecture.project.instruction;

import com.architecture.project.Devices.IOBus;
import com.architecture.project.processor.registers.Registers;

/**
 * Created by larry on 10/13/2016.
 */
public class IOInstructions extends Instructions {
    private int R = -1;
    private int DevID = -1;

    public IOInstructions(Instruction instruction){
        //Move operator code;
        int operatorCode = instruction.getOperatorCode();
        this.setOperatorCode(operatorCode);
        R = instruction.subInstruction(6, 8).parseInt();
        DevID = instruction.subInstruction(11, 16).parseInt();
    }


    private void executeIN() {
        char data = IOBus.ReadIO(DevID);
        Registers.generalProposeRegisters.storeByRegister(data,R);
    }

    private void executeOUT() {
        char data = Registers.generalProposeRegisters.fetchByRegister(R);
        IOBus.writeIO(DevID,data);
    }

    private void executeCHK() {
        char data = IOBus.ReadDeviceStatus(DevID);
        Registers.generalProposeRegisters.storeByRegister(data,R);
    }
}
