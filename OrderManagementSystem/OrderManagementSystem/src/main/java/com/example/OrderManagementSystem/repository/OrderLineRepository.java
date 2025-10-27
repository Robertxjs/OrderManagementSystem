package com.example.OrderManagementSystem.repository;

import com.example.OrderManagementSystem.model.OrderLine;
import com.example.OrderManagementSystem.model.Product;
import com.example.OrderManagementSystem.model.Service;
import com.example.OrderManagementSystem.model.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderLineRepository {

    @Autowired
    private SellableItemRepo sellableItemRepository;

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    private List<OrderLine> orderLines = new ArrayList<>();

    public List<OrderLine> findAll() {
        return orderLines;
    }

    public Optional<OrderLine> findById(String id) {
        return orderLines.stream()
                .filter(line -> line.getId().equals(id))
                .findFirst();
    }

    public OrderLine save(OrderLine orderLine) {
        if (orderLine.getId() == null) {
            int maxId = orderLines.stream()
                    .mapToInt(ol -> Integer.parseInt(ol.getId().substring(2)))
                    .max()
                    .orElse(0);
            orderLine.setId("OL" + String.format("%03d", maxId + 1));
        }
        for (int i = 0; i < orderLines.size(); i++) {
            if (orderLines.get(i).getId().equals(orderLine.getId())) {
                orderLines.set(i, orderLine);
                return orderLine;
            }
        }

        orderLines.add(orderLine);
        return orderLine;
    }

    public boolean deleteById(String id) {
        return orderLines.removeIf(line -> line.getId().equals(id));
    }
}