package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Pop-up window loads previous file if user hits yes
public class LoadWindow extends JFrame implements ActionListener {
    private JWindow window;
    private JFrame nameWindow;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 350;
    private JButton buttonYes;
    private JButton buttonNo;
    private boolean isFileLoaded;
    private String name = "";
    private JTextField nameField;

    // EFFECTS: creates load window pop-up
    public LoadWindow() {
        initializeGUI();
    }

    // EFFECTS: initializes GUI for the window asking if user wants to load file
    private void initializeGUI() {
        window = new JWindow();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        window.setSize(WIDTH, HEIGHT);

        JPanel textPane = createTextPanel("Would you like to load a previous closet?", 38);
        JPanel buttonPane = createButtonPanel();

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
        mainPane.add(textPane);
        mainPane.add(buttonPane);
        mainPane.setBackground(Color.decode("#FFE6EE"));

        window.add(mainPane);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    @Override
    public String getName() {
        return name;
    }

    private enum Events {
        YES, NO
    }

    // EFFECTS: creates yes/no buttons with styling and action listeners
    private JPanel createButtonPanel() {
        buttonYes = new JButton("Yes");
        buttonNo = new JButton("No");

        buttonYes.setActionCommand(Events.YES.name());
        buttonNo.setActionCommand(Events.NO.name());
        buttonYes.addActionListener(this);
        buttonNo.addActionListener(this);

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

    // EFFECTS: user selects no -> sets new closet name using pop-up
    private void setNameWindow() {
        nameWindow = new JFrame();
        nameWindow.setSize(WIDTH, HEIGHT);
        nameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nameWindow.setUndecorated(true);

        JPanel textPane = createTextPanel("Enter your new closet's name", 42);
        JPanel fieldPane = createTextField();

        JPanel mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
        mainPane.add(textPane);
        mainPane.add(fieldPane);
        mainPane.setBackground(Color.decode("#FFE6EE"));

        nameWindow.add(mainPane);
        nameWindow.setLocationRelativeTo(null);
        nameWindow.setVisible(true);
        nameWindow.toFront();
    }

    // EFFECTS: creates text field with styling and listener to get name
    private JPanel createTextField() {
        nameField = new JTextField("Kiara's Closet");
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

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameField.getText();
                nameWindow.dispose();
            }
        };

        nameField.addActionListener(action);

        return fieldPane;
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

    // EFFECTS: yes loads the previous saved json closet file, no proceeds to creating new closet
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Events.YES.name())) {
            isFileLoaded = true;
        } else if (e.getActionCommand().equals(Events.NO.name())) {
            isFileLoaded = false;
            setNameWindow();
        }
        window.setVisible(false);
        window.dispose();
    }

    public boolean getIsFileLoaded() {
        return isFileLoaded;
    }
}
