package com.architecture.project.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/14/16 10:56 PM.
 */
public class MainMemory {
    private static final int MEMORY_CAPACITY = 2048;
    static private List<Character> data = new ArrayList<>(MEMORY_CAPACITY);

    public static char fetch(char address) {
        return data.get(address);
    }


}
