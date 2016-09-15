package com.architecture.project;

import javax.swing.*;

/**
 * @author taoranxue on 9/15/16 12:23 AM.
 */
public class Application extends JFrame{
    private JPanel rootPanel;
    private JButton button1;
    private JPanel panel1;
    private JButton button2;

    public Application() {
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String args[]) {
        new Application();

    }
}
