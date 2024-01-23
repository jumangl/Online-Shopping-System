import org.junit.Test;

import static org.junit.Assert.*;

public class ElectronicsTest {

    @Test
    public void testGetBrand() {
        Electronics electronics = new Electronics();
        electronics.setBrand("Bosh");
        assertEquals("Bosh", electronics.getBrand());

    }

    @Test
    public void testSetBrand() {
        Electronics electronics = new Electronics();
        electronics.setBrand("Bosh");
        assertEquals("Bosh", electronics.getBrand());
    }

    @Test
    public void testGetWarrantyPeriod() {
        Electronics electronics = new Electronics();
        electronics.setWarrantyPeriod(12.0);
        assertEquals(12.0, electronics.getWarrantyPeriod());
    }

    @Test
    public void setWarrantyPeriod() {
        Electronics electronics = new Electronics();
        electronics.setWarrantyPeriod(12);
        assertEquals(12, electronics.getWarrantyPeriod());
    }



    @Test
    public void cloneElectronics() {
    }
}