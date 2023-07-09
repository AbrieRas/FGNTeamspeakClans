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

        // Add footer buttons & functionality below

        /**
         * Button class
         * Param1 - String - Name of the button
         * Param2 - String - Text of the button
         * Param3 - JButton - Button object
         */

        JPanel container = gui.getPanel();

        // Un-check all button [TOP-LEFT]
        var unCheckAllButton = new Button(
                swingElements.createSwing(
                        "button",
                        "buttonUnCheckAll",
                        "Un-check All"
                )
        );
        unCheckAllButton.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (String clanName : clanNames) {
                    // Change checked status of all checkboxes
                    String checkBoxName = "checkbox" + clanName;
                    JCheckBox checkBox = (JCheckBox) swingElements.getComponentByName(container, checkBoxName);
                    checkBox.setSelected(false);
                }
                gui.notifyUser("Successfully un-checked all!");
            }
        });
        gui.addToGUI(unCheckAllButton.getButton());

        // Check all button [TOP-RIGHT]
        var checkAllButton = new Button(
                swingElements.createSwing(
                        "button",
                        "buttonCheckAll",
                        "Check All"
                )
        );
        checkAllButton.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (String clanName : clanNames) {
                    // Change checked status of all checkboxes
                    String checkBoxName = "checkbox" + clanName;
                    JCheckBox checkBox = (JCheckBox) swingElements.getComponentByName(container, checkBoxName);
                    checkBox.setSelected(true);
                }
                gui.notifyUser("Successfully checked all!");
            }
        });
        gui.addToGUI(checkAllButton.getButton());

        // Save as json button [BOTTOM-LEFT]
        var saveJsonButton = new Button(
                swingElements.createSwing(
                        "button",
                        "buttonExportToJSON",
                        "Export To JSON"
                )
        );
        saveJsonButton.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update clan's activity
                for (Clan clan: clanObjects) {
                    String checkBoxName = "checkbox" + clan.getName();
                    JCheckBox checkBox = (JCheckBox) swingElements.getComponentByName(container, checkBoxName);
                    boolean checkBoxActivity = checkBox.isSelected();
                    clan.setActivity(checkBoxActivity);
                }

                // Save to file
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
        gui.addToGUI(saveJsonButton.getButton());

        // Save as csv button [BOTTOM-RIGHT]
        var saveCsvButton = new Button(
                swingElements.createSwing(
                        "button",
                        "buttonExportToCSV",
                        "Export To CSV"
                )
        );
        saveCsvButton.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Update clan's activity
                for (Clan clan: clanObjects) {
                    String checkBoxName = "checkbox" + clan.getName();
                    JCheckBox checkBox = (JCheckBox) swingElements.getComponentByName(container, checkBoxName);
                    boolean checkBoxActivity = checkBox.isSelected();
                    clan.setActivity(checkBoxActivity);
                }

                // Save to file
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
        gui.addToGUI(saveCsvButton.getButton());

        // Add empty space before exit button
        JLabel emptyLabel3 = new JLabel();
        gui.addToGUI(emptyLabel3);

        // Exit button [BOTTOM-RIGHT]
        var exitButton = new Button(
                swingElements.createSwing(
                        "button",
                        "buttonExportToJSON",
                        "Exit"
                )
        );
        exitButton.getButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { System.exit(0); }
        });
        gui.addToGUI(exitButton.getButton());

        // Refresh the panel to update the gui
        gui.refresh();
    }
}
