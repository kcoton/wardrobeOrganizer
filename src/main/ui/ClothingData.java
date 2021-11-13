package ui;

import model.Closet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Represents the clothing storage system
public class ClothingData {
    private static final String JSON_STORE = "./data/closet.json";
    private Scanner input;
    private Closet closet;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the clothing data storage system
    public ClothingData() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes closet and scanner
    private void init() {
        closet = new Closet();
        input = new Scanner(System.in);
    }
}
