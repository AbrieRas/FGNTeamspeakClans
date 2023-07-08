package main;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JPanel panel;

    public void createGui(int width, int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        GridLayout layout = new GridLayout(0, 3);
        panel = new JPanel(layout);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
