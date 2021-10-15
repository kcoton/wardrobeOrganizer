package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {

    @Test
    void testDifferentConstructors() {
        Clothes tee = new Tops("BAND shirt");
        Clothes tee2 = new Tops("white shirt");
        Clothes jeans = new Bottoms("mom jeans");
        Clothes dress = new OnePiece("Sparkly Dress");
        Clothes romper = new OnePiece("cute romper");
        Clothes romper2 = new OnePiece("cute romper2");
        Clothes boots = new Shoes("DOC MARTENS");
        Clothes mittens = new Accessories("fuzzy white mitts");

        assertEquals(tee.getTotalNumOfClothes(),8);
        assertEquals(tee.getNumOfClothes(),2);
        assertEquals(tee2.getNumOfClothes(),2);
        assertEquals(jeans.getNumOfClothes(),1);
        assertEquals(dress.getNumOfClothes(),3);
        assertEquals(romper.getNumOfClothes(),3);
        assertEquals(romper2.getNumOfClothes(),3);
        assertEquals(boots.getNumOfClothes(),1);
        assertEquals(mittens.getNumOfClothes(),1);
    }
}