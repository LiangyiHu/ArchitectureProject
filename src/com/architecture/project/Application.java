package com.architecture.project;

import com.architecture.project.gui.img.Led;
import com.architecture.project.instruction.Instruction;
import com.architecture.project.instruction.Instructions;
import com.architecture.project.instruction.InstructionsFactory;
import com.architecture.project.memory.MainMemory;
import com.architecture.project.processer.registers.Register;
import com.architecture.project.processer.registers.Registers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author taoranxue on 9/15/16 12:23 AM.
 */
public class Application extends JFrame {
    private JPanel panel1;
    private JLabel GPR0;
    private JLabel GPR1;
    private JLabel GPR2;
    private JLabel GPR3;
    private JLabel IXR0;
    private JLabel IXR1;
    private JLabel IXR2;
    private JLabel PC;
    private JTextField MAR;
    private JTextField MBR;
    private JTextField MSR;
    private JTextField MFR;
    private JButton bMAR;
    private JButton bMBR;
    private JButton bMSR;
    private JButton bMFR;
    private JButton runButton;
    private JCheckBox stepCheckBox;

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
        GPR1.setIcon(Registers.generalProposeRegisters.fetchImageIconByRegister(1));
        GPR2.setIcon(Registers.generalProposeRegisters.fetchImageIconByRegister(2));
        GPR3.setIcon(Registers.generalProposeRegisters.fetchImageIconByRegister(3));
        //refresh 3 IXR
        IXR0.setIcon(Registers.indexRegisters.fetchImageIconByRegister(1));
        IXR1.setIcon(Registers.indexRegisters.fetchImageIconByRegister(2));
        IXR2.setIcon(Registers.indexRegisters.fetchImageIconByRegister(3));
        //refresh PC
        PC.setIcon(Registers.programCounter.fetchImageIconByRegister(0));
    }

    public Application() {
        setContentPane(panel1);

        Image combined = Led.parseImage(11);
        refresh();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Application();
    }

}
