package DSA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class Trial2 {
    public static void main(String[] args) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        String filePath = "C:\\Users\\User\\Desktop\\JAVA\\hehe\\newDatabase.txt";

        AzpStack azpStack = new AzpStack();
        InputStorage inputStorage1 = new InputStorage(filePath, textArea);
        InputStorage inputStorage = new InputStorage("C:\\Users\\User\\Desktop\\JAVA\\hehe\\newDatabase.txt", textArea);

        Password.main(new String[]{});
        String[] mainMenuOptions = {"Add", "Edit", "Delete", "Display", "Database", "Exit"};
        CustomImageIcon icon = new CustomImageIcon("C:\\Users\\User\\Downloads\\OneDrive_1_11-8-2023\\20230913_003414000_iOS.png");
        Image image = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH); // Adjust the dimensions as per your requirement
        CustomImageIcon scaledIcon = new CustomImageIcon(image);

        JLabel logoLabel = new JLabel(scaledIcon);
        JPanel logoPanel = new JPanel(new BorderLayout());
        logoPanel.add(logoLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 20, 5, 20);

        for (String option : mainMenuOptions) {
            JButton button = new JButton(option);
            button.addActionListener(e -> {
                switch (option) {
                    case "Add":
                    System.out.println("Add option selected");
                
                    JTextField nameField = new JTextField(10);
                    JTextField ignField = new JTextField(10);
                    JTextField emailField = new JTextField(10);
                    JTextField externalURLField = new JTextField(10);
                
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Name:"));
                    panel.add(nameField);
                    panel.add(new JLabel("IGN:"));
                    panel.add(ignField);
                    panel.add(new JLabel("Email:"));
                    panel.add(emailField);
                    panel.add(new JLabel("External URL:"));
                    panel.add(externalURLField);
                
                    int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter the Needed Input",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (result == JOptionPane.OK_OPTION) {
                        String name = nameField.getText();
                        String ign = ignField.getText();
                        String email = emailField.getText();
                        String externalURL = externalURLField.getText();
                        inputStorage.addURL(name, ign, email, externalURL);
                        JOptionPane.showMessageDialog(null, "Saved in Database!");
                    }
                    break;

                    case "Edit":
                    System.out.println("Edit option selected");
                    JPanel editPanel = new JPanel();
                    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
                
                    JTextField fullNameField = new JTextField(10);
                    JTextField newNameField = new JTextField(10);
                    JTextField newIGNField = new JTextField(10);
                    JTextField newEmailField = new JTextField(10);
                    JTextField newExternalURLField = new JTextField(10);
                
                    editPanel.add(new JLabel("Enter the full name from the database:"));
                    editPanel.add(fullNameField);
                    editPanel.add(new JLabel("Enter the new name:"));
                    editPanel.add(newNameField);
                    editPanel.add(new JLabel("Enter the new IGN:"));
                    editPanel.add(newIGNField);
                    editPanel.add(new JLabel("Enter the new email:"));
                    editPanel.add(newEmailField);
                    editPanel.add(new JLabel("Enter the new external URL:"));
                    editPanel.add(newExternalURLField);
                
                    int optionResult = JOptionPane.showConfirmDialog(null, editPanel, "Edit Player Data",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                
                    if (optionResult == JOptionPane.OK_OPTION) {
                        String fullName = fullNameField.getText();
                        String newName = newNameField.getText();
                        String newIGN = newIGNField.getText();
                        String newEmail = newEmailField.getText();
                        String newExternalURL = newExternalURLField.getText();
                
                        inputStorage.editData(fullName, newName, newIGN, newEmail, newExternalURL);
                    } else {
                        System.out.println("Edit canceled");
                    }
                    break;
                

                
                        case "Delete":
                        System.out.println("Delete option selected");
                        String[] deleteOptions = {"Delete One", "Delete All"};
                        int deleteChoice = JOptionPane.showOptionDialog(null, "Choose deletion option", "Delete Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, deleteOptions, deleteOptions[0]);
                    
                        if (deleteChoice == 0) {
                            String nameToDelete = JOptionPane.showInputDialog(null, "Enter the name of the player to delete:");
                            inputStorage.deleteData(nameToDelete);
                        } else {
                            try {
                                java.io.File file = new java.io.File(filePath);
                                if (file.delete()) {
                                    JOptionPane.showMessageDialog(null, "Entire database deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } catch (java.lang.Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        break;
                    
                        case "Display":
                        System.out.println("Display All");
                        SwingUtilities.invokeLater(() -> {
                            try {
                                JFrame displayFrame = new JFrame("Database");
                                displayFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                JTextArea displayTextArea = new JTextArea(10, 30);
                                JScrollPane scrollPaneDisplay = new JScrollPane(displayTextArea);
                                displayFrame.add(scrollPaneDisplay, BorderLayout.CENTER);
                                
                                // Ensure the textArea is cleared before displaying new data
                                displayTextArea.setText("");
                                
                                inputStorage.displayData(displayTextArea, filePath);
                                
                                displayFrame.pack();
                                displayFrame.setVisible(true);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(null, "Error displaying database", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        });
                        break;
                    

                        case "Database":
                        System.out.println("Database option selected");
                        inputStorage.createNewDatabase();
                        break;

                    
                    case "Exit":
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            });

            // Adding rounded edges to the buttons
            button.setPreferredSize(new Dimension(200, 100)); // Adjust the width and height as needed
            button.setFont(new Font("Poppins Bold", Font.PLAIN, 20)); // Change the font and style as desired
            button.setBackground(new Color(128, 128, 128));
            button.setBorder(new RoundBorder(20, new Color(54, 69, 79))); // Adjust the radius and color as needed

            // Add space between buttons
            buttonPanel.add(button, gbc);
            gbc.gridy++;
        }

        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title Label addition
        JLabel titleLabel = new JLabel("Azure Pantheons"); // Change "Company Name" to the desired title
        titleLabel.setFont(new Font("Akira Expanded", Font.BOLD, 40)); // Adjust the font and style as needed

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        titlePanel.add(titleLabel);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.add(logoPanel);
        upperPanel.add(titlePanel);

        JPanel contentPanel = new JPanel(); // Initialize contentPanel
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.add(upperPanel);
        contentPanel.add(buttonPanel);

        frame.add(contentPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}

class CustomImageIcon extends ImageIcon implements Icon {
    private Image image;

    public CustomImageIcon(String filePath) {
        super(filePath);
        this.image = new ImageIcon(filePath).getImage();
    }

    public CustomImageIcon(Image image) {
        super(image);
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }
}


class RoundBorder implements Border {
    private int radius;
    private Color color;

    RoundBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(this.color);
        g.drawRoundRect(x, y, width - 1, height - 1, this.radius, this.radius);
    }
}
