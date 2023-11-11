package DSA;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Trial {
    public static void main(String[] args) {
        String[] mainMenuOptions = {"Add", "Edit", "Delete", "Display All Members", "Database", "Exit"};

        ImageIcon icon = new ImageIcon("C:\\Users\\User\\Downloads\\OneDrive_1_11-8-2023\\20230913_003414000_iOS.png");
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Adjust the dimensions as per your requirement
        icon = new ImageIcon(newImage);

        JLabel logoLabel = new JLabel(icon);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoPanel.add(logoLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        for (String option : mainMenuOptions) {
            JButton button = new JButton(option);
            button.addActionListener(e -> {
                switch (option) {
                    case "Add":
                        System.out.println("Add option selected");
                        break;
                    case "Edit":
                        System.out.println("Edit option selected");
                        break;
                    case "Delete":
                        System.out.println("Delete option selected");
                        break;
                    case "Display All Members":
                        System.out.println("Display All Members option selected");
                        break;
                    case "Database":
                        System.out.println("Database option selected");
                        break;
                    case "Exit":
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            });

            // Set the background color of the button to the lighter shade of blue
            button.setBackground(Color.decode("#caf4fe"));

            // Set the preferred size of the button
            button.setPreferredSize(new Dimension(500, 100));

            // Add space between buttons
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.add(logoPanel);
        contentPanel.add(buttonPanel);

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        frame.add(contentPanel, BorderLayout.LINE_END);
        frame.add(textArea, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
