package com.architecture.project.processor.registers;

import com.architecture.project.exception.RegisterIndexOutOfBoundsException;

/**
 * Register index begin from left!
 *
 * @author taoranxue on 9/14/16 11:28 PM.
 */
public class Register {
    protected static final int WORD_LENGTH = 16;
    protected Character data;

    /**
     * Construct with string.
     *
     * @param s string.
     */
    public Register(String s) {
        this(s, 10);
    }

    /**
     * Construct with based number.
     *
     * @param s    number string.
     * @param base base number.
     */
    public Register(String s, int base) {
        this.data = (char) Integer.parseInt(s, base);
    }

    public Register() {
        this.data = 0;
    }

    public Register(char data) {
        this.data = data;
    }

    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }


    /**
     * get Integer data
     *
     * @return int
     */
    public int parseInt() {
        return (int) data;
    }

    /**
     * Convert to binary string.
     *
     * @return binary String
     */
    public String toString() {
        String _s = Integer.toBinaryString(data);
        if (_s.length() < WORD_LENGTH) {
            int count = WORD_LENGTH - _s.length();
            String s = "";
            for (int i = 0; i < count; ++i) {
                s += "0";
            }
            return s += _s;
        }
        return _s;
    }

    /**
     * Get data's index-th binary bit.
     *
     * @param index index
     * @return one or zero
     */
    public int getBitByIndex(int index) {
        if (index > WORD_LENGTH || index < 0) {
            throw new RegisterIndexOutOfBoundsException(index);
        }
        int _data = data;
        int _tmp = (1 << index);
        if ((_data & _tmp) > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Set data's index-th binary bit to be one or zero.
     *
     * @param index index
     * @param bit   set value, one or zero
     */
    public void setBitByIndex(int index, boolean bit) {
        if (index > WORD_LENGTH || index < 0) {
            throw new RegisterIndexOutOfBoundsException(index);
        }
        if (bit) {
            char _tmp = (char) (1 << index);
            setData((char) (_tmp | getData()));
        } else {
            int _tmp = ~(1 << index);
            setData((char) (_tmp & getData()));
        }
    }

    /**
     * Get the sub-data of register data.
     *
     * @param beginIndex begin index.
     * @param endIndex   end index.
     * @return sub-data of register data.
     */
    public Register subRegister(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new RegisterIndexOutOfBoundsException(beginIndex);
        }
        if (endIndex > WORD_LENGTH) {
            throw new RegisterIndexOutOfBoundsException(endIndex);
        }
        int subLen = endIndex - beginIndex;
        if (subLen < 0) {
            throw new RegisterIndexOutOfBoundsException(subLen);
        }

        char _data = this.data;
        _data = (char) (_data << (beginIndex));
        _data = (char) (_data >> (beginIndex + WORD_LENGTH - endIndex));

        return new Register(_data);
    }
}
