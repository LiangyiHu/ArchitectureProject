package com.architecture.project.devices;

/**
 * IO bus
 * Created by larry on 10/13/2016.
 */
public class IOBus {

    //Device 0 is a Short number reader and writer.
    public static final int DEVICE_NUMBER = 0;
    //Device 1 is a character reader and writer.
    public static final int DEVICE_CHARACTER = 1;

    private String byteStream;
    private String byteOutStream;
    private int next;

    public IOBus() {
        byteStream = null;
        next = 0;
    }

    public String getByteStream() {
        return byteStream;
    }

    //This method is used for IO instructions
    public void setByteStream(String byteStream) {
        this.byteStream = byteStream;
        this.next = 0;
    }
    //This method is used for IO instructions
    public String getByteOutStream() {
        return byteOutStream;
    }
    //This method is used for IO instructions
    public char getNext(int deviceId) {
        if (deviceId == DEVICE_CHARACTER) {
            return byteStream.charAt(next++);
        } else if (deviceId == DEVICE_NUMBER) {
            StringBuffer s = new StringBuffer("");
            char c;
            while ((c = byteStream.charAt(next++)) != ' ') {
                s.append(c);
            }
            if (!s.toString().equals("")) {
                return (char) Integer.parseInt(s.toString());
            }
        }
        return Character.MAX_VALUE;
    }
    //This method is used for IO instructions
    public char ReadDeviceStatus(int deviceID) {
        char data = Character.MAX_VALUE;
        //device selections and get return value
        return data;
    }
    //This method is used for IO instructions
    public void write(int deviceID, short data) {
        //write to IO by ID goes here
        if (deviceID == DEVICE_CHARACTER)
            byteOutStream = String.valueOf((char)data);
        else if (deviceID == DEVICE_NUMBER)
            byteOutStream = String.valueOf((int)data);
    }
    //This method is used resetting and flushing.
    public void close() {
        byteStream = null;
        byteOutStream = null;
        next = 0;
    }
}
