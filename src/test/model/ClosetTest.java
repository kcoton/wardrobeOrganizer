package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosetTest {
    private Closet closet;
    private Clothes shirt;

    @BeforeEach
    public void runBefore() {
        closet = new Closet();
        closet.setName("Kiara's Closet");
        shirt = new Tops("plain tee");
    }

    @Test
    public void testAddClothes() {
        assertEquals(closet.getTotalNumOfClothes(),0);
        closet.addClothes(shirt);
        assertEquals(closet.getTotalNumOfClothes(),1);
        assertEquals(closet.getNumOfTops(),1);

        Clothes tee = new Tops("plain White TEE");
        Clothes boots = new Shoes("doc martens");
        Clothes mittens = new Accessories("fuzzy gloves");
        Clothes mittens2 = new Accessories("soft gloves");
        Clothes jeans = new Bottoms("mom jeans");
        Clothes dress = new OnePiece("black dress");
        Clothes dress2 = new OnePiece("ball gown");
        Clothes dress3 = new OnePiece("prom dress");

        closet.addClothes(tee);
        closet.addClothes(boots);
        closet.addClothes(mittens);
        closet.addClothes(mittens2);
        closet.addClothes(jeans);
        closet.addClothes(dress);
        closet.addClothes(dress2);
        closet.addClothes(dress3);

        assertEquals(closet.getNumOfTops(), 2);
        assertEquals(closet.getNumOfShoes(), 1);
        assertEquals(closet.getNumOfAccessories(), 2);
        assertEquals(closet.getNumOfBottoms(), 1);
        assertEquals(closet.getNumOfOnePieces(), 3);
        assertEquals(closet.getTotalNumOfClothes(), 9);
    }

    @Test
    public void testGetCloset() {
        assertEquals(closet.getTotalNumOfClothes(),0);
        Clothes dress = new OnePiece("black dress");
        Clothes dress2 = new OnePiece("ball gown");
        closet.addClothes(shirt);
        closet.addClothes(dress);
        closet.addClothes(dress2);
        assertEquals(closet.getTotalNumOfClothes(),3);
        List<Clothes> list = closet.getCloset();
        assertEquals(list.get(0).getName(),"plain tee");
        assertEquals(list.get(1).getName(),"black dress");
        assertEquals(list.get(2).getName(),"ball gown");
    }
}
