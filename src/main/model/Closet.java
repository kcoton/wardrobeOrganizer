package model;

import java.util.ArrayList;

// Represents the full collection of clothing items
public class Closet {
    private ArrayList<Clothes> closet;
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
     * REQUIRES: a valid clothes object, no duplicate objects
     * EFFECTS: item is added to list of closet
     *          number of clothes and number of clothing type is incremented
     * MODIFIES: this
     */
    public void addClothes(Clothes item) {
        closet.add(item);
        String type = item.getClass().getName().substring(6);
        switch (type) {
            case "Tops":
                numOfTops++;
                break;
            case "Bottoms":
                numOfBottoms++;
                break;
            case "OnePieces":
                numOfOnePieces++;
                break;
            case "Shoes":
                numOfShoes++;
                break;
            case "Accessories":
                numOfAccessories++;
                break;
        }
        totalNumOfClothes++;
    }

    // returns entire closet list
    public ArrayList<Clothes> getCloset() {
        return closet;
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
