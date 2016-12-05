package com.architecture.project;

import com.architecture.project.gui.CacheModel;
import com.architecture.project.gui.MemoryModel;
import com.architecture.project.instruction.Instructions;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processor.Processor;
import com.architecture.project.processor.registers.Registers;
import com.architecture.project.run.Executor;
import com.architecture.project.run.TextExecutor;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The project run from here.
 *
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
    private JLabel MAR;
    private JLabel MBR;

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
    private JTable table2;
    public JTextArea debugPanel;
    private JTextField keyBoardInput;
    private JButton keyBoardEnter;
    private JTextField consoleOutput;
    private JCheckBox pipeCheckBox;
    private JComboBox comboBox1;
    private JLabel FR0;
    private JLabel FR1;

    private int memBeginIndex = 0;
    private final int memDisplayLength = 400;

    private void reset() {
        Processor.reset();
        Registers.resetAll();
        MainMemory.resetAll();
        refresh();
    }

    /**
     * Refresh the GUI, display all the things it supposed to show.
     */
    private void refresh() {
        //IOBus
        consoleOutput.setText(Processor.ioBus.getByteOutStream());
        //Debug
        debugPanel.setText(Instructions.debugInfo);
        //Refresh memory table
        MemoryModel memoryModel = new MemoryModel(memBeginIndex, memBeginIndex + memDisplayLength);
        table1.setModel(memoryModel);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);

        //Add file
        Scanner in = null;
        try {
            URL url = getClass().getResource("InputFile.txt");
            File file = Paths.get(url.toURI()).toFile();
//            File file = new File("./InputFile.txt");
            in = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = "";
        while (in.hasNextLine()) {
            str += in.nextLine();
        }
//        System.out.println(str);
        Processor.ioBus.setFileStream(str);

        //Refresh cache table
        CacheModel cacheModel = new CacheModel();
        table2.setModel(cacheModel);
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
        //refresh 2 FR
        FR0.setIcon(Registers.floatingPointRegisters.fetchImageIconByRegister(0));
        FR0.setText(Registers.floatingPointRegisters.fetchHexByRegister(0));
        FR1.setIcon(Registers.floatingPointRegisters.fetchImageIconByRegister(1));
        FR1.setText(Registers.floatingPointRegisters.fetchHexByRegister(1));
        //refresh 3 IXR
        IXR0.setIcon(Registers.indexRegisters.fetchImageIconByRegister(1));
        IXR0.setText(Registers.indexRegisters.fetchHexByRegister(1));
        IXR1.setIcon(Registers.indexRegisters.fetchImageIconByRegister(2));
        IXR1.setText(Registers.indexRegisters.fetchHexByRegister(2));
        IXR2.setIcon(Registers.indexRegisters.fetchImageIconByRegister(3));
        IXR2.setText(Registers.indexRegisters.fetchHexByRegister(3));
        //refresh PC, IR, MSR, MFR and CC
        PC.setIcon(Registers.programCounter.fetchImageIconByRegister(0,12));
        PC.setText(Registers.programCounter.fetchHexByRegister(0));
        IR.setIcon(Registers.instructionRegister.fetchImageIconByRegister(0));
        IR.setText(Registers.instructionRegister.fetchHexByRegister(0));
        MSR.setIcon(Registers.machineStatusRegister.fetchImageIconByRegister(0));
        MSR.setText(Registers.machineStatusRegister.fetchHexByRegister(0));
        MFR.setIcon(Registers.machineFaultRegister.fetchImageIconByRegister(0, 4));
        MFR.setText(Registers.machineFaultRegister.fetchHexByRegister(0));
        CC.setIcon(Registers.conditionCodeRegister.fetchImageIconByRegister(0, 4));
        CC.setText(Registers.conditionCodeRegister.fetchHexByRegister(0));
        MAR.setIcon(Registers.memoryAddressRegister.fetchImageIconByRegister(0));
        MAR.setText(Registers.memoryAddressRegister.fetchHexByRegister(0));
        MBR.setIcon(Registers.memoryBufferRegister.fetchImageIconByRegister(0));
        MBR.setText(Registers.memoryBufferRegister.fetchHexByRegister(0));
    }

    /**
     * Following code builds up the GUI for user to operate
     */
    public Application() {
        //Basic GUI properties setup
        setContentPane(mainPanel);
        refresh();
        setSize(1215, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Project--Simulated CPU for CS6461");


        //Decide what happens if "RUN" button is been clicked
        runButton.addActionListener(e -> {
            boolean step = false;
            boolean pipe = false;
            if (stepCheckBox.isSelected()) step = true;
            if (pipeCheckBox.isSelected()) pipe = true;
            Executor executor = new TextExecutor(textProgramInput.getText(), loadAddressInput.getText().substring(2), step, pipe);
            executor.start();
            refresh();
        });

        //Load the program to memory
        loadProgramButton.addActionListener(e -> {
            boolean step = false;
            boolean pipe = false;
            if (stepCheckBox.isSelected()) step = true;
            if (pipeCheckBox.isSelected()) pipe = true;
            Executor executor = new TextExecutor(textProgramInput.getText(), loadAddressInput.getText().substring(2), step, pipe);
            executor.load();
            refresh();
        });

        //Build function managing SetPC button
        setPCbutton.addActionListener(e -> {
            Registers.programCounter.setOne((char) Integer.parseInt(textFieldPC.getText().substring(2), 16));
            Processor.pipeProcessor.setPipelinePC(Integer.parseInt(textFieldPC.getText().substring(2), 16));
            refresh();
        });

        //Reset the machine
        powerButton.addActionListener(e -> {
            reset();
        });

        //Set MBR value
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

        //Load Memory to MBR
        setMARbutton.addActionListener(e -> {
            String text = textFieldMAR.getText().substring(2);
            if (text != null && !text.equals("")) {
                Registers.fetchMemory((char) Integer.parseInt(text, 16));
                refresh();
            }
        });

        updateMemoryRangeButton.addActionListener(e -> {
            memBeginIndex = Integer.parseInt(textMemoryStartField.getText().substring(2), 16);
            refresh();
        });

        keyBoardEnter.addActionListener(e -> {
            //once hit enter, the strings on keyboard input textfield will be separated by space.
            //then converted to short type (negative number supported).
            //then append to an arrayList in keyBoardInput object.
            //IOBus will access this arrayList and return a short to Register.
            Processor.ioBus.setByteStream(keyBoardInput.getText());
        });

    }

    //Start the GUI
    public static void main(String args[]) {
        new Application();
    }

}
