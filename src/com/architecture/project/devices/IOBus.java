package com.architecture.project.devices;

/**
 * IO bus
 * Created by larry on 10/13/2016.
 */
public class IOBus {

    public static final int DEVICE_NUMBER = 0;
    public static final int DEVICE_CHARACTER = 1;

    private String byteStream;
    private int next;

    public IOBus() {
        byteStream = null;
        next = 0;
    }

    public String getByteStream() {
        return byteStream;
    }

    public void setByteStream(String byteStream) {
        this.byteStream = byteStream;
        this.next = 0;
    }


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

    public char ReadDeviceStatus(int deviceID) {
        char data = Character.MAX_VALUE;
        //device selections and get return value
        return data;
    }

    public void write(int deviceID, char data) {
        //write to IO by ID goes here
        if (deviceID == DEVICE_CHARACTER)
            byteStream = String.valueOf(data);
        else if (deviceID == DEVICE_NUMBER)
            byteStream = String.valueOf((int)data);
    }

    public void write(int deviceID, short data) {
        //write to IO by ID goes here
        if (deviceID == DEVICE_CHARACTER)
            byteStream = String.valueOf((char)data);
        else if (deviceID == DEVICE_NUMBER)
            byteStream = String.valueOf((int)data);
    }

    public void close() {
        byteStream = null;
        next = 0;
    }
}
