package edu.mille.project.mastermind;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MastermindGUI extends JFrame
{
    public  JFrame frame;
    private JPanel mainPanel;
    private JPanel colorPanel;

    public MastermindGUI()
    {
        mainPanel.setBorder (new TitledBorder( new EtchedBorder(), "Mastermind" ) );
        SetupFrame();
    }

    public void SetupFrame()
    {
        frame = new JFrame("Mastermind");
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        frame.setSize(width/2, height);
        colorPanel.setBackground(Color.BLUE);
    }
}
