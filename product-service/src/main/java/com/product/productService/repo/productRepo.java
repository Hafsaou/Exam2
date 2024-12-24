package com.product.productService.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoCollection;
import com.product.productService.model.product;
@Repository
public interface productRepo   extends MongoRepository<product,String> {
}
