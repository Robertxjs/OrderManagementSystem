package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.model.Service;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    public Optional<SellableItem> findById(String id) {
        return sellableItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    public SellableItem save(SellableItem item) {
        if (item.getId() == null) {
            int maxId = sellableItems.stream()
                    .map(SellableItem::getId)
                    .filter(id -> id != null && id.matches(".*\\d+"))
                    .mapToInt(id -> Integer.parseInt(id.substring(1)))
                    .max()
                    .orElse(0);
            String prefix = item instanceof Product ? "P" : "S";
            item.setId(prefix + String.format("%03d", maxId + 1));
        }

        for (int i = 0; i < sellableItems.size(); i++) {
            if (sellableItems.get(i).getId().equals(item.getId())) {
                sellableItems.set(i, item);
                return item;
            }
        }

        sellableItems.add(item);
        return item;
    }

    public boolean deleteById(String id) {
        return sellableItems.removeIf(item -> item.getId().equals(id));
    }
}
