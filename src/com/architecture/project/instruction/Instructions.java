package com.architecture.project.instruction;

import com.architecture.project.exception.WrongInstructionException;

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
        INSTRUCTION_MAP.put(004, "AMR");
        INSTRUCTION_MAP.put(005, "SMR");
        INSTRUCTION_MAP.put(006, "AIR");
        INSTRUCTION_MAP.put(007, "SIR");
        INSTRUCTION_MAP.put(020, "MLT");
        INSTRUCTION_MAP.put(021, "DVD");
        INSTRUCTION_MAP.put(022, "TRR");
        INSTRUCTION_MAP.put(023, "AND");
        INSTRUCTION_MAP.put(024, "ORR");
        INSTRUCTION_MAP.put(025, "NOT");
        INSTRUCTION_MAP.put(010, "JZ");
        INSTRUCTION_MAP.put(011, "JNE");
        INSTRUCTION_MAP.put(012, "JCC");
        INSTRUCTION_MAP.put(013, "JMA");
        INSTRUCTION_MAP.put(014, "JSR");
        INSTRUCTION_MAP.put(015, "RFS");
        INSTRUCTION_MAP.put(016, "SOB");
        INSTRUCTION_MAP.put(017, "JGE");
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
