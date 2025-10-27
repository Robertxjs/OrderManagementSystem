package com.example.OrderManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private String id;
    private String name;
    private Customer customer;
    private Contract contract; // Optional
    private List<OrderLine> orderLines;

    public Order() {
        this.orderLines = new ArrayList<>();
    }

    public Order(String id, String name, Customer customer) {
        this();
        this.id = id;
        this.name = name;
        this.customer = customer;
    }

    public Order(String id, String name, Customer customer, Contract contract) {
        this(id, name, customer);
        this.contract = contract;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void addOrderLine(OrderLine orderLine) {
        if (orderLines == null) {
            orderLines = new ArrayList<>();
        }
        orderLines.add(orderLine);
    }

    public double getTotalValue() {
        return orderLines.stream()
                .mapToDouble(OrderLine::getTotalValue)
                .sum();
    }

    public boolean hasContract() {
        return contract != null;
    }
}
