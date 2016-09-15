package com.architecture.project.processer.registers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 11:37 PM.
 */
public class IndexRegisters extends AbstractRegisters {
    private static final int IXR_NUM = 3;
    public IndexRegisters() {
        registers = new ArrayList<>(IXR_NUM);
    }
}
