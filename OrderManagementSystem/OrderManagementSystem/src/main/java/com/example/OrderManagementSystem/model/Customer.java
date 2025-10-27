package com.example.OrderManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String id;
    private String name;
    private String currency;
    private List<Order> orders;
    private List<Contract> contracts;

    public Customer() {
        this.orders = new ArrayList<>();
        this.contracts = new ArrayList<>();
    }

    public Customer(String id, String name, String currency) {
        this();
        this.id = id;
        this.name = name;
        this.currency = currency;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public void addOrder(Order order) {
        if (orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void addContract(Contract contract) {
        if (contracts == null) {
            contracts = new ArrayList<>();
        }
        contracts.add(contract);
    }
}
