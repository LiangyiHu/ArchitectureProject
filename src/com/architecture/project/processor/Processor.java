package com.architecture.project.processor;

import com.architecture.project.devices.IOBus;
import com.architecture.project.processor.cache.Cache;

/**
 * @author taoranxue on 10/12/16 3:12 PM.
 */
public final class Processor {
    private Processor() {

    }

    public static void reset() {
        ioBus.close();
        cache.reset();
    }
    //Initiate cache
    public static final Cache cache = new Cache();

    //IO Bus
    public static final IOBus ioBus = new IOBus();


}
