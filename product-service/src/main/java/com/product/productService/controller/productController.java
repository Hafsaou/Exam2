package com.product.productService.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.product.productService.model.product;

import com.product.productService.service.productService;

@RestController
@RequestMapping("/product")

public class productController {
    @Autowired
    private productService productService;

    @GetMapping("/all")
    public List<product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public product addProduct(@RequestBody product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public product updateProduct(@RequestBody product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}