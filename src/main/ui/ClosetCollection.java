package ui;

import model.Closet;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
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
        loadPreviousFile();
        //initializeGraphics();
    }

    private void loadPreviousFile() {

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
        closet = new Closet();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: draws JFrame window where ClosetCollection operates with buttons and fields
    private void initializeGraphics() throws MalformedURLException {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
