package za.co.momentum.active.shoppe.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.momentum.active.shoppe.entity.Product;
import za.co.momentum.active.shoppe.model.ActiveShoppeProductResponse;
import za.co.momentum.active.shoppe.service.IActiveShoppeService;

import javax.annotation.security.PermitAll;
import java.util.List;

@Api("Momentum Active Shoppe Controller")
@RestController
public class ActiveShoppeController {

    private static final Logger LOG = LoggerFactory.getLogger(ActiveShoppeController.class);

    @Autowired
    private IActiveShoppeService activeShoppeService;

    @ApiOperation(value = "Retrieves all active shoppe products from database")
    @GetMapping(value="/getAllProducts",produces = MediaType.APPLICATION_JSON_VALUE)
    @PermitAll
    public ResponseEntity<ActiveShoppeProductResponse> getAllProducts(){
        String method = "[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]: ";
        LOG.info("entering.... {}", method);

        List<Product> products = activeShoppeService.getAllProductsInStore();
        ActiveShoppeProductResponse activeShoppeProductResponse = new ActiveShoppeProductResponse(products);
        return new ResponseEntity<>(activeShoppeProductResponse,HttpStatus.OK);
    }

    @ApiOperation(value = "purchases product for customer with enough active days points")
    @PostMapping("/purchaseProduct/{customerId}/{productId}")
    @PermitAll
    public ResponseEntity<String> purchaseProduct(@PathVariable int customerId, @PathVariable int productId ){
        String method = "[" + Thread.currentThread().getStackTrace()[1].getMethodName() + "]: ";
        LOG.info("entering.... {}", method);
       String response = activeShoppeService.purchaseProduct(customerId,productId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
