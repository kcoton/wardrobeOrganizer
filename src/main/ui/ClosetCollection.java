package ui;

import model.Closet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

// Graphical user interface for the closet collection app
// References CPSC 210 examples: SimpleDrawingPlayer and SpaceInvaders
public class ClosetCollection extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String JSON_STORE = "./data/closet.json";
    private Scanner input;
    private Closet closet;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private LoadingScreen loadingScreen;

    // EFFECTS: initializes GUI panels and required handlers
    public ClosetCollection() {
        super("Closet Collection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeLoadingScreen();
        initializeFields();
        initializeLoadWindow();
        initializeWelcomeScreen();
    }

    // MODIFIES: this
    // EFFECTS: main frame window that shows welcome screen with options
    private void initializeWelcomeScreen() {
        WelcomeWindow welcomeWindow = new WelcomeWindow(closet.getName());
        JFrame welcomeScreen = welcomeWindow.createGUI();
        welcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeScreen.setLocationRelativeTo(null);
        welcomeScreen.setVisible(true);
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
