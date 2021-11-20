package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Pop-up window prompts user to add clothing piece
public class AddClothingWindow extends JFrame {
    private JFrame window;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 650;
    private JButton buttonAdd;
    private JTextField nameField;
    private JTextField typeField;

    // EFFECTS: creates and returns GUI for the window asking if user wants to save file
    public JFrame createGUI() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setSize(WIDTH, HEIGHT);

        JPanel textPane = createTextPanel("Add a Clothing Item", 48);
        JPanel buttonPane = createButtonPanel();
        JPanel fieldPane = createTextField();
        JPanel typeText = createTextPanel("Type (TOP, BOTTOM, ONEPIECE, SHOE, ACCESSORY)", 28);
        JPanel typePane = createTypeField();

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
        mainPane.add(textPane);
        mainPane.add(fieldPane);
        mainPane.add(typeText);
        mainPane.add(typePane);
        mainPane.add(buttonPane);
        mainPane.setBackground(Color.decode("#FFE6EE"));

        window.add(mainPane);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        return window;
    }

    // EFFECTS: creates text field with styling and listener to get type
    private JPanel createTypeField() {
        typeField = new JTextField("TOP");
        typeField.setEnabled(true);
        typeField.setEditable(true);
        typeField.setColumns(10);
        typeField.setHorizontalAlignment(JTextField.CENTER);
        JPanel fieldPane = new JPanel();
        typeField.setFont(new Font("Bell MT", Font.BOLD, 50));
        typeField.setForeground(Color.decode("#554d4f"));
        typeField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        fieldPane.setBackground(Color.decode("#FFE6EE"));
        fieldPane.add(typeField);

        return fieldPane;
    }

    // EFFECTS: creates text field with styling and listener to get name
    private JPanel createTextField() {
        nameField = new JTextField("Black Dress");
        nameField.setEnabled(true);
        nameField.setEditable(true);
        nameField.setColumns(10);
        nameField.setHorizontalAlignment(JTextField.CENTER);
        JPanel fieldPane = new JPanel();
        nameField.setFont(new Font("Bell MT", Font.BOLD, 50));
        nameField.setForeground(Color.decode("#554d4f"));
        nameField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        fieldPane.setBackground(Color.decode("#FFE6EE"));
        fieldPane.add(nameField);

        return fieldPane;
    }

    // EFFECTS: creates add button with action command
    private JPanel createButtonPanel() {
        buttonAdd = new JButton("Add (+)");
        JPanel buttonPane = new JPanel();
        buttonAdd.setFont(new Font("Bell MT", Font.BOLD, 50));
        buttonAdd.setForeground(Color.decode("#554d4f"));
        buttonAdd.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.setPreferredSize(new Dimension(200,75));
        buttonPane.setBackground(Color.decode("#FFE6EE"));
        buttonPane.add(buttonAdd);
        buttonPane.setBorder(new EmptyBorder(20,0,0,0));
        buttonAdd.setActionCommand("doneAdd");
        return buttonPane;
    }

    // REQUIRES: string message, font size
    // EFFECTS: creates text panes with styling for the panels
    private JPanel createTextPanel(String str, int fontSize) {
        JLabel text = new JLabel(str);
        text.setFont(new Font("Bell MT", Font.PLAIN, fontSize));
        text.setForeground(Color.decode("#554d4f"));
        JPanel textPane = new JPanel();
        textPane.setBackground(Color.decode("#FFE6EE"));
        textPane.add(text);
        textPane.setBorder(new EmptyBorder(90, 0, 20, 0));
        textPane.setMaximumSize(new Dimension(WIDTH, 100));
        return textPane;
    }

    public JButton getButtonAdd() {
        return buttonAdd;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getTypeField() {
        return typeField;
    }
}
