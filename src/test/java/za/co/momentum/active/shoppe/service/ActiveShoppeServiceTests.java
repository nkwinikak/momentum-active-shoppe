package za.co.momentum.active.shoppe.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.momentum.active.shoppe.entity.Customer;
import za.co.momentum.active.shoppe.entity.Product;
import za.co.momentum.active.shoppe.repository.CustomerRepo;
import za.co.momentum.active.shoppe.repository.ProductRepo;
import za.co.momentum.active.shoppe.service.impl.ActiveShoppeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ActiveShoppeServiceTests {

    @InjectMocks
    ActiveShoppeServiceImpl activeShoppeService;

    @Mock
    ProductRepo productRepo;

    @Mock
    CustomerRepo customerRepo;

    @Test
    public void getAllProducts(){
        //Set up
        List<Product> productList = new ArrayList<>();
        Product p = new Product();
        p.setCode("PP");
        p.setCostPoints(12);
        p.setName("Puma");
        p.setId(1);
        productList.add(p);


        when(productRepo.findAll()).thenReturn(productList);

        List<Product> actualList = activeShoppeService.getAllProductsInStore();
        assertEquals(1,actualList.size());
    }

    @Test
    public void purchaseProduct(){
        Product p = new Product();
        p.setCode("PP");
        p.setCostPoints(12);
        p.setName("Puma");
        p.setId(1);

        Customer cust = new Customer();
        cust.setAvailablePoints(15);
        cust.setId(1);
        cust.setName("Kulani Desmond Nkwinika");

        when(productRepo.findById(1)).thenReturn(java.util.Optional.of(p));

        when(customerRepo.findById(1)).thenReturn(java.util.Optional.of(cust));

        String result = activeShoppeService.purchaseProduct(1,1);
        assertEquals("OK!",result);
    }

    @Test
    public void purchaseProductWithInsufficientFunds(){
        Product p = new Product();
        p.setCode("PP");
        p.setCostPoints(12);
        p.setName("Puma");
        p.setId(1);

        Customer cust = new Customer();
        cust.setAvailablePoints(9);
        cust.setId(1);
        cust.setName("Kulani Desmond Nkwinika");

        when(productRepo.findById(1)).thenReturn(java.util.Optional.of(p));

        when(customerRepo.findById(1)).thenReturn(java.util.Optional.of(cust));

        String result = activeShoppeService.purchaseProduct(1,1);
        assertEquals("Insufficient Active Point to purchase product",result);
    }
}
