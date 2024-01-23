import java.util.ArrayList;
import java.util.*;

public class ShoppingCart {
    private List<Product> List;

    public ShoppingCart(){
        List = new ArrayList<>();
    }

    public void addProduct(Product product){
        List.add(product);
    }

    public void removeProduct(Product product){
        List.remove(product);
    }

    public double calTotalCost(){
        double TotCost = 0.0;
        for (Product product: List){
            TotCost = TotCost + product.getPrice();
        }

        return TotCost;
    }

}
