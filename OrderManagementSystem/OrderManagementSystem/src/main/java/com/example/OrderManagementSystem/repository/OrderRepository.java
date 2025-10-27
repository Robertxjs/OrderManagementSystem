package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.Customer;
import com.example.OrderManagementSystem.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContractRepository contractRepository;

    private List<Order> orders = new ArrayList<>();

    public List<Order> findAll() {
        return orders;
    }

    public Optional<Order> findById(String id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id)).findFirst();
    }

    public Order save(Order order) {
        if (order.getId() == null) {
            int maxId = orders.stream()
                    .mapToInt(o -> Integer.parseInt(o.getId().substring(1)))
                    .max()
                    .orElse(0);
            order.setId("O" + String.format("%03d", maxId + 1));
        }

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                orders.set(i, order);
                return order;
            }
        }

        orders.add(order);
        return order;
    }

    public boolean deleteById(String id) {
        return orders.removeIf(order -> order.getId().equals(id));
    }
}