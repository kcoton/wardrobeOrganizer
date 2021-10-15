package model;

// Extends clothes to be more specific to the type of clothing added
public class OnePiece extends Clothes {
    private int numOfOnePieces;

    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     *          increments count for total number of this type of clothing
     */
    public OnePiece(String name) {
        super(name);
        numOfOnePieces++;
    }

    protected int getNumOfClothes() {
        return numOfOnePieces;
    }
}
