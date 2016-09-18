package com.architecture.project.instruction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author taoranxue on 9/18/16 12:04 AM.
 */
public abstract class Instructions {
    protected final Map<Integer, String> INSTRUCTION_MAP = new HashMap<>();
    private int operatorCode;
    public abstract void execute();

    public int getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(int operatorCode) {
        this.operatorCode = operatorCode;
    }
}
