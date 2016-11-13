package com.architecture.project.processor.registers;

import com.architecture.project.utils.Constants;

/**
 * @author taoranxue on 11/13/16 3:04 AM.
 */
public class PipeRegisters {
    public int F_predPC = 0;
    public int F_carryPC = -1;
    public int F_stat = Constants.STAT_AOK;

    // Decode
    public int D_icode = Constants.I_NOP;
    public int D_ifun = 0;
    public int D_rA = Constants.R_NONE;
    public int D_rB = Constants.R_NONE;
    public int D_valC = 0;
    public int D_valP = 0;
    public int D_carryPC = -1;
    public int D_stat = Constants.STAT_BUB;

    // Execute
    public int E_icode = Constants.I_NOP;
    public int E_ifun = 0;
    public int E_valC = 0;
    public int E_valA = 0;
    public int E_valB = 0;
    public int E_dstE = Constants.R_NONE;
    public int E_dstM = Constants.R_NONE;
    public int E_srcA = Constants.R_NONE;
    public int E_srcB = Constants.R_NONE;
    public int E_carryPC = -1;
    public int E_stat = Constants.STAT_BUB;

    // Memory
    public int M_icode = Constants.I_NOP;
    public int M_valE = 0;
    public int M_valA = 0;
    public int M_dstE = Constants.R_NONE;
    public int M_dstM = Constants.R_NONE;
    public int M_stat = Constants.STAT_BUB;
    public int M_carryPC = -1;
    boolean M_Cnd = false;

    // Write Back
    public int W_icode = Constants.I_NOP;
    public int W_ifun = 0;
    public int W_valE = 0;
    public int W_valM = 0;
    public int W_dstE = Constants.R_NONE;
    public int W_dstM = Constants.R_NONE;
    public int W_stat = Constants.STAT_BUB;

    
}
