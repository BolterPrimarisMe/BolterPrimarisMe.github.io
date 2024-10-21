import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class DataEntryApp extends JFrame {
    private JTextField textField1, textField2, textField3;
    private JRadioButton option1, option2;
    private ButtonGroup optionGroup;
    private JButton submitButton, loadButton;
    private File selectedFile;

    public DataEntryApp() {
        setTitle("Data Entry Program");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Create text fields
        textField1 = new JTextField(20);
        textField2 = new JTextField(20);
        textField3 = new JTextField(20);
        
        // Create radio buttons
        option1 = new JRadioButton("Option 1");
        option2 = new JRadioButton("Option 2");
        optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);

        // Create buttons
        submitButton = new JButton("Submit");
        loadButton = new JButton("Load Data");

        // Add components to frame
        add(new JLabel("Text Field 1:"));
        add(textField1);
        add(new JLabel("Text Field 2:"));
        add(textField2);
        add(new JLabel("Text Field 3:"));
        add(textField3);
        add(option1);
        add(option2);
        add(submitButton);
        add(loadButton);

        // Action listeners
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveData();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    private void saveData() {
        String fileName = JOptionPane.showInputDialog("Enter file name to save:");
        if (fileName != null) {
            try (FileWriter writer = new FileWriter(fileName + ".txt")) {
                writer.write("Text Field 1: " + textField1.getText() + "\n");
                writer.write("Text Field 2: " + textField2.getText() + "\n");
                writer.write("Text Field 3: " + textField3.getText() + "\n");
                writer.write("Selected Option: " + (option1.isSelected() ? "Option 1" : "Option 2") + "\n");
                JOptionPane.showMessageDialog(this, "Data saved successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loadData() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                textField1.setText(reader.readLine().split(": ")[1]);
                textField2.setText(reader.readLine().split(": ")[1]);
                textField3.setText(reader.readLine().split(": ")[1]);
                String selectedOption = reader.readLine().split(": ")[1];
                if (selectedOption.equals("Option 1")) {
                    option1.setSelected(true);
                } else {
                    option2.setSelected(true);
                }
                JOptionPane.showMessageDialog(this, "Data loaded successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DataEntryApp().setVisible(true);
        });
    }
}
