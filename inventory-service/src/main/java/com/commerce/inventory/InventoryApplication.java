package com.commerce.inventory;

import com.commerce.inventory.model.Inventory;
import com.commerce.inventory.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class InventoryApplication {

    public static void main(String[] args) {

        SpringApplication.run(InventoryApplication.class, args);


    }
    @Bean
    public CommandLineRunner commandLineRunner(InventoryService inventoryService){
        System.out.println("In inventoryyyyyyyyyyyy");
        return (args) -> {
            inventoryService.saveInventory(
                    Inventory.builder()
                            .id(2)
                            .productId(1)
                            .price(12)
                            .quantity(12)
                            .build()

            );


        };

}}
