package com.architecture.project.Devices;

/**
 * Created by larry on 10/13/2016.
 * 所有的IO访问都走IO bus
 */
public final class IOBus {

    public IOBus(){

    }

    //instructions will call this function to get a word from device specified by ID to registers.
    public static char ReadIO(int DevID){
        char data='a';
        //device selections and get return value
        return data;
    }

    public static char ReadDeviceStatus(int DevID){
        char data='a';
        //device selections and get return value
        return data;
    }

    public static void writeIO(int DevID, char data){
        //write to IO by ID goes here
    }
}
