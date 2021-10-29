package persistence;

import model.Clothes;
import model.Type;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Checks that objects are equal when run through json
public class JsonTest {
    protected void checkClothingNameType(String name, Type type, Clothes clothes) {
        assertEquals(name, clothes.getName());
        assertEquals(type, clothes.getType());
    }

    protected void checkClothingTags(ArrayList<String> tags, ArrayList<String> testTags) {
        assertEquals(tags.size(), testTags.size());
        int i = 0;
        for (String tag : tags) {
            assertEquals(tag, testTags.get(i));
            i++;
        }
    }
}
