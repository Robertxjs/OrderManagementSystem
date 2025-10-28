package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.repository.SellableItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellableItemService {
    private final SellableItemRepo sellableItemRepo;

    @Autowired
    public SellableItemService(SellableItemRepo sellableItemRepo) {
        this.sellableItemRepo = sellableItemRepo;
    }

    public List<SellableItem> findAll() {
        return sellableItemRepo.findAll();
    }

    public Optional<SellableItem> findById(String id) {
        return sellableItemRepo.findById(id);
    }

    public SellableItem save(SellableItem item) {
        return sellableItemRepo.save(item);
    }

    public boolean deleteById(String id) {
        return sellableItemRepo.deleteById(id);
    }
}
