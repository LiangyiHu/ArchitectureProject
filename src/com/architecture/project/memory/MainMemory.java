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

    public static void store(List<Character> dataList, int startAddress) {
        for (Character data : dataList) {
            store(data, startAddress);
            startAddress += 2;
        }
    }

    public static List<MemorySegment> getData() {
        return data;
    }

    public static void setData(List<MemorySegment> data) {
        MainMemory.data = data;
    }

    public static List<String[]> getMemoryData(int beginIndex, int endIndex) {
        if (beginIndex < 0 || endIndex > MEMORY_CAPACITY * 2 || beginIndex > endIndex) {
            throw new MemoryAddressingOutOfBoundsException(beginIndex);
        }
        List<String[]> rtn = new ArrayList<>();
        for (int i = beginIndex, j = 0; i <= endIndex; i += 2, ++ j) {
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
