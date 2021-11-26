package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Graphical user interface for the closet collection app
// References CPSC 210 examples: SimpleDrawingPlayer and SpaceInvaders
public class ClosetCollection extends JFrame implements ActionListener {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/closet.json";
    private Scanner input;
    private Closet closet;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private LoadingScreen loadingScreen;
    private JFrame welcomeScreen;
    private JWindow saveWindow;
    private JFrame closetScreen;
    private ClosetOverview closetOverview;
    private JFrame addClothingScreen;
    private AddClothingWindow addClothingWindow;

    // EFFECTS: initializes GUI panels and required handlers
    public ClosetCollection() {
        super("Closet Collection");
        initializeLoadingScreen();
        initializeFields();
        initializeLoadWindow();
        initializeWelcomeScreen();
    }

    // MODIFIES: this
    // EFFECTS: main frame window that shows welcome screen with options
    private void initializeWelcomeScreen() {
        WelcomeWindow welcomeWindow = new WelcomeWindow(closet.getName());
        welcomeScreen = welcomeWindow.createGUI();
        welcomeScreen.setLocationRelativeTo(null);
        welcomeScreen.setVisible(true);
        welcomeScreen.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        welcomeScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                initializeSaveWindow();
            }
        });
        JButton viewCloset = welcomeWindow.getViewClosetButton();
        JButton viewOutfits = welcomeWindow.getViewOutfitsButton();
        viewCloset.addActionListener(this);
        viewOutfits.addActionListener(this); // not implemented yet
    }

    // EFFECTS: pop-up window asks if user wants to save current json closet file
    private void initializeSaveWindow() {
        SaveWindow saveFrame = new SaveWindow();
        saveWindow = saveFrame.createGUI();
        saveWindow.setLocationRelativeTo(null);
        saveWindow.setVisible(true);
        JButton yes = saveFrame.getButtonYes();
        JButton no = saveFrame.getButtonNo();
        yes.addActionListener(this);
        no.addActionListener(this);
    }

    private void printEvents() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
    }

    // MODIFIES: this
    // EFFECTS: action listener for events
    @SuppressWarnings("methodlength")
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("viewCloset")) {
            welcomeScreen.setVisible(false);
            initializeClosetCollection();
        } else if (command.equals("viewOutfits")) {
            System.out.println("tba");
        } else if (command.equals("yesSave")) {
            saveCloset();
            printEvents();
            System.exit(0);
        } else if (command.equals("noSave")) {
            System.out.println("no save");
            printEvents();
            System.exit(0);
        } else if (command.equals("addItem")) {
            closetScreen.dispose();
            initializeAddClothingWindow();
        } else if (command.equals("doneAdd")) {
            JTextField nameField = addClothingWindow.getNameField();
            String itemName = nameField.getText();
            JTextField typeField = addClothingWindow.getTypeField();
            String type = typeField.getText().toUpperCase();

            addClothing(itemName,type);

            addClothingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            addClothingWindow.dispose();
            addClothingScreen.dispose();
            System.out.println("added");
            initializeClosetCollection();
        } else if (command.equals("clearCloset")) {
            closet.clearCloset();
            System.out.println("cleared");
            closetScreen.dispose();
            initializeClosetCollection();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds clothing piece to collection
    private void addClothing(String itemName, String type) {
        if (type.equals("TOP")) {
            Clothes top = new Tops(itemName);
            closet.addClothes(top);
        } else if (type.equals("BOTTOM")) {
            Clothes bottom = new Bottoms(itemName);
            closet.addClothes(bottom);
        } else if (type.equals("ONEPIECE")) {
            Clothes onePiece = new OnePiece(itemName);
            closet.addClothes(onePiece);
        } else if (type.equals("SHOE")) {
            Clothes shoes = new Shoes(itemName);
            closet.addClothes(shoes);
        } else if (type.equals("ACCESSORY")) {
            Clothes accessory = new Accessories(itemName);
            closet.addClothes(accessory);
        }
    }

    // EFFECTS: add window opens for user to add clothing piece
    private void initializeAddClothingWindow() {
        addClothingWindow = new AddClothingWindow();
        addClothingScreen = addClothingWindow.createGUI();
        addClothingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addClothingScreen.setLocationRelativeTo(null);
        addClothingScreen.setVisible(true);
        JButton buttonAdd = addClothingWindow.getButtonAdd();
        buttonAdd.addActionListener(this);
    }

    // EFFECTS: hides welcome window and switches to overview of closet
    private void initializeClosetCollection() {
        closetOverview = new ClosetOverview(closet.getName());
        closetScreen = closetOverview.createGUI();
        closetScreen.setLocationRelativeTo(null);
        closetScreen.setVisible(true);
        closetScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        closetScreen.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                welcomeScreen.setVisible(true);
            }
        });

        if (closet.getClothes().size() > 0) {
            initializeClothes();
        }

        JButton addButton = closetOverview.getAddButton();
        JButton clearButton = closetOverview.getClearButton();
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    // EFFECTS: if previous closet loaded, loads items into ui
    private void initializeClothes() {
        List<Clothes> list = closet.getClothes();
        for (Clothes item : list) {
            closetOverview.addItemPane(item.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: pop-up window asks if user wants to load previous json closet file
    private void initializeLoadWindow() {
        LoadWindow loadWindow = new LoadWindow();
        while (true) {
            boolean isFileLoaded = loadWindow.getIsFileLoaded();
            System.out.print(""); // loop doesn't stop unless this is in it?
            if (isFileLoaded) { // yes to load previous file
                loadCloset();
                break;
            } else if (!loadWindow.getName().isEmpty()) { // no to load previous file
                closet = new Closet();
                closet.setName(loadWindow.getName());
                System.out.println("Welcome " + closet.getName() + "!");
                break;
            }
        }
    }

    // EFFECTS: saves closet to file
    private void saveCloset() {
        try {
            jsonWriter.open();
            jsonWriter.write(closet);
            jsonWriter.close();
            System.out.println("saved " + closet.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.print("error: cannot write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads closet from file
    private void loadCloset() {
        try {
            closet = jsonReader.read();
            System.out.println("loaded " + closet.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("cannot read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes loading screen for short amount of time
    private void initializeLoadingScreen() {
        try {
            loadingScreen = new LoadingScreen();
            loadingScreen.setLocationRelativeTo(null);
            loadingScreen.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3600);
            loadingScreen.setVisible(false);
            loadingScreen.dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes closet, scanner, reader/writer
    private void initializeFields() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }
}
