package com.architecture.project.processer.registers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 11:35 PM.
 */
public class GeneralProposeRegisters extends AbstractRegisters {
    private static final int GPR_NUM = 4;
    public GeneralProposeRegisters() {
        registers = new ArrayList<>(GPR_NUM);
    }
}
