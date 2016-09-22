package com.architecture.project.processer.registers;

/**
 * @author taoranxue on 9/15/16 3:35 PM.
 */
public class ProgramCounter extends AbstractRegisters {
    //PC increased by 2 bytes (1 word) each time
    public void addPC() {
        setOne((char) (getOne() + 2));
    }
}
