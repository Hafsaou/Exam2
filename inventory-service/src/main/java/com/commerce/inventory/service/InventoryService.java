package com.commerce.inventory.service;

import com.commerce.inventory.model.Inventory;
import com.commerce.inventory.repo.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepo inventoryRepo;

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    public List<Inventory> getInventories() {
        return inventoryRepo.findAll();
    }

    public Inventory getInventoryById(String id) {
        return inventoryRepo.findById(id).orElse(null);
    }

    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    public String deleteInventory(String id) {
        inventoryRepo.deleteById(id);
        return "Inventory removed !! " + id;
    }
}
