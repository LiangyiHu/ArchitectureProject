package com.architecture.project.processer.registers;

/**
 * @author taoranxue on 9/14/16 11:28 PM.
 */
public class Register {
    private Integer data;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public int getBitByIndex(int index) {
        int _data = data;
        int _tmp = (1 << index);
        if ((_data & _tmp) > 0) {
            return 1;
        }
        return 0;
    }

    public void setBitByIndex(int index, int bit) {
        if (bit == 1) {
            int _tmp = (1 << index);
            setData(_tmp | getData());
        } else {
            int _tmp = ~(1 << index);
            setData(_tmp & getData());
        }
    }
}
