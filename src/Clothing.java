public class Clothing extends Product{ //Extends the Product class to Clothing
    private String size;  //Initialize the Size
    private String color; //Initialize the color

    //Create a constructor for the Clothing Product
    public Clothing(String productID, String ProductName, int availableCount, double price, String size, String color){
        this.setProductID(productID);
        this.setProductName(ProductName);
        this.setAvailableCount(availableCount);
        this.setPrice(price);
        this.size = size;
        this.color = color;
    }

    public Clothing(){
    }

    public String getSize() {
        return size;
    } //Getter and Setter to get size

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    } //Getter and Setter to get the color

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString(){ //Return the String representation of product to String

        return super.toString() +
                "Size: " + this.getSize() + "\n" +
                "Color: " + this.getColor() + "\n";

    }

    public Clothing cloneClothing(){ //Create a clone of the current clothing object
        Clothing clonedClothing = new Clothing();// Create a new clothing object to hold the cloned data
        //Set attributes of the cloned clothing to match the original object
        clonedClothing.setProductID(this.getProductID()); //
        clonedClothing.setProductName(this.getProductName());
        clonedClothing.setAvailableCount(this.getAvailableCount());
        clonedClothing.setPrice(this.getPrice());
        clonedClothing.setSize(this.getSize());
        clonedClothing.setColor(this.getColor());
        clonedClothing.setQuantity(this.getQuantity());

        return clonedClothing; //Returned the cloned clothing object
    }


}
