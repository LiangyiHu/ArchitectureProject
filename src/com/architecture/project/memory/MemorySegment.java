package com.architecture.project.memory;

/**
 * @author taoranxue on 9/15/16 8:49 PM.
 */
public class MemorySegment {
    private Character data;

    public MemorySegment(char data) {
        this.data = data;
    }

    public MemorySegment() {
        this.data = 0;
    }


    public Character getData() {
        return data;
    }

    public void setData(Character data) {
        this.data = data;
    }
}
