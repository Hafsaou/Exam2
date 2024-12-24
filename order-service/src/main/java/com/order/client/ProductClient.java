package com.order.client;

import com.order.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@FeignClient(name = "order-service", url = "http://localhost:8080/product")
public interface ProductClient {

    @Retry(name = "productService")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetAllProducts")
    @GetMapping("/all")
   List<Product> getAllProducts();

    default boolean fallbackGetAllProducts(String code, Integer quantity, Throwable throwable) {
        System.out.println("Circuit breaker activated: " + throwable.getMessage());
        return false;
    }
}
