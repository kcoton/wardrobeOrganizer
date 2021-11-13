package ui;

import javax.swing.*;
import java.io.IOException;

// Splash screen for when application is loading
public class LoadingScreen extends JFrame {
    private final JWindow splash;

    // EFFECTS: creates loading screen image and sets height/width
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