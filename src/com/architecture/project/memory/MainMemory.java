package com.architecture.project.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Memory consists of 4096 bytes which equals 2048 words in our case, the the addressing unit is in byte, which means only even number value is valid.
 * The data type of memory in Java is char, since char in Java now is 16 bit.
 *
 * @author taoranxue on 9/14/16 10:56 PM.
 */
public final class MainMemory {
    private MainMemory() {

    }

    //The capacity of the memory, 4096 bytes equals 2048 words.
    private static final int MEMORY_CAPACITY = 2048;
    //Create memory array.
    static private List<MemorySegment> data = new ArrayList<>(MEMORY_CAPACITY);

    //Initialize the memory
    static {
        for (int i = 0; i < MEMORY_CAPACITY; ++i) {
            data.add(new MemorySegment());
        }
    }

    /**
     * @param address
     * @return The value in the address
     */
    public static char fetch(int address) {
        if (address < 0 || address >= MEMORY_CAPACITY) throw new IllegalArgumentException();
        return data.get(address / 2).getData();
    }

    /**
     * @param dt
     * @param address
     */
    public static void store(char dt, int address) {
        if (address < 0 || address >= MEMORY_CAPACITY) throw new IllegalArgumentException();
        data.get(address / 2).setData(dt);
    }

    public static void store(List<Character> dataList, int startAddress) {
        for (Character data : dataList) {
            store(data, startAddress);
            startAddress += 2;
        }
    }

    //Reset memory
    public static void resetAll() {
        for (int i = 0; i < MEMORY_CAPACITY; ++i) {
            data.get(i).setData((char) 0);
        }
    }

    public static List<MemorySegment> getData() {
        return data;
    }

    public static void setData(List<MemorySegment> data) {
        MainMemory.data = data;
    }

    //This function get the value from memory and used to display them in GUI.
    public static List<String[]> getMemoryData(int beginIndex, int endIndex) {
        endIndex = Math.min(MEMORY_CAPACITY * 2 - 1, endIndex);
        if (beginIndex < 0 || endIndex > MEMORY_CAPACITY * 2 || beginIndex > endIndex) {
            return new ArrayList<>();
        }
        List<String[]> rtn = new ArrayList<>();
        for (int i = beginIndex, j = 0; i <= endIndex; i += 2, ++j) {
            char value = MainMemory.fetch(i);
            String[] tmp = new String[3];
            tmp[0] = String.format("0x%04x", i);
            tmp[1] = String.format("0x%04x", (int) value);
            tmp[2] = String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
            rtn.add(tmp);
        }
        return rtn;
    }
}
