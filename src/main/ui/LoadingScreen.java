package ui;

import javax.swing.*;
import java.io.IOException;

// Pop-up window loads splash screen for application
public class LoadingScreen extends JFrame {
    private JWindow splash;

    // EFFECTS: initializes splash window with image and size
    public LoadingScreen() throws IOException {
        splash = new JWindow();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        Icon image = new ImageIcon("./data/splash-original.gif");
        JLabel imageLabel = new JLabel(image);
        setSize(image.getIconWidth(), image.getIconHeight());
        add(imageLabel);
    }
}