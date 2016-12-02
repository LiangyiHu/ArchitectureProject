package com.architecture.project.utils;

import com.architecture.project.processor.registers.Register;

/**
 * @author taoranxue on 12/2/16 12:56 AM.
 */
public class Floating {
    private int sign;
    private int exponent;
    private int mantissa;
    private double result;

    public Floating(char data) {
        Register register = new Register(data);
        this.sign = register.subRegister(0, 1).parseInt();
        this.exponent = register.subRegister(1, 8).parseInt();
        this.mantissa = register.subRegister(8, 16).parseInt();
        double s = (sign == 1) ? -1.0 : 1.0;
        this.result = s * mantissa * Math.pow(2.0, exponent - 63.0);
    }

    public Floating(double data) {
        int integer = (int) data;
        double decimal = data - (double) integer;
        String intBits = Integer.toBinaryString(integer);
        String decBits = "";
        int k = 0;
        while (decimal > 0.0 && (++k) < 9) {
            if (decimal * 2 > 1) {
                decimal = decimal * 2 - 1;
                decBits += "1";
            } else {
                decimal = decimal * 2;
                decBits += "0";
            }
        }
        int exponent = 0;
        String mantissaStr = "";
        if (intBits.length() + decBits.length() > 8) {
            int offset = 8 - (intBits.length() + decBits.length());
            if (intBits.length() < 8) {
                exponent = -decBits.length();
                decBits = decBits.substring(0, decBits.length() - offset);
                mantissaStr = intBits + decBits;
            } else {
                exponent = intBits.length() - 8;
                mantissaStr = intBits.substring(0, 8);
            }
        }
        int sign = 0;
        if (data < 0.0) sign = 1;
        int mantissa = Integer.parseInt(mantissaStr);
        this.mantissa = mantissa;
        this.exponent = exponent;
        this.sign = sign;
        this.result = data;
    }

    public char toBinary() {
        String binaryStr = Integer.toBinaryString(sign) + Integer.toBinaryString(exponent) + Integer.toBinaryString(mantissa);
        return (char) Integer.parseInt(binaryStr);
    }

    public double toDouble() {
        return result;
    }

    public Floating add(Floating other) {
        double d = other.toDouble();
        double res = this.result + d;
        return new Floating(res);
    }

    public Floating add(double other) {
        return null;
    }

    public Floating sub(Floating other) {
        double d = other.toDouble();
        double res = this.result - d;
        return new Floating(res);
    }
}
