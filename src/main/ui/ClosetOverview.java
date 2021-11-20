package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

// Frame create and loads main overview of the closet with all clothing added
public class ClosetOverview {
    private JFrame window;
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 900;
    private String closetName;
    private JButton addButton;
    private JButton statButton;
    private Container clothingPane;
    private Container filterPane;
    private JCheckBox top;
    private JCheckBox bottom;
    private JCheckBox onepiece;
    private JCheckBox shoe;
    private JCheckBox accessory;

    // REQUIRES: non-null string
    // EFFECTS: creates the GUI for the closet overview frame and sets the closet name
    public ClosetOverview(String closetName) {
        this.closetName = closetName;
        createGUI();
    }

    // EFFECTS: creates and returns frame for closet overview window
    public JFrame createGUI() {
        window = new JFrame(closetName);
        window.setSize(WIDTH, HEIGHT);

        initializeIcon();

        JPanel textPane = createTextPanel(closetName + " : Clothing Collection", 52, Font.ITALIC);
        JPanel buttonPane = createButtonPanel();
        JPanel headerPane = new JPanel();
        headerPane.setLayout(new FlowLayout());
        headerPane.add(textPane);
        headerPane.add(buttonPane);
        headerPane.setBackground(Color.decode("#FFE6EE"));

        createClothingPane();
        createFilterPane();
        Container dividerPane = createDividerPane();

        Container mainPane = new Container();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.Y_AXIS));
        mainPane.add(headerPane);
        mainPane.add(dividerPane);
        headerPane.setMaximumSize(new Dimension(WIDTH, 300));
        mainPane.setBackground(Color.decode("#FFE6EE"));

        window.add(mainPane);

        return window;
    }

    // EFFECTS: creates divider pane to separate filter and clothing pieces
    private Container createDividerPane() {
        Container dividerPane = new Container();
        dividerPane.setMinimumSize(new Dimension(WIDTH, 700));
        dividerPane.setLayout(new BoxLayout(dividerPane, BoxLayout.LINE_AXIS));
        dividerPane.add(filterPane);
        dividerPane.add(Box.createRigidArea(new Dimension(10, 0)));
        dividerPane.add(clothingPane);
        dividerPane.setBackground(Color.decode("#FFE6EE"));
        return dividerPane;
    }

    // EFFECTS: creates pane for clothing overview
    private void createClothingPane() {
        clothingPane = new Container();
        clothingPane.setLayout(new FlowLayout());
        clothingPane.setMaximumSize(new Dimension(100,500));
        clothingPane.setBackground(Color.decode("#FFE6EE"));
    }

    // REQUIRES: name of item
    // EFFECTS: adds clothing pieces to the clothing pane
    public void addItemPane(String name) {
        JPanel itemLabel = createItemLabel(name, 35, Font.PLAIN);
        clothingPane.add(itemLabel);
    }

    // REQUIRES: string message, font size, font style
    // EFFECTS: creates text panes with styling for the panels
    private JPanel createItemLabel(String str, int fontSize, int fontType) {
        JLabel text = new JLabel(str);
        text.setFont(new Font("Bell MT", fontType, fontSize));
        text.setForeground(Color.decode("#554d4f"));
        text.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel itemPane = new JPanel();
        JPanel picPane = createImagePane();
        itemPane.setBackground(Color.decode("#FFE6EE"));
        itemPane.add(text);
        itemPane.add(picPane);
        itemPane.setLayout(new BoxLayout(itemPane, BoxLayout.Y_AXIS));
        itemPane.setMaximumSize(new Dimension(250,100));
        return itemPane;
    }

    // EFFECTS: initializes checkboxes
    private void initializeFilters() {
        top = new JCheckBox("",true);
        bottom = new JCheckBox("",true);
        onepiece = new JCheckBox("",true);
        shoe = new JCheckBox("",true);
        accessory = new JCheckBox("",true);
    }

    // EFFECTS: creates pane for filter settings
    private void createFilterPane() {
        initializeFilters();
        filterPane = new JPanel();
        filterPane.setMinimumSize(new Dimension(300, 0));
        GridLayout filterGrid = new GridLayout(0,1,0,0);
        filterPane.setLayout(filterGrid);
        JPanel textTop = createTextPanel("TOPS",30, Font.PLAIN);
        JPanel textBottom = createTextPanel("BOTTOMS",30, Font.PLAIN);
        JPanel textOnePiece = createTextPanel("ONE-PIECES",30, Font.PLAIN);
        JPanel textShoe = createTextPanel("SHOES",30, Font.PLAIN);
        JPanel textAccessory = createTextPanel("ACCESSORIES",30, Font.PLAIN);
        textTop.add(top);
        textBottom.add(bottom);
        textOnePiece.add(onepiece);
        textShoe.add(shoe);
        textAccessory.add(accessory);
        filterPane.add(textTop);
        filterPane.add(textBottom);
        filterPane.add(textOnePiece);
        filterPane.add(textShoe);
        filterPane.add(textAccessory);
        filterPane.setBackground(Color.decode("#FFFFFF"));
    }

    // EFFECTS: creates and returns image frame with styling
    private JPanel createImagePane() {
        JPanel imagePane = new JPanel();
        Icon image = new ImageIcon("./data/hanger.jpg");
        JLabel hanger = new JLabel(image);
        hanger.setBorder(new BevelBorder(BevelBorder.RAISED, Color.WHITE, Color.decode("#a77782")));
        imagePane.add(hanger);
        return imagePane;
    }

    // EFFECTS: sets the icon for the window
    private void initializeIcon() {
        ImageIcon icon = new ImageIcon("./data/icon.png");
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        icon.paintIcon(new JPanel(), image.getGraphics(), 0, 0);
        window.setIconImage(image);
    }

    // REQUIRES: string message, font size, font style
    // EFFECTS: creates text panes with styling for the panels
    private JPanel createTextPanel(String str, int fontSize, int fontType) {
        JLabel text = new JLabel(str);
        text.setFont(new Font("Bell MT", fontType, fontSize));
        text.setForeground(Color.decode("#554d4f"));
        JPanel textPane = new JPanel();
        textPane.setBackground(Color.decode("#FFE6EE"));
        textPane.add(text);
        textPane.setBorder(new EmptyBorder(50, 0, 20, 0));
        return textPane;
    }

    // EFFECTS: creates yes/no buttons with styling and action listeners
    private JPanel createButtonPanel() {
        addButton = new JButton("(+) Add Item");
        statButton = new JButton("(?) Statistics");
        GridLayout gridLayout = new GridLayout(0,1,0,10);
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(gridLayout);
        styleButtons(addButton, Font.BOLD, 40, 300, 75);
        styleButtons(statButton, Font.BOLD, 40, 320, 75);
        buttonPane.setBackground(Color.decode("#FFE6EE"));
        buttonPane.add(addButton);
        buttonPane.add(statButton);
        buttonPane.setBorder(new EmptyBorder(30,40,0,0));
        addButton.setActionCommand("addItem");
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

    public JButton getAddButton() {
        return getAddButton();
    }

    public Container getClothingPane() {
        return clothingPane;
    }
}
