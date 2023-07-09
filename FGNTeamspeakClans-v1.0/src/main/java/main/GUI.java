package main;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JPanel panel;

    public GUI(int width, int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        GridLayout layout = new GridLayout(0, 2);
        panel = new JPanel(layout);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void notifyUser(String message) {
        JOptionPane.showMessageDialog(panel, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void refresh() {
        panel.revalidate();
        panel.repaint();
    }

    public void addToGUI(JButton button) {
        panel.add(button);
    }

    public void addToGUI(JCheckBox checkBox) {
        panel.add(checkBox);
    }

    public void addToGUI(JLabel label) {
        panel.add(label);
    }
}
