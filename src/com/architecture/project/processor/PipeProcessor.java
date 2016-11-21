package com.architecture.project.processor;

import com.architecture.project.instruction.Instruction;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processor.registers.PipeRegisters;
import com.architecture.project.processor.registers.Registers;
import com.architecture.project.utils.Constants;
import com.architecture.project.utils.ProjectUtils;

/**
 * @author taoranxue on 11/13/16 3:14 AM.
 */
public class PipeProcessor {
    private PipeRegisters input;
    private PipeRegisters output;
    private ALU alu;
    private int cycle;

    private int state;
    private boolean halt;

    public void setPipelinePC(int pc) {
        this.input.F_predPC = pc;
    }

    public PipeProcessor() {
        cycle = 0;
        input = new PipeRegisters();
        output = new PipeRegisters();
        alu = new ALU();

        state = Constants.STAT_AOK;
        halt = false;
    }

    public void next() throws CloneNotSupportedException {
        cycle++;
        writeBackStage();
        memoryStage();
        executeStage();
        decodeStage();
        fetchStage();

        nextPipeRegister();
    }

    private String print(PipeRegisters reg) {
        String fetchStr = String.format("FETCH \t prePc: %d \t instructions: %d\n",
                reg.F_predPC, cycle);
        String decodeStr = String.format("DECODE \t stat: %d \t icode: %d \t rA: %d \t rB: %d \t I: %d \t valC: %d \t valP: %d\n",
                reg.D_stat, reg.D_icode, reg.D_rA, reg.D_rB, reg.D_i, reg.D_valC, reg.D_valP);
        String executeStr = String.format("EXECUTE \t stat: %d \t icode: %d \t valC: %d \t valA: %d \t valB: %d \t dstE: %d \t dstM: %d \t srcA : %d \t srcB: %d\n",
                reg.E_stat, reg.E_icode, reg.E_valC, reg.E_valA, reg.E_valB, reg.E_dstE, reg.E_dstM, reg.E_srcA, reg.E_srcB);
        String memoryStr = String.format("MEMORY \t stat: %d \t icode: %d \t valE: %d \t valA: %d \t dstE: %d \t dstM: %d\n",
                reg.M_stat, reg.M_icode, reg.M_valE, reg.M_valA, reg.M_dstE, reg.M_dstM);
        String writeBackStr = String.format("WRITE BACK \t stat: %d \t icode %d \t valE: %d \t valM: %d \t dstE: %d \t dstM: %d \n",
                reg.W_stat, reg.W_icode, reg.W_valE, reg.W_valM, reg.W_dstE, reg.W_dstM);

        return (fetchStr + decodeStr + executeStr + memoryStr + writeBackStr);


    }

    private void fetchStage() {
        if (this.halt) return;

        int next;

//        if (input.M_icode == Constants.I_JXX && !input.M_Cnd) {
//            next = input.M_valA;
//        } else if (input.W_icode == Constants.I_RET) {
//            next = input.W_valM;
//        } else {
        next = input.F_predPC;
//        }
        int ins = MainMemory.fetch(next);
        next += 2;
        Instruction instruction = new Instruction((char) ins);
        Registers.instructionRegister.setOne((char) ins);
//        Registers.programCounter.addPC();
        output.D_icode = instruction.subInstruction(0, 6).parseInt();

        if (!ProjectUtils.inArrays(output.D_icode, new int[]{Constants.I_HALT, Constants.I_LDR,
                Constants.I_STR, Constants.I_LDA, Constants.I_AMR, Constants.I_SMR, Constants.I_AIR,
                Constants.I_SIR, Constants.I_LDX, Constants.I_STX, Constants.I_MLT, Constants.I_DVD,
                Constants.I_TRR, Constants.I_AND, Constants.I_ORR, Constants.I_NOT, Constants.I_NOP})) {
            output.F_stat = output.D_stat = Constants.STAT_INS;
            System.out.println("r1");
            return;
        } else if (ProjectUtils.inArrays(output.D_icode, new int[]{Constants.I_HALT})) {
            output.F_stat = output.D_stat = Constants.STAT_HLT;
            System.out.println("r2");
            return;
        } else {
            output.F_stat = output.D_stat = Constants.STAT_AOK;
        }
//
        if (ProjectUtils.inArrays(output.D_icode, new int[]{Constants.I_LDR, Constants.I_STR,
                Constants.I_LDA, Constants.I_AMR, Constants.I_SMR, Constants.I_AIR, Constants.I_SIR,
                Constants.I_LDX, Constants.I_STX, Constants.I_MLT, Constants.I_DVD, Constants.I_TRR,
                Constants.I_AND, Constants.I_ORR, Constants.I_NOT})) {
            output.D_rA = instruction.subInstruction(6, 8).parseInt();
            output.D_rB = instruction.subInstruction(8, 10).parseInt();
            output.D_i = instruction.subInstruction(10, 11).parseInt();
            output.D_valC = instruction.subInstruction(11, 16).parseInt();
        } else {
            output.D_rA = Constants.R_NONE;
            output.D_rB = Constants.R_NONE;
        }
        output.F_predPC = next;
        //predict block
        output.D_valP = next;
    }

    private void decodeStage() {
        output.E_icode = input.D_icode;
        output.E_valC = input.D_valC;
        output.E_stat = input.D_stat;


        if (ProjectUtils.inArrays(input.D_icode, new int[]{Constants.I_STR, Constants.I_LDA,
                Constants.I_AMR, Constants.I_SMR, Constants.I_AIR, Constants.I_SIR, Constants.I_MLT, Constants.I_DVD,
                Constants.I_TRR, Constants.I_AND, Constants.I_ORR, Constants.I_NOT})) {
            output.E_srcA = input.D_rA;
        } else {
            output.E_srcA = Constants.R_NONE;
        }


        if (ProjectUtils.inArrays(input.D_icode, new int[]{Constants.I_LDR, Constants.I_STR,
                Constants.I_LDA, Constants.I_LDX, Constants.I_STX, Constants.I_MLT, Constants.I_DVD,
                Constants.I_TRR, Constants.I_AND, Constants.I_ORR})) {
            output.E_srcB = input.D_rB;
        } else {
            output.E_srcB = Constants.R_NONE;
        }

        if (ProjectUtils.inArrays(input.D_icode, new int[]{Constants.I_LDA, Constants.I_AMR,
                Constants.I_SMR, Constants.I_AIR, Constants.I_SIR, Constants.I_MLT, Constants.I_DVD,
                Constants.I_TRR, Constants.I_AND, Constants.I_ORR, Constants.I_NOT})) {
            output.E_dstE = input.D_rA;
        } else {
            output.E_dstE = Constants.R_NONE;
        }

        if (ProjectUtils.inArrays(input.D_icode, new int[]{Constants.I_LDR})) {
            output.E_dstM = input.D_rA;
        } else if (ProjectUtils.inArrays(input.D_icode, new int[]{Constants.I_LDX})) {
            output.E_dstM = input.D_rB;
        } else {
            output.E_dstM = Constants.R_NONE;
        }

        if (output.E_srcA != Constants.R_NONE) {
            if (output.E_srcA == output.M_dstE) {
                output.E_valA = output.M_valE;
            } else if (output.E_srcA == input.M_dstM) {
                output.E_valA = output.W_valM;
            } else if (output.E_srcA == input.M_dstE) {
                output.E_valA = input.M_valE;
            } else if (output.E_srcA == input.W_dstM) {
                output.E_valA = input.W_valM;
            } else if (output.E_srcA == input.W_dstE) {
                output.E_valA = input.W_valE;
            } else {
                output.E_valA = Registers.readReg(output.E_srcA);
            }
        }


        if (output.E_srcB != Constants.R_NONE) {
            if (output.E_srcB == output.M_dstE) {
                output.E_valB = output.M_valE;
            } else if (output.E_srcB == input.M_dstM) {
                output.E_valB = output.W_valM;
            } else if (output.E_srcB == input.M_dstE) {
                output.E_valB = input.M_valE;
            } else if (output.E_srcB == input.W_dstM) {
                output.E_valB = input.W_valM;
            } else if (output.E_srcB == input.W_dstE) {
                output.E_valB = input.W_valE;
            } else {
                output.E_valB = Registers.readReg(output.E_srcB);
            }
        }

        // Need indirect addressing
        int address = input.D_valC;
        if (ProjectUtils.inArrays(input.D_icode, new int[]{Constants.I_LDR, Constants.I_STR,
                Constants.I_LDA, Constants.I_LDX, Constants.I_STX})) {
            if (input.D_i == 0) {
                address = Registers.readReg(output.E_srcB) + input.D_valC;
            } else {
                address = Registers.fetchMemory((char) (output.E_valB + input.D_valC));
            }
        }
        output.E_valC = address;


    }


    private void executeStage() {
        output.M_icode = input.E_icode;
        output.M_valA = input.E_valA;
        output.M_dstM = input.E_dstM;
        output.M_stat = input.E_stat;
        //
        output.M_dstE = input.E_dstE;

        if (input.E_icode == Constants.I_HALT && input.E_stat != Constants.STAT_BUB) {
            this.halt = true;
            output.M_stat = Constants.STAT_HLT;
        }

        alu.init(input.E_icode, input.E_valC, input.E_valA, input.E_valB);
        output.M_valE = alu.execute();
        output.M_valA = input.E_valA;

//        if (input.E_icode == Constants.I_RRMOVL && !output.M_Cnd) {
//            output.M_dstE = Constants.R_NONE;
//        } else {
//            output.M_dstE = input.E_dstE;
//        }
    }

    private void memoryStage() {
        boolean writeMemory = false;
        boolean readMemory = false;
        int memoryAddress = 0;

        output.W_stat = input.M_stat;
        output.W_icode = input.M_icode;
        output.W_valE = input.M_valE;
        output.W_dstE = input.M_dstE;
        output.W_dstM = input.M_dstM;

        if (ProjectUtils.inArrays(input.M_icode, new int[]{Constants.I_LDR, Constants.I_STR,
                Constants.I_AMR, Constants.I_SMR})) {
            //Memory address is the result of execute stage
            memoryAddress = input.M_valE;
        } else if (ProjectUtils.inArrays(input.M_icode, new int[]{})) {
            //Memory addresses are stored in registers:
        }

        //instruction read memory
        readMemory = ProjectUtils.inArrays(input.M_icode, new int[]{Constants.I_LDR,
                Constants.I_AMR, Constants.I_SMR});
        writeMemory = ProjectUtils.inArrays(input.M_icode, new int[]{Constants.I_STR});

        if (readMemory) {
            output.W_valM = Registers.fetchMemory((char) memoryAddress);
        }

        if (writeMemory) {
            Registers.storeMemory((char) input.M_valA, (char) memoryAddress);
        }
    }

    private void writeBackStage() {
        this.state = input.W_stat;
        // Don't need to write back if write into memory
        if (ProjectUtils.inArrays(input.W_stat, new int[]{Constants.I_STR})) {
            return;
        }
        Registers.writeReg(input.W_dstE, input.W_valE);
        Registers.writeReg(input.W_dstM, input.W_valM);
    }

    private void nextPipeRegister() throws CloneNotSupportedException {
//        boolean F_stall = ProjectUtils.inArrays(input.E_dstM, new int[]{output.E_srcA, output.E_srcB});
//        boolean D_stall = ([Constant.I_MRMOVL, Constant.I_POPL, Constant.I_LEAVE].indexOf(input.E_icode) != -1) && ([output.E_srcA, output.E_srcB].indexOf(input.E_dstM) != -1);
//        boolean D_bubble = (input.E_icode == Constant.I_JXX && !output.M_Cnd) || ((!D_stall) && ([input.D_icode, input.E_icode, input.M_icode].indexOf(Constant.I_RET) != -1));
//        boolean E_bubble = (input.E_icode == Constant.I_JXX && !output.M_Cnd) || (([Constant.I_MRMOVL, Constant.I_POPL, Constant.I_LEAVE].indexOf(input.E_icode) != -1 && [output.E_srcA, output.E_srcB].indexOf(input.E_dstM) != -1));
//        boolean M_bubble = [Constant.STAT_ADR, Constant.STAT_INS, Constant.STAT_HLT].indexOf(output.W_stat) != -1 || [Constant.STAT_ADR, Constant.STAT_INS, Constant.STAT_HLT].indexOf(input.W_stat) != -1;
//
//        if (([Constant.I_MRMOVL, Constant.I_POPL].indexOf(input.E_icode) != -1) && (input.E_dstM == input.E_srcA || input.E_dstM == input.E_srcB)) {
//            this.use++;
//        }
        PipeRegisters reg = (PipeRegisters) output.clone();

        //do pipeline


        this.input = reg;


    }
}
