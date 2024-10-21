import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UserFormApp {

    private JFrame frame;
    private JTextField nameField;
    private JTextArea addressArea;
    private JRadioButton maleButton, femaleButton, otherButton;
    private JCheckBox agreeBox;
    private JButton submitButton;
    private String fileName = "user_data.txt";

    public UserFormApp() {
        // Set up the main frame
        frame = new JFrame("User Information Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(6, 1));

        // Name Field
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        namePanel.add(nameField);
        frame.add(namePanel);

        // Address Area
        JPanel addressPanel = new JPanel();
        addressPanel.add(new JLabel("Address:"));
        addressArea = new JTextArea(3, 20);
        addressPanel.add(new JScrollPane(addressArea));
        frame.add(addressPanel);

        // Gender Selection
        JPanel genderPanel = new JPanel();
        genderPanel.add(new JLabel("Gender:"));
        ButtonGroup genderGroup = new ButtonGroup();
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);  
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        frame.add(genderPanel);

        // Agreement Checkbox
        JPanel agreePanel = new JPanel();
        agreeBox = new JCheckBox("I agree to the terms and conditions.");
        agreePanel.add(agreeBox);
        frame.add(agreePanel);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openConfirmationWindow();
            }
        });
        frame.add(submitButton);

        frame.setVisible(true);
    }

    private void openConfirmationWindow() {
        // Collect Data
        String name = nameField.getText();
        String address = addressArea.getText();
        String gender = maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : "yay";
        String agree = agreeBox.isSelected() ? "Agreed" : "Not Agreed";

        // Confirmation Window
        JFrame confirmFrame = new JFrame("Confirm Your Data");
        confirmFrame.setSize(300, 200);
        confirmFrame.setLayout(new GridLayout(6, 1));

        confirmFrame.add(new JLabel("Name: " + name));
        confirmFrame.add(new JLabel("Address: " + address));
        confirmFrame.add(new JLabel("Gender: " + gender));
        confirmFrame.add(new JLabel("Agreement: " + agree));

        JButton editButton = new JButton("Edit");
        JButton saveButton = new JButton("Confirm and Save");

        // If user wants to edit, close the confirmation window
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmFrame.dispose();
            }
        });

        // If confirmed, save the data to a file and close the confirmation window
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveDataToFile(name, address, gender, agree);
                JOptionPane.showMessageDialog(frame, "Data Saved!");
                confirmFrame.dispose();
            }
        });

        confirmFrame.add(editButton);
        confirmFrame.add(saveButton);

        confirmFrame.setVisible(true);
    }

    private void saveDataToFile(String name, String address, String gender, String agree) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Name: " + name);
            writer.newLine();
            writer.write("Address: " + address);
            writer.newLine();
            writer.write("Gender: " + gender);
            writer.newLine();
            writer.write("Agreement: " + agree);
            writer.newLine();
            writer.write("---------------------------");
            writer.newLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error saving data.");
        }
    }

    public static void main(String[] args) {
        new UserFormApp();
    }
}

