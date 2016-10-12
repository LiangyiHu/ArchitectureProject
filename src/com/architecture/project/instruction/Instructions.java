package com.architecture.project.instruction;

import com.architecture.project.exception.WrongInstructionException;
import com.architecture.project.processer.registers.Registers;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Instruction service provide class. Root class of all instruction service class.
 *
 * @author taoranxue on 9/18/16 12:04 AM.
 */
public abstract class Instructions {

    public Instructions() {
        // Operation Code, used to connect functions by the Uppercase string value.
        INSTRUCTION_MAP.put(001, "LDR");
        INSTRUCTION_MAP.put(002, "STR");
        INSTRUCTION_MAP.put(003, "LDA");
        INSTRUCTION_MAP.put(041, "LDX");
        INSTRUCTION_MAP.put(042, "STX");
    }
    /**
     * Instruction operator code map to its name.
     */
    protected final Map<Integer, String> INSTRUCTION_MAP = new HashMap<>();
    private int operatorCode;

    /**
     * execute the instruction.
     */
    public void execute() {
        String operateCode = INSTRUCTION_MAP.get(getOperatorCode());
        if (operateCode == null || operateCode.equals("")) {
            throw new WrongInstructionException();
        }
        String methodName = "execute" + operateCode;
        System.out.println(methodName);
        try {
            Method executeMethod = getClass().getDeclaredMethod(methodName);
            executeMethod.setAccessible(true);
            executeMethod.invoke(this);
            Registers.programCounter.addPC();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

//    public abstract void execute();

    /**
     * Get the operator code of the instruction.
     *
     * @return operator code.
     */
    public int getOperatorCode() {
        return operatorCode;
    }

    /**
     * Set the operator code of the instruction.
     *
     * @param operatorCode operator code.
     */
    public void setOperatorCode(int operatorCode) {
        this.operatorCode = operatorCode;
    }
}
