package com.architecture.project.gui;

import com.architecture.project.processor.registers.GeneralProposeRegisters;
import com.architecture.project.processor.registers.IndexRegisters;
import com.architecture.project.processor.registers.Register;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author taoranxue on 10/5/16 7:37 PM.
 */
public class MemoryModelTest {
    @Test
    public void mainTest() throws Exception {
        Class c = Register.class;
        IndexRegisters ix = new IndexRegisters();
        System.out.println(ix.getClass() == c);
    }

}