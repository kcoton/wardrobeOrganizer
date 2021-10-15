package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {
    @Test
    public void testAddTagUnique() {
        Clothes tee = new Tops("BAND shirt");
        assertEquals(tee.getNumOfTags(),0);
        assertTrue(tee.addTag("memorabilia"));
        assertTrue(tee.addTag("concert merch"));
        assertTrue(tee.addTag("Paramore"));
        assertEquals(tee.getNumOfTags(),3);
    }

    @Test
    public void testAddTagDuplicate() {
        Clothes jeans = new Bottoms("mom jeans");
        assertTrue(jeans.addTag("Y2K"));
        assertEquals(jeans.getNumOfTags(),1);
        assertTrue(jeans.addTag("90s"));
        assertTrue(jeans.addTag("baggy"));
        assertFalse(jeans.addTag("y2k"));
        assertFalse(jeans.addTag("bAgGy"));
        assertFalse(jeans.addTag("90s"));
    }

    @Test
    public void testDeleteTagFound() {
        Clothes mittens = new Accessories("fuzzy gloves");
        mittens.addTag("soft");
        mittens.addTag("winter");
        mittens.addTag("white");
        assertEquals(mittens.getNumOfTags(),3);
        assertTrue(mittens.deleteTag("winter"));
        assertEquals(mittens.getNumOfTags(),2);
        assertTrue(mittens.deleteTag("white"));
        assertEquals(mittens.getNumOfTags(),1);
        assertTrue(mittens.deleteTag("soft"));
        assertEquals(mittens.getNumOfTags(),0);
    }

    @Test
    public void testDeleteTagNotFound() {
        Clothes boots = new Shoes("doc martens");
        assertEquals(boots.getNumOfTags(),0);
        assertFalse(boots.deleteTag("waterproof"));
        assertFalse(boots.deleteTag("boots"));
        boots.addTag("comfy");
        boots.addTag("black");
        boots.addTag("leather");
        assertEquals(boots.getNumOfTags(),3);
        assertFalse(boots.deleteTag("rain"));
    }

    @Test
    public void testGetName() {
        Clothes tee = new Tops("BAND shirt");
        Clothes jeans = new Bottoms("mom jeans");
        Clothes dress = new OnePiece("Sparkly Dress");
        assertEquals(tee.getName(), "band shirt");
        assertEquals(jeans.getName(), "mom jeans");
        assertEquals(dress.getName(), "sparkly dress");
    }
}