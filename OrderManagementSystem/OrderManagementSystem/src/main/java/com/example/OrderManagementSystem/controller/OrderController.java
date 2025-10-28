package com.example.OrderManagementSystem.controller;

import com.example.OrderManagementSystem.model.Order;
import com.example.OrderManagementSystem.model.OrderLine;
import com.example.OrderManagementSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get all orders
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Optional<Order> order = orderService.findById(id);
        return order.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    // Update order
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order order) {
        Optional<Order> existingOrder = orderService.findById(id);
        if (existingOrder.isPresent()) {
            order.setId(id);
            Order updatedOrder = orderService.save(order);
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        boolean deleted = orderService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



    // Request DTOs
    public static class CreateOrderRequest {
        private String name;
        private String customerId;
        private String contractId;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getCustomerId() { return customerId; }
        public void setCustomerId(String customerId) { this.customerId = customerId; }
        public String getContractId() { return contractId; }
        public void setContractId(String contractId) { this.contractId = contractId; }
    }

    public static class AddOrderLineRequest {
        private String itemId;
        private String unitId;
        private double quantity;

        public String getItemId() { return itemId; }
        public void setItemId(String itemId) { this.itemId = itemId; }
        public String getUnitId() { return unitId; }
        public void setUnitId(String unitId) { this.unitId = unitId; }
        public double getQuantity() { return quantity; }
        public void setQuantity(double quantity) { this.quantity = quantity; }
    }
}
