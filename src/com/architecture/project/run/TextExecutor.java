package com.architecture.project.run;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoranxue on 9/18/16 9:42 PM.
 */
public class TextExecutor extends AbstractExecutor implements Executor {
    public TextExecutor(String text, int startAddress) {
        List<Character> instructionList = new ArrayList<>();
        String [] instructions = text.split("\n");
        for (String instruction : instructions) if (!instruction.equals("")) {
            instructionList.add((char) Integer.parseInt(instruction, 2));
        }
        setInstructionList(instructionList);
        setStartAddress(startAddress);
    }

}
