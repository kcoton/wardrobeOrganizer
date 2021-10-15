package model;

// Extends clothes to be more specific to the type of clothing added
public class Tops extends Clothes {
    private int numOfTops;

    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     *          increments count for total number of this type of clothing
     */
    public Tops(String name) {
        super(name);
        numOfTops++;
    }

    // returns number of this piece of clothing
    protected int getNumOfClothes() {
        return numOfTops;
    }
}
