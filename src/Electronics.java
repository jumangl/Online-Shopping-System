public class Electronics extends Product{
    private String brand; //Initialize the brand
    private double warrantyPeriod; //Initialize the warranty period

    //Create a constructor for Electronics project
    public Electronics(String productID, String ProductName, int availableCount, double price, String brand, double warrantyPeriod){
        this.setProductID(productID);
        this.setProductName(ProductName);
        this.setAvailableCount(availableCount);
        this.setPrice(price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public Electronics(){

    }

    public String getBrand() {
        return brand;
    } //Getter and Setter to get the brand

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getWarrantyPeriod() {
        return warrantyPeriod;
    }  //Getter and setter to get the warranty period

    public void setWarrantyPeriod(double warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }


    @Override
    public String toString(){ //Return a string representation of brand and warranty together with other details in super class
        return super.toString() +
                "Brand: " + this.getBrand() + "\n" +
                "Warranty Period: " + this.getWarrantyPeriod() + "\n";

    }

    public Electronics cloneElectronics(){
        Electronics clonedElectronics = new Electronics(); //Create a new Clone Object for Electronics, ensuring objects are independent each other
        //Set attributes of the cloned electronics to match the original product
        clonedElectronics.setProductID(this.getProductID());
        clonedElectronics.setProductName(this.getProductName());
        clonedElectronics.setAvailableCount(this.getAvailableCount());
        clonedElectronics.setPrice(this.getPrice());
        clonedElectronics.setBrand(this.getBrand());
        clonedElectronics.setWarrantyPeriod(this.getWarrantyPeriod());
        clonedElectronics.setQuantity(this.getQuantity());

        return clonedElectronics;
    }

}
