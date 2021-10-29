package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    private Closet closet;

    @BeforeEach
    void runBefore() {
        closet = new Closet();
        closet.setName("Kiara's Closet");
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noneExistent.json");
        try {
            closet = reader.read();
            fail("ioexception is expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCloset() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCloset.json");
        try {
            closet = reader.read();
            assertEquals("Kiara's Closet", closet.getName());
            assertEquals(0, closet.getTotalNumOfClothes());
        } catch (IOException e) {
            fail("cannot read from file");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testReaderClosetNoTags() {
        JsonReader reader = new JsonReader("./data/testWriterClosetNoTags.json");
        try {
            closet = reader.read();
            assertEquals("Kiara's Closet", closet.getName());
            List<Clothes> testCloset = closet.getClothes();
            assertEquals(4, testCloset.size());
            checkClothingNameType("tee shirt", Type.TOP, testCloset.get(0));
            checkClothingNameType("band shirt", Type.TOP, testCloset.get(1));
            checkClothingNameType("jeans", Type.BOTTOM, testCloset.get(2));
            checkClothingNameType("dress", Type.ONEPIECE, testCloset.get(3));

            for (Clothes pieces : testCloset) {
                ArrayList<String> testEmptyTags = pieces.getTags();
                assertEquals(0, testEmptyTags.size());
            }
        } catch (IOException e) {
            fail("exception should not be thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterClosetHasTags() {
        JsonReader reader = new JsonReader("./data/testWriterClosetHasTags.json");
        try {
            closet = reader.read();
            assertEquals("Kiara's Closet", closet.getName());
            List<Clothes> testCloset = closet.getClothes();
            assertEquals(2, testCloset.size());
            checkClothingNameType("tee shirt", Type.TOP, testCloset.get(0));
            checkClothingNameType("jeans", Type.BOTTOM, testCloset.get(1));

            ArrayList<String> testTagsTee = testCloset.get(0).getTags();
            ArrayList<String> teeTags = new ArrayList<>();
            teeTags.add("plain");
            teeTags.add("white");
            teeTags.add("basics");

            ArrayList<String> testTagsJeans = testCloset.get(1).getTags();
            ArrayList<String> jeanTags = new ArrayList<>();
            jeanTags.add("ripped");

            checkClothingTags(teeTags, testTagsTee);
            checkClothingTags(jeanTags, testTagsJeans);
        } catch (IOException e) {
            fail("exception should not be thrown");
        }
    }
}
