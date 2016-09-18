package com.architecture.project.processer.registers;

import com.architecture.project.exception.RegistersIndexOutOfBoundsException;
import com.architecture.project.gui.img.Led;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 11:42 PM.
 */
public abstract class AbstractRegisters {
    private static final int DEFAULT_NUM = 1;
    protected List<Register> registers;

    public AbstractRegisters() {
        registers = new ArrayList<>(length());
        for (int i = 0; i < length(); ++ i) {
            registers.add(new Register());
        }
    }

    public int length() {
        return DEFAULT_NUM;
    }

    public boolean indexLegal(int ix) {
        if (ix >= registers.size() || ix < 0) {
            return false;
        }
        return true;
    }

    public char fetchByRegister(int ix) {
        if (!indexLegal(ix)) {
            throw new RegistersIndexOutOfBoundsException(ix);
        }
        return registers.get(ix).getData();
    }

    public ImageIcon fetchImageIconByRegister(int ix) {
        char data = this.fetchByRegister(ix);
        return new ImageIcon(Led.parseImage(data));
    }

    public void storeByRegister(char data, int ix) {
        if (ix >= registers.size() || ix < 0) {
            throw new RegistersIndexOutOfBoundsException(ix);
        }
        registers.get(ix).setData(data);
    }
}
