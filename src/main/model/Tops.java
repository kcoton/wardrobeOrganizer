package model;

// Extends clothes to be more specific to the type of clothing added
public class Tops extends Clothes {
    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     */
    public Tops(String name) {
        super(name);
    }
}
