import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.util.*;


public class WestminsterShoppingManager implements ShoppingManager {
    private final HashMap<String, Product> List;  //Hashmap to store product ID and other values
    private int clothingCount;  // initialize clothingCount
    private int electronicsCount; //initialize electronicsCount


    private ArrayList<Product> removeList;
    private static final int max_Products = 50;

    private int product_Count = 0; //Use th product count starting from 0




    public WestminsterShoppingManager(){
        List = new HashMap<>();
        clothingCount = 0;
        electronicsCount = 0;

    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);


        while(true){
            try{

                int choice;   //Get the choice from the customer
                System.out.println("1- Add Product");
                System.out.println("2- Remove product");
                System.out.println("3- Print products");
                System.out.println("4- Save in a File");
                System.out.println("5- View User Panel");
                System.out.println("Choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: //Add the product entered by the manager
                        product_Count = product_Count + 1;  //Increment the count
                        if (product_Count == 50) { //If product count is 50, then stocks cannot be added.
                            System.out.println("Couldn't add stock!!!!");
                            break;
                        }

                        System.out.println("Enter 'C' for Clothing or 'E' for Electronics: ");
                        char selection = sc.next().charAt(0); //Get a selection from the manager whether he enters clothing or electronic product


                        if (selection == 'E') { // Getting parameters related to electronics

                            System.out.println("Enter Electronic ProductID: "); //getting electronic productID
                            String id = sc.next();
                            sc.nextLine();


                            System.out.println("Enter Electronic ProductName: "); //getting electronic product name
                            String name = sc.nextLine();

                            System.out.println("Enter Electronic Available Count: "); // getting available count
                            int availableCount = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Enter Price: ");  //Get price from the manager
                            double price = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Enter Brand: "); //Get the brand
                            String brand = sc.nextLine();


                            System.out.println("Enter Warranty Period: ");  //get the warranty period
                            double warranty = sc.nextDouble();
                            sc.nextLine();

                            Electronics new1 = new Electronics(id, name, availableCount, price, brand, warranty); //Instantiate the electronics product
                            addProduct(new1);
                        }

                        if (selection == 'C'){  // Getting Clothing related parameters
                            System.out.println("Enter Clothing ProductID: ");  //Getting clothing product ID

                            String Id = sc.next();
                            sc.nextLine();


                            System.out.println("Enter Clothing ProductName: "); //Getting Clothing product name
                            String cName = sc.nextLine();

                            System.out.println("Enter Available Count: "); //Getting available count
                            int availableCount = sc.nextInt();
                            sc.nextLine();

                            System.out.println("Enter Price: "); //Getting Price
                            double price = sc.nextDouble();
                            sc.nextLine();

                            System.out.println("Enter Size: "); //Enter Size of Clothing
                            String size = sc.next();


                            System.out.println("Enter Color: "); //Enter the color of the clothing
                            String color = sc.next();
                            sc.nextLine();



                            Clothing newCloth = new Clothing(Id, cName, availableCount,price,size,color); //Instantiate the clothing product
                            addProduct(newCloth);

                        }

                        break;

                    case 2:
                        System.out.println("Enter the ID Number: "); // Enter ID Number to remove clothing
                        String id = sc.next();
                        sc.nextLine();

                        System.out.println("Enter Product Category: ");
                        char type = sc.next().charAt(0);


                        if (type == 'C'){  //Remove clothing related to the id
                            Clothing clothRemove = new Clothing();
                            clothRemove.setProductID(id);
                            removeProduct(clothRemove);
                        }

                        else if (type == 'E'){ //Remove electronics related to electronics.
                            Electronics electronicsRemove = new Electronics();
                            electronicsRemove.setProductID(id);
                            removeProduct(electronicsRemove);
                        }
                    break;

                    case 3:
                        ArrayList<String> productList = new ArrayList<>(List.keySet()); //Print products in the console
                        Collections.sort(productList); //Sorting the arraylist. Reference: https://stackoverflow.com/questions/16252269/how-to-sort-a-list-arraylist;

                        for (String productId: productList){  // select product id from the product list
                            Product product = List.get(productId);

                            if (product instanceof Clothing clothing){  // Get the Clothing instance
                                System.out.println("Clothing Product");
                                System.out.println("ID: " + clothing.getProductID());
                                System.out.println("Name: " + clothing.getProductName());
                                System.out.println("Available Count: " + clothing.getAvailableCount());
                                System.out.println("Price: " + clothing.getPrice());
                                System.out.println("Size: " + clothing.getSize());
                                System.out.println("Color: " + clothing.getColor());
                                System.out.println("-----------------------------");
                            }

                            else if (product instanceof Electronics electronics){  // Get the Electronics Instance
                                System.out.println("Electronics Product");
                                System.out.println("ID: " + electronics.getProductID());
                                System.out.println("Name: " + electronics.getProductName());
                                System.out.println("Available Count: " + electronics.getAvailableCount());
                                System.out.println("Price: " + electronics.getPrice());
                                System.out.println("Brand: " + electronics.getBrand());
                                System.out.println("Warranty Period: " + electronics.getWarrantyPeriod());
                                System.out.println("-----------------------------");

                            }
                        }
                        break;

                    case 4:
                        try{
                            BufferedWriter Writer = new BufferedWriter(new FileWriter("products.txt", true));
                            //Write the file in the console using BufferedWriter
                            for (Product product: List.values()){  //Get the values from the List
                                if (product instanceof Clothing clothingFile){  //Get the instance of Clothing product
                                    Writer.write("Clothing Product" + "\n");
                                    Writer.write("ID: " + clothingFile.getProductID() + "\n");
                                    Writer.write("Name: " + clothingFile.getProductName() + "\n");
                                    Writer.write("Available Count: " + clothingFile.getAvailableCount() + "\n");
                                    Writer.write("Price: " + clothingFile.getPrice() + "\n");
                                    Writer.write("Size: " + clothingFile.getSize() + "\n");
                                    Writer.write("Color: " + clothingFile.getColor() + "\n");
                                    Writer.write("-----------------------------\n");
                                }

                                else if (product instanceof Electronics electronics){ // Get the instance of Electronics product
                                    Writer.write("Electronics Product" + "\n");
                                    Writer.write("ID: " + electronics.getProductID() + "\n");
                                    Writer.write("Name: " + electronics.getProductName() + "\n");
                                    Writer.write("Available Count: " + electronics.getAvailableCount() + "\n");
                                    Writer.write("Price: " + electronics.getPrice() + "\n");
                                    Writer.write("Brand: " + electronics.getBrand() + "\n");
                                    Writer.write("Warranty Period: " + electronics.getWarrantyPeriod() + "\n");
                                    Writer.write("-----------------------------\n");

                                }

                            }
                            Writer.close(); //Closing the writer file.
                            System.out.println("Product Details saved to the File!!");





                        }catch (IOException e){  //Handle IO Exception
                            e.getStackTrace();
                            System.out.println("Error in saving product details: " + e.getMessage());
                        }

                        break;

                    case 5: // Introduce the GUI
                        MyFrame frame = new MyFrame(new ArrayList<>(List.values()));
                        frame.setVisible(true);
                        frame.setTitle("Westminster Shopping Manager");
                        frame.setSize(400,300);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        break;



                    default:  //Give invalid choice if user enters anything incorrectly
                        System.out.println("Invalid Choice!!");
                        break;

                }


            }catch(InputMismatchException e){
                System.out.println("An error occurred! Please Try Again");
                sc.nextLine();
            }


        }
    }

    @Override
    public void addProduct(Product product) {  //Override the add product from the Interface

        List.put(product.getProductID(), product);//Add the product to the list
        if (product instanceof Clothing){ //Get count for clothing
            clothingCount++;
        }else if(product instanceof Electronics){ //Get the count for electronics
            electronicsCount++;
        }
    }

    @Override
    public void removeProduct(Product productToRemove) { //Remove the product
        String idToRemove = productToRemove.getProductID();  //Get the Product ID to remove
        if (List.containsKey(idToRemove)){  //Get the ID to remove
            List.remove(idToRemove);  // Remove the product
            System.out.println("Deleted Successfully");
        }else{
            System.out.println("Product not found with ID: " + idToRemove);
        }
    }

    @Override
    public double TotCost() {
        return 0;
    }  //Override the total cost.

    public int getClothingCount() {
        return clothingCount;
    }

    public int getElectronicsCount() {
        return electronicsCount;
    }
}



