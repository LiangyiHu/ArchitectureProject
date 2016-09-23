package com.architecture.project.instruction;

import java.util.HashMap;
import java.util.Map;

/**
 * Instruction service provide class. Root class of all instruction service class.
 *
 * @author taoranxue on 9/18/16 12:04 AM.
 */
public abstract class Instructions {
    /**
     * Instruction operator code map to its name.
     */
    protected final Map<Integer, String> INSTRUCTION_MAP = new HashMap<>();
    private int operatorCode;

    /**
     * execute the instruction.
     */
    public abstract void execute();

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
