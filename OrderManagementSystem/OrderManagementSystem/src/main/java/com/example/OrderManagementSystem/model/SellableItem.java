package com.example.OrderManagementSystem.model;

public abstract class SellableItem implements IdentifiableLongId {
    private Long id;
    private String name;

    @Override
    public Long getLongId() {
        return id;
    }
    @Override
    public void setLongId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
