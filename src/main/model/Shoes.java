package model;

// Extends clothes to be more specific to the type of clothing added
public class Shoes extends Clothes {
    private int numOfShoes;

    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     *          increments count for total number of this type of clothing
     */
    public Shoes(String name) {
        super(name);
        numOfShoes++;
    }

    protected int getNumOfClothes() {
        return numOfShoes;
    }
}
