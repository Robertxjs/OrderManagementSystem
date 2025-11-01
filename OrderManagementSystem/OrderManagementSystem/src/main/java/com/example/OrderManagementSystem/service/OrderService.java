package com.example.OrderManagementSystem.service;

import com.example.OrderManagementSystem.model.*;
import com.example.OrderManagementSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private SellableItemRepository sellableItemRepository;

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(String id) {
        return orderRepository.findById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public boolean deleteById(String id) {
        return orderRepository.deleteById(id);
    }
}