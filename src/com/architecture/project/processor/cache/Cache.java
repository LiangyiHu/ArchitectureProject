package com.architecture.project.processor.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * The main cache of the processor.
 *
 * @author taoranxue on 10/12/16 11:43 AM.
 */
public class Cache {
    private static final int LINE_NUM = 8;
    private List<CacheLine> cacheLines;
    // Point to the next inserting or replacing index if cache miss.
    private int cursor;

    public Cache() {
        cacheLines = new ArrayList<>(LINE_NUM);
        for (int i = 0; i < LINE_NUM; ++ i) {
            cacheLines.add(new CacheLine(Character.MAX_VALUE));
        }
        cursor = 0;
    }
    public void reset() {
        for (int i = 0; i < cacheLines.size(); ++ i) {
            cacheLines.set(i, new CacheLine(Character.MAX_VALUE));
        }
    }
    /**
     * Address hit cache or not.
     *
     * @param address address
     * @return Hit return hit line index, otherwise -1
     */
    public int isHit(int address) {
        for (int i = 0; i < Math.min(LINE_NUM, cacheLines.size()); ++i) {
            if (cacheLines.get(i).isLineMatch(address)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Fetch data from cache.
     *
     * @param address address
     * @return data
     */
    public char fetch(int address) {
        int ix;
        int offset = (address & ((1 << 3) - 1));
        if ((ix = isHit(address)) >= 0) {
            //Hit
            return cacheLines.get(ix).fetchData(offset);
        } else {
            //Miss
            CacheLine cacheLine = new CacheLine(address);
            cacheLines.set(getCursor(), cacheLine);
            return cacheLine.fetchData(offset);
        }
    }

    /**
     * Store data in cache, write through.
     *
     * @param data    data
     * @param address address
     */
    public void store(char data, int address) {
        int ix;
        int offset = (address & ((1 << 3) - 1));
        if ((ix = isHit(address)) >= 0) {
            //Hit
            cacheLines.get(ix).storeData(offset, data);

        } else {
            //Miss
            CacheLine cacheLine = new CacheLine(address);
            cacheLines.set(getCursor(), cacheLine);
            cacheLine.storeData(offset, data);
        }
    }

    public List<CacheLine> getCacheLines() {
        return cacheLines;
    }

    private int getCursor() {
        int temp = cursor;
        cursor = ((cursor + 1) % LINE_NUM);
        return temp;
    }
}
