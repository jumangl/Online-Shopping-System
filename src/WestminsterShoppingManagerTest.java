import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;



import static org.junit.Assert.*;

public class WestminsterShoppingManagerTest {
    private WestminsterShoppingManager ans;
    private Scanner mockScanner;



    @Test
    public void displayMenu() {
        String input = "1";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);






    }

    @Test
    public void addProduct() {
    }

    @Test
    public void removeProduct() {
    }

    @Test
    public void totCost() {
    }

    @Test
    public void getClothingCount() {

    }

    @Test
    public void getElectronicsCount() {
    }
}