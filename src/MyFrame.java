import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MyFrame extends JFrame implements ActionListener {
    JPanel inputPanel, mainPanel, buttonPanel; //Initialize the inputPanel, mainPanel, buttonPanel
    JLabel label,label2; //Initialize labels
    JButton button, addButton;  // Initialize the add to cart button,
    JComboBox<String> comboBox; //Initialize the drop-down list
    JTable table; //Initialize the JTable

    JTextArea productInfoTextArea;   //Initialize the textArea to represent the productInfo
    ArrayList<Product> shoppingCart = new ArrayList<>(); //Arraylist for shopping cart
    ShoppingCartTableModel cartTableModel;  //Table for viewing the added products to the cart

    private HashMap<String, String> loginInfo;  //Hashmap to store the loginInfo
    private boolean isLoggedIn = false;  //Initialize the program which the user has not logged in






    public MyFrame(ArrayList<Product> productList) { // Use MyFrame to open the User Interface for the Customer
        inputPanel = new JPanel(new FlowLayout()); //Panel for the user to Select Category and the combo box
        label = new JLabel("Select Product Category");
        String[] choices = {"All", "Clothing", "Electronics"};
        comboBox = new JComboBox<>(choices);
        comboBox.addActionListener(this);  //Activate the DropDown List according to the users choice
        button = new JButton();    // Button to view the shopping cart
        button.setText("Shopping Cart");

        inputPanel.add(label); //Add the label to the input panel
        inputPanel.add(comboBox); //Add the comboBox to the input panel
        inputPanel.add(button); //Add the shopping cart  button to the input panel

        cartTableModel = new ShoppingCartTableModel();  //Implement the shopping cart

        BookTableModel model = new BookTableModel(productList); //Model for viewing the information entered by the manager
        model.sortByFirstCharId();  //Sort the table by the first two characters of the id
        table = new JTable(model);  //Add a J table to clearly identify the information properly
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);   //Scrollable View for the data
        scrollPane.setColumnHeaderView(null); //Set the header view of the scroll pane is removed

        label2 = new JLabel("Product Details");  //View part for the product details
        productInfoTextArea = new JTextArea(10,20);
        JScrollPane infoScrollPane = new JScrollPane(productInfoTextArea);


        buttonPanel = new JPanel(new FlowLayout()); //button panel for the lower button "Add to shopping cart"
        addButton = new JButton("Add to Shopping Cart");
        buttonPanel.add(addButton);   //Add the button to the button panel
        addButton.addActionListener(this);  //Add the actionListener





        mainPanel = new JPanel(new BorderLayout()); //Introduce the main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH); //Add the input panel to the top
        mainPanel.add(scrollPane, BorderLayout.CENTER);  //Add the scroll Pane to the top
        mainPanel.add(label2, BorderLayout.SOUTH);   // Add the Product Details part to the lower part
        mainPanel.add(infoScrollPane, BorderLayout.AFTER_LAST_LINE);


        this.add(mainPanel);
        loginInfo = new HashMap<>(); //Initially saved Login Information for the user to store data.
        loginInfo.put("hello", "1234");

        ListSelectionModel selectionModel = table.getSelectionModel();  //List Selection model to select a row of table
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Allow only Single selection of row
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()){  //Handle the selected row
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1){
                        Product selectedProduct = model.getCurrentList().get(selectedRow);
                        String productInfo = getProductInfo(selectedProduct);
                        productInfoTextArea.setText(productInfo);

                    }
                }
            }
        });




        JPanel combinedPanel = new JPanel(new BorderLayout());  //Define a combined panel
        combinedPanel.add(mainPanel, BorderLayout.CENTER);  //add the main panel to the center
        combinedPanel.add(buttonPanel, BorderLayout.SOUTH); //add the button panel to the south
        this.add(combinedPanel);



        button.addActionListener(e -> {   //Action Listener for the Cart section
            JTable cartTable = new JTable(cartTableModel);  //Table introduced for J table
            JScrollPane cartScrollPane = new JScrollPane(cartTable);
            JTextArea textArea = new JTextArea(); //Textarea to display the data related to total display
            JScrollPane textAreaScrollPane = new JScrollPane(textArea);

            JPanel panel = new JPanel(new BorderLayout()); //Create a panel to add cart section and textarea

            panel.add(cartScrollPane, BorderLayout.CENTER); //Set the cart table to center
            panel.add(textAreaScrollPane,BorderLayout.SOUTH); // Text Area to the lower part

            double total = cartTableModel.calTotPrice();  //Set Calculate the total price
            double firstItemDiscount = cartTableModel.calculateFirstItemDiscount();  //Set the firstItemDiscount
            double discount = cartTableModel.categoryDiscount();  //Set the discount for the category
            double finalTotal = cartTableModel.finalTotal();  //Calculate the final total after discounts

            textArea.append("Total Price: " + total + "\n");
            textArea.append("First Purchase Discount (10%): " + firstItemDiscount + "\n");
            textArea.append("3 Items in Same Category Discount (20%): " + discount + "\n");
            textArea.append("---------------------------------------------------------------\n");
            textArea.append("Final Total: " + finalTotal + "\n");

            JFrame cartFrame = new JFrame("Shopping Cart");  //New cart Frame implemented
            cartFrame.add(panel);  //Add the panel to the cart
            cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //Close only the cart without setting default close operation
            cartFrame.setSize(600, 400);
            cartFrame.setVisible(true);
        });
    }

    private String getProductInfo(Product product){
        StringBuilder info = new StringBuilder(); //Introduce mutable as the data changed and it's mutable.
        info.append("Product ID: ").append(product.getProductID()).append(("\n"));
        info.append("Name: ").append(product.getProductName()).append("\n");
        info.append("Category: ").append(product instanceof Clothing ? "Clothing" : "Electronics").append("\n"); //If category belongs to the category, it displays accordingly
        info.append("Price: ").append(product.getPrice()).append("\n");
        if (product instanceof Clothing clothing){
            info.append("Color: ").append(clothing.getColor()).append("\n");
            info.append("Size: ").append(clothing.getSize()).append("\n");
            info.append("Clothing Count: ").append(clothing.getAvailableCount()).append("\n");
        } else if (product instanceof Electronics electronics){
            info.append("Brand: ").append(electronics.getBrand()).append("\n");
            info.append("Warranty Period: ").append(electronics.getWarrantyPeriod()).append("\n");
            info.append("Electronic Count: ").append(electronics.getAvailableCount()).append("\n");
        }

        return info.toString(); //Return the information related to the data selected by the user
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comboBox){  //Dropdown List
            int selected = comboBox.getSelectedIndex();
            if (selected == 0){  //Sort the table by the ID
                ((BookTableModel) table.getModel()).sortByFirstCharId();
            }

            else if (selected == 1){  //Sort the table which are having Clothing products only
                ((BookTableModel) table.getModel()).sortByCategoryClothing();
            }

            else if (selected == 2){ //Sort the table which are having electronics only
                ((BookTableModel) table.getModel()).sortByCategoryElectronics();
            }
        }

        if (e.getSource() == addButton){  //Activate the Add to shopping cart button
            int selectedRow = table.getSelectedRow(); //Get the Selected row by the customer
            if (selectedRow != -1){
                //Casting to BookTableModel and invokes the currentList method
                Product selectedProduct = ((BookTableModel) table.getModel()).getCurrentList().get(selectedRow);
                int quantity = promptForQuantity(); //Use the login details and the quantity needed after the correct login
                if (quantity > 0 && selectedProduct.getAvailableCount() >= quantity){
                    selectedProduct.setQuantity(quantity);
                    shoppingCart.add(selectedProduct); //Add the selected product details to the shopping carts data table
                    cartTableModel.addToCart(selectedProduct, quantity);
                    selectedProduct.setAvailableCount(selectedProduct.getAvailableCount()- quantity); //Product Quantity in the original table will be deducted

                }else{
                    JOptionPane.showMessageDialog(this, "Invalid quantity Entered or Insufficient stock!"); //Show notification if quantity is wrong
                }
            } else{
                JOptionPane.showMessageDialog(this, "Please select a product");
            }
        }


    }

    private int promptForQuantity() {
        while (!isLoggedIn) { //Continued as Not logged in
            String username = JOptionPane.showInputDialog(this, "Enter Username: "); //Enter  Username
            String password = JOptionPane.showInputDialog(this, "Enter Password: "); //Enter Password
            if (checkLogin(username, password)) {
                isLoggedIn = true;  //pass login is correct

            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
                //If invalid credentials, make it as wrong Login
                return 0;
            }
        }
        String input = JOptionPane.showInputDialog(this, "Enter Quantity: ");
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private boolean checkLogin(String username, String password){
        return loginInfo.containsKey(username) && loginInfo.get(username).equals(password);
        //Check whether the Login Credentials is correct or not.
    }

    static class BookTableModel extends AbstractTableModel{ //Introduce the Table which user see the products in the table
        private final String[] columnNames = {"ProductID", "Name", "Category", "Price", "Info"}; //Initialize the column Names
        private final ArrayList<Product> originalList;
        private ArrayList<Product> currentList;


        public BookTableModel(ArrayList<Product> pList){
            originalList = new ArrayList<>(pList);
            currentList = new ArrayList<>(pList);

        }



        public void sortByCategoryClothing(){
            ArrayList<Product> clothingList = new ArrayList<>(); //Sort the list by Clothing when user search in the combo box

            for (Product product : originalList){
                if (product instanceof Clothing){
                    clothingList.add(product);
                }
            }
            currentList = clothingList;
            fireTableDataChanged();

        }

        public void sortByCategoryElectronics(){ //Sort the List by Electronics when user clicks electronics in the combo box
            ArrayList<Product> electronicsList = new ArrayList<>();

            for (Product product : originalList){
                if (product instanceof Electronics){
                    electronicsList.add(product);
                }
            }
            currentList = electronicsList;
            fireTableDataChanged();
        }
        public void sortByFirstCharId(){
            Comparator<Product> firstCharComparator = Comparator.comparing(p -> p.getProductID().substring(0,2));
            //used the comparator for comparing the first 2 characters of the product id
            //Lambda Expression is used to retrieve the productID from each product.
            //
            originalList.sort(firstCharComparator); //Sort the original List according to the order.
            currentList = new ArrayList<>(originalList);


            fireTableDataChanged();

        }

        @Override
        public int getRowCount() {
            return currentList.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Product product = currentList.get(rowIndex);

            if (columnIndex == 0) { //ProductID displayed in 0th column index

                return product.getProductID();
            }
            else if (columnIndex == 1){ //Product Name is displayed in column2
                return product.getProductName();
            }

            else if (columnIndex == 2){
                if (product instanceof Clothing){
                    return "Clothing Product";
                }
                else{
                    return "Electronics Product";
                }
            }

            else if (columnIndex == 3){
                return product.getPrice();
            }

            else if (columnIndex == 4){
                if (product instanceof Clothing){
                    //Cast the product to the clothing and access the properties needed for the table
                    return ((Clothing) product).getColor() + "," + ((Clothing) product).getSize() + "," + ((Clothing) product).getAvailableCount() ;
                }
                else if (product instanceof Electronics){
                    return ((Electronics) product).getBrand() + "," +  ((Electronics) product).getWarrantyPeriod() + "," + ((Electronics) product).getAvailableCount();
                }
            }

            return product; //Return the product
        }

        public ArrayList<Product> getCurrentList() {
            return currentList;
        }

        public String getColumnName(int col){
            return columnNames[col];
        }
    }

    static class ShoppingCartTableModel extends AbstractTableModel{ //Table model for Shopping cart
        private final String[] columnNames = {"Product Details", "Quantity", "Price"}; //Initialize the columnNames
        private final ArrayList<Product> cartItems;
        private final HashMap<String, Integer> purchaseCount = new HashMap<>();



        public ShoppingCartTableModel(){
            cartItems = new ArrayList<>();
        }

        public void addToCart(Product product, int quantity){ //Add to cart the products
            boolean alreadyInCart = false;
            for (Product cartProduct : cartItems){
                if (cartProduct.getProductID().equals(product.getProductID())){
                    cartProduct.setQuantity(cartProduct.getQuantity() + quantity); //Add the Quantity
                    alreadyInCart = true;
                    break;
                }
            }

            if (!alreadyInCart){
                Product clonedProduct = null;
                if(product instanceof Clothing){
                    clonedProduct = ((Clothing) product).cloneClothing();
                } else if (product instanceof Electronics){
                    clonedProduct = ((Electronics) product).cloneElectronics();
                }

                if (clonedProduct != null){
                    clonedProduct.setQuantity(quantity); //Set the quantity
                    cartItems.add(clonedProduct);
                }

            }
            //Calculate the total quantity of the added product in the cart
            int totalQuantity = 0;
            double totalPrice = 0;

            for (Product p: cartItems){
                if (p.getClass() == product.getClass()){
                    totalQuantity += p.getQuantity();
                    totalPrice += p.getPrice() * p.getQuantity();

                }
            }
            fireTableDataChanged();
        }

        public double calTotPrice(){ //Calculate the total price
            double totalPrice = 0;
            for(Product product: cartItems){
                double price = product.getPrice() * product.getQuantity();
                totalPrice += price;
            }
            return totalPrice;
        }

        public double calculateFirstItemDiscount(){ //Calculate the first discount price
            double totalPrice = calTotPrice();
            boolean firstItemFound = false;
            double firstItemPrice = 0.0;

            return Math.round((totalPrice * 0.1) * 100.0/100.0);
        }

        public double categoryDiscount() {  //Calculate the category discount of 20%
            HashMap<String, Integer> categoryCount= new HashMap<>();
            String category = "";
            for (Product product : cartItems){
                if (product instanceof Clothing){
                    category = "Clothing";
                } else if (product instanceof Electronics){
                    category = "Electronics";
                }
                if (categoryCount.containsKey(category)){
                    categoryCount.put(category, categoryCount.get(category) + product.getQuantity());
                } else{
                    categoryCount.put(category, product.getQuantity());
                }
            }

            // Map entry is used. Idea taken from 'https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map'
            for (Map.Entry<String, Integer> entry : categoryCount.entrySet()){
                if (entry.getValue() >= 3){ //If product quantity for the same product is greater than 3 , discount applicable
                    return Math.round((calTotPrice() * 0.2) * 100.0/100.0);
                }
            }
            return 0.0;
        }

        public double finalTotal(){ //Calculate Final Total price
            double total = calTotPrice() - calculateFirstItemDiscount() - categoryDiscount();
            return Math.round(total * 100.0)/100.0;
        }

        @Override
        public int getRowCount() {
            return cartItems.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col){
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Product product = cartItems.get(rowIndex);
            if (columnIndex == 0){ //Get product instance
                if (product instanceof Clothing){
                    return product.getProductID() + ",\n" + ((Clothing)  product).getProductName() + ",\n"  + ((Clothing) product).getSize() + ",\n" +  ((Clothing) product).getColor();
                } else if (product instanceof Electronics){
                    return product.getProductID() + ",\n" + ((Electronics) product).getWarrantyPeriod() + ",\n" + ((Electronics) product).getBrand();
                }
            }else if (columnIndex == 1){ //Add product quantity in the shopping cart
                return product.getQuantity();


            }else if (columnIndex == 2){ //Add price in shopping cart
                return product.getPrice() * product.getQuantity();
            }
            return product;
        }

    }
}



