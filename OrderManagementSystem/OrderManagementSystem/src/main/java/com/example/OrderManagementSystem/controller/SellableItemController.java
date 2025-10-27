package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.SellableItem;
import com.example.OrderManagementSystem.service.SellableItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sellable-items")
public class SellableItemController {
    private final SellableItemService sellableItemService;

    @Autowired
    public SellableItemController(SellableItemService sellableItemService) {
        this.sellableItemService = sellableItemService;
    }

    @GetMapping
    public ResponseEntity<List<SellableItem>> getAllItems() {
        List<SellableItem> sellableItems = sellableItemService.findAll();
        return ResponseEntity.ok(sellableItems);
    }
}
