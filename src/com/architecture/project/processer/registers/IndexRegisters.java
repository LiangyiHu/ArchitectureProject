package com.architecture.project.processer.registers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 11:37 PM.
 */
public class IndexRegisters extends AbstractRegisters {
    private static final int IXR_NUM = 4;

    @Override
    public boolean indexLegal(int ix) {
        return !(ix >= registers.size() || ix < 1);
    }

    @Override
    public int length() {
        return IXR_NUM;
    }
}
