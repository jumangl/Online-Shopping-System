import org.junit.Test;

import static org.junit.Assert.*;

public class ClothingTest {

    @Test
    public void testGetSize() {
        Clothing clothing = new Clothing();
        clothing.setSize("M");
        assertEquals("M", clothing.getSize());
    }

    @Test
    public void testSetSize() {
        Clothing clothing = new Clothing();
        clothing.setSize("L");
        assertEquals("L", clothing.getSize());
    }

    @Test
    public void testGetColor() {
        Clothing clothing = new Clothing();
        clothing.setColor("Red");
        assertEquals("Red", clothing.getColor());

    }

    @Test
    public void testSetColor() {
        Clothing clothing = new Clothing();
        clothing.setColor("Red");
        assertEquals("Red", clothing.getColor());
    }



    @Test
    public void testCloneClothing() {
        Clothing originalCLothing = new Clothing("C001", "Shirt", 10, 29.99, "L", "Blue");
        Clothing clonedClothing = originalCLothing.cloneClothing();
        assertEquals(originalCLothing, clonedClothing);
    }
}