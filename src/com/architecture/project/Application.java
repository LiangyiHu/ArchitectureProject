package com.architecture.project;

import com.architecture.project.gui.img.Led;

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

    public Application() {
        setContentPane(panel1);

        Image combined = Led.parseImage(11);

        GPR0.setIcon(new ImageIcon(combined));
        GPR1.setIcon(new ImageIcon(combined));
        GPR2.setIcon(new ImageIcon(combined));
        GPR3.setIcon(new ImageIcon(combined));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String args[]) {
        new Application();
    }

}
