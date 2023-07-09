package main;

import javax.swing.*;

public class Button {
    private String name;
    private String text;
    private JButton button;

    public Button(JButton button) {
        this.button = button;
        this.name = button.getName();
        this.text = button.getText();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }
}
