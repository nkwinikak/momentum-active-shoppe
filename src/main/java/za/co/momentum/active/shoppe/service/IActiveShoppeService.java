package za.co.momentum.active.shoppe.service;

import za.co.momentum.active.shoppe.entity.Product;

import java.util.List;

public interface IActiveShoppeService {
    List<Product> getAllProductsInStore();
    String purchaseProduct(int customerId, int productId);
}
