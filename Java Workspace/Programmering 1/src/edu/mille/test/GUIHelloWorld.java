package edu.mille.test;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIHelloWorld {
    private JButton button1;
    private JPanel panel1;

    public GUIHelloWorld() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Hello World!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("TestGui");
        window.setContentPane(new GUIHelloWorld().panel1);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
        window.setSize(1280, 720);
    }
}
