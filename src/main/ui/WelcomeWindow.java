package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

// Frame creates and loads main welcome window with closet options
public class WelcomeWindow {
    private JFrame window;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    private String closetName;
    private JButton viewClosetButton;
    private JButton viewOutfitsButton;

    // REQUIRES: non-null string
    // EFFECTS: creates the GUI for the welcome window and sets the closet name
    public WelcomeWindow(String closetName) {
        this.closetName = closetName;
        createGUI();
    }

    // EFFECTS: creates and returns frame for welcome window
    public JFrame createGUI() {
        window = new JFrame("Welcome to your Clothing Collection!");
        window.setSize(WIDTH, HEIGHT);

        initializeIcon();

        JPanel textPane = createTextPanel("Welcome to " + closetName + "!", 52);
        JPanel buttonPane = createButtonPanel();
        JPanel imagePane = createImagePane();

        Container mainPane = new Container();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
        mainPane.add(textPane);
        mainPane.add(buttonPane);
        mainPane.add(imagePane);
        mainPane.setBackground(Color.decode("#FFE6EE"));

        window.add(mainPane);

        return window;
    }

    // EFFECTS: creates and returns image frame with styling
    private JPanel createImagePane() {
        JPanel imagePane = new JPanel();
        Icon image1 = new ImageIcon("./data/clothesOnHanger.jpg");
        Icon image2 = new ImageIcon("./data/outfits.jpg");
        JLabel viewClosetImage = new JLabel(image1);
        JLabel viewOutfitsImage = new JLabel(image2);
        viewOutfitsImage.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        viewClosetImage.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        viewOutfitsImage.setSize(400, 200);

        imagePane.add(viewClosetImage);
        imagePane.add(Box.createRigidArea(new Dimension(75,0)));
        imagePane.add(viewOutfitsImage);
        imagePane.setBackground(Color.decode("#FFE6EE"));
        imagePane.setBorder(new EmptyBorder(40,0,0,0));

        return imagePane;
    }

    // EFFECTS: sets the icon for the window
    private void initializeIcon() {
        ImageIcon icon = new ImageIcon("./data/icon.png");
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        icon.paintIcon(new JPanel(), image.getGraphics(), 0, 0);
        window.setIconImage(image);
    }

    // REQUIRES: string message, font size
    // EFFECTS: creates text panes with styling for the panels
    private JPanel createTextPanel(String str, int fontSize) {
        JLabel text = new JLabel(str);
        text.setFont(new Font("Bell MT", Font.ITALIC, fontSize));
        text.setForeground(Color.decode("#554d4f"));
        JPanel textPane = new JPanel();
        textPane.setBackground(Color.decode("#FFE6EE"));
        textPane.add(text);
        textPane.setBorder(new EmptyBorder(90, 0, 20, 0));
        textPane.setMaximumSize(new Dimension(WIDTH, 100));
        return textPane;
    }

    // EFFECTS: creates yes/no buttons with styling and action listeners
    private JPanel createButtonPanel() {
        viewClosetButton = new JButton("View Closet");
        viewOutfitsButton = new JButton("View Outfits");
        JPanel buttonPane = new JPanel();

        styleButtons(viewClosetButton, Font.BOLD, 50, 400, 75);
        styleButtons(viewOutfitsButton, Font.BOLD, 50, 400, 75);

        buttonPane.setBackground(Color.decode("#FFE6EE"));
        buttonPane.add(viewClosetButton);
        buttonPane.add(Box.createRigidArea(new Dimension(125,0)));
        buttonPane.add(viewOutfitsButton);
        buttonPane.setBorder(new EmptyBorder(20,0,0,0));
        buttonPane.setMaximumSize(new Dimension(WIDTH, 50));

        viewClosetButton.setActionCommand("viewCloset");
        viewOutfitsButton.setActionCommand("viewOutfits");
        return buttonPane;
    }

    // EFFECTS: styles buttons for the button panel
    private void styleButtons(JButton button, int fontWeight, int fontSize, int width, int height) {
        button.setFont(new Font("Bell MT", fontWeight, fontSize));
        button.setForeground(Color.decode("#554d4f"));
        button.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(width,height));
    }

    public JButton getViewClosetButton() {
        return viewClosetButton;
    }

    public JButton getViewOutfitsButton() {
        return viewOutfitsButton;
    }
}
