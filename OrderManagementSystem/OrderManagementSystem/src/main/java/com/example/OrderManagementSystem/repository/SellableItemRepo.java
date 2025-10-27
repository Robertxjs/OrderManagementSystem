package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.model.Service;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SellableItemRepo {
    private final List<SellableItem> sellableItems = new ArrayList<>(Arrays.asList(
            new Product("P001", "Laptop", 1200.00),
            new Product("P002", "Smartphone", 800.00),
            new Product("P003", "Monitor", 200.00),
            new Service("S001", "Web Hosting", "Active"),
            new Service("S002", "Technical Support", "Down")
    ));

    public List<SellableItem> findAll() {
        return sellableItems;
    }
}
