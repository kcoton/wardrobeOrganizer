package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    Closet closet;

    @BeforeEach
    void runBefore() {
        closet = new Closet();
        closet.setName("Kiara's Closet");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("ioexception was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCloset() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCloset.json");
            writer.open();
            writer.write(closet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCloset.json");
            closet = reader.read();
            assertEquals("Kiara's Closet", closet.getName());
            assertEquals(0, closet.getTotalNumOfClothes());
        } catch (IOException e) {
            fail("exception should not be thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterClosetNoTags() {
        try {
            closet.addClothes(new Tops("tee shirt"));
            closet.addClothes(new Tops("band shirt"));
            closet.addClothes(new Bottoms("jeans"));
            closet.addClothes(new OnePiece("Dress"));
            JsonWriter writer = new JsonWriter("./data/testWriterClosetNoTags.json");
            writer.open();
            writer.write(closet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClosetNoTags.json");
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
        try {
            Clothes tee = closet.addClothes(new Tops("tee shirt"));
            Clothes jeans = closet.addClothes(new Bottoms("jeans"));
            tee.addTag("plain");
            tee.addTag("white");
            tee.addTag("BasicS");
            jeans.addTag("RIPPED");
            JsonWriter writer = new JsonWriter("./data/testWriterClosetHasTags.json");
            writer.open();
            writer.write(closet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterClosetHasTags.json");
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
