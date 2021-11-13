package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Pop-up window loads previous file if user hits yes
public class LoadWindow extends JFrame {
    private JWindow window;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 350;

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

        JPanel textPane = createTextPanel();
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

    // EFFECTS: creates yes/no buttons with styling
    private JPanel createButtonPanel() {
        JButton buttonYes = new JButton("Yes");
        JButton buttonNo = new JButton("No");
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

    // EFFECTS: creates text asking to load previous closet with styling
    private JPanel createTextPanel() {
        JLabel text = new JLabel("Would you like to load a previous closet?");
        text.setFont(new Font("Bell MT", Font.PLAIN, 38));
        text.setForeground(Color.decode("#554d4f"));
        text.setVerticalTextPosition(SwingConstants.BOTTOM);
        JPanel textPane = new JPanel();
        textPane.setBackground(Color.decode("#FFE6EE"));
        textPane.add(text);
        textPane.setBorder(new EmptyBorder(90,0,20,0));
        textPane.setMaximumSize(new Dimension(WIDTH, 100));
        return textPane;
    }
}
