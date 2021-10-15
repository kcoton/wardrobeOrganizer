package model;

// Extends clothes to be more specific to the type of clothing added
public class Accessories extends Clothes {
    private int numOfAccessories;

    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     *          increments count for total number of this type of clothing
     */
    public Accessories(String name) {
        super(name);
        numOfAccessories++;
    }

    protected int getNumOfClothes() {
        return numOfAccessories;
    }
}
