package com.architecture.project.processor;

import com.architecture.project.utils.Constants;
import com.architecture.project.utils.ProjectUtils;

/**
 * ALU simulator.
 *
 * @author taoranxue on 9/14/16 11:28 PM.
 */
public class ALU {
    private int aluA;
    private int aluB;
    private int data;
    private int op;

    public void init(int iCode, int valC, int valA, int valB) {
        data = valC;
        op = -1;
        if (ProjectUtils.inArrays(iCode, Constants.NEED_ADD)) {
            op = Constants.A_ADD;
        } else if (ProjectUtils.inArrays(iCode, Constants.NEED_SUB)) {
            op = Constants.A_SUB;
        } else if (ProjectUtils.inArrays(iCode, Constants.NEED_AND)) {
            op = Constants.A_AND;
        }


        if (ProjectUtils.inArrays(iCode, new int[]{Constants.I_AMR, Constants.I_SMR, Constants.I_AIR, Constants.I_SIR})) {
            aluA = valA;
            aluB = valC;
        } else if (ProjectUtils.inArrays(iCode, new int[]{Constants.I_MLT, Constants.I_DVD, Constants.I_TRR,
                Constants.I_AND, Constants.I_ORR, Constants.I_NOT})) {
            aluA = valA;
            aluB = valB;
        }
    }

    public int execute() {
        if (op == Constants.A_ADD) {
            data = aluA + aluB;
        } else if (op == Constants.A_SUB) {
            data = aluA - aluB;
        } else if (op == Constants.A_AND) {
            data = aluA & aluB;
        }

        return data;
    }

}
