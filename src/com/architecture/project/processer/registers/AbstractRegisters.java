package com.architecture.project.processer.registers;

import com.architecture.project.exception.RegistersIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 11:42 PM.
 */
public abstract class AbstractRegisters {
    private static final int DEFAULT_NUM = 1;
    protected List<Register> registers;

    public AbstractRegisters() {
        registers = new ArrayList<>(DEFAULT_NUM);
    }

    public char fetchByRegister(int ix) {
        if (ix >= registers.size() || ix < 0) {
            throw new RegistersIndexOutOfBoundsException(ix);
        }
        return registers.get(ix).getData();
    }
}
