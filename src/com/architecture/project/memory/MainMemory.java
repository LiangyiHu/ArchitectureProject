package com.architecture.project.memory;

import com.architecture.project.exception.MemoryAddressingOutOfBoundsException;
import com.architecture.project.exception.RegisterIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 10:56 PM.
 */
public class MainMemory {
    private static final int MEMORY_CAPACITY = 2048;
    static private List<MemorySegment> data = new ArrayList<>(MEMORY_CAPACITY);

    static {
        for (int i = 0; i < MEMORY_CAPACITY; ++i) {
            data.add(new MemorySegment());
        }
    }

    public static char fetch(int address) {
        return data.get(address / 2).getData();
    }

    public static void store(char dt, int address) {
        data.get(address / 2).setData(dt);
    }

    public static List<MemorySegment> getData() {
        return data;
    }

    public static void setData(List<MemorySegment> data) {
        MainMemory.data = data;
    }

    public static void printMemory(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > MEMORY_CAPACITY * 2 || beginIndex > endIndex) {
            throw new MemoryAddressingOutOfBoundsException(beginIndex);
        }
        for (int i = beginIndex; i <= endIndex; i += 2) {
            char value = MainMemory.fetch(i);
            String b = String.format("%16s", Integer.toBinaryString(value)).replace(' ', '0');
            String h = String.format("%04x", (int) value);
            String d = String.format("%05d", (int) value);
            System.out.println("Address: " + String.format("%04x", i) + "  BValue: " + b + "  HValue: " + h + "  DValue:" + d);
        }
    }
}
