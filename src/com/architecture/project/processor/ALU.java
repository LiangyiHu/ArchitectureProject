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
        if (ProjectUtils.inArrays(iCode, Constants.NEED_ADD)) {
            op = Constants.O_ADD;
        } else if (ProjectUtils.inArrays(iCode, Constants.NEED_SUB)) {
            op = Constants.O_SUB;
        }
        if (ProjectUtils.inArrays(iCode, new int[]{Constants.I_AMR, Constants.I_SMR, Constants.I_AIR, Constants.I_SIR})) {
            aluA = valA;
            aluB = valC;
        } else {
            //
        }
    }

    public int execute() {
        if (op == Constants.O_ADD) {
            data = aluA + aluB;
        } else if (op == Constants.O_SUB) {
            data = aluA - aluB;
        }

        return data;
    }

}
