package com.architecture.project.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author taoranxue on 12/2/16 2:01 AM.
 */
public class FloatingTest {
    @Test
    public void testAll() {
        Floating a = new Floating(1.1);
        System.out.println(a.toDouble());
        System.out.println(Integer.toBinaryString(a.toBinary()));

//        Floating b = new Floating(0.8);
//        System.out.println(b.toDouble());
//
//        Floating res = a.add(b);
//        System.out.println(res.toDouble());
//
//        res = a.add(0.1);
//        System.out.println(res.toDouble());
//        System.out.println(res.toBinary());
    }
}