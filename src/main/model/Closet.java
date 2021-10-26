package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents the full collection of clothing items
public class Closet implements Writable {
    private List<Clothes> closet;
    private String name;
    private int totalNumOfClothes;
    protected int numOfTops;
    protected int numOfBottoms;
    protected int numOfOnePieces;
    protected int numOfShoes;
    protected int numOfAccessories;

    /*
     * EFFECTS: initializes closet and sets number of all clothes to 0;
     */
    public Closet() {
        closet = new ArrayList<>();
        totalNumOfClothes = 0;
        numOfTops = 0;
        numOfBottoms = 0;
        numOfOnePieces = 0;
        numOfShoes = 0;
        numOfAccessories = 0;
    }

    /*
     * REQUIRES: non-null string name
     * EFFECTS: sets name for the closet
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * REQUIRES: a valid clothes object, no duplicate objects
     * EFFECTS: item is added to list of closet
     *          number of clothes and number of clothing type is incremented
     * MODIFIES: this
     */
    public Clothes addClothes(Clothes item) {
        closet.add(item);
        Type type = item.getType();
        if (type.equals(Type.TOP)) {
            numOfTops++;
        } else if (type.equals(Type.BOTTOM)) {
            numOfBottoms++;
        } else if (type.equals(Type.ONEPIECE)) {
            numOfOnePieces++;
        } else if (type.equals(Type.SHOE)) {
            numOfShoes++;
        } else {
            numOfAccessories++;
        }
        totalNumOfClothes++;
        return item;
    }

    // EFFECTS: returns an unmodifiable list of clothes in this closet
    public List<Clothes> getClothes() {
        return Collections.unmodifiableList(closet);
    }

    // EFFECTS: returns clothes in this closet as a JSON array
    private JSONArray clothesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Clothes item : closet) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns json representation of closet name and json array of clothing
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("closet", clothesToJson());
        return json;
    }

    // EFFECTS: returns entire (modifiable) closet list
    public List<Clothes> getCloset() {
        return closet;
    }

    public String getName() {
        return name;
    }

    public int getTotalNumOfClothes() {
        return totalNumOfClothes;
    }

    public int getNumOfTops() {
        return numOfTops;
    }

    public int getNumOfBottoms() {
        return numOfBottoms;
    }

    public int getNumOfOnePieces() {
        return numOfOnePieces;
    }

    public int getNumOfShoes() {
        return numOfShoes;
    }

    public int getNumOfAccessories() {
        return numOfAccessories;
    }
}
