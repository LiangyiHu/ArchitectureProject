package com.architecture.project.processor.registers;

/**
 * @author taoranxue on 12/2/16 2:17 AM.
 */
public class FloatingPointRegisters extends AbstractRegisters {

    private static final int FPR_NUM = 2;

    @Override
    public int length() {
        return FPR_NUM;
    }
}
