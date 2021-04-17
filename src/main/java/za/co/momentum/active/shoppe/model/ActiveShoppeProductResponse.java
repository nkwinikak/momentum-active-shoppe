package za.co.momentum.active.shoppe.model;

import lombok.Data;
import za.co.momentum.active.shoppe.entity.Product;
import java.util.List;

@Data
public class ActiveShoppeProductResponse {

    private List<Product> productList = null;

    public ActiveShoppeProductResponse(List<Product> productList){
        this.productList = productList;
    }

    public ActiveShoppeProductResponse(){}

}
