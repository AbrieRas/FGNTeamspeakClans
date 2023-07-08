package main;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String... args) {
        var GUI = new GUI();
        GUI.createGui(500, 500);

        JPanel guiPanel = GUI.getPanel();
        int amountOfButtons = 9;
        for (int i = 0; i < amountOfButtons; i++) {
            JButton button = new JButton("Press");
            button.setSize(100, 100);
            guiPanel.add(button);
        }

        // Refresh the panel to update the GUI
        guiPanel.revalidate();
        guiPanel.repaint();

        // Get clan names and create clan objects
        String pathToClans = System.getProperty("user.dir") + "/src/main/java/resources/clans.txt";

        /** getClans
         * Param: String - filepath to read from file
         * Return: Arraylist of items read from file */
        ArrayList<String> clanNames = new ReadWriteFile().getClans(pathToClans);
        ArrayList<Clan> clanObjects = new ArrayList<>();
        for (String clanName : clanNames) {
            clanObjects.add(new Clan(clanName));
        }

        String pathToExports = System.getProperty("user.dir") + "/src/main/java/resources/exports/";
        String fileType = "json";
        boolean wroteDataToDatabase = new ReadWriteFile().storeDataInExports(pathToExports, clanObjects, fileType);
        if (wroteDataToDatabase) {
            // Send success message
            System.out.println("Successfully wrote to file.");
        } else {
            // Send fail message
            System.out.println("Writing to file failed.");
        }
    }
}
