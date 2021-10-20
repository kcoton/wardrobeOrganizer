package model;

import java.util.ArrayList;

// Represents each clothing piece having a name and a list of tags associated
public abstract class Clothes extends Closet {
    private String name;
    private ArrayList<String> tags;
    private int numOfTags;

    /*
     * REQUIRES: name must be non-zero length
     * EFFECTS: name on clothes is set to name
     *          tags is initialized
     *          number of tags is set to 0
     */
    public Clothes(String name) {
        this.name = name.toLowerCase();
        numOfTags = 0;
        tags = new ArrayList<>();
    }

    /*
     * REQUIRES: tag must be non-zero length
     * EFFECTS: tag is added to the list of tags for the clothing if not a duplicate
     *          tagName is converted to lowercase letters to match formatting
     *          number of tags is incremented
     */
    public boolean addTag(String tag) {
        String tagName = tag.toLowerCase();
        if (numOfTags > 0) {
            for (String next : tags) {
                if (next.equals(tagName)) {
                    return false;
                }
            }
        }
        tags.add(tagName);
        numOfTags++;
        return true;
    }

    /*
     * REQUIRES: tag must be non-zero length
     * EFFECTS: tag is removed from list of tags if found
     *          tagName is converted to lowercase letters to match formatting
     *          num of tags is decremented if found
     */
    public boolean deleteTag(String tag) {
        String tagName = tag.toLowerCase();
        for (String next : tags) {
            if (next.equals(tagName)) {
                tags.remove(next);
                numOfTags--;
                return true;
            }
        }
        return false;
    }

    // returns list of all tags for that clothing piece
    public ArrayList<String> getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    protected int getNumOfTags() {
        return numOfTags;
    }
}
