package model;

import java.util.List;

// Represents each clothing piece having a name and a list of tags associated
public abstract class Clothes {
    private String name;
    private List<String> tags;
    private int totalNumOfClothes;

    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     *          increments count for total number of clothes
     */
    public Clothes(String name) {
        this.name = name;
        totalNumOfClothes++;
    }

    /*
     * REQUIRES: tag must be non-zero length
     * EFFECTS: tag is added to the list of tags for the clothing if not a duplicate
     *          tagName is converted to lowercase letters to match formatting
     */
    private void addTag(String tag) {
        String tagName = tag.toLowerCase();
        for (String next : tags) {
            if (next.equals(tagName)) {
                return;
            }
        }
        tags.add(tagName);
    }

    /*
     * REQUIRES: tag must be non-zero length
     * EFFECTS: tag is removed from list of tags if found
     *          tagName is converted to lowercase letters to match formatting
     */
    private void deleteTag(String tag) {
        String tagName = tag.toLowerCase();
        for (String next : tags) {
            if (next.equals(tagName)) {
                tags.remove(next);
                return;
            }
        }
    }

    // returns list of all tags for that clothing piece
    protected List<String> getTags() {
        return tags;
    }

    // returns number of clothing pieces in that category
    protected abstract int getNumOfClothes();

    protected int getTotalNumOfClothes() {
        return totalNumOfClothes;
    }

    protected String getName() {
        return name;
    }
}
