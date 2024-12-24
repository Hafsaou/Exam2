package com.commerce.inventory.controller;

import com.commerce.inventory.model.Inventory;
import com.commerce.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.Id;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8083")
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    // @Id
    // private String id;
   
    // private String productId;

    // private int quantityInStock; 

    // private int holdQuantity;
    @Autowired
    InventoryService inventoryService;

    @PostMapping("/add")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }


    @GetMapping("/get")
    public List<Inventory> getAllInventory() {
        return inventoryService.getInventories();
    }

    @PutMapping("/update")
    public Inventory updateInventory(@RequestBody Inventory inventory) {
        return inventoryService.updateInventory(inventory);
    }


}
