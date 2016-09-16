package com.architecture.project.processer.registers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 11:35 PM.
 */
public class GeneralProposeRegisters extends AbstractRegisters {
    private static final int GPR_NUM = 4;

    @Override
    public int length() {
        return GPR_NUM;
    }
}
