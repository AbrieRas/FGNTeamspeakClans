package main;

import javax.swing.*;
import java.awt.*;

public class SwingElements {
    public <T extends JComponent> T createSwing(String elementType, String elementName, String elementText) {
        switch(elementType) {
            case "button":
                JButton button = new JButton(elementText);
                button.setName("button" + elementName);
                button.setSize(100, 50);
                return (T) button;
            case "checkbox":
                JCheckBox checkBox = new JCheckBox(elementText);
                checkBox.setName("checkbox" + elementName);
                checkBox.setSelected(false);
                checkBox.setEnabled(false);
                return (T) checkBox;
        }

        System.out.println("Could not create swing element (createSwing).");
        return null;
    }

    public Component getComponentByName(Container container, String name) {
        Component[] components = container.getComponents();

        for (Component component : components) {
            if (component.getName() != null && component.getName().equals(name)) {
                return component;
            }
        }

        System.out.println("Could not find component (getComponentByName).");
        return null;
    }
}
