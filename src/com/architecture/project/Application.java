package com.architecture.project;

import com.architecture.project.gui.MemoryModel;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Registers;
import com.architecture.project.run.Executor;
import com.architecture.project.run.TextExecutor;

import javax.swing.*;

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
    private JButton updateMemoryRangeButton;
    //trivial
    private JPanel authorPanel;
    private JButton powerButton;
    private JTable table1;

    private void reset() {
        Registers.resetAll();
        MainMemory.resetAll();
    }


    private void refresh() {
        MemoryModel memoryModel = new MemoryModel(0, 200);
        table1.setModel(memoryModel);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);

        textFieldMAR.setText(Registers.memoryAddressRegister.getHexByOne());
        textFieldMBR.setText(Registers.memoryBufferRegister.getHexByOne());

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
        refresh();
        setSize(1215, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        runButton.addActionListener(e -> {
            boolean step = false;
            if (stepCheckBox.isSelected()) step = true;
            Executor executor = new TextExecutor(textProgramInput.getText(), loadAddressInput.getText().substring(2), step);
            executor.start();
            refresh();
        });

        loadProgramButton.addActionListener(e -> {
            boolean step = false;
            if (stepCheckBox.isSelected()) step = true;
            Executor executor = new TextExecutor(textProgramInput.getText(), loadAddressInput.getText().substring(2), step);
            executor.load();
            refresh();
        });

        setPCbutton.addActionListener(e -> {
            Registers.programCounter.setOne((char) Integer.parseInt(textFieldPC.getText().substring(2), 16));
            refresh();
        });

        powerButton.addActionListener(e -> {
                reset();
                refresh();
        });

        setMBRbutton.addActionListener(e -> {
            String MBRText = textFieldMBR.getText().substring(2);
            String MARText = textFieldMAR.getText().substring(2);
            if ((MARText != null && !MARText.equals("")) && (MBRText != null && !MBRText.equals(""))) {
                char data = (char) Integer.parseInt(MBRText, 16);
                char address = (char) Integer.parseInt(MARText, 16);
                Registers.storeMemory(data, address);
                refresh();
            }

        });

        setMARbutton.addActionListener(e -> {
            String text = textFieldMAR.getText().substring(2);
            if (text != null && !text.equals("")) {
                Registers.fetchMemory((char) Integer.parseInt(text, 16));
                refresh();
            }
        });

    }

    public static void main(String args[]) {
        new Application();
    }

}
