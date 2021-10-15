package model;

// Extends clothes to be more specific to the type of clothing added
public class Accessories extends Clothes {
    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     */
    public Accessories(String name) {
        super(name);
    }

    @Override
    public String getClothingType() {
        return "accessory";
    }
}
