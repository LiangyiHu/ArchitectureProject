package com.architecture.project.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author taoranxue on 12/2/16 2:01 AM.
 */
public class FloatingTest {
    @Test
    public void testAll() {
        Floating a = new Floating((char) Integer.parseInt(String.valueOf("0011111000001101"), 2));
        //0011111000001011
        System.out.println(a.toDouble());
    }
}