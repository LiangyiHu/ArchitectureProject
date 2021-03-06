package com.architecture.project.processor.registers;

import com.architecture.project.exception.RegistersIndexOutOfBoundsException;
import com.architecture.project.gui.img.Led;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstracted registers, this is a general parent class for all the registers.
 *
 * @author taoranxue on 9/14/16 11:42 PM.
 */
public abstract class AbstractRegisters {
    private static final int DEFAULT_NUM = 1;
    protected List<Register> registers;

    //Provide reset method for all registers.
    public void reset() {
        for (int i = 0; i < length(); ++i) {
            registers.get(i).setData((char) 0);
        }
    }

    //Create register array based on its type
    public AbstractRegisters() {
        registers = new ArrayList<>(length());
        for (int i = 0; i < length(); ++i) {
            registers.add(new Register());
        }
    }

    //Return its length
    public int length() {
        return DEFAULT_NUM;
    }

    //self-check if indexing is legal
    public boolean indexLegal(int ix) {
        return !(ix >= registers.size() || ix < 0);
    }

    //The function to access a particular register
    public char fetchByRegister(int ix) {
        if (!indexLegal(ix)) {
            throw new RegistersIndexOutOfBoundsException(ix);
        }
        return registers.get(ix).getData();
    }

    //Return hex value of a register, most used for displaying purpose
    public String fetchHexByRegister(int ix) {
        char data = this.fetchByRegister(ix);
        return String.format("0x%04X", (int) data);
    }

    //Get image for GUI display, here it is light bulb in red and gray.
    public ImageIcon fetchImageIconByRegister(int ix) {
        char data = this.fetchByRegister(ix);
        return new ImageIcon(Led.parseImage(data));
    }

    public ImageIcon fetchImageIconByRegister(int ix, int number) {
        char data = this.fetchByRegister(ix);
        return new ImageIcon(Led.parseImage(data, number));
    }


    /**
     * Send value to a particular register
     * NOTE: For IndexRegisters in this method ix = 0 is legal, the gui however doesn't display the zero element
     * , and it starts with index 1;
     *
     * @param data data
     * @param ix   register index
     */
    public void storeByRegister(char data, int ix) {
        if (ix >= registers.size() || ix < 0) {
            throw new RegistersIndexOutOfBoundsException(ix);
        }
        registers.get(ix).setData(data);
    }

    //For registers with only one member...
    public void setOne(char data) {
        storeByRegister(data, 0);
    }

    public char getOne() {
        return fetchByRegister(0);
    }

    public String getHexByOne() {
        char data = this.getOne();
        return String.format("0x%04X", (int) data);
    }

    public Register getRegisterByIndex(int index) {
        return registers.get(index);
    }
}
