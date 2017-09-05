package edu.mille.project.YatzyGUI;

import javax.swing.*;

public class YatzyGUI {
    private JPanel mainPanel;
    private JLabel topText;
    private JLabel systemInfo;
    private JPanel topPanel;
    private JTable table1;
    private static JFrame frame;

    public YatzyGUI()
    {
        topText.setText("Yatzy Emulator 6000");
        systemInfo.setText(System.getProperty("os.name") +", "+System.getProperty("user.name"));
    }

    public static void main(String[] args) {

        frame = new JFrame("TestGui");
        frame.setContentPane(new YatzyGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1280, 720);
        YatzyGUI gui = new YatzyGUI();
    }
}
