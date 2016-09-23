package com.architecture.project.run;

import java.util.ArrayList;
import java.util.List;

/**
 * Executor from text input from gui.
 *
 * @author taoranxue on 9/18/16 9:42 PM.
 */
public class TextExecutor extends AbstractExecutor implements Executor {
    public TextExecutor(String text, String startAddress, boolean step) {
        List<Character> instructionList = new ArrayList<>();
        String[] instructions = text.split("\n");
        for (String instruction : instructions)
            if (!instruction.equals("")) {
                instructionList.add((char) Integer.parseInt(instruction, 2));
            }
        setInstructionList(instructionList);
        setStartAddress(Integer.parseInt(startAddress, 16));
        if (step) setStep(true);
    }

}
