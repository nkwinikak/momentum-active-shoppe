package za.co.momentum.active.shoppe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.momentum.active.shoppe.entity.Customer;
import za.co.momentum.active.shoppe.entity.Product;

import za.co.momentum.active.shoppe.repository.CustomerRepo;
import za.co.momentum.active.shoppe.repository.ProductRepo;
import za.co.momentum.active.shoppe.service.IActiveShoppeService;

import java.util.List;
import java.util.Optional;

@Component
public class ActiveShoppeServiceImpl implements IActiveShoppeService {

    private static final Logger LOG = LoggerFactory.getLogger(ActiveShoppeServiceImpl.class);

    private final static String INSUFFICIENT_FUNDS_MESSAGE = "Insufficient Active Point to purchase product";
    private final static String PRODUCT_DOES_NOT_EXIST = "Product does not exist";
    private final static String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist";
    private final static String PURCHASE_SUCCESSFUL = "Purchase Successful";

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public List<Product> getAllProductsInStore() {
        return productRepo.findAll();
    }

    @Override
    public String purchaseProduct(int customerId, int productId) {

        String method = "[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]: ";
        LOG.info("entering.... {}", method);

        Optional<Customer> customer = customerRepo.findById(customerId);

        if(!customer.isPresent()){
            LOG.warn(CUSTOMER_DOES_NOT_EXIST);
            return CUSTOMER_DOES_NOT_EXIST;
        }

        Optional<Product> product = productRepo.findById(productId);

        if(!product.isPresent()){
            LOG.warn(PRODUCT_DOES_NOT_EXIST);
            return PRODUCT_DOES_NOT_EXIST;
        }

        if(doesCustomerAffordProduct(customer.get(),product.get())){
            int newCustomerBalance = customer.get().getAvailablePoints() - product.get().getCostPoints();
            updateCustomerWithNewBalance(customer.get(),newCustomerBalance);
        }else{
            return INSUFFICIENT_FUNDS_MESSAGE;
        }
        return PURCHASE_SUCCESSFUL;
    }

    private void updateCustomerWithNewBalance(Customer customer, int newCustomerBalance) {
        customer.setAvailablePoints(newCustomerBalance);
        customerRepo.save(customer);
        LOG.info("Customer {} balance updated ",customer.getId());
    }

    private boolean doesCustomerAffordProduct(Customer cust, Product prod){

        return (cust.getAvailablePoints() - prod.getCostPoints() > 0) ? true : false;
    }
}
