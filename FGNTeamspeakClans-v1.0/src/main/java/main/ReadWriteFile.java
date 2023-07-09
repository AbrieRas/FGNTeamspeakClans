package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadWriteFile {
    public ArrayList<String> getClans(String path) {
        ArrayList<String> data = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String rowData = scanner.nextLine();
                data.add(rowData);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred reading the file (getClans).");
            e.printStackTrace();
        }
        return data;
    }

    public boolean storeDataInExports(String pathToExports, ArrayList<Clan> clansClass, String fileType) {
        String dateTimeNow = new Utility().getDateTimeNow();
        String fileName = "Clans-Export-" + dateTimeNow.replace(":", "") + "." + fileType;
        String pathToFile = pathToExports + fileName;

        try {
            FileWriter writer = new FileWriter(pathToFile);

            // Format data based on fileType (.json or .csv)
            StringBuilder formattedData = formatData(clansClass, fileType, dateTimeNow);

            // Write to file
            writer.write(String.valueOf(formattedData));
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while appending the data: " + e.getMessage());
            return false;
        }
        return true;
    }

    private StringBuilder formatData(ArrayList<Clan> clansClass, String fileType, String dateTimeNow) {
        StringBuilder exportData = new StringBuilder();
        boolean isLastClan = false;
        switch (fileType) {
            case "json" -> {
                // JSON format (Clans-Export-dateTime.json)
                /*
                {
                    "date": "dateTime",
                    "clans" : [
                        {
                            "name": "clanName",
                            "active": "clanActivity"
                        },
                        {
                            "name": "clanName",
                            "active": "clanActivity"
                        }, etc
                    ]
                 }
                 */
                exportData.append("""
                        {
                            "date": "$dateTime",
                            "clans": [
                        """.replace("$dateTime", dateTimeNow)
                );
                for (Clan clan : clansClass) {
                    isLastClan = (clan == clansClass.get(clansClass.size() - 1));
                    if (!isLastClan) {
                        // All other iterations
                        exportData.append("""
                                    {
                                        "name": "$clanName",
                                        "active": "$clanActivity"
                                    },
                            """
                                .replace("$clanName", clan.getName())
                                .replace("$clanActivity", Boolean.toString(clan.getActivity()))
                        );
                    } else {
                        // Last iteration
                        exportData.append("""
                                    {
                                        "name": "$clanName",
                                        "active": "$clanActivity"
                                    }
                                ]
                            }
                            """
                                .replace("$clanName", clan.getName())
                                .replace("$clanActivity", Boolean.toString(clan.getActivity()))
                        );
                    }
                }
            }
            case "csv" -> {
                // CSV format (Clans-Export-dateTime.csv)
                // dateTime, clanName, activity --> per clan
                for (Clan clan : clansClass) {
                    isLastClan = (clan == clansClass.get(clansClass.size() - 1));
                    if (!isLastClan) {
                        // All other iterations
                        exportData.append("""
                            $dateTime,"$clanName",$activity
                            """.replace("$dateTime", dateTimeNow)
                                .replace("$clanName", clan.getName())
                                .replace("$clanActivity", Boolean.toString(clan.getActivity()))
                        );
                    } else {
                        // Last iteration
                        exportData.append("""
                            $dateTime,"$clanName",$activity
                            """.replace("$dateTime", dateTimeNow)
                                .replace("$clanName", clan.getName())
                                .replace("$clanActivity", Boolean.toString(clan.getActivity()))
                        );
                    }
                }
            }
        }
        return exportData;
    }
}
