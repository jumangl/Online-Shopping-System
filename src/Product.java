import javax.swing.table.AbstractTableModel;

public abstract class Product {
    private String productID;  //Initialize the product ID
    private String productName; //Initialize the product Name
    private int availableCount; //Initialize the available count
    private double price;  //Initialize the product price
    private double originalPrice; //Get the original price of the product

    private int quantity; // Get the quantity of a product

    public String getProductID() {
        return productID;
    } //Create a Getter and setter for product ID

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }  //Create a Getter and Setter for Product Name

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAvailableCount() {
        return availableCount;
    } //Create a getter and setter for available count

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public double getPrice() {
        return price;
    } //Create a getter and setter for price

    public void setPrice(double price) {
        this.price = price;
    }

    //Return the String representation of the product which includes product id, product name, available count and price
    @Override
    public String toString(){
        return (
                "Product ID: " + this.getProductID() + "\n" +
                "Product Name: " + this.getProductName() + "\n" +
                "Available Count: " + this.getAvailableCount() + "\n" +
                "Price: " + this.getPrice() + "\n"

                );

    }

    public int getQuantity(){
        return quantity;
    } //Create getter and setter for the quantity

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
}
