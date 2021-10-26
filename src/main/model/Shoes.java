package model;

// Extends clothes to be more specific to the type of clothing added
public class Shoes extends Clothes {
    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     */
    public Shoes(String name) {
        super(name);
        type = Type.SHOE;
    }
}
