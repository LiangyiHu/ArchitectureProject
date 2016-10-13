package com.architecture.project.instruction;

import com.architecture.project.exception.WrongInstructionException;
import com.architecture.project.processor.registers.Registers;

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
        INSTRUCTION_MAP.put(031, "SRC");
        INSTRUCTION_MAP.put(032, "RRC");
        INSTRUCTION_MAP.put(061, "IN");
        INSTRUCTION_MAP.put(062, "OUT");
        INSTRUCTION_MAP.put(063, "CHK");

        INSTRUCTION_MAP.put(070, "LLD");
        INSTRUCTION_MAP.put(071, "LLD");
        INSTRUCTION_MAP.put(072, "LLD");
        INSTRUCTION_MAP.put(073, "LLD");
        INSTRUCTION_MAP.put(074, "LST");
        INSTRUCTION_MAP.put(075, "LST");
        INSTRUCTION_MAP.put(076, "LST");
        INSTRUCTION_MAP.put(077, "LST");
    }

    public static String debugInfo;

    public static void setFlowConditionCode(int value){
        if(value>32767){
            //over flow
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(0, true);
        }
        else if(value<-32768){
            //under flow
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(1, true);
        }
        else{
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(0, false);
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(1, false);
        }
    }

    public static void setDivZeroConditionCode(int value){
        if(value==0){
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(2, true);
        }
        else{Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(2, false);}
    }

    public static void setEqualOrNotConditionCode(int value1, int value2){
        if(value1==value2){
            Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(3, true);
        }
        else{Registers.conditionCodeRegister.getRegisterByIndex(0).setBitByIndex(3, false);}
    }

    /**
     * Instruction operator code map to its name.
     */
    protected final Map<Integer, String> INSTRUCTION_MAP = new HashMap<>();
    private int operatorCode;

    /**
     * execute the instruction.
     */
    public final void execute() {
        String operateCode = INSTRUCTION_MAP.get(getOperatorCode());
        if (operateCode == null || operateCode.equals("")) {
            throw new WrongInstructionException();
        }
        String methodName = "execute" + operateCode;
        debugInfo+="Executed instruction: ";
        debugInfo+=operateCode;
        debugInfo+="\n";
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
