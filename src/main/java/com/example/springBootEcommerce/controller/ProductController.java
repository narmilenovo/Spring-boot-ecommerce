package com.example.springBootEcommerce.controller;

import com.example.springBootEcommerce.dto.Purchase;
import com.example.springBootEcommerce.dto.PurchaseResponse;
import com.example.springBootEcommerce.entity.Product;
import com.example.springBootEcommerce.service.CheckoutService;
import com.example.springBootEcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CheckoutService checkoutService;


    @PostMapping("/product")
    public Product save(@RequestBody Product product)
    {
        return productService.save(product);
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder (@RequestBody Purchase purchase){
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
       return purchaseResponse;
    }


}
