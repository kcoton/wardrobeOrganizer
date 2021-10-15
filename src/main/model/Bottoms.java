package model;

// Extends clothes to be more specific to the type of clothing added
public class Bottoms extends Clothes {
    private int numOfBottoms;

    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     *          increments count for total number of this type of clothing
     */
    public Bottoms(String name) {
        super(name);
        numOfBottoms++;
    }

    protected int getNumOfClothes() {
        return numOfBottoms;
    }
}
