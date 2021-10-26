package persistence;

import model.Closet;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes JSON representation of Closet to file
// References JSON Example provided in the CPSC 210 phase 2 instructions
public class JsonWriter {
    private static final int INDENT = 5;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer, throws FileNotFoundException if destination
    //          file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of closet to file
    public void write(Closet closet) {
        JSONObject json = closet.toJson();
        saveToFile(json.toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
