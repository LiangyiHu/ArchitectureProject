package com.architecture.project.memory;

/**
 * Memory data entity.
 *
 * @author taoranxue on 9/15/16 8:49 PM.
 */
public class MemorySegment {
    private Character data;

    /**
     * Construct with char data.
     *
     * @param data data.
     */
    public MemorySegment(char data) {
        this.data = data;
    }

    /**
     * Default constructor initialized data equaling zero.
     */
    public MemorySegment() {
        this.data = 0;
    }

    /**
     * Get the data.
     *
     * @return data.
     */
    public Character getData() {
        return data;
    }

    /**
     * Set the data.
     *
     * @param data data.
     */
    public void setData(Character data) {
        this.data = data;
    }
}
