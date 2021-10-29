package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads closet from JSON data stored in file
// References JSON Example provided in the CPSC 210 phase 2 instructions
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads closet from file and returns it
    //          throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses closet from JSON object and returns it
    private Closet parseCloset(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Closet closet = new Closet();
        closet.setName(name);
        addClothing(closet, jsonObject);
        return closet;
    }

    // EFFECTS: reads closet from file and returns it
    //          throws IOException if an error occurs reading data from file
    public Closet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCloset(jsonObject);
    }

    // MODIFIES: closet
    // EFFECTS: parses clothing from JSON object and adds them to closet
    private void addClothing(Closet closet, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("closet");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addPiece(closet, nextItem);
        }
    }

    // MODIFIES: closet
    // EFFECTS: parses clothing pieces from JSON object and adds it to closet
    private void addPiece(Closet closet, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Type type = Type.valueOf(jsonObject.getString("type"));
        Clothes item;
        if (type.equals(Type.TOP)) {
            Clothes top = new Tops(name);
            item = closet.addClothes(top);
        } else if (type.equals(Type.BOTTOM)) {
            Clothes bottom = new Bottoms(name);
            item = closet.addClothes(bottom);
        } else if (type.equals(Type.ONEPIECE)) {
            Clothes onePiece = new OnePiece(name);
            item = closet.addClothes(onePiece);
        } else if (type.equals(Type.SHOE)) {
            Clothes shoes = new Shoes(name);
            item = closet.addClothes(shoes);
        } else {
            Clothes accessory = new Accessories(name);
            item = closet.addClothes(accessory);
        }
        addTags(item, jsonObject);
    }

    // MODIFIES: closet
    // EFFECTS: parses tags from JSON object and adds them to clothing pieces
    private void addTags(Clothes item, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        for (Object json : jsonArray) {
            String nextTag = json.toString();
            item.addTag(nextTag);
        }
    }
}
