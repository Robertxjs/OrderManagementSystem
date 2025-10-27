package com.example.OrderManagementSystem.model;

public class Service extends SellableItem {
    private String Status; //Active,Down

    public Service(String id, String name, String status) {
        super(id, name);
        this.Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
