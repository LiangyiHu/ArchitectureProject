package com.architecture.project.processor.registers;

/**
 * @author taoranxue on 9/14/16 11:37 PM.
 */
public class IndexRegisters extends AbstractRegisters {
    private static final int IXR_NUM = 4;

    //Index register is not 0 based in instruction set, so check if the format is right.
    @Override
    public boolean indexLegal(int ix) {
        return !(ix >= registers.size() || ix < 1);
    }

    @Override
    public int length() {
        return IXR_NUM;
    }
}
