package com.architecture.project.utils;

/**
 * @author taoranxue on 11/13/16 3:07 AM.
 */
public class Constants {
    public static final int P_LOAD = 0;
    public static final int P_STALL = 1;
    public static final int P_BUBBLE = 2;
    public static final int P_ERROR = 3;

    public static final int I_HALT = 000;
    public static final int I_NOP = 010;
    public static final int I_LDR = 001;
    public static final int I_STR = 002;
    public static final int I_LDA = 003;
    public static final int I_AMR = 004;
    public static final int I_SMR = 005;
    public static final int I_AIR = 006;
    public static final int I_SIR = 007;
    public static final int I_LDX = 041;
    public static final int I_STX = 042;
    public static final int I_MLT = 020;
    public static final int I_DVD = 021;
    public static final int I_TRR = 022;
    public static final int I_AND = 023;
    public static final int I_ORR = 024;
    public static final int I_NOT = 025;

    //Operators
    public static final int A_ADD = 0;
    public static final int A_SUB = 1;
    public static final int A_AND = 2;
    public static final int A_XOR = 3;
    public static final int A_NONE = 4;

    public static final int F_OF = 1; // OF at bit 0
    public static final int F_SF = 2; // SF at bit 1
    public static final int F_ZF = 4; // ZF at bit 2

    public static final int C_TRUE = 0;
    public static final int C_LE = 1;
    public static final int C_L = 2;
    public static final int C_E = 3;
    public static final int C_NE = 4;
    public static final int C_GE = 5;
    public static final int C_G = 6;

    public static final int STAT_BUB = 0;
    public static final int STAT_AOK = 1;
    public static final int STAT_HLT = 2;
    public static final int STAT_ADR = 3;
    public static final int STAT_INS = 4;
    public static final int STAT_PIP = 5;


    // 4 GPRs
    public static final int R_EAX = 0x01;
    public static final int R_EBX = 0x02;
    public static final int R_ECX = 0x03;
    public static final int R_EDX = 0x04;
    // 3 IXRs
    public static final int R_IX1 = 0x11;
    public static final int R_IX2 = 0x12;
    public static final int R_IX3 = 0x13;
    public static final int R_NONE = 0xf;

    public static final int DEF_CC = 4;


    public static final int[] NEED_ADD = new int[]{I_AMR, I_AIR};
    public static final int[] NEED_SUB = new int[]{I_AMR, I_SIR};
    public static final int[] NEED_AND = new int[]{I_AND};
}
