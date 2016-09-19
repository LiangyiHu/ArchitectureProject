package com.architecture.project;

import com.architecture.project.gui.img.Led;
import com.architecture.project.instruction.Instruction;
import com.architecture.project.instruction.Instructions;
import com.architecture.project.instruction.InstructionsFactory;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;

import javax.swing.*;
import java.awt.*;

/**
 * @author taoranxue on 9/15/16 12:23 AM.
 */
public class Application extends JFrame {
    private JPanel mainPanel;
    //define register dashboard panel
    private JPanel registerDashboard;
    private JLabel GPR0;
    private JLabel GPR1;
    private JLabel GPR2;
    private JLabel GPR3;
    private JLabel IXR0;
    private JLabel IXR1;
    private JLabel IXR2;
    private JLabel PC;
    private JLabel IR;
    private JLabel MSR;
    private JLabel MFR;
    private JLabel CC;
    //define MAR MBR, setPC panel
    private JPanel operatingPanel;
    private JTextField textFieldMAR;
    private JTextField textFieldMBR;
    private JTextField textFieldPC;
    private JButton setMARbutton;
    private JButton setMBRbutton;
    private JButton setPCbutton;
    //run and step panel
    private JButton runButton;
    private JCheckBox stepCheckBox;
    //program loader and editor panel
    private JPanel inputProgramPanel;
    private JButton loadProgramButton;
    private JTextField loadAddressInput;
    private JTextArea textProgramInput;
    //Memory table panel
    private JPanel memoryStatusPanel;
    private JTextField textMemoryStartField;
    private JTable memoryStatusTable;
    private JButton updateMemoryRangeButton;
    //trivial
    private JPanel authorPanel;
    private JButton powerButton;
    private JLabel powerLabel;


    private void refresh() {
        Instruction instruction = new Instruction((char)(0b0000011100100110));
        InstructionsFactory instructionsFactory = new InstructionsFactory(instruction);
        Registers.indexRegisters.storeByRegister((char)10,3);
        MainMemory.store((char)2, 6);
        MainMemory.store((char)22, 2);
        MainMemory.store((char)5000, 12);
        MainMemory.store((char)12, 16);
        Instructions instructions = instructionsFactory.getInstructions();
        instructions.execute();
        //refresh 4 GPR
        GPR0.setIcon(Registers.generalProposeRegisters.fetchImageIconByRegister(0));
        GPR0.setText(Registers.generalProposeRegisters.fetchHexByRegister(0));
        GPR1.setIcon(Registers.generalProposeRegisters.fetchImageIconByRegister(1));
        GPR1.setText(Registers.generalProposeRegisters.fetchHexByRegister(1));
        GPR2.setIcon(Registers.generalProposeRegisters.fetchImageIconByRegister(2));
        GPR2.setText(Registers.generalProposeRegisters.fetchHexByRegister(2));
        GPR3.setIcon(Registers.generalProposeRegisters.fetchImageIconByRegister(3));
        GPR3.setText(Registers.generalProposeRegisters.fetchHexByRegister(3));
        //refresh 3 IXR
        IXR0.setIcon(Registers.indexRegisters.fetchImageIconByRegister(1));
        IXR0.setText(Registers.indexRegisters.fetchHexByRegister(1));
        IXR1.setIcon(Registers.indexRegisters.fetchImageIconByRegister(2));
        IXR1.setText(Registers.indexRegisters.fetchHexByRegister(2));
        IXR2.setIcon(Registers.indexRegisters.fetchImageIconByRegister(3));
        IXR2.setText(Registers.indexRegisters.fetchHexByRegister(3));
        //refresh PC
        PC.setIcon(Registers.programCounter.fetchImageIconByRegister(0));
        PC.setText(Registers.programCounter.fetchHexByRegister(0));
        IR.setIcon(Registers.instructionRegister.fetchImageIconByRegister(0));
        IR.setText(Registers.instructionRegister.fetchHexByRegister(0));
        MSR.setIcon(Registers.machineStatusRegister.fetchImageIconByRegister(0));
        MSR.setText(Registers.machineStatusRegister.fetchHexByRegister(0));
        MFR.setIcon(Registers.machineFaultRegister.fetchImageIconByRegister(0));
        MFR.setText(Registers.machineFaultRegister.fetchHexByRegister(0));
        CC.setIcon(Registers.conditionCodeRegister.fetchImageIconByRegister(0));
        CC.setText(Registers.conditionCodeRegister.fetchHexByRegister(0));
    }

    public Application() {
        setContentPane(mainPanel);

        Image combined = Led.parseImage(11);
        refresh();
        setSize(1000, 450);
        //setResizable(false);
        setLocationRelativeTo(null);
//        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Application();
    }

}
