package DSA;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class InputStorage {

    private String[] urlArray;
    private AzpStack urlStack;
    private String filePath;
    private JTextArea textArea;

    public InputStorage(String filePath, JTextArea textArea) {
        this.filePath = filePath;
        this.urlArray = new String[10]; // Set a default capacity of 10 or use a dynamic data structure for a variable capacity
        this.urlStack = new AzpStack();
        this.textArea = textArea;
    }

    public void addURL(String name, String ign, String email, String externalURL) {
        if (!checkForDuplicates(name, ign, email, externalURL)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                String formattedURL = String.format("%-20s%-20s%-30s%-40s", name, ign, email, externalURL);
                writer.write(formattedURL);
                writer.newLine();
                System.out.println("URL has been added to the file.");
                urlStack.push(formattedURL); // Push to the stack if no duplicates are found
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            String displayText = String.format("%-20s%-20s%-30s%-40s\n", name, ign, email, externalURL);
            textArea.append(displayText);
        } else {
            JOptionPane.showMessageDialog(null, "Duplicate data detected. Please enter unique information.", "Duplicate Data",
                    JOptionPane.ERROR_MESSAGE);
            // Handle the re-entry of new inputs here
            String reenteredName = JOptionPane.showInputDialog(null, "Re-enter your Name:");
            String reenteredIgn = JOptionPane.showInputDialog(null, "Re-enter your IGN:");
            String reenteredEmail = JOptionPane.showInputDialog(null, "Re-enter your Email:");
            String reenteredExternalURL = JOptionPane.showInputDialog(null, "Re-enter your External URL:");
            addURL(reenteredName, reenteredIgn, reenteredEmail, reenteredExternalURL);
        }
    }
    public boolean checkForDuplicates(String name, String ign, String email, String externalURL) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                if (data.length >= 4 && (data[0].equalsIgnoreCase(name) || data[1].equalsIgnoreCase(ign) || data[2].equalsIgnoreCase(email) || data[3].equalsIgnoreCase(externalURL))) {
                    return true; // Found a duplicate
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // No duplicates found
    }
    
    
    public void deleteData(String name) {
        try {
            java.io.File file = new java.io.File(filePath);
            java.io.File tempFile = new java.io.File("C:\\Users\\User\\Desktop\\JAVA\\hehe\\temp.txt");
            java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(tempFile));
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file));
            String line;
            boolean isFound = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                if (data[0].equals(name)) {
                    isFound = true;
                    continue;
                }
                writer.write(line + System.lineSeparator());
            }
            writer.close();
            reader.close();
            if (!isFound) {
                JOptionPane.showMessageDialog(null, "Name not found in the database", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (file.delete()) {
                    if (tempFile.renameTo(file)) {
                        JOptionPane.showMessageDialog(null, "Player Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }


    public void displayData(JTextArea textArea, String filePath) {
        // Set the font to monospaced for uniform alignment
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        textArea.setFont(font);
    
        // Set the headers
        String header = String.format("%-20s%-20s%-30s%-40s\n", "Name", "IGN", "Email Address", "External Link");
        textArea.append(header);
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+"); // Split by any whitespace
                if (data.length >= 4) {
                    String formattedLine = String.format("%-20s%-20s%-30s%-40s\n", data[0], data[1], data[2], data[3]);
                    textArea.append(formattedLine);
                } else {
                    // Handle cases where the line doesn't have enough elements
                    System.err.println("Invalid data format: " + line);
                    // You can choose to skip this line or display an error message in the UI
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void editData(String oldName, String name, String ign, String email, String externalURL) {
        boolean isFound = false;
        try {
            File file = new File(filePath);
            File tempFile = new File("C:\\Users\\User\\Desktop\\JAVA\\hehe\\TempData.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\s+");
                if (data[0].equals(oldName)) {
                    isFound = true;
                    data[0] = name; // Update the name directly
                    String updatedData = String.format("%-20s%-20s%-30s%-40s", data[0], ign, email, externalURL);
                    writer.write(updatedData + System.lineSeparator());
                } else {
                    writer.write(line + System.lineSeparator());
                }
            }
            writer.close();
            reader.close();
            if (!isFound) {
                JOptionPane.showMessageDialog(null, "Name not found in the database", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!file.delete()) {
                    System.out.println("Could not delete file");
                    return;
                }
                if (!tempFile.renameTo(file)) {
                    System.out.println("Could not rename the temporary file");
                    return;
                }
                JOptionPane.showMessageDialog(null, "Player Edited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int databaseNumber = 1;

    public void createNewDatabase() {
        String directoryPath = "C:\\Users\\User\\Desktop\\JAVA\\hehe\\";
        String newFilePath = directoryPath + "newDatabase" + databaseNumber + ".txt";
        databaseNumber++;

        try {
            File file = new File(newFilePath);
            if (file.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Database Creation Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // If the file already exists, prompt the user to rename the file
                String newName = JOptionPane.showInputDialog(null, "Enter a new name for the database file:");
                if (newName == null) {
                    JOptionPane.showMessageDialog(null, "Database creation was canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String renamedFilePath = directoryPath + newName + ".txt";
                    File renamedFile = new File(renamedFilePath);

                    // Check if the renamed file already exists
                    if (renamedFile.exists()) {
                        JOptionPane.showMessageDialog(null, "File with the provided name already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (file.renameTo(renamedFile)) {
                            JOptionPane.showMessageDialog(null, "Database Successfully Renamed", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error Renaming Database", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
         }
