package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Pop-up window saves current file if user hits yes
public class SaveWindow extends JFrame {
    private JWindow window;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 350;
    private JButton buttonYes;
    private JButton buttonNo;

    // EFFECTS: creates save window pop-up
    public SaveWindow() {
        createGUI();
    }

    // EFFECTS: creates and returns GUI for the window asking if user wants to save file
    public JWindow createGUI() {
        window = new JWindow();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        window.setSize(WIDTH, HEIGHT);

        JPanel textPane = createTextPanel("Would you like to save the current closet?", 38);
        JPanel buttonPane = createButtonPanel();

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
        mainPane.add(textPane);
        mainPane.add(buttonPane);
        mainPane.setBackground(Color.decode("#FFE6EE"));

        window.add(mainPane);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        return window;
    }

    // EFFECTS: creates yes/no buttons with styling and action listeners
    private JPanel createButtonPanel() {
        buttonYes = new JButton("Yes");
        buttonNo = new JButton("No");

        JPanel buttonPane = new JPanel();
        buttonYes.setFont(new Font("Bell MT", Font.BOLD, 50));
        buttonNo.setFont(new Font("Bell MT", Font.BOLD, 50));
        buttonYes.setForeground(Color.decode("#554d4f"));
        buttonNo.setForeground(Color.decode("#554d4f"));
        buttonYes.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        buttonYes.setContentAreaFilled(false);
        buttonNo.setPreferredSize(new Dimension(200,75));
        buttonNo.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        buttonNo.setContentAreaFilled(false);
        buttonYes.setPreferredSize(new Dimension(200,75));
        buttonPane.setBackground(Color.decode("#FFE6EE"));
        buttonPane.add(buttonYes);
        buttonPane.add(Box.createRigidArea(new Dimension(95,0)));
        buttonPane.add(buttonNo);
        buttonPane.setBorder(new EmptyBorder(20,0,0,0));

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

    public JButton getButtonYes() {
        return buttonYes;
    }

    public JButton getButtonNo() {
        return buttonNo;
    }
}
