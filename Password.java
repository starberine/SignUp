package DSA;

import java.awt.Font;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Password {
    private static final String KEY = "AZUREPANTHEONS";
    private static final String FILE_NAME = "UNsANDPW.txt";

    private static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                result.append((char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A'));
                j = ++j % key.length();
            }
        }
        return result.toString();
    }

    private static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                result.append((char) ((c - key.charAt(j) + 26) % 26 + 'A'));
                j = ++j % key.length();
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // Log In
        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));

        ImageIcon icon = new ImageIcon("C:\\Users\\User\\Downloads\\OneDrive_1_11-8-2023\\20230913_003414000_iOS.png");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
        imagePanel.add(imageLabel);
        imagePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("Log In");
        titleLabel.setFont(new Font("Akira Expanded", Font.PLAIN, 16));
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        signUpPanel.add(imagePanel);
        signUpPanel.add(titleLabel);

        JTextField signUpUsernameField = new JTextField(10);
        JPasswordField signUpPasswordField = new JPasswordField(10);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        signUpPanel.add(usernameLabel);
        signUpPanel.add(signUpUsernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        signUpPanel.add(passwordLabel);
        signUpPanel.add(signUpPasswordField);

        boolean isLoginSuccessful = false;

        while (!isLoginSuccessful) {
            int signUpResult = JOptionPane.showConfirmDialog(null, signUpPanel, "Log In", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (signUpResult == JOptionPane.OK_OPTION) {
                String username = signUpUsernameField.getText();
                String password = new String(signUpPasswordField.getPassword());

                // Perform your login verification logic here
                if (username.equals("Admin") && password.equals("123")) {
                    isLoginSuccessful = true;
                    JOptionPane.showMessageDialog(null, "Welcome, Empyrean!", "Login Status", JOptionPane.INFORMATION_MESSAGE);

                    // Encrypt user credentials
                    String encryptedUsername = encrypt(username, KEY);
                    String encryptedPassword = encrypt(password, KEY);

                    // Save the credentials to the file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                        writer.write(encryptedUsername);
                        writer.newLine();
                        writer.write(encryptedPassword);
                        writer.newLine();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Login Status",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.exit(0);
            }
        }
    }
}
