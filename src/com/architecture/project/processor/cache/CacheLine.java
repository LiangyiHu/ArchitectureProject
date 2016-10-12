package com.architecture.project.processor.cache;

import com.architecture.project.memory.MainMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * For the cache line, we design that it has 4 blocks that save one word (16 bits) each.
 * So the memory address should have 3 bits for offset and 13 bits for tags.
 *
 * @author taoranxue on 10/12/16 11:31 AM.
 */
public class CacheLine {
    private static final int BLOCK_NUM = 4;
    private boolean valid;
    private int tag;
    private List<Character> blocks;

    /**
     * Construct cache line by address, valid is true
     *
     * @param address memory address
     */
    public CacheLine(int address) {
        this(true, address);
    }

    /**
     * Construct cache line by address and valid
     *
     * @param valid   true or false
     * @param address memory address
     */
    public CacheLine(boolean valid, int address) {
        this.valid = valid;
        this.tag = (address >> 3);
        blocks = new ArrayList<>(BLOCK_NUM);
        int curAddress = address;
        for (int i = 0; i < BLOCK_NUM; ++i) {
            blocks.add(MainMemory.fetch(curAddress));
            curAddress += 2;
        }
    }

    /**
     * Fetch data from cache, if the address is hit.
     *
     * @param offset offset
     * @return data
     * @see MainMemory where we desigh memory segment store half word, so the offset should
     * be divided by 2.
     */
    public char fetchData(int offset) {
        offset /= 2;
        if (offset < 0 || offset >= BLOCK_NUM) throw new IllegalArgumentException();
        return blocks.get(offset);
    }

    /**
     * Store data to cache, where we use write-through policy here. It means when changing the data
     * in the cache we should change the data in the memory.
     *
     * @param offset offset
     * @param data   data
     * @see MainMemory
     * where we design memory segment store half word, so the offset should
     * be divided by 2.
     */
    public void storeData(int offset, char data) {
        int address = (tag << 3) + offset;
        offset /= 2;
        if (offset < 0 || offset >= BLOCK_NUM) throw new IllegalArgumentException();
        MainMemory.store(data, address);
    }

    /**
     * Line matching with address.
     *
     * @param address address
     * @return Match return true, otherwise false
     */
    public boolean isLineMatch(int address) {
        int tag = (address >> 3);
        return this.valid && this.tag == tag;
    }
}
