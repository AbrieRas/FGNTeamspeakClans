package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    public static void main(String... args) {
        // Get clan names and create clan objects
        String pathToClans = System.getProperty("user.dir") + "/src/main/java/resources/clans.txt";

        /** getClansFromFile
         * Param: String - filepath to read from file
         * Return: Arraylist of items read from file */
        var fileReader = new FileReader();
        ArrayList<String> clanNames = fileReader.getClansFromFile(pathToClans);
        ArrayList<Clan> clanObjects = new ArrayList<>();

        // GUI settings
        var gui = new GUI(500, 500);
        var swingElements = new SwingElements();
        for (String clanName : clanNames) {
            clanObjects.add(new Clan(clanName));

            // Create elements
            String buttonText = "Change Activity";
            String checkBoxText = clanName;
            JButton button = swingElements.createSwing("button", clanName, buttonText);
            JCheckBox checkBox = swingElements.createSwing("checkbox", clanName, checkBoxText);

            // Add listeners
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    checkBox.setSelected(!checkBox.isSelected()); // Toggle the checkbox state
                }
            });

            // Add elements to GUI
            gui.addToGUI(button);
            gui.addToGUI(checkBox);
        }

        // Add space between navigation buttons and change activity buttons
        JLabel emptyLabel1 = new JLabel();
        JLabel emptyLabel2 = new JLabel();
        gui.addToGUI(emptyLabel1);
        gui.addToGUI(emptyLabel2);

        // Check all button [TOP-LEFT]
        String buttonText = "Check All";
        String buttonName = "buttonCheckAll";
        JButton button = swingElements.createSwing("button", buttonName, buttonText);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (String clanName : clanNames) {
                    // Change checked status of all checkboxes
                    JPanel guiPanel = gui.getPanel();
                    JCheckBox checkBox = (JCheckBox) swingElements.getComponentByName(guiPanel, "checkbox" + clanName);
                    checkBox.setSelected(!checkBox.isSelected());
                }
                gui.notifyUser("Successfully checked all!");
            }
        });
        gui.addToGUI(button);

        // Check all button [TOP-RIGHT]
        buttonText = "Un-Check All";
        buttonName = "buttonUnCheckAll";
        button = swingElements.createSwing("button", buttonName, buttonText);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (String clanName : clanNames) {
                    // Change checked status of all checkboxes
                    JPanel guiPanel = gui.getPanel();
                    JCheckBox checkBox = (JCheckBox) swingElements.getComponentByName(guiPanel, "checkbox" + clanName);
                    checkBox.setSelected(!checkBox.isSelected());
                }
                gui.notifyUser("Successfully un-checked all!");
            }
        });
        gui.addToGUI(button);

        // Save as json button [BOTTOM-LEFT]
        buttonText = "Export To JSON";
        buttonName = "buttonExportToJSON";
        button = swingElements.createSwing("button", buttonName, buttonText);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pathToExports = System.getProperty("user.dir") + "/src/main/java/resources/exports/";
                String fileType = "json";
                boolean wroteDataToDatabase = fileReader.storeDataInExports(pathToExports, clanObjects, fileType);
                if (wroteDataToDatabase) {
                    gui.notifyUser("Successfully exported to JSON.\n" + pathToExports);
                } else {
                    gui.notifyUser("Export to JSON failed. Please contact the developer.");
                }
            }
        });
        gui.addToGUI(button);

        // Save as csv button [BOTTOM-RIGHT]
        buttonText = "Export To CSV";
        buttonName = "buttonExportToCSV";
        button = swingElements.createSwing("button", buttonName, buttonText);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pathToExports = System.getProperty("user.dir") + "/src/main/java/resources/exports/";
                String fileType = "csv";
                boolean wroteDataToDatabase = fileReader.storeDataInExports(pathToExports, clanObjects, fileType);
                if (wroteDataToDatabase) {
                    gui.notifyUser("Successfully exported to CSV.\n" + pathToExports);
                } else {
                    gui.notifyUser("Export to CSV failed. Please contact the developer.");
                }
            }
        });
        gui.addToGUI(button);

        // Add empty space before exit button
        JLabel emptyLabel3 = new JLabel();
        gui.addToGUI(emptyLabel3);

        // Exit button [BOTTOM-RIGHT]
        buttonText = "Exit";
        buttonName = "buttonExportToJSON";
        button = swingElements.createSwing("button", buttonName, buttonText);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Terminate the application
            }
        });
        gui.addToGUI(button);

        // Refresh the panel to update the gui
        gui.refresh();

//        // Uncomment below when using export buttons
//        String pathToExports = System.getProperty("user.dir") + "/src/main/java/resources/exports/";
//        String fileType = "json";
//        boolean wroteDataToDatabase = fileReader.storeDataInExports(pathToExports, clanObjects, fileType);
//        if (wroteDataToDatabase) {
//            System.out.println("Successfully wrote to file.");
//        } else {
//            System.out.println("Writing to file failed.");
//        }
    }
}
