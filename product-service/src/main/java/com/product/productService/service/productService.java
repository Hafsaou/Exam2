package com.product.productService.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.product.productService.model.product;

import com.product.productService.repo.productRepo;

@Service
public class productService {
    @Autowired
    private productRepo productRepo;

    public List<product> getAllProducts() {
        return productRepo.findAll();
    }

    public product getProductById(String id) {
        return productRepo.findById(id).get();
    }

    public product addProduct(product product) {
        return productRepo.save(product);
    }

    public product updateProduct(product product) {
        return productRepo.save(product);
    }

    public void deleteProduct(String id) {
        productRepo.deleteById(id);
    }
}