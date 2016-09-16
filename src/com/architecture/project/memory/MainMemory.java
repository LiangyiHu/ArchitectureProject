package com.architecture.project.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 10:56 PM.
 */
public class MainMemory {
    private static final int MEMORY_CAPACITY = 2048;
    static private List<MemorySegment> data = new ArrayList<>(MEMORY_CAPACITY);
    static {
        for (int i = 0; i < MEMORY_CAPACITY; ++ i) {
            data.add(new MemorySegment());
        }
    }

    public static char fetch(int address) {
        return data.get(address).getData();
    }

    public static void store(char dt, int address) {
        data.get(address).setData(dt);
    }

    public static List<MemorySegment> getData() {
        return data;
    }

    public static void setData(List<MemorySegment> data) {
        MainMemory.data = data;
    }
}
