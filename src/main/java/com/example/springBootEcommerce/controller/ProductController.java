package com.example.springBootEcommerce.controller;

import com.example.springBootEcommerce.entity.Product;
import com.example.springBootEcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/product")
    public Product save(@RequestBody Product product)
    {
        return productService.save(product);
    }
}
