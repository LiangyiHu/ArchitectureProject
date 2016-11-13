package com.architecture.project.processor;

import com.architecture.project.processor.registers.PipeRegisters;
import com.architecture.project.processor.registers.Registers;
import com.architecture.project.utils.Constants;
import com.architecture.project.utils.ProjectUtils;

/**
 * @author taoranxue on 11/13/16 3:14 AM.
 */
public class PipeProcessor {
    PipeRegisters input;
    PipeRegisters output;

    int state;
    boolean halt;

    public PipeProcessor() {
        input = new PipeRegisters();
        output = new PipeRegisters();
        state = Constants.STAT_AOK;
        halt = false;

    }

    public void executeStage() {
        output.M_icode = input.E_icode;
        output.M_valA = input.E_valA;
        output.M_dstM = input.E_dstM;
        output.M_stat = input.E_stat;

        if (input.E_icode == Constants.I_HALT && input.E_stat != Constants.STAT_BUB) {
            this.halt = true;
            output.M_stat = Constants.STAT_HLT;
        }
    }

    public void memoryStage() {
        boolean writeMemory = false;
        boolean readMemory = false;
        int memoryAddress = 0;

        output.W_stat = input.M_stat;
        output.W_icode = input.M_icode;
        output.W_valE = input.M_valE;
        output.W_dstE = input.M_dstE;
        output.W_dstM = input.M_dstM;

        if (ProjectUtils.inArrays(input.M_icode, new int[]{Constants.I_LDR, Constants.I_STR, Constants.I_AMR, Constants.I_SMR})) {
            //Memory address is the result of execute stage
            memoryAddress = input.M_valE;
        } else if (ProjectUtils.inArrays(input.M_icode, new int[]{})) {
            //Memory addresses are stored in registers:
        }

        //instruction read memory
        readMemory = ProjectUtils.inArrays(input.M_icode, new int[]{Constants.I_LDR, Constants.I_AMR, Constants.I_SMR});
        writeMemory = ProjectUtils.inArrays(input.M_icode, new int[]{Constants.I_STR});

        if (readMemory) {
            output.W_valM = Registers.fetchMemory((char) memoryAddress);
        }

        if (writeMemory) {
            Registers.storeMemory((char) input.M_valA, (char) memoryAddress);
        }
    }

    public void writeBackStage() {
        this.state = input.W_stat;
        // Don't need to write back if write into memory
        if (ProjectUtils.inArrays(input.W_stat, new int[]{Constants.I_STR})) {
            return;
        }
        Registers.writeReg(input.W_dstE, input.W_valE);
        Registers.writeReg(input.W_dstM, input.W_valM);
    }
}
